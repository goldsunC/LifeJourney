package org.kangning.lifejourney.service;

import org.kangning.lifejourney.dto.UserDTO;
import org.kangning.lifejourney.entity.User;

/**
 * 用户服务接口
 * 定义用户信息查询和更新等功能
 */
public interface UserService {
    
    /**
     * 根据用户名获取用户信息
     * @param username 用户名
     * @return 用户实体
     */
    User getUserByUsername(String username);
    
    /**
     * 更新用户信息
     * @param username 用户名
     * @param userDTO 用户信息DTO
     * @return 更新后的用户实体
     */
    User updateUserProfile(String username, UserDTO userDTO);
    
    /**
     * 将用户实体转换为DTO
     * @param user 用户实体
     * @return 用户DTO
     */
    UserDTO convertToDTO(User user);
}