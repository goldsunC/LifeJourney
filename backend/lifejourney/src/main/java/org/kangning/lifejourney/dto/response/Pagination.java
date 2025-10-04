package org.kangning.lifejourney.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 分页信息对象
 * 用于API响应中的分页数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "分页信息")
public class Pagination implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "当前页码", example = "1")
    private int page;
    
    @Schema(description = "每页记录数", example = "10")
    private int size;
    
    @Schema(description = "总记录数", example = "100")
    private long total;
    
    @Schema(description = "总页数", example = "10")
    private int totalPages;
    
    /**
     * 创建分页信息
     * @param page 当前页码
     * @param size 每页数量
     * @param total 总记录数
     * @return 分页信息对象
     */
    public static Pagination of(int page, int size, long total) {
        int totalPages = total > 0 ? (int) Math.ceil((double) total / size) : 1;
        return new Pagination(page, size, total, totalPages);
    }
}