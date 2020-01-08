package com.llm.demo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.llm.demo.IOrderService;
import com.llm.demo.entity.Course;
import com.llm.demo.entity.Order;
import com.llm.demo.entity.Result;
import com.llm.demo.entity.User;
import com.llm.demo.util.FileDownOrUpload;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {

    @Reference
    private IOrderService service;
    @Autowired
    private FreeMarkerConfig freeMarkerConfig;

    @Value("${mystatic}")
    public String filepath;

    /**
     * 教务登录后的课程列表
     * @param name
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping("/getcourseList")
    public PageInfo<Course> getcourseList(String name,int pageNo,int pageSize){
        return service.getcourseList(name,pageNo,pageSize);
    }

    /**
     * 普通学员登录的订单列表
     * @return
     */
    @ResponseBody
    @RequestMapping("/getorderList")
    public List<Order> getorderList(){
        return service.getorderList();
    }

    /**
     * 登录
     * @param name
     * @return
     */
    @RequestMapping("/login")
    public String login(String name){
        User user=service.selectbyname(name);
        if(user.getAuth()==1){
            return "redirect:html/courselist.html";
        }else{
            return "redirect:html/orderlist.html";
        }
    }

    /**
     * 上架
     * 并生成静态页
     * @param id
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/shangjia")
    public void  shangjia(Integer id) throws Exception {
        service.updatestate(id);
        Configuration configuration = freeMarkerConfig.getConfiguration();
        Template template = configuration.getTemplate("freemark.ftl");
        Map map = new HashMap<>();
        Course course=findOne(id);
        map.put("course",course);
        FileOutputStream fileOutputStream = new FileOutputStream(filepath + id + ".html", Boolean.parseBoolean("utf-8"));
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        Writer writer = new BufferedWriter(outputStreamWriter);
        template.process(map,writer);
        writer.close();
    }

    /**
     * 下架
     * @param id
     */
    @ResponseBody
    @RequestMapping("/xiajia")
    public void  xiajia(Integer id){
        service.updatestates(id);
    }

    /**
     * 添加课程
     * @param course
     * @return
     */

    @ResponseBody
    @RequestMapping("/insertcourse")
    public Result insertcourse(@RequestBody Course course){
        try {
            service.insertcourse(course);

           return new Result(true,"添加成功");
        }catch (Exception e){
            return new Result(false,"添加失败");

        }
    }

    /**
     * 上传图片
     * @param id
     * @param file
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/upload")
    public String upload(Integer id, MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String img = FileDownOrUpload.upload(file, request);
        service.updateimg(id,img);
        return "redirect:html/courselist.html";
    }

    /**
     * 根据id查询单条数据
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/findOne")
    public Course findOne(Integer id){
        return service.findOne(id);
    }
    @ResponseBody
    @RequestMapping("/findorder")
    public Order findorder(Integer id){
        return service.findorder(id);
    }

    /**
     * 添加订单
     * @param order
     * @return
     */
    @ResponseBody
    @RequestMapping("/insertorder")
    public Result insertorder(@RequestBody Order order){
        try {
            service.insertorder(order);
            return new Result(true,"添加成功");
        }catch (Exception e){
            return new Result(false,"添加失败");

        }
    }

}
