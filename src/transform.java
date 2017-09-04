/*
ID: asl_mbc2
LANG: JAVA
TASK: transform
*/

import java.io.*;
import java.util.StringTokenizer;

public class transform {
    public static String PROB = "test";
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

        int i1 = Integer.parseInt(st.nextToken());
        int i2 = Integer.parseInt(st.nextToken());

        return "" + (i1+i2);
    }
}