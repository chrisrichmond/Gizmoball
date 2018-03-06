package Model.gizmos;

import Model.Ball;
import physics.Circle;
import physics.LineSegment;
import physics.Vect;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CircularBumper implements Gizmo {

    private int xPos;
    private int yPos;
    private float radius;
    private String type="circle";
    private Color colour;
    private String ID;

    public CircularBumper(String ID,int xpos,int ypos){
        this.ID = ID;
        this.xPos=xpos;
        this.yPos=ypos;
        this.radius=0.5F;
        this.colour = Color.green;
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
        colour = Color.YELLOW;
    }

    @Override
    public Color getColour() {
        return colour;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public List<LineSegment> getLines() {
        return null;
    }

    @Override
    public List<Circle> getCircles() {
        List<Circle> c = new ArrayList<Circle>();
        c.add(getCircle());
        return c;
    }

    @Override
    public void storeGizmoBall(Ball gizmoBall) {

    }

    @Override
    public int getWidth() {
        return 1;
    }

    @Override
    public int getHeight() {
        return 1;
    }

    @Override
    public Vect fireBall() {

        return null;
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public Circle getCircle() {
        return new Circle(xPos, yPos, radius);

    }

    public Shape getShape(){
        return getCircle().toEllipse2D();
    }

    @Override
    public int getDirection() {
        return 0;
    }

    @Override
    public boolean rotate() {
        return false;
    }
}
