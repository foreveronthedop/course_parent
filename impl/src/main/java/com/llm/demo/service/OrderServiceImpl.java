package com.llm.demo.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.llm.demo.IOrderService;
import com.llm.demo.entity.Course;
import com.llm.demo.entity.Order;
import com.llm.demo.entity.User;
import com.llm.demo.mapper.CourseMapper;
import com.llm.demo.mapper.OrderMapper;
import com.llm.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service
@Component
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CourseMapper courseMapper;

    /**
     * 分页查询列表
     * @param name
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Course> getcourseList(String name, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<Course> list= courseMapper.getcourseList(name);
        PageInfo<Course> pageInfo = new PageInfo<Course>(list);
        return pageInfo;
    }

    /**
     * 查询订单列表
     * @return
     */
    @Override
    public List<Order> getorderList() {
        return orderMapper.getorderList();
    }

    /**
     * 登录
     * @param name
     * @return
     */
    @Override
    public User login(String name) {
        return userMapper.login(name);
    }

    /**
     * 根据name查询用户数据
     * @param name
     * @return
     */
    @Override
    public User selectbyname(String name) {
        return userMapper.selectbyname(name);
    }

    /**
     * 修改状态值
     * @param id
     */
    @Override
    public void updatestate(Integer id) {
        courseMapper.updatestate(id);
    }

    @Override
    public void updatestates(Integer id) {
        courseMapper.updatestates(id);
    }

    @Override
    public Course findOne(Integer id) {
        return courseMapper.findOne(id);
    }

    /**
     * 添加课程
     * @param course
     */
    @Override
    public void insertcourse(Course course) {
        courseMapper.insertcourse(course);
    }

    /**
     * 上传图片
     * @param id
     * @param img
     */
    @Override
    public void updateimg(Integer id, String img) {
        courseMapper.updateimg(id,img);

    }

    /**
     * 添加订单
     * @param order
     */
    @Override
    public void insertorder(Order order) {
        orderMapper.insertorder(order);
    }

    @Override
    public Order findorder(Integer id) {
        return orderMapper.findorder(id);
    }
}
