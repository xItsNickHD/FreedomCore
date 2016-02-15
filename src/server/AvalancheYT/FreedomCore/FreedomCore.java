package server.AvalancheYT.FreedomCore;

import java.util.logging.Level;
import org.bukkit.plugin.java.JavaPlugin;
import server.AvalancheYT.FreedomCore.Commands.Command_admin;
import server.AvalancheYT.FreedomCore.Commands.Command_fc;
import server.AvalancheYT.FreedomCore.Commands.Command_list;
import server.AvalancheYT.FreedomCore.Commands.Command_op;
import server.AvalancheYT.FreedomCore.Config.ConfigManager;

public class FreedomCore extends JavaPlugin {
    public static FreedomCore plugin;
    
    @Override
    public void onLoad() {
        plugin = this;
    }
    
    @Override
    public void onEnable() {
        this.register();
        getLogger().log(Level.INFO, "FreedomCore enabled! Created by AvalancheYT and TaahThePhoenix");
    }
    
    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "FreedomCore disabled!");
    }
    
    public void register() {
        new ConfigManager();
        this.getCommand("list").setExecutor(new Command_list(this));
        this.getCommand("fc").setExecutor(new Command_fc(this));
        this.getCommand("admin").setExecutor(new Command_admin(this));
        this.getCommand("op").setExecutor(new Command_op(this));
    }
}
