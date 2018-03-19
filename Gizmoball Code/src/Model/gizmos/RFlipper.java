package Model.gizmos;

import Model.Ball;
import physics.Circle;
import physics.LineSegment;
import physics.Vect;

import java.awt.*;
import java.util.List;

public class RFlipper implements Gizmo{

    private int xPos;
    private int yPos;
    private String ID;
    final private String type = "rightflipper";
    private Color colour;

    public RFlipper(String ID, int xPos, int yPos){
        this.ID = ID;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public void setPos(int xPos, int yPos) {

    }

    @Override
    public int getXPos() {
        return 0;
    }

    @Override
    public int getYPos() {
        return 0;
    }

    @Override
    public void trigger() {

    }

    @Override
    public Color getColour() {
        return null;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Circle getCircle() {
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

    @Override
    public void storeGizmoBall(Ball gizmoBall) {

    }

    @Override
    public float getWidth() {
        return 0;
    }

    @Override
    public float getHeight() {
        return 0;
    }

    @Override
    public Vect fireBall() {
        return null;
    }

    @Override
    public String getID() {
        return null;
    }

    @Override
    public Shape getShape() {
        return null;
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
                {xPos+2, yPos+2}
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
