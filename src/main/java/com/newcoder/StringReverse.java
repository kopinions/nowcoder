/*
题目描述
写出一个程序，接受一个字符串，然后输出该字符串反转后的字符串。例如：
输入描述:
输入N个字符

输出描述:
输出该字符串反转后的字符串

示例1
输入
复制
abcd
输出
复制
dcba
 */

package com.newcoder;

import java.util.Scanner;

public class StringReverse {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNext()) {
      System.out.println(reverse(scanner.next()));
    }
  }

  private static String reverse(String src) {
    StringBuilder collector = new StringBuilder();
    for (int i = src.length() - 1; i >= 0; i--) {
      collector.append(src.charAt(i));
    }
    return collector.toString();
  }
}
