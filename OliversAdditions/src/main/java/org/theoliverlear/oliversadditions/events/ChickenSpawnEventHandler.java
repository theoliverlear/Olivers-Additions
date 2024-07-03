package org.theoliverlear.oliversadditions.events;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.util.concurrent.TickDelayedTask;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.theoliverlear.oliversadditions.OliversAdditionsMod;
import org.theoliverlear.oliversadditions.entities.PluckableChickenEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Mod.EventBusSubscriber(modid = OliversAdditionsMod.MODID,
                        bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ChickenSpawnEventHandler {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final List<Runnable> PENDING_TASKS = Collections.synchronizedList(new ArrayList<>());
    @SubscribeEvent
    public static void onChickenSpawnEvent(EntityJoinWorldEvent event) {
        LOGGER.info("ChickenSpawnEventHandler.onChickenSpawnEvent called");
        Entity entity = event.getEntity();

        if (entity instanceof ChickenEntity && !(entity instanceof PluckableChickenEntity) && !event.getWorld().isClientSide()) {
            Runnable task = () -> {
                ChickenEntity chicken = (ChickenEntity) entity;
                PluckableChickenEntity pluckableChicken = new PluckableChickenEntity(EntityType.CHICKEN, chicken.level);
                pluckableChicken.setPos(chicken.getX(), chicken.getY(), chicken.getZ());
                pluckableChicken.setDeltaMovement(chicken.getDeltaMovement());
                pluckableChicken.setCustomName(chicken.getCustomName());
                pluckableChicken.setCustomNameVisible(chicken.isCustomNameVisible());

                chicken.level.addFreshEntity(pluckableChicken);
                chicken.remove();
            };
            PENDING_TASKS.add(task);
        }
    }
    @SubscribeEvent
    public static void onServerTickEvent(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            synchronized (PENDING_TASKS) {
                for (Runnable task : PENDING_TASKS) {
                    task.run();
                }
                PENDING_TASKS.clear();
            }
        }
    }
}
