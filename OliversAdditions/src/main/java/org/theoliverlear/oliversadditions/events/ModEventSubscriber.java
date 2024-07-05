package org.theoliverlear.oliversadditions.events;
//=================================-Imports-==================================
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.theoliverlear.oliversadditions.OliversAdditionsMod;
import org.theoliverlear.oliversadditions.entities.PluckableChickenEntity;
import org.theoliverlear.oliversadditions.register.EntityRegistration;

@Mod.EventBusSubscriber(modid = OliversAdditionsMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {
    //=============================-Methods-==================================

    //-----------------On-Entity-Attribute-Creation-Event---------------------
    @SubscribeEvent
    public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
        event.put(EntityRegistration.PLUCKABLE_CHICKEN.get(), PluckableChickenEntity.createAttributes().build());
    }
}