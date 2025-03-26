# 配置说明

## 配置文件

项目使用 `application.yml` 作为主配置文件，支持通过环境变量覆盖配置项。

## MQTT 配置

### 客户端配置

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
```

**配置说明**：
- `client-id`: MQTT 客户端 ID
- `username`: MQTT 用户名
- `password`: MQTT 密码
- `clean-session`: 是否清除会话
- `auto-reconnect`: 是否自动重连
- `connection-timeout`: 连接超时时间（秒）
- `keep-alive-interval`: 心跳间隔（秒）

### Broker 配置

```yaml
mqtt:
  broker:
    url: ${MQTT_BROKER_URL:tcp://localhost:1883}
    topics:
      - topic1
      - topic2
```

**配置说明**：
- `url`: MQTT Broker 地址
- `topics`: 默认订阅的主题列表

## 环境变量

可以通过以下环境变量覆盖配置：

| 环境变量 | 说明 | 默认值 |
|----------|------|--------|
| MQTT_CLIENT_ID | 客户端 ID | springboot-mqtt-client |
| MQTT_USERNAME | 用户名 | admin |
| MQTT_PASSWORD | 密码 | public |
| MQTT_BROKER_URL | Broker 地址 | tcp://localhost:1883 |

## 配置示例

### 开发环境配置

```yaml
mqtt:
  client:
    client-id: dev-mqtt-client
    username: dev
    password: dev123
  broker:
    url: tcp://localhost:1883
    topics:
      - dev/topic1
      - dev/topic2
```

### 生产环境配置

```yaml
mqtt:
  client:
    client-id: prod-mqtt-client
    username: prod
    password: prod123
  broker:
    url: tcp://mqtt.example.com:1883
    topics:
      - prod/topic1
      - prod/topic2
```

## 注意事项

1. 生产环境中请使用环境变量或配置中心管理敏感信息
2. 建议使用 SSL/TLS 加密 MQTT 连接
3. 根据实际需求调整心跳间隔和超时时间
4. 确保客户端 ID 的唯一性 