package org.kangning.lifejourney.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 时间线事件DTO
 * 用于API接口中的时间线事件信息传输
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimelineEventDTO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    // 事件ID
    private Long id;
    
    // 用户ID
    private Long userId;
    
    // 事件标题
    private String title;
    
    // 事件描述
    private String description;
    
    // 事件日期
    private LocalDate eventDate;
    
    // 事件类型
    private String type;
    
    // 事件标签
    private List<String> tags;
    
    // 相关图片URL列表
    private List<String> images;
    
    // 相关链接
    private String link;
    
    // 是否公开
    private boolean isPublic;
    
    // 情绪值 (1-10)
    private Integer moodValue;
    
    // 位置信息
    private String location;
    
    // 关联人物
    private List<String> relatedPeople;
    
    // 创建时间
    private LocalDateTime createdAt;
    
    // 更新时间
    private LocalDateTime updatedAt;
}