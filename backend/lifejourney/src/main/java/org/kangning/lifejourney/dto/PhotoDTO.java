package org.kangning.lifejourney.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 照片DTO
 * 用于API接口中的照片信息传输
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhotoDTO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    // 照片ID
    private Long id;
    
    // 相册ID
    private Long albumId;
    
    // 用户ID
    private Long userId;
    
    // 照片URL
    private String url;
    
    // 缩略图URL
    private String thumbnailUrl;
    
    // 照片描述
    private String description;
    
    // 拍摄日期
    private LocalDateTime takenAt;
    
    // 拍摄地点
    private String location;
    
    // 照片标签
    private List<String> tags;
    
    // 点赞数
    private Integer likes;
    
    // 创建时间
    private LocalDateTime createdAt;
    
    // 更新时间
    private LocalDateTime updatedAt;
}