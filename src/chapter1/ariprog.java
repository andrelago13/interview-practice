package chapter1;/*
ID: asl_mbc2
LANG: JAVA
TASK: chapter1.ariprog
*/

import javafx.util.Pair;
import util.Exercise;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class ariprog extends Exercise {
    public static String PROB = "chapter1.ariprog";
    public static String INFILE = PROB + ".in";
    public static String OUTFILE = PROB + ".out";

    public static void main (String [] args) throws Exception {
        testMain();
    }

    public static String solve(BufferedReader f) throws Exception {
        StringTokenizer st = new StringTokenizer(f.readLine());

        int maxLength = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(f.readLine());
        int maxNum = Integer.parseInt(st.nextToken());

        ArrayList<Pair<Integer, Integer>> sequences = new ArrayList<>();


        ArrayList<Integer> bisquares = new ArrayList<>();
        int max = 0;
        boolean contains[] = new boolean[2 * maxNum * maxNum + 1];

        for(int p = 0; p <= maxNum; ++p) {
            for(int q = 0; q <= maxNum; ++q) {
                int val = p*p + q*q;
                if(!bisquares.contains(val)) {
                    System.out.println("" + val);
                    bisquares.add(val);
                    contains[val] = true;
                }
                if(val > max) {
                    max = val;
                }
            }
        }
        Collections.sort(bisquares);

        for(int i = 0; i < bisquares.size(); ++i) {
            int start = bisquares.get(i);

            for(int j = i + 1; j < bisquares.size(); ++j) {
                int diff = bisquares.get(j) - start;

                boolean bad = false;
                for(int n = maxLength - 1; n >= 0; --n) {
                    if((start + diff*n >= contains.length) || (!contains[start + diff*n])) {
                        bad = true;
                        break;
                    }
                }

                if(!bad) {
                    sequences.add(new Pair<>(start, diff));
                }
            }
        }







        String result = "";
        if(sequences.isEmpty()) {
            result = "NONE";
        }
        Collections.sort(sequences, new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                if(o1.getValue() < o2.getValue()) {
                    return -1;
                }
                if(o1.getValue() > o2.getValue()) {
                    return 1;
                }

                if(o1.getKey() < o2.getKey()) {
                    return -1;
                }
                if(o1.getKey() > o2.getKey()) {
                    return 1;
                }

                return 0;
            }
        });
        for(int i = 0; i < sequences.size(); ++i) {
            Pair p = sequences.get(i);
            result += p.getKey() + " " + p.getValue();
            if(i < sequences.size() - 1) {
                result += '\n';
            }
        }
        return result;
    }
}