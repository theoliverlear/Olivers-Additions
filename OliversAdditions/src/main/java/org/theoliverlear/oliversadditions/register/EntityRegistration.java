package org.theoliverlear.oliversadditions.register;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.theoliverlear.oliversadditions.OliversAdditionsMod;
import org.theoliverlear.oliversadditions.entities.PluckableChickenEntity;

public class EntityRegistration {
    private static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, OliversAdditionsMod.MODID);
    public static final RegistryObject<EntityType<PluckableChickenEntity>> PLUCKABLE_CHICKEN =
            ENTITY_TYPES.register("pluckable_chicken", () ->
                    EntityType.Builder.of(PluckableChickenEntity::new, EntityClassification.CREATURE)
                            .sized(0.4F, 0.7F)
                            .build(new ResourceLocation(OliversAdditionsMod.MODID, "pluckable_chicken").toString()));
    public static void register(IEventBus modEventBus) {
        ENTITY_TYPES.register(modEventBus);
    }
}
