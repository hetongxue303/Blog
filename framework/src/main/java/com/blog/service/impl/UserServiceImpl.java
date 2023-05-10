package com.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.domain.entity.User;
import com.blog.mapper.UserMapper;
import com.blog.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户业务实现
 *
 * @author hy
 * @version 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}