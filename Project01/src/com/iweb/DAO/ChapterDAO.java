package com.iweb.DAO;

import com.iweb.model.Chapter;

import java.util.List;

public interface ChapterDAO {
//1.查询全部章节
public List showAllChapter();
//2.根据编号查看章节
public Chapter showChapterById(long id);
//3.新增章节
public boolean addChapter(String chapterName,String chapterPath,long fictionId);
//4.修改章节
public boolean alterChapter(long chapterId,String chapterName,String chapterPath);
//5.删除章节
public boolean deleteChapter(long chapterId);
}
