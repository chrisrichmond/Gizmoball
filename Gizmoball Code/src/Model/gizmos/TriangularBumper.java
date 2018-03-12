package Model.gizmos;

import Model.Ball;
import physics.Circle;
import physics.LineSegment;
import physics.TriangleClass;
import physics.Vect;

import java.awt.*;
import java.util.List;

public class TriangularBumper implements Gizmo{

    private int xPos;
    private int yPos;
    private TriangleClass triangle;
    private Color colour;
    private String ID;
    final private String type = "triangle";
    // Legend for Triangle Rotation
    //     |\       |--/    \--|       /|
    // 0 = | \  1 = | /  2 = \ | 3 =  / |
    //     |__\     |/        \|     /__|
    private int rotation;

    public TriangularBumper(String ID, int xpos, int ypos) {
        this.ID = ID;
        this.xPos = xpos;
        this.yPos = ypos;
        this.triangle = new TriangleClass(xPos, yPos, 0);
        this.colour = Color.red;
        this.rotation = 0;

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
    public Circle getCircle() {
        return null;
    }

    @Override
    public List<LineSegment> getLines() {
        return triangle.getLines();
    }

    @Override
    public List<Circle> getCircles() {
        return triangle.getCircles();
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

    public Shape getShape(){
        return triangle.makeTriangle();
    }

    @Override
    public int getDirection() {
        return 0;
    }

    @Override
    public boolean rotate(){
        rotation += 1;
        if(rotation > 3){
            rotation = 0;
        }
        System.out.println("rotation = "+rotation);
        this.triangle = new TriangleClass(xPos, yPos, rotation);
        return true;
    }
}
