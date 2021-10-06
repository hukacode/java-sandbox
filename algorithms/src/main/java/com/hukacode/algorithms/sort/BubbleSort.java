/*
 * Copyright 2021 hukacode
 */
package com.hukacode.algorithms.sort;

import java.util.Arrays;

public class BubbleSort implements Sort {
  public int[] sort(int[] input) {
    int length = input.length;

    for (int i = 0; i < length - 1; i++) {
      for (int j = 0; j < length - i - 1; j++) {
        if (input[j] > input[j + 1]) {
          int temp = input[j + 1];
          input[j + 1] = input[j];
          input[j] = temp;
        }
      }

      System.out.println(Arrays.toString(input));
    }

    return input;
  }
}
