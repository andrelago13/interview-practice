/*
ID: asl_mbc2
LANG: JAVA
TASK: wormhole
*/

import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;


/*
 * I couldn't solve this one and got laze, but the idea behind it is to simply test all combinations and check for loops.
 *
 * One can check for loops by doing 12 iterations (max number of wormholes) starting in each possible wormhole and
 * checking whether we got to a border (null) or not using the nextRight value.
 */
public class wormhole {
    public static String PROB = "wormhole";
    public static String INFILE = PROB + ".in";
    public static String OUTFILE = PROB + ".out";

    public static class Wormhole {
        public Pair<Integer, Integer> coords;

        public Wormhole(int i1, int i2) {
            coords = new Pair<>(i1, i2);
        }

        public int first() {
            return coords.getKey();
        }

        public int second() {
            return coords.getValue();
        }

        public Wormhole nextRight = null;
        public Wormhole pair = null;
    }

    public static ArrayList<Wormhole> wormholes;

    public static void main (String [] args) throws Exception {
        BufferedReader f = new BufferedReader(new FileReader(INFILE));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTFILE)));

        String result = solve(f);
        out.println(result);
        out.close();
    }

    public static String solve(BufferedReader f) throws Exception {
        StringTokenizer st = new StringTokenizer(f.readLine());

        int numWormholes = Integer.parseInt(st.nextToken());
        wormholes = new ArrayList<>();
        for(int i = 0; i < numWormholes; ++i) {
            st = new StringTokenizer(f.readLine());
            wormholes.add(new Wormhole(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
//        Collections.sort(wormholes, new Comparator<Wormhole>() {
//            @Override
//            public int compare(Wormhole o1, Wormhole o2) {
//                return ((Integer) o1.first()).compareTo(o2.first()) ;
//            }
//        });

        for(Wormhole w : wormholes) {
            for (int i = 0; i < wormholes.size(); ++i) {
                if(wormholes.get(i) == w) {
                    continue;
                }
                if(wormholes.get(i).second() == w.second()) {
                    if(wormholes.get(i).first() > w.first()) {
                        if(w.nextRight == null || wormholes.get(i).first() > w.nextRight.first()) {
                            w.nextRight = wormholes.get(i);
                        }
                    }
                }
            }
        }


        return "" + findPairingLoops();
    }

    public static int findPairingLoops() {
        int result = 0;
        Wormhole first = null;
        for(Wormhole w : wormholes) {
            if(w.pair == null) {
                first = w;
            }
        }
        if(first == null) {
            System.out.println("SET");
            if(hasLoop()) {
                return 1;
            }
            return 0;
        }

        for(Wormhole w : wormholes) {
            if(w.pair == null) {
                w.pair = first;
                first.pair = w;
                result += findPairingLoops();
                first.pair = null;
                w.pair = null;
            }
        }

        return result;
    }

    public static boolean hasLoop() {
        /*for(Wormhole w : wormholes) {
            Wormhole pos = w;
            for(int i = 0; i <= 12; ++i) {
                if(pos.nextRight == null) {
                    //System.out.println("NO LOOP");
                    break;
                }
                pos = pos.nextRight;
            }
            return true;
        }*/
        //System.out.println("LOOP");
        return false;
    }

    public static int combination(int n, int k) {
        return permutation(n) / (permutation(k) * permutation(n - k));
    }

    public static int permutation(int i) {
        if (i == 1) {
            return 1;
        }
        return i * permutation(i - 1);
    }
}