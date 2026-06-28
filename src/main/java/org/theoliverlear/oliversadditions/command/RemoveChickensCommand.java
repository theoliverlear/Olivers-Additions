package org.theoliverlear.oliversadditions.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.entity.Entity;

public class RemoveChickensCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> command = Commands.literal("removeAllChickens")
                .requires((commandSource) -> commandSource.hasPermission(2))  // Example permission level
                .executes(context -> removeAllChickens(context.getSource()));

        dispatcher.register(command);
    }

    private static int removeAllChickens(CommandSource source) {
        ServerWorld world = source.getLevel();

        world.getEntities()
                .filter(entity -> entity.getType().equals(EntityType.CHICKEN))
                .forEach(Entity::remove);

        if (source.getEntity() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) source.getEntity();
            player.sendMessage(new StringTextComponent("All chickens have been removed.")
                    .withStyle(TextFormatting.GREEN), player.getUUID());
        }

        return 1;  // Command was successful
    }
}
