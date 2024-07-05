package org.theoliverlear.oliversadditions;
//=================================-Imports-==================================
import net.minecraft.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.theoliverlear.oliversadditions.command.HelloWorldCommand;
import org.theoliverlear.oliversadditions.register.EntityRegistration;
import org.theoliverlear.oliversadditions.register.ItemRegistration;
import org.theoliverlear.oliversadditions.render.PluckableChickenRenderer;

import java.util.stream.Collectors;

@Mod(OliversAdditionsMod.MODID)
public class OliversAdditionsMod {
    //============================-Constants-=================================
    public static final String MODID = "olivers_additions";
    private static final Logger LOGGER = LogManager.getLogger();
    //===========================-Constructors-===============================
    public OliversAdditionsMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ItemRegistration.register(modEventBus);
        EntityRegistration.register(modEventBus);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::initSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::dispatchInterModComms);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processInterModComms);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::handleClientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistration);
        MinecraftForge.EVENT_BUS.register(this);
    }
    //=============================-Methods-==================================

    //------------------------Client-Registration-----------------------------
    private void clientRegistration(final FMLClientSetupEvent event) {
        LOGGER.info("CREATING PLUCKABLE CHICKEN RENDERER");
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistration.PLUCKABLE_CHICKEN.get(), PluckableChickenRenderer::new);
    }
    //-----------------------------Init-Setup---------------------------------
    private void initSetup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
    //------------------------Handle-Client-Setup-----------------------------
    private void handleClientSetup(final FMLClientSetupEvent event) {
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().options);
    }
    //------------------Dispatch-Intermod-Communication-----------------------
    private void dispatchInterModComms(final InterModEnqueueEvent event) {
        InterModComms.sendTo("olivers_additions", "helloworld",
                () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }
    //-------------------Process-Intermod-Communication-----------------------
    private void processInterModComms(final InterModProcessEvent event) {
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    //-------------------------On-Server-Starting-----------------------------
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
        HelloWorldCommand.registerHelloWorldCommand(event.getServer().getCommands().getDispatcher());
    }
}
