package Model.gizmos;

import physics.Circle;
import physics.LineSegment;

import javax.sound.sampled.Line;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TriangularBumper implements Gizmo{

    private int xPos;
    private int yPos;
    private ArrayList<LineSegment> lines ;
    private ArrayList<Circle> circles ;
    private String type="triangle";
    private Color colour;

    public TriangularBumper(int xpos,int ypos){
        this.xPos=xpos;
        this.yPos=ypos;
        this.lines=new ArrayList<LineSegment>();
        this.circles=new ArrayList< Circle>();
        this.colour = Color.blue;

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
        colour = Color.cyan;
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
        return lines;
    }

    @Override
    public List<Circle> getCircles() {
        return circles;
    }

    @Override
    public int getWidth() {
        return 1;
    }

    @Override
    public int getHeight() {
        return 1;
    }
}
