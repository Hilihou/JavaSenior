package com.novel;

import com.util.JDBCutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NomalTest {
    public static void main(String[] args) {
//        update("update dept1 set dename=? where deptno=?","jj","114");
        boolean update = update("delete from dept1 where deptno=?", "112");
        System.out.println(update);

    }

    //通用的增删改操作
    //占位符的个数和可变形参的个数要一致
    public static boolean update(String sql,Object ...args){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //获取连接
            conn = JDBCutil.getConnection();
            //预编译sql语句
            ps = conn.prepareStatement(sql);
            //填充字符
            for (int i=0;i<args.length;i++) {
                ps.setObject(i+1,args[i]);
            }
            ps.execute();
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //关闭连接
            try {
                JDBCutil.closeResorse(conn,ps);
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }
}
