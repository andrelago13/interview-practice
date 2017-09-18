/*
ID: asl_mbc2
LANG: JAVA
TASK: frac1
*/

import java.io.*;
import java.util.*;

public class frac1 {
    public static String PROB = "frac1";
    public static String INFILE = PROB + ".in";
    public static String OUTFILE = PROB + ".out";

    public static class Frac {
        int num;
        int denum;

        public Frac(int num, int denum) {
            this.num = num;
            this.denum = denum;
        }

        public String toString() {
            return "" + num + "/" + denum;
        }

        public boolean isReductible() {
            System.out.println("" + num + "/" + denum + " " + lcm(num, denum) + " " + ((num != 1) && (denum != 1) && lcm(num, denum) < Math.min(num, denum)));
            return (num != 1) && (denum != 1) && lcm(num, denum) < Math.min(num, denum);
        }

        public double value() {
            return ((double) num) / ((double) denum);
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
        HashSet<Double> values = new HashSet<>();

        for(int num = 0; num <= max; ++num) {
            for(int denum = 1; denum <= max; ++denum) {
                Frac frac = new Frac(num, denum);
                double val = frac.value();
                if(!values.contains(val) && val <= 1) {
                    result.add(frac);
                    values.add(val);
                }
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