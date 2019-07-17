package com.newcoder;

import java.util.Scanner;
import java.util.TreeSet;

public class RandomUniqueSort {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNext()) {
      int count = scanner.nextInt();

      TreeSet<Integer> integers = new TreeSet<>();
      while (count-- > 0) {
        integers.add(scanner.nextInt());
      }

      for (Integer i : integers) {
        System.out.println(i);
      }
    }
  }
}
