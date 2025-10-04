package org.kangning.lifejourney.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import jakarta.persistence.*;

/**
 * 文章标签关联实体类
 * 对应数据库表: article_tags
 * JPA负责表结构创建，MyBatis-Plus负责CRUD操作
 */
@Data
@Entity
@Table(name = "article_tags") // JPA表名配置
@TableName("article_tags")    // MyBatis-Plus表名配置
@IdClass(ArticleTagId.class)
public class ArticleTag {

    @Id
    @Column(name = "article_id", nullable = false)
    private Long articleId;

    @Id
    @Column(name = "tag_id", nullable = false)
    private Long tagId;
}