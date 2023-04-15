package com.blog.handler.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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
        this.setFieldValByName("operationTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        //        this.strictInsertFill(metaObject, "updateBy", defaultUser, Long.class);
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //        this.strictUpdateFill(metaObject, "updateBy", defaultUser, Long.class);
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }
}