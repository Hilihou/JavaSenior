package ch1;

import com.util.JDBCutil;
import org.junit.Test;

import java.sql.*;

public class ManyInsert {
    @Test
    public void test() throws  Exception{
        String url="jdbc:mysql://localhost:3306/test?rewriteBatchedStatements=true";
        String user="root";
        String password="123456";
        //主要代码
        Class.forName("com.mysql.jdbc.Driver");
//在MySQL的实现类中静态代码块加载了驱动 DriverManager.registerDriver(driver);
        Connection connection = DriverManager.getConnection(url, user, password);

//        System.out.println(connection);
//        Statement statement = connection.createStatement();
//        long l = System.currentTimeMillis();
//        for (int i = 0; i <20000; i++) {
//            String sql="insert into stu(sname) values ('name_"+i+"')";
//                statement.execute(sql);
//        }
//        long l1=System.currentTimeMillis()-l;
//        System.out.println(l1*1.0/1000+"秒");
//        JDBCutil.closeResorse(connection,null);
        //14秒
        //--------------------------------------------------------------
//                long l = System.currentTimeMillis();
//        String sql="insert into stu(sname) values (?)";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        for (int i = 0; i < 20000; i++) {
//            preparedStatement.setObject(1,"name_"+i);
//            preparedStatement.execute();
//        }
//                long l1=System.currentTimeMillis()-l;
//        System.out.println(l1*1.0/1000+"秒");
//        JDBCutil.closeResorse(connection,preparedStatement);
//        //14秒
//--------------------------------------------------------------------
//                        long l = System.currentTimeMillis();
//        String sql="insert into stu(sname) values (?)";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        for (int i = 0; i < 20000; i++) {
//            preparedStatement.setObject(1,"name_"+i);
//            preparedStatement.addBatch();
//            if (i%500==0){
//                preparedStatement.executeBatch();
//                preparedStatement.clearBatch();
//            }
////            preparedStatement.execute();
//        }
//                long l1=System.currentTimeMillis()-l;
//        System.out.println(l1*1.0/1000+"秒");
//        JDBCutil.closeResorse(connection,preparedStatement);
//0.167秒
        //-------------------------------------------
        long l = System.currentTimeMillis();
        String sql="insert into stu(sname) values (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        connection.setAutoCommit(false);
        for (int i = 0; i < 1000000; i++) {
            preparedStatement.setObject(1,"name_"+i);
            preparedStatement.addBatch();
            if (i%500==0){
                preparedStatement.executeBatch();
                preparedStatement.clearBatch();
            }
//            preparedStatement.execute();
        }
        connection.commit();
        long l1=System.currentTimeMillis()-l;
        System.out.println(l1*1.0/1000+"秒");
        JDBCutil.closeResorse(connection,preparedStatement);


    }
}
