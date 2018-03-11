package Model.gizmos;

import Model.Ball;
import physics.Circle;
import physics.LineSegment;
import physics.SquareClass;
import physics.Vect;

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
        this.colour = Color.red;
    }

    public ArrayList<Circle> getCircles() {
        return square.getCircles();
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

//    public void setCircles(ArrayList<Circle> circles) {
//        this.circles = circles;
//    }

    public ArrayList<LineSegment> getLines() {
        return square.getLines();
    }

//    public void setLines(ArrayList<LineSegment> lines) {
//        this.lines = lines;
//    }

    public Rectangle2D.Double getDrawableSquare(){
        return square.makeSquare();
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
        System.out.println("Shooting ball into game!!");
        colour = Color.green;
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
}
