package org.kangning.lifejourney.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 仪表板数据DTO
 * 用于统计API中的仪表板数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDataDTO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    // 统计概览数据
    private OverviewStats overview;
    
    // 时间线事件统计
    private TimelineStats timelineStats;
    
    // 最近活动列表
    private List<ActivityDTO> recentActivities;
    
    /**
     * 概览统计内部类
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OverviewStats {
        // 总时间线事件数
        private Long totalTimelineEvents;
        
        // 总文章数
        private Long totalArticles;
        
        // 总相册数
        private Long totalAlbums;
        
        // 总照片数
        private Long totalPhotos;
        
        // 总目标数
        private Long totalTargets;
        
        // 已完成目标数
        private Long completedTargets;
    }
    
    /**
     * 时间线统计内部类
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TimelineStats {
        // 按年份分布
        private Map<Integer, Long> eventsByYear;
        
        // 按类型分布
        private Map<String, Long> eventsByType;
    }
    
    /**
     * 活动DTO内部类
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ActivityDTO {
        // 活动ID
        private Long id;
        
        // 活动类型
        private String type;
        
        // 活动标题
        private String title;
        
        // 活动时间
        private String time;
        
        // 活动URL
        private String url;
    }
}