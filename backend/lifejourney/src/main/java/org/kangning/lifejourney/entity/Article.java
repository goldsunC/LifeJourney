package org.kangning.lifejourney.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * 文章实体类
 * 对应数据库表: articles
 * JPA负责表结构创建，MyBatis-Plus负责CRUD操作
 */
@Data
@Entity
@Table(name = "articles") // JPA表名配置
@TableName("articles")    // MyBatis-Plus表名配置
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private String excerpt;

    @Column(nullable = false, unique = true, length = 200)
    private String slug;

    private String status = "draft";

    @Column(name = "featured_image")
    private String featuredImage;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "published_at")
    private LocalDateTime publishedAt;

    // 创建时间自动填充
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    // 更新时间自动填充
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}