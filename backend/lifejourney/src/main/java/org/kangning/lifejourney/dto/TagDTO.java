package org.kangning.lifejourney.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 标签DTO
 * 用于API接口中的标签信息传输
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagDTO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    // 标签ID
    private Long id;
    
    // 标签名称
    private String name;
    
    // 标签类型（如：timeline, article, photo等）
    private String type;
    
    // 使用次数
    private Integer usageCount;
    
    // 创建时间
    private LocalDateTime createdAt;
    
    // 更新时间
    private LocalDateTime updatedAt;
}