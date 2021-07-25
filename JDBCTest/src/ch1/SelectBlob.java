package ch1;

import com.util.JDBCutil;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class SelectBlob {
    @Test
    public void test(){
        InputStream is= null;
        FileOutputStream outputStream = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            is = null;
            outputStream = null;
            String url="jdbc:mysql://localhost:3306/test";
            String user="root";
            String password="123456";
            //主要代码
            Class.forName("com.mysql.jdbc.Driver");
//在MySQL的实现类中静态代码块加载了驱动 DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(url, user, password);

//        System.out.println(connection);
            String sql="select photo from customers where id=20";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                //将blob下载下来保存到本地
                Blob blob = resultSet.getBlob(1);
                 is = blob.getBinaryStream();
                 outputStream = new FileOutputStream("gg.jpg");
                byte[] bytes = new byte[1024];
                int len;
                while ( (len=is.read(bytes))!=-1 )
                {
                    outputStream.write(bytes,0,len);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
        JDBCutil.closeResorse(connection,preparedStatement);
        JDBCutil.closeResultSet(resultSet);
        if (is!=null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (outputStream!=null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }
}
