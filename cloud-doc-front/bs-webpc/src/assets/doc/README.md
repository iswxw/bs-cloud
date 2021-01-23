<!--
 * @Descripttion: 
 * @version: V 1.0
 * @Author: wxw
 * @Date: 2020-05-11 13:13:18
 -->
本项目是基于微服务架构的农货交易平台、涉及的技术栈有：

- Spring Cloud、Spring Boot、MyBatis Plus、Vue框架
- 基于 FastDFS 解决大数据量的分布式文件存储问题
- 基于 Elasticsearch 高级聚合功能，实现商品的智能过滤搜索
- 基于 Elasticsearch 高级聚合功能，实现销售业务的复杂统计及报表输出
- 基于 LocalStorage 实现离线客户端购物车，减轻服务端压力。
- 基于 JWT 技术及 RSA 非对称加密实现真正无状态的单点登录。
- 结合 JWT 和 RSA 非对称加密，自定义 Feign 过滤器实现自动化服务间鉴权，解决服务对外暴露的安全问题
- 基于 RabbitMQ 实现可靠消息服务，解决服务间通信问题
- 基于 RabbitMQ 实现可靠消息服务，解决分布式事务问题
- 使用微信 SDK 实现微信扫码支付，符合主流付款方式
- 基于 Redis 搭建高可用集群，实现可靠缓存服务即热点数据保存。
- redis 持久化，集群，哨兵，主从，缓存击穿，热点 key。
- 基于 Redis 和 Mq 来应对高可用高并发的秒杀场景
- 基于 MyCat 实现数据库的读写分离和分库分表
- 基于 Nginx 实现初步的请求负载均衡和请求限流