package com.newcoder;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

interface Validator<T, R> {
  R validate(T input);
}

class CompositeValidator implements Validator<String, Boolean> {

  private final List<Validator<String, Boolean>> validators;

  @SafeVarargs
  public CompositeValidator(Validator<String, Boolean>... validators) {
    this.validators = Arrays.asList(validators);
  }

  @Override
  public Boolean validate(String input) {
    return validators.stream().map(v -> v.validate(input)).reduce(true, (l, r) -> l && r);
  }
}

class Symbol implements Validator<String, Boolean> {

  @Override
  public Boolean validate(String input) {
    String result = input.replaceAll("[A-Za-z0-9]", "");
    return result.length() > 0;
  }
}

class LengthValidator implements Validator<String, Boolean> {

  private int min;

  public LengthValidator(int min) {
    this.min = min;
  }

  @Override
  public Boolean validate(String input) {
    return input.length() >= min;
  }
}

class RepeatValidator implements Validator<String, Boolean> {

  private int min;

  public RepeatValidator(int min) {
    this.min = min;
  }

  @Override
  public Boolean validate(String input) {
    for (int i = 0; i < input.length() - min; i++) {
      String substring = input.substring(i, i + min);
      if (input.substring(i + 1).contains(substring)) {
        return false;
      }
    }
    return true;
  }
}

class LeastValidator implements Validator<String, Boolean> {

  private final int min;
  private final List<Validator<String, Boolean>> validators;


  @SafeVarargs
  public LeastValidator(int min, Validator<String, Boolean>... validators) {
    this.min = min;
    this.validators = Arrays.asList(validators);
  }

  @Override
  public Boolean validate(String input) {
    Integer succeed = validators.stream().map(v -> v.validate(input)).reduce(0,
        (acc, i) -> i ? acc + 1 : acc, (l, r) -> l);
    return succeed >= min;
  }
}

class Uppercase implements Validator<String, Boolean> {

  @Override
  public Boolean validate(String input) {
    return input.matches(".*[A-Z].*");
  }
}

class Lowercase implements Validator<String, Boolean> {


  @Override
  public Boolean validate(String input) {
    return input.matches(".*[a-z].*");
  }
}


class Num implements Validator<String, Boolean> {


  @Override
  public Boolean validate(String input) {
    return input.matches(".*[0-9].*");
  }
}


public class PasswordValidator {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    CompositeValidator validator = new CompositeValidator(new LengthValidator(9),
        new RepeatValidator(3),
        new LeastValidator(3, new Uppercase(), new Lowercase(), new Num(), new Symbol()));

    while (scanner.hasNextLine()) {
      String password = scanner.nextLine();
      System.out.println(validator.validate(password) ? "OK" : "NG");
    }
  }

}
