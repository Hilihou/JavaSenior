package com.iweb.test;
import com.iweb.utils.JDBCutils;
import org.testng.annotations.Test;
import java.sql.Connection;
import java.sql.SQLException;


/**
 * 测试类，随便造
 */
public class ConnectionTest {

    /**
     * 测试数据库连接
     */
    @Test
    public void conn() throws Exception {
        Connection connecton = JDBCutils.getConnecton();
        System.out.println(connecton);
    }
}
