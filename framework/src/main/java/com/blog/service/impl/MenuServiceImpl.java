package com.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.domain.entity.Menu;
import com.blog.mapper.MenuMapper;
import com.blog.service.MenuService;
import org.springframework.stereotype.Service;

/**
 * 菜单业务实现
 *
 * @author hy
 * @version 1.0
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}