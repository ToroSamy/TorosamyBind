## Dependency
- TorosamyCore
- PlaceholderAPI
## Usage
1. download [TorosamyCore](https://github.com/ToroSamy/TorosamyCore) and [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/) as a dependency plugin
2. put the **dependencies** and this plugin into your plugins folder and start your server
3. Modify the default configuration file generated and restart your server
## Permission
- - **Usage:** /tb reload
  - **Description:** reload this plugin
  - **Permission:** torosamybind.reload
  <br>
- - **Usage:** /bind
  - **Description:** Bind or unbind items in hand
  - **Permission:** torosamybind.use
  <br>
- - **Usage:** /tb owner
  - **Description:** Check the owner of the item
  - **Permission:** torosamybind.look
## Config
### lang.yml
```yml
reload-message: "&b[服务器娘]&a插件 &eTorosamyBind &a重载成功喵~"
bind-success: "&b[服务器娘]&a成功绑定玩家 &e%player_name%"
unbind-success: "&b[服务器娘]&a成功解除 &e%player_name% &a的绑定"
not-owner: "&b[服务器娘]&c错误 您不是该物品的主人"
no-owner: "&b[服务器娘]&a该物品还没有任何玩家绑定"
prevent-take: "&b[服务器娘]&c错误 该物品已被玩家 &e%player_name% &c绑定 无法拿取"
show-owner: "&b[服务器娘]&a该物品主人: {owner}"
```

## Contact Author
- qq: 1364596766
- website: https://www.torosamy.net

## License

[MIT](./LICENSE)
