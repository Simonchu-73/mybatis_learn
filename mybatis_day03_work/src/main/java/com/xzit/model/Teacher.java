package com.xzit.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Teacher {
    private int id;
    private String name;
    private String addr;
    private int age;
    private String job;
    private int sal;
}
