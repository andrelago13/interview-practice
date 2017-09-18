/*
ID: asl_mbc2
LANG: JAVA
TASK: frac1
*/

import java.io.*;
import java.util.*;

/**
 * Although this code does not pass the corrector, the logic is the same as the code that passes. I am not sure what the
 * mistake is, but is likely to be related to I/O (e.g. maybe I shouldn't create the result string that way).
 */
public class frac1 {
    public static String PROB = "frac1";
    public static String INFILE = PROB + ".in";
    public static String OUTFILE = PROB + ".out";

    public static class Frac {
        int num;
        int denum;
        double val;

        public Frac(int num, int denum) {
            this.num = num;
            this.denum = denum;
            val = ((double) num) / denum;
        }

        public String toString() {
            return "" + num + "/" + denum;
        }

        public boolean isReductible() {
            if(num == 0)
                return denum > 1;
            long gcd = gcd(num, denum);
            return (num != 1) && (denum != 1) && gcd > 1;
        }

        public double value() {
            return val;
        }

        public static Comparator<Frac> comp = new Comparator<Frac>() {
            @Override
            public int compare(Frac o1, Frac o2) {
                return Double.compare(o1.value(), o2.value());
            }
        };
    }

    public static long lcm(long a, long b) {
        return a * (b / gcd(a, b));
    }

    public static long gcd(long a, long b) {
        while (b > 0) {
            long temp = b;
            b = a % b; // % is remainder
            a = temp;
        }
        return a;
    }

    public static void main (String [] args) throws Exception {
        BufferedReader f = new BufferedReader(new FileReader(INFILE));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTFILE)));

        String result = solve(f);
        out.println(result);
        out.close();
    }

    public static String solve(BufferedReader f) throws Exception {
        StringTokenizer st = new StringTokenizer(f.readLine());

        int max = Integer.parseInt(st.nextToken());
        ArrayList<Frac> result = new ArrayList<>();

        result.add(new Frac(0, 1));
        for(int num = 1; num <= max; ++num) {
            for(int denum = num; denum <= max; ++denum) {
                if (gcd(num, denum) == 1) {
                    result.add(new Frac(num, denum));
                }
//                Frac frac = new Frac(num, denum);
//                if(!frac.isReductible()) {
//                    result.add(frac);
                }
            }

        Collections.sort(result, Frac.comp);
        String res = "";
        for(int i = 0; i < result.size(); ++i) {
            res += result.get(i);
            if(i < result.size() - 1) {
                res += '\n';
            }
        }
        return res;
    }
}