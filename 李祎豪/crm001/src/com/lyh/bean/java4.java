package com.lyh.bean;

import java.util.Scanner;

public class java4 {
    public static void main(String agrs[]) {
        Scanner input =new Scanner (System.in);

        for(int i=2;i>=0;i--) {
            System.out.print("请输入账号/用户名：");

            String name =input.next();

            System.out.print("请输入密码：");

            int password=input.nextInt();

            if(name.equals("小明")&&password==123456) {
                System.out.println("欢迎你，会员大佬");

                break;

            }else

                System.out.print("你输入错误！！！你还有"+i+"次机会");

            System.out.println();

            if(i==0) {
                System.out.print("三次全错！你没有机会了！");

            }

        }

    }

}

