/*
ID: asl_mbc2
LANG: JAVA
TASK: sprime
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class sprime {
    public static String PROB = "sprime";
    public static String INFILE = PROB + ".in";
    public static String OUTFILE = PROB + ".out";

    public static void main (String [] args) throws Exception {
        BufferedReader f = new BufferedReader(new FileReader(INFILE));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTFILE)));

        String result = solve(f);
        out.println(result);
        out.close();
    }

    static ArrayList<Integer> result = new ArrayList<>();

    public static String solve(BufferedReader f) throws Exception {
        StringTokenizer st = new StringTokenizer(f.readLine());

        int size = Integer.parseInt(st.nextToken());

        rec(0, 0, size);

        Collections.sort(result);
        String str = "";
        for(int i = 0; i < result.size(); ++i) {
            str += result.get(i);
            if(i < result.size() - 1) {
                str += '\n';
            }
        }
        return str;
    }

    static void rec(int base, int currSize, int maxSize) {
        if(currSize > maxSize) {
            return;
        }

        if(currSize == 0) {
            for(int i = 1; i <= 9; ++i) {
                int val = base * 10 + i;
                if(isPrime(val)) {
                    if(currSize + 1 == maxSize) {
                        result.add(val);
                    } else {
                        rec(val, currSize+1, maxSize);
                    }
                }
            }
        } else {
            for(int i = 1; i <= 9; i += 2) {
                int val = base * 10 + i;
                if(isPrime(val)) {
                    if(currSize + 1 == maxSize) {
                        result.add(val);
                    } else {
                        rec(val, currSize+1, maxSize);
                    }
                }
            }
        }
    }

    static boolean isPrime(int n) {
        if(n < 2) return false;
        if(n == 2) return true;
        //check if n is a multiple of 2
        if (n%2==0) return false;
        //if not, then just check the odds
        for(int i=3;i*i<=n;i+=2) {
            if(n%i==0)
                return false;
        }
        return true;
    }
}