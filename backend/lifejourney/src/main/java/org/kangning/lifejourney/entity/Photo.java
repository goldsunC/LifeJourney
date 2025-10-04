package org.kangning.lifejourney.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * 照片实体类
 * 对应数据库表: photos
 * JPA负责表结构创建，MyBatis-Plus负责CRUD操作
 */
@Data
@Entity
@Table(name = "photos") // JPA表名配置
@TableName("photos")    // MyBatis-Plus表名配置
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "album_id", nullable = false)
    private Long albumId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String filename;

    @Column(name = "original_name")
    private String originalName;

    @Column(name = "file_path", nullable = false, columnDefinition = "TEXT")
    private String filePath;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "mime_type")
    private String mimeType;

    private Integer width;

    private Integer height;

    private String title;

    private String description;

    @Column(name = "taken_at")
    private LocalDateTime takenAt;

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