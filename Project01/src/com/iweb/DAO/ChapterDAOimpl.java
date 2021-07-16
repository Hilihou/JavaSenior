package com.iweb.DAO;

import com.iweb.model.Chapter;
import com.iweb.utils.JDBCutils;

import java.util.List;

public class ChapterDAOimpl implements ChapterDAO{
    private ChapterDAOimpl() {}
    private static ChapterDAOimpl chp=new ChapterDAOimpl();
    public static ChapterDAOimpl getInstance(){
        return  chp;
    }

    @Override
    public List showAllChapter() {
        String sql="select * from chapter";
        List<Chapter> instance = JDBCutils.getInstance(Chapter.class, sql);
        return instance;
    }

    @Override
    public Chapter showChapterById(long id) {
        String sql="select * from chapter where chapterId=?";
        List<Chapter> instance = JDBCutils.getInstance(Chapter.class, sql, id);
        return instance.get(0);
    }

    @Override
    public boolean addChapter(String chapterName, String chapterPath, long fictionId) {
        boolean update = JDBCutils.update("insert into chapter(`chapterName`,`chapterPath`,`fictionId`) values(?,?,?)", chapterName, chapterPath, fictionId);
        return update;
    }

    @Override
    public boolean alterChapter(long chapterId, String chapterName, String chapterPath) {
        boolean update = JDBCutils.update("update chapter set `chapterName`=?,`chapterPath`=? where chapterId=?", chapterName, chapterPath, chapterId);
        return update;
    }

    @Override
    public boolean deleteChapter(long chapterId) {
        boolean update = JDBCutils.update("delete from chapter where chapterId=?", chapterId);
        return update;
    }
}
