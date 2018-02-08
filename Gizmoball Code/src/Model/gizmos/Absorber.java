package Model.gizmos;

public class Absorber implements Gizmo{

    private int xPos;
    private int yPos;

    public Absorber(){

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
