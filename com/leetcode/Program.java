
package com.leetcode;

import java.util.*;

public class Program {
    public static String getPermutation(int n, int k) {
        List<Integer> numbers = new ArrayList<>();
        int[] factorial = new int[n+1];
        StringBuilder sb = new StringBuilder();

        // create an array of factorial lookup
        // factorial[] = {1, 1, 2, 6, 24, ... n!}
        int sum = 1;
        factorial[0] = 1;
        for(int i=1; i<=n; i++){
            sum *= i;
            factorial[i] = sum;
        }


        // create a list of numbers to get indices
        // numbers = {1, 2, 3, 4}
        for(int i=1; i<=n; i++){
            numbers.add(i);
        }


        //k indexed from 0
        k--;

        for(int i = 1; i <= n; i++){
            //Divide the permutations into groups of factorial[n-1]
            int index = k/factorial[n-i];
            sb.append(String.valueOf(numbers.get(index)));
            numbers.remove(index);
            //Eliminate all possible permutations using n-1
            k=k-index*factorial[n-i];
        }

        return String.valueOf(sb);

    }

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        System.out.println("Permutation Sequence:"+ getPermutation(4,4));
    }
}