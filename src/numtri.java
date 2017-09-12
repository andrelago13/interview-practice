/*
ID: asl_mbc2
LANG: JAVA
TASK: numtri
*/

import java.io.*;
import java.util.StringTokenizer;

public class numtri {
    public static String PROB = "numtri";
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

        int rows = Integer.parseInt(st.nextToken());
        int mat[][] = new int[rows][];
        int maxSum[][] = new int[rows][];

        for(int i = 0; i < rows; ++i) {
            st = new StringTokenizer(f.readLine());
            int row[] = new int[i+1];
            int sum[] = new int[i+1];
            for(int j = 0; j < i + 1; ++j) {
                row[j] = Integer.parseInt(st.nextToken());
                sum[j] = 0;
                if(i == rows - 1) {
                    sum[j] = row[j];
                }
            }
            mat[i] = row;
            maxSum[i] = sum;
        }

        for(int row = rows - 1; row >= 0; --row) {
            for(int col = 0; col <= row; ++col) {
                if(row > 0) {
                    if(col > 0) {
                        int leftVal = mat[row-1][col-1] + maxSum[row][col];
                        if(leftVal > maxSum[row-1][col-1]) {
                            maxSum[row-1][col-1] = leftVal;
                        }
                    }

                    if(col < row) {
                        int topVal = mat[row-1][col] + maxSum[row][col];
                        if(topVal > maxSum[row-1][col]) {
                            maxSum[row-1][col] = topVal;
                        }
                    }
                }
            }
        }

        return "" + maxSum[0][0];
    }
}