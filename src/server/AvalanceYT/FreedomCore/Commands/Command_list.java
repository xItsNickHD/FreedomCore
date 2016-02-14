package server.AvalanceYT.FreedomCore.Commands;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import server.AvalancheYT.FreedomCore.FreedomCore;
import server.AvalancheYT.FreedomCore.RankManager;

public class Command_list implements CommandExecutor {
    
    private final FreedomCore plugin;
    
    public Command_list(FreedomCore plugin) {
        this.plugin = plugin;
    }
    
    private enum Filter {
        ALL,
        ADMIN;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length > 1) {
            return false;
        }
        
        final Filter filter;
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("-a")) {
                filter = Filter.ADMIN;
            } else {
                return false;
            }
        } else {
            filter = Filter.ALL;
        }
        
        final List<String> names = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (filter == Filter.ADMIN && !RankManager.isSuperAdmin(player)) {
                continue;
            }
            
            names.add(RankManager.getRank(player).getPrefix() + player.getName());
        }
        
        sender.sendMessage(ChatColor.GREEN + "There are " + ChatColor.RED + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers() + ChatColor.GREEN + " players online!");
        sender.sendMessage("Connected players: ");
        sender.sendMessage(org.apache.commons.lang3.StringUtils.join(names, ChatColor.WHITE, ", "));
        return true;
    }
}
