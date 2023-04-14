package com.blog.handler.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * MybatisPlusMetaObject 处理类
 *
 * @author hy
 * @version 1.0
 */
@Component
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        //        this.strictInsertFill(metaObject, "createBy", defaultUser, Long.class);
        this.setFieldValByName("createTime", new Date(), metaObject);
        //        this.strictInsertFill(metaObject, "updateBy", defaultUser, Long.class);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //        this.strictUpdateFill(metaObject, "updateBy", defaultUser, Long.class);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}