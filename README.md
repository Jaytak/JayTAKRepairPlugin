# JayTAK Repair Plugin

Version 1.2.3

Only tested on paper 1.20.4 build 407, 409, 422

Simple minecraft server repair plugin that allows players to repair the item they are holding in their hands for the price of one of the base material the item is made from.
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
The following list of item use the associated material for repair.
```
Any item that starts with LEATHER:
LEATHER

Fishing Rod, Bow, CrossBow and any item that starts with WOODEN:
OAK_PLANKS

Any item starting with STONE:
COBBLESTONE

Shield, Chipped Anvil and any item that starts with IRON:
IRON_INGOT

Damaged Anvil:
IRON_BLOCK

Any item starting with GOLD:
GOLD_INGOT

Any item starting with DIAMOND:
DIAMOND

Trident, Elytra and any item starting with NETHERITE:
NETHERITE_INGOT
```

##### Changelog:<br>
V1.2.4
- Fixed bStats, hopefully.... 

V1.2.3
- Added Leather items.
- More robust exception handling.

V1.2.2
- Added Chipped Anvils.
- Implemented command check for future potential commands.

V1.2.1
- Removed duplicate console logs.
- Added @NonNullable annotations on parameter overrides.

V1.2.0
- Removed all deprecated methods.<br>
- Fixed Unhandled Exception when console ran command as I was trying to cast sender to player before checking if it was run by a player. Derp!<br>

v1.1.1
- bStats BORKED. Removed for now.<br>

V1.1.0
- Added bStats<br>

V1.0.1
- Update README.MD<br>

V1.0.0
- Minor bug fix, cleaned up the code a bit.<br>

V0.0.29-BETA
- Added Permission Node For Command.<br>

V0.0.21-ALPHA - V0.0.28-BETA 
- Removing essentials as a dependency.<br>

V0.0.1-ALPHA - V0.0.20-ALPHA 
- Getting the plugin to function.<br>



