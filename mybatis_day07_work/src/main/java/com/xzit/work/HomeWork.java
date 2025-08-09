package com.xzit.work;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xzit.mapper.TeacherMapper;
import com.xzit.model.Teacher;
import com.xzit.model.TeacherExample;
import org.apache.ibatis.io.Resources;

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
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sessionFactory.openSession();
        TeacherMapper mapper = session.getMapper(TeacherMapper.class);
        /*
        1.使用 mybatis 逆向工程，生成对应的 model 和 mapper 处理
        2.调用逆向工程 API 向其中插入数据
        */
        ArrayList<Teacher> list = new ArrayList<>();
        list.add(new Teacher().setName("张三").setAddr("江苏省南京市").setAge(35).setJob("区域总监").setSal(20000));
        list.add(new Teacher().setName("李四").setAddr("江苏省苏州市").setAge(32).setJob("销售经理").setSal(15000));
        list.add(new Teacher().setName("王五").setAddr("四川省成都市").setAge(26).setJob("销售员").setSal(8000));
        list.add(new Teacher().setName("赵六").setAddr("吉林省长春市").setAge(27).setJob("财务").setSal(10000));
        list.add(new Teacher().setName("刘七").setAddr("吉林省吉林市").setAge(34).setJob("销售员").setSal(6000));
        list.add(new Teacher().setName("吴八").setAddr("陕西省西安市").setAge(31).setJob("销售员").setSal(7000));
        list.add(new Teacher().setName("张丽 ").setAddr("江苏省南京市").setAge(32).setJob("销售员").setSal(7000));
        list.add(new Teacher().setName("张伟").setAddr("江苏省苏州市").setAge(26).setJob("职员").setSal(5000));
        list.add(new Teacher().setName("王晓").setAddr("上海市").setAge(30).setJob("区域总监").setSal(20000));
        list.add(new Teacher().setName("赵敏").setAddr("上海市").setAge(25).setJob("财务").setSal(10000));
        for (Teacher teacher : list) {
            mapper.insert(teacher);
        }
        session.commit();
        showResult("1、2");
        /*3、调用逆向工程 API 及分页插件，使用查询江苏省员工信息，每 3 条数据一页*/
        TeacherExample example = new TeacherExample();
        example.createCriteria().andAddrLike("江苏省%");
        PageHelper.startPage(1, 3);
        List<Teacher> teachers = mapper.selectByExample(example);
        PageInfo<Teacher> pageInfo = new PageInfo<>(teachers);
        List<Teacher> pageList = pageInfo.getList();
        System.out.println("第3问结果展示");
        for (Teacher teacher : pageList) {
            System.out.println(teacher);
        }
        System.out.println("-----------------------------------------------------------------------");
        /*4、调用逆向工程 API 将所有 27 岁以上的销售员，工资加 1000 元*/
        TeacherExample exampleForUpdate = new TeacherExample();
        exampleForUpdate.createCriteria().andAgeGreaterThan(27);

        List<Teacher> teachersForUpdate = mapper.selectByExample(exampleForUpdate);
        for (Teacher teacher : teachersForUpdate) {
            Teacher t = new Teacher();
            t.setSal(teacher.getSal() + 1000);
            t.setId(teacher.getId());
            mapper.updateByPrimaryKeySelective(t);
        }
        session.commit();
        showResult("4");
        /*5、调用逆向工程 API 将陕西省员工删除*/
        TeacherExample exampleForDelete = new TeacherExample();
        exampleForDelete.createCriteria().andAddrLike("陕西省%");
        mapper.deleteByExample(exampleForDelete);
        session.commit();
        showResult("5");
        session.close();
    }

    public static void showResult(String num) {
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sessionFactory.openSession();
        TeacherMapper mapper = session.getMapper(TeacherMapper.class);
        List<Teacher> teachers = mapper.selectAll();
        System.out.println("第" + num + "结果展示");
        for (Teacher teacher : teachers) {
            System.out.println(teacher);
        }
        System.out.println("-----------------------------------------------------------------------");
        session.close();
    }
}
