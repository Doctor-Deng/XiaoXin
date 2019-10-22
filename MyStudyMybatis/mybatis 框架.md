**mybatis 框架**

（1）：在 pom.xml 编写，导入的包，还有使用的包是什么类型

```xml
<packaging>jar</packaging>

    <dependencies>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.4.6</version>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.41</version>
        </dependency>

        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.12</version>
        </dependency>

        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.10</version>
        </dependency>
    </dependencies>
```

解析：我们用 mybatis 框架自然要导入 官方的依赖包，这个框架需要连接数据库，理所当然的需要 mysql 的依赖包，log4j 是一个日志的 xml，能让你更好的找到问题出现在哪，很多大企业都使用这个 log4j  （log4j 主要是输出你操作的过程）尤其是产品上线之后，出现问题，日志显得尤为重要，junit 是一个单元测试的依赖包，用于单元测试框架体系。一个项目不经过测试是很难成功被客户认可的。

（2）：在项目中 resource 资源文件夹中，创建 SqlMapConfig.xml 文件，

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <!--配置环境-->
    <environments default="mysql">
        <!--配置 mysql 环境-->
        <environment id="mysql">
            <transactionManager type="JDBC"></transactionManager>

            <!--配置数据库连接池-->
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"></property>
                <property name="url" value="jdbc:mysql://localhost:3306/mybatisdb?characterEncoding=utf-8"></property>
                <property name="username" value="root"></property>
                <property name="password" value="123"></property>
            </dataSource>
        </environment>
    </environments>
    <!--配置映射文件的位置-->
    <mappers>
        <mapper resource="com/dao/IUserDao.xml"></mapper>
    </mappers>
</configuration>
```

**注意**：配置数据库连接池的时候，一定要修改自己的 url 和 username、password，不要简单的复制粘贴。jdbc:mysql://localhost:3306/mybatisdb  中的mybatisdb 是数据库名。root 是数据库的用户名，123 是数据库的密码。

（3）在 java 文件夹中新建一个  com.domain，添加一个 User 类，将数据库文件表格里面的参数，私有化定义，并生成 get 和 set 方法。

（4）在项目的 java 文件夹下创建 com.dao 的 IUSerDao 接口（用户的持久层接口），然后写一个你想要的方法，可以写 List<User> findAll()

（5）在 dao 下面创建一个映射文件 IUserDao.xml 文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- namespace 是确定哪个接口 -->
 <mapper namespace="com.dao.IUserDao">
    <!-- id 是确定哪个方法 resultType 表示封装到哪里去 -->
    <select id="findAll" resultType="com.domain.User">
        <!-- 查询所有的操作 -->
        select * from user;
    </select>
</mapper>
```

（6）新建一个测试类

```java
package com;

import com.dao.IUserDao;
import com.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author XiaoXin
 * @title: MybatisTest
 * @projectName day02_01mybatisCRUD
 * @description: 测试 myabtis 的 crub 操作
 * @date 2019/10/8 11:12
 */
public class MybatisTest {
    /**
      * 测试查询所有
      * @Return void
      * @author XiaoXin
      * @date 2019/10/8 11:21
      */
    @Test
    public void testFindAll() throws IOException {
        // 读取配置文件，生成字节输入流
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 获取 SqlSessionFactory  对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 获取 SqlSession 对象
        SqlSession sqlSession = factory.openSession();
        // 获取 dao 的代理对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        // 执行查询所有方法
        List<User> users = userDao.findAll();
        for (User user : users){
            System.out.println(user);
        }

        // 关闭资源
        sqlSession.close();
        in.close();
    }
}

```

（7）接下来我们新增一个 saveUser方法，将自己的创建的数据添加到数据库里

首先在用户持久层接口 IUserDao 里添加方法

```java
package com.dao;

import com.domain.User;

import java.util.List;

/**
 * @author XiaoXin
 * @title: IUserDao
 * @projectName day02_01mybatisCRUD
 * @description: 用户的持久层接口
 * @date 2019/10/8 10:20
 */
public interface IUserDao {
    /**
      *
    查询所有用户
      * @Return java.util.List<com.domain.User>
      * @author XiaoXin
      * @date 2019/10/8 10:21
      */
    List<User> findAll();
    /**
      * 保存用户
      * @param user
      * @Return void
      * @author XiaoXin
      * @date 2019/10/8 11:41
      */
    void saveUser(User user);

    
}
```

然后在 IUserDao.xml 里面

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- namespace 是确定哪个接口 -->
 <mapper namespace="com.dao.IUserDao">
    <!-- id 是确定哪个方法 resultType 表示封装到哪里去 -->
    <select id="findAll" resultType="com.domain.User">
        <!-- 查询所有的操作 -->
        select * from user;
    </select>
    
    <!-- 保存用户 -->
    <insert id="saveUser" parameterType="com.domain.User">
        insert into user(username,address,sex,birthday)values(#{username},#{address},#{sex},#{birthday});
    </insert>


</mapper>
```

最终在 测试类中执行方法，进行测试-

```Java
package com;

import com.dao.IUserDao;
import com.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author XiaoXin
 * @title: MybatisTest
 * @projectName day02_01mybatisCRUD
 * @description: 测试 myabtis 的 crub 操作
 * @date 2019/10/8 11:12
 */
public class MybatisTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before // 测试方法最先执行
    public void init() throws IOException {
        // 读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 获取 SqlSessionFactory  对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 获取 SqlSession 对象
        sqlSession = factory.openSession();
        // 获取 dao 的代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    @After // 用于在测试方法执行之后执行
    public void destory() throws IOException {
        //提交事务
        sqlSession.commit();
        // 关闭资源
        sqlSession.close();
        in.close();
    }
    /**
      * 测试查询所有
      * @Return void
      * @author XiaoXin
      * @date 2019/10/8 11:21
      */
    @Test
    public void testFindAll() {
        // 执行查询所有方法
        List<User> users = userDao.findAll();
        for (User user : users){
            System.out.println(user);
        }
    }
    /**
      * 测试保存操作
      * @Return void
      * @author XiaoXin
      * @date 2019/10/8 11:47
      */
    @Test
    public void testSava() {
        User user = new User();
        user.setUsername("测试保存方法");
        user.setAddress("呀拉索");
        user.setSex("男");
        user.setBirthday(new Date());

        // 执行保存方法
        userDao.saveUser(user);

    }
    
}

```



细心的人可能会发现，这里另外新增了两个方法，是因为我们进行  testFindAll() 与  testSava() 方法都会先执行 init() 里面的内容，最后释放资源，也就是 destory() 的方法。那么 destory() 方法里还有一个提交事务的方法，testFindAll() 是不需要提交事务的，testSava() 需要提交事务，因此为了代码的整洁，所以添加到 destory() 方法里。

（8）接下来，为代码添加一些功能

我们尝试修改，User 实体类，让里面的属性，与数据库表中的属性名字不相同。

```java
package com.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author XiaoXin
 * @title: User
 * @projectName day02_01mybatisCRUD
 * @description: TODO
 * @date 2019/10/8 8:59
 */
public class User implements Serializable {
    private Integer userId;
    private String userName;
    private String userAddress;
    private String userSex;
    private Date userBirthday;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userbirthday=" + userBirthday +
                '}';
    }
}

```

首先，在用户持久层中添加接口

```java
package com.dao;

import com.domain.QueryVo;
import com.domain.User;

import java.util.List;

/**
 * @author XiaoXin
 * @title: IUserDao
 * @projectName day02_01mybatisCRUD
 * @description: 用户的持久层接口
 * @date 2019/10/8 10:20
 */
public interface IUserDao {
    /**
      *
    查询所有用户
      * @Return java.util.List<com.domain.User>
      * @author XiaoXin
      * @date 2019/10/8 10:21
      */
    List<User> findAll();
    /**
      * 保存用户
      * @param user
      * @Return void
      * @author XiaoXin
      * @date 2019/10/8 11:41
      */
    void saveUser(User user);
    /**
      * 修改用户
      * @param user
      * @Return void
      * @author XiaoXin
      * @date 2019/10/8 14:37
      */
    void updateUser(User user);
    /**
      * 根据 ID 删除用户
      * @param userId
      * @Return void
      * @author XiaoXin
      * @date 2019/10/8 14:37
      */
    void delUser(Integer userId);
    /**
      * 根据输入的 ID 查询信息
      * @param userId
      * @Return void
      * @author XiaoXin
      * @date 2019/10/8 14:51
      */
    User findById(Integer userId);
    /**
      * 根据名称模糊的查询用户的信息
      * @param name
      * @Return java.util.List<com.domain.User>
      * @author XiaoXin
      * @date 2019/10/8 15:06
      */
    List<User> findByName(String name);
    /**
      * 查询总用户数
      * @Return int
      * @author XiaoXin
      * @date 2019/10/8 15:17
      */
    int findTotal();

    /**
      * 根据 queryVo 中的条件查询用户
      * @param vo
      * @Return int
      * @author XiaoXin
      * @date 2019/10/8 16:13
      */
    List<User> findUserByVo(QueryVo vo);
}
```

然后在 IuserDao.xml 写 select方法语句

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- namespace 是确定哪个接口 -->
 <mapper namespace="com.dao.IUserDao">

    <!-- 如果在 User 实体中的属性，和数据库的一致，就不用进行这种配置-->
    <!-- 配置  查询结果的列名和实体类的属性名的对应关系 -->
    <resultMap id="userMap" type="com.domain.User">
        <!-- 主键字段的对应配置 -->
        <!-- property 里写的是你在 User 实体类里定义的，与 column 相对应-->
        <id property="userId" column="id"></id>

        <!-- 非主键字段的对应配置 -->
        <result property="userName" column="username"></result>
        <result property="userSex" column="sex"></result>
        <result property="userBirthday" column="birthday"></result>
        <result property="userAddress" column="address"></result>

    </resultMap>

    <!-- id 是确定哪个方法 resultType 表示封装到哪里去 -->
    <select id="findAll" resultMap="userMap">
        <!-- 查询所有的操作 -->
        <!-- select id as userId, username as userName, birthday as userBirthday, sex as userSex, address as userAdress from user; -->
        select * from user;
    </select>
    
    <!-- 保存用户 -->
    <insert id="saveUser" parameterType="com.domain.User">
        <!-- 配置插入操作后，获取插入数据的 id -->
        <selectKey keyProperty="userId" keyColumn="id" resultType="int" order="AFTER">
            select last_insert_id();
        </selectKey>
        insert into user(username,address,sex,birthday)values(#{userName},#{userAddress},#{userSex},#{userBirthday});
    </insert>

    <!-- 修改用户 -->
    <update id="updateUser" parameterType="com.domain.User">
        update user set username=#{userName},address=#{userAddress},sex=#{userSex},birthday=#{userBirthday} where id=#{userId};
    </update>

    <!-- 删除用户 -->
    <delete id="delUser" parameterType="java.lang.Integer">
        delete from user where id=#{uid};
    </delete>

    <!-- 根据 id 查询用户 -->
    <select id="findById" parameterType="int" resultType="com.domain.User">
        select * from user where id=#{uid}
    </select>

    <!-- 根据名称模糊查询用户 -->
    <select id="findByName" parameterType="string" resultType="com.domain.User">
           <!-- 注意，第一种写法的 ${value} 是固定的，不能改其它的名字。相比第二种写法，好处就是在测试类中 testfindByName 里面的名字不用写 % 了-->
           select * from user where username like '%${value}%'
           <!-- 第二种写法 -->
           <!-- select * from user where username like #{name} -->
    </select>

    
    <!-- 获取用户的总记录条数 -->
    <select id="findTotal" resultType="int">
        select count(id) from user;
    </select>

    <!-- 根据 queryVo 的条件查询用户 -->
    <select id="findUserByVo" parameterType="com.domain.QueryVo" resultType="com.domain.User">
        select * from user where username like #{user.username};
    </select>
</mapper>
```

**注意：**如果在 User 实体中的属性，和数据库的一致，就不用进行这种配置

假如 不一致，那么有两种方法。各有优缺点

第一种：起别名，我们知道 mysql 语句中是有起别名的功能。
上面一句将下面一句掉换。每一个方法中都要这样起别名，只要有 mysql 语句的都需要改。
优点：程序执行效率快
缺点：很麻烦

```xml
  		select id as userId, username as userName, birthday as userBirthday, sex as userSex, address as userAdress from user; 
        select * from user;
```

第二种：使用 resultMap

```xml
 <!-- 配置  查询结果的列名和实体类的属性名的对应关系 -->
    <resultMap id="userMap" type="com.domain.User">
        <!-- 主键字段的对应配置 -->
        <!-- property 里写的是你在 User 实体类里定义的，与 column 相对应-->
        <id property="userId" column="id"></id>

        <!-- 非主键字段的对应配置 -->
        <result property="userName" column="username"></result>
        <result property="userSex" column="sex"></result>
        <result property="userBirthday" column="birthday"></result>
        <result property="userAddress" column="address"></result>

    </resultMap>
```

优点：很快，相比第一种方法，开发人员省力很多
缺点：程序执行的效率会下降



在 测试主类中写

```java
package com;

import com.dao.IUserDao;
import com.domain.QueryVo;
import com.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author XiaoXin
 * @title: MybatisTest
 * @projectName day02_01mybatisCRUD
 * @description: 测试 myabtis 的 crub 操作
 * @date 2019/10/8 11:12
 */
public class MybatisTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before // 测试方法最先执行
    public void init() throws IOException {
        // 读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 获取 SqlSessionFactory  对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 获取 SqlSession 对象
        sqlSession = factory.openSession();
        // 获取 dao 的代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    @After // 用于在测试方法执行之后执行
    public void destory() throws IOException {
        //提交事务
        sqlSession.commit();
        // 关闭资源
        sqlSession.close();
        in.close();
    }
    /**
      * 测试查询所有
      * @Return void
      * @author XiaoXin
      * @date 2019/10/8 11:21
      */
    @Test
    public void testFindAll() {
        // 执行查询所有方法
        List<User> users = userDao.findAll();
        for (User user : users){
            System.out.println(user);
        }
    }
    /**
      * 测试保存操作
      * @Return void
      * @author XiaoXin
      * @date 2019/10/8 11:47
      */
    @Test
    public void testSava() {
        User user = new User();
        user.setUserName("测试方法");
        user.setUserAddress("呀拉索");
        user.setUserSex("男");
        user.setUserBirthday(new Date());

        System.out.println("保存前："+ user);

        // 执行保存方法
        userDao.saveUser(user);

        System.out.println("保存前："+ user);
    }

    /**
      * 执行更新操作
      * @Return void
      * @author XiaoXin
      * @date 2019/10/8 14:33
      */
    @Test
    public void testUpdate() {
        User user = new User();
        user.setUserId(53);
        user.setUserName("测试更新方法");
        user.setUserAddress("呀拉索");
        user.setUserSex("男");
        user.setUserBirthday(new Date());

        // 执行更新方法
        userDao.updateUser(user);
    }
    /**
      * 根据 ID 删除操作
      * @Return void
      * @author XiaoXin
      * @date 2019/10/8 14:49
      */
    @Test
    public void testDel(){
        userDao.delUser(58);
    }
    /**
      * 根据 id 查询用户
      * @Return void
      * @author XiaoXin
      * @date 2019/10/8 15:08
      */
    @Test
    public void testFindOne(){
        User user = userDao.findById(56);
        System.out.println(user);
    }
    /**
      * 根据 姓名 模糊查找
      * @Return void
      * @author XiaoXin
      * @date 2019/10/8 15:19
      */
    @Test
    public void testFindByName(){
        List<User> users= userDao.findByName("徐");
        for (User user1 : users){
            System.out.println(user1);
        }
    }
   /**
     * 测试查询总记录条数
     * @Return void
     * @author XiaoXin
     * @date 2019/10/8 15:21
     */
    @Test
    public void testFindTotal(){
        int count = userDao.findTotal();
        System.out.println(count);
    }

    @Test
    public void testFindByQueryVo(){
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUserName("%王%");
        vo.setUser(user);
        List<User> users = userDao.findUserByVo(vo);
        for (User user1 : users){
            System.out.println(user1);
        }
    }
}

```

mybatis 中 resultType 和 resultMap 使用时的区别

```java
public class User {
  private int id;
  private String username;
  private String hashedPassword;
   
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getHashedPassword() {
    return hashedPassword;
  }
  public void setHashedPassword(String hashedPassword) {
    this.hashedPassword = hashedPassword;
  }
}
```

resultType 

```xml
<!-- In mybatis-config.xml file -->
<typeAlias type="com.someapp.model.User" alias="User"/>

使用resultType

<select id="selectUsers" parameterType="int" resultType="com.someapp.model.User">
  select id, username, hashedPassword
  from some_table
  where id = #{id}
</select>
```

resultMap 起别名，然后直接引入别名

```
<resultMap id="userResultMap" type="User">
  <id property="id" column="user_id" />
  <result property="username" column="username"/>
  <result property="password" column="password"/>
</resultMap>

<select id="selectUsers" parameterType="int" resultMap="userResultMap">
  select user_id, user_name, hashed_password
  from some_table
  where id = #{id}
</select>
```

