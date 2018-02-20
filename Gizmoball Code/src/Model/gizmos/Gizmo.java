package Model.gizmos;


import physics.Circle;
import physics.LineSegment;

import java.awt.*;
import java.util.List;

import static Model.Model.*;

public interface Gizmo {

    /**
     * Sets the grid position of the Gizmo object
     * @param xPos the x axis coordinate as an integer
     * @param yPos the y axis coordinate as an integer
     */
    void setPos(int xPos, int yPos);

    /**
     * Gets the x grid position of the Gizmo object
     * @return x integer grid pos
     */
    int getXPos();

    /**
     * Gets the y grid position of the Gizmo object
     * @return y integer grid pos
     */
    int getYPos();

    /**
     * Called to trigger this Gizmo
     */
    void trigger();

    Color getColour();

    String getType();
    Circle getCircle();

    List<LineSegment> getLines();

    List<Circle> getCircles();

    void storeGizmoBall(Model.Ball gizmoBall);

    int getWidth();

    int getHeight();

    void fireBall();

    String getID();
}
