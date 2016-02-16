package server.AvalancheYT.FreedomCore;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import server.AvalancheYT.FreedomCore.Config.ConfigManager;

public class RankManager {
    
    public enum Rank {
        NON("Non-Op", ChatColor.YELLOW + ""), OP("Op", ChatColor.RED + "[OP]"), SA("Super Admin", ChatColor.GOLD + "[SA]"), STA("Super Telnet Admin", ChatColor.DARK_GREEN + "[STA]"), SRA("Senior Admin", ChatColor.LIGHT_PURPLE + "[SrA]"), CONSOLE("Console", ChatColor.DARK_AQUA + "[Console]");
        
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
    
    public static boolean isAdmin(CommandSender sender) {
        Player player = (Player) sender;
        return ConfigManager.getAdmin().getConfig().contains(player.getUniqueId().toString());
    }
    
    public static boolean isSuperAdmin(CommandSender sender) {
        Player player = (Player) sender;
        return ConfigManager.getAdmin().getConfig().getString(player.getUniqueId().toString() + ".admin").equalsIgnoreCase("super admin");
    }
    
    public static boolean isTelnetAdmin(CommandSender sender) {
        Player player = (Player) sender;
        return ConfigManager.getAdmin().getConfig().getString(player.getUniqueId().toString() + ".admin").equalsIgnoreCase("telnet admin");
    }
    
    public static boolean isSeniorAdmin(CommandSender sender) {
        Player player = (Player) sender;
        return ConfigManager.getAdmin().getConfig().getString(player.getUniqueId().toString() + ".admin").equalsIgnoreCase("senior admin");
    } 
    
    public static Rank getRank(CommandSender sender) {
        if (!(sender instanceof Player)) {
            return Rank.CONSOLE;
        }
        
        final Player player = (Player) sender;
        
        if (isSuperAdmin(player)) {
            return Rank.SA;
        }
        
        if (isTelnetAdmin(player)) {
            return Rank.STA;
        }
        
        if (isSeniorAdmin(player)) {
            return Rank.SRA;
        }
        
        if (player.isOp()) {
            return Rank.OP;
        }
        return Rank.NON;
    }
}
