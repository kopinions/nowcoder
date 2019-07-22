/*
题目描述
输入一个int型整数，按照从右向左的阅读顺序，返回一个不含重复数字的新的整数。

输入描述:
输入一个int型整数

输出描述:
按照从右向左的阅读顺序，返回一个不含重复数字的新的整数

示例1
输入
复制
9876673
输出
复制
37689
 */

package com.newcoder;

import java.util.Scanner;

public class ReverseWithUnique {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    while (scanner.hasNext()) {
      System.out.println(reverse(scanner.next()));
    }
  }

  private static String reverse(String number) {
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = number.length()-1; i >= 0; i--) {
      char c = number.charAt(i);
      if (stringBuilder.indexOf(String.valueOf(c)) == -1) {
        stringBuilder.append(c);
      }
    }
    return stringBuilder.toString();
  }

}
