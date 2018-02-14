package Model;

import physics.Circle;
import physics.Vect;

public interface Ball {

    //methods used for basic ball code implementation

    public double getBallRadius();

    public double getYCoords();

    public double getXCoords();

    Circle getCircle();

    Vect getVelocity();
}
