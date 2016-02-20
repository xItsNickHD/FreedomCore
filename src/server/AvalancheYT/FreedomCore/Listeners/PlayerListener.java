package server.AvalancheYT.FreedomCore.Listeners;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import server.AvalancheYT.FreedomCore.FreedomCore;
import server.AvalancheYT.FreedomCore.RankManager;

public class PlayerListener implements Listener 
{

    public PlayerListener(FreedomCore plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        
        if (RankManager.isAdmin(player)) {
            if (RankManager.isSuperAdmin(player)) {
                event.setJoinMessage(ChatColor.AQUA + " is a " + ChatColor.GOLD + "Super Admin!");
            }
            if (RankManager.isTelnetAdmin(player)) {
                event.setJoinMessage(ChatColor.AQUA + " is a " + ChatColor.DARK_GREEN + "Telnet Admin");
            }
            if (RankManager.isSeniorAdmin(player)) {
                event.setJoinMessage(ChatColor.AQUA + " is a " + ChatColor.LIGHT_PURPLE + "Senior Admin!");
            }
        }
    }
    
}
