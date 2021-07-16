package com.iweb.model;
/*
小说章节chapter
chapterId
chapterName
chapterPath(存放路径)
fictionId
*/
public class Chapter {
   private long chapterId;
   private String chapterName;
   private String chapterPath;
   private long  fictionId;

    public Chapter() {
    }

    public Chapter(long chapterId, String chapterName, String chapterPath, long fictionId) {
        this.chapterId = chapterId;
        this.chapterName = chapterName;
        this.chapterPath = chapterPath;
        this.fictionId = fictionId;
    }

    @Override
    public String toString() {
        return "chapter{" +
                "chapterId=" + chapterId +
                ", chapterName='" + chapterName + '\'' +
                ", chapterPath='" + chapterPath + '\'' +
                ", fictionId=" + fictionId +
                '}';
    }



    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public void setChapterPath(String chapterPath) {
        this.chapterPath = chapterPath;
    }



    public long getChapterId() {
        return chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public String getChapterPath() {
        return chapterPath;
    }

    public long getFictionId() {
        return fictionId;
    }
}
