package com.iweb.DAO;

import com.iweb.utils.JDBCutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class C_fictionDAOimpl implements C_fictionDAO{
    private C_fictionDAOimpl(){}
    private static C_fictionDAOimpl cfic=new C_fictionDAOimpl();
    public static C_fictionDAOimpl getInstance(){
        return cfic;
    }
    @Override
    public Map showC_fiction() {
        Connection connecton = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String sql="select fiction.fictionName,category.categoryId from c_fiction,fiction,category where fiction.fictionId=c_fiction.fictionId and category.categoryId=c_fiction.categoryId";
            connecton = JDBCutils.getConnecton();
            preparedStatement = connecton.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            HashMap<Object, Object> hashMap = new HashMap<>();
            while (resultSet.next()){
                Object fictionName = resultSet.getObject(1);
                Object categoryId = resultSet.getObject(2);
                hashMap.put(categoryId,fictionName);
            }
            return hashMap;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCutils.closeResources(connecton,preparedStatement,resultSet);
        }
        return null;
    }
}
