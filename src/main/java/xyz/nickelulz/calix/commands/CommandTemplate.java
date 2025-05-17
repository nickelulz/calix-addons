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
    private final int minArguments, maxArguments;
    private final boolean playerOnly;
    private final String description;

    protected abstract String getSpecializedSyntax(String mode);
    protected abstract void sendSyntax(CommandSender sender);

    public CommandBase(String command, int minArguments, int maxArguments, boolean playerOnly, String description) {
        super(command);

        this.minArguments = minArguments;
        this.maxArguments = maxArguments;
        this.playerOnly = playerOnly;
        this.description = description;
    }
    
    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        String permission = getPermission();
        if (permission != null && !sender.hasPermission(permission)) {
            sender.sendMessage(ChatColor.GRAY + "You do not have permission to use this command.");
            return true;
        }

	if (!onCommand(sender, args)) {
	    sendSyntax(sender);
	}

	return true;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	this.onCommand(sender, args);
    }
    
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
