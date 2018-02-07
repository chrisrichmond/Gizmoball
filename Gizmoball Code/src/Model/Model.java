package Model;

import java.util.ArrayList;
import java.util.Observable;

import physics.Circle;
import physics.SquareClass;
import physics.TriangleClass;

public class Model extends Observable {
	private Ball ball;
	private ArrayList<SquareClass> squares;
	private ArrayList<Circle> circles;
	private ArrayList<TriangleClass> triangles;
	
 
	
	public Model(){
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
	
	
}
