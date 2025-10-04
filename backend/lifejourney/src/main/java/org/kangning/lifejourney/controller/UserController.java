package org.kangning.lifejourney.controller;

import org.kangning.lifejourney.dto.UserDTO;
import org.kangning.lifejourney.dto.response.CommonResponse;
import org.kangning.lifejourney.entity.User;
import org.kangning.lifejourney.exception.ErrorEnum;
import org.kangning.lifejourney.mapper.UserMapper;
import org.kangning.lifejourney.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制器
 * 处理用户信息相关的HTTP请求
 */
@RestController
@RequestMapping("/v1/users")
public class UserController {
    
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserMapper userMapper;
    
    /**
     * 获取当前登录用户的个人信息
     * @param username 当前登录的用户名
     * @return 包含用户信息的响应
     */
    @GetMapping("/profile")
    public CommonResponse<UserDTO> getUserProfile(@RequestAttribute("username") String username) {
        logger.info("获取用户个人信息: {}", username);
        User user = userService.getUserByUsername(username);
        UserDTO userDTO = userService.convertToDTO(user);
        return CommonResponse.success(userDTO);
    }
    
    /**
     * 更新用户个人信息
     * @param username 当前登录的用户名
     * @param userDTO 用户信息DTO
     * @return 包含更新后用户信息的响应
     */
    @PutMapping("/profile")
    public CommonResponse<UserDTO> updateUserProfile(
            @RequestAttribute("username") String username,
            @RequestBody UserDTO userDTO) {
        logger.info("更新用户个人信息: {}", username);
        User updatedUser = userService.updateUserProfile(username, userDTO);
        UserDTO updatedDTO = userService.convertToDTO(updatedUser);
        return CommonResponse.success(updatedDTO);
    }
    
    /**
     * 根据用户ID获取用户公开信息
     * @param userId 用户ID
     * @return 包含用户公开信息的响应
     */
    @GetMapping("/{userId}")
    public CommonResponse<UserDTO> getUserPublicInfo(@PathVariable Long userId) {
        logger.info("获取用户公开信息: userId={}", userId);
        // 查询用户信息
        User user = userMapper.selectById(userId);
        if (user == null) {
            logger.error("用户不存在: userId={}", userId);
            return CommonResponse.fail(ErrorEnum.USER_NOT_FOUND.getCode(), ErrorEnum.USER_NOT_FOUND.getMessage());
        }
        
        // 转换为DTO并返回（只包含公开信息）
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setNickname(user.getDisplayName());
        userDTO.setAvatar(user.getAvatarUrl());
        userDTO.setBio(user.getBio());
        
        return CommonResponse.success(userDTO);
    }
}