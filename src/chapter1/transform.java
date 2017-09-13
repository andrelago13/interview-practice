package chapter1;/*
ID: asl_mbc2
LANG: JAVA
TASK: chapter1.transform
*/

import util.Exercise;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class transform extends Exercise {
    public static String PROB = "chapter1.transform";
    public static String INFILE = PROB + ".in";
    public static String OUTFILE = PROB + ".out";

    public static void main (String [] args) throws Exception {
        testMain();
    }

    private static int N = 0;

    public static String solve(BufferedReader f) throws Exception {
        StringTokenizer st = new StringTokenizer(f.readLine());

        N = Integer.parseInt(st.nextToken());
        char[][] matrix1 = new char[N][N];
        for(int l = 0; l < N; ++l) {
            String line = f.readLine();
            for(int c = 0; c < line.length(); ++c) {
                matrix1[l][c] = line.charAt(c);
            }
        }
        char[][] matrix2 = new char[N][N];
        for(int l = 0; l < N; ++l) {
            String line = f.readLine();
            for(int c = 0; c < line.length(); ++c) {
                matrix2[l][c] = line.charAt(c);
            }
        }

        if(equal(matrix2, rot90(matrix1))) {
            return "" + 1;
        }
        if(equal(matrix2, rot180(matrix1))) {
            return "" + 2;
        }
        if(equal(matrix2, rot270(matrix1))) {
            return "" + 3;
        }
        if(equal(matrix2, ref(matrix1))) {
            return "" + 4;
        }
        char[][] test = ref(matrix1);
        for(int l = 0; l < N; ++l) {
            for(int c = 0; c < N; ++c) {
                System.out.print(test[l][c]);
            }
            System.out.println();
        }
        test = rot90(test);
        for(int l = 0; l < N; ++l) {
            for(int c = 0; c < N; ++c) {
                System.out.print(test[l][c]);
            }
            System.out.println();
        }
        if (equal(matrix2, rot90(ref(matrix1))) ||
                equal(matrix2, rot180(ref(matrix1))) ||
                equal(matrix2, rot270(ref(matrix1)))) {
            return "" + 5;
        }
        if(equal(matrix2, matrix1)) {
            return "" + 6;
        }

        return "" + 7;
    }

    public static boolean equal(char[][] mat1, char[][] mat2) {
        for(int l = 0; l < N; ++l) {
            for(int c = 0; c < N; ++c) {
                if(mat1[l][c] != mat2[l][c]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static char[][] rot90(char[][] mat) {
        char[][] res = new char[N][N];

        for(int l = 0; l < N; ++l) {
            for(int c = 0; c < N; ++c) {
                res[c][N-l-1] = mat[l][c];
            }
        }

        return res;
    }

    public static char[][] rot180(char[][] mat) {
        char[][] res = new char[N][N];

        for(int l = 0; l < N; ++l) {
            for(int c = 0; c < N; ++c) {
                res[N-l-1][N-c-1] = mat[l][c];
            }
        }

        return res;
    }

    public static char[][] rot270(char[][] mat) {
        char[][] res = new char[N][N];

        for(int l = 0; l < N; ++l) {
            for(int c = 0; c < N; ++c) {
                res[N-c-1][l] = mat[l][c];
            }
        }

        return res;
    }

    public static char[][] ref(char[][] mat) {
        char[][] res = new char[N][N];

        for(int l = 0; l < N; ++l) {
            for(int c = 0; c < N; ++c) {
                res[l][N-c-1] = mat[l][c];
            }
        }

        return res;
    }
}