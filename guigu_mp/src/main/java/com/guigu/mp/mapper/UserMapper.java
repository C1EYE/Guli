package com.guigu.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guigu.mp.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {

}
