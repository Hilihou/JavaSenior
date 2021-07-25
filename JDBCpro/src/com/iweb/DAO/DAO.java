package com.iweb.DAO;

import com.iweb.util.JDBCutils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 封装了针对数据表的通用操作
 */
public class DAO {

    /**
     * 通用增删改
     * @param connection
     * @param sql
     * @param args
     * @return
     */
    public boolean update(Connection connection,String sql,Object ...args) {
//        Connection oracleConnecton = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCutils.closeResources(null,preparedStatement);
        }
        return false;
    }

    /**
     * 通用查询操作
     * @param connection
     * @param clazz
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    public  <T> List<T> getInstance(Connection connection,Class<T> clazz, String sql, Object ...args){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
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
                    field.set(t,value);
                }
                arr.add(t);

            }
            return  arr;
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            JDBCutils.closeResources(null,preparedStatement,resultSet);
        }
        return null;
    }


    /**
     * 针对查询一些特殊值的方法，对于聚集函数<事务的概念>
     * @param connection
     * @param sql
     * @param args
     * @param <E>
     * @return
     * @throws Exception
     */
    public <E> E getValue(Connection connection,String sql,Object...args) throws Exception{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
              return (E) resultSet.getObject(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCutils.closeResources(null,preparedStatement,resultSet);
        }
        return null;
    }



}
