package org.kangning.lifejourney.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * MySQL连接测试工具类
 * 用于测试数据库是否能正常连接
 */
@Component
public class DatabaseConnectionTester implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("===== 开始测试MySQL数据库连接 =====");
        
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
        
        System.out.println("===== MySQL数据库连接测试结束 =====");
    }
}