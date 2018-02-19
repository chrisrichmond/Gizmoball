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
    private String type="square";
    private Color colour;

    public SquareBumper(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
        this.square = new SquareClass(xPos, yPos, 50);
        this.colour = Color.red;
    }

    public ArrayList<Circle> getCircles() {
        return square.getCircles();
    }

    @Override
    public int getWidth() {
        return 1;
    }

    @Override
    public int getHeight() {
        return 1;
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
}
