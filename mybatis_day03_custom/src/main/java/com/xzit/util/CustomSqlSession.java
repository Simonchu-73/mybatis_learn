package com.xzit.util;

import lombok.AllArgsConstructor;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

@AllArgsConstructor
public class CustomSqlSession implements SqlSession {
    private DbProfile profile;
    private Connection connection;
    public CustomSqlSession(DbProfile profile) {
        this.profile = profile;
        this.connection=DbUtil.getConnection(profile);
    }
    @Override
    public <T> T getMapper(Class<T> mapperClass) {
        return (T) Proxy.newProxyInstance(mapperClass.getClassLoader(), //代理方法接口的类加载器，原有方法从何而来
                new Class[]{mapperClass}, // 代理方法接口class对象数据
                new ProxyImpl(profile.getMappers(), connection)); //新增功能工具类对象
    }

    @Override
    public void close() {
    if (connection != null) {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    }
}
