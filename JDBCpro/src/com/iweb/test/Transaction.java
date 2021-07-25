package com.iweb.test;

import com.iweb.util.JDBCutils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transaction {
    @Test
    public void test1() {
        Connection connecton = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        try {
            connecton = JDBCutils.getConnecton();
            connecton.getTransactionIsolation();
            connecton.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connecton.setAutoCommit(false);
            String sql1 = "update user_table set balance=balance-100 where `user` ='AA'";
            System.out.println(10/0);
            String sql2 =  "update user_table set balance=balance+100 where `user` ='BB'";
            preparedStatement = connecton.prepareStatement(sql1);
            preparedStatement.execute();
            preparedStatement1 = connecton.prepareStatement(sql2);
            preparedStatement1.execute();
            connecton.commit();
            System.out.println("转账成功");
        } catch (Exception e) {
            System.out.println("转账失败");
//            e.printStackTrace();
            try {
                connecton.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            if (preparedStatement!=null) {
                try {
                    preparedStatement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (preparedStatement1!=null) {
                try {
                    preparedStatement1.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (connecton!=null) {
                try {
                    connecton.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    /**
     * 设置隔离级别
     */
    @Test
    public void test2(){

    }
}
