package com.newcoder;

import java.util.Scanner;

public class PrimeDivide {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    while (scanner.hasNext()) {
      long input = scanner.nextLong();

      System.out.println(prime(input));

    }
  }

  private static String prime(long input) {
    StringBuilder builder = new StringBuilder();
    long num = input;
    for (int i = 2; i <= input; i++) {
      while (num % i == 0) {
        builder.append(i).append(" ");
        num = num / i;
      }
    }

    return builder.toString();
  }
}
