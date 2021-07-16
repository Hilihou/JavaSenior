package com.iweb.view;

import com.iweb.Control.FictionCtl;

import java.util.Scanner;

public class TestWindow {
    public static void main(String[] args) {

        while (true){

            System.out.println("---------------<小说阅读网站>------------------");
            System.out.println("---------------1.小说功能---------------------");
            System.out.println("---------------2.用户功能(未实现)--------------");
            System.out.println("---------------3.小说类别(未实现)--------------");
            Scanner scanner = new Scanner(System.in);
            System.out.print("输入您的选择(-1退出):");
            int i = scanner.nextInt();
            if (i==-1) break;
            switch (i){
                case 1:
                    new FictionCtl().FicChoose();
                    break;
                case 2:
                    System.out.println("用户功能(未实现)");
                    break;
                case 3:
                    System.out.println("小说类别(未实现)");
                    break;
                default:
                    System.out.println("输入有误,重新输入");
            }

        }

    }
}
