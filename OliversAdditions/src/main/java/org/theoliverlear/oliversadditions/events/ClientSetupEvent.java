package org.theoliverlear.oliversadditions.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.theoliverlear.oliversadditions.OliversAdditionsMod;
import org.theoliverlear.oliversadditions.entities.PluckableChickenEntity;
import org.theoliverlear.oliversadditions.register.EntityRegistration;
import org.theoliverlear.oliversadditions.render.PluckableChickenRenderer;

@Mod.EventBusSubscriber(modid = OliversAdditionsMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetupEvent {

//    @SubscribeEvent
//    public static void clientSetup(FMLClientSetupEvent event) {
//        RenderingRegistry.registerEntityRenderingHandler(EntityRegistration.PLUCKABLE_CHICKEN.get(), PluckableChickenRenderer::new);
//    }
    @SubscribeEvent
    public static void onClientSetupEvent(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistration.PLUCKABLE_CHICKEN.get(), PluckableChickenRenderer::new);
    }
}
