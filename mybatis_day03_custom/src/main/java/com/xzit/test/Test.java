package com.xzit.test;

import com.xzit.mapper.StudentMapper;
import com.xzit.model.Student;
import com.xzit.util.Resources;
import com.xzit.util.SqlSession;
import com.xzit.util.SqlSessionFactory;
import com.xzit.util.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class Test {
    public static void main(String[] args) {
       InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory=builder.build(inputStream);
        SqlSession session=factory.openSession();
        StudentMapper mapper=session.getMapper(StudentMapper.class);
        List<Student> list=mapper.selectList();
        list.forEach(System.out::println);
        session.close();
    }
}
