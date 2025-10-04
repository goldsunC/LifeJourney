package org.kangning.lifejourney.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Redis连接测试工具类
 * 用于测试Redis是否能正常连接
 */
@Component
public class RedisConnectionTester implements CommandLineRunner {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("===== 开始测试Redis连接 =====");
        
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
        
        System.out.println("===== Redis连接测试结束 =====");
    }
}