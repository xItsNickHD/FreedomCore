package server.AvalanceYT.FreedomCore.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import server.AvalancheYT.FreedomCore.Config.ConfigManager;
import server.AvalancheYT.FreedomCore.FreedomCore;

public class Command_fc implements CommandExecutor {
    
    private final FreedomCore plugin;
    
    public Command_fc(FreedomCore plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1) {
            if (!args[0].equalsIgnoreCase("reload")) {
                return false;
            }
            ConfigManager.getAdmin().reloadConfig();
            ConfigManager.getMConfig().reloadConfig();
            sender.sendMessage("Configuration reloaded");
            return true;
        }
        
        sender.sendMessage(ChatColor.DARK_RED + "===== FreedomHostingMod =====");
        sender.sendMessage(ChatColor.GREEN + "Running on: " + ConfigManager.getMConfig().getConfig().getString("server.name"));
        sender.sendMessage(ChatColor.GREEN + "Forum: " + ConfigManager.getMConfig().getConfig().getString("server.forum"));
        sender.sendMessage(ChatColor.GOLD + "Created by AvalancheYT and TaahThePhoenix");
        return true;
    }
}
