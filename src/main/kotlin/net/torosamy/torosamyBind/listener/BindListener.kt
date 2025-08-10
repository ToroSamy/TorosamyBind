package net.torosamy.torosamyBind.listener

import me.clip.placeholderapi.PlaceholderAPI
import net.torosamy.torosamyBind.utils.ConfigUtil
import net.torosamy.torosamyBind.utils.ListenerUtil.Companion.OWNER_NAME_KEY
import net.torosamy.torosamyCore.utils.MessageUtil
import net.torosamy.torosamyCore.utils.NbtUtil
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityPickupItemEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerDropItemEvent
class BindListener : Listener {
    @EventHandler
    fun onDropItem(event: PlayerDropItemEvent) {
        val itemStack = event.itemDrop.itemStack
        if (itemStack.type === Material.AIR) return

        val ownerName: String? = NbtUtil.getString(itemStack, OWNER_NAME_KEY);

        if (ownerName.isNullOrEmpty()) return

        if (event.player.name != ownerName) return
        event.isCancelled = true
    }


    @EventHandler
    fun onPickupItem(event: EntityPickupItemEvent) {

        val entity = event.entity
        if (entity !is Player) return
        val player = entity
        if (player.isOp) return
        val itemStack = event.item.itemStack
        if (itemStack.type === Material.AIR) return

        val ownerName: String? = NbtUtil.getString(itemStack, OWNER_NAME_KEY);

        if (ownerName.isNullOrEmpty()) return

        if (player.name == ownerName) return

        event.isCancelled = true
    }

    @EventHandler
    fun onClickInventory(event: InventoryClickEvent) {
        if (event.whoClicked !is Player) return
        val player = event.whoClicked as Player
        if (player.isOp) return
        val item = event.currentItem
        if (item == null) return
        if (item.type === Material.AIR) return

        val ownerName: String = NbtUtil.getString(item, OWNER_NAME_KEY) ?: return;

        if (ownerName.isEmpty()) return

        if (player.name == ownerName) return


        player.sendMessage(
            MessageUtil.text(
                PlaceholderAPI.setPlaceholders(
                    player,
                    ConfigUtil.langConfig.preventTake.replace("{owner}", ownerName)
                )
            )
        )
        event.isCancelled = true
    }
}