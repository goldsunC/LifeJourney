package org.kangning.lifejourney.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 目标DTO
 * 用于API接口中的目标信息传输
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TargetDTO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    // 目标ID
    private Long id;
    
    // 用户ID
    private Long userId;
    
    // 目标名称
    private String name;
    
    // 目标描述
    private String description;
    
    // 目标类型
    private String type;
    
    // 目标值
    private Double targetValue;
    
    // 当前进度
    private Double currentProgress;
    
    // 进度单位
    private String progressUnit;
    
    // 开始日期
    private LocalDate startDate;
    
    // 结束日期
    private LocalDate endDate;
    
    // 是否完成
    private boolean isCompleted;
    
    // 重要程度 (1-5)
    private Integer priority;
    
    // 创建时间
    private LocalDateTime createdAt;
    
    // 更新时间
    private LocalDateTime updatedAt;
}