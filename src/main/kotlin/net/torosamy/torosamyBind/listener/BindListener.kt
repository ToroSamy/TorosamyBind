package net.torosamy.torosamyBind.listener


import net.torosamy.torosamyBind.api.TorosamyBindAPI
import net.torosamy.torosamyBind.utils.ConfigUtil
import net.torosamy.torosamyCore.utils.MessageUtil
import org.bukkit.Material
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityPickupItemEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerInteractAtEntityEvent
import org.bukkit.event.player.PlayerInteractEntityEvent


class BindListener : Listener {
    @EventHandler
    fun onDropItem(event: PlayerDropItemEvent) {
        val itemStack = event.itemDrop.itemStack
        
        if (itemStack.type === Material.AIR) return

        if (!TorosamyBindAPI.hasOwner(itemStack)) {
            return
        }
        
        if (event.player.name != TorosamyBindAPI.getOwner(itemStack)) {
            return
        }
        
        event.isCancelled = true
    }


    @EventHandler
    fun onPickupItem(event: EntityPickupItemEvent) {
        val entity = event.entity
        
        if (entity !is Player) {
            return
        }
        
        if (entity.isOp) {
            return
        }
        
        val itemStack = event.item.itemStack
        
        if (itemStack.type === Material.AIR) return

        if (!TorosamyBindAPI.hasOwner(itemStack)) {
            return
        }

        if (entity.name == TorosamyBindAPI.getOwner(itemStack)) {
            return
        }
        
        event.isCancelled = true
    }

    @EventHandler
    fun onClickInventory(event: InventoryClickEvent) {
        if (event.whoClicked !is Player) return
        val player = event.whoClicked as Player
        if (player.isOp) return
        val item = event.currentItem ?: return
        if (item.type === Material.AIR) return


        if (!TorosamyBindAPI.hasOwner(item)) {
            return
        }

        val ownerName = TorosamyBindAPI.getOwner(item)
        
        if (player.name == ownerName) {
            return
        }

        player.sendMessage(MessageUtil.format(player,ConfigUtil.langConfig.notOwner))
        event.isCancelled = true
    }


    @EventHandler
    fun onPlayerInteractEntity(event: PlayerInteractEntityEvent) {
        val player = event.player

        if (!preventInteractArmorStand(event.rightClicked, player)) {
            return
        }

        player.sendMessage(MessageUtil.format(player, ConfigUtil.langConfig.notOwner))

        event.isCancelled = true
    }

    @EventHandler
    fun onPlayerInteractAtEntity(event: PlayerInteractAtEntityEvent) {
        val player = event.player
        
        if (!preventInteractArmorStand(event.rightClicked, player)) {
            return
        }

        player.sendMessage(MessageUtil.format(event.player, ConfigUtil.langConfig.notOwner))

        event.isCancelled = true
    }
    
    private fun preventInteractArmorStand(entity: Entity, player: Player): Boolean {
        if (entity !is ArmorStand) {
            return false
        }

        if (player.isOp) {
            return false
        }

        val item = player.equipment.itemInMainHand

        if (item.type === Material.AIR) return false

        if (!TorosamyBindAPI.hasOwner(item)) {
            return false
        }
        
        return true
    }
}