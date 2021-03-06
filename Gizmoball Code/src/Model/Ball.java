package Model;

import physics.Circle;
import physics.Vect;

import java.awt.*;

public interface Ball {

    //methods used for basic ball code implementation

    String getID();

    void setID(String ID);

    double getBallRadius();

    float getXpos();

    void setXpos(float xpos);

    float getYpos();

    void setYpos(float ypos);

    Circle getCircle();

    Vect getVelocity();

    void setVelocity(Vect velocity);

    boolean isStopped();

    void setStopped(boolean stopped);

    Color getColour();

    void setColour(Color colour);

    double getRadius();

    void setRadius(double v);
}
