package Model.gizmos;

import physics.Circle;
import physics.LineSegment;

import java.util.List;

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

    @Override
    public String getType() {
        return null;
    }

    @Override
    public List<LineSegment> getLines() {
        return null;
    }

    @Override
    public List<Circle> getCircles() {
        return null;
    }

    public Circle getCircle() {
        return new Circle(xPos, yPos, radius);

    }
}
