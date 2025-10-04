package org.kangning.lifejourney.util;

import io.minio.BucketExistsArgs;
import io.minio.ListBucketsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveBucketArgs;
import io.minio.RemoveObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 服务健康检查器
 * 整合MySQL、Redis和MinIO连接测试功能
 * 在应用启动时自动运行，检查各服务是否正常连接
 */
@Component
public class ServiceHealthChecker implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private MinioClient minioClient;

    @Value("${minio.endpoint}")
    private String minioEndpoint;

    @Value("${minio.bucket-name}")
    private String minioBucketName;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("===== 开始服务健康检查 =====");
        
        // 测试MySQL数据库连接
        testDatabaseConnection();
        
        // 测试Redis连接
        testRedisConnection();
        
        // 测试MinIO连接
        testMinioConnection();
        
        System.out.println("===== 服务健康检查完成 =====");
    }

    /**
     * 测试MySQL数据库连接
     */
    private void testDatabaseConnection() {
        System.out.println("\n----- 开始测试MySQL数据库连接 -----");
        
        try {
            Connection connection = dataSource.getConnection();
            boolean isValid = connection.isValid(5); // 5秒超时
            
            if (isValid) {
                System.out.println("✅ MySQL数据库连接成功！");
                System.out.println("数据库URL: " + connection.getMetaData().getURL());
                System.out.println("数据库用户: " + connection.getMetaData().getUserName());
                System.out.println("数据库产品名称: " + connection.getMetaData().getDatabaseProductName());
                System.out.println("数据库版本: " + connection.getMetaData().getDatabaseProductVersion());
            } else {
                System.out.println("❌ MySQL数据库连接无效！");
            }
            
            connection.close();
        } catch (SQLException e) {
            System.out.println("❌ MySQL数据库连接失败！");
            System.out.println("错误信息: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println("----- MySQL数据库连接测试结束 -----");
    }

    /**
     * 测试Redis连接
     */
    private void testRedisConnection() {
        System.out.println("\n----- 开始测试Redis连接 -----");
        
        try {
            // 测试Redis连接的简单操作：设置值和获取值
            String testKey = "redis_connection_test";
            String testValue = "Hello Redis!";
            
            // 设置测试值
            redisTemplate.opsForValue().set(testKey, testValue);
            System.out.println("✅ 成功设置测试键值对: " + testKey + " = " + testValue);
            
            // 获取测试值
            Object retrievedValue = redisTemplate.opsForValue().get(testKey);
            
            if (retrievedValue != null && retrievedValue.equals(testValue)) {
                System.out.println("✅ Redis连接测试成功！");
                System.out.println("获取到的值: " + retrievedValue);
                
                try {
                    // 获取Redis连接信息
                    org.springframework.data.redis.connection.RedisConnectionFactory factory = redisTemplate.getConnectionFactory();
                    if (factory instanceof org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory) {
                        org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory lettuceFactory = 
                                (org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory) factory;
                        
                        // 获取单机配置信息
                        org.springframework.data.redis.connection.RedisStandaloneConfiguration standaloneConfig = 
                                lettuceFactory.getStandaloneConfiguration();
                        
                        String redisHost = standaloneConfig.getHostName();
                        int redisPort = standaloneConfig.getPort();
                        
                        System.out.println("Redis服务器: " + redisHost + ":" + redisPort);
                    }
                } catch (Exception e) {
                    System.out.println("获取Redis连接信息时出错: " + e.getMessage());
                }
                
                // 清理测试数据
                redisTemplate.delete(testKey);
                System.out.println("已清理测试数据");
            } else {
                System.out.println("❌ Redis连接测试失败：获取的值不匹配！");
                System.out.println("预期值: " + testValue);
                System.out.println("实际值: " + retrievedValue);
            }
        } catch (Exception e) {
            System.out.println("❌ Redis连接失败！");
            System.out.println("错误信息: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println("----- Redis连接测试结束 -----");
    }

    /**
     * 测试MinIO连接
     */
    private void testMinioConnection() {
        System.out.println("\n----- 开始测试MinIO连接 -----");
        
        try {
            // 打印MinIO配置信息
            System.out.println("MinIO服务器地址: " + minioEndpoint);
            System.out.println("默认桶名称: " + minioBucketName);
            
            // 测试连接：列出所有桶
            System.out.println("尝试列出所有桶...");
            minioClient.listBuckets(ListBucketsArgs.builder().build());
            System.out.println("✅ 成功连接到MinIO服务器！");
            
            // 检查默认桶是否存在
            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioBucketName).build());
            System.out.println("默认桶 '" + minioBucketName + "' 存在: " + bucketExists);
            
            // 创建一个临时测试桶进行更完整的测试
            String testBucketName = "minio-test-bucket-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            System.out.println("创建测试桶: " + testBucketName);
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(testBucketName).build());
            System.out.println("✅ 成功创建测试桶！");
            
            // 上传测试文件
            String testFileName = "test-file.txt";
            String testFileContent = "This is a test file for MinIO connection test.\nCreated at: " + LocalDateTime.now();
            
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(testBucketName)
                            .object(testFileName)
                            .stream(
                                    new ByteArrayInputStream(testFileContent.getBytes(StandardCharsets.UTF_8)),
                                    testFileContent.getBytes(StandardCharsets.UTF_8).length,
                                    -1
                            )
                            .contentType("text/plain")
                            .build()
            );
            System.out.println("✅ 成功上传测试文件！");
            
            // 清理测试资源：删除测试文件和测试桶
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(testBucketName)
                            .object(testFileName)
                            .build()
            );
            System.out.println("已删除测试文件");
            
            minioClient.removeBucket(
                    RemoveBucketArgs.builder()
                            .bucket(testBucketName)
                            .build()
            );
            System.out.println("已删除测试桶");
            
            System.out.println("✅ MinIO连接测试完全成功！");
        } catch (Exception e) {
            System.out.println("❌ MinIO连接失败！");
            System.out.println("错误信息: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println("----- MinIO连接测试结束 -----");
    }
}