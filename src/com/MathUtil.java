package com;

public class MathUtil {

    public static String round(int num,int scale)
    {
        /**
         * @param 四舍五入工具
         *
         */
        double mb=1024.0*1024.0;//计算MB的
        double kb=1024.0;//计算Kb的
        double result=num/mb;//得出结果
        String str=String.valueOf(result);
        return str.substring(0,scale);
    }

//    public static void main(String[] args) {
//        System.err.println(round(65536,4));
//    }
}
