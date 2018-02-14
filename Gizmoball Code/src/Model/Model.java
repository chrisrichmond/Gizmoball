package Model;

import java.util.ArrayList;
import java.util.List;

import Model.gizmos.*;
import observerpattern.Observer;
import physics.*;

public class Model implements ModelAPI {
	private List<Observer> observers;
	private boolean changed;
	private boolean isBuildMode;
	private Ball ball;
	private List<Gizmo> gizmos;
	private List<SquareBumperGizmo> squares;
	private List<CircularBumperGizmo> circles;
	private List<TriangularBumperGizmo> triangles;
	private Walls walls;
 	private List<VerticalLine> lines;
	
	public Model(){
		observers = new ArrayList<Observer>();
		changed = false;
		squares = new ArrayList<SquareBumperGizmo>();
		circles = new ArrayList<CircularBumperGizmo>();
		triangles = new ArrayList<TriangularBumperGizmo>();
		lines = new ArrayList<VerticalLine>();

		ball = new BallImpl(25, 25, 100, 100);
		walls = new Walls(0,0,500,500);

	 
 }
	
	private CollisionDetails timeUntilCollision() {
        Circle circle = ball.getCircle();
        Vect velocity= ball.getVelocity();
        Vect newVelocity;
        double tickTime=0;
        double shortestTime =50000;

		// check for collision on gizmo walls
        List<LineSegment> wls= walls.getLineSegments();
        System.out.println("Checking wall colliosions");
        for(int i=0;i<wls.size();i++){
            double minTimeuntilCollision= Geometry.timeUntilWallCollision(wls.get(i),circle,velocity);
            if(minTimeuntilCollision<shortestTime){
                shortestTime=minTimeuntilCollision;
                newVelocity=Geometry.reflectWall(wls.get(i),velocity);
            }else{
                //no colliosion
            }

        }

        // check for square bumper (line seqments and circles) collisions
        System.out.println("Checking square colliosions");
        for(int i=0;i<squares.size();i++) {
            List<LineSegment> squareLines = squares.get(i).getLines();
            List<Circle> circles = squares.get(i).getCircles();

            for (int x = 0; i < squareLines.size(); i++) {
                double minTimeuntilCollision = Geometry.timeUntilWallCollision(squareLines.get(x), circle, velocity);
                if (minTimeuntilCollision < shortestTime) {
                    shortestTime = minTimeuntilCollision;
                    newVelocity = Geometry.reflectWall(squareLines.get(x), velocity);
                }
            }

            for (int x = 0; i < circles.size(); i++) {
                double minTimeuntilCollision = Geometry.timeUntilCircleCollision(circles.get(x), circle, velocity);
                if (minTimeuntilCollision < shortestTime) {
                    shortestTime = minTimeuntilCollision;
                    newVelocity = Geometry.reflectCircle(circle.getCenter(),circle.getCenter(),velocity);
                }
            }
        }



        // check for other Triange collisions
        System.out.println("Checking triangle colliosions");
        for(int i=0;i<triangles.size();i++) {
            List<LineSegment> triangleLines = triangles.get(i).getLines();
            List<Circle> triangleCircles = triangles.get(i).getCircles();

            for (int x = 0; i < triangleLines.size(); i++) {
                double minTimeuntilCollision = Geometry.timeUntilWallCollision(triangleLines.get(x), circle, velocity);
                if (minTimeuntilCollision < shortestTime) {
                    shortestTime = minTimeuntilCollision;
                    newVelocity = Geometry.reflectWall(triangleLines.get(x), velocity);
                }
            }

            for (int x = 0; i < triangleCircles.size(); i++) {
                double minTimeuntilCollision = Geometry.timeUntilCircleCollision(triangleCircles.get(i), circle, velocity);
                if (minTimeuntilCollision < shortestTime) {
                    shortestTime = minTimeuntilCollision;
                    newVelocity = Geometry.reflectCircle(circle.getCenter(),ball.getVelocity(),velocity);
                }
            }
        }

            // check for other Circular Bumper collisions
        System.out.println("Checking Circular Bumper colliosions");
        for(int i=0;i<circles.size();i++) {
            double minTimeuntilCollision = Geometry.timeUntilCircleCollision(circles.get(i), ball,velocity);
            newVelocity = Geometry.reflectCircle(circles.get(i).getCircle(),ball.getVelocity(),velocity);
                if (minTimeuntilCollision < shortestTime) {
                    shortestTime = minTimeuntilCollision;
             ////new velo
                }

        }

		return new CollisionDetails(null,0.0);
	}
	
	private void moveBall() {
		////////////////////
		/////////////////////
		////////////////////
		
	}
	
	private Ball moveBallForTime(BallImpl ball, double time) {
		ball.setXpos(ball.getXpos()*time);
		ball.setYpos(ball.getYpos()*time);
		return ball;
		
	}

	@Override
	public Ball getBall() {
		return ball;
	}

	@Override
	public boolean isBuildMode() {
		return isBuildMode;
	}

	@Override
	public Walls getWalls() {
		return null;
	}

	public List<SquareBumperGizmo> getSquares() {
		return squares;
	}

	public List<CircularBumperGizmo> getCircles() {
		return circles;
	}

	public List<TriangularBumperGizmo> getTriangles() {
		return triangles;
	}
	
	public void addCircle(CircularBumperGizmo circle) {
		circles.add(circle);
	}
	
	public void addSquare(SquareBumperGizmo square) {
		squares.add(square);
	}
	
	
    public void addTriangle(TriangularBumperGizmo triangle) {
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
