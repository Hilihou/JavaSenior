package com.iweb.utils;

import org.junit.Test;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JDBCutils {
    @Test
    public void test() throws Exception {
//        System.out.println(update("delete from dept1 where deptno=?", 112));
//        Connection connecton = getConnecton();
//        System.out.println(connecton);
//        update("INSERT INTO fiction(`fictionName`,`author`,`pages`,`createTime`,`descriptions`,`isMember`,`isputaway`,`putawayTime`,`fictionimg`) VALUES(?,?,?,?,?,?,?,?,?)","西游记","吴承恩",852,"1666-8-6","四大名著","Y","Y","1858-6-9",null);
    }

    /**
     * 获取连接
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
     * 关闭connection，preparedStatement，resultSet的连接
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
     * 关于oracle数据库的增删改操作，集成了连接查询关闭操作，并抛出异常
     * true代表执行成功
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
     * 泛型方法，通用的查询
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
