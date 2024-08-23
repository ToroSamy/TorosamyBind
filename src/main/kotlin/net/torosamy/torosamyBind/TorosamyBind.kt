package net.torosamy.torosamyBind

import net.torosamy.torosamyBind.utils.CommandUtil
import net.torosamy.torosamyBind.utils.ConfigUtil
import net.torosamy.torosamyBind.utils.ListenerUtil
import net.torosamy.torosamyCore.utils.MessageUtil
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin


class TorosamyBind : JavaPlugin() {
    companion object{lateinit var plugin: TorosamyBind}
    override fun onEnable() {
        plugin = this
        CommandUtil.registerCommand()
        ConfigUtil.reloadConfig()
        ListenerUtil.registerListener()

        Bukkit.getConsoleSender().sendMessage(MessageUtil.text("&a[服务器娘]&a插件 &eTorosamyBind &a成功开启喵~"))
        Bukkit.getConsoleSender().sendMessage(MessageUtil.text("&a[服务器娘]&a作者 &eTorosamy|yweiyang"))
    }

    override fun onDisable() {
        ConfigUtil.saveConfig()


        Bukkit.getConsoleSender().sendMessage(MessageUtil.text("&a[服务器娘]&c插件 &eTorosamyBind &c成功关闭喵~"))
        Bukkit.getConsoleSender().sendMessage(MessageUtil.text("&a[服务器娘]&c作者 &eTorosamy|yweiyang"))
    }
}
