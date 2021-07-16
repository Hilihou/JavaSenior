package com.test.Tables;

public class novel {
    private String nid;
    private String nname;
    private String nauthor;
    private String pages;
    private String ncreatetime;
    private String ndesc;
    private String isvip;
    private String nstate;
    private String nondate;
    private String nkid;

    @Override
    public String toString() {
        return "novel{" +
                "nid='" + nid + '\'' +
                ", nname='" + nname + '\'' +
                ", nauthor='" + nauthor + '\'' +
                ", pages='" + pages + '\'' +
                ", ncreatetime='" + ncreatetime + '\'' +
                ", ndesc='" + ndesc + '\'' +
                ", isvip='" + isvip + '\'' +
                ", nstate='" + nstate + '\'' +
                ", nondate='" + nondate + '\'' +
                ", nkid='" + nkid + '\'' +
                '}';
    }

    public novel() {
    }

    public novel(String nid, String nname, String nauthor, String pages, String ncreatetime, String ndesc, String isvip, String nstate, String nondate, String nkid) {
        this.nid = nid;
        this.nname = nname;
        this.nauthor = nauthor;
        this.pages = pages;
        this.ncreatetime = ncreatetime;
        this.ndesc = ndesc;
        this.isvip = isvip;
        this.nstate = nstate;
        this.nondate = nondate;
        this.nkid = nkid;
    }


    public void setNid(String nid) {
        this.nid = nid;
    }

    public void setNname(String nname) {
        this.nname = nname;
    }

    public void setNauthor(String nauthor) {
        this.nauthor = nauthor;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public void setNcreatetime(String ncreatetime) {
        this.ncreatetime = ncreatetime;
    }

    public void setNdesc(String ndesc) {
        this.ndesc = ndesc;
    }

    public void setIsvip(String isvip) {
        this.isvip = isvip;
    }

    public void setNstate(String nstate) {
        this.nstate = nstate;
    }

    public void setNondate(String nondate) {
        this.nondate = nondate;
    }

    public void setNkid(String nkid) {
        this.nkid = nkid;
    }

    public String getNid() {
        return nid;
    }

    public String getNname() {
        return nname;
    }

    public String getNauthor() {
        return nauthor;
    }

    public String getPages() {
        return pages;
    }

    public String getNcreatetime() {
        return ncreatetime;
    }

    public String getNdesc() {
        return ndesc;
    }

    public String getIsvip() {
        return isvip;
    }

    public String getNstate() {
        return nstate;
    }

    public String getNondate() {
        return nondate;
    }

    public String getNkid() {
        return nkid;
    }
}
