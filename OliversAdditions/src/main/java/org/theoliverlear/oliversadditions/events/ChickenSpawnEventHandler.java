package org.theoliverlear.oliversadditions.events;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.theoliverlear.oliversadditions.OliversAdditionsMod;
import org.theoliverlear.oliversadditions.entities.PluckableChickenEntity;

@Mod.EventBusSubscriber(modid = OliversAdditionsMod.MODID,
                        bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ChickenSpawnEventHandler {
//    @SubscribeEvent
//    public static void onChickenSpawnEvent(EntityJoinWorldEvent joinWorldEvent) {
//        Entity entity = joinWorldEvent.getEntity();
//        boolean entityIsNull = entity == null;
//        boolean entityIsChicken = entity instanceof ChickenEntity;
//        boolean entityIsPluckableChicken = entity instanceof PluckableChickenEntity;
//        if (!entityIsNull && entityIsChicken && !entityIsPluckableChicken) {
//            ChickenEntity chickenEntity = (ChickenEntity) entity;
//            PluckableChickenEntity pluckableChickenEntity = new PluckableChickenEntity(EntityType.CHICKEN,
//                                                                                      chickenEntity.level);
//            pluckableChickenEntity.setPos(chickenEntity.getX(), chickenEntity.getY(), chickenEntity.getZ());
//            pluckableChickenEntity.setPlucked(false);
//            joinWorldEvent.getWorld().addFreshEntity(pluckableChickenEntity);
//            chickenEntity.remove();
//        }
//    }
}
