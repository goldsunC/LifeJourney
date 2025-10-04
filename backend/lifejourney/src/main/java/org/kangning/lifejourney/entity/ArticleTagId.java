package org.kangning.lifejourney.entity;

import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

/**
 * 文章标签关联表的复合主键类
 */
@Data
@Embeddable
public class ArticleTagId implements Serializable {

    @Column(name = "article_id")
    private Long articleId;

    @Column(name = "tag_id")
    private Long tagId;

    // Lombok的@Data注解会自动生成equals和hashCode方法
}