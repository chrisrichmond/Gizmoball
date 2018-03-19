package Model;

import java.awt.Color;

import physics.Circle;
import physics.Vect;

public class BallImpl implements Ball{
	private Vect velocity;
	private float xpos;
	private double radius;
	private float ypos;
	private Color colour;
	private boolean ballStopped;
	private String ID;

	public BallImpl(String ID, float x, float y, double xv, double yv){
		this.ID = ID;
		this.velocity = new Vect(xv,yv);
		this.xpos = x;
		this.ypos = y;
		this.colour = Color.black;
		radius = 0.1D;
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

	public float getXpos() {
		return xpos;
	}

	public void setXpos(float xpos) {
		this.xpos = xpos;
	}

	public float getYpos() {
		return ypos;
	}

	public void setYpos(float ypos) {
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


	@Override
	public String getID() {
		return ID;
	}

	@Override
	public void setID(String ID) {
		this.ID = ID;
	}

	//access method for value of radius
	public double getBallRadius(){
		return radius;
	}

	public Circle getCircle() {
		return new Circle(xpos, ypos, radius);
	}





}
