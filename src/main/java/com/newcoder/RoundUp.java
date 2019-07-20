package com.newcoder;

import java.util.Scanner;

public class RoundUp {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNext()) {
      System.out.println(round(scanner.next()));
    }
  }

  private static int round(String line) {
    int result;
    String[] segs = line.split("\\.");
    int major = Integer.parseInt(segs[0]);
    if (segs.length == 1) {
      result = major;
    } else {
      if (segs[1].length() > 0) {
        char c = segs[1].charAt(0);
        int i = Integer.parseInt(String.valueOf(c));
        if (i >= 5) {
          result = major + 1;
        } else {
          result = major;
        }
      } else {
        result = major;
      }

    }
    return result;
  }
}
