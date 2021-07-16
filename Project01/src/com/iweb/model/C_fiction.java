package com.iweb.model;
/*
小说类别关系表c_fiction
categoryId
fictionId
*/

public class C_fiction {
    private long categoryId;
    private long fictionId;

    public long getCategoryId() {
        return categoryId;
    }

    public long getFictionId() {
        return fictionId;
    }

    @Override
    public String toString() {
        return "c_fiction{" +
                "categoryId=" + categoryId +
                ", fictionId=" + fictionId +
                '}';
    }
}
