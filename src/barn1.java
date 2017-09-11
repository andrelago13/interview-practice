/*
ID: asl_mbc2
LANG: JAVA
TASK: barn1
*/

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class barn1 {
    public static String PROB = "barn1";
    public static String INFILE = PROB + ".in";
    public static String OUTFILE = PROB + ".out";

    public static void main (String [] args) throws Exception {
        BufferedReader f = new BufferedReader(new FileReader(INFILE));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTFILE)));

        String result = solve(f);
        out.println(result);
        out.close();
    }

    public static String solve(BufferedReader f) throws Exception {
        StringTokenizer st = new StringTokenizer(f.readLine());

        int maxBoards = Integer.parseInt(st.nextToken());
        int numStalls = Integer.parseInt(st.nextToken());
        int numCows = Integer.parseInt(st.nextToken());
        boolean stalls[] = new boolean[numStalls+1];
        for(int i = 0; i <= numStalls; ++i) {
            stalls[i] = false;
        }
        for(int i = 0; i < numCows; ++i) {
            stalls[Integer.parseInt(f.readLine())] = true;
        }

        while(countBoards(stalls) > maxBoards) {
            mergeClosest(stalls);
        }

        return "" + countStalls(stalls);
    }

    public static int countBoards(boolean[] stalls) {
        int count = 0;
        for(int i = 0; i < stalls.length - 1; ++i) {
            if(i <= stalls.length - 1 && stalls[i] && !stalls[i+1]) {
                ++count;
            } else if (i == stalls.length - 1) {
                ++count;
            }
        }
        System.out.println("Boards " + count);
        return count;
    }

    public static int countStalls(boolean[] stalls) {
        int count = 0;
        for(int i = 0; i < stalls.length - 1; ++i) {
            if(stalls[i]) {
                ++count;
            }
        }
        System.out.println("Count " + count);
        return count;
    }

    public static void mergeClosest(boolean[] stalls) {
        int closest = Integer.MAX_VALUE;
        int closestStart = -1;
        int closestEnd = -1;

        for(boolean b : stalls) {
            System.out.print(b ? "1" : "0");
        }
        System.out.println("Merging ");
        int firstStart = -1;
        for(int i = 0; i < stalls.length; ++i) {
            if(i > 0) {
                if(stalls[i] && !stalls[i-1]) {
                    if(firstStart != -1) {
                        if(i - firstStart < closest) {
                            closest = i - firstStart;
                            closestStart = firstStart;
                            closestEnd = i;
                            firstStart = -1;
                            continue;
                        }
                        firstStart = -1;
                    }
                }
            }
            System.out.println("here " + (i < stalls.length -1));
            if(i < stalls.length -1) {
                System.out.println("here2 " +  (stalls[i] && !stalls[i+1]));
                if(stalls[i] && !stalls[i+1]) {
                    System.out.println("Starting " + i);
                    firstStart = i;
                }
            }
        }

        if(closestStart != -1 && closestEnd != -1) {
            System.out.println("Merging " + closestStart + " and " + closestEnd);
            for(int i = closestStart + 1; i < closestEnd; ++i) {
                stalls[i] = true;
            }
        }
    }
}