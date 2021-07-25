package ch1;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Updatetest {
    public static void main(String[] args) throws Exception {
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("JDBC.properties");
        Properties properties = new Properties();
        properties.load(is);
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("driverClass");

        Class.forName(driverClass);
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);

        String sql="insert into customers(name,email,birth) values (?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,"哪吒");
        preparedStatement.setString(2,"nezha@email.com");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = simpleDateFormat.parse("1002-03-04");
        preparedStatement.setDate(3,new java.sql.Date(parse.getTime()));
        boolean execute = preparedStatement.execute();
        System.out.println(execute);

        preparedStatement.close();
        connection.close();

    }
}
