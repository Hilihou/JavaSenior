package com.novel;

import com.util.JDBCutil;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public  class FinalSelect {
    @Test
    public void test(){
        System.out.println(getInstance(dept1.class, "select * from dept1"));
    }
    /**
     * 泛型方法，通用的查询
     * @param clazz 提供需要查询表的类
     * @param sql sql语句
     * @param args 填充占位符的参数
     * @param <T> 具体类
     * @return
     */
    public static  <T> List<T> getInstance(Class<T> clazz, String sql, Object ...args){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCutil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            //填充占位符
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            //获取结果集列数，封装在元数据中
            ResultSetMetaData metaData = resultSet.getMetaData();
            //通过resultSetMetaData获取结果集列数
            ArrayList<T> arr = new ArrayList<>();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()){
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    //获取列的值
                    Object value = resultSet.getObject(i + 1);

                    //获取列名
                    String columnName = metaData.getColumnLabel(i + 1);
//                    System.out.println(columnName);

                    //给dept1指定的某个属性=列名赋值为value(不知道属性，利用反射)
//                    Class<dept1> dept1Class = dept1.class;//获取运行时类的对象
                    Field field = clazz.getDeclaredField(columnName);//加载运行时类的属性
                    field.setAccessible(true);//保证属性可访问
                    field.set(t,value.toString());
                }
                arr.add(t);

            }
            return  arr;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } finally {
            JDBCutil.closeResorse(connection,preparedStatement);
            JDBCutil.closeResultSet(resultSet);
        }
        return null;
    }
}
