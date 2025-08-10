package net.torosamy.torosamyBind.utils

import net.torosamy.torosamyBind.TorosamyBind
import net.torosamy.torosamyBind.listener.BindListener
import net.torosamy.torosamyCore.utils.NbtUtil
import org.bukkit.inventory.ItemStack


class ListenerUtil {
    companion object{
        const val OWNER_NAME_KEY = "ownerName"
        fun registerListener() {
            TorosamyBind.plugin.server.pluginManager.registerEvents(BindListener(),TorosamyBind.plugin)
        }

    }
}