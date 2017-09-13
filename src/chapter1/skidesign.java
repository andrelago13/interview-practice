package chapter1;/*
ID: asl_mbc2
LANG: JAVA
TASK: chapter1.skidesign
*/

import util.Exercise;

import java.io.*;
import java.util.*;

public class skidesign extends Exercise {
    public static String PROB = "chapter1.skidesign";
    public static String INFILE = PROB + ".in";
    public static String OUTFILE = PROB + ".out";

    public static void main (String [] args) throws Exception {
        testMain();
    }

    public static String solve(BufferedReader f) throws Exception {
        StringTokenizer st = new StringTokenizer(f.readLine());

        int numHills = Integer.parseInt(st.nextToken());
        int hills[] = new int[numHills];
        int maxH = 0;
        for(int i = 0; i < numHills; ++i) {
            hills[i] = Integer.parseInt(f.readLine());
            if(hills[i] > maxH) {
                maxH = hills[i];
            }
        }

        int minCost = Integer.MAX_VALUE;

        for(int minHeight = 0; minHeight < maxH; ++minHeight) {
            int maxHeight = minHeight + 17;

            int currCost = 0;

            for(int i = 0; i < numHills; ++i) {
                int val = hills[i];
                if(val < minHeight) {
                    currCost += Math.pow(val - minHeight, 2);
                } else if (val > maxHeight) {
                    currCost += Math.pow(maxHeight - val, 2);
                }
            }

            if(currCost < minCost) {
                minCost = currCost;
            }
        }

//        for(int minHill = 0; minHill < numHills; ++minHill) {
//            int currCost = 0;
//            int minHeight = hills[minHill];
//            int maxHeight = minHeight + 17;
//
//            for(int i = 0; i < numHills; ++i) {
//                int val = hills[i];
//                if(val < minHeight) {
//                    currCost += Math.pow(val - minHeight, 2);
//                } else if (val > maxHeight) {
//                    currCost += Math.pow(maxHeight - val, 2);
//                }
//            }
//
//            if(currCost < minCost) {
//                minCost = currCost;
//            }
//        }

        return "" + minCost;
    }
}