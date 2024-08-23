package net.torosamy.torosamyBind.utils

import net.torosamy.torosamyCore.manager.ConfigManager
import net.torosamy.torosamyBind.TorosamyBind.Companion.plugin
import net.torosamy.torosamyBind.config.LangConfig


class ConfigUtil {
    companion object {
        private var langConfig: LangConfig = LangConfig()
        private var langConfigManager: ConfigManager = ConfigManager(langConfig)


        fun getLangConfig(): LangConfig {return langConfig }

        fun reloadConfig() {
            langConfigManager.load(plugin, "lang.yml")
        }

        fun saveConfig() {
            langConfigManager.saveFile()
        }
    }
}