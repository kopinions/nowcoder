package com.newcoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Err {

  private String filename;
  private int lineno;

  public Err(String filename, int lineno) {
    this.filename = filename;
    this.lineno = lineno;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    if (obj instanceof Err) {
      Err that = (Err) obj;
      return filename.equals(that.filename) && lineno == that.lineno;
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    return filename + " " + lineno;
  }
}

class Result {

  Err error;
  int count;

  public Result(Err error, int count) {
    this.error = error;
    this.count = count;
  }

  public Err getErr() {
    return error;
  }

  public int getCount() {
    return count;
  }

  public void countUp() {
    count = count + 1;
  }
}


class Recorder {

  private List<Result> results;

  public Recorder() {
    results = new ArrayList<>();
  }

  public void record(String line) {
    String[] segments = line.split(" ");
    String key = segments[0];
    String no = segments[1];

    String[] filesegments = key.split("\\\\");
    String filename = filesegments[filesegments.length - 1];
    Err err = new Err(
        filename.length() > 16 ? filename.substring(filename.length() - 16) : filename,
        Integer.parseInt(no));

    if (results.stream().anyMatch(r -> r.getErr().equals(err))) {
      Result result = results.stream().filter(r -> r.getErr().equals(err)).findFirst().get();
      result.countUp();
    } else {
      results.add(new Result(err, 1));
    }
  }

  public List<Result> results() {
    return results;
  }
}

public class ErrorCounter {

  public static void main(String[] args) throws IOException {
    Recorder recorder = new Recorder();
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    String line;
    while ((line = in.readLine()) != null) {
      if (line.isEmpty()) {
        break;
      }

      recorder.record(line);
    }

    List<Result> results = recorder.results();

    results.stream()
        .skip(Math.max(0, results.size() - 8))
        .forEach(e -> System.out.println(String.format("%s %s", e.getErr(), e.getCount())));
  }

}
