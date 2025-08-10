package net.torosamy.torosamyBind.commands

import me.clip.placeholderapi.PlaceholderAPI
import net.torosamy.torosamyBind.utils.ConfigUtil
import net.torosamy.torosamyBind.utils.ListenerUtil
import net.torosamy.torosamyBind.utils.ListenerUtil.Companion.OWNER_NAME_KEY
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

        val ownerName: String? = NbtUtil.getString(itemInMainHand, OWNER_NAME_KEY);

        //若为空 则说明没有人绑定 则可以绑定
        if (ownerName.isNullOrEmpty()) {
            NbtUtil.setString(itemInMainHand, OWNER_NAME_KEY, player.name)

            player.sendMessage(MessageUtil.text(PlaceholderAPI.setPlaceholders(player,ConfigUtil.langConfig.bindSuccess)))
            return
        }
        //不为空则有人绑定 检测是否有人绑定
        if(player.name != ownerName) {
            player.sendMessage(MessageUtil.text(PlaceholderAPI.setPlaceholders(player,ConfigUtil.langConfig.notOwner)))
            return
        }
        //没人绑定则解除绑定
        NbtUtil.setString(itemInMainHand, OWNER_NAME_KEY, "")
        player.sendMessage(MessageUtil.text(PlaceholderAPI.setPlaceholders(player,ConfigUtil.langConfig.unbindSuccess)))
    }

    @Command("tb owner", requiredSender = Player::class)
    @Permission("torosamybind.look")
    @CommandDescription("查看物品主人")
    fun lookOwner(sender: CommandSender) {
        val player = sender as Player

        val itemInMainHand = player.inventory.itemInMainHand
        if (itemInMainHand.type == Material.AIR) return

        val ownerName: String? = NbtUtil.getString(itemInMainHand, OWNER_NAME_KEY);

        if (ownerName.isNullOrEmpty()) {
            player.sendMessage(MessageUtil.text(PlaceholderAPI.setPlaceholders(player,ConfigUtil.langConfig.noOwner)))
            return
        }
        player.sendMessage(MessageUtil.text(ConfigUtil.langConfig.showOwner.replace("{owner}", ownerName)))
    }

}