package com.xzit.model;

import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class Teacher {
    private Integer id;
    private String name;
    private String addr;
    private Integer age;
    private String job;
    private Integer sal;
}