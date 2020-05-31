package com.wmc.arithmetic.kmp;

import java.util.Arrays;

/**
 * @author: WangMC
 * @date: 2020/5/28 21:51
 * @description:
 */
public class KMPAlgorithm {


    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int[] next = kmpNext(str2);
        System.out.println(Arrays.toString(next));
        int index = kmpSearch(str1, str2, next);
        System.out.println("index=" + index);
    }
    //写出我们的kmp 搜索算法

    /**
     * @param str1 源字符串
     * @param str2 子串
     * @param next 部分匹配表, 是子串对应的部分匹配表
     * @return 如果是-1 就是没有匹配到，否则返回第一个匹配的位置
     */
    public static int kmpSearch(String str1, String str2, int[] next) {

        for (int i = 0, j = 0; i < str1.length(); i++) {
            //需要处理str1.charAt(i) ！= str2.charAt(j), 去调整j 的大小
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {

                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }

            if (j == str2.length()) {
                return i - j + 1;
            }

        }
        return -1;
    }

    //获取到一个字符串(子串) 的部分匹配值表
    public static int[] kmpNext(String dest) {
        //创建一个next 数组保存部分匹配值
        int[] next = new int[dest.length()];
        //如果字符串是长度为1 部分匹配值就是0
        next[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++) {
            //当dest.charAt(i) != dest.charAt(j) ，我们需要从next[j-1]获取新的j
            //直到我们发现有dest.charAt(i) == dest.charAt(j)成立才退出
            //这时kmp 算法的核心点
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }

            //当dest.charAt(i) == dest.charAt(j) 满足时，部分匹配值就是+1
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }

            next[i] = j;
        }
        return next;
    }

}
