package org.kangning.lifejourney.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 目标实体类
 * 对应数据库表: goals
 * JPA负责表结构创建，MyBatis-Plus负责CRUD操作
 */
@Data
@Entity
@Table(name = "goals") // JPA表名配置
@TableName("goals")    // MyBatis-Plus表名配置
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false, length = 200)
    private String title;

    private String description;

    private String category;

    private String priority = "medium";

    private String status = "active";

    @Column(name = "target_date")
    private LocalDate targetDate;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    private Integer progress = 0;

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