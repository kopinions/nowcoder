package com.newcoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class FixedColumn {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    List<String> inputs = new ArrayList<>();
    while (scanner.hasNext()) {
      inputs.add(scanner.next());
    }

    inputs.forEach(i -> output(splitUsingMod(i)));
  }

  private static void output(List<String> results) {
    for (String s : results) {
      System.out.println(s);
    }
  }

  private static List<String> split(String i) {
    List<String> results = new ArrayList<>();
    String tmp = i;
    while (tmp.length() > 0) {
      for (int j = 0; j < 8; j++) {
        if (tmp.length() > 8) {
          results.add(tmp.substring(0, 8));
          tmp = tmp.substring(8);
        } else if (tmp.length() != 0) {
          results.add(tmp + FixedColumn.zero(8 - tmp.length()));
          tmp = "";
        }
      }
    }
    return results;
  }

  public static List<String> splitUsingMod(String input) {
    List<String> results = new ArrayList<>();
    if (input.length() % 8 != 0) {
      input = input + zero(8 - input.length() % 8);
    }
    while (input.length() > 0) {
      results.add(input.substring(0, 8));
      input = input.substring(8);
    }
    return results;
  }

  private static StringBuilder zero(int length) {
    return IntStream.range(0, length).map(a -> 0)
        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
  }
}

