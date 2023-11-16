package org.theoliverlear.oliversadditions.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;

public class HelloWorldCommand {
    public static void registerHelloWorldCommand(CommandDispatcher<CommandSource> cmdDispatcher) {
        cmdDispatcher.register(Commands.literal("helloworld")
                              .executes(cmdExecution -> {
            cmdExecution.getSource().sendSuccess(new StringTextComponent("Hello. World! Hey it worked!"), false);
            return 1;
        }));
    }
}
