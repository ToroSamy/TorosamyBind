package net.torosamy.torosamyBind.utils

import net.torosamy.torosamyBind.TorosamyBind
import net.torosamy.torosamyBind.commands.AdminCommands
import net.torosamy.torosamyBind.commands.PlayerCommands
import net.torosamy.torosamyCore.commands.CommandManager

class CommandUtil {
    companion object {
        private val commanderManager: CommandManager = CommandManager(TorosamyBind.plugin)

        public val ADMIN_COMMANDS: AdminCommands = AdminCommands();
        public val PLAYER_COMMANDS: PlayerCommands = PlayerCommands();

        fun registerCommand() {
            commanderManager.annotationParser.parse(ADMIN_COMMANDS)
            commanderManager.annotationParser.parse(PLAYER_COMMANDS)
        }
    }
}