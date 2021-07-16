package ch1;

import org.junit.Test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Myconnection {
    @Test
    public void test1() throws SQLException {
        Driver driver=new com.mysql.jdbc.Driver();
        String url="jdbc:mysql://localhost:3306/test";
        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","123456");
        Connection connect = driver.connect(url, info);
        System.out.println(connect);
    }

    @Test
    public void test2() throws Exception {
        //1.获取Driver的实现类对象
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver= (Driver) clazz.getConstructor().newInstance();

        //2.提供连接的数据库
        String url;
        url = "jdbc:mysql://localhost:3306/test";
        //3.用户名和密码
        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","123456");
        //4.获取连接
        Connection connect = driver.connect(url, properties);
        System.out.println(connect);
    }

    @Test
    public void test3() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        //获取实现类的对象
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver= (Driver) clazz.newInstance();

        //基本信息
        String url="jdbc:mysql://localhost:3306/test";
        String user="root";
        String pwd="123456";

        //注册驱动
        DriverManager.registerDriver(driver);

        //获取连接
        Connection connection = DriverManager.getConnection(url, user, pwd);
        System.out.println(connection);


    }

}
