package com.iweb.model;

public class Companys {
    private Integer id;
    private String name;

    @Override
    public String toString() {
        return "t_exp_companys{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Companys that = (Companys) o;

        if (!id.equals(that.id)) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    public void setid(Integer id) {
        this.id = id;
    }

    public void setname(String name) {
        this.name = name;
    }

    public Integer getid() {
        return id;
    }

    public String getname() {
        return name;
    }

    public Companys(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Companys() {
    }
}
