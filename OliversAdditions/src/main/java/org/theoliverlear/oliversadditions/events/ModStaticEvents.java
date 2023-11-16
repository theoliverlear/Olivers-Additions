package org.theoliverlear.oliversadditions.events;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.theoliverlear.oliversadditions.items.TweezersItem;

public class ModStaticEvents {
    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
        itemRegistryEvent.getRegistry().register(new TweezersItem().setRegistryName("tweezers"));
    }
}
