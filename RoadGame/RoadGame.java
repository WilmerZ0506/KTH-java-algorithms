import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class RoadGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in); //Initiate scanner
        String[] gameSize = scanner.nextLine().split(" "); //Game size
        int M = Integer.parseInt(gameSize[0]); //Rows
        int N = Integer.parseInt(gameSize[1]); //Columns

        int Xamount = 0; //Amount of X's
        int Oamount = 0; //Amount of O's

        char[][] grid = new char[M][N]; //Game grid
        for (int i = 0; i < M; i++) {
            String row = scanner.nextLine(); //Read row
            for (int j = 0; j < N; j++) {
                grid[i][j] = row.charAt(j); //Fill grid
                if (grid[i][j] == 'x') { //Count X's
                    Xamount++;
                } else if (grid[i][j] == 'o') { //Count O's
                    Oamount++;
                }
            }
        }
        scanner.close();

        boolean Xwins = breadthFirstSearch(grid, M, N, 'x'); //Check if X wins
        boolean Owins = breadthFirstSearch(grid, M, N, 'o'); //Check if O wins

        boolean valid = (Xamount == Oamount || Xamount == 1 || Oamount == 1); //Check if the game state is valid

        if (!valid || Xwins && Owins) { //Check if the game state is invalid
            System.out.println("fusk");
            return;
        }

        if (Xwins) {
            System.out.println("x"); // X wins
        } else if (Owins) {
            System.out.println("o"); // O wins
        } else {
            System.out.println("ingen"); // No winner
        }

    }

    public static boolean breadthFirstSearch(char[][] grid, int M, int N, char player) {
    boolean[][] visited = new boolean[M][N]; //Visited positions
    Queue<int[]> queue = new LinkedList<>(); //Positions to explore

    if (player == 'x') {
        for (int i = 0; i < N; i++) { //Check all columns in the first row
            if (grid[0][i] == player) { //Check if the position is occupied by the player
                queue.offer(new int[]{0, i}); //Add position to the queue
                visited[0][i] = true; //Mark position as visited
            }
        }
    } else if (player == 'o') {
        for (int j = 0; j < M; j++) { //Check all rows in the first column
            if (grid[j][0] == player) { //Check if the position is occupied by the player
                queue.offer(new int[]{j, 0}); //Add position to the queue
                visited[j][0] = true; //Mark position as visited
            }
        }
    }

    while (!queue.isEmpty()) {
        int[] pos = queue.poll(); //Get next position to explore
        int x = pos[0]; //Current row
        int y = pos[1]; //Current column

        if (player == 'x' && x == M - 1) return true; //Check if X has reached the last row
        if (player == 'o' && y == N - 1) return true; //Check if O has reached the last column

        for (int[] dir : new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}}) { //Possible directions to move
            int newX = x + dir[0]; //New row
            int newY = y + dir[1]; //New column
            if (newX >= 0 && newX < M && newY >= 0 && newY < N && !visited[newX][newY] && grid[newX][newY] == player) { //Check if the position is valid and not visited
                visited[newX][newY] = true; //Mark position as visited
                queue.offer(new int[]{newX, newY}); //Add position to the queue
            }
        }
    }

    return false;
    }

}
