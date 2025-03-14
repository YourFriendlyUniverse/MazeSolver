public class Fork {

    int[] coord;
    int[] nextCoord;

    public Fork(int[] coord, int[] nextCoord){
        this.coord = coord;
        this.nextCoord = nextCoord;
    }

    public int[] getCoord() {
        return coord;
    }

    public int[] getNextCoord() {
        return nextCoord;
    }

}
