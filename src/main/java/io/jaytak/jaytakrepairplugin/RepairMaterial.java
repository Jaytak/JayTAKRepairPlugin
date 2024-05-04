package io.jaytak.jaytakrepairplugin;

import org.bukkit.Material;

public class RepairMaterial {
    private final Material material;
    private final int amount;

    public RepairMaterial(Material material, int amount){
        this.material = material;
        this.amount = amount;
    }

    public Material getMaterial(){
        return material;
    }
    public int getAmount(){
        return amount;
    }
}
