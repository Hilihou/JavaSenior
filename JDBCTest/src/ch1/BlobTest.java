package ch1;

import com.util.JDBCutil;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BlobTest {

    @Test
    public void test1()  {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        FileInputStream is = null;
        try {
            String url="jdbc:mysql://localhost:3306/test";
            String user="root";
            String password="123456";
            //主要代码
            Class.forName("com.mysql.jdbc.Driver");
//在MySQL的实现类中静态代码块加载了驱动 DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(url, user, password);
//        System.out.println(connection);

            String sql="insert into customers(name,email,birth,photo) values(?,?,?,?)";

//        JDBCutil.update(sql,"战三","emabi@qnw.com","1953-01-01",)
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,"tom");
            preparedStatement.setObject(2,"yuasd@asd.com");
            preparedStatement.setObject(3,"1956-05-06");
            is = new FileInputStream(new File("C:\\Users\\Soul\\Pictures\\IDEA Background\\20200523200523_rsogw.jpg"));
            preparedStatement.setBlob(4,is);
            preparedStatement.execute();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            JDBCutil.closeResorse(connection,preparedStatement);
            if (is!=null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
