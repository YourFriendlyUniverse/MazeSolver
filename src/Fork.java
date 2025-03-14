public class Fork {

    int[] coord;
    int[] nextCoord;
    int prevDirection;

    public Fork(int[] coord, int[] nextCoord){
        this.coord = coord;
        this.nextCoord = nextCoord;
        this.prevDirection = getPrevDirection(coord, nextCoord);
    }

    public int[] getCoord() {
        return coord;
    }

    public int[] getNextCoord() {
        return nextCoord;
    }

    public int getPrevDirection() {
        return prevDirection;
    }

    public String toString(){
        return "Current Coord: (" + getCoord()[0] + "," + getCoord()[1] + ")\nNext Coord: (" + getNextCoord()[0] + "," + getNextCoord()[1] + ")";
    }

    private int getPrevDirection(int[] curr, int[] next) {
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
        System.out.println("[Fork Class getPrevDirection]: uh oh");
        return 0;
    }

}
