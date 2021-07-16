package com.iweb.model;

/*
小说类别表category
categoryId
categoryName
createTime
desc
*/
public class Category {
    private long categoryId;
    private String categoryName;
    private java.sql.Timestamp createTime;
    private String desc;

    public Category() {
    }

    @Override
    public String toString() {
        return "category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", createTime=" + createTime +
                ", desc='" + desc + '\'' +
                '}';
    }



    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCreateTime(java.sql.Timestamp createTime) {
        this.createTime = createTime;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public java.sql.Timestamp getCreateTime() {
        return createTime;
    }

    public String getDesc() {
        return desc;
    }

    public Category(long categoryId, String categoryName, java.sql.Timestamp createTime, String desc) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.createTime = createTime;
        this.desc = desc;
    }
}
