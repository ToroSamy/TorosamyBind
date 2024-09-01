package net.torosamy.torosamyBind.utils

import net.torosamy.torosamyBind.TorosamyBind
import net.torosamy.torosamyCore.manager.ConfigManager
import net.torosamy.torosamyBind.config.LangConfig


class ConfigUtil {
    companion object {
        var langConfig: LangConfig = LangConfig()
        private var langConfigManager: ConfigManager = ConfigManager(langConfig, TorosamyBind.plugin, "", "lang.yml")

        fun reloadConfig() {
            langConfigManager.load()
        }

        fun saveConfig() {
            langConfigManager.save()
        }
    }
}