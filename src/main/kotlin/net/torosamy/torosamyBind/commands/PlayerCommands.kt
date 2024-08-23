package net.torosamy.torosamyBind.commands

import me.clip.placeholderapi.PlaceholderAPI
import net.torosamy.torosamyBind.utils.ConfigUtil
import net.torosamy.torosamyCore.nbtapi.NBT
import net.torosamy.torosamyCore.utils.MessageUtil
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

        var ownerName: String = ""
        NBT.get(itemInMainHand) { nbt -> ownerName = nbt.getString("ownerName") }
        //若为空 则说明没有人绑定 则可以绑定
        if (ownerName == "") {
            NBT.modify(itemInMainHand) {nbt -> nbt.setString("ownerName", player.name) }
            player.sendMessage(MessageUtil.text(PlaceholderAPI.setPlaceholders(player,ConfigUtil.getLangConfig().bindSuccess)))
            return
        }
        //不为空则有人绑定 检测是否有人绑定
        if(player.name != ownerName) {
            player.sendMessage(MessageUtil.text(PlaceholderAPI.setPlaceholders(player,ConfigUtil.getLangConfig().notOwner)))
            return
        }
        //没人绑定则解除绑定
        NBT.modify(itemInMainHand) {nbt->nbt.setString("ownerName", "")}
        player.sendMessage(MessageUtil.text(PlaceholderAPI.setPlaceholders(player,ConfigUtil.getLangConfig().unbindSuccess)))
    }

    @Command("tb owner", requiredSender = Player::class)
    @Permission("torosamybind.look")
    @CommandDescription("查看物品主人")
    fun lookOwner(sender: CommandSender) {
        val player = sender as Player

        val itemInMainHand = player.inventory.itemInMainHand
        if (itemInMainHand.type == Material.AIR) return

        var ownerName: String = ""
        NBT.get(itemInMainHand) { nbt -> ownerName = nbt.getString("ownerName") }
        if (ownerName == "") {
            player.sendMessage(MessageUtil.text(PlaceholderAPI.setPlaceholders(player,ConfigUtil.getLangConfig().noOwner)))
            return
        }
        player.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().showOwner.replace("{owner}", ownerName)))
    }

}