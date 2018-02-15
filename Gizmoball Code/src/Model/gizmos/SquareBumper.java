package Model.gizmos;

import physics.Circle;
import physics.LineSegment;
import physics.SquareClass;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class SquareBumper implements Gizmo{

    private int xPos;
    private int yPos;
    private SquareClass square;

    public SquareBumper(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
        this.square = new SquareClass(xPos, yPos, 50);
    }

    public ArrayList<Circle> getCircles() {
        return square.getCircles();
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

    }

    @Override
    public String getType() {
        return null;
    }
}
