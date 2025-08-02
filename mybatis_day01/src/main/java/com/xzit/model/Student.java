package com.xzit.model;

import lombok.Data;

@Data
//有了lombok插件，加上一个@Data注解，其JavaBean的Getter和Setter方法，toString方法、无参有参构造方法都已经提供了
public class Student {
    private int id;
    private String name;
    private int age;
    private String gender;


}
