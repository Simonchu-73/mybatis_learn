package com.xzit.test;

import com.xzit.model.Student;
import com.xzit.util.SessionUtil;
import org.apache.ibatis.session.SqlSession;

public class TestInsertObject {
    public static void main(String[] args) {
        SqlSession session= SessionUtil.getSession();
        Student student=new Student();
        student.setName("李清照").setAge(16).setGender("女");
        //语句id 参数
        int insert = session.insert("student.insertObject", student);
        session.commit();
        session.close();
        System.out.println(insert);

    }
}
