package com.newcoder;

/*
题目描述
数据表记录包含表索引和数值，请对表索引相同的记录进行合并，即将相同索引的数值进行求和运算，输出按照key值升序进行输出。

输入描述:
先输入键值对的个数
然后输入成对的index和value值，以空格隔开

输出描述:
输出合并后的键值对（多行）

示例1
输入
复制
4
0 1
0 2
1 2
3 4
输出
复制
0 3
1 2
3 4

*/

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class TableMerge {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    while (scanner.hasNext()) {
      int count = Integer.parseInt(scanner.nextLine());
      Map<Integer, Integer> merged = new TreeMap<>();
      while (count-- > 0) {
        String line = scanner.nextLine();
        String[] pairs = line.split(" ");
        merged.compute(Integer.parseInt(pairs[0]),
            (key, oldValue) -> {
              if (oldValue == null) {
                return Integer.parseInt(pairs[1]);
              } else {
                return oldValue + Integer.parseInt(pairs[1]);
              }
            });
      }

      merged.forEach((key, value) -> System.out.println(key + " " + value));
    }
  }
}
