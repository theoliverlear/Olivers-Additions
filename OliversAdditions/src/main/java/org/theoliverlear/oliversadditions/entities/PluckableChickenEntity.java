package org.theoliverlear.oliversadditions.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.world.World;

public class PluckableChickenEntity extends ChickenEntity {
    private boolean isPlucked = false;
    public PluckableChickenEntity(EntityType<? extends ChickenEntity> chickenType, World fromWorld) {
        super(chickenType, fromWorld);
    }
    public boolean canPluck() {
        return !this.isPlucked;
    }
    public void setPlucked(boolean isPlucked) {
        this.isPlucked = isPlucked;
    }

}
