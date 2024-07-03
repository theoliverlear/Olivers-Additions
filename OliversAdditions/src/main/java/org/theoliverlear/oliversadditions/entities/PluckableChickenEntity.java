package org.theoliverlear.oliversadditions.entities;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Getter
@Setter
public class PluckableChickenEntity extends ChickenEntity {
    private static final Logger LOGGER = LogManager.getLogger();
    private boolean isPlucked = false;
    public PluckableChickenEntity(EntityType<? extends ChickenEntity> chickenType,
                                  World fromWorld) {
        super(chickenType, fromWorld);
        LOGGER.info("PluckableChickenEntity constructor called");
    }
}
