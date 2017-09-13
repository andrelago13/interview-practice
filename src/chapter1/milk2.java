package chapter1;/*
ID: asl_mbc2
LANG: JAVA
TASK: chapter1.milk2
*/

import javafx.util.Pair;
import util.Exercise;

import java.io.*;
import java.util.*;

public class milk2 extends Exercise {
    public static String PROB = "chapter1.milk2";
    public static String INFILE = PROB + ".in";
    public static String OUTFILE = PROB + ".out";

    public static void main (String [] args) throws Exception {
        testMain();
    }

    private static int milkStreak = 0;
    private static int dryStreak = 0;

    public static String solve(BufferedReader f) throws Exception {
        StringTokenizer st = new StringTokenizer(f.readLine());
        int farmers = Integer.parseInt(st.nextToken());
        ArrayList<Pair<Integer, Integer>> times = new ArrayList();

        for(int i = 0; i < farmers; ++i) {
            st = new StringTokenizer(f.readLine());
            int t1 = Integer.parseInt(st.nextToken());
            int t2 = Integer.parseInt(st.nextToken());
            times.add(new Pair<>(t1, t2));
        }

        while(makeChanges(times)) {}

        Collections.sort(times, new Comparator<Pair<Integer, Integer>>() {
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

        for(Pair<Integer, Integer> p : times) {
            int diff = p.getValue() - p.getKey();
            if(diff > milkStreak) {
                milkStreak = diff;
            }
        }

        for(int i = 0; i < times.size() - 1; ++i) {
            int diff = times.get(i+1).getKey() - times.get(i).getValue();
            if(diff > dryStreak) {
                dryStreak = diff;
            }
        }

        return "" + milkStreak + " " + dryStreak;
    }

    public static boolean makeChanges(ArrayList<Pair<Integer, Integer>> times) {
        for(int i = 0; i < times.size(); ++i) {
            Pair time = times.get(i);
            for(int j = i + 1; j < times.size(); ++j) {
                Pair comp = times.get(j);
                Pair p = merge(time, comp);
                if(p != null) {
                    times.remove(j);
                    times.remove(i);
                    times.add(p);
                    return true;
                }
            }
        }
        return false;
    }

    public static Pair<Integer, Integer> merge(Pair<Integer, Integer> t1, Pair<Integer, Integer> t2) {
        int t1_1 = t1.getKey();
        int t1_2 = t1.getValue();
        int t2_1 = t2.getKey();
        int t2_2 = t2.getValue();

        if(t1_1 <= t2_1) {
            if(t2_2 <= t1_2) {
                return new Pair<>(t1_1, t1_2);
            }
            if (t2_1 <= t1_2) {
                return new Pair<>(t1_1, t2_2);
            }
        }
        if(t2_1 <= t1_1) {
            if(t1_2 <= t2_2) {
                return new Pair<>(t2_1, t2_2);
            }
            if(t1_1 <= t2_2) {
                return new Pair<>(t2_1, t1_2);
            }
        }

        return null;
    }

    public static String solve2(BufferedReader f) throws Exception {
        StringTokenizer st = new StringTokenizer(f.readLine());
        int farmers = Integer.parseInt(st.nextToken());
        Set<Pair<Integer, Integer>> times = new HashSet();

        int firstMilk = -1;
        int lastMilk = -1;
        for(int i = 0; i < farmers; ++i) {
            st = new StringTokenizer(f.readLine());
            int t1 = Integer.parseInt(st.nextToken());
            int t2 = Integer.parseInt(st.nextToken());
            times.add(new Pair<>(t1, t2));
            if(firstMilk == -1 || t1 < firstMilk) {
                firstMilk = t1;
            }
            if(t2 > lastMilk) {
                lastMilk = t2;
            }
        }

        int longestMilkStreak = 0;
        int longestDryStreak = 0;
        int currStreak = 0;
        for(int time = firstMilk; time <= lastMilk + 1; ++time) {
            if(anyMilking(times, time)) {
                ++currStreak;
            } else {
                if(currStreak > longestMilkStreak) {
                    longestMilkStreak = currStreak;
                }
                currStreak = 0;
            }
        }
        currStreak = 0;
        for(int time = firstMilk; time <= lastMilk; ++time) {
            if(!anyMilking(times, time)) {
                ++currStreak;
            } else {
                if(currStreak > longestDryStreak) {
                    longestDryStreak = currStreak;
                }
                currStreak = 0;
            }
        }

        return "" + longestMilkStreak + " " + longestDryStreak;
    }

    static boolean anyMilking(Set<Pair<Integer, Integer>> times, int time) {
        for(Pair<Integer, Integer> p : times) {
            if(time >= p.getKey() && time < p.getValue()) {
                return true;
            }
        }
        return false;
    }
}