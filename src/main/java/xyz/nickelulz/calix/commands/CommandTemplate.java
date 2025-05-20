package xyz.nickelulz.calix.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import xyz.nickelulz.calix.Calix;

public abstract class CommandTemplate extends BukkitCommand
{
    private Calix pluginInstance;
    
    private int minArguments, maxArguments;
    private boolean playerOnly;
    private String description;

    protected abstract String  getSpecializedSyntax(String mode);
    protected abstract void    sendSyntax(CommandSender sender);
    public    abstract boolean commandCall(CommandSender sender, String[] args);

    public CommandTemplate(Calix instance, String command, int minArguments, int maxArguments,
			   boolean playerOnly, String description, String permission)
    {
        super(command);

	this.pluginInstance = instance;
        this.minArguments = minArguments;
        this.maxArguments = maxArguments;
        this.playerOnly = playerOnly;
        this.description = description;

	if (permission instanceof String) {
	    this.setPermission(permission);
	}
    }

    protected static enum CommandReturnType {
	SUCCESS, ERR_SYNTAX, ERR_PERMISSION,
	ERR_ARGUMENT, ERR_MISUSE, ERR_STATE, ERR_OTHER;
    }
    
    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
	// Mismatched argument length
	if (args.length < minArguments || (args.length > maxArguments && maxArguments != -1)) {
            sendSyntax(sender);
            return true;
        }

	// Invalid Permissions
        String permission = this.getPermission();
        if (permission != null && !sender.hasPermission(permission)) {
            sender.sendMessage(ChatColor.GRAY + "You do not have permission to use this command.");
            return true;
        }

	// Command is Player only
	if (playerOnly && !(sender instanceof Player)) {
            sender.sendMessage(ChatColor.GRAY + "Only plays can use this command.");
            return true;
        }

	// Attempt running the command
	commandResult = commandCall(sender, args);
	if (commandResult == CommandReturnType.ERR_SYNTAX)
	    sendSyntax(sender);
	
	return true;
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
