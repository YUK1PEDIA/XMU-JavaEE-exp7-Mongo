# 基于 Mongo 的读写效率分析

## 注意事项

1. 测试时请配置 `application.yaml` 文件的如下参数

   - 本地测试时请配置 `active` 为 `dev` 

   - 部署到服务器前请将 `active` 修改为 `prod` 

![1.png](https://s2.loli.net/2024/12/13/znfq12T9Ka7OlFW.png) ![1.png](https://s2.loli.net/2024/12/13/HkW3zgrlDGFwhRU.png) 

2. 配置文件中的 `hosts` 部分，需要配置本地 `host` 文件，将相关字段映射到 mongo 服务器的公网 ip

![1.png](https://s2.loli.net/2024/12/13/Ktzki6VHgjwUN52.png) 