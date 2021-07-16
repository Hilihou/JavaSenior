package com.iweb.DAO;

import com.iweb.model.Fiction;
import com.iweb.utils.JDBCutils;

import java.util.List;

public class FictionDAOimpl implements FictionDAO{
    private FictionDAOimpl() { }
    private static FictionDAOimpl fic=new FictionDAOimpl();
    public static FictionDAOimpl getInstance(){
        return fic;
    }

    @Override
    public List showAllfiction() {
        String sql="select * from fiction";
        List<Fiction> instance = JDBCutils.getInstance(Fiction.class, sql);
        return instance;
    }

    @Override
    public Fiction showfiction(long id) {
        String sql="select * from fiction where fictionId=?";
        List<Fiction> instance = JDBCutils.getInstance(Fiction.class, sql, id);
        return instance.get(0);
    }

    @Override
    public boolean addFiction(String fictionName, String author, long pages, String createTime,
                              String descriptions, String isMember, String isputaway, String putawayTime) {
        boolean update = JDBCutils.update("INSERT INTO fiction(`fictionName`,`author`,`pages`,`createTime`," +
                        "`descriptions`,`isMember`,`isputaway`,`putawayTime`,`fictionimg`) VALUES(?,?,?,?,?,?,?,?,?)",
                fictionName, author, pages, createTime, descriptions, isMember, isputaway, putawayTime, null);
        return update;
    }

    @Override
    public boolean alterFiction(long id, String fictionName, String author, long pages, String descriptions, String isMember, String isputaway) {
        boolean update = JDBCutils.update("UPDATE fiction SET `fictionName`=?,`author`=?,`pages`=?,`descriptions`=?,`isMember`=?,`isputaway`=? WHERE `fictionId`=?", fictionName, author, pages, descriptions, isMember, isputaway,id);
        return update;
    }

    @Override
    public boolean deleteFiction(long id) {
        boolean update = JDBCutils.update("DELETE FROM fiction WHERE fictionId=?", id);
        return update;
    }



//    /**
//     * 泛型方法，通用的查询
//     * @param clazz 提供需要查询表的类
//     * @param sql sql语句
//     * @param args 填充占位符的参数
//     * @param <T> 具体类
//     * @return
//     */
//    public static  <T> List<T> getInstance(Class<T> clazz, String sql, Object ...args){
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        try {
//            connection = JDBCutils.getConnecton();
//            preparedStatement = connection.prepareStatement(sql);
//            //填充占位符
//            for (int i = 0; i < args.length; i++) {
//                preparedStatement.setObject(i+1,args[i]);
//            }
//            resultSet = preparedStatement.executeQuery();
//            //获取结果集列数，封装在元数据中
//            ResultSetMetaData metaData = resultSet.getMetaData();
//            //通过resultSetMetaData获取结果集列数
//            ArrayList<T> arr = new ArrayList<>();
//            int columnCount = metaData.getColumnCount();
//            while (resultSet.next()){
//                T t = clazz.newInstance();
//                for (int i = 0; i < columnCount; i++) {
//                    //获取列的值
//                    Object value = resultSet.getObject(i + 1);
//
//                    //获取列名
//                    String columnName = metaData.getColumnLabel(i + 1);
////                    System.out.println(columnName);
//
//                    //给dept1指定的某个属性=列名赋值为value(不知道属性，利用反射)
////                    Class<dept1> dept1Class = dept1.class;//获取运行时类的对象
//                    Field field = clazz.getDeclaredField(columnName);//加载运行时类的属性
//                    field.setAccessible(true);//保证属性可访问
//                    field.set(t,value.toString());
//                }
//                arr.add(t);
//
//            }
//            return  arr;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }  finally {
//            JDBCutils.closeResources(connection,preparedStatement,resultSet);
//        }
//        return null;
//    }
}
