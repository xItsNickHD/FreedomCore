package server.AvalancheYT.FreedomCore.Listeners;

import net.minecraft.server.v1_8_R3.ServerPing;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import server.AvalancheYT.FreedomCore.FreedomCore;

public class ServerListener implements Listener {
    public ServerListener(FreedomCore plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onServerPing(ServerListPingEvent event) {
        final String ip = event.getAddress().getHostAddress();
        
        if (Bukkit.hasWhitelist()) {
            event.setMotd(ChatColor.RED + "Whitelist is enabled");
            return;
        }
        
        if (Bukkit.getOnlinePlayers().size() >= Bukkit.getMaxPlayers())
        {
            event.setMotd(ChatColor.RED + "Server is full");
            return;
        } else {
            event.setMotd(ChatColor.DARK_RED + FreedomCore.plugin.getConfig().getString("server.name")+ ChatColor.DARK_GRAY + " - " + ChatColor.BLUE + "Spigot " + ChatColor.GOLD + "1.8.3");
            return;
        }
        
        
    }
    
    }
