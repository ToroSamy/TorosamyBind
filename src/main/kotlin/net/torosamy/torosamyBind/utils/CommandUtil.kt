package net.torosamy.torosamyBind.utils

import net.torosamy.torosamyCore.TorosamyCore
import net.torosamy.torosamyBind.TorosamyBind
import net.torosamy.torosamyBind.commands.PlayerCommands

class CommandUtil {
    companion object {
        private var torosamyCorePlugin: TorosamyCore = TorosamyBind.plugin.server.pluginManager.getPlugin("TorosamyCore") as TorosamyCore
        fun registerCommand() {
            torosamyCorePlugin.getCommandManager().annotationParser.parse(PlayerCommands())
        }
    }
}