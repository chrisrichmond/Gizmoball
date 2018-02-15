package Model;

import java.awt.Color;

import physics.Circle;
import physics.Vect;

public class BallImpl implements Ball{
	private Vect velocity;
	private double xpos;
	private double radius;
	private double ypos;
	private Color colour;
	private boolean ballStopped;

	public BallImpl(double x, double y, double xv, double yv){
		this.velocity = new Vect(xv,yv);
		this.xpos = x;
		this.ypos = y;
		this.colour = Color.pink;
		radius = 0.3;
	}

	public Vect getVelocity() {
		return velocity;
	}

	public void setVelocity(Vect velocity) {
		this.velocity = velocity;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double getXpos() {
		return xpos;
	}

	public void setXpos(double xpos) {
		this.xpos = xpos;
	}

	public double getYpos() {
		return ypos;
	}

	public void setYpos(double ypos) {
		this.ypos = ypos;
	}

	public Color getColour() {
		return colour;
	}

	public void setColour(Color colour) {
		this.colour = colour;
	}

	public boolean isStopped() {
		return ballStopped;
	}

	public void setStopped(boolean stopped) {
		this.ballStopped = stopped;
	}


	//access method for value of radius
	public double getBallRadius(){
		return radius;
	}

	//return y position of ball in the game
	public double getYCoords(){
		return ypos;
	}

	//return x position of ball in the game
	public double getXCoords(){
		return xpos;
	}

	public Circle getCircle() {
		return new Circle(xpos, ypos, radius);
	}





}
