/*
ID: asl_mbc2
LANG: JAVA
TASK: pprime
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class pprime {
    public static String PROB = "pprime";
    public static String INFILE = PROB + ".in";
    public static String OUTFILE = PROB + ".out";

    public static void main (String [] args) throws Exception {
        BufferedReader f = new BufferedReader(new FileReader(INFILE));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTFILE)));

        String result = solve(f);
        out.println(result);
        out.close();
    }
    static ArrayList<Integer> ppals;
    public static String solve(BufferedReader f) throws Exception {
        StringTokenizer st = new StringTokenizer(f.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int maxDigits = numDigits(b);
        ppals = new ArrayList<>();

        generateByLoop(a, maxDigits, b);

        Collections.sort(ppals);
        String result = "";
        for(int i = 0; i < ppals.size(); ++i) {
            result += ppals.get(i);
            if(i < ppals.size() - 1) {
                result += '\n';
            }
        }
        return result;
    }

    public static void generateByLoop(int minVal, int maxDigits, int maxVal) {
        for(int d1 = 1; d1 <= 9; d1 += 2) {
            maybeAdd(d1, minVal, maxVal);
            maybeAdd(d1*10 + d1, minVal, maxVal);
            System.out.println("before");
            if(3 > maxDigits) {
                continue;
            }
            System.out.println("before");
            for(int d2 = 0; d2 <= 9; d2 += 1) {
                int num1 = d1*100 + d2*10 + d1;
                int num2 = d1*1000 + d2*100 + d2*10 + d1;
                maybeAdd(num1, minVal, maxVal);
                maybeAdd(num2, minVal, maxVal);
                if(5 > maxDigits) {
                    continue;
                }
                for(int d3 = 0; d3 <= 9; d3 += 1) {
                    num1 = d1*10000 + d2*1000 + d3*100 + d2*10 + d1;
                    num2 = d1*100000 + d2*10000 + d3*1000 + d3*100 + d2*10 + d1;
                    maybeAdd(num1, minVal, maxVal);
                    maybeAdd(num2, minVal, maxVal);
                    if(7 > maxDigits) {
                        continue;
                    }
                    for(int d4 = 0; d4 <= 9; d4 += 1) {
                        num1 = d1*1000000 + d2*100000 + d3*10000 + d4*1000 + d3*100 + d2*10 + d1;
                        num2 = d1*10000000 + d2*1000000 + d3*100000 + d4*10000 + d4*1000 + d3*100 + d2*10 + d1;
                        maybeAdd(num1, minVal, maxVal);
                        maybeAdd(num2, minVal, maxVal);
                        if(9 > maxDigits) {
                            continue;
                        }
                        for(int d5 = 0; d5 <= 9; d5 += 1) {
                            num1 = d1*100000000 + d2*10000000 + d3*1000000 + d4*100000 + d5*10000 + d4*1000 + d3*100 + d2*10 + d1;
                            maybeAdd(num1, minVal, maxVal);
                        }
                    }
                }
            }
        }
    }

    public static void maybeAdd(int num, int minVal, int maxVal) {
        if(isPrime(num) && num >= minVal && num <= maxVal && !ppals.contains(num)) {
            ppals.add(num);
        }
    }

    public static void generateByString(String acc, int minVal, int maxDigits, int maxVal) {
        String reverse = new StringBuilder(acc).reverse().toString();
        if(acc.length()*2 + 1 <= maxDigits) {
            for(int i = 0; i <= 9; ++i) {
                int val = Integer.parseInt(acc + i + reverse);
                if(isPrime(val) && val >= minVal && val <= maxVal) {
                    if(!ppals.contains(val)) {
                        ppals.add(val);
                    }
                }
                generateByString(acc + i, minVal, maxDigits, maxVal);
            }
        }

        if(acc.length() > 0 && acc.length()*2 <= maxDigits) {
            for(int i = 0; i <= 9; ++i) {
                int val = Integer.parseInt(acc + reverse);
                if(isPrime(val) && val > minVal && val < maxVal) {
                    if(!ppals.contains(val)) {
                        ppals.add(val);
                    }
                }
            }
        }
    }

    public static void generateByNum(ArrayList<Integer> digits, int minVal, int maxDigits, int maxVal) {
        int currDigits = digits.size();
        int num = getNum(digits);
        int reverse = getReverse(digits);

        if(currDigits*2 + 1 <= maxDigits) {
            for(int i = 0; i < 10; ++i) {
                int val = num*((int)Math.pow(10, currDigits+1)) + i*((int)Math.pow(10, currDigits)) + reverse;
                if(isPrime(val) && val >= minVal && val <= maxVal && !ppals.contains(val)) {
                    ppals.add(val);
                }
                ArrayList<Integer> newDigits = new ArrayList<>(digits);
                newDigits.add(i);
                generateByNum(newDigits, minVal, maxDigits, maxVal);
            }
        }

        if(digits.size() > 0 && currDigits*2 <= maxDigits) {
            for(int i = 0; i < 10; ++i) {
                int val = num*((int)Math.pow(10, currDigits)) + reverse;
                if(isPrime(val) && val >= minVal && val <= maxVal && !ppals.contains(val)) {
                    ppals.add(val);
                }
            }
        }
    }

    static int getReverse(ArrayList<Integer> digits) {
        int result = 0;
        for(int i = digits.size() - 1; i >= 0; --i) {
            result += digits.get(i) * Math.pow(10, i);
        }
        return result;
    }

    static int getNum(ArrayList<Integer> digits) {
        int result = 0;
        for(int i = 0; i < digits.size(); ++i) {
            result = result * 10 + digits.get(i);
        }
        return result;
    }

    public static boolean isPrime(int n) {
        //check if n is a multiple of 2
        if (n%2==0) return false;
        //if not, then just check the odds
        for(int i=3;i*i<=n;i+=2) {
            if(n%i==0)
                return false;
        }
        return true;
    }

    public static int numDigits(int num) {
        int result = 0;

        while(num != 0) {
            ++result;
            num = num / 10;
        }

        return result;
    }
}