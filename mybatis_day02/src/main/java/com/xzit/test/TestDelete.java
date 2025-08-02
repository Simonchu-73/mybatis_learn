package com.xzit.test;

import com.xzit.util.SessionUtil;
import org.apache.ibatis.session.SqlSession;

public class TestDelete {
    public static void main(String[] args) {
        SqlSession sqlSession = SessionUtil.getSession();
        int delete = sqlSession.delete("student.delete", 7);
        System.out.println(delete);
        sqlSession.commit();
        sqlSession.close();

    }
}
