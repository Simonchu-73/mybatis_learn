package com.xzit.util;

import com.xzit.model.Student;
import org.dom4j.DocumentException;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbUtil {

    public static Connection getConnection(DbProfile profile) {

        try {
            Class.forName(profile.getDriver());

            return DriverManager.getConnection(profile.getUrl(), profile.getUsername(), profile.getPassword());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public <E> List<E> selectList(Mapper mapper, Connection connection) {
        String sql = mapper.getSqlStatement();
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String className = mapper.getClassName();
            Class<?> clazz = Class.forName(className);
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            List<E> list = new ArrayList<E>();
            while (rs.next()) {
                E object = (E) clazz.getConstructor().newInstance(null);
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = rs.getObject(i);

                    PropertyDescriptor pd = new PropertyDescriptor(columnName, clazz);
                    Method method = pd.getWriteMethod();
                    method.invoke(object, value);
                }
                list.add(object);
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs, ps);
        }
        return null;
    }


    private void close(ResultSet resultSet, Statement statement) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
