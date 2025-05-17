package xyz.nickelulz.calix;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginLogger;
import java.util.logging.Logger;

import xyz.nickelulz.calix.commands.CommandManager;

public final class Calix extends JavaPlugin {
    private Logger log;
    
    @Override
    public void onEnable() 
    {   
	log = getLogger();
	log.info("Enabling Calix...")
    
	getServer.getPluginManager().registerEvents(this, this);
	this.saveDefaultConfig();
    
	CommandManager.initialize();
    
	log.info("Calix has been enabled!");
    }
    
    @Override
    public void onDisable()
    {
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
}
