package Model.gizmos;


import Model.Ball;
import physics.Circle;
import physics.LineSegment;
import physics.Vect;

import java.awt.*;
import java.util.List;

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

    void storeGizmoBall(Ball gizmoBall);

    float getWidth();

    float getHeight();

    Vect fireBall();

    String getID();

    Shape getShape();

    int getDirection();

    boolean rotate();

    void setID(String ID);

    /**
     * Gets the coordinates representing the top left position to the bottom right position of the space occupied by the Gizmo
     * Note: the bounds are different compared to the physical collision area or graphically drawn area of a Gizmo
     * Other Gizmos cannot occupy the bounds of a Gizmo by the ball may pass through the bounds if there is not an active collision wall impeding it
     * In Java, [rows][columns] -> [y][x] e.g. [0][1] means y=0, x=1
     *
     * Should be defined something like this
     *  int[][] bounds = new int[][]{
            {xPos1, yPos1},
            {xPos2, yPos2}
        };
     *  Which would give the following reference:
     *      bounds[0][0] = xPos1
     *      bounds[0][1] = yPos1
     *      bounds[1][0] = xPos2
     *      bounds[1][1] = yPos2
     *
     * @return a set of coordinates representing the bounds of the Gizmo e.g. {[0,0], [1,1]}
     */
    int[][] getBounds();

    void setColour(Color colour);

}
