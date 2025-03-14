import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArrayList<Fork> forks = new ArrayList<Fork>();
        ArrayList<int[]> solution = new ArrayList<int[]>();
        String[][] maze = getMaze("data/maze");
        int[] currCoord = {0, 0};
        int prevDirection = 0;
        // 0 is no direction, 1 is up, 2 is right, 3 is down, 4 is left

        solution.add(currCoord);

        while (!(currCoord[0] == maze.length - 1 && currCoord[1] == maze[maze.length - 1].length - 1)){
            if (isFork(maze, currCoord, prevDirection, solution)){
                addForks(maze, forks, currCoord, prevDirection);
            }
            else{
                prevDirection = getPrevDirection(currCoord, solution.getLast());
                currCoord = solution.getLast();
            }

        }

        for (int[] coord : solution){
            System.out.print("(" + coord[0] +"," + coord[1] +")" + "->");
        }
        System.out.println("finish");

    }

    private static int getPrevDirection(int[] curr, int[] next) {
        System.out.println(Arrays.toString(curr) + ", " + Arrays.toString(next));
        int y_diff = curr[0] - next[0];
        if (y_diff == -1){
            return 1;
        }
        else if (y_diff == 1){
            return 3;
        }
        int x_diff = curr[1] - next[1];
        if (x_diff == -1){
            return 4;
        }
        else if (x_diff == 1){
            return 2;
        }
        System.out.println("uh oh");
        return 0;
    }

    private static void addForks(String[][] maze, ArrayList<Fork> forks, int[] curr, int prev) {
        if (curr[0] != 0 && prev != 1 && maze[curr[0] - 1][curr[1]].equals(".")){
            forks.add(new Fork(curr, new int[]{curr[0] - 1, curr[1]}));
        }
        if (curr[1] != maze[curr[0]].length - 1 && prev != 2 && maze[curr[0]][curr[1] + 1].equals(".")){
            forks.add(new Fork(curr, new int[]{curr[0], curr[1] + 1}));
        }
        if (curr[0] != maze.length - 1 && prev != 3 && maze[curr[0] + 1][curr[1]].equals(".")){
            forks.add(new Fork(curr, new int[]{curr[0] + 1, curr[1]}));
        }
        if (curr[1] != 0 && prev != 4 && maze[curr[0]][curr[1] - 1].equals(".")){
            forks.add(new Fork(curr, new int[]{curr[0], curr[1] - 1}));
        }
    }

    private static boolean isFork(String[][] maze, int[] curr, int prev, ArrayList<int[]> solution) {
        boolean[] validNextPoints = new boolean[4];
        int nextDir = 0;
        if (curr[0] != 0 && prev != 1 && maze[curr[0] - 1][curr[1]].equals(".")){
            validNextPoints[0] = true;
            nextDir = 1;
        }
        if (curr[1] != maze[curr[0]].length - 1 && prev != 2 && maze[curr[0]][curr[1] + 1].equals(".")){
            validNextPoints[1] = true;
            nextDir = 2;
        }
        if (curr[0] != maze.length - 1 && prev != 3 && maze[curr[0] + 1][curr[1]].equals(".")){
            validNextPoints[2] = true;
            nextDir = 3;
        }
        if (curr[1] != 0 && prev != 4 && maze[curr[0]][curr[1] - 1].equals(".")){
            validNextPoints[3] = true;
            nextDir = 4;
        }

        int numOfNextPoints = 0;
        for (boolean valid : validNextPoints){
            if (valid){
                numOfNextPoints++;
            }
        }

        System.out.println(Arrays.toString(validNextPoints));

        if (numOfNextPoints > 1){
            return true;
        }
        else{
            switch (nextDir){
                case 1 -> solution.add(new int[]{curr[0] - 1, curr[1]});
                case 2 -> solution.add(new int[]{curr[0], curr[1] + 1});
                case 3 -> solution.add(new int[]{curr[0] + 1, curr[1]});
                case 4 -> solution.add(new int[]{curr[0], curr[1] - 1});
            }
            return false;
        }

    }

    public static String[][] getMaze(String fileName) {
        File f = new File(fileName);
        Scanner s = null;
        try {
            s = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        }

        ArrayList<String> fileData = new ArrayList<String>();
        while (s.hasNextLine())
            fileData.add(s.nextLine());

        int rows = fileData.size();
        int cols = fileData.get(0).length();

        String[][] maze = new String[rows][cols];

        for (int i = 0; i < fileData.size(); i++) {
            String d = fileData.get(i);
            for (int j = 0; j < d.length(); j++) {
                maze[i][j] = d.charAt(j) + "";
            }
        }
        return maze;
    }

}