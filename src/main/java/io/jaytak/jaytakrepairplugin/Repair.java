package io.jaytak.jaytakrepairplugin;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Repair implements CommandExecutor {

    private final Map<String, Material> repairMaterials = new HashMap<>();
    Logger logger = Logger.getLogger("Minecraft");

    public void loadRepairMaterials(FileConfiguration config) {
        repairMaterials.clear();
        for (String key : config.getConfigurationSection("repairMaterials").getKeys(false)) {
            String materialName = config.getString("repairMaterials." + key);
            try {
                Material material = Material.valueOf(materialName);
                repairMaterials.put(key, material);
            } catch (IllegalArgumentException e) {
                logger.warning("Invalid material specified for key: " + key);
            }
        }
    }

    @Override
    public boolean onCommand(@NonNull CommandSender sender,
                             @NonNull Command command,
                             @NonNull String label,
                             @SuppressWarnings("NullableProblems") String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("[JayTAK Repair] Only players can use this command!");
            return false;
        }

        Player player = (Player) sender;
        String username = player.getName();
        //Logger logger = Logger.getLogger("Minecraft");

        if (!player.hasPermission("jaytakrepairplugin.repair")) {
            player.sendMessage("[JayTAK Repair] You don't have permission to run this command.");
            return false;
        }

        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        if (itemInHand.getType() == Material.AIR) {
            player.sendMessage("[JayTAK Repair] You need to hold an item in your hand to use this command.");
            return false;
        }


        loadRepairMaterials(JayTAKRepairPlugin.config);


        String itemType = itemInHand.getType().toString();
        if (repairMaterials.containsKey(itemType)) {
            Material repairMaterial = repairMaterials.get(itemType);
            if (player.getInventory().contains(repairMaterial)) {
                player.getInventory().removeItem(new ItemStack(repairMaterial, 1));
                if (itemType.equals("DAMAGED_ANVIL") || itemType.equals("CHIPPED_ANVIL")){
                    player.getInventory().setItemInMainHand(new ItemStack(Material.ANVIL, 1));
                }else{
                    repairItem(player, itemInHand);
                }
                return true;
            } else {
                player.sendMessage("[JayTAK Repair] You don't have enough " + repairMaterial.name() + " to repair " + itemType);
                return false;
            }
        }
        else {
            player.sendMessage("[JayTAK Repair] Item " + itemType + " is not supported for repair.");
            return false;
        }
    }

    private void repairItem(Player player, ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (meta instanceof Damageable) {
            Damageable damageable = (Damageable) meta;
            if (damageable.getDamage() > 0) {
                damageable.setDamage(0);
                item.setItemMeta(meta);
                player.sendMessage("[JayTAK Repair] Item repaired successfully.");
            } else {
                player.sendMessage("[JayTAK Repair] Item does not need repairing.");
            }
        } else {
            player.sendMessage("[JayTAK Repair] Item cannot be repaired.");
        }
    }
}
