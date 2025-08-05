package com.xzit.mapper;

import com.xzit.model.Teacher;

import java.util.List;

public interface TeacherMapper {
    int insert(List<Teacher> list);
    List<Teacher> selectListOfCity(String city);
    double getAvgSalOfCity(String city);
    int updateJob(String city);
    int deleteByCheck(List<Integer> ids);
    List<Teacher> selectList();

}
