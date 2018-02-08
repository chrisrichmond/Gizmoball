package Model.gizmos;

public class TriangularBumper implements Gizmo{

    private int xPos;
    private int yPos;

    public TriangularBumper(){

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
