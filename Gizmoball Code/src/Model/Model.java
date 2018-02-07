package Model;

import java.util.ArrayList;
import java.util.List;

import Model.gizmos.Gizmo;
import observerpattern.Observer;
import physics.Circle;
import physics.SquareClass;
import physics.TriangleClass;

public class Model implements ModelAPI {
	private List<Observer> observers;
	private boolean changed;
	private Ball ball;
	private List<Gizmo> gizmos;
	private List<SquareClass> squares;
	private List<Circle> circles;
	private List<TriangleClass> triangles;
 
	
	public Model(){
		observers = new ArrayList<Observer>();
		changed = false;
		squares = new ArrayList<SquareClass>();
		circles = new ArrayList<Circle>();
		triangles = new ArrayList<TriangleClass>();
		
		ball = new BallImpl(25, 25, 100, 100);
		
	 
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
	
	private Ball movelBallForTime(BallImpl ball, double time) {
		ball.setXpos(ball.getXpos()*time);
		ball.setYpos(ball.getYpos()*time);
		return ball;
		
	}

	@Override
	public Ball getBall() {
		return ball;
	}

	public List<SquareClass> getSquares() {
		return squares;
	}

	public List<Circle> getCircles() {
		return circles;
	}

	public List<TriangleClass> getTriangles() {
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
				currentObserver.update();
			}
			changed = false;
		}
	}

	@Override
	public List<Gizmo> getGizmos(){
		return gizmos;
	}

}
