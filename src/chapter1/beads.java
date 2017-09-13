package chapter1;/*
ID: asl_mbc2
LANG: JAVA
TASK: chapter1.beads
*/

import util.Exercise;

import java.io.*;
import java.util.StringTokenizer;

public class beads extends Exercise {
    public static String PROB = "chapter1.beads";
    public static String INFILE = PROB + ".in";
    public static String OUTFILE = PROB + ".out";

    public static void main (String [] args) throws Exception {
        testMain();
    }

    public static String solve(BufferedReader f) throws Exception {
        StringTokenizer st = new StringTokenizer(f.readLine());
        int length = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(f.readLine());
        String beads = st.nextToken();

        int maxCount = 0;

        for(int i = 0; i < beads.length(); ++i) {
            String left = beads.substring(0, i);
            String right = beads.substring(i, length);

            String temp = right + left;

            char startColor = '0';
            int currCount = 0;
            boolean foundSecond = false;
            for(int j = 0; j < temp.length(); ++j) {
                char c = temp.charAt(j);
                if(i == 23) {
                    System.out.println(c);
                }
                if(c == 'w') {
                    ++currCount;
                    continue;
                }
                if(startColor == '0') {
                    startColor = c;
                    ++currCount;
                    continue;
                } else if (startColor != c) {
                    foundSecond = true;
                }

                if(startColor == c && foundSecond) {
                    break;
                }

                currCount++;
            }
            if(currCount > maxCount)
                maxCount = currCount;
        }

        return "" + maxCount;
    }
}