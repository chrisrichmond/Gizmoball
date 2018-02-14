package Model.gizmos;

import physics.Circle;

public class CircularBumper implements Gizmo {

    private int xPos;
    private int yPos;
    private int radius;


    public CircularBumper(int radius,int xpos,int ypos){
        this.xPos=xpos;
        this.yPos=ypos;
        this.radius=radius;

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

    @Override
    public void trigger() {

    }
    public Circle getCircle() {
        return new Circle(xPos, yPos, radius);

    }
}
