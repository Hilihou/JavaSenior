package mysqlTets;

public class test {
    private int SNO;
    private String sname;
    private int sage;

    @Override
    public String toString() {
        return "test{" +
                "SNO=" + SNO +
                ", sname='" + sname + '\'' +
                ", sage=" + sage +
                '}';
    }

    public void setSNO(int SNO) {
        this.SNO = SNO;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public void setSage(int sage) {
        this.sage = sage;
    }

    public int getSNO() {
        return SNO;
    }

    public String getSname() {
        return sname;
    }

    public int getSage() {
        return sage;
    }

    public test(int SNO, String sname, int sage) {
        this.SNO = SNO;
        this.sname = sname;
        this.sage = sage;
    }

    public test() {
    }
}
