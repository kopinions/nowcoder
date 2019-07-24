/*
链接：https://www.nowcoder.com/questionTerminal/708f0442863a46279cce582c4f508658

现有一个容量大小为V的背包和N件物品，每件物品有两个属性，体积和价值，请问这个背包最多能装价值为多少的物品？


输入描述:
第一行两个整数V和n。
接下来n行，每行两个整数体积和价值。1≤N≤1000,1≤V≤20000。
每件物品的体积和价值范围在[1,500]。


输出描述:
输出背包最多能装的物品价值。
示例1
输入
6 3
3 5
2 4
4 2
输出
9
 */

package com.newcoder;

import java.util.Scanner;

class Item {

  private int weight;
  private int value;

  public Item(int weight, int value) {
    this.weight = weight;
    this.value = value;
  }

  public int getWeight() {
    return weight;
  }

  public int getValue() {
    return value;
  }
}

public class Knapsack {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNextLine()) {
      int volume = scanner.nextInt();
      int quantity = scanner.nextInt();

      Item[] items = new Item[quantity + 1];

      for (int i = quantity - 1; i >= 0; i--) {
        items[quantity - 1] = new Item(scanner.nextInt(), scanner.nextInt());
      }

      System.out.println(maximize(volume, quantity, items));
    }
  }

  private static int maximize(int volume, int quantity, Item[] items) {
    int[][] matrix = new int[quantity + 1][volume + 1];

    for (int i = 1; i <= quantity; i++) {
      for (int j = 1; j <= volume; j++) {
        Item item = items[i];
        if (item.getWeight() > j) {
          matrix[i][j] = matrix[i - 1][j];
        } else {
          matrix[i][j] = Math
              .max(matrix[i - 1][j], matrix[i - 1][j - item.getWeight()] + item.getValue());
        }
      }
    }

    return matrix[quantity][volume];
  }
}
