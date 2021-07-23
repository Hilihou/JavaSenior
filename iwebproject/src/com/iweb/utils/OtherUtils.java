package com.iweb.utils;

import java.util.Iterator;
import java.util.List;

public class OtherUtils {
    public static void showList(List list){
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
