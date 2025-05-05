package xyz.nickelulz.calix.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

public abstract class CommandTemplate extends BukkitCommand implements CommandExecutor
{
    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        String permission = getPermission();
        if (permission != null && !sender.hasPermission(permission)) {
            sender.sendMessage(ChatColor.GRAY + "You do not have permission to use this command.");
            return true;
        }
    }

    protected abstract String getSpecializedSyntax(String mode);

    // protected void sendSyntax(CommandSender sender)
    
    protected void error(CommandSender sender, String err) {
        sender.sendMessage(ChatColor.RED + err);
    }
    
    protected void reply(CommandSender sender, String... messages) {
        for (String message: messages)
            sender.sendMessage(ChatColor.WHITE + message);
    }
    
    protected void success(CommandSender sender, String message) {
        sender.sendMessage(ChatColor.GREEN + message);
    }
}
