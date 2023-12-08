package org.theoliverlear.oliversadditions;

import net.minecraft.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
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

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(OliversAdditionsMod.MODID)
public class OliversAdditionsMod {
    public static final String MODID = "olivers_additions";
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public OliversAdditionsMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ItemRegistration.register(modEventBus);
        EntityRegistration.register(modEventBus);
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::dispatchInterModComms);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processInterModComms);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::handleClientSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }
    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
    private void handleClientSetup(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().options);
    }
    private void dispatchInterModComms(final InterModEnqueueEvent event) {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("olivers_additions", "helloworld",
                () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }
    private void processInterModComms(final InterModProcessEvent event) {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
        HelloWorldCommand.registerHelloWorldCommand(event.getServer().getCommands().getDispatcher());
        // Register tweezers item

    }

    // You can use EventBusSubscriber to automatically subscribe events
    // on the contained class (this is subscribing to the MOD Event bus for
    // receiving Registry Events).
}
