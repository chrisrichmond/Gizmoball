package Model.gizmos;

import Model.Ball;
import physics.Circle;
import physics.LineSegment;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Absorber implements Gizmo{

    private ArrayList<Ball> ballAbsorber;
    private int width = 20;
    private int xPos = 0;
    private int yPos;
    private String type="absorber";
    private Color colour;

    public Absorber(int xPos, int yPos){

        //instantiate variables
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public void setPos(int xPos, int yPos) {
        ballAbsorber= new ArrayList<Ball>();
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
    public List<LineSegment> getLines() {
        return null;
    }

    @Override
    public List<Circle> getCircles() {
        return null;
    }


    public void storeGizmoBall(Ball gizmoBall){
        //absorb the ball into the absorber ready to be fired
        ballAbsorber.add(gizmoBall);
    }


    //return the ballAbsorber array list
    public ArrayList<Ball> getBallAbsorber() {
        return ballAbsorber;
    }


    public Ball removeFirstBall(){
        //remove the front of the array list
        System.out.println(ballAbsorber.get(0));
        return ballAbsorber.remove(0);
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

    public void fireBall(){
        System.out.println("Shooting ball into game!!");
        //code to be added to fire ball using Ball object
        //need to add code to the ball class to start the ball moving!
    }

}
