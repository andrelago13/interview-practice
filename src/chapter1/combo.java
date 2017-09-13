package chapter1;/*
ID: asl_mbc2
LANG: JAVA
TASK: chapter1.combo
*/

import util.Exercise;

import java.io.*;
import java.util.StringTokenizer;

public class combo extends Exercise {
    public static String PROB = "chapter1.combo";
    public static String INFILE = PROB + ".in";
    public static String OUTFILE = PROB + ".out";

    public static void main (String [] args) throws Exception {
        testMain();
    }

    public static String solve(BufferedReader f) throws Exception {
        StringTokenizer st = new StringTokenizer(f.readLine());

        int dialSize = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(f.readLine());
        int farmer1 = Integer.parseInt(st.nextToken());
        int farmer2 = Integer.parseInt(st.nextToken());
        int farmer3 = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(f.readLine());
        int master1 = Integer.parseInt(st.nextToken());
        int master2 = Integer.parseInt(st.nextToken());
        int master3 = Integer.parseInt(st.nextToken());

        int count = 0;
        for(int num1 = 1; num1 <= dialSize; ++num1) {
            if(minDist(num1, farmer1, dialSize) > 2) {
                continue;
            }
            for(int num2 = 1; num2 <= dialSize; ++num2) {
                if(minDist(num2, farmer2, dialSize) > 2) {
                    continue;
                }
                for(int num3 = 1; num3 <= dialSize; ++num3) {
                    if(minDist(num3, farmer3, dialSize) > 2) {
                        continue;
                    }
                    if(minDist(num1, master1, dialSize) <= 2 && minDist(num2, master2, dialSize) <= 2 && minDist(num3, master3, dialSize) <= 2) {
                        continue;
                    }
                    count++;
                }
            }
        }
        for(int num1 = 1; num1 <= dialSize; ++num1) {
            if(minDist(num1, master1, dialSize) > 2) {
                continue;
            }
            for(int num2 = 1; num2 <= dialSize; ++num2) {
                if(minDist(num2, master2, dialSize) > 2) {
                    continue;
                }
                for(int num3 = 1; num3 <= dialSize; ++num3) {
                    if(minDist(num3, master3, dialSize) > 2) {
                        continue;
                    }
                    count++;
                }
            }
        }

        return "" + count;
    }

    public static int minDist(int num1, int num2, int size) {
        if(num2 > num1) {
            int v1 = num2 - num1;
            int v2 = (num1 + size)-num2;
            if(v1 < v2) {
                return v1;
            }
            return v2;
        } else if (num1 > num2) {
            int v1 = num1 - num2;
            int v2 = (num2 + size)-num1;
            if(v1 < v2) {
                return v1;
            }
            return v2;
        }
        return 0;
    }
}