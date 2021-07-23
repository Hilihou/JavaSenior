package com.iweb.test;

import com.iweb.model.Companys;
import com.iweb.utils.JDBCutils;
import org.testng.annotations.Test;

import java.util.List;

public class SelectTest {
    @Test
            public void test1(){
        List<Companys> instance = JDBCutils.getInstance(Companys.class, "select exp_com_id id,exp_com_name name from t_exp_companys;");
        instance.forEach(System.out::println);
    }

}
