package com.test.connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class connection {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.注册驱动
        Class.forName("oracle.jdbc.OracleDriver");//抛出异常到方法名

        //2.获取连接//   "jdbc:oracle:thin:@192.168.77.100:1521:HELOWIN ","scott","123456"
        java.sql.Connection connection=java.sql.DriverManager.getConnection
                ("jdbc:oracle:thin:@192.168.77.100:1521:helowin ","scott","123456");
        //3.获取对象
        Statement statement=connection.createStatement();

        //4.编写pl/sql语句
        String sql="select * from emp";

        //
        ResultSet resultSet=statement.executeQuery(sql);
        //5.验证 是否获取到数值
        while(resultSet.next()){
            String nameString =resultSet.getString(3);
            System.out.println(nameString);
        }
        //6.关闭连接
        resultSet.close();
        statement.close();
        connection.close();


    }
}
