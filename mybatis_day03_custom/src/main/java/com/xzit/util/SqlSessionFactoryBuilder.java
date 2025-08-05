package com.xzit.util;

import org.dom4j.DocumentException;

import java.io.InputStream;

public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream inputStream) {
        //解析全局文件，获取数据库相关对象
        try {
            DbProfile profile = XMlParser.parseXml(inputStream);
            return new CustomSqlSessionFactory(profile);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}
