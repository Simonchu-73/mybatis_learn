package com.xzit.test;

import com.xzit.model.Student;
import com.xzit.util.SessionUtil;
import org.apache.ibatis.session.SqlSession;

public class TestInsertAndGetId {
    public static void main(String[] args) {
        SqlSession session = SessionUtil.getSession();
        Student student=new Student();
        student.setName("霍去病").setAge(19).setGender("男");
        //语句id 参数
        int insert = session.insert("student.insertAndGetId", student);
        session.commit();
        session.close();
        System.out.println(insert);
        System.out.println(student.getId());
    }
}
