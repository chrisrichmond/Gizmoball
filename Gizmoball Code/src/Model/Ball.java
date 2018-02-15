package Model;

import physics.Circle;
import physics.Vect;

public interface Ball {

    //methods used for basic ball code implementation

    double getBallRadius();

    double getXpos();

    void setXpos(double xpos);

    double getYpos();

    void setYpos(double ypos);

    Circle getCircle();

    Vect getVelocity();

    void setVelocity(Vect velocity);

    boolean isStopped();
}
