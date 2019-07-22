/*
输入一个int型的正整数，计算出该int型数据在内存中存储时1的个数。

输入描述:
 输入一个整数（int类型）

输出描述:
 这个数转换成2进制后，输出1的个数

示例1

输入
5

输出
2
 */

package com.newcoder;

import java.util.Scanner;

public class DecimalToBinary {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNextLine()) {
      String next = scanner.nextLine();
      int value = Integer.valueOf(next);

      System.out.println(d2b(value).chars().filter(c -> c == '1').count());
    }
  }

  private static String d2b(int value) {
    StringBuilder stringBuilder = new StringBuilder();
    while (value / 2 !=0 ) {
      int mode = value % 2;
      stringBuilder.insert(0, mode);
      value = value / 2;
    }
    stringBuilder.insert(0, value % 2);
    return stringBuilder.toString();
  }
}
