/*
ID: asl_mbc2
LANG: JAVA
TASK: castle
*/

import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * I didn't quite finish this test, but it was really close. The main idea is that we flood fill to get the cells of
 * each room. Then, for each possible pair of rooms, if their combined size is largest than the current largest possible
 * room, we try to find a demolishable wall and set that.
 */
public class castle {
    public static String PROB = "castle";
    public static String INFILE = PROB + ".in";
    public static String OUTFILE = PROB + ".out";

    public static void main (String [] args) throws Exception {
        BufferedReader f = new BufferedReader(new FileReader(INFILE));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTFILE)));

        String result = solve(f);
        out.println(result);
        out.close();
    }

    static class Room {
        int size = 0;
        boolean[][] cells = new boolean[width][];

        public Room() {
            for(int x = 0; x < width; ++x) {
                cells[x] = new boolean[height];
                for(int y = 0; y < height; ++y) {
                    cells[x][y] = false;
                }
            }
        }

        public void addCell(int x, int y) {
            ++size;
            cells[x][y] = true;
        }

        public int getSize() {
            return size;
        }

        public boolean hasCell(int x, int y) {
            if(x < 0 || x >= width || y < 0 || y >= height)
                return false;
            return cells[x][y];
        }

        public boolean isNeighbour(Room r) {
            boolean found = false;
            for(int x = 0; x < width; ++x) {
                for(int y = 0; y < height; ++y) {
                    if(cells[x][y]) {
                        if(hasBottomWall(matrix[x][y]) && r.hasCell(x, y+1)) {
                            found = true;
                            if(x < demolishX) {
                                demolishX = x;
                                demolishY = y+1;
                                demolishWall = 'N';
                            } else if (x == demolishX) {
                                if(y+1 > demolishY) {
                                    demolishX = x;
                                    demolishY = y+1;
                                    demolishWall = 'N';
                                } else if (y+1 == demolishY) {
                                    demolishWall = 'N';
                                }
                            }
                        }
                        if(hasTopWall(matrix[x][y]) && r.hasCell(x, y-1)) {
                            found = true;
                            if(x < demolishX) {
                                demolishX = x;
                                demolishY = y;
                                demolishWall = 'N';
                            } else if (x == demolishX) {
                                if(y+1 > demolishY) {
                                    demolishX = x;
                                    demolishY = y;
                                    demolishWall = 'N';
                                } else if (y+1 == demolishY) {
                                    demolishWall = 'N';
                                }
                            }
                        }
                        if(hasRightWall(matrix[x][y]) && r.hasCell(x+1, y)) {
                            found = true;
                            if(x < demolishX) {
                                demolishX = x;
                                demolishY = y;
                                demolishWall = 'E';
                            } else if (x == demolishX) {
                                if(y+1 > demolishY) {
                                    demolishX = x;
                                    demolishY = y;
                                    demolishWall = 'E';
                                } else if (y+1 == demolishY) {
                                    demolishWall = 'N';
                                }
                            }
                        }
                        if(hasLeftWall(matrix[x][y]) && r.hasCell(x-1, y)) {
                            found = true;
                            if(x < demolishX) {
                                demolishX = x-1;
                                demolishY = y;
                                demolishWall = 'E';
                            } else if (x == demolishX) {
                                if(y+1 > demolishY) {
                                    demolishX = x-1;
                                    demolishY = y;
                                    demolishWall = 'E';
                                } else if (y+1 == demolishY) {
                                    demolishWall = 'N';
                                }
                            }
                        }
                    }
                }
            }
            return found;
        }
    }

    static int width, height;
    static int[][] matrix;
    static boolean[][] visited;
    static ArrayList<Room> rooms = new ArrayList<>();
    static Room currentRoom;
    static int largestRoom = 0;
    static int largestPossibleRoom = 0;
    static int demolishX = 1000;
    static int demolishY = -1;
    static char demolishWall = ' ';

    public static String solve(BufferedReader f) throws Exception {
        StringTokenizer st = new StringTokenizer(f.readLine());

        width = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());

        matrix = new int[width][];
        visited = new boolean[width][];
        for(int i = 0; i < width; ++i) {
            matrix[i] = new int[height];
            visited[i] = new boolean[height];
        }

        for(int y = 0; y < height; ++y) {
            st = new StringTokenizer(f.readLine());
            for(int x = 0; x < width; ++x) {
                matrix[x][y] = Integer.parseInt(st.nextToken());
                visited[x][y] = false;
            }
        }

        Pair<Integer, Integer> cell = findUnvisited();
        while(cell != null) {
            currentRoom = new Room();
            explore(cell.getKey(), cell.getValue());
            rooms.add(currentRoom);
            if(currentRoom.getSize() > largestRoom) {
                largestRoom = currentRoom.getSize();
            }
            cell = findUnvisited();
        }

        computeRooms();

        String result = "" + rooms.size() + '\n';
        result += "" + largestRoom + '\n';
        result += "" + largestPossibleRoom + '\n';
        result += "" + (demolishY+1) + " " + (demolishX+1) + " " + demolishWall;
        return "" + result;
    }

    static void computeRooms() {
        for(int i = 0; i < rooms.size()-1; ++i) {
            for(int j = 1; j < rooms.size(); ++j) {
                Room r1 = rooms.get(i);
                Room r2 = rooms.get(j);
                if(r1.getSize() + r2.getSize() < largestPossibleRoom) {
                    continue;
                }
                if(r1.isNeighbour(r2)) {
                    largestPossibleRoom = r1.getSize() + r2.getSize();
                }
            }
        }
    }

    static void explore(int x, int y) {
        if(x < 0 || x >= width || y < 0 || y >= height || visited[x][y])
            return;
        currentRoom.addCell(x, y);
        visited[x][y] = true;
        int val = matrix[x][y];

        if(!hasLeftWall(val)) {
            explore(x-1, y);
        }
        if(!hasRightWall(val)) {
            explore(x+1, y);
        }
        if(!hasTopWall(val)) {
            explore(x, y-1);
        }
        if(!hasBottomWall(val)) {
            explore(x, y+1);
        }
    }

    static Pair<Integer, Integer> findUnvisited() {
        for(int y = 0; y < height; ++y) {
            for(int x = 0; x < width; ++x) {
                if(!visited[x][y])
                    return new Pair<>(x, y);
            }
        }
        return null;
    }

    static boolean hasLeftWall(int val) {
        if(val >= 8) {
            val -= 8;
        }
        if(val >= 4) {
            val -= 4;
        }
        if(val >= 2) {
            val -= 2;
        }
        return val >= 1;
    }

    static boolean hasRightWall(int val) {
        if(val >= 8) {
            val -= 8;
        }
        return val >= 4;
    }

    static boolean hasTopWall(int val) {
        if(val >= 8) {
            val -= 8;
        }
        if(val >= 4) {
            val -= 4;
        }
        return val >= 2;
    }

    static boolean hasBottomWall(int val) {
        return val >= 8;
    }
}