package org.theoliverlear.oliversadditions.register;
//=================================-Imports-==================================
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.theoliverlear.oliversadditions.OliversAdditionsMod;
import org.theoliverlear.oliversadditions.entities.PluckableChickenEntity;

@Mod.EventBusSubscriber(modid = OliversAdditionsMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityRegistration {
    //============================-Constants-=================================
    private static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, OliversAdditionsMod.MODID);
//    public static final RegistryObject<EntityType<PluckableChickenEntity>> PLUCKABLE_CHICKEN =
////            ENTITY_TYPES.register("pluckable_chicken", () ->
////                    EntityType.Builder.of(PluckableChickenEntity::new, EntityClassification.CREATURE)
////                            .sized(0.4F, 0.7F)
////                            .build(new ResourceLocation(OliversAdditionsMod.MODID, "pluckable_chicken").toString()));
public static final EntityType<PluckableChickenEntity> PLUCKABLE_CHICKEN =
        EntityType.Builder.of(PluckableChickenEntity::new, EntityClassification.CREATURE)
                .sized(0.4F, 0.7F)  // .size is replaced by .sized in newer versions as well
                .build("pluckable_chicken");
    //=============================-Methods-==================================

    //------------------------------Register----------------------------------
    public static void register(IEventBus modEventBus) {
        ENTITY_TYPES.register(modEventBus);
    }
    @SubscribeEvent
    public void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
        event.getRegistry().registerAll(
                EntityRegistration.PLUCKABLE_CHICKEN.setRegistryName("minecraft", "chicken")
        );
    }
}
