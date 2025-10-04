package org.kangning.lifejourney.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * 相册实体类
 * 对应数据库表: albums
 * JPA负责表结构创建，MyBatis-Plus负责CRUD操作
 */
@Data
@Entity
@Table(name = "albums") // JPA表名配置
@TableName("albums")    // MyBatis-Plus表名配置
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false, length = 100)
    private String name;

    private String description;

    @Column(name = "cover_image")
    private String coverImage;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

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