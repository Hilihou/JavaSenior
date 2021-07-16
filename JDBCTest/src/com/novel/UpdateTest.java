package com.novel;

import com.util.JDBCutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateTest {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //1.获取连接
            connection = JDBCutil.getConnection();
            //2.预编译sql语句，返回prepareStatement对象
            String sql="update dept1 set dename=? where deptno=?";
            ps = connection.prepareStatement(sql);
            //3.填充占位符
            ps.setString(1,"hello");
            ps.setString(2,"114");
            //4.执行
            ps.execute();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //5.关闭资源
            if (connection!=null&&ps!=null) {
                try {
                    JDBCutil.closeResorse(connection,ps);
                } catch (Exception throwables) {
                    throwables.printStackTrace();
                }
            }
        }


    }
}
