package com.llm.demo.mapper;

import com.llm.demo.entity.Course;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CourseMapper extends Mapper<Course> {

    @Select("SELECT * FROM z_course WHERE `name` LIKE concat('%',#{name},'%') ")
    List<Course> getcourseList(String name);

    @Update("update z_course set state=1 where id=#{id}")
    void updatestate(Integer id);
    @Update("update z_course set state=0 where id=#{id}")
    void updatestates(Integer id);

    @Select("select * from z_course where id=#{id}")
    Course findOne(Integer id);

    @Insert("insert into z_course (name,teacher,type,starttime,state) values (#{name},#{teacher},#{type},#{starttime},0)")
    void insertcourse(Course course);

    @Update("update z_course set img=#{img} where id=#{id}")
    void updateimg(Integer id, String img);
}