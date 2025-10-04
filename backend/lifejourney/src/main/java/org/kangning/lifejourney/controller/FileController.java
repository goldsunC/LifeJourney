package org.kangning.lifejourney.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.kangning.lifejourney.dto.response.CommonResponse;
import org.kangning.lifejourney.util.MinioUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传控制器
 * 提供文件上传的API接口
 */
@Tag(name = "文件管理", description = "文件上传和管理接口")
@RestController
@RequestMapping("/v1/files")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private MinioUtils minioUtils;

    /**
     * 上传单个文件
     * @param file 要上传的文件
     * @return 包含文件URL的响应
     */
    @Operation(summary = "上传单个文件", description = "支持上传图片和文本文件到MinIO对象存储")
    @PostMapping("/upload")
    public CommonResponse<Map<String, String>> uploadFile(
            @Parameter(description = "要上传的文件", required = true)
            @RequestParam("file") MultipartFile file) {
        
        logger.info("文件上传请求: {}", file.getOriginalFilename());
        
        // 调用MinIO工具类上传文件
        String fileUrl = minioUtils.uploadFile(file);
        
        // 构建响应
        Map<String, String> result = new HashMap<>();
        result.put("url", fileUrl);
        result.put("filename", file.getOriginalFilename());
        result.put("size", String.valueOf(file.getSize()));
        result.put("type", file.getContentType());
        
        return CommonResponse.success(result, "文件上传成功");
    }
}