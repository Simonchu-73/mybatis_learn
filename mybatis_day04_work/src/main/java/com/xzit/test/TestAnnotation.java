package com.xzit.test;

import com.xzit.mapper.TeacherMapper;
import com.xzit.model.Teacher;
import com.xzit.util.SessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TestAnnotation {
    public static void main(String[] args) throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder =new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
        /*1.增加操作*/
        ArrayList<Teacher> list = new ArrayList<>();
        list.add(new Teacher().setName("张三").setAddr("江苏省南京市").setAge(35).setJob("区域总监").setSal(20000));
        list.add(new Teacher().setName("李四").setAddr("江苏省苏州市").setAge(32).setJob("销售经理").setSal(15000));
        list.add(new Teacher().setName("王五").setAddr("四川省成都市").setAge(26).setJob("销售员").setSal(8000));
        list.add(new Teacher().setName("赵六").setAddr("吉林省长春市").setAge(27).setJob("财务").setSal(10000));
        list.add(new Teacher().setName("刘七").setAddr("吉林省吉林市").setAge(34).setJob("销售员").setSal(6000));
        list.add(new Teacher().setName("吴八").setAddr("陕西省西安市").setAge(31).setJob("销售员").setSal(7000));
        for (Teacher teacher : list) {
            mapper.save(teacher);
        }
        sqlSession.commit();
        showResult("增加操作");
        /*2.删除操作*/
        mapper.delete(2);
        sqlSession.commit();
        showResult("删除操作");
        /*3.修改操作*/
        Teacher teacher = mapper.selectById(3);
        teacher.setName("王阳明");
        mapper.update(teacher);
        sqlSession.commit();
        showResult("修改操作");
        /*4.查询操作*/
        showResult("查询操作");

    }
    private static void showResult(String method) {
        SqlSession session = SessionUtil.getSession();
        TeacherMapper mapper = session.getMapper(TeacherMapper.class);
        List<Teacher> teachers = mapper.selectList();
        System.out.println( method+ "结果展示");
        for (Teacher teacher : teachers) {
            System.out.println(teacher);
        }
        System.out.println("---------------------------------------------------------");
    }


}
