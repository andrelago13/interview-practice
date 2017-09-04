/*
ID: asl_mbc2
LANG: JAVA
TASK: ride
*/

import java.io.*;
import java.util.StringTokenizer;

public class ride {

    public static String PROB = "ride";
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
        String comet = st.nextToken();
        st = new StringTokenizer(f.readLine());
        String group = st.nextToken();

        int cometVal = calcProd(comet.toUpperCase());
        int groupVal = calcProd(group.toUpperCase());

        if(cometVal % 47 == groupVal % 47) {
            return "GO";
        }

        return "STAY";
    }

    public static int calcProd(String name) {
        int base = (int) 'A';
        int prod = 1;

        for(int i = 0; i < name.length(); ++i) {
            prod *= ((int) name.charAt(i)) - base + 1;
        }

        return prod;
    }
}