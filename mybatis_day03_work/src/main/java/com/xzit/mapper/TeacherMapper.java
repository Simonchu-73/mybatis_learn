package com.xzit.mapper;

import com.xzit.model.Teacher;

import java.util.List;

public interface TeacherMapper {
   int insert(Teacher teacher);
   List<Teacher> selectListOfJiangSu();
   double getAvgSalOfJiLin();
   int updateJob();
   int delete();
   List<Teacher> selectList();

}
