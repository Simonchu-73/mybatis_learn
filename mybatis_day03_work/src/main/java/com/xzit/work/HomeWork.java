package com.xzit.work;

import com.xzit.mapper.TeacherMapper;
import com.xzit.model.Teacher;
import com.xzit.util.SessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class HomeWork {
    public static void main(String[] args) {
        SqlSession session = SessionUtil.getSession();
        TeacherMapper mapper = session.getMapper(TeacherMapper.class);
        /*1.根据第一天中表的结构，向表中插入数据*/
        ArrayList<Teacher> list = new ArrayList<>();
        list.add(new Teacher().setName("张三").setAddr("江苏省南京市").setAge(35).setJob("区域总监").setSal(20000));
        list.add(new Teacher().setName("李四").setAddr("江苏省苏州市").setAge(32).setJob("销售经理").setSal(15000));
        list.add(new Teacher().setName("王五").setAddr("四川省成都市").setAge(26).setJob("销售员").setSal(8000));
        list.add(new Teacher().setName("赵六").setAddr("吉林省长春市").setAge(27).setJob("财务").setSal(10000));
        list.add(new Teacher().setName("刘七").setAddr("吉林省吉林市").setAge(34).setJob("销售员").setSal(6000));
        list.add(new Teacher().setName("吴八").setAddr("陕西省西安市").setAge(31).setJob("销售员").setSal(7000));
        for (Teacher teacher : list) {
            mapper.insert(teacher);
        }
        session.commit();
        showResult(1);
        System.out.println("------------------------------------------------------------------------------");
        /*2、查询所有江苏省的员工信息*/
        List<Teacher> teachers = mapper.selectListOfJiangSu();
        System.out.println("第2问结果展示");
        for (Teacher teacher : teachers) {
            System.out.println(teacher);
        }
        System.out.println("------------------------------------------------------------------------------");
        /*3、计算吉林省员工的平均工资*/
        double avgSalOfJiLin = mapper.getAvgSalOfJiLin();
        System.out.println("第三问结果展示");
        System.out.println("吉林省员工平均工资= " + avgSalOfJiLin);
        System.out.println("------------------------------------------------------------------------------");
        /*4、将所有年龄超过 30 岁的销售员提升为销售经理*/
        mapper.updateJob();
        session.commit();
        showResult(4);
        System.out.println("------------------------------------------------------------------------------");
        /*5、删除年龄超过 35 岁的员工*/
        mapper.delete();
        session.commit();
        showResult(5);

        session.close();
    }

    private static void showResult(int num) {
        SqlSession session = SessionUtil.getSession();
        TeacherMapper mapper = session.getMapper(TeacherMapper.class);
        List<Teacher> teachers = mapper.selectList();
        System.out.println("第" + num + "问结果展示");
        for (Teacher teacher : teachers) {
            System.out.println(teacher);
        }
    }
}
