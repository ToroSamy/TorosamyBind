package net.torosamy.torosamyBind.listener

import me.clip.placeholderapi.PlaceholderAPI
import net.torosamy.torosamyBind.utils.ConfigUtil
import net.torosamy.torosamyCore.nbtapi.NBT
import net.torosamy.torosamyCore.utils.MessageUtil
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

        var ownerName: String = ""
        NBT.get(itemStack) { nbt -> ownerName = nbt.getString("ownerName") }
        if (ownerName == "") return

        if (event.player.name != ownerName) return
        event.isCancelled = true
    }


    @EventHandler
    fun onPickupItem(event: EntityPickupItemEvent) {

        val entity = event.entity
        if (entity !is Player) return
        val player = entity as Player
        if (player.isOp) return
        val itemStack = event.item.itemStack
        if (itemStack.type === Material.AIR) return

        var ownerName: String = ""
        NBT.get(itemStack) { nbt -> ownerName = nbt.getString("ownerName") }
        if (ownerName == "") return

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

        var ownerName: String = ""
        NBT.get(item) { nbt -> ownerName = nbt.getString("ownerName") }
        if (ownerName == "") return

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