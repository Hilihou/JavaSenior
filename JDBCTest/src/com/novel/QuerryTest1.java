package com.novel;

import com.util.JDBCutil;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;

public class QuerryTest1 {
    @Test
    public void oldtest() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCutil.getConnection();
            String sql="select J_NAME JNAME from jdbctest where J_ID=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,1);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                JDBCTest jdbcTest = new JDBCTest();
                for (int i = 0; i < 1; i++) {
                    Object value = resultSet.getObject(i + 1);
                    System.out.println(value);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCutil.closeResorse(connection,preparedStatement);
            JDBCutil.closeResultSet(resultSet);
        }
    }
    @Test
    public void test1(){
        QueryJDBCTest("select j_id jid,j_name JNAME,j_date JDATE from jdbctest where j_id=?",1);
    }


    public static void QueryJDBCTest(String sql,Object ...args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //获取连接
            connection = JDBCutil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()){
                JDBCTest jb=new JDBCTest();
                for (int i = 0; i < columnCount; i++) {
                    //获取值
                    Object value = resultSet.getObject(i + 1);
                    //获取列名
                    String columnLabel = metaData.getColumnLabel(i + 1);

                    //反射获取类的对象，循环赋值属性
                    Class<JDBCTest> jdbcTestClass = JDBCTest.class;
                    Field declaredField = jdbcTestClass.getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    declaredField.set(jb,value);
                }
                System.out.println(jb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            JDBCutil.closeResorse(connection,preparedStatement);
            JDBCutil.closeResultSet(resultSet);
        }
    }
}
