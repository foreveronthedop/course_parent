package com.llm.demo.mapper;

import com.github.pagehelper.PageInfo;
import com.llm.demo.entity.Course;
import com.llm.demo.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OrderMapper extends Mapper<Order> {
    @Select("select * from z_order")
    List<Order> getorderList();


    @Insert("insert into z_order (orderno,name,creattime,state) values (#{orderno},#{name},#{creattime},0)")
    void insertorder(Order order);

    @Select("select * from z_order where cid=#{id}")
    Order findorder(Integer id);
}