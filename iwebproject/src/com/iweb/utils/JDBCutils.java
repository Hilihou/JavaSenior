package com.iweb.utils;

import org.testng.annotations.Test;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JDBCutils {


    /**
     * 单元测试方法，你可以测试工具类里面的一些方法
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
//        System.out.println(update("delete from dept1 where deptno=?", 112));
//        Connection connecton = getConnecton();
//        System.out.println(connecton);
//        update("INSERT INTO fiction(`fictionName`,`author`,`pages`,`createTime`,`descriptions`,`isMember`,`isputaway`,`putawayTime`,`fictionimg`) VALUES(?,?,?,?,?,?,?,?,?)","西游记","吴承恩",852,"1666-8-6","四大名著","Y","Y","1858-6-9",null);
    }

    /**
     * 获取连接，只能获取连接，如调用，需要手动关闭
     * 此方法调用配置文件，如需更改连接，修改src下的JDBC.properties文件即可
     * @return
     * @throws Exception
     */
    public static Connection getConnecton() throws Exception{
        //加载并读取Properties文件
        InputStream resourceAsStream = ClassLoader.getSystemClassLoader().getResourceAsStream("JDBC.properties");
        Properties properties = new Properties();
        properties.load(resourceAsStream);
        String driver = properties.getProperty("Driver");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        //加载驱动
        Class.forName(driver);
        //获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }



    /**
     * 关闭connection，preparedStatement的连接
     * 关闭连接的重载方法其一，看参数就知道了，把这两个连接放进去就给你关了（哼）
     * @param connection
     * @param preparedStatement
     */
    public static void closeResources(Connection connection, PreparedStatement preparedStatement){
        if (connection!=null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (preparedStatement!=null) {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * 关闭connection，preparedStatement，resultSet的连接、
     * 关闭连接的重载方法其一，看参数就知道了，把这三个连接放进去就给你关了（哼）
     * @param connection
     * @param preparedStatement
     * @param resultSet
     */
    public static void closeResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
        if (connection!=null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (preparedStatement!=null) {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (resultSet!=null) {
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * 关于oracle数据库的增删改操作，集成了连接查询关闭操作，并抛出异常（集成了JDBC连接和关闭操作，不要傻乎乎的去调用上面的连接关闭操作啦）
     * true代表执行成功
     * 关于这两个参数，这里用的是preparedstatement
     * 前面的SQL语句是String类型的，需要参数的地方加上占位符就ok，arg就是填充的参数
     * 不会？那就看下面更新数据的例子吧
     * update("INSERT INTO fiction(`fictionName`,`author`,`pages`,`createTime`,`descriptions`,`isMember`,`isputaway`,`putawayTime`,`fictionimg`)
     * VALUES(?,?,?,?,?,?,?,?,?)","西游记","吴承恩",852,"1666-8-6","四大名著","Y","Y","1858-6-9",null);
     *
     * 就像这样，这个是增删改都可以的，目前好像只能单表操作，多表的涉及到自增的还没尝试，有问题及时修改，蟹蟹
     * @param sql
     * @param args
     */
    public static boolean update(String sql,Object ...args) {
        Connection oracleConnecton = null;
        PreparedStatement preparedStatement = null;
        try {
            oracleConnecton = JDBCutils.getConnecton();
            preparedStatement = oracleConnecton.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCutils.closeResources(oracleConnecton,preparedStatement);
        }
        return false;
    }


    /**
     * 泛型方法，通用的查询操作（集成了JDBC连接和关闭操作，不要傻乎乎的去调用上面的连接关闭操作啦）
     * 这个就用起来比较麻烦了，这个必须在java里面创建一个model，就是对应数据库里面的一张表例如student表对应的Student类
     * 说一下参数吧：
     * 第一个：clazz你要传入一个运行时类的对象，比如你的Student类就传入Student.class，就是用反射的方法获取类内部的结构，
     * 然后用结果集获取你SQl语句中的别名，并且修改，改完之后把new出来的对象直接加到list里面去
     * ！！！注意：起的别名要和你创建类（如student类的）的属性名要一样！！！
     * ！！！注意：起的别名要和你创建类（如student类的）的属性名要一样！！！
     * ！！！注意：起的别名要和你创建类（如student类的）的属性名要一样！！！包括大小写啊
     * 第二第三个参数和上面增删改的操作一样，实在不会就给你复制一遍吧：
     *      * 关于这两个参数，这里用的是preparedstatement
     *      * 前面的SQL语句是String类型的，需要参数的地方加上占位符就ok，arg就是填充的参数
     *      * 不会？那就看下面更新数据的例子吧
     *      * update("INSERT INTO fiction(`fictionName`,`author`,`pages`,`createTime`,`descriptions`,`isMember`,`isputaway`,`putawayTime`,`fictionimg`)
     *      * VALUES(?,?,?,?,?,?,?,?,?)","西游记","吴承恩",852,"1666-8-6","四大名著","Y","Y","1858-6-9",null);
     *
     * 目前也只测试了单表的查询，多表以及复杂的还没涉及到，如果有BUG及时修改，蟹蟹！！！
     *
     * @param clazz 提供需要查询表的类
     * @param sql sql语句
     * @param args 填充占位符的参数
     * @param <T> 具体类
     * @return
     */
    public static  <T> List<T> getInstance(Class<T> clazz, String sql, Object ...args){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCutils.getConnecton();
            preparedStatement = connection.prepareStatement(sql);
            //填充占位符
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            //获取结果集列数，封装在元数据中
            ResultSetMetaData metaData = resultSet.getMetaData();
            //通过resultSetMetaData获取结果集列数
            ArrayList<T> arr = new ArrayList<>();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()){
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    //获取列的值
                    Object value = resultSet.getObject(i + 1);

                    //获取列名
                    String columnName = metaData.getColumnLabel(i + 1);
//                    System.out.println(columnName);

                    //给dept1指定的某个属性=列名赋值为value(不知道属性，利用反射)
//                    Class<dept1> dept1Class = dept1.class;//获取运行时类的对象
                    Field field = clazz.getDeclaredField(columnName);//加载运行时类的属性
                    field.setAccessible(true);//保证属性可访问
                    field.set(t,value);
                }
                arr.add(t);

            }
            return  arr;
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            JDBCutils.closeResources(connection,preparedStatement,resultSet);
        }
        return null;
    }
}
