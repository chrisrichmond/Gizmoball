package Model;

import java.util.ArrayList;
import java.util.List;

import observerpattern.Observer;
import physics.Circle;
import physics.SquareClass;
import physics.TriangleClass;

public class Model implements ModelAPI {
	private List<Observer> observers;
	private boolean changed;
	private Ball ball;
	private ArrayList<SquareClass> squares;
	private ArrayList<Circle> circles;
	private ArrayList<TriangleClass> triangles;
 
	
	public Model(){
		observers = new ArrayList<Observer>();
		changed = false;
		squares = new ArrayList<SquareClass>();
		circles = new ArrayList<Circle>();
		triangles = new ArrayList<TriangleClass>();
		
		ball = new Ball(25, 25, 100, 100);
		
	 
 }
	
	private CollisionDetails timeUntilCollision() {
		//////////////////////
		//////////////
		//////////
		/////
		/////
		//////////////////////
		/////////////
		/////////////////
		return new CollisionDetails(null,0.0);
	}
	
	private void moveBall() {
		////////////////////
		/////////////////////
		////////////////////
		
	}
	
	private Ball movelBallForTime(Ball ball, double time) {
		ball.setXpos(ball.getXpos()*time);
		ball.setYpos(ball.getYpos()*time);
		return ball;
		
	}

	public Ball getBall() {
		return ball;
	}

	public ArrayList<SquareClass> getSquares() {
		return squares;
	}

	public ArrayList<Circle> getCircles() {
		return circles;
	}

	public ArrayList<TriangleClass> getTriangles() {
		return triangles;
	}
	
	public void addCircle(Circle circle) {
		circles.add(circle);
	}
	
	public void addSquare(SquareClass square) {
		squares.add(square);
	}
	
	
    public void addTriangle(TriangleClass triangle) {
		triangles.add(triangle);
	}


	@Override
	public void attach(Observer o) {
		observers.add(o);
	}

	@Override
	public void detach(Observer o) {
		observers.remove(o);
	}

	@Override
	public void setChanged() {
		changed = true;
	}

	@Override
	public void notifyObservers() {
		if(changed){
			for (Observer currentObserver: observers){
				currentObserver.update(this);
			}
			changed = false;
		}
	}


}
