### 购物商城

***

### 前言

bs-cloud，一个基于微服务架构的前后端分离的购物商城

### 项目介绍

购物商城（bs-cloud）一个基于微服务架构的前后端分离的购物商城。**Web** 端使用 **Vue** + **ElementUi** ，后端使用 **SpringCloud** + **SpringBoot** + **Mybatis-plus**进行开发， JWT 技术及 RSA 非对称加密实现真正无状态的单点登录，图片等文件上传至FastDFS 分布式文件存储服务器。

### 演示

- 视频演示地址：https://www.bilibili.com/video/BV1aa411F7ic

### 项目特点

- 友好的代码结构及注释，便于阅读及二次开发
- 实现前后端分离，通过 **Json** 进行数据交互，前端再也不用关注后端技术
- 页面交互使用 **Vue2.x**，极大的提高了开发效率。
- 引入**Swagger**  多服务文档支持，方便编写 **API** 接口文档。
- 引入**RabbitMQ** 消息队列，用于同步ES索引库和后台管理新上架商品后的信息
- 引入**ElasticSearch** 实现商品过滤和搜索
- 基于 JWT 技术及 RSA 非对称加密实现真正无状态的单点登录
- FastDFS 进行处理大量的分布式文件存储
- 基于 Nginx 实现初步的请求负载均衡和请求限流

### 项目目录

- bs-cloud 是一款基于当下热门技术开发的购物商城。
- cloud-doc-front  是前端文档部分
  - bs-webpc  是基于Vue实现的前台门户网站
  - wxw-manage-web 是基于Vue实现的管理商城后台
  - mysql 数据库脚本
  - shell 服务部署脚本
- cloud-gateway-server 服务网关
- cloud-registry-server 注册中心
- cloud-server-auth 鉴权中心
- cloud-server-cart 购物车
- cloud-server-fileload 文件服务模块
- cloud-server-order 订单支付模块，接入了支付宝沙箱支付
- cloud-server-search 搜素服务，ElasticSearch和Solr作为全文检索工具，结合MQ维护索引库数据同步
- cloud-server-user 用户中心，提供用户信息维护和管理
- cloud-web-server 基础数据服务，提供管理后台和Web端API服务接口

### 关注&交流

刚兴趣的小伙伴可以搜索或扫描下方二维码关注公众号【Java半颗糖】，一起交流学习。

![](https://gitee.com/wwxw/image/raw/master/wechat/FDekBM1FXHpH.jpg) 

