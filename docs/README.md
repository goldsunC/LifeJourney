# LifeJourney 项目文档

欢迎来到 **LifeJourney** 项目文档中心！这里包含了项目的完整技术文档，帮助您了解、开发和部署这个个人数字空间平台。

## 📚 文档目录

### 1. [产品需求文档](./产品需求文档.md)
- **产品概述**: 项目愿景、定位和目标用户
- **功能需求**: 详细的功能规格和用户故事
- **非功能性需求**: 性能、安全、兼容性要求
- **产品路线图**: 版本规划和功能迭代计划

### 2. [技术架构文档](./技术架构文档.md)
- **系统架构**: 整体架构设计和组件关系
- **技术栈**: Vue3 + Spring Boot 技术选型和理由
- **数据库设计**: MySQL数据表结构和索引设计
- **安全架构**: 认证、授权和安全防护
- **性能优化**: 缓存策略和性能调优

### 3. [API接口文档](./API接口文档.md)
- **接口规范**: RESTful API设计标准
- **认证机制**: JWT Token认证流程
- **接口列表**: 完整的API端点文档
- **错误处理**: 错误码和异常处理
- **开发工具**: API测试和调试工具

## 🚀 快速开始

### 环境要求
- Java 21+
- Node.js 18+
- MySQL 8.0+
- Redis 7+
- MinIO

### 安装步骤
```bash
# 1. 克隆项目
git clone https://github.com/username/lifejourney.git
cd lifejourney

# 2. 安装依赖
# 后端依赖
cd backend
./mvnw clean install

# 前端依赖
cd ../frontend
npm install

# 3. 配置环境变量
cp .env.example .env
cp frontend/.env.example frontend/.env.local
# 编辑配置文件

# 4. 启动数据库和MinIO
docker-compose up -d mysql redis minio

# 5. 运行数据库迁移
cd backend
./mvnw flyway:migrate

# 6. 启动开发服务器
# 后端
./mvnw spring-boot:run

# 前端
cd ../frontend
npm run dev
```

### 访问应用
- **前端**: http://localhost:3000
- **后端API**: http://localhost:8080
- **API文档**: http://localhost:8080/swagger-ui.html

## 🏗️ 项目结构

```
LifeJourney/
├── docs/                    # 项目文档
│   ├── 产品需求文档.md
│   ├── 技术架构文档.md
│   ├── API接口文档.md
│   └── README.md
├── frontend/                # Vue3前端应用
│   ├── src/
│   │   ├── components/      # 组件
│   │   ├── views/          # 页面
│   │   ├── router/         # 路由
│   │   ├── store/          # 状态管理
│   │   └── utils/          # 工具函数
│   ├── public/
│   ├── package.json
│   └── vite.config.ts
├── backend/                 # Spring Boot后端应用
│   ├── src/main/java/
│   │   ├── controller/      # 控制器
│   │   ├── service/         # 服务层
│   │   ├── mapper/          # MyBatis-Plus映射器
│   │   ├── entity/          # 实体类
│   │   └── config/          # 配置类
│   ├── src/main/resources/
│   │   ├── application.yml  # 配置文件
│   │   └── db/migration/    # 数据库迁移
│   ├── pom.xml
│   └── Dockerfile
├── docker-compose.yml       # Docker配置
├── .env.example            # 环境变量模板
└── README.md               # 项目说明
```

## 🎯 核心功能

### 1. 时间线模块
- 按时间轴展示人生重要事件
- 支持多种事件类型和分类
- 提供筛选和搜索功能
- 数据可视化统计

### 2. 文章管理
- 富文本编辑器支持
- Markdown格式支持
- 标签和分类管理
- 文章搜索和筛选

### 3. 相册管理
- 照片上传和管理
- 相册分类组织
- 幻灯片浏览模式
- 照片信息编辑

### 4. 目标管理
- 个人目标设定和跟踪
- 进度管理和统计
- 成就展示系统
- 目标分析报告

## 🛠️ 技术特色

### 前端技术
- **Vue 3**: 现代化的前端框架
- **TypeScript**: 类型安全开发
- **Vite**: 快速的构建工具
- **Element Plus**: 企业级UI组件库
- **Pinia**: 轻量级状态管理

### 后端技术
- **Spring Boot 3.5.6**: 企业级Java框架
- **Java 21**: 最新LTS版本
- **Spring Security**: 安全框架
- **MyBatis-Plus**: 增强的ORM框架
- **MySQL 8.0**: 关系型数据库
- **Redis**: 缓存和会话存储
- **MinIO**: 对象存储

## 📖 开发指南

### 代码规范
- 使用TypeScript进行类型检查
- 遵循阿里巴巴Java开发手册
- 采用ESLint和Checkstyle规范
- 使用Conventional Commits提交规范
- 编写单元测试和集成测试

### 分支管理
- `main`: 主分支，用于生产环境
- `develop`: 开发分支，用于集成开发
- `feature/*`: 功能分支，用于新功能开发
- `hotfix/*`: 热修复分支，用于紧急修复

### 提交规范
```bash
# 功能开发
git commit -m "feat: 添加时间线事件创建功能"

# 问题修复
git commit -m "fix: 修复照片上传失败问题"

# 文档更新
git commit -m "docs: 更新API接口文档"

# 样式调整
git commit -m "style: 调整按钮样式和间距"
```

## 🔧 开发工具

### 推荐IDE
- **IntelliJ IDEA**: Java开发首选
- **VS Code**: 前端开发推荐
- **WebStorm**: 全栈开发IDE
- **Cursor**: AI辅助编程工具

### 必备插件
- **Vue Language Features (Volar)**
- **TypeScript Importer**
- **Element Plus Snippets**
- **Spring Boot Extension Pack**
- **Docker**

### 调试工具
- **Vue DevTools**
- **Pinia DevTools**
- **Postman**: API测试
- **DBeaver**: 数据库管理
- **RedisInsight**: Redis管理

## 📊 性能指标

### 目标性能
- **首屏加载时间**: < 3秒
- **API响应时间**: < 500ms
- **数据库查询时间**: < 100ms
- **图片加载时间**: < 2秒

### 监控指标
- **页面访问量**: Google Analytics
- **API性能**: Micrometer指标
- **错误率**: Sentry错误追踪
- **用户体验**: 真实用户监控

## 🔒 安全考虑

### 数据安全
- JWT Token认证
- 密码BCrypt加密
- HTTPS传输加密
- SQL注入防护

### 隐私保护
- 用户数据加密存储
- 隐私设置控制
- 数据备份和恢复
- 符合GDPR规范

## 🤝 贡献指南

### 如何贡献
1. Fork项目仓库
2. 创建功能分支
3. 提交代码更改
4. 创建Pull Request
5. 代码审查和合并

### 贡献类型
- **Bug修复**: 修复现有问题
- **功能开发**: 添加新功能
- **文档完善**: 改进项目文档
- **性能优化**: 提升系统性能
- **测试覆盖**: 增加测试用例

## 📞 技术支持

### 获取帮助
- **GitHub Issues**: 报告问题和建议
- **Discord社区**: 实时交流讨论
- **邮件支持**: support@lifejourney.com
- **文档中心**: 查看完整文档

### 常见问题
- [安装和配置问题](./技术架构文档.md#开发环境搭建)
- [API使用问题](./API接口文档.md#常见问题)
- [性能优化建议](./技术架构文档.md#性能优化)
- [安全配置指南](./技术架构文档.md#安全架构)

## 📄 许可证

本项目采用 MIT 许可证，详情请查看 [LICENSE](../LICENSE) 文件。

## 🙏 致谢

感谢所有为LifeJourney项目做出贡献的开发者和社区成员！

---

**LifeJourney** - 记录你的生命旅程，分享成长的点滴！ 🌟

*最后更新: 2025年10月*