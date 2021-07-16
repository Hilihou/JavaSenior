package com.iweb.DAO;

import com.iweb.model.Category;

import java.util.List;

public interface CategoryDAO {
//1.查询全部类别
    public List showAllCategory();
//2.根据编号查看类别
    public Category showCategoryById(long id);
//3.新增类别
    public boolean addCategory(String categoryName,String desc);
//4.修改类别
    public boolean alterCategory(long categoryId,String NewcategoryName,String NewDesc);
//5.删除类别
    public boolean deleteCategory(long categoryId);

}
