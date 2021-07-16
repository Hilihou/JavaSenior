package com.util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 操作数据库的工具类
 */
public class JDBCutil {
    /**
     * 获取连接
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.OracleDriver");
        java.sql.Connection connection=java.sql.DriverManager.getConnection
                ("jdbc:oracle:thin:@192.168.77.100:1521:helowin ","scott","123456");
        return connection;
    }

    /**
     * 关闭资源连接和preparedStatement
     * @param connection
     * @param preparedStatement
     * @throws SQLException
     */
    public static void closeResorse(Connection connection, PreparedStatement preparedStatement)  {
        if (connection!=null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (preparedStatement!=null) {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * oracle通用的增删改操作
     *  占位符的个数和可变形参的个数要一致
     * @param sql
     * @param args
     * @return
     */
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

    /**
     * 关闭结果集
     * @param resultSet
     */
    public static void closeResultSet(ResultSet resultSet){
        if (resultSet!=null) {
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
