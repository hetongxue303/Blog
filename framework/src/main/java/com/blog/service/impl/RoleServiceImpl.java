package com.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.domain.entity.Role;
import com.blog.mapper.RoleMapper;
import com.blog.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * 角色业务实现
 *
 * @author hy
 * @version 1.0
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}