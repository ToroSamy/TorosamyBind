package net.torosamy.torosamyBind.utils

import net.torosamy.torosamyCore.TorosamyCore
import net.torosamy.torosamyBind.commands.PlayerCommands
import net.torosamy.torosamyBind.commands.AdminCommands

class CommandUtil {
    companion object {
        fun registerCommand() {
            TorosamyCore.commanderManager.annotationParser.parse(PlayerCommands())
            TorosamyCore.commanderManager.annotationParser.parse(AdminCommands())
        }
    }
}