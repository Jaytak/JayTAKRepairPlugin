package io.jaytak.jaytakrepairplugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class JayTAKRepairPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        super.onEnable();
        getCommand("jaytakrepair").setExecutor(new Repair());
        this.getLogger().info("JayTAK Repair Plugin Loaded");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        super.onDisable();
        this.getLogger().info("JayTAK Repair Plugin Exiting");
    }
}
