package org.kangning.lifejourney.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * 标签实体类
 * 对应数据库表: tags
 * JPA负责表结构创建，MyBatis-Plus负责CRUD操作
 */
@Data
@Entity
@Table(name = "tags") // JPA表名配置
@TableName("tags")    // MyBatis-Plus表名配置
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    private String color;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // 创建时间自动填充
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}