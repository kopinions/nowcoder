/*
开发一个坐标计算工具， A表示向左移动，D表示向右移动，W表示向上移动，S表示向下移动。从（0,0）点开始移动，从输入字符串里面读取一些坐标，并将最终输入结果输出到输出文件里面。

输入：
合法坐标为A(或者D或者W或者S) + 数字（两位以内）
坐标之间以;分隔。
非法坐标点需要进行丢弃。如AA10;  A1A;  $%$;  YAD; 等。

下面是一个简单的例子 如：
A10;S20;W10;D30;X;A1A;B10A11;;A10;

处理过程：

起点（0,0）
+   A10   =  （-10,0）
+   S20   =  (-10,-20)
+   W10  =  (-10,-10)
+   D30  =  (20,-10)
+   x    =  无效
+   A1A   =  无效
+   B10A11   =  无效
+  一个空 不影响
+   A10  =  (10,-10)

结果 （10， -10）

输入描述:
一行字符串

输出描述:
最终坐标，以,分隔

示例1
输入
A10;S20;W10;D30;X;A1A;B10A11;;A10;
输出
10,-10
 */

package com.newcoder;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

interface Parser<F, T> {
  T parse(F commands);
}

interface Command {
  void execute(Context context);
}

class LeftCommand implements Command {

  private int amount;

  LeftCommand(int amount) {
    this.amount = amount;
  }

  @Override
  public void execute(Context context) {
    context.left(amount);
  }
}

class RightCommand implements Command {

  private int amount;

  RightCommand(int amount) {
    this.amount = amount;
  }

  @Override
  public void execute(Context context) {
    context.right(amount);
  }
}

class UpCommand implements Command {

  private int amount;

  UpCommand(int amount) {
    this.amount = amount;
  }

  @Override
  public void execute(Context context) {
    context.up(amount);
  }
}

class DownCommand implements Command {

  private int amount;

  DownCommand(int amount) {
    this.amount = amount;
  }

  @Override
  public void execute(Context context) {
    context.down(amount);
  }
}

class NullCommand implements Command {
  @Override
  public void execute(Context context) {
  }
}

class CommandParser implements Parser<String, Command> {
  @Override
  public Command parse(String command) {
    Pattern pattern = Pattern.compile("([AWSD])(\\d{1,2})");
    Matcher matcher = pattern.matcher(command);
    if (matcher.matches()) {
      String direction = matcher.group(1);
      Integer amount = Integer.valueOf(matcher.group(2));
      switch (direction) {
        case "A":
          return new LeftCommand(amount);
        case "S":
          return new DownCommand(amount);
        case "D":
          return new RightCommand(amount);
        case "W":
          return new UpCommand(amount);
      }
      return new NullCommand();
    } else {
      return new NullCommand();
    }
  }
}

class CommandsParser implements Parser<String, List<Command>> {

  @Override
  public List<Command> parse(String commands) {
    String[] split = commands.split(";");
    return Arrays.stream(split).map(c -> new CommandParser().parse(c))
        .collect(Collectors.toList());
  }
}

interface Context {

  void left(int amount);

  void right(int amount);

  void up(int amount);

  void down(int amount);
}

class Coordinate {

  private int x;
  private int y;

  public Coordinate() {
    x = 0;
    y = 0;
  }

  public void apply(Command command) {
    command.execute(new Context() {
      @Override
      public void left(int amount) {
        Coordinate.this.x -= amount;
      }

      @Override
      public void right(int amount) {
        Coordinate.this.x += amount;
      }

      @Override
      public void up(int amount) {
        Coordinate.this.y += amount;
      }

      @Override
      public void down(int amount) {
        Coordinate.this.y -= amount;
      }
    });
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
}

public class CoordinateMoving {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNextLine()) {
      List<Command> commands = new CommandsParser().parse(scanner.nextLine());
      Coordinate coordinate = new Coordinate();
      commands.forEach(coordinate::apply);
      System.out.println(coordinate.getX() +  "," + coordinate.getY());
    }
  }
}
