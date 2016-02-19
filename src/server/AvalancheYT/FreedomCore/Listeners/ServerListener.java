package server.AvalancheYT.FreedomCore.Listeners;

import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import server.AvalancheYT.FreedomCore.Config.ConfigManager;
import server.AvalancheYT.FreedomCore.FUtil;
import server.AvalancheYT.FreedomCore.FreedomCore;

public class ServerListener implements Listener {
    
    public ServerListener() {
        Bukkit.getPluginManager().registerEvents(this, FreedomCore.plugin);
    }
    
    @EventHandler
    public void onServerListPing(ServerListPingEvent event) {
        if (Bukkit.hasWhitelist()) {
            event.setMotd(ChatColor.RED + "Whitelist enabled");
        }
        
        if (Arrays.asList(Bukkit.getMaxPlayers()).size() >= Bukkit.getMaxPlayers()) {
            event.setMotd(ChatColor.RED + "The server is full");
        }
        
        event.setMotd(FUtil.colorize(ConfigManager.getMConfig().getConfig().getString("server.motd")));
    }
}
