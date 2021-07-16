package com.iweb.model;

import java.util.Arrays;

/*小说表fiction
fictionId
fictionName
author
pages
createTime
descriptions
isMember（是否会员书籍）
isputaway(是否上架)
putawayTime（上架时间）
fictionimg(小说的封面图片)
*/
public class Fiction {

private long fictionId;
private String fictionName;
private String author;
private long pages;
private java.sql.Timestamp createTime;
private  String descriptions;
private String isMember;
private String isputaway;
private java.sql.Timestamp putawayTime;
private byte[] fictionimg;

    public Fiction() {
    }

    public Fiction(long fictionId, String fictionName, String author, long pages, java.sql.Timestamp createTime, String descriptions, String isMember, String isputaway, java.sql.Timestamp putawayTime, byte[] fictionimg) {
        this.fictionId = fictionId;
        this.fictionName = fictionName;
        this.author = author;
        this.pages = pages;
        this.createTime = createTime;
        this.descriptions = descriptions;
        this.isMember = isMember;
        this.isputaway = isputaway;
        this.putawayTime = putawayTime;
        this.fictionimg = fictionimg;
    }

    @Override
    public String toString() {
        return "fiction{" +
                "小说id=" + fictionId +
                ", 小说名称='" + fictionName + '\'' +
                ", 小说作者='" + author + '\'' +
                ", 小说页数=" + pages +
                ", 创建时间=" + createTime +
                ", 小说描述='" + descriptions + '\'' +
                ", 是否会员='" + isMember + '\'' +
                ", 是否上架='" + isputaway + '\'' +
                ", 上架时间=" + putawayTime +
                ", 小说封面=" + Arrays.toString(fictionimg) +
                '}';
    }



    public void setFictionName(String fictionName) {
        this.fictionName = fictionName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

    public void setCreateTime(java.sql.Timestamp createTime) {
        this.createTime = createTime;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public void setIsMember(String isMember) {
        this.isMember = isMember;
    }

    public void setIsputaway(String isputaway) {
        this.isputaway = isputaway;
    }

    public void setPutawayTime(java.sql.Timestamp putawayTime) {
        this.putawayTime = putawayTime;
    }

    public void setFictionimg(byte[] fictionimg) {
        this.fictionimg = fictionimg;
    }

    public long getFictionId() {
        return fictionId;
    }

    public String getFictionName() {
        return fictionName;
    }

    public String getAuthor() {
        return author;
    }

    public long getPages() {
        return pages;
    }

    public java.sql.Timestamp getCreateTime() {
        return createTime;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public String getIsMember() {
        return isMember;
    }

    public String getIsputaway() {
        return isputaway;
    }

    public java.sql.Timestamp getPutawayTime() {
        return putawayTime;
    }

    public byte[] getFictionimg() {
        return fictionimg;
    }
}
