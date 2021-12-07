package com.bermudalocket.stitchedterracotta;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StitchedTerracotta implements ModInitializer {

	public static final Logger LOGGER = LogManager.getLogger("stitchedterracotta");

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing StitchedTerracotta.");

		CommandRegistrationCallback.EVENT.register(this::registerCommands);
	}

	private void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
		CommandManager.literal("ac").executes(new AdminChatCommand());
	}

	public static class AdminChatCommand implements Command<ServerCommandSource> {
		@Override
		public int run(CommandContext context) throws CommandSyntaxException {

			return 0;
		}
	}

}
