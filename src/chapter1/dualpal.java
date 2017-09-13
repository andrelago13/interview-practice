package chapter1;/*
ID: asl_mbc2
LANG: JAVA
TASK: chapter1.dualpal
*/

import util.Exercise;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class dualpal extends Exercise {
    public static String PROB = "chapter1.dualpal";
    public static String INFILE = PROB + ".in";
    public static String OUTFILE = PROB + ".out";

    public static void main (String [] args) throws Exception {
        testMain();
    }

    public static String solve(BufferedReader f) throws Exception {
        StringTokenizer st = new StringTokenizer(f.readLine());

        int num = Integer.parseInt(st.nextToken());
        int min = Integer.parseInt(st.nextToken());
        ArrayList<Integer> results = new ArrayList<>();

        //Integer.toString(Integer.parseInt(number, base1), base2);

        for(int n = min + 1; n < Integer.MAX_VALUE; ++n) {
            int palCount = 0;
            for(int base = 2; base <= 10; ++base) {
                if(isPal(Integer.toString(n, base))) {
                    palCount++;
                    if(palCount > 1) {
                        results.add(n);
                        break;
                    }
                }
            }
            if(results.size() >= num) {
                break;
            }
        }


        String res = "";
        for(int i = 0; i < results.size(); ++i) {
            res += results.get(i);
            if(i < results.size() - 1) {
                res += '\n';
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