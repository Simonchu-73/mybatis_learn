package com.xzit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
//有了lombok插件，加上一个@Data注解，其JavaBean的Getter和Setter方法，toString方法、无参有参构造方法都已经提供了
@Accessors(chain = true)
//允许我们进行set方法的链式变成，set方法变成返回对象本身
@AllArgsConstructor
//创建全参构造方法
@NoArgsConstructor
//无参构造方法
public class Student {
    private int id;
    private String name;
    private int age;
    private String gender;


}
