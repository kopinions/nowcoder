/*
题目描述
描述：

输入一个整数，将这个整数以字符串的形式逆序输出

程序不考虑负数的情况，若数字含有0，则逆序形式也含有0，如输入为100，则输出为001


输入描述:
输入一个int整数

输出描述:
将这个整数以字符串的形式逆序输出

示例1
输入
复制
1516000
输出
复制
0006151
 */

package com.newcoder;

import java.util.Scanner;

public class IntegerReverse {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNext()) {
      System.out.println(reverse(scanner.nextInt()));
    }
  }

  private static String reverse(int number) {
    String src = String.valueOf(number);
    StringBuilder collector = new StringBuilder();
    for (int i = src.length() - 1; i >= 0; i--) {
      collector.append(src.charAt(i));
    }
    return collector.toString();
  }
}
