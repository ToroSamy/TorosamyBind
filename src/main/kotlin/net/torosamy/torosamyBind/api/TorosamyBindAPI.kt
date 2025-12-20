package net.torosamy.torosamyBind.api

import net.torosamy.torosamyCore.utils.NbtUtil
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

object TorosamyBindAPI {
    private const val OWNER_NAME_KEY = "ownerName"
    
    fun hasOwner(item: ItemStack): Boolean {
        val ownerName = NbtUtil.getString(item, OWNER_NAME_KEY);

        return !ownerName.isNullOrEmpty();
    }

    fun getOwner(item: ItemStack): String {
        val ownerName = NbtUtil.getString(item, OWNER_NAME_KEY) ?: return ""
        
        return ownerName
    }

    fun bind(item: ItemStack, player: Player) {
        NbtUtil.setString(item, OWNER_NAME_KEY, player.name)
    }

    fun unbind(item: ItemStack) {
        NbtUtil.setString(item, OWNER_NAME_KEY, "")
    }
}