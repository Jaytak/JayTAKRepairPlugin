# JayTAK Repair Plugin
<img src="https://cdn.modrinth.com/data/KNFBnUnH/66c81bb00e229dbfd771601b53e5f29bfc12f7b7.png" alt="plugin icon" width="150">

Version 2.0.4 (for paper 1.20.X)

V2.0.0 brings a config.yml so server admins can customise the plugins repair costs.
<br><br>
Simple Minecraft server repair plugin that allows players to repair the item they are holding in their hands for the price of one of the base material the item is made from.

Download from [Modrinth](https://modrinth.com/plugin/jaytak-repair)

>[!NOTE]
> As a full-time student, I cannot provide support for this plugin. I am only sharing this to GitHub in case someone else out there finds it useful. I will do my best to keep it up to date with the latest paper builds where possible.<br>
>Feel free to submit bugs and feature requests, but there is no guarantee I ever get around to fixing/implementing them.

##### Commands & Aliases:

```
/jaytakrepair
/jrepair
/jrep
/jr
```

##### Permission Nodes:

``
jaytakrepairplugin.repair
``

Grants the player access to the command.

#### Items & Materials:
You can alter the default repair items and amounts in the config.yml:
<details>
    <summary>Default config.yml (Spoiler)</summary>

    # JayTAK Repair Configuration File.
    repairMaterials:
        # Anvils:
        DAMAGED_ANVIL:
            material: IRON_BLOCK
            amount: 1
        CHIPPED_ANVIL:
            material: IRON_INGOT
            amount: 1
        
        # Leather Items:
        LEATHER_CAP:
            material: LEATHER
            amount: 1
        LEATHER_TUNIC:
            material: LEATHER
            amount: 1
        LEATHER_PANTS:
            material: LEATHER
            amount: 1
        LEATHER_BOOTS:
            material: LEATHER
            amount: 1
        LEATHER_HORSE_ARMOUR:
            material: LEATHER
            amount: 3
        
        # Chainmail Items:
        CHAINMAIL_HELMET:
            material: IRON_INGOT
            amount: 4
        CHAINMAIL_CHESTPLATE:
            material: IRON_INGOT
            amount: 4
        CHAINMAIL_LEGGINGS:
            material: IRON_INGOT
            amount: 4
        CHAINMAIL_BOOTS:
            material: IRON_INGOT
            amount: 4
        
        # Iron Items:
        IRON_HELMET:
            material: IRON_INGOT
            amount: 1
        IRON_CHESTPLATE:
            material: IRON_INGOT
            amount: 1
        IRON_LEGGINGS:
            material: IRON_INGOT
            amount: 1
        IRON_BOOTS:
            material: IRON_INGOT
            amount: 1
        IRON_HORSE_ARMOUR:
            material: IRON_INGOT
            amount: 3
        IRON_PICKAXE:
            material: IRON_INGOT
            amount: 1
        IRON_AXE:
            material: IRON_INGOT
            amount: 1
        IRON_SHOVEL:
            material: IRON_INGOT
            amount: 1
        IRON_SWORD:
            material: IRON_INGOT
            amount: 1
        IRON_HOE:
            material: IRON_INGOT
            amount: 1
        
        # Gold Items:
        GOLD_HELMET:
            material: GOLD_INGOT
            amount: 1
        GOLD_CHESTPLATE:
            material: GOLD_INGOT
            amount: 1
        GOLD_LEGGINGS:
            material: GOLD_INGOT
            amount: 1
        GOLD_BOOTS:
            material: GOLD_INGOT
            amount: 1
        GOLD_HORSE_ARMOUR:
            material: GOLD_INGOT
            amount: 3
        GOLD_PICKAXE:
            material: GOLD_INGOT
            amount: 1
        GOLD_AXE:
            material: GOLD_INGOT
            amount: 1
        GOLD_SHOVEL:
            material: GOLD_INGOT
            amount: 1
        GOLD_SWORD:
            material: GOLD_INGOT
            amount: 1
        GOLD_HOE:
            material: GOLD_INGOT
            amount: 1
        
        # Diamond Items:
        DIAMOND_HELMET:
            material: DIAMOND
            amount: 1
        DIAMOND_CHESTPLATE:
            material: DIAMOND
            amount: 1
        DIAMOND_LEGGINGS:
            material: DIAMOND
            amount: 1
        DIAMOND_BOOTS:
            material: DIAMOND
            amount: 1
        DIAMOND_HORSE_ARMOUR:
            material: DIAMOND
            amount: 3
        DIAMOND_PICKAXE:
            material: DIAMOND
            amount: 1
        DIAMOND_AXE:
            material: DIAMOND
            amount: 1
        DIAMOND_SHOVEL:
            material: DIAMOND
            amount: 1
        DIAMOND_SWORD:
            material: DIAMOND
            amount: 1
        DIAMOND_HOE:
            material: DIAMOND
            amount: 1
        
        # Netherite Items:
        NETHERITE_HELMET:
            material: NETHERITE_INGOT
            amount: 1
        NETHERITE_CHESTPLATE:
            material: NETHERITE_INGOT
            amount: 1
        NETHERITE_LEGGINGS:
            material: NETHERITE_INGOT
            amount: 1
        NETHERITE_BOOTS:
            material: NETHERITE_INGOT
            amount: 1
        NETHERITE_HORSE_ARMOUR:
            material: NETHERITE_INGOT
            amount: 3
        NETHERITE_PICKAXE:
            material: NETHERITE_INGOT
            amount: 1
        NETHERITE_AXE:
            material: NETHERITE_INGOT
            amount: 1
        NETHERITE_SHOVEL:
            material: NETHERITE_INGOT
            amount: 1
        NETHERITE_SWORD:
            material: NETHERITE_INGOT
            amount: 1
        NETHERITE_HOE:
            material: NETHERITE_INGOT
            amount: 1
        
        # Miscellaneous Items:
        TRIDENT:
            material: DIAMOND
            amount: 4
        SHIELD:
            material: IRON_INGOT
            amount: 1
        BOW:
            material: OAK_PLANKS
            amount: 1
        CROSSBOW:
            material: OAK_PLANKS
            amount: 2
        FISHING_ROD:
            material: STICK
            amount: 1
        ELYTRA:
            material: DIAMOND
            amount: 4
        FLINT_AND_STEEL:
            material: FLINT
            amount: 1

        # You can add any item that is damageable (has a damage bar) to this file.
        #
        # ITEM_NAME: WOODEN_SWORD < The name of the item you want to repair.
        #   material: DIRT < The name of the item you want to repair it with.
        #   amount: 20 < The amount of the repair item you want to use during repair.
        #
        # Custom Configuration Here:


</details>

### bStats

![Plugin bStats](https://bstats.org/signatures/bukkit/JayTAK%20Repair%20Plugin.svg)

