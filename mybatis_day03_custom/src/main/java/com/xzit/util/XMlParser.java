package com.xzit.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMlParser {
    public static DbProfile parseXml(InputStream inputStream) throws DocumentException {
        DbProfile profile = new DbProfile();
        //使用dom4j读取xml文件
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        //读取到XML文件的根元素Configuration标签
        Element root = document.getRootElement();
        List<Element> properties=root.selectNodes("//property");
        for (Element property : properties) {
            if("driver".equals(property.attributeValue("name"))) {
                profile.setDriver(property.attributeValue("value"));
            }
            if("url".equals(property.attributeValue("name"))) {
                profile.setUrl(property.attributeValue("value"));
            }
            if("username".equals(property.attributeValue("name"))) {
                profile.setUsername(property.attributeValue("value"));
            }
            if("password".equals(property.attributeValue("name"))) {
                profile.setPassword(property.attributeValue("value"));
            }
        }
         List<Element> mappersList = root.selectNodes("//mappers/mapper");
        for (Element mapper : mappersList) {
            //获得StudentMapper.xml文件的路径
            String path=mapper.attributeValue("resource");

            if(path!=null) {
                //解析StudentMapper.xml文件
                Map<String,Mapper> map=getMapper(path);
                profile.setMappers(map);
            }
        }
        return profile;
    }
    private  static Map<String,Mapper> getMapper(String path) throws DocumentException {
        Map<String,Mapper> map=new HashMap<>();
        SAXReader reader = new SAXReader();
        Document document = reader.read(Resources.getResourceAsStream(path));
        //读到根元素mapper
        Element root = document.getRootElement();
        String namespace = root.attributeValue("namespace");
        List<Element> elements = root.selectNodes("//select");
        for (Element element : elements) {
            //StudentMapper.xml文件中sql语句的id
            String id=element.attributeValue("id");
            String resultType=element.attributeValue("resultType");
            String sql=element.getText();
            Mapper mapper=new Mapper();
            mapper.setClassName(resultType).setSqlStatement(sql);
            map.put(namespace+"."+id,mapper);//注意map的键是是StudentMapper.xml文件（特定实体对象对象sql语句）中对应sql语句的全路径
        }
        return map;

    }


}
