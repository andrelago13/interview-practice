package chapter1;/*
ID: asl_mbc2
LANG: JAVA
TASK: chapter1.milk3
*/

import util.Exercise;

import java.io.*;
import java.util.*;

public class milk3 extends Exercise {
    public static String PROB = "chapter1.milk3";
    public static String INFILE = PROB + ".in";
    public static String OUTFILE = PROB + ".out";

    public static void main (String [] args) throws Exception {
        testMain();
    }

    public static class Combo {
        public int a;
        public int b;
        public int c;

        public boolean explored = false;

        public Combo(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public String toString() {
            return "" + a + " " + b + " " + c;
        }
    }

    public static class ComboSet {
        public ArrayList<Combo> combos;

        public ComboSet() {
            combos = new ArrayList<>();
        }

        public void add(Combo c) {
            for(Combo cc : combos) {
                if(cc.a == c.a && cc.b == c.b && cc.c == c.c) {
                    return;
                }
            }
            combos.add(c);
        }

        public Combo getUnexplored() {
            for(Combo cc : combos) {
                if(!cc.explored) {
                    return cc;
                }
            }
            return null;
        }
    }

    public static String solve(BufferedReader f) throws Exception {
        StringTokenizer st = new StringTokenizer(f.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        boolean ammounts[] = new boolean[c+1];

        ComboSet set = new ComboSet();
        set.add(new Combo(0, 0, c));
        while(true) {
            Combo combo = set.getUnexplored();
            if(combo == null) {
                break;
            }
            System.out.println("Exploring " + combo.toString());
            combo.explored = true;

            if(combo.a == 0) {
                ammounts[combo.c] = true;
            }

            if(combo.a > 0) {
                int diff = b - combo.b;
                int min = Math.min(diff, combo.a);
                set.add(new Combo(combo.a - min, combo.b + min, combo.c));

                diff = c - combo.c;
                min = Math.min(diff, combo.a);
                set.add(new Combo(combo.a - min, combo.b, combo.c + min));
            }

            if(combo.b > 0) {
                int diff = a - combo.a;
                int min = Math.min(diff, combo.b);
                set.add(new Combo(combo.a + min, combo.b - min, combo.c));

                diff = c - combo.c;
                min = Math.min(diff, combo.b);
                set.add(new Combo(combo.a, combo.b - min, combo.c + min));
            }

            if(combo.c > 0) {
                int diff = a - combo.a;
                int min = Math.min(diff, combo.c);
                set.add(new Combo(combo.a + min, combo.b, combo.c - min));

                diff = b - combo.b;
                min = Math.min(diff, combo.c);
                set.add(new Combo(combo.a, combo.b + min, combo.c - min));
            }
        }

        String result = "";
        for(int i = 0; i <= c; ++i) {
            if(!ammounts[i]) {
                continue;
            }
            if(result.isEmpty()) {
                result += "" + i;
            } else {
                result += " " + i;
            }
        }
        return result;
    }
}