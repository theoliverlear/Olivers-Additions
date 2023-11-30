package org.theoliverlear.oliversadditions.register;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.theoliverlear.oliversadditions.OliversAdditionsMod;
import org.theoliverlear.oliversadditions.items.TweezersItem;


public class ItemRegistration {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, OliversAdditionsMod.MODID);
    public static final RegistryObject<Item> TWEEZERS = ITEMS.register("tweezers", TweezersItem::new);
    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
