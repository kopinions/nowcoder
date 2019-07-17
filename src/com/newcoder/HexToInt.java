package com.newcoder;

import java.util.Scanner;

public class HexToInt {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNext()) {
      String next = scanner.next("0x[0-9A-Fa-f]*");
      String hex = next.replaceAll("0x", "").toUpperCase();

      System.out.println(hex2int(hex));
    }
  }

  private static int hex2int(String hex) {
    String mapping = "0123456789ABCDEF";
    int result = 0;
    for (int i = 0; i < hex.length(); i++) {
      result += mapping.indexOf(hex.charAt(i)) * Math.pow(16, hex.length() - i -1);
    }
    return result;
  }
}
