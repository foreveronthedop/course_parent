package com.llm.demo.mapper;

import com.llm.demo.entity.User;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
    @Select("select * from z_user where name=#{name}")
    User login(String name);

    @Select("select * from z_user where name=#{name}")
    User selectbyname(String name);
}