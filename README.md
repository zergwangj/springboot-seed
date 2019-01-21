# springboot-seed

项目基于Spring Boot 2.1.0 、MyBatis Plus、 Spring Security、Redis的后台管理系统

#### 开发环境

- JDK：8
- IDE：IntelliJ IDEA （后端）
- IDE：JetBrains WebStorm  （前端）
- 依赖管理：Maven
- 数据库：MySQL 5.7.24

#### 功能模块
```
- 系统管理
    - 用户管理 提供用户的相关配置
    - 角色管理 角色菜单进行权限的分配
    - Swagger文档 localhost:8000/swagger-ui.html
    - 权限管理 权限细化到接口
    - 菜单管理 已实现菜单动态路由，后端可配置化，支持多级菜单
    - 定时任务 整合Quartz做定时任务，加入任务日志，任务运行情况一目了然
- 系统监控
    - 系统日志 使用apo记录用户操作日志，并且记录异常堆栈信息
    - 系统缓存 使用jedis将缓存操作可视化，并提供对redis的基本操作，可根据需求自行扩展
    - 实时控制台 实时打印logback日志，来自微强迫症患者的精心配色，更好的监控系统的运行状态
    - SQL监控 采用druid 监控数据库访问性能，默认用户名admin，密码123456
- 三方工具
    - 邮件工具 配合富文本，发送html格式的邮件
    - SM.MS免费图床 挺好用的一个图床，作为公共图片上传使用
    - 七牛云存储 这个就不多说了
    - 支付宝支付 提供了测试账号，可自行测试
- 组件管理
    - 图标库 系统图标来自 https://www.iconfont.cn/
    - 富文本 集成wangEditor富文本
```
#### 项目结构
```
- common 公共包
    - aop 记录日志与接口限流
    - config 线程池配置
    - exception 项目异常处理
    - mapper mapstruct的通用mapper
    - mybatisplus mybatis plus自动填充类
    - redis redis缓存相关配置
    - swagger2 接口文档配置
    - utils 通用工具
- core 核心包
    - config JWT的安全过滤器配置与跨域配置
    - controller 用户授权的接口
    - security 配置spring security
    - service 用户登录与权限的处理
    - utils 包含加密工具与JWT工具
- monitor 系统监控
    - config 配置日志拦截器与WebSocket等
    - controller 前端控制器    
    - entity 实体类
    - mapper 数据库操作
    - service 业务接口
        - impl 业务接口实现
        - query 业务查询
- quartz 定时任务
- system 系统管理
- tools 第三方工具
```
#### 后端技术栈

- 基础框架：Spring Boot 2.1.0.RELEASE
- 持久层框架：Mybatis Plus
- 安全框架：Spring Security
- 缓存框架：Redis
- 日志打印：logback+log4jdbc
- 接口文档 swagger2
- 其他：fastjson、aop、MapStruct等

#### 前端技术栈
- node
- vue
- vue-router
- axios
- element ui

# springboot-seed
