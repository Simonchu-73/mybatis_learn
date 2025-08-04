package com.xzit.mapper;

import com.xzit.model.Teacher;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface TeacherMapper {
    @Insert("INSERT INTO teacher VALUES (DEFAULT,#{name},#{addr},#{age},#{job},#{sal})")
    int save(Teacher teacher);
    @Delete("DELETE FROM teacher WHERE id=#{id}")
    int delete(int id);
    @Update("UPDATE teacher SET name=#{name},addr=#{addr},job=#{job},sal=#{sal} WHERE id=#{id}")
    int update(Teacher teacher);
    @Select("SELECT * FROM teacher")
    List<Teacher> selectList();
    @Select("SELECT * FROM teacher WHERE id=#{id}")
    Teacher selectById(int id);
}
