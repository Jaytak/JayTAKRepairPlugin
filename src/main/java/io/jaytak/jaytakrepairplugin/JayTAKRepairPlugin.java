package io.jaytak.jaytakrepairplugin;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Objects;

public final class JayTAKRepairPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        int pluginId = 20963;
        metrics metrics = new metrics(this, pluginId);

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
}
