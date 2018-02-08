package Model.gizmos;

public class SquareBumper implements Gizmo{

    private int xPos;
    private int yPos;

    public SquareBumper(){

    }

    @Override
    public void setPos(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public int getXPos() {
        return xPos;
    }

    @Override
    public int getYPos() {
        return yPos;
    }
}
