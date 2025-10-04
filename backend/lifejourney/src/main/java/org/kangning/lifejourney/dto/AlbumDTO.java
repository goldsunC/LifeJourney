package org.kangning.lifejourney.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 相册DTO
 * 用于API接口中的相册信息传输
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumDTO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    // 相册ID
    private Long id;
    
    // 用户ID
    private Long userId;
    
    // 相册名称
    private String name;
    
    // 相册描述
    private String description;
    
    // 封面图URL
    private String coverImage;
    
    // 照片数量
    private Integer photoCount;
    
    // 创建时间
    private LocalDateTime createdAt;
    
    // 更新时间
    private LocalDateTime updatedAt;
}