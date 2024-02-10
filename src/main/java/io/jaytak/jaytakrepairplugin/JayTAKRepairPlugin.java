package io.jaytak.jaytakrepairplugin;

import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public final class JayTAKRepairPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        // Load bStats
        int pluginId = 20963;
        Metrics metrics = new Metrics(this, pluginId);

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
