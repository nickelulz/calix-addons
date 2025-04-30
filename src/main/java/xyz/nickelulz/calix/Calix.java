package xyz.nickelulz.calix;
import xyz.nickelulz.calix.commands.TestCommand;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginLogger;
    
import java.util.logging.Logger;

public final class Calix extends JavaPlugin {
  private Logger log;

  @Override
  public void onEnable() 
  {
    log = getLogger();
    getCommand("test").setExecutor(new TestCommand());
    log.info("We have been enabled!");
  }

  @Override
  public void onDisable()
  {
    log.info("Ending Plugin..");
  }
}
