package Model.gizmos;

import Model.Ball;
import physics.Circle;
import physics.LineSegment;
import physics.Vect;

import java.awt.*;
import java.util.List;

public class RFlipper implements Gizmo{

    final private String type = "rightflipper";

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
    public int getWidth() {
        return 0;
    }

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
}
