/*
题目描述
给定n个字符串，请对n个字符串按照字典序排列。
输入描述:
输入第一行为一个正整数n(1≤n≤1000),下面n行为n个字符串(字符串长度≤100),字符串中只含有大小写字母。
输出描述:
数据输出n行，输出结果为按照字典序排列的字符串。
示例1
输入
复制
9
cap
to
cat
card
two
too
up
boat
boot
输出
复制
boat
boot
cap
card
cat
to
too
two
up
 */

package com.newcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DictSort {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNextLine()) {
      int count = Integer.valueOf(scanner.nextLine());
      List<String> collections = new ArrayList<>();
      while (count > 0) {
        String word = scanner.nextLine();
        collections.add(word);
        count--;
      }
//      collectionSort(collections);
      for (String e : bubble(collections)) {
        System.out.println(e);
      }
    }
  }

  private static void collectionSort(List<String> collections) {
    Collections.sort(collections);
  }

  public static List<String> bubble(List<String> args) {
    String[] arr = args.toArray(new String[0]);

    for (int i = 0; i < arr.length; i++) {
      for (int j = i; j < arr.length; j++) {
        if (arr[j].compareTo(arr[i]) < 0) {
          String tmp = arr[i];
          arr[i] = arr[j];
          arr[j] = tmp;
        }
      }
    }
    return Arrays.asList(arr);
  }
}
