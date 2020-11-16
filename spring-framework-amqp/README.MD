### Spring-AMQP
RabbitMQ 消息队列的使用，测试消息的发送及接收

### 部署项目
需要对系统环境进行配置，按照特定的版本下载对应的	rabbitmq-server 和 Erlang，具体版本支持：

[https://www.rabbitmq.com/which-erlang.html](https://www.rabbitmq.com/which-erlang.html)

Windows 环境下官网配置引导：

[https://www.rabbitmq.com/install-windows-manual.html#downloads](https://www.rabbitmq.com/install-windows-manual.html#downloads)


### 常用命令
启用/关闭服务、插件管理等
```
// 启用插件管理，添加可视化插件
rabbitmq-plugins enable rabbitmq_management

// 启动服务，后台运行
rabbitmq-server  -detached

// 停止服务
rabbitmqctl stop
```