package xyz.nickelulz.calix;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginLogger;
import java.util.logging.Logger;

import xyz.nickelulz.calix.commands.CommandManager;
import xyz.nickelulz.calix.datatypes.*;

public final class Calix extends JavaPlugin {
    private Logger log;

    // Databases
    private Table<PlayerData> players;
    private Table<Hit> hits;
    private Table<War> wars;
    private Table<Team> teams;
    
    /*
     * Duels get a memory-only temporary database and are
     * not saved to the hard disk due to their short-term
     * nature.
     */
    private Set<Duel> duels;
    
    @Override
    public void onEnable() 
    {
        log = getLogger();
        log.info("Enabling Calix...");

        Config config = new Config(this);
        SQLDatabase db = new SQLDatabase(config.DATABASE_FILENAME);

	// Persistent Storage
	players = new Table<PlayerData>(db, "users");
	hits    = new Table<Hit>(db, "hits");
	wars    = new Table<War>(db, "wars");
	teams   = new Table<Team>(db, "teams");

	// Temporary Storage
	duels   = new HashSet<Duel>();
        
        this.getServer.getPluginManager().registerEvents(this, this);
        this.saveDefaultConfig();
    
        CommandManager.initialize(this);
    
        log.info("Calix has been enabled!");
    }
    
    @Override
    public void onDisable() {
        log.info("Disabling Calix...");
    }

    @EventHandler
    public void onPlayerJoin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
    }
    
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player victim = (Player) event.getEntity();
    }

    /*
     * Getters/Setters 
     */
    public Table<PlayerData> getPlayerDatabase() { return players; }
    public Table<Hit> getHitsDatabase() { return hits; }
    public Table<War> getWarsDatabase() { return wars; }
    public Table<Team> getTeamDatabase() { return teams; }
    public Logger getLogger() { return log; }
}
