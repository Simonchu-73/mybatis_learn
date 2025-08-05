package com.xzit.work;

import com.xzit.mapper.TeacherMapper;
import com.xzit.model.Teacher;
import com.xzit.util.SessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HomeWork {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH);
        TeacherMapper mapper = session.getMapper(TeacherMapper.class);
        /*1、使用动态拼接 sql 方式批量向数据库表中插入数据*/
        ArrayList<Teacher> list = new ArrayList<>();
        list.add(new Teacher().setName("张三").setAddr("江苏省南京市").setAge(35).setJob("区域总监").setSal(20000));
        list.add(new Teacher().setName("李四").setAddr("江苏省苏州市").setAge(32).setJob("销售经理").setSal(15000));
        list.add(new Teacher().setName("王五").setAddr("四川省成都市").setAge(26).setJob("销售员").setSal(8000));
        list.add(new Teacher().setName("赵六").setAddr("吉林省长春市").setAge(27).setJob("财务").setSal(10000));
        list.add(new Teacher().setName("刘七").setAddr("吉林省吉林市").setAge(34).setJob("销售员").setSal(6000));
        list.add(new Teacher().setName("吴八").setAddr("陕西省西安市").setAge(31).setJob("销售员").setSal(7000));
        mapper.insert(list);
        session.commit();
        showResult(1);
        /*2、使用动态拼接 sql 方式，查询给定城市所有员工信息*/
        System.out.println("第2问结果展示");
        List<Teacher> jiangSu = mapper.selectListOfCity("江苏省");
        List<Teacher> jiLin = mapper.selectListOfCity("吉林省");
        List<Teacher> all = mapper.selectListOfCity("");
        System.out.println("江苏省员工信息");
        jiangSu.forEach(System.out::println);
        System.out.println("吉林省员工信息");
        jiLin.forEach(System.out::println);
        System.out.println("参数为空时员工信息");
        all.forEach(System.out::println);
        System.out.println("----------------------------------------------------------------------------");
        /*3、使用动态拼接 sql 方式，统计在给定省份工作的员工平均工资*/
        System.out.println("第3问结果展示");
        double salOfJiangSu = mapper.getAvgSalOfCity("江苏省");
        double salOfJiLin = mapper.getAvgSalOfCity("吉林省");
        double salOfAll = mapper.getAvgSalOfCity("");
        System.out.println("江苏省员工平均工资");
        System.out.println(salOfJiangSu);
        System.out.println("吉林省员工平均工资");
        System.out.println(salOfJiLin);
        System.out.println("参数为空时员工平均工资");
        System.out.println(salOfAll);
        System.out.println("----------------------------------------------------------------------------");
        /*4、使用动态拼接 sql 方式，更新给定城市员工的工资和职务*/
        mapper.updateJob("吉林省");
        session.commit();
        showResult(4);

        /*5、使用动态拼接 sql 方式，删除给定 id 数组的员工*/
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(4);
        ids.add(2);
        mapper.deleteByCheck(ids);
        session.commit();
        showResult(5);

        session.close();
    }
    private static void showResult(int num) {
        SqlSession session = SessionUtil.getSession();
        TeacherMapper mapper = session.getMapper(TeacherMapper.class);
        List<Teacher> teachers = mapper.selectList();
        System.out.println("第" + num + "问结果展示");
        if(num==4){
            System.out.println("将吉林省员工职位更新为客服老师，工资改为12000");
        }
        for (Teacher teacher : teachers) {
            System.out.println(teacher);
        }
        System.out.println("----------------------------------------------------------------------------");
    }
}
