package net.torosamy.torosamyBind.utils

import net.torosamy.torosamyBind.TorosamyBind
import net.torosamy.torosamyBind.listener.BindListener


class ListenerUtil {
    companion object{
        fun registerListener() {
            TorosamyBind.plugin.server.pluginManager.registerEvents(BindListener(),TorosamyBind.plugin)
        }
    }
}