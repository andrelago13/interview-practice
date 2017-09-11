/*
ID: asl_mbc2
LANG: JAVA
TASK: crypt1
*/

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class crypt1 {
    public static String PROB = "crypt1";
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

        int numDigits = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(f.readLine());
        ArrayList<Integer> digits = new ArrayList<>();
        for(int i = 0; i < numDigits; ++i) {
            digits.add(Integer.parseInt(st.nextToken()));
        }

        int numSolutions = 0;

        for(Integer a : digits) {
            for(Integer b : digits) {
                for(Integer c : digits) {
                    for(Integer d : digits) {
                        for(Integer e : digits) {
                            int num1 = (a * 100 + b * 10 + c) * d;
                            int num2 = (a * 100 + b * 10 + c) * e;
                            int num3 = (a * 100 + b * 10 + c) * (d * 10 + e);
                            if(num1 > 999 || num2 > 999 || num3 > 9999) {
                                continue;
                            }
                            if (numIsInDigits(num1, digits) && numIsInDigits(num2, digits) && numIsInDigits(num3, digits)) {
                                numSolutions++;
                                System.out.println("" + a + " " + b + " " + c + " " + d + " " + e + " " + num1 + " " + num2 + " " + num3);
                            }
                        }
                    }
                }
            }
        }

        return "" + numSolutions;
    }

    public static boolean numIsInDigits(int num, ArrayList<Integer> digits) {
        while(num > 0) {
            int digit = num % 10;
            num = num / 10;
            if(!digits.contains(digit)) {
                return false;
            }
        }
        return true;
    }
}