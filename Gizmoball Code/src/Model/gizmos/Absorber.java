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
    private int width = 20;
    private int xPos;
    private int yPos;
    private String type="absorber";
    private Color colour;

    public Absorber(int xPos, int yPos){

        //instantiate variables
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
        gizmoBall.setStopped(false);
        gizmoBall.setVelocity(new Vect(10.0D,10.0D));

    }

}
