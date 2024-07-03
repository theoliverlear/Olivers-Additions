package org.theoliverlear.oliversadditions.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Consumer;

public class TweezersItem extends Item {
    private static final Logger LOGGER = LogManager.getLogger();
    public TweezersItem() {
        super(new Item.Properties().durability(10).tab(ItemGroup.TAB_TOOLS));
    }
    @Override
    public ActionResultType interactLivingEntity(ItemStack stack, PlayerEntity player, LivingEntity target, Hand hand) {
        LOGGER.info("TweezersItem.interactLivingEntity called");
        if (!player.level.isClientSide && target instanceof ChickenEntity) {
            ChickenEntity chicken = (ChickenEntity) target;
            player.level.addFreshEntity(new ItemEntity(player.level, chicken.getX(), chicken.getY(), chicken.getZ(), new ItemStack(Items.FEATHER)));
            // Decrease item durability
            stack.setDamageValue(stack.getDamageValue() + 1);
            if (stack.getDamageValue() >= stack.getMaxDamage()) {
                player.level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_BREAK, SoundCategory.PLAYERS, 1.0F, 1.0F);
                stack.shrink(1);
            }
            return ActionResultType.SUCCESS;
        }
        return super.interactLivingEntity(stack, player, target, hand);
    }
    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        if (entity instanceof PlayerEntity && ((PlayerEntity)entity).abilities.instabuild) {
            return 0;
        }
        if (this.isDamageable(stack)) {
            if (amount > 0) {
                stack.setDamageValue(stack.getDamageValue() + amount);

                if (stack.getDamageValue() >= stack.getMaxDamage()) {
                    onBroken.accept(entity);
                    stack.shrink(1);
                }
            }
        }
        return amount;
    }
    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }
    @Override
    public boolean isDamageable(ItemStack stack) {
        return stack.getDamageValue() < stack.getMaxDamage();
    }
    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack stack = itemStack.copy();
        stack.setDamageValue(stack.getDamageValue() + 1);
        return stack;
    }
}