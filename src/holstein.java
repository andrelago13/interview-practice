/*
ID: asl_mbc2
LANG: JAVA
TASK: holstein
*/

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class holstein {
    public static String PROB = "holstein";
    public static String INFILE = PROB + ".in";
    public static String OUTFILE = PROB + ".out";

    public static class FeedType {
        public int content[];
    }

    public static void main (String [] args) throws Exception {
        BufferedReader f = new BufferedReader(new FileReader(INFILE));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTFILE)));

        String result = solve(f);
        out.println(result);
        out.close();
    }

    static int requirements[];
    static ArrayList<FeedType> feeds = new ArrayList<>();
    static ArrayList<Boolean> best = new ArrayList<>();
    static int bestCount = 1000;

    public static String solve(BufferedReader f) throws Exception {
        StringTokenizer st = new StringTokenizer(f.readLine());

        int numVitaminTypes = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(f.readLine());
        requirements = new int[numVitaminTypes];
        for(int i = 0; i < numVitaminTypes; ++i) {
            requirements[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(f.readLine());
        int numFeeds = Integer.parseInt(st.nextToken());
        for(int feed = 0; feed < numFeeds; ++feed) {
            st = new StringTokenizer(f.readLine());
            FeedType fd = new FeedType();
            fd.content = new int[numVitaminTypes];
            for(int i = 0; i < numVitaminTypes; ++i) {
                fd.content[i] = Integer.parseInt(st.nextToken());
            }
            feeds.add(fd);
        }

        ArrayList<Boolean> contain = new ArrayList<>();
        combo(contain);
        contain = best;

        ArrayList<Integer> result = new ArrayList<>();
        for(int i = 0; i < contain.size(); ++i) {
            if(contain.get(i)) {
                result.add(i+1);
            }
        }
        String res = "" + result.size();
        for(int i = 0; i < result.size(); ++i) {
            res += " " + result.get(i);
            /*if(i < result.size() - 1) {
                res += " ";
            }*/
        }
        return res;
    }

    public static void combo(ArrayList<Boolean> contain) {
        int pos = contain.size();
        if(pos == feeds.size()) {
            return;
        }

        contain.add(true);
        test(contain);
        // test satisfies

        contain.remove(pos);
        contain.add(false);
        test(contain);
        // test satisfies

        contain.remove(pos);
    }

    public static void test(ArrayList<Boolean> contain) {
        if(satisfies(contain)) {
            int count = 0;
            for(boolean b : contain) {
                count += b ? 1 : 0;
            }
            if(count < bestCount) {
                best = new ArrayList<>(contain);
                bestCount = count;
            }
        } else {
            combo(contain);
        }
    }

    public static boolean satisfies(ArrayList<Boolean> contain) {
        int fulfilled[] = new int[requirements.length];
        for(int i = 0; i < contain.size(); ++i) {
            if(contain.get(i)) {
                FeedType f = feeds.get(i);
                for(int v = 0; v < f.content.length; ++v) {
                    fulfilled[v] += f.content[v];
                }
            }
        }

        for(int i = 0; i < requirements.length; ++i) {
            //System.out.println("check " + fulfilled[i]);
            if(fulfilled[i] < requirements[i]) {
                return false;
            }
        }

        return true;
    }
}