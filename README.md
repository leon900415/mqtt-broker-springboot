# MQTT Broker with Spring Boot

这是一个基于 Spring Boot 实现的 MQTT 客户端项目，支持 MQTT 消息的发布和订阅功能。

## 功能特点

- 支持 MQTT 消息的发布和订阅
- 支持 MQTT 连接配置的灵活管理
- 支持消息的异步处理
- 支持消息的持久化存储
- 提供 RESTful API 接口进行消息操作
- 支持 MQTT 消息的 QoS 级别设置
- 支持 MQTT 连接的安全认证

## 技术栈

- Spring Boot 3.2.3
- Spring Integration MQTT
- MQTT Paho Client
- Lombok
- Spring Web

## 快速开始

### 环境要求

- JDK 17 或更高版本
- Maven 3.6 或更高版本
- MQTT Broker（如 Mosquitto、EMQX 等）

### 配置说明

在 `application.yml` 中配置 MQTT 连接信息：

```yaml
mqtt:
  client:
    client-id: ${MQTT_CLIENT_ID:springboot-mqtt-client}
    username: ${MQTT_USERNAME:admin}
    password: ${MQTT_PASSWORD:public}
    clean-session: true
    auto-reconnect: true
    connection-timeout: 30
    keep-alive-interval: 60
  broker:
    url: ${MQTT_BROKER_URL:tcp://localhost:1883}
    topics:
      - topic1
      - topic2
```

### 运行项目

1. 克隆项目
```bash
git clone https://github.com/leon900415/mqtt-broker-springboot.git
```

2. 进入项目目录
```bash
cd mqtt-broker-springboot
```

3. 编译项目
```bash
mvn clean package
```

4. 运行项目
```bash
java -jar target/mqtt-broker-0.0.1-SNAPSHOT.jar
```

## API 接口

### 发布消息

```http
POST /api/mqtt/publish
Content-Type: application/json

{
    "topic": "test/topic",
    "message": "Hello MQTT!",
    "qos": 1
}
```

### 订阅主题

```http
POST /api/mqtt/subscribe
Content-Type: application/json

{
    "topic": "test/topic",
    "qos": 1
}
```

### 取消订阅

```http
POST /api/mqtt/unsubscribe
Content-Type: application/json

{
    "topic": "test/topic"
}
```

## 项目结构

```
src/main/java/com/kingdom/client/
├── config/           # MQTT 配置类
├── controller/       # REST API 控制器
├── handle/          # 消息处理器
└── service/         # 业务服务层
```

## 贡献指南

1. Fork 本仓库
2. 创建你的特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交你的更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启一个 Pull Request

## 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情

## 联系方式

- GitHub: [leon900415](https://github.com/leon900415) 