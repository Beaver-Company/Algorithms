/*
 * Copyright (C) 2014 Pedro Vicente Gómez Sánchez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.pedrovgs.app.problem4;

/**
 * Given an integer passed as parameter, can you write a method to calculate the square root?
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class SquareRoot {

  private static final double DELTA = 0.1;

  /**
   * Solution implemented to calculate the square root of a given number based on an iterative
   * algorithm.
   *
   * First, we are going to find the first number witch square is greater than the number. Once we
   * have that number we are going to apply a binary search between candidate and candidate +1.
   *
   * The complexity order in space terms of this algorithm is O(1) because we are not using any
   * additional data structure. The complexity order in time terms is more difficult to calculate
   * O(sqrt(N)) + O(binarySearch) = O(sqrt(N)) the binary search complexity order depends on the
   * number of decimals the solution contains.
   */
  public float calculateSquareRootIterative(int number) {
    //Search first candidate
    float candidate = 1f;
    while (candidate * candidate <= number) {
      candidate++;
    }
    candidate--;
    if (isGoodResultForSqrt(number, candidate * candidate)) {
      return candidate;
    }

    //Apply binary search.
    float top = candidate + 1;
    float bottom = candidate;
    float newCandidate = (top + bottom) / 2;
    float result = newCandidate * newCandidate;
    while (!isGoodResultForSqrt(number, result)) {
      if (result > number) {
        top -= 0.1f;
      } else {
        bottom -= 0.1f;
      }
      newCandidate = (top + bottom) / 2;
      result = newCandidate * newCandidate;
    }
    return Math.abs(newCandidate);
  }

  private static boolean isGoodResultForSqrt(float number, float result) {
    return Math.abs(result - number) < DELTA;
  }
}
