package server.AvalancheYT.FreedomCore.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import server.AvalancheYT.FreedomCore.Config.ConfigManager;
import server.AvalancheYT.FreedomCore.FreedomCore;
import server.AvalancheYT.FreedomCore.RankManager;

public class PlayerListener implements Listener {

    public PlayerListener() {
        Bukkit.getPluginManager().registerEvents(this, FreedomCore.plugin);
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        if (ConfigManager.getAdmin().getConfig().getString(player.getUniqueId().toString() + ".name").equals(player.getName()) && !ConfigManager.getAdmin().getConfig().getString(player.getUniqueId().toString() + ".ip").equals(player.getAddress().getHostString())) {
            RankManager.impostors.add(player.getName());
            event.setJoinMessage(ChatColor.RED + player.getName() + " is an impostor");
            for (Player admins : Bukkit.getOnlinePlayers()) {
                if (RankManager.isAdmin(admins)) {
                    admins.sendMessage(ChatColor.GREEN + player.getName() + " is an impostor! Take action.");
                }
            }
        }
    }
    
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        final Player player = event.getPlayer();
        if (RankManager.impostors.contains(player.getName())) {
            player.sendMessage(ChatColor.RED + "You may not move when frozen");
            player.teleport(player.getLocation());
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        final Player player = event.getPlayer();
        if (RankManager.impostors.contains(player.getName())) {
            player.sendMessage(ChatColor.RED + "You may not teleport when frozen");
            player.teleport(player.getLocation());
            event.setCancelled(true);
        }
    }
}
