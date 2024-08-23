package net.torosamy.torosamyBind.commands

import net.torosamy.torosamyBind.utils.ConfigUtil
import net.torosamy.torosamyCore.utils.MessageUtil
import org.bukkit.command.CommandSender
import org.incendo.cloud.annotations.Command
import org.incendo.cloud.annotations.CommandDescription
import org.incendo.cloud.annotations.Permission

class AdminCommans {
    @Command("tb reload")
    @Permission("torosamybind.reload")
    @CommandDescription("重载TorosamyBind配置文件")
    fun reloadConfig(sender: CommandSender) {
        ConfigUtil.reloadConfig()
        sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().reloadMessage))
    }
}