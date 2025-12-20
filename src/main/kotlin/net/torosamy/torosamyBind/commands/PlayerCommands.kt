package net.torosamy.torosamyBind.commands

import net.torosamy.torosamyBind.api.TorosamyBindAPI
import net.torosamy.torosamyBind.utils.ConfigUtil
import net.torosamy.torosamyCore.TorosamyCore
import net.torosamy.torosamyCore.utils.MessageUtil
import net.torosamy.torosamyCore.utils.NbtUtil
import org.bukkit.Material
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.incendo.cloud.annotations.Command
import org.incendo.cloud.annotations.CommandDescription
import org.incendo.cloud.annotations.Permission

class PlayerCommands {
    @Command("bind", requiredSender = Player::class)
    @Permission("torosamybind.use")
    @CommandDescription("绑定或解除绑定手上物品")
    fun bindOrUnbind(sender: CommandSender) {
        val player = sender as Player

        val itemInMainHand = player.inventory.itemInMainHand
        
        if (itemInMainHand.type == Material.AIR) return

        if (!TorosamyBindAPI.hasOwner(itemInMainHand)) {
            TorosamyBindAPI.bind(itemInMainHand, player)

            player.sendMessage(MessageUtil.format(player,ConfigUtil.langConfig.bindSuccess))
            return
        }

        val ownerName = TorosamyBindAPI.getOwner(itemInMainHand)
        
        if(player.name != ownerName) {
            player.sendMessage(MessageUtil.format(player,ConfigUtil.langConfig.notOwner))
            return
        }
        
        TorosamyBindAPI.unbind(itemInMainHand)
        
        player.sendMessage(MessageUtil.format(player,ConfigUtil.langConfig.unbindSuccess))
    }

    @Command("tb owner", requiredSender = Player::class)
    @Permission("torosamybind.look")
    @CommandDescription("查看物品主人")
    fun lookOwner(sender: CommandSender) {
        val player = sender as Player

        val itemInMainHand = player.inventory.itemInMainHand
        
        if (itemInMainHand.type == Material.AIR) return

        if (!TorosamyBindAPI.hasOwner(itemInMainHand)) {
            player.sendMessage(MessageUtil.format(player,ConfigUtil.langConfig.noOwner))
            return
        }

        val ownerName = TorosamyBindAPI.getOwner(itemInMainHand)


        player.sendMessage(MessageUtil.format(ConfigUtil.langConfig.showOwner.replace("{owner}", ownerName)))
    }

}