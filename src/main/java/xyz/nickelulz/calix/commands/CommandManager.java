package xyz.nickelulz.calix.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.help.GenericCommandHelpTopic;
import org.bukkit.help.HelpMap;
import org.bukkit.plugin.SimplePluginManager;

public class CommandManager
{
    /* TODO: List of Commands */
    
    public static CommandMap getCommandMap() {
        try {
            if (Bukkit.getPluginManager() instanceof SimplePluginManager) {
                Field field = SimplePluginManager.class.getDeclaredField("commandMap");
                field.setAccessible(true);

                return (CommandMap) field.get(Bukkit.getPluginManager());
            }
        } catch(NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void registerCommand(String commandLabel, Command command) {
        CommandMap commandMap = getCommandMap();

        if (commandMap != null) {
            commandMap.register(commandLabel, command);
        }
	
        HelpMap helpMap = Bukkit.getHelpMap();
        helpMap.addTopic(new GenericCommandHelpTopic(command));
    }

    public static void initialize(Calix instance) {
        /* TODO: Initialize All Commands */
    }
}
