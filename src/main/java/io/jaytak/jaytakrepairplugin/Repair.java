package io.jaytak.jaytakrepairplugin;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import static org.bukkit.Bukkit.getLogger;


public class Repair implements CommandExecutor {
    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, @Nullable String[] args) {
        // Check if the command sender is a player
        if (!(sender instanceof Player)) {
            sender.sendMessage("[JayTAK Repair] Only players can use this command!");
            return false; // Command did not execute successfully.
        }

        Player player = (Player) sender;
        String username = player.getName();

        // Check player has permission to use the command.
        getLogger().info("[JayTAK Repair] " + sender.getName() + " issued the command " + command.getName());
        if (!player.hasPermission("jaytakrepairplugin.repair")){
            getLogger().info("[JayTAK Repair} " + sender.getName() + " doesnt have permission to run this command.");
            player.sendMessage("[JayTAK Repair] You dont have permission to run this command.");
            return false; // Command did not execute successfully.
        }


        ItemStack itemInHand = player.getInventory().getItemInMainHand();

        try {
            // Check if the item is not air (an actual item is held)
            if (itemInHand.getType() != Material.AIR) {
                getLogger().info("[JayTAK Repair] User Holding: " + itemInHand.getType());
                String item = itemInHand.getType().toString();

                // Non damageable items, like anvils:
                if (item.equals("DAMAGED_ANVIL")){
                    if (player.getInventory().contains(Material.IRON_BLOCK)) {
                        // Remove one item of the material from the player's inventory
                        player.getInventory().removeItem(new ItemStack(Material.IRON_BLOCK, 1));
                        // Repair Item.
                        //player.setItemInHand(new ItemStack(Material.ANVIL, 1));
                        player.getInventory().setItemInMainHand(new ItemStack(Material.ANVIL, 1));

                        player.sendMessage("[JayTAK Repair] Item repaired using one Iron Block.");
                        getLogger().info("[JayTAK Repair] Item repaired using one Iron Block.");
                        // Update players inventory.
                        //player.updateInventory();
                        return true;  // Command executed successfully.
                    }
                    else {
                        player.sendMessage("[JayTAK Repair] You don't have enough Iron Blocks to repair " + itemInHand.getType());
                        getLogger().info("[JayTAK Repair] Player " + username + " doesnt have enough Iron Blocks to repair " + itemInHand.getType());
                        return false; // Command did not execute successfully.
                    }
                }

                // Repairable/damageable Items:

                // Get items MetaData, then check if the MetaData is type Damageable.
                ItemMeta data = itemInHand.getItemMeta();
                if (data instanceof Damageable){
                    Damageable damageable = (Damageable) data; // Cast data as Damageable
                    int damageValue = damageable.getDamage(); // Store items current damage value
                    getLogger().info("[JayTAK Repair] Item Damage Value Is: " + damageValue);
                    if (damageValue > 0) { // If item has damage.
                        getLogger().info("[JayTAK Repair] Attempting to repair item.");


                        String[] parts = item.split("_");// Split the item name by _

                        String itemMaterial = parts[0];

                        switch(itemMaterial){

                            // Items to repair using OAK_PLANKS
                            case "FISHING":
                            case "BOW":
                            case "CROSSBOW":
                            case "WOODEN":
                                // Check players inventory contains base material for item in hand.
                                if (player.getInventory().contains(Material.OAK_PLANKS)) {
                                    // Remove one item of the material from the player's inventory
                                    player.getInventory().removeItem(new ItemStack(Material.OAK_PLANKS, 1));
                                    // Repair Item.
                                    ((Damageable) data).setDamage(0);
                                    itemInHand.setItemMeta(data);
                                    player.sendMessage("[JayTAK Repair] Item repaired using one Oak Planks.");
                                    getLogger().info("[JayTAK Repair] Item repaired using one Oak Planks.");
                                }
                                else {
                                    player.sendMessage("[JayTAK Repair] You don't have enough Oak Planks to repair " + itemInHand.getType());
                                    getLogger().info("[JayTAK Repair] Player " + username + " doesnt have enough Oak Planks to repair " + itemInHand.getType());
                                }
                                break;

                            // Items to repair using COBBLESTONE
                            case "STONE":
                                if (player.getInventory().contains(Material.COBBLESTONE)) {
                                    // Remove one item of the material from the player's inventory
                                    player.getInventory().removeItem(new ItemStack(Material.COBBLESTONE, 1));
                                    // Repair Item.
                                    ((Damageable) data).setDamage(0);
                                    itemInHand.setItemMeta(data);
                                    player.sendMessage("[JayTAK Repair] Item repaired using one Cobblestone.");
                                    getLogger().info("[JayTAK Repair] Item repaired using one Oak Cobblestone.");
                                }
                                else {
                                    player.sendMessage("[JayTAK Repair] You don't have enough Cobblestone to repair " + itemInHand.getType());
                                    getLogger().info("[JayTAK Repair] Player " + username + " doesnt have enough Cobblestone to repair " + itemInHand.getType());
                                }
                                break;

                            // Items to repair using IRON_INGOTS
                            case "SHIELD":
                            case "IRON":
                                if (player.getInventory().contains(Material.IRON_INGOT)) {
                                    // Remove one item of the material from the player's inventory
                                    player.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 1));
                                    // Repair Item.
                                    ((Damageable) data).setDamage(0);
                                    itemInHand.setItemMeta(data);
                                    player.sendMessage("[JayTAK Repair] Item repaired using one Iron Ingot.");
                                    getLogger().info("[JayTAK Repair] Item repaired using one Iron Ingot.");
                                }
                                else {
                                    player.sendMessage("[JayTAK Repair] You don't have enough Iron Ingots to repair " + itemInHand.getType());
                                    getLogger().info("[JayTAK Repair] Player " + username + " doesnt have enough Iron Ingots to repair " + itemInHand.getType());
                                }
                                break;

                            // Items to repair using GOLD_INGOTS
                            case "GOLD":
                                if (player.getInventory().contains(Material.GOLD_INGOT)) {
                                    // Remove one item of the material from the player's inventory
                                    player.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 1));
                                    // Repair Item.
                                    ((Damageable) data).setDamage(0);
                                    itemInHand.setItemMeta(data);
                                    player.sendMessage("[JayTAK Repair] Item repaired using one Gold Ingot.");
                                    getLogger().info("[JayTAK Repair] Item repaired using one Gold Ingot.");
                                }
                                else {
                                    player.sendMessage("[JayTAK Repair] You don't have enough Gold Ingots to repair " + itemInHand.getType());
                                    getLogger().info("[JayTAK Repair] Player " + username + " doesnt have enough Gold Ingots to repair " + itemInHand.getType());
                                }
                                break;

                            // Items to repair using DIAMONDS
                            case "DIAMOND":
                                if (player.getInventory().contains(Material.DIAMOND)) {
                                    // Remove one item of the material from the player's inventory
                                    player.getInventory().removeItem(new ItemStack(Material.DIAMOND, 1));
                                    // Repair Item.
                                    ((Damageable) data).setDamage(0);
                                    itemInHand.setItemMeta(data);
                                    player.sendMessage("[JayTAK Repair] Item repaired using one Diamond.");
                                    getLogger().info("[JayTAK Repair] Item repaired using one Diamond.");
                                }
                                else {
                                    player.sendMessage("[JayTAK Repair] You don't have enough Diamonds to repair " + itemInHand.getType());
                                    getLogger().info("[JayTAK Repair] Player " + username + " doesnt have enough Diamonds to repair " + itemInHand.getType());
                                }
                                break;

                            // Items to repair using NETHERITE_INGOTS
                            case "TRIDENT":
                            case "ELYTRA":
                            case "NETHERITE":
                                if (player.getInventory().contains(Material.NETHERITE_INGOT)) {
                                    // Remove one item of the material from the player's inventory
                                    player.getInventory().removeItem(new ItemStack(Material.NETHERITE_INGOT, 1));
                                    // Repair Item.
                                    ((Damageable) data).setDamage(0);
                                    itemInHand.setItemMeta(data);
                                    player.sendMessage("[JayTAK Repair] Item repaired using one Netherite Ingot.");
                                    getLogger().info("[JayTAK Repair] Item repaired using one Netherite Ingot.");
                                }
                                else {
                                    player.sendMessage("[JayTAK Repair] You don't have enough Netherite Ingots to repair " + itemInHand.getType());
                                    getLogger().info("[JayTAK Repair] Player " + username + " doesnt have enough Netherite to repair " + itemInHand.getType());
                                }
                                break;

                            // Any Other Item.
                            default:
                                player.sendMessage("[JayTAK Repair] Item " + itemMaterial + " currently not supported by the plugin, poke JayTAK...");
                                getLogger().info("[JayTAK Repair] Item " + itemMaterial + " currently not supported by the plugin, poke JayTAK...");
                                break;
                        }

                        // Update players inventory.
                        //player.updateInventory();
                    }
                    // Item does not have any damage.
                    else{
                        player.sendMessage("[JayTAK Repair] Item " + itemInHand.getType() + " does not need repairing!");
                        getLogger().info("[JayTAK Repair] Item " + itemInHand.getType() + " does not need repairing!");
                    }
                }
                // Item does not have any metaData.
                else{
                    player.sendMessage("[JayTAK Repair] Item does not have metadata!");
                    getLogger().info("[JayTAK Repair] Item does not have MetaData!");
                }
            }
            // Player not holding an item in their hand.
            else {
                player.sendMessage("[JayTAK Repair] You need to hold an item in your hand to use this command.");
                getLogger().info("[JayTAK Repair]  Player " + username + " doesnt have an item in their hand.");
            }
            return true; // Command executed successfully.
        }
        // Caught Exception
        catch (Exception exception){
            player.sendMessage("[JayTAK Repair] Caught an Exception, contact an admin to check the logs.");
            getLogger().info("[JayTAK Repair] Caught Exception: " + exception);
            //exception.printStackTrace();
        }
        return false; // Command did not execute successfully.
    }
}
