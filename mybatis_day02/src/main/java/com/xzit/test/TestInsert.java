package com.xzit.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class TestInsert {
    public static void main(String[] args) throws IOException {
        InputStream inputStream= Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int insert = sqlSession.insert("student.insert");
        System.out.println(insert);
        //执行查询对数据库没有影响，不用事务提交，而增删改对数据库有影响，必须事务提交
        sqlSession.commit();
        sqlSession.close();
    }
}
