package Model;

import physics.Circle;
import physics.Vect;

public interface Ball {

    //methods used for basic ball code implementation

    double getBallRadius();

    float getXpos();

    void setXpos(float xpos);

    float getYpos();

    void setYpos(float ypos);

    Circle getCircle();

    Vect getVelocity();

    void setVelocity(Vect velocity);

    boolean isStopped();
}
