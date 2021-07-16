package com.novel;

import java.math.BigDecimal;

public class JDBCTest {
    private BigDecimal jid;
    private String JNAME;
    private java.sql.Timestamp JDATE;

    @Override
    public String toString() {
        return "JDBCTest{" +
                "jid=" + jid +
                ", JNAME='" + JNAME + '\'' +
                ", JDATE=" + JDATE +
                '}';
    }

    public void setJID(BigDecimal jid) {
        this.jid = jid;
    }

    public void setJNAME(String JNAME) {
        this.JNAME = JNAME;
    }

    public void setJDATE(java.sql.Timestamp JDATE) {
        this.JDATE = JDATE;
    }

    public BigDecimal getJID() {
        return jid;
    }

    public String getJNAME() {
        return JNAME;
    }

    public java.sql.Timestamp getJDATE() {
        return JDATE;
    }

    public JDBCTest(BigDecimal jid, String JNAME, java.sql.Timestamp JDATE) {
        this.jid = jid;
        this.JNAME = JNAME;
        this.JDATE = JDATE;
    }

    public JDBCTest() {
    }
}
