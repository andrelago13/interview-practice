package chapter1;/*
ID: asl_mbc2
LANG: JAVA
TASK: chapter1.milk
*/

import javafx.util.Pair;
import util.Exercise;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class milk extends Exercise {
    public static String PROB = "chapter1.milk";
    public static String INFILE = PROB + ".in";
    public static String OUTFILE = PROB + ".out";

    public static void main (String [] args) throws Exception {
        testMain();
    }

    public static String solve(BufferedReader f) throws Exception {
        StringTokenizer st = new StringTokenizer(f.readLine());

        int ammountWanted = Integer.parseInt(st.nextToken());
        int numFarmers = Integer.parseInt(st.nextToken());
        ArrayList<Pair<Integer, Integer>> farmers = new ArrayList<>();
        for(int i = 0; i < numFarmers; ++i) {
            st = new StringTokenizer(f.readLine());
            farmers.add(new Pair<>(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(farmers, new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                if(o1.getKey() < o2.getKey()) {
                    return -1;
                } else if (o1.getKey() > o2.getKey()) {
                    return 1;
                }
                return 0;
            }
        });

        int min = 0;
        int ammountLeft = ammountWanted;
        for(Pair<Integer, Integer> farmer : farmers) {
            int ammount = farmer.getValue();
            if(ammount > ammountLeft) {
                ammount = ammountLeft;
            }
            ammountLeft -= ammount;

            min += ammount * farmer.getKey();

            if(ammountLeft <= 0) {
                break;
            }
        }
        return "" + min;
    }
}