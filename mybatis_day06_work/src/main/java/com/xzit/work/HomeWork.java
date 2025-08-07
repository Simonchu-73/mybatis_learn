package com.xzit.work;

import com.xzit.mapper.UsersMapper;
import com.xzit.model.Permission;
import com.xzit.model.Role;
import com.xzit.model.Users;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class HomeWork {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        UsersMapper mapper = session.getMapper(UsersMapper.class);
        Users user = new Users();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        user.setUname(sc.next());
        System.out.println("请输入密码：");
        user.setPwd(sc.next());

        System.out.println("---------------------------------------------------------");
        Users login = mapper.login(user);
        if (login == null) {
            System.out.println("用户不存在");
            return;
        }

        LocalDateTime now = LocalDateTime.now();


        login.setLastTime(now);
        mapper.updateLastTime(login);
        System.out.println("---------------登入时间" + now + "---------------");
        session.commit();
        System.out.println("登入的账户是：");
        System.out.println(user.getUname());
        System.out.println("角色信息");
        List<Role> roles = login.getRoles();
        for (Role role : roles) {
            System.out.print(role.getRName() + "\t");
        }
        System.out.println();
        System.out.println("权限是：");
        List<Permission> permissions = login.getPermissions();
        for (Permission permission : permissions) {
            System.out.print(permission.getPName() + "\t");
        }


        session.close();

    }
}
