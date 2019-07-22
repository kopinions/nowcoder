/*
题目描述
将一个英文语句以单词为单位逆序排放。例如“I am a boy”，逆序排放后为“boy a am I”
所有单词之间用一个空格隔开，语句中除了英文字母外，不再包含其他字符


接口说明

/**
 * 反转句子
 *
 * @param sentence 原句子
 * @return 反转后的句子

public String reverse(String sentence);







    输入描述:
    将一个英文语句以单词为单位逆序排放。

    输出描述:
    得到逆序的句子

    示例1
    输入
    复制
    I am a boy
    输出
    复制
    boy a am I


*/

package com.newcoder;

import java.util.Scanner;

public class SentenceReverse {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNext()) {
      String sentence = scanner.nextLine();
      SentenceReverse main = new SentenceReverse();
      System.out.println(main.reverse(sentence));
    }
  }

  public String reverse(String sentence) {
    String[] words = sentence.split(" ");
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = words.length - 1; i >= 0; i--) {
      stringBuilder.append(words[i]);
      if (i > 0) {
        stringBuilder.append(" ");
      }
    }
    return stringBuilder.toString();
  }
}
