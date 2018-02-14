package Model.gizmos;

import physics.Circle;
import physics.LineSegment;

import java.util.ArrayList;

public class SquareBumper implements Gizmo{

    private int xPos;
    private int yPos;
    private ArrayList<LineSegment> lines ;
    private ArrayList<Circle> circles = new ArrayList<>();

    public ArrayList<Circle> getCircles() {
        return circles;
    }

    public void setCircles(ArrayList<Circle> circles) {
        this.circles = circles;
    }

    public ArrayList<LineSegment> getLines() {

        return lines;
    }

    public void setLines(ArrayList<LineSegment> lines) {
        this.lines = lines;
    }

    public SquareBumper(){
lines = new ArrayList<>();

circles=new ArrayList<>();
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
}
