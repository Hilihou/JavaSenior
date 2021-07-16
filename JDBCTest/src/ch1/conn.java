package ch1;

import org.junit.Test;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class conn {
    @Test
    public void test1() throws SQLException {
        Driver driver=new com.mysql.jdbc.Driver();//第三方api
        String url="jdbc:mysql://localhost:3306/test";
        Properties info=new Properties();
        info.setProperty("user","root");
        info.setProperty("password","123456");
        Connection connect = driver.connect(url, info);
        System.out.println(connect);
    }
    @Test
    public void test2() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        //反射操作，没有用第三方api
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();
        String url="jdbc:mysql://localhost:3306/test";
        Properties info=new Properties();
        info.setProperty("user","root");
        info.setProperty("password","123456");
        Connection connect = driver.connect(url, info);
        System.out.println(connect);

    }
    @Test
    public void test3() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();

        String url="jdbc:mysql://localhost:3306/test";
        String user="root";
        String password="123456";
        DriverManager.registerDriver(driver);

        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);

    }

    @Test
    public void test4() throws Exception{
        String url="jdbc:mysql://localhost:3306/test";
        String user="root";
        String password="123456";
        Class.forName("com.mysql.jdbc.Driver");
//在MySQL的实现类中静态代码块加载了驱动
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }

    @Test
    public void test4_1() throws Exception {
        Class.forName("oracle.jdbc.OracleDriver");
        java.sql.Connection connection=java.sql.DriverManager.getConnection
                ("jdbc:oracle:thin:@192.168.77.100:1521:helowin ","scott","123456");

        System.out.println(connection);

    }


    @Test
    public void test5() throws Exception{
        //实现了数据和代码的分离
        //修改配置文件信息不需要重新打包
        InputStream is = conn.class.getClassLoader().getResourceAsStream("JDBC.properties");
        Properties properties = new Properties();
        properties.load(is);
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("driverClass");
            Class.forName(driverClass);
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);

    }
}
