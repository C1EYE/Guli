package com.guigu.mp.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataMetaObjectHandler implements MetaObjectHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataMetaObjectHandler.class);

    @Override
    public void insertFill(MetaObject metaObject) {
//        LOGGER.error("start insert fill ....");
//        this.setFieldValByName("deleted", 0, metaObject);
//        this.setFieldValByName("create_time", new Date(), metaObject);
//        this.setFieldValByName("update_time", new Date(), metaObject);
//        this.setFieldValByName("version", 1, metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
//        LOGGER.error("start update fill...");
//        this.setFieldValByName("update_time", new Date(), metaObject);
    }
}
