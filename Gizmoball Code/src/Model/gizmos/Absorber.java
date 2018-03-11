package Model.gizmos;

import Model.Ball;
import physics.*;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Absorber implements Gizmo{

    private Queue<Ball> ballAbsorber;
    private RectangleClass rectangle;
    private int xPos1; // left side of where the absorber starts
    private int yPos1; // top side of where the absorber starts
    private int xPos2; // right side of where the absorber ends
    private int yPos2; // bottom side of where the absorber ends
    private int width;
    private int height;
    private Color colour;
    private String ID;
    final private String type = "absorber";

    public Absorber(String ID, int xPos1, int yPos1, int xPos2, int yPos2){

        //instantiate variables
        this.ID = ID;
        ballAbsorber= new LinkedList<>();
        this.xPos1 = xPos1;
        this.yPos1 = yPos1;
        this.xPos2 = xPos2;
        this.yPos2 = yPos2;
        this.width = xPos2 - xPos1;
        this.height = yPos2 - yPos1;
        this.rectangle = new RectangleClass(xPos1, yPos2, width, height);
        this.colour = Color.pink;
    }


    @Override
    public void setPos(int xPos, int yPos) {

    }

    @Override
    public int getXPos() {
        return xPos1;
    }

    public int getXPos2(){
        return xPos2;
    }

    @Override
    public int getYPos() {
        return yPos1;
    }

    public int getYPos2(){
        return yPos2;
    }


    //trigger method that will be called from the model gizmo class will
    //call the methods to absorb the ball and fire it back up into gameplay
    @Override
    public void trigger() {
        if(!ballAbsorber.isEmpty()){
            fireBall();
        }
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
       return rectangle.getLines();
    }

    @Override
    public List<Circle> getCircles() {
        return rectangle.getCircles();
    }

    @Override
    public void storeGizmoBall(Ball gizmoBall){
        //absorb the ball into the absorber ready to be fired
        gizmoBall.setXpos(width-0.5F);
        gizmoBall.setYpos(getYPos());
        gizmoBall.setStopped(true);
        ballAbsorber.add(gizmoBall);



        fireBall();

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
        return height;
    }
    @Override
    public Vect fireBall(){
        System.out.println("Shooting ball into game!!");
        //code to be added to fire ball using Ball object
        //need to add code to the ball class to start the ball moving!
        Ball gizmoBall = removeFirstBall();

        if(gizmoBall!=null) {

        gizmoBall.setVelocity(new Vect(0D,-0.015D));
        gizmoBall.setStopped(false);
        gizmoBall.setVelocity(new Vect(0.0D,-10.0D));


            return new Vect(0.0, 15);
        }
        return null;
    }

    @Override
    public String getID() {
        return ID;
    }


    public Shape getShape(){
        return rectangle.makeRectangle();
    }

    @Override
    public int getDirection() {
        return 0;
    }

    @Override
    public boolean rotate() {
        return false;
    }

}
