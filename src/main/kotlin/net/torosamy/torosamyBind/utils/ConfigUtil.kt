package net.torosamy.torosamyBind.utils

import net.torosamy.torosamyBind.TorosamyBind
import net.torosamy.torosamyBind.config.LangConfig
import net.torosamy.torosamyCore.config.Config
import net.torosamy.torosamyCore.config.ConfigFile


class ConfigUtil {
    companion object {
        private val configs: ArrayList<Config> = ArrayList()
        
        public var langConfig: LangConfig = LangConfig()

        fun initConfig() {
            configs.add(Config(langConfig, ConfigFile(TorosamyBind.plugin,"lang.yml")))
        }

        fun reloadConfig() {
            for (config in configs) {
                config.load()
            }
        }

        fun saveConfig() {
            for (config in configs) {
                config.save()
            }
        }
    }
}