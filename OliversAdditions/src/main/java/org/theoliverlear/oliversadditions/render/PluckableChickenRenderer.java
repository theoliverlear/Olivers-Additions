package org.theoliverlear.oliversadditions.render;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.ChickenRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.theoliverlear.oliversadditions.entities.PluckableChickenEntity;

public class PluckableChickenRenderer extends ChickenRenderer {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final ResourceLocation PLUCKED_TEXTURE = new ResourceLocation("olivers_additions", "textures/entity/plucked_chicken.png");
    public static final ResourceLocation NORMAL_TEXTURE = new ResourceLocation("olivers_additions", "textures/entity/pluckable_chicken.png");
    public PluckableChickenRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
        LOGGER.info("PluckableChickenRenderer constructor called");
        LOGGER.info("PLUCKED TEXTURE PATH: {}", PLUCKED_TEXTURE.getPath());
        LOGGER.info("NORMAL TEXTURE PATH: {}", NORMAL_TEXTURE.getPath());
    }
    @MethodsReturnNonnullByDefault
    @Override
    public ResourceLocation getTextureLocation(ChickenEntity entity) {
        if (entity instanceof PluckableChickenEntity) {
//            LOGGER.info("Entity is PluckableChickenEntity");
            PluckableChickenEntity pluckable = (PluckableChickenEntity) entity;
            if (pluckable.getEntityData().get(PluckableChickenEntity.IS_PLUCKED)) {
                LOGGER.info("Using plucked texture");
                return PLUCKED_TEXTURE;
            }
        }
//        LOGGER.info("Using normal texture");
        return NORMAL_TEXTURE;
    }
}
