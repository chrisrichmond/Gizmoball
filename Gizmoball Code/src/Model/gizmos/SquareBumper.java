package Model.gizmos;

import Model.Ball;
import physics.Circle;
import physics.LineSegment;
import physics.SquareClass;
import physics.Vect;
import utilities.GizmoConstants;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class SquareBumper implements Gizmo{

    private int xPos;
    private int yPos;
    private SquareClass square;
    private Color colour;
    private String ID;
    final private String type = "square";

    public SquareBumper(String ID, int xPos, int yPos){
        this.ID = ID;
        this.xPos = xPos;
        this.yPos = yPos;
        this.square = new SquareClass(xPos, yPos, 1);
        this.colour = GizmoConstants.squareColour;
    }

    public ArrayList<Circle> getCircles() {
        return square.getCircles();
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

//    public void setCircles(ArrayList<Circle> circles) {
//        this.circles = circles;
//    }

    public ArrayList<LineSegment> getLines() {
        return square.getLines();
    }

//    public void setLines(ArrayList<LineSegment> lines) {
//        this.lines = lines;
//    }

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
        System.out.println("Square Triggered");
        colour = GizmoConstants.squareTriggerColour;
        Thread colourTimeout = new Thread(new ChangeColourRunnable(this, GizmoConstants.squareColour));
        colourTimeout.start();
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
    public Circle getCircle() {
        return null;
    }

    @Override
    public Shape getShape() {
        return square.makeSquare();
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
                {xPos+(int)getWidth(), yPos+(int)getHeight()}
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
