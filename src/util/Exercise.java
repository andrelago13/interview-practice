package util;/*
ID: asl_mbc2
LANG: JAVA
TASK: test
*/

import java.io.*;
import java.util.StringTokenizer;

public class Exercise {
    public static String PROB = "test";
    public static String INFILE = PROB + ".in";
    public static String OUTFILE = PROB + ".out";

    public static void main (String [] args) throws Exception {
        testMain();
    }

    public static void testMain() {
        try {
            BufferedReader f = new BufferedReader(new FileReader(INFILE));
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTFILE)));

            String result = solve(f);
            out.println(result);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String solve(BufferedReader f) throws Exception {
        StringTokenizer st = new StringTokenizer(f.readLine());

        int i1 = Integer.parseInt(st.nextToken());
        int i2 = Integer.parseInt(st.nextToken());

        return "";
    }
}