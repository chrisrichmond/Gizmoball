package Model.gizmos;

import Model.Ball;
import physics.*;

import java.awt.*;
import java.util.List;

public class LFlipper implements Gizmo{

    private int xPos;
    private int yPos;
    private RectangleClass rectangle;
    final private float breadth = 0.5F;
    final private float length = 2.0F;
    private float pivotXpos;
    private float pivotYpos;
    private Color colour;
    private String ID;
    final private String type = "leftflipper";

    public LFlipper(String ID, int xPos, int yPos){
        this.ID = ID;
        this.xPos = xPos;
        this.yPos = yPos;
        this.rectangle = new RectangleClass(xPos, yPos, breadth, length);
        this.colour = Color.orange;
        this.pivotXpos = breadth/2;
        this.pivotYpos = breadth/2;
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
    public List<LineSegment> getLines() {
        return rectangle.getLines();
    }

    @Override
    public List<Circle> getCircles() {
        return rectangle.getCircles();
    }

    @Override
    public void storeGizmoBall(Ball gizmoBall) {

    }

    public float getBreadth(){
        return breadth;
    }

    public float getLength(){
        return length;
    }

    // USE getBreadth()
    @Override
    public int getWidth() {
        return 0;
    }

    // USE getLength()
    @Override
    public int getHeight() {
        return 0;
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
    public Shape getShape() {
        return rectangle.makeRectangle();
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

    public void spin(Angle angle){
        rectangle.rotate(new Vect(pivotXpos, pivotYpos), angle);
    }
}
