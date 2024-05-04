package io.jaytak.jaytakrepairplugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.util.Objects;

public final class JayTAKRepairPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        int pluginId = 20963;
        @SuppressWarnings("unused")
        metrics metrics = new metrics(this, pluginId);
        configLoad();
        super.onEnable();
        Objects.requireNonNull(getCommand("jaytakrepair")).setExecutor(new Repair(this));
        this.getLogger().info("[JayTAK Repair] Plugin Loaded");
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.getLogger().info("[JayTAK Repair] Plugin Exiting");
    }

    private void configLoad() {
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            saveResource("config.yml", false);
        }
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        Repair.loadRepairMaterials(config);
    }
}
