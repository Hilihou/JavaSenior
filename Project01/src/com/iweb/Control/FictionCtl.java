package com.iweb.Control;

import com.iweb.DAO.FictionDAOimpl;
import com.iweb.model.Fiction;
import com.iweb.utils.OtherUtils;

import java.util.List;
import java.util.Scanner;

public class FictionCtl {
    public void FicChoose(){
        while (true) {
            System.out.println("---------------1.查询所有小说-----------");
            System.out.println("---------------2.按编号查询小说---------");
            System.out.println("---------------3.新增小说--------------");
            System.out.println("---------------4.修改小说--------------");
            System.out.println("---------------5.删除小说--------------");
            Scanner scanner = new Scanner(System.in);
            System.out.print("请输入您的选择(-1返回)：");
            int i = scanner.nextInt();
            FictionDAOimpl fiction = FictionDAOimpl.getInstance();
            if (i==-1)break;
            switch (i) {
                //查询所有小说
                case 1:
                    List list = fiction.showAllfiction();
                    OtherUtils.showList(list);
                    System.out.println("查询完成");
                    break;
                //按编号查看小说
                case 2:
                    try {
                        System.out.print("请输入小说编号:");
                        int i1 = scanner.nextInt();
                        Fiction showfiction = fiction.showfiction(i1);
                        System.out.println(showfiction);
                        System.out.println("查询完成");
                    } catch (Exception e) {
                        System.out.println("编号不存在！！");
                    } finally {
                        break;
                    }
                //新增
                case 3:
                    String name = null;
                    String author = null;
                    int pages = 0;
                    String createdate = null;
                    String desc = null;
                    String ismenber = null;
                    String isput = null;
                    String putime = null;
                    try {
                        System.out.print("请输入小说名称：");
                        name = scanner.next();
                        System.out.print("作者：");
                        author = scanner.next();
                        System.out.print("页数：");
                        pages = scanner.nextInt();
                        System.out.print("创建时间(年-月-日)：");
                        createdate = scanner.next();
                        System.out.print("小说描述：");
                        desc = scanner.next();
                        System.out.print("是否会员：");
                        ismenber = scanner.next();
                        System.out.print("是否上架：");
                        isput = scanner.next();
                        System.out.print("上架时间(年-月-日)：");
                        putime = scanner.next();
                        boolean b = fiction.addFiction(name, author, pages, createdate, desc, ismenber, isput, putime);
                        System.out.println(b);
                    } catch (Exception e) {
                        throw new RuntimeException("填写错误，请重新填写！！");
                    }finally {
                        break;
                    }
                //修改
                case 4:
                    try {
                        System.out.print("请输入你需要修改的小说id：");
                        int id = scanner.nextInt();
                        Fiction showfiction1 = fiction.showfiction(id);
                        System.out.println("您要修改的小说是：" + showfiction1);
                        System.out.print("请输入新的小说名称(" + showfiction1.getFictionName() + "):");
                        String newname = scanner.next();
                        System.out.print("新作者(" + showfiction1.getAuthor() + "):");
                        String newauthor = scanner.next();
                        System.out.print("新页数(" + showfiction1.getPages() + "):");
                        int newpages = scanner.nextInt();
                        System.out.print("小说描述(" + showfiction1.getDescriptions() + "):");
                        String newdesc = scanner.next();
                        System.out.print("是否会员(" + showfiction1.getIsMember() + "):");
                        String newismenber = scanner.next();
                        System.out.print("是否上架(" + showfiction1.getIsputaway() + "):");
                        String newputime = scanner.next();
                        System.out.println("宁填写的是:"+newname+","+newauthor+","+newpages+","+newdesc+","+newismenber+","+newputime);
                        System.out.print("是否保留修改？(按1 确定)：");
                        int i1 = scanner.nextInt();
                        if (i1==1){
                            boolean b1 = fiction.alterFiction(id, newname, newauthor, newpages, newdesc, newismenber, newputime);
                            System.out.println(b1);
                        }else {
                            break;
                        }

                    } catch (Exception e) {
                        System.out.println("填写错误，请重新填写！！");
                    } finally {
                        break;
                    }
                //删除
                case 5:
                    System.out.print("请输入需要删除的小说号:");
                    int i2 = scanner.nextInt();
                    Fiction showfiction2 = fiction.showfiction(i2);
                    System.out.println("您要删除的小说是:" + showfiction2);
                    System.out.print("是否确认删除？(按1删除)");
                    int cfg = scanner.nextInt();
                    if (cfg == 1) {
                        boolean b2 = fiction.deleteFiction(i2);
                        System.out.println(b2);
                        break;
                    } else
                        break;
                default:
                    System.out.println("选择有误，重新选择！");
                    break;
            }
        }


    }
}
