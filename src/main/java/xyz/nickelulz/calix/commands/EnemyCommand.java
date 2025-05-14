package xyz.nickelulz.calix.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

public final class EnemyCommand extends CommandTemplate 
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (args.length != 2) {
            sender.sendMessage(ChatColor.GRAY + command.getUsage());
            return true;
        }
        
        String mode = args[0];
        switch (mode) {
            case "add": {
                return true;
            }
                
            case "remove": {
                return true;
            }
        }

        sender.sendMessage(ChatColor.GRAY + command.getUsage());
        return true;
    }
}