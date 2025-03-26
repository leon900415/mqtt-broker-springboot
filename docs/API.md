# API 文档

## 接口列表

### 1. 发布消息

**接口地址**：`/api/mqtt/publish`

**请求方式**：POST

**请求参数**：

```json
{
    "topic": "test/topic",
    "message": "Hello MQTT!",
    "qos": 1
}
```

**参数说明**：
- topic: 消息主题
- message: 消息内容
- qos: 服务质量等级（0, 1, 2）

**响应示例**：
```json
{
    "success": true,
    "message": "消息发布成功"
}
```

### 2. 订阅主题

**接口地址**：`/api/mqtt/subscribe`

**请求方式**：POST

**请求参数**：

```json
{
    "topic": "test/topic",
    "qos": 1
}
```

**参数说明**：
- topic: 要订阅的主题
- qos: 服务质量等级（0, 1, 2）

**响应示例**：
```json
{
    "success": true,
    "message": "主题订阅成功"
}
```

### 3. 取消订阅

**接口地址**：`/api/mqtt/unsubscribe`

**请求方式**：POST

**请求参数**：

```json
{
    "topic": "test/topic"
}
```

**参数说明**：
- topic: 要取消订阅的主题

**响应示例**：
```json
{
    "success": true,
    "message": "取消订阅成功"
}
```

## 错误码说明

| 错误码 | 说明 |
|--------|------|
| 400 | 请求参数错误 |
| 401 | 未授权 |
| 403 | 禁止访问 |
| 404 | 资源不存在 |
| 500 | 服务器内部错误 |

## 注意事项

1. 所有接口都需要在请求头中设置 `Content-Type: application/json`
2. QoS 级别说明：
   - 0：最多发送一次
   - 1：至少发送一次
   - 2：确保只发送一次
3. 主题名称支持通配符：
   - `+`：单层通配符
   - `#`：多层通配符 