# LifeJourney API接口文档

## 📋 文档信息

- **API名称**: LifeJourney REST API
- **版本**: v1.0
- **基础URL**: `https://api.lifejourney.com/v1`
- **文档类型**: API接口文档
- **更新日期**: 2025年10月
- **后端框架**: Spring Boot 3.2+ (Java 21)
- **数据库**: MySQL 8.0

## 🔐 认证说明

### JWT Token认证
所有API请求都需要在请求头中包含有效的JWT Token：

```http
Authorization: Bearer <your_jwt_token>
```

### Token获取
通过登录接口获取Token，Token有效期为24小时，可通过刷新接口延长有效期。

## 📝 通用规范

### 请求格式
- **Content-Type**: `application/json`
- **字符编码**: UTF-8
- **请求方法**: GET、POST、PUT、DELETE

### 响应格式
所有API响应都遵循统一的JSON格式：

```json
{
    "success": true,
    "data": {},
    "message": "操作成功",
    "timestamp": "2025-10-01T10:00:00Z",
    "pagination": {
        "page": 1,
        "size": 20,
        "total": 100,
        "totalPages": 5
    }
}
```

### 错误响应
```json
{
    "success": false,
    "error": {
        "code": "VALIDATION_ERROR",
        "message": "请求参数验证失败",
        "details": {
            "field": "email",
            "message": "邮箱格式不正确"
        }
    },
    "timestamp": "2025-10-01T10:00:00Z"
}
```

### 状态码说明
- `200` - 请求成功
- `201` - 创建成功
- `400` - 请求参数错误
- `401` - 未授权
- `403` - 禁止访问
- `404` - 资源不存在
- `422` - 数据验证失败
- `500` - 服务器内部错误

## 👤 用户认证API

### 用户注册
```http
POST /auth/register
```

**请求体:**
```json
{
    "username": "john_doe",
    "email": "john@example.com",
    "password": "password123",
    "displayName": "John Doe"
}
```

**响应:**
```json
{
    "success": true,
    "data": {
        "user": {
            "id": 1,
            "username": "john_doe",
            "email": "john@example.com",
            "displayName": "John Doe",
            "createdAt": "2025-10-01T10:00:00Z"
        },
        "tokens": {
            "accessToken": "jwt_token",
            "refreshToken": "refresh_token"
        }
    },
    "message": "注册成功"
}
```

### 用户登录
```http
POST /auth/login
```

**请求体:**
```json
{
    "email": "john@example.com",
    "password": "password123"
}
```

**响应:**
```json
{
    "success": true,
    "data": {
        "user": {
            "id": 1,
            "username": "john_doe",
            "email": "john@example.com",
            "displayName": "John Doe"
        },
        "tokens": {
            "accessToken": "jwt_token",
            "refreshToken": "refresh_token"
        }
    },
    "message": "登录成功"
}
```

### 刷新Token
```http
POST /auth/refresh
```

**请求体:**
```json
{
    "refreshToken": "refresh_token"
}
```

### 获取用户信息
```http
GET /auth/profile
```

**响应:**
```json
{
    "success": true,
    "data": {
        "id": 1,
        "username": "john_doe",
        "email": "john@example.com",
        "displayName": "John Doe",
        "avatarUrl": "https://example.com/avatar.jpg",
        "bio": "这是我的个人简介",
        "createdAt": "2025-10-01T10:00:00Z",
        "updatedAt": "2025-10-01T10:00:00Z"
    }
}
```

### 更新用户信息
```http
PUT /auth/profile
```

**请求体:**
```json
{
    "displayName": "John Smith",
    "bio": "更新后的个人简介",
    "avatarUrl": "https://example.com/new-avatar.jpg"
}
```

## ⏰ 时间线API

### 获取时间线事件列表
```http
GET /timeline/events
```

**查询参数:**
- `page` (int): 页码，默认1
- `size` (int): 每页数量，默认20
- `year` (int): 年份筛选
- `month` (int): 月份筛选
- `type` (string): 事件类型筛选
- `category` (string): 分类筛选
- `search` (string): 关键词搜索

**响应:**
```json
{
    "success": true,
    "data": [
        {
            "id": 1,
            "title": "大学毕业",
            "description": "完成了计算机科学学士学位",
            "eventDate": "2025-06-15",
            "eventType": "education",
            "category": "graduation",
            "status": "completed",
            "imageUrl": "https://example.com/graduation.jpg",
            "metadata": {
                "location": "北京",
                "degree": "学士"
            },
            "createdAt": "2025-10-01T10:00:00Z"
        }
    ],
    "pagination": {
        "page": 1,
        "size": 20,
        "total": 50,
        "totalPages": 3
    }
}
```

### 创建时间线事件
```http
POST /timeline/events
```

**请求体:**
```json
{
    "title": "新工作开始",
    "description": "加入了一家新的科技公司",
    "eventDate": "2025-10-01",
    "eventType": "career",
    "category": "job_change",
    "status": "completed",
    "imageUrl": "https://example.com/new-job.jpg",
    "metadata": {
        "company": "Tech Corp",
        "position": "软件工程师",
        "location": "上海"
    }
}
```

### 获取单个时间线事件
```http
GET /timeline/events/{id}
```

### 更新时间线事件
```http
PUT /timeline/events/{id}
```

### 删除时间线事件
```http
DELETE /timeline/events/{id}
```

### 获取时间线统计
```http
GET /timeline/events/stats
```

**响应:**
```json
{
    "success": true,
    "data": {
        "totalEvents": 50,
        "eventsByYear": {
            "2025": 15,
            "2024": 20,
            "2023": 15
        },
        "eventsByType": {
            "education": 10,
            "career": 15,
            "travel": 8,
            "health": 5,
            "other": 12
        },
        "recentEvents": [
            {
                "id": 1,
                "title": "最近的事件",
                "eventDate": "2025-10-01"
            }
        ]
    }
}
```

## 📝 文章API

### 获取文章列表
```http
GET /articles
```

**查询参数:**
- `page` (int): 页码
- `size` (int): 每页数量
- `status` (string): 文章状态 (draft, published)
- `tag` (string): 标签筛选
- `search` (string): 关键词搜索
- `sort` (string): 排序方式 (createdAt, updatedAt, publishedAt)

**响应:**
```json
{
    "success": true,
    "data": [
        {
            "id": 1,
            "title": "我的第一篇博客",
            "excerpt": "这是文章的摘要...",
            "slug": "my-first-blog",
            "status": "published",
            "featuredImage": "https://example.com/featured.jpg",
            "tags": ["技术", "生活"],
            "createdAt": "2025-10-01T10:00:00Z",
            "updatedAt": "2025-10-01T10:00:00Z",
            "publishedAt": "2025-10-01T10:00:00Z"
        }
    ],
    "pagination": {
        "page": 1,
        "size": 20,
        "total": 25,
        "totalPages": 2
    }
}
```

### 创建文章
```http
POST /articles
```

**请求体:**
```json
{
    "title": "新文章标题",
    "content": "文章内容...",
    "excerpt": "文章摘要",
    "status": "draft",
    "featuredImage": "https://example.com/image.jpg",
    "tags": ["标签1", "标签2"]
}
```

### 获取单个文章
```http
GET /articles/{slug}
```

**响应:**
```json
{
    "success": true,
    "data": {
        "id": 1,
        "title": "文章标题",
        "content": "完整的文章内容...",
        "excerpt": "文章摘要",
        "slug": "article-slug",
        "status": "published",
        "featuredImage": "https://example.com/image.jpg",
        "tags": ["标签1", "标签2"],
        "createdAt": "2025-10-01T10:00:00Z",
        "updatedAt": "2025-10-01T10:00:00Z",
        "publishedAt": "2025-10-01T10:00:00Z"
    }
}
```

### 更新文章
```http
PUT /articles/{slug}
```

### 删除文章
```http
DELETE /articles/{slug}
```

### 搜索文章
```http
GET /articles/search
```

**查询参数:**
- `q` (string): 搜索关键词
- `page` (int): 页码
- `size` (int): 每页数量

## 📸 相册API

### 获取相册列表
```http
GET /albums
```

**查询参数:**
- `page` (int): 页码
- `size` (int): 每页数量
- `search` (string): 搜索关键词

**响应:**
```json
{
    "success": true,
    "data": [
        {
            "id": 1,
            "name": "旅行相册",
            "description": "2025年的旅行记录",
            "coverImage": "https://example.com/cover.jpg",
            "photoCount": 25,
            "createdAt": "2025-10-01T10:00:00Z",
            "updatedAt": "2025-10-01T10:00:00Z"
        }
    ],
    "pagination": {
        "page": 1,
        "size": 20,
        "total": 10,
        "totalPages": 1
    }
}
```

### 创建相册
```http
POST /albums
```

**请求体:**
```json
{
    "name": "新相册",
    "description": "相册描述"
}
```

### 获取单个相册
```http
GET /albums/{id}
```

### 更新相册
```http
PUT /albums/{id}
```

### 删除相册
```http
DELETE /albums/{id}
```

## 🖼️ 照片API

### 获取照片列表
```http
GET /photos
```

**查询参数:**
- `albumId` (long): 相册ID筛选
- `page` (int): 页码
- `size` (int): 每页数量

**响应:**
```json
{
    "success": true,
    "data": [
        {
            "id": 1,
            "filename": "photo_001.jpg",
            "originalName": "IMG_001.jpg",
            "filePath": "/photos/2025/10/photo_001.jpg",
            "fileSize": 2048000,
            "mimeType": "image/jpeg",
            "width": 1920,
            "height": 1080,
            "title": "美丽的风景",
            "description": "拍摄于某个美丽的地方",
            "takenAt": "2025-10-01T10:00:00Z",
            "createdAt": "2025-10-01T10:00:00Z"
        }
    ],
    "pagination": {
        "page": 1,
        "size": 20,
        "total": 100,
        "totalPages": 5
    }
}
```

### 上传照片
```http
POST /photos/upload
```

**请求体:** `multipart/form-data`
- `file` (file): 照片文件
- `albumId` (long): 相册ID
- `title` (string): 照片标题
- `description` (string): 照片描述

**响应:**
```json
{
    "success": true,
    "data": {
        "id": 1,
        "filename": "photo_001.jpg",
        "filePath": "/photos/2025/10/photo_001.jpg",
        "url": "https://minio.lifejourney.com/photos/2025/10/photo_001.jpg"
    },
    "message": "照片上传成功"
}
```

### 获取单个照片
```http
GET /photos/{id}
```

### 更新照片信息
```http
PUT /photos/{id}
```

**请求体:**
```json
{
    "title": "更新后的标题",
    "description": "更新后的描述"
}
```

### 删除照片
```http
DELETE /photos/{id}
```

## 🎯 目标API

### 获取目标列表
```http
GET /goals
```

**查询参数:**
- `status` (string): 目标状态 (active, completed, paused)
- `category` (string): 目标分类
- `priority` (string): 优先级 (low, medium, high)
- `page` (int): 页码
- `size` (int): 每页数量

**响应:**
```json
{
    "success": true,
    "data": [
        {
            "id": 1,
            "title": "学习新技能",
            "description": "掌握Vue3和Spring Boot",
            "category": "learning",
            "priority": "high",
            "status": "active",
            "targetDate": "2026-06-01",
            "progress": 65,
            "createdAt": "2025-10-01T10:00:00Z",
            "updatedAt": "2025-10-01T10:00:00Z"
        }
    ],
    "pagination": {
        "page": 1,
        "size": 20,
        "total": 15,
        "totalPages": 1
    }
}
```

### 创建目标
```http
POST /goals
```

**请求体:**
```json
{
    "title": "新目标",
    "description": "目标描述",
    "category": "career",
    "priority": "medium",
    "targetDate": "2026-12-01"
}
```

### 获取单个目标
```http
GET /goals/{id}
```

### 更新目标
```http
PUT /goals/{id}
```

### 删除目标
```http
DELETE /goals/{id}
```

### 更新目标进度
```http
PUT /goals/{id}/progress
```

**请求体:**
```json
{
    "progress": 75,
    "note": "进度更新说明"
}
```

## 🏷️ 标签API

### 获取标签列表
```http
GET /tags
```

**响应:**
```json
{
    "success": true,
    "data": [
        {
            "id": 1,
            "name": "技术",
            "color": "#3B82F6",
            "usageCount": 15,
            "createdAt": "2025-10-01T10:00:00Z"
        }
    ]
}
```

### 创建标签
```http
POST /tags
```

**请求体:**
```json
{
    "name": "新标签",
    "color": "#FF6B6B"
}
```

### 更新标签
```http
PUT /tags/{id}
```

### 删除标签
```http
DELETE /tags/{id}
```

## 📊 统计API

### 获取仪表板数据
```http
GET /dashboard/stats
```

**响应:**
```json
{
    "success": true,
    "data": {
        "timeline": {
            "totalEvents": 50,
            "recentEvents": 5
        },
        "articles": {
            "totalArticles": 25,
            "publishedArticles": 20,
            "draftArticles": 5
        },
        "albums": {
            "totalAlbums": 10,
            "totalPhotos": 200
        },
        "goals": {
            "totalGoals": 15,
            "activeGoals": 8,
            "completedGoals": 7
        }
    }
}
```

## 🔍 搜索API

### 全局搜索
```http
GET /search
```

**查询参数:**
- `q` (string): 搜索关键词
- `type` (string): 搜索类型 (all, timeline, articles, photos, goals)
- `page` (int): 页码
- `size` (int): 每页数量

**响应:**
```json
{
    "success": true,
    "data": {
        "timeline": [
            {
                "id": 1,
                "title": "搜索结果1",
                "type": "timeline",
                "snippet": "包含关键词的片段..."
            }
        ],
        "articles": [
            {
                "id": 1,
                "title": "搜索结果2",
                "type": "article",
                "snippet": "包含关键词的片段..."
            }
        ],
        "photos": [],
        "goals": []
    },
    "pagination": {
        "page": 1,
        "size": 20,
        "total": 2,
        "totalPages": 1
    }
}
```

## 📝 错误码说明

| 错误码 | 说明 | 解决方案 |
|--------|------|----------|
| `VALIDATION_ERROR` | 请求参数验证失败 | 检查请求参数格式 |
| `AUTHENTICATION_ERROR` | 认证失败 | 检查Token是否有效 |
| `AUTHORIZATION_ERROR` | 权限不足 | 检查用户权限 |
| `RESOURCE_NOT_FOUND` | 资源不存在 | 检查资源ID是否正确 |
| `DUPLICATE_RESOURCE` | 资源已存在 | 检查是否重复创建 |
| `FILE_UPLOAD_ERROR` | 文件上传失败 | 检查文件格式和大小 |
| `RATE_LIMIT_EXCEEDED` | 请求频率超限 | 降低请求频率 |
| `INTERNAL_SERVER_ERROR` | 服务器内部错误 | 联系技术支持 |

## 🔧 开发工具

### API测试
推荐使用以下工具进行API测试：
- **Postman**: 图形化API测试工具
- **Insomnia**: 轻量级API客户端
- **curl**: 命令行工具
- **Swagger UI**: 自动生成的API文档界面

### Spring Boot集成
项目集成了Swagger UI，可以通过以下地址访问：
```
http://localhost:8080/swagger-ui.html
```

### 示例curl命令
```bash
# 用户登录
curl -X POST https://api.lifejourney.com/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"john@example.com","password":"password123"}'

# 获取时间线事件
curl -X GET https://api.lifejourney.com/v1/timeline/events \
  -H "Authorization: Bearer your_jwt_token"

# 创建文章
curl -X POST https://api.lifejourney.com/v1/articles \
  -H "Authorization: Bearer your_jwt_token" \
  -H "Content-Type: application/json" \
  -d '{"title":"新文章","content":"文章内容","status":"draft"}'
```

## 📚 更新日志

### v1.0.0 (2025-10-01)
- 初始版本发布
- 支持用户认证、时间线、文章、相册、目标管理
- 完整的RESTful API设计
- JWT认证机制
- 分页和搜索功能
- Spring Boot 3.2+ (Java 21) 后端支持
- MySQL 8.0 数据库支持
- MinIO 对象存储支持