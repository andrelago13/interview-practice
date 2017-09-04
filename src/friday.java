/*
ID: asl_mbc2
LANG: JAVA
TASK: friday
*/

import java.io.*;
import java.util.StringTokenizer;

public class friday {
    public static String PROB = "friday";
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
        int years = Integer.parseInt(st.nextToken());

        int freq[] = {0, 0, 0, 0, 0, 1, 0};

        int year = 1900;
        int day = 12;

        while(year < 1900 + years) {
            for(int month = 1; month <= 12; ++month) {
                if(year == 1900 + years - 1 && month == 12) {
                    break;
                }
                day += getMonthDays(year, month);
                System.out.println("" + (day%7));
                freq[day%7]++;
            }

            ++year;
        }

        String result = "" + freq[5] + " " + freq[6] + " " + freq[0] + " " + freq[1] + " " + freq[2] + " " + freq[3] + " " + freq[4];
        return result;
    }

    public static int getMonthDays(int year, int month) {
        if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            return 31;
        }

        if(month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        }

        if(isLeap(year)) {
            return 29;
        }
        return 28;
    }

    public static boolean isLeap(int year) {
        if(year % 100 == 0) {
            if(year % 400 == 0) {
                return true;
            }
            return false;
        }

        return year % 4 == 0;
    }
}