package server.AvalancheYT.FreedomCore;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import server.AvalancheYT.FreedomCore.Config.ConfigManager;

public class RankManager {
    
    public enum Rank {
        OP("Op", ChatColor.RED + "[OP]"), SA("Super Admin", ChatColor.GOLD + "[SA]"), STA("Super Telnet Admin", ChatColor.DARK_GREEN + "[STA]"), SRA("Senior Admin", ChatColor.LIGHT_PURPLE + "[SrA]"), CONSOLE("Console", ChatColor.DARK_AQUA + "[Console]");
        
        private final String name;
        private final String prefix;
        
        private Rank(String name, String prefix) {
            this.name = name;
            this.prefix = prefix;
        }
        
        public String getName() {
            return name;
        }
        
        public String getPrefix() {
            return prefix;
        }
    }
    
    public static boolean isSuperAdmin(CommandSender sender) {
        Player player = (Player) sender;
        return ConfigManager.getAdmin().getConfig().contains(player.getUniqueId().toString());
    }
    
    public static boolean isTelnetAdmin(CommandSender sender) {
        Player player = (Player) sender;
        return ConfigManager.getAdmin().getConfig().getBoolean(player.getUniqueId().toString() + ".telnet");
    }
    
    public static boolean isSeniorAdmin(CommandSender sender) {
        Player player = (Player) sender;
        return ConfigManager.getAdmin().getConfig().getBoolean(player.getUniqueId().toString() + ".senior");
    } 
    
    public static Rank getRank(CommandSender sender) {
        if (!(sender instanceof Player)) {
            return Rank.CONSOLE;
        }
        
        if (isSuperAdmin((Player) sender)) {
            return Rank.SA;
        }
        
        if (isTelnetAdmin((Player) sender)) {
            return Rank.STA;
        }
        
        if (isSeniorAdmin((Player) sender)) {
            return Rank.SRA;
        }
        return Rank.OP;
    }
    
    public static void addRank(Player player) {
        ConfigManager.getAdmin().getConfig().set(player.getUniqueId().toString() + ".name", player.getName());
        ConfigManager.getAdmin().getConfig().set(player.getUniqueId().toString() + ".ip", player.getAddress().getHostString());
        ConfigManager.getAdmin().getConfig().set(player.getUniqueId().toString() + ".telnet", false);
        ConfigManager.getAdmin().getConfig().set(player.getUniqueId().toString() + ".senior", false);
        ConfigManager.getAdmin().getConfig().set(player.getUniqueId().toString() + ".tag", "");
        ConfigManager.getAdmin().saveConfig();
    }
}
