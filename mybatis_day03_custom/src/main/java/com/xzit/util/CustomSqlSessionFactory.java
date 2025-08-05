package com.xzit.util;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomSqlSessionFactory implements SqlSessionFactory {
    private DbProfile profile;
    @Override
    public SqlSession openSession() {
        return new CustomSqlSession(profile);
    }
}
