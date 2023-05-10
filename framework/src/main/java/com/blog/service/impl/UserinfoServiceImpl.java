package com.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.domain.entity.Userinfo;
import com.blog.mapper.UserinfoMapper;
import com.blog.service.UserinfoService;
import org.springframework.stereotype.Service;

/**
 * 用户信息业务实现
 *
 * @author hy
 * @version 1.0
 */
@Service
public class UserinfoServiceImpl extends ServiceImpl<UserinfoMapper, Userinfo> implements UserinfoService {

}