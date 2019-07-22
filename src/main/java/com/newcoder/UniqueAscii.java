/*

编写一个函数，计算字符串中含有的不同字符的个数。字符在ACSII码范围内(0~127)。不在范围内的不作统计。

输入描述:
输入N个字符，字符在ACSII码范围内。

输出描述:
输出范围在(0~127)字符的个数。

示例1
输入
复制
abc
输出
复制
3
 */
package com.newcoder;

import java.util.Scanner;

public class UniqueAscii {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNext()) {
      String next = scanner.next();
      System.out.println(uniqueAscii(next).length());
    }
  }

  private static String uniqueAscii(String next) {
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < next.length(); i++) {
      char c = next.charAt(i);
      if (c <= 127 && stringBuilder.indexOf(String.valueOf(c)) == -1) {
        stringBuilder.append(c);
      }
    }
    return stringBuilder.toString();
  }
}
