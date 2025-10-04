package org.kangning.lifejourney.util;

import io.minio.BucketExistsArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.http.Method;
import org.kangning.lifejourney.exception.BusinessException;
import org.kangning.lifejourney.exception.ErrorEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * MinIO文件操作工具类
 * 提供文件上传、下载、删除等功能
 */
@Component
public class MinioUtils {

    private static final Logger logger = LoggerFactory.getLogger(MinioUtils.class);

    @Autowired
    private MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String defaultBucketName;

    @Value("${file.upload.max-size}")
    private String maxFileSize;

    @Value("${file.upload.allowed-types}")
    private String allowedTypes;

    /**
     * 上传文件到MinIO
     * @param file 要上传的文件
     * @return 文件访问URL
     */
    public String uploadFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new BusinessException(ErrorEnum.PARAM_ERROR, "文件不能为空");
        }

        // 检查文件大小
        long fileSize = file.getSize();
        long maxSize = parseFileSize(maxFileSize);
        if (fileSize > maxSize) {
            throw new BusinessException(ErrorEnum.FILE_SIZE_EXCEED);
        }

        // 检查文件类型
        String contentType = file.getContentType();
        if (!isAllowedFileType(contentType)) {
            throw new BusinessException(ErrorEnum.FILE_TYPE_NOT_ALLOWED);
        }

        try {
            // 确保存储桶存在
            ensureBucketExists(defaultBucketName);

            // 生成唯一的文件名
            String originalFilename = file.getOriginalFilename();
            String fileExtension = getFileExtension(originalFilename);
            String fileName = UUID.randomUUID().toString() + (fileExtension.isEmpty() ? "" : "." + fileExtension);

            // 文件存储路径
            String objectName = generateObjectName(fileName);

            // 上传文件
            try (InputStream inputStream = file.getInputStream()) {
                minioClient.putObject(
                        PutObjectArgs.builder()
                                .bucket(defaultBucketName)
                                .object(objectName)
                                .stream(inputStream, fileSize, -1)
                                .contentType(contentType)
                                .build()
                );
            }

            // 返回文件URL
            return getPresignedObjectUrl(defaultBucketName, objectName);

        } catch (Exception e) {
            logger.error("上传文件失败: {}", e.getMessage(), e);
            throw new BusinessException(ErrorEnum.FILE_UPLOAD_ERROR);
        }
    }

    /**
     * 上传文本文件到MinIO
     * @param content 文件内容
     * @param fileName 文件名
     * @param contentType 文件类型
     * @return 文件访问URL
     */
    public String uploadTextFile(String content, String fileName, String contentType) {
        if (content == null || content.isEmpty()) {
            throw new BusinessException(ErrorEnum.PARAM_ERROR, "文件内容不能为空");
        }

        if (fileName == null || fileName.isEmpty()) {
            throw new BusinessException(ErrorEnum.PARAM_ERROR, "文件名不能为空");
        }

        try {
            // 确保存储桶存在
            ensureBucketExists(defaultBucketName);

            // 文件存储路径
            String objectName = generateObjectName(fileName);

            // 上传文件
            byte[] contentBytes = content.getBytes();
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(defaultBucketName)
                            .object(objectName)
                            .stream(new java.io.ByteArrayInputStream(contentBytes), contentBytes.length, -1)
                            .contentType(contentType)
                            .build()
            );

            // 返回文件URL
            return getPresignedObjectUrl(defaultBucketName, objectName);

        } catch (Exception e) {
            logger.error("上传文本文件失败: {}", e.getMessage(), e);
            throw new BusinessException(ErrorEnum.FILE_UPLOAD_ERROR);
        }
    }

    /**
     * 删除MinIO中的文件
     * @param objectName 文件对象名
     */
    public void deleteFile(String objectName) {
        if (objectName == null || objectName.isEmpty()) {
            throw new BusinessException(ErrorEnum.PARAM_ERROR, "文件名不能为空");
        }

        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(defaultBucketName)
                            .object(objectName)
                            .build()
            );
        } catch (Exception e) {
            logger.error("删除文件失败: {}", e.getMessage(), e);
            throw new BusinessException(ErrorEnum.SYSTEM_ERROR, "文件删除失败");
        }
    }

    /**
     * 获取文件的预签名URL
     * @param bucketName 存储桶名称
     * @param objectName 文件对象名
     * @return 预签名URL
     */
    public String getPresignedObjectUrl(String bucketName, String objectName) {
        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucketName)
                            .object(objectName)
                            .expiry(7, TimeUnit.DAYS)  // URL有效期7天
                            .build()
            );
        } catch (Exception e) {
            logger.error("获取文件URL失败: {}", e.getMessage(), e);
            throw new BusinessException(ErrorEnum.SYSTEM_ERROR, "获取文件URL失败");
        }
    }

    /**
     * 确保存储桶存在，如果不存在则创建
     * @param bucketName 存储桶名称
     */
    private void ensureBucketExists(String bucketName) throws Exception {
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }

    /**
     * 解析文件大小字符串
     * @param sizeStr 文件大小字符串，如"10MB"
     * @return 文件大小的字节数
     */
    private long parseFileSize(String sizeStr) {
        sizeStr = sizeStr.trim().toUpperCase();
        long size = Long.parseLong(sizeStr.replaceAll("[^0-9]", ""));
        if (sizeStr.endsWith("KB")) {
            return size * 1024;
        } else if (sizeStr.endsWith("MB")) {
            return size * 1024 * 1024;
        } else if (sizeStr.endsWith("GB")) {
            return size * 1024 * 1024 * 1024;
        }
        return size;
    }

    /**
     * 检查文件类型是否允许上传
     * @param contentType 文件类型
     * @return 是否允许上传
     */
    private boolean isAllowedFileType(String contentType) {
        if (contentType == null) {
            return false;
        }
        String[] allowedTypesArray = allowedTypes.split(",");
        for (String allowedType : allowedTypesArray) {
            if (contentType.equalsIgnoreCase(allowedType.trim())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取文件扩展名
     * @param fileName 文件名
     * @return 文件扩展名
     */
    private String getFileExtension(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    }

    /**
     * 生成文件对象名（包含日期目录）
     * @param fileName 文件名
     * @return 文件对象名
     */
    private String generateObjectName(String fileName) {
        // 按照日期组织文件存储
        java.time.LocalDate now = java.time.LocalDate.now();
        String year = String.valueOf(now.getYear());
        String month = String.format("%02d", now.getMonthValue());
        return String.format("%s/%s/%s", year, month, fileName);
    }
}