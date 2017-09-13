/*
ID: asl_mbc2
LANG: JAVA
TASK: castle
*/

import util.Exercise;

import java.io.BufferedReader;
import java.util.StringTokenizer;

public class castle extends Exercise {
    public static String PROB = "castle";
    public static String INFILE = PROB + ".in";
    public static String OUTFILE = PROB + ".out";

    public static void main (String [] args) throws Exception {
        testMain();
    }

    public static String solve(BufferedReader f) throws Exception {
        StringTokenizer st = new StringTokenizer(f.readLine());

        int i1 = Integer.parseInt(st.nextToken());
        int i2 = Integer.parseInt(st.nextToken());

        return "";
    }
}