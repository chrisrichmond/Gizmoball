package Model;

import java.awt.Color;

import physics.Vect;

public class BallImpl implements Ball{
	private Vect velocity;
	private double radius;
	private double xpos;
	private double ypos;
	private Color colour;

	private boolean stopped;

	public BallImpl(double x, double y, double xv, double yv){
		this.velocity = new Vect(xv,yv);
		this.xpos = x;
		this.ypos = y;
		this.colour = Color.pink;
		
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
		return stopped;
	}

	public void setStopped(boolean stopped) {
		this.stopped = stopped;
	}

	@Override
	public float getBallRadius() {
		return 0;
	}

	@Override
	public float getYCoords() {
		return 0;
	}

	@Override
	public float getXCoords() {
		return 0;
	}
}
