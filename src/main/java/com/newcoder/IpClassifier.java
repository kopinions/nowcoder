package com.newcoder;/*
请解析IP地址和对应的掩码，进行分类识别。要求按照A/B/C/D/E类地址归类，不合法的地址和掩码单独归类。

所有的IP地址划分为 A,B,C,D,E五类

A类地址1.0.0.0~126.255.255.255;

B类地址128.0.0.0~191.255.255.255;

C类地址192.0.0.0~223.255.255.255;

D类地址224.0.0.0~239.255.255.255；

E类地址240.0.0.0~255.255.255.255


私网IP范围是：

10.0.0.0～10.255.255.255

172.16.0.0～172.31.255.255

192.168.0.0～192.168.255.255


子网掩码为二进制下前面是连续的1，然后全是0。（例如：255.255.255.32就是一个非法的掩码）

输入描述:
多行字符串。每行一个IP地址和掩码，用~隔开。

输出描述:
统计A、B、C、D、E、错误IP地址或错误掩码、私有IP的个数，之间以空格隔开。

示例1
输入
10.70.44.68~255.254.255.0
1.0.0.1~255.0.0.0
192.168.0.2~255.255.255.0
19..0.~255.255.255.0
输出
1 0 1 0 0 2 1
 */
import static java.util.stream.IntStream.range;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

interface IP {

  enum Category {
    A, B, C, D, E, PRIVATE, UNKNOWN
  }


  List<Category> categories();
}

class DefaultIP implements IP {

  private final String ip;
  private final String mask;

  public DefaultIP(String ip, String mask) {
    this.ip = ip;
    this.mask = mask;
  }

  @Override
  public List<Category> categories() {
    String[] segments = ip.split("\\.");
    int first = Integer.parseInt(segments[0]);
    int second = Integer.parseInt(segments[1]);
    String firstTwoBinary = Stream.of(first, second).map(IpFactory::d2b)
        .reduce("", (l, r) -> l + r);
    List<Category> categories = new ArrayList<>();
    if (Stream.of("00001010", "101011000001", "1100000010101000")
        .anyMatch(firstTwoBinary::startsWith)) {
      categories.add(Category.PRIVATE);
    }

    if (first >= 1 && first <= 126) {
      categories.add(Category.A);
    }

    if (first >= 128 && first <= 191) {
      categories.add(Category.B);
    }

    if (first >= 192 && first <= 223) {
      categories.add(Category.C);
    }

    if (first >= 224 && first <= 239) {
      categories.add(Category.D);
    }
    if (first >= 240 && first <= 255) {
      categories.add(Category.E);
    }

    return categories;
  }
}

class IpFactory {

  public IP create(String line) {
    String[] segments = line.split("~");
    if (segments.length != 2) {
      throw new RuntimeException("invalid input");
    }

    String ip = segments[0];
    String mask = segments[1];

    if (validFormat(ip) && validMask(mask)) {
      return new DefaultIP(ip, mask);
    }
    throw new RuntimeException();
  }


  private boolean validMask(String mask) {
    boolean validFormat = validFormat(mask);

    Pattern compile = Pattern
        .compile("(\\d+)\\.(\\d+)\\.(\\d+)\\.(\\d+)");
    Matcher matcher = compile.matcher(mask);
    if (!matcher.matches()) {
      return false;
    }

    boolean all255 = range(1, matcher.groupCount() + 1)
        .mapToObj(matcher::group)
        .map(Integer::parseInt)
        .allMatch($ -> $ == 255);


    boolean zeroBeforeOne = range(1, matcher.groupCount() + 1)
        .mapToObj(matcher::group)
        .map(Integer::parseInt)
        .map(IpFactory::d2b)
        .reduce("", (l, r) -> l + r)
        .contains("01");

    return validFormat && !all255 && !zeroBeforeOne;
  }

  public static String d2b(int decimal) {
    if (decimal == 0) {
      return "0";
    }
    int tmp = decimal;
    StringBuilder stringBuilder = new StringBuilder();
    while (tmp / 2 != 0) {
      stringBuilder.insert(0, tmp % 2);
      tmp = tmp / 2;
    }
    stringBuilder.insert(0, tmp % 2);
    for (int i = stringBuilder.length(); i < 8; i++) {
      stringBuilder.insert(0, 0);
    }
    return stringBuilder.toString();
  }

  private boolean validFormat(String ip) {
    Pattern pattern = Pattern
        .compile("(\\d+)\\.(\\d+)\\.(\\d+)\\.(\\d+)");
    Matcher matcher = pattern.matcher(ip);

    return matcher.matches() && range(1, matcher.groupCount() + 1)
        .mapToObj(matcher::group)
        .map(Integer::parseInt)
        .allMatch($ -> $ >= 0 && $ <= 255);
  }
}

public class IpClassifier {

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    List<IP> ipAddresses = new ArrayList<>();
    List<String> invalid = new ArrayList<>();
    IpFactory ipFactory = new IpFactory();
    String str;
    while ((str = bufferedReader.readLine()) != null) {
      try {
        ipAddresses.add(ipFactory.create(str));
      } catch (Exception e) {
        invalid.add(str);
      }
    }

    System.out.println(
        ipAddresses.stream().filter(i -> i.categories().stream().anyMatch(c -> c == IP.Category.A))
            .count() + " " +
            ipAddresses.stream()
                .filter(i -> i.categories().stream().anyMatch(c -> c == IP.Category.B)).count()
            + " " +
            ipAddresses.stream()
                .filter(i -> i.categories().stream().anyMatch(c -> c == IP.Category.C)).count()
            + " " +
            ipAddresses.stream()
                .filter(i -> i.categories().stream().anyMatch(c -> c == IP.Category.D)).count()
            + " " +
            ipAddresses.stream()
                .filter(i -> i.categories().stream().anyMatch(c -> c == IP.Category.E)).count()
            + " " +
            invalid.size() + " " +
            ipAddresses.stream()
                .filter(i -> i.categories().stream().anyMatch(c -> c == IP.Category.PRIVATE))
                .count()
    );
  }
}
