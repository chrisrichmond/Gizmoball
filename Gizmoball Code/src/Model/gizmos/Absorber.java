package Model.gizmos;

import Model.Ball;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Absorber implements Gizmo{

    private ArrayList<Ball> ballAbsorber;
    public int width = 1;
    private int xPos = 0;
    private int yPos;

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


    //accessor method to return the width
    public int getWidth(){
        return width;
    }




    public void fireBall(){

        System.out.println("Shooting ball into game!!");

        //code to be added to fire ball using Ball object
        //need to add code to the ball class to start the ball moving!

    }


}
