package com.novel;

/**
 * ORM编程思想
 * 一个数据表对应一个java类
 * 表中的一条记录对应java类的一个对象
 * 表中的一个字段对应java类的一个属性
 *
 */
public class dept1 {
    private String DEPTNO;
    private String DENAME;
    private String DELOCA;

    public dept1() {
    }

    public dept1(String DEPTNO, String DENAME, String DELOCA) {
        this.DEPTNO = DEPTNO;
        this.DENAME = DENAME;
        this.DELOCA = DELOCA;
    }

    @Override
    public String toString() {
        return "dept1{" +
                "DEPTNO='" + DEPTNO + '\'' +
                ", DENAME='" + DENAME + '\'' +
                ", DELOCA='" + DELOCA + '\'' +
                '}';
    }

    public void setDEPTNO(String DEPTNO) {
        this.DEPTNO = DEPTNO;
    }

    public void setDENAME(String DENAME) {
        this.DENAME = DENAME;
    }

    public void setDELOCA(String DELOCA) {
        this.DELOCA = DELOCA;
    }

    public String getDEPTNO() {
        return DEPTNO;
    }

    public String getDENAME() {
        return DENAME;
    }

    public String getDELOCA() {
        return DELOCA;
    }
}
