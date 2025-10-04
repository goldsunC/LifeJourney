package org.kangning.lifejourney.service.impl;

import org.kangning.lifejourney.dto.UserDTO;
import org.kangning.lifejourney.entity.User;
import org.kangning.lifejourney.exception.BusinessException;
import org.kangning.lifejourney.exception.ErrorEnum;
import org.kangning.lifejourney.mapper.UserMapper;
import org.kangning.lifejourney.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 用户服务实现类
 * 实现用户信息查询和更新等功能
 */
@Service
public class UserServiceImpl implements UserService {
    
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    
    @Autowired
    private UserMapper userMapper;
    
    @Override
    public User getUserByUsername(String username) {
        logger.info("根据用户名查询用户信息: {}", username);
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            logger.error("用户不存在: {}", username);
            throw new BusinessException(ErrorEnum.USER_NOT_FOUND);
        }
        return user;
    }
    
    @Override
    public User updateUserProfile(String username, UserDTO userDTO) {
        logger.info("更新用户信息: {}", username);
        
        // 查询用户
        User user = getUserByUsername(username);
        
        // 更新用户信息
        if (userDTO.getNickname() != null) {
            user.setDisplayName(userDTO.getNickname());
        }
        if (userDTO.getAvatar() != null) {
            user.setAvatarUrl(userDTO.getAvatar());
        }
        if (userDTO.getBio() != null) {
            user.setBio(userDTO.getBio());
        }
        
        // 更新时间
        user.setUpdatedAt(LocalDateTime.now());
        
        // 保存更新
        int result = userMapper.updateById(user);
        if (result != 1) {
            logger.error("更新用户信息失败: {}", username);
            throw new BusinessException(ErrorEnum.USER_UPDATE_FAIL);
        }
        
        logger.info("更新用户信息成功: {}", username);
        return user;
    }
    
    @Override
    public UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setNickname(user.getDisplayName());
        userDTO.setAvatar(user.getAvatarUrl());
        userDTO.setBio(user.getBio());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setUpdatedAt(user.getUpdatedAt());
        return userDTO;
    }
}