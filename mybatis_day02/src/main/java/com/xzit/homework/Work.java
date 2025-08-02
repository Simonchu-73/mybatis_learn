package com.xzit.homework;

import com.xzit.model.Teacher;
import com.xzit.util.SessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class Work {
    public static void main(String[] args) {
        SqlSession session = SessionUtil.getSession();
        /*1、根据第一天中表的结构，向表中插入数据*/
        ArrayList<Teacher> list = new ArrayList<>();
        list.add(new Teacher().setName("张三").setAddr("江苏省南京市").setAge(35).setJob("区域总监").setSal(20000));
        list.add(new Teacher().setName("李四").setAddr("江苏省苏州市").setAge(32).setJob("销售经理").setSal(15000));
        list.add(new Teacher().setName("王五").setAddr("四川省成都市").setAge(26).setJob("销售员").setSal(8000));
        list.add(new Teacher().setName("赵六").setAddr("吉林省长春市").setAge(27).setJob("财务").setSal(10000));
        list.add(new Teacher().setName("刘七").setAddr("吉林省吉林市").setAge(34).setJob("销售员").setSal(6000));
        list.add(new Teacher().setName("吴八").setAddr("陕西省西安市").setAge(31).setJob("销售员").setSal(7000));
        for (Teacher teacher : list) {
            session.insert("teacher.insertObject", teacher);
        }
        session.commit();
        /*2.查询所有江苏省的员工信息*/
        List<Teacher> teachers = session.selectList("teacher.selectListOfJiangSu");
        System.out.println("江苏省员工信息：");
        for (Teacher teacher : teachers) {
            System.out.println(teacher);
        }
        System.out.println("------------------------------------------------------------------");
        /*3、计算吉林省员工的平均工资*/
        double avg = session.selectOne("teacher.getAvgSalOfJiLin");
        System.out.println("吉林省员工平均工资是：" + avg);
        System.out.println("------------------------------------------------------------------");
        /*4、将所有年龄超过 30 岁的销售员提升为销售经理*/
        session.update("teacher.updateJob");
        session.commit();
        System.out.println("升职后员工信息：");
        List<Teacher> newlist = session.selectList("teacher.selectList");
        for (Teacher teacher : newlist) {
            System.out.println(teacher);
        }
        System.out.println("------------------------------------------------------------------");
        /*5、删除年龄超过 35 岁的员工*/
        session.delete("teacher.delete");
        session.commit();
        List<Teacher> listAfterDelete = session.selectList("teacher.selectList");
        for (Teacher teacher : listAfterDelete) {
            System.out.println(teacher);
        }
        System.out.println("------------------------------------------------------------------");
        session.close();
    }
}
