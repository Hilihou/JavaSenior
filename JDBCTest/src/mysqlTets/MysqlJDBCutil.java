package mysqlTets;

import com.util.JDBCutil;
import org.junit.Test;

import java.sql.*;

public class MysqlJDBCutil {
    @Test
    public void test() throws Exception {

    }

    public static Connection getConnection() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection root = DriverManager.getConnection("jdbc:mysql://localhost:3306/myemployees", "root", "123456");
        return root;
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
     *通用的增删改操作
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
            conn = MysqlJDBCutil.getConnection();
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
        } catch (Exception e) {
            e.printStackTrace();
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
