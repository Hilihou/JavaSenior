package com.novel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OracleConnect {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.77.100:1521:HELOWIN", "scott", "123456");
//        System.out.println(connection);
            //插入
            String sql="insert into dept1(deptno,dename,deloca) values (?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1,"118");
            ps.setString(2,"lucy");
            ps.setString(3,"xian");
            System.out.println(ps.execute());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (ps!=null) {
                try {
                    ps.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (connection!=null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
