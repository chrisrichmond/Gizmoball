package Model.gizmos;

import Model.Ball;
import physics.Circle;
import physics.LineSegment;
import physics.SquareClass;
import physics.Vect;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Absorber implements Gizmo{

    private Queue<Ball> ballAbsorber;
    private SquareClass square;
    private int width = 10;
    private int xPos;
    private int yPos;
    private String type="absorber";
    private Color colour;
    private String ID;

    public Absorber(String ID, int xPos, int yPos){

        //instantiate variables
        this.ID = ID;
        ballAbsorber= new LinkedList<>();
        this.square = new SquareClass(xPos, yPos, width);
        this.xPos = xPos;
        this.yPos = yPos;
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


    //trigger method that will be called from the model gizmo class will
    //call the methods to absorb the ball and fire it back up into gameplay
    @Override
    public void trigger() {
        removeFirstBall();
        fireBall();
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
       return square.getLines();
    }

    @Override
    public List<Circle> getCircles() {
        return square.getCircles();
    }

    @Override
    public void storeGizmoBall(Ball gizmoBall){
        //absorb the ball into the absorber ready to be fired
        gizmoBall.setXpos(width);
        gizmoBall.setYpos(getYPos());
        gizmoBall.setStopped(true);
        ballAbsorber.add(gizmoBall);
<<<<<<< HEAD
fireBall();
=======
        fireBall();
>>>>>>> b435679634c278402e8feaeb4ac5de6d2b191fb8
       //set balls position to be at the bottom right corner of absorber

    }
    //return the ballAbsorber array list
    public Queue<Ball> getBallAbsorber() {
        return ballAbsorber;
    }


    public Ball removeFirstBall(){
        //remove the front of the list/queue
        return ballAbsorber.poll();
    }

    public void setWidth(int width){
        this.width = width;
    }

    //accessor method to return the width
    public int getWidth(){
        return width;
    }

    @Override
    public int getHeight() {
        return 1;
    }
    @Override
    public void fireBall(){
        System.out.println("Shooting ball into game!!");
        //code to be added to fire ball using Ball object
        //need to add code to the ball class to start the ball moving!
        Ball gizmoBall = removeFirstBall();
        gizmoBall.setVelocity(new Vect(0D,-0.015D));
        gizmoBall.setStopped(false);
<<<<<<< HEAD


=======
        gizmoBall.setVelocity(new Vect(0.0D,-10.0D));

    }

    @Override
    public String getID() {
        return ID;
    }
>>>>>>> b435679634c278402e8feaeb4ac5de6d2b191fb8

    public Shape getShape(){
        return square.makeSquare();
    }

}
