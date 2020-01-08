package com.llm.demo;

import com.github.pagehelper.PageInfo;
import com.llm.demo.entity.Course;
import com.llm.demo.entity.Order;
import com.llm.demo.entity.User;

import java.util.List;

public interface IOrderService {

    //分页查询方法
    PageInfo<Course> getcourseList(String name, int pageNo, int pageSize);

    //查询订单列表
    List<Order> getorderList();


    //登录
    User login(String name);

    //根绝name查询用户数据
    User selectbyname(String name);

    //修改状态值
    void updatestate(Integer id);

    void updatestates(Integer id);

    Course findOne(Integer id);


    void insertcourse(Course course);

    void updateimg(Integer id, String img);


    void insertorder(Order order);

    Order findorder(Integer id);
}
