package com.xzit.util;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Mapper {
    //封装xml文件中sql语句
    private String sqlStatement;
    //封装xml文件中返回值类型的
    private String className;
}
