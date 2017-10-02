/*
ID: asl_mbc2
LANG: JAVA
TASK: sort3
*/

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


// used the solution here: https://github.com/mash99/USACO/blob/master/USACO/sort3.java

public class sort3 {
    static int arr[];
    static int n, a, b, c, counter;

    public static void main(String[] args) throws java.io.IOException {
        String prob = "sort3";
        StreamTokenizer input = new StreamTokenizer(new BufferedReader(new FileReader(prob + ".in")));
        PrintWriter output = new PrintWriter(new FileWriter(prob + ".out"));
        input.nextToken();
        n = (int) input.nval;
        arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            input.nextToken();
            arr[i] = (int) input.nval;
        }
        int[] sorted = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sorted);
        a = 0;
        while (a < sorted.length && sorted[a] == 1) a++;
        b = a;//a, b, c are starting regions of 1,2,3 respectively
        while (b < sorted.length && sorted[b] == 2) b++;
        b -= a;
        c = sorted.length - b - a;
        int x, y;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                x = arr[i];
                y = arr[j];
                if (x != y && inPlace(x, j) && inPlace(y, i)) {
                    swap(i, j);
                    counter++;
                }
            }
        }
        int c = 0;
        for (int i = 0; i < arr.length; i++)
            if (!inPlace(arr[i], i))
                c++;
        counter += 2 * (c / 3);
        output.println(counter);
        output.close();
    }

    public static void swap(int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static boolean inPlace(int val, int index) {
        switch (val) {
            case 1:
                return index < a;
            case 2:
                return index >= a && index < a + b;
            case 3:
                return index >= a + b;
        }
        return false;
    }
}