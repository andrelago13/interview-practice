package chapter1;/*
ID: asl_mbc2
LANG: JAVA
TASK: chapter1.palsquare
*/

import util.Exercise;

import java.io.*;
import java.util.StringTokenizer;

public class palsquare extends Exercise {
    public static String PROB = "chapter1.palsquare";
    public static String INFILE = PROB + ".in";
    public static String OUTFILE = PROB + ".out";

    public static void main (String [] args) throws Exception {
        testMain();
    }

    public static String solve(BufferedReader f) throws Exception {
        StringTokenizer st = new StringTokenizer(f.readLine());

        int base = Integer.parseInt(st.nextToken());
        //Integer.toString(Integer.parseInt(number, base1), base2);
        String res = "";

        for(int i = 1; i <= 300; ++i) {
            int square = i*i;
            String sqr = Integer.toString(square, base).toUpperCase();
            if(isPal(sqr)) {
                if(!res.isEmpty()) {
                    res += '\n';
                }
                res += Integer.toString(i, base).toUpperCase() + " " + sqr;
            }
        }

        return res;
    }

    public static boolean isPal(String num) {
        int left = 0;
        int right = num.length() - 1;
        while(left < right) {
            if(num.charAt(left) != num.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}