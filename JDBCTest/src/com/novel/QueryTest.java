package com.novel;

import com.util.JDBCutil;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.LinkedList;

public class QueryTest {
    @Test
    public void test1(){

        System.out.println(queryForDept1("select * from dept1 "));

    }

    public static LinkedList queryForDept1(String sql,Object ...args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCutil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
//            System.out.println(args.length);
            //填充占位符
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            //获取结果集列数，封装在元数据中
            ResultSetMetaData metaData = resultSet.getMetaData();
            //通过resultSetMetaData获取结果集列数
        LinkedList<dept1> dept1LinkedList = new LinkedList<>();
            int columnCount = metaData.getColumnCount();

//            System.out.println(columnCount);
            while (resultSet.next()){
                dept1 d1=new dept1();
                for (int i = 0; i < columnCount; i++) {
                    //获取列的值
                    Object value = resultSet.getObject(i + 1);

                    //获取列名
                    String columnName = metaData.getColumnLabel(i + 1);
//                    System.out.println(columnName);

                    //给dept1指定的某个属性=列名赋值为value(不知道属性，利用反射)
                    Class<dept1> dept1Class = dept1.class;//获取运行时类的对象
                    Field declaredField = dept1Class.getDeclaredField(columnName);//加载运行时类的属性
                    declaredField.setAccessible(true);//保证属性可访问
                    declaredField.set(d1,value.toString());
                }
                dept1LinkedList.add(d1);

            }
           return dept1LinkedList;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            JDBCutil.closeResorse(connection,preparedStatement);
            JDBCutil.closeResultSet(resultSet);
        }
        return null;
    }
    public static void main(String[] args) {
        Connection connection = null;
        ResultSet resultSet=null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCutil.getConnection();
            String sql="select * from dept1 where deptno=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,104);

            //执行返回结果集
            resultSet = preparedStatement.executeQuery();

            //next():返回值Boolean，1.判断下个位置是否有元素 2.指针下移
            //如果有元素，返回true并指针下移；没有元素，返回false，就直接结束
            while (resultSet.next()){
                String no= resultSet.getString(1);
                String name=  resultSet.getString(2);
                String loca= resultSet.getString(3);
                //方式1
//                System.out.println(no+"+"+name+"+"+loca);
                //方式2
//                Object[] data=new Object[]{no,name,loca};
                //方式3:将数据封装成一个对象（推荐）
                System.out.println(new dept1(no, name, loca));
//                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Result.txt"));
//                objectOutputStream.write(new String("hello"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCutil.closeResorse(connection,preparedStatement);
                JDBCutil.closeResultSet(resultSet);
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
        }

    }
}
