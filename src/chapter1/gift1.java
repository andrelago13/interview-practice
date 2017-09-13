package chapter1;/*
ID: asl_mbc2
LANG: JAVA
TASK: chapter1.gift1
*/

import util.Exercise;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class gift1 extends Exercise {
    public static String PROB = "chapter1.gift1";
    public static String INFILE = PROB + ".in";
    public static String OUTFILE = PROB + ".out";

    public static void main (String [] args) throws Exception {
        testMain();
    }

    public static String solve(BufferedReader f) throws Exception {
        StringTokenizer st = new StringTokenizer(f.readLine());
        int numberPersons = Integer.parseInt(st.nextToken());
        ArrayList<String> people = new ArrayList<>();

        HashMap<String, Integer> initialMoney = new HashMap<>();
        HashMap<String, Integer> receives = new HashMap<>();
        HashMap<String, Integer> gives = new HashMap<>();

        for(int i = 0; i < numberPersons; ++i) {
            st = new StringTokenizer(f.readLine());
            String person = st.nextToken();
            people.add(person);
            receives.put(person, 0);
            gives.put(person, 0);
        }

        String line = f.readLine();
        while(line != null) {
            String person = line;
            st = new StringTokenizer(f.readLine()); // money and num
            int ammount = Integer.parseInt(st.nextToken());
            initialMoney.put(person, ammount);
            int friends = Integer.parseInt(st.nextToken());
            System.out.println(person + " starts " + ammount + " (" + friends);
            if(friends == 0) {
                line = f.readLine();
                continue;
            }
            int parcel = Math.floorDiv(ammount, friends);
            gives.put(person, parcel*friends);

            for(int i = 0; i < friends; ++i) {
                String receiver = f.readLine();
                receives.put(receiver, parcel + receives.get(receiver));
                System.out.println(person + " gives " + parcel + " to " + receiver);
            }

            line = f.readLine();
        }

        String result = "";

        for(int i = 0; i < people.size(); ++i) {
            String person = people.get(i);
            result += person + " " + (receives.get(person)- gives.get(person));
            if(i < people.size() - 1) {
                result += '\n';
            }
        }
        return result;
    }
}