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
    private Color colour;
    private Circle circle;
    private String ID;
    final private String type = "circle";

    public CircularBumper(String ID,int xpos,int ypos){
        this.ID = ID;
        this.xPos=xpos;
        this.yPos=ypos;
        this.radius=0.5F;
        this.circle=new Circle(new Vect(xpos+radius, ypos+radius), radius);
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
    public float getWidth() {
        return 1;
    }

    @Override
    public float getHeight() {
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
        return circle;

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

    @Override
    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public int[][] getBounds() {
        int[][] bounds = new int[][]{
                {xPos, yPos},
                {xPos+((int)(2*radius)), yPos+((int)(2*radius))}
        };

        return bounds;
    }

    @Override
    public void setColour(Color colour) {
        this.colour = colour;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", ID, xPos, yPos);
    }

}
