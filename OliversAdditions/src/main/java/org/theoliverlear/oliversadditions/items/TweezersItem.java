package org.theoliverlear.oliversadditions.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class TweezersItem extends Item {
    public TweezersItem() {
        super(new Item.Properties().stacksTo(1).defaultDurability(10).tab(ItemGroup.TAB_TOOLS));
    }
    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }
    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack stack = itemStack.copy();
        stack.setDamageValue(stack.getDamageValue() + 1);
        return stack;
    }
}
