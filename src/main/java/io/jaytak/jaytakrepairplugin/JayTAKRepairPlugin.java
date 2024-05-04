package io.jaytak.jaytakrepairplugin;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public final class JayTAKRepairPlugin extends JavaPlugin {
    public static final Map<String, Material> repairMaterials = new HashMap<>();
    public static FileConfiguration config;
    private File configFile;

    @Override
    public void onEnable() {
        // Plugin startup logic
        int pluginId = 20963;
        metrics metrics = new metrics(this, pluginId);
        configLoad();
        super.onEnable();
        Objects.requireNonNull(getCommand("jaytakrepair")).setExecutor(new Repair());
        this.getLogger().info("JayTAK Repair Plugin Loaded");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        super.onDisable();
        this.getLogger().info("JayTAK Repair Plugin Exiting");
    }

    private void loadRepairMaterials() {
        repairMaterials.clear();
        for (String key : config.getKeys(false)) {
            if (!Objects.equals(key, "repairMaterials")){
                try {
                    Material material = Material.valueOf(config.getString(key));
                    repairMaterials.put(key, material);
                } catch (IllegalArgumentException e) {
                    Logger logger = Logger.getLogger("Minecraft");
                    logger.warning("Invalid material specified for key: " + key);
                }
            }
        }
    }

    private void configLoad(){
        configFile = new File("plugins/JayTAKRepair/config.yml");
        boolean fileCreated = false;
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            try {
                configFile.createNewFile();
                fileCreated = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(configFile);
        if (fileCreated){
            try{
                initializeDefaultConfig();
            }
            catch(Exception ex){
                getLogger().warning("Jaytak Repair Ran into exception: " + ex.toString());
            }
        }
        loadRepairMaterials();
    }

    private void initializeDefaultConfig() throws IOException {
        config.options().copyDefaults(true);
        config.addDefault("repairMaterials.DAMAGED_ANVIL", Material.IRON_BLOCK.name());
        config.addDefault("repairMaterials.CHIPPED_ANVIL", Material.IRON_INGOT.name());
        config.addDefault("repairMaterials.DIAMOND_PICKAXE", Material.DIAMOND.name());

        config.save(configFile);
    }
}
