package org.kangning.lifejourney.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 文章DTO
 * 用于API接口中的文章信息传输
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    // 文章ID
    private Long id;
    
    // 用户ID
    private Long userId;
    
    // 文章标题
    private String title;
    
    // 文章内容（Markdown格式）
    private String content;
    
    // 文章摘要
    private String summary;
    
    // 封面图URL
    private String coverImage;
    
    // 文章标签
    private List<String> tags;
    
    // 阅读量
    private Integer views;
    
    // 点赞数
    private Integer likes;
    
    // 是否公开
    private boolean isPublic;
    
    // 是否置顶
    private boolean isTop;
    
    // 创建时间
    private LocalDateTime createdAt;
    
    // 更新时间
    private LocalDateTime updatedAt;
}