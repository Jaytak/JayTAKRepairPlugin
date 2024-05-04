package io.jaytak.jaytakrepairplugin;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashMap;
import java.util.Map;

public class Repair implements CommandExecutor {
    private static final Map<String, RepairMaterial> repairMaterials = new HashMap<>();
    private static JayTAKRepairPlugin plugin;

    public Repair(JayTAKRepairPlugin plugin){
        Repair.plugin = plugin;
    }
    public static void loadRepairMaterials(FileConfiguration config) {
        repairMaterials.clear();
        ConfigurationSection repairMaterialsSection = config.getConfigurationSection("repairMaterials");
        if (repairMaterialsSection != null) {
            for (String key : repairMaterialsSection.getKeys(false)) {
                ConfigurationSection repairMaterialSection = repairMaterialsSection.getConfigurationSection(key);
                if (repairMaterialSection != null) {
                    String materialName = repairMaterialSection.getString("material");
                    int amount = repairMaterialSection.getInt("amount", 1);
                    try {
                        Material material = Material.valueOf(materialName);
                        repairMaterials.put(key, new RepairMaterial(material, amount));
                    } catch (IllegalArgumentException e) {
                        plugin.getLogger().warning("[JayTAK Repair] config.yml error! Invalid material specified for key: " + key);
                        plugin.getLogger().info("[JayTAK Repair] will attempt to continue to load the remaining configuration.");
                    }
                }
            }
        }
    }

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, @SuppressWarnings("NullableProblems") String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("[JayTAK Repair] Only players can use this command!");
            return true;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("jaytakrepairplugin.repair")) {
            playerPrint(player, "You don't have permission to run this command.");
            return true;
        }
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        if (itemInHand.getType() == Material.AIR) {
            playerPrint(player, "You need to hold an item in your hand to use this command.");
            return true;
        }
        String itemType = itemInHand.getType().toString();
        if (repairMaterials.containsKey(itemType)) {
            RepairMaterial repairMaterial = repairMaterials.get(itemType);
            Material material = repairMaterial.getMaterial();
            int amountRequired = repairMaterial.getAmount();

            if (player.getInventory().contains(material, amountRequired)) {
                player.getInventory().removeItem(new ItemStack(material, amountRequired));
                if (itemType.equals("DAMAGED_ANVIL") || itemType.equals("CHIPPED_ANVIL")) {
                    player.getInventory().setItemInMainHand(new ItemStack(Material.ANVIL, 1));
                } else {
                    repairItem(player, itemInHand);
                }
                return true;
            } else {
                playerPrint(player, "You need " + amountRequired + " " + cleanOutput(material.name()) + " to repair " + cleanOutput(itemType));
                return true;
            }
        } else {
            playerPrint(player, "Item " + cleanOutput(itemType) + " is not supported for repair.");
            return true;
        }
    }
    private void repairItem(Player player, ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (meta instanceof Damageable) {
            Damageable damageable = (Damageable) meta;
            if (damageable.getDamage() > 0) {
                damageable.setDamage(0);
                item.setItemMeta(meta);
                playerPrint(player, "Item repaired successfully.");
            } else {
                playerPrint(player, "Item does not need repairing.");
            }
        } else {
            playerPrint(player, "Item cannot be repaired.");
        }
    }
    public static String cleanOutput(String input){
        return input.toLowerCase().replace("_", " ");
    }
    public void playerPrint(Player player, String input){
        player.sendMessage("[JayTAK Repair] " + input);
    }
}
