package com.iweb.Test;

import com.iweb.Control.FictionCtl;
import com.iweb.DAO.FictionDAOimpl;
import com.iweb.model.Fiction;
import com.iweb.utils.OtherUtils;

import java.util.List;

public class FictonTest {
    public static void main(String[] args) {
//        FictionDAOimpl fictionDAOimpl = new FictionDAOimpl();
//        //查询所有小说
//        List list = fictionDAOimpl.showAllfiction();
//        Iterator iterator = list.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
//        //编号查询小说
//        System.out.println(fictionDAOimpl.showfiction(1));
//
//        System.out.println(new C_fictionDAOimpl().showC_fiction());
        FictionDAOimpl fiction = FictionDAOimpl.getInstance();
        List list = fiction.showAllfiction();
        OtherUtils.showList(list);
        Fiction showfiction = fiction.showfiction(1);
        System.out.println(showfiction);
        FictionCtl fictionCtl = new FictionCtl();
        fictionCtl.FicChoose();
    }
}
