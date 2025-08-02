package com.xzit.test;

import com.xzit.model.Student;
import com.xzit.util.SessionUtil;
import org.apache.ibatis.session.SqlSession;

public class TestSelectById {
    public static void main(String[] args) {
        SqlSession session = SessionUtil.getSession();
        Student stu = session.selectOne("student.selectById", 1);
        System.out.println(stu);
        session.close();
    }
}
