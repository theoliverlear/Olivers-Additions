package org.theoliverlear.oliversadditions.events;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.theoliverlear.oliversadditions.OliversAdditionsMod;
import org.theoliverlear.oliversadditions.entities.PluckableChickenEntity;

@Mod.EventBusSubscriber(modid = OliversAdditionsMod.MODID,
                        bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModDynamicEvents {
    @SubscribeEvent
    public static void onChickenRightClick(PlayerInteractEvent.EntityInteract entityInteractEvent) {
        if (entityInteractEvent.getTarget() instanceof PluckableChickenEntity && entityInteractEvent.getItemStack().getItem() == OliversAdditionsMod.TWEEZERS) {
            PluckableChickenEntity chicken = (PluckableChickenEntity) entityInteractEvent.getTarget();
            PlayerEntity player = entityInteractEvent.getPlayer();
            ItemStack tweezers = entityInteractEvent.getItemStack();
            if (!chicken.isBaby() && chicken.canPluck()) {
                // chicken.setPlucked(true);
                player.swing(entityInteractEvent.getHand());
                chicken.playSound(SoundEvents.CHICKEN_HURT, 1.0F, 1.0F);
                // player.playSound(SoundEvents.CHICKEN_EGG, 1.0F, 1.0F);
                if (!player.isCreative()) {
                    tweezers.hurtAndBreak(1, player, (playerEntity) -> {
                        playerEntity.broadcastBreakEvent(entityInteractEvent.getHand());
                    });
                }
                chicken.spawnAtLocation(new ItemStack(Items.FEATHER, 0));
                entityInteractEvent.setCancellationResult(ActionResultType.SUCCESS);
                entityInteractEvent.setCanceled(true);
            }
        }
    }
    @SubscribeEvent
    public void onChickenSpawn(EntityJoinWorldEvent entityCreatedEvent) {
        if (entityCreatedEvent.getEntity() instanceof ChickenEntity) {
            ChickenEntity chicken = (ChickenEntity) entityCreatedEvent.getEntity();
            PluckableChickenEntity pluckableChicken = new PluckableChickenEntity(EntityType.CHICKEN,
                                                                                 chicken.getCommandSenderWorld());
            pluckableChicken.copyPosition(chicken);
            pluckableChicken.setPlucked(false);
            entityCreatedEvent.getWorld().addFreshEntity(pluckableChicken);
            chicken.remove();
        }
    }
}

