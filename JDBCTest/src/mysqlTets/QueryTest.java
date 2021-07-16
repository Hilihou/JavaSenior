package mysqlTets;

import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class QueryTest {
    @Test
    public void simplequery(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = MysqlJDBCutil.getConnection();
            String sql="SELECT *  FROM departments d";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Object object = resultSet.getObject(2);
                System.out.println(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MysqlJDBCutil.closeResorse(connection,preparedStatement);
            MysqlJDBCutil.closeResultSet(resultSet);
        }
    }


    public static void main(String[] args) {
        newquery("select sno SNO,sname,sage from test where sno=?",2);
    }

    public static void newquery(String sql,Object ...args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = MysqlJDBCutil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()){
                test t1=new test();
                for (int i = 0; i < columnCount; i++) {
                    //获取列的值
                    Object value = resultSet.getObject(i + 1);

                    //获取列名
    //                String columnName = metaData.getColumnName(i + 1);
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    //反射为属性赋值,反射的类必须是表的对应类
                    Class<test> queryTestClass = test.class;
                    Field declaredField = queryTestClass.getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    declaredField.set(t1,value);
                }
                System.out.println(t1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MysqlJDBCutil.closeResorse(connection,preparedStatement);
            MysqlJDBCutil.closeResultSet(resultSet);
        }


    }
}
