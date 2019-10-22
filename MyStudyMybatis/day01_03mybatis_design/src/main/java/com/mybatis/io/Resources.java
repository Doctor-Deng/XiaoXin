package com.mybatis.io;

import java.io.InputStream;

/**
 * @author XiaoXin
 * @title: Resources
 * @projectName day01_03mybatis_design
 * @description: 使用类加载器读取配置文件的类
 * @date 2019/10/6 15:38
 */
public class Resources {
    /**
      * @description: 根据传入的参数，获取一个字节输入流
      * @param filePath
      * @return ${return_type} 
      * @throws
      * @author XiaoXin
      * @date 2019/10/6 15:53
      */
    public static InputStream getResourceAsStream(String filePath){
        return Resources.class.getClassLoader().getResourceAsStream(filePath);
    }
}
