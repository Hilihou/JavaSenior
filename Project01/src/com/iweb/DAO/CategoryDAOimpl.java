package com.iweb.DAO;

import com.iweb.model.Category;
import com.iweb.utils.JDBCutils;

import java.util.List;

public class CategoryDAOimpl implements CategoryDAO{
    private CategoryDAOimpl() {}
    private static CategoryDAOimpl cate=new CategoryDAOimpl();
    public static CategoryDAOimpl getInstance(){
        return cate;
    }

    @Override
    public List showAllCategory() {
        String sql="select * from category";
        List<Category> instance = JDBCutils.getInstance(Category.class, sql);
        return instance;
    }

    @Override
    public Category showCategoryById(long id) {
        String sql="select * from category where categoryId=?";
        List<Category> instance = JDBCutils.getInstance(Category.class, sql, id);
        return instance.get(0);
    }

    @Override
    public boolean addCategory(String categoryName, String desc) {
        boolean update = JDBCutils.update("insert into category(`categoryName`,`createTime`,`desc`) values(?,NOW(),?)", categoryName, desc);
        return update;
    }

    @Override
    public boolean alterCategory(long categoryId, String NewcategoryName, String NewDesc) {
        boolean update = JDBCutils.update("update category set `categoryName`=? ,set `desc`=? where categoryId=?", NewcategoryName, NewDesc, categoryId);
        return update;
    }

    @Override
    public boolean deleteCategory(long categoryId) {
        boolean update = JDBCutils.update("delete from category where categoryId=?", categoryId);
        return update;
    }
}
