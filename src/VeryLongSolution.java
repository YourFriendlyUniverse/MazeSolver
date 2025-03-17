import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class VeryLongSolution {
    public static void main(String[] args) {

        ArrayList<Fork> forks = new ArrayList<Fork>();
        ArrayList<int[]> solution = new ArrayList<int[]>();
        String[][] maze = getMaze("data/maze");
        int[] currCoord = {0, 0};

        while (!(currCoord[0] == maze.length - 1 && currCoord[1] == maze[maze.length - 1].length - 1)){
            solution.add(currCoord);
            maze[currCoord[0]][currCoord[1]] = "V";
            int[] nextCoord = move(maze, currCoord);
            if (nextCoord[0] == currCoord[0] && nextCoord[1] == currCoord[1]){
                maze[currCoord[0]][currCoord[1]] = "#";
                solution.clear();
                currCoord = new int[]{0, 0};
                for (int i = 0; i < maze.length; i++){
                    for (int j = 0; j < maze[i].length; j++){
                        if (maze[i][j].equals("V")){
                            maze[i][j] = ".";
                        }
                    }
                }
            }
            else{
                currCoord = nextCoord;
            }
        }

        solution.add(currCoord);

        for (int i = 0; i < solution.size(); i++) {
            if (i != solution.size() - 1) {
                System.out.println("(" + solution.get(i)[0] + ", " + solution.get(i)[1] + ")" + " ---> ");
            } else {
                System.out.println("(" + solution.get(i)[0] + ", " + solution.get(i)[1] + ")");
            }
        }
        for (String[] row : maze){
            for (String tile : row){
                System.out.print(tile);
            }
            System.out.println();
        }

    }

    private static int[] move(String[][] maze, int[] curr) {
        if (canGoUp(maze, curr)){
            return new int[]{curr[0] - 1, curr[1]};
        }
        else if (canGoRight(maze, curr)){
            return new int[]{curr[0], curr[1] + 1};
        }
        else if (canGoDown(maze, curr)){
            return new int[]{curr[0] + 1, curr[1]};
        }
        else if (canGoLeft(maze, curr)){
            return new int[]{curr[0], curr[1] - 1};
        }
        else{
            return curr;
        }
    }


    private static boolean canGoUp(String[][] maze, int[] curr){
        return curr[0] != 0 && maze[curr[0] - 1][curr[1]].equals(".");
    }

    private static boolean canGoRight(String[][] maze, int[] curr){
        return curr[1] != maze[curr[0]].length - 1 && maze[curr[0]][curr[1] + 1].equals(".");
    }

    private static boolean canGoDown(String[][] maze, int[] curr){
        return curr[0] != maze.length - 1 && maze[curr[0] + 1][curr[1]].equals(".");
    }

    private static boolean canGoLeft(String[][] maze, int[] curr){
        return curr[1] != 0 && maze[curr[0]][curr[1] - 1].equals(".");
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