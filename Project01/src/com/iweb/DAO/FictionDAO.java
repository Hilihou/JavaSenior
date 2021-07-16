package com.iweb.DAO;

import com.iweb.model.Fiction;

import java.util.List;

public interface FictionDAO {
//1.查询全部小说
    public List showAllfiction();
//2.根据编号查看一本小说
    public Fiction showfiction(long id);
//3.新增一本小说
    public boolean addFiction(String fictionName, String author, long pages, String createTime,String descriptions,String isMember,String isputaway,String putawayTime);
//4.修改一本小说
    public boolean alterFiction(long id,String fictionName, String author, long pages,String descriptions,String isMember,String isputaway);
//5.删除一本小说
    public boolean deleteFiction(long id);

}
