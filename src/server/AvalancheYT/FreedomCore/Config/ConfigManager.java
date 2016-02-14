package server.AvalancheYT.FreedomCore.Config;

import server.AvalancheYT.FreedomCore.FreedomCore;

public class ConfigManager {
    
    private static FConfig admins;
    private static FConfig mconfig;
    
    public ConfigManager() {
        admins = new FConfig(FreedomCore.plugin, "admins.yml");
        admins.saveConfig();
        mconfig = new FConfig(FreedomCore.plugin, "config.yml");
        mconfig.saveConfig();
    }
    
    public static FConfig getAdmin() {
        return admins;
    }
    
    public static FConfig getMConfig() {
        return mconfig;
    }
}
