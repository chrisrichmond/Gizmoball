package Model;

import java.util.ArrayList;
import java.util.List;

import Model.gizmos.*;
import com.sun.org.apache.bcel.internal.generic.BREAKPOINT;
import observerpattern.Observer;
import physics.*;

public class Model implements ModelAPI {
	private List<Observer> observers;
	private boolean changed;
	private boolean isBuildMode;
	private Ball ball;
	private List<Gizmo> gizmos;
	private List<Gizmo> squares;
	private List<Gizmo> circles;
	private List<Gizmo> triangles;
	private Walls walls;
 	private List<VerticalLine> lines;
	
	public Model(){
		observers = new ArrayList<Observer>();
		changed = false;
		gizmos = new ArrayList<Gizmo>();
		squares = new ArrayList<Gizmo>();
		circles = new ArrayList<Gizmo>();
		triangles = new ArrayList<Gizmo>();

		lines = new ArrayList<VerticalLine>();

		ball = new BallImpl(25.0D, 25.0D, 100.0D, 100.0D);
		walls = new Walls(0,0,500,500);

	 
 }
	
	private CollisionDetails timeUntilCollision() {
		System.out.println("x="+ball.getXpos()+" y="+ball.getYpos());
		//git testing
        Circle circle = ball.getCircle();
        Vect velocity= ball.getVelocity();
        Vect newVelocity=velocity;
        double tickTime=0.05D;
        double shortestTime =50000.0D;
		double minTimeuntilCollision;
		boolean hasCollided = false;

		// check for collision on gizmo walls
        List<LineSegment> wls= walls.getLineSegments();
        System.out.println("Checking wall colliosions");
        for(int i=0;i<wls.size();i++){

			minTimeuntilCollision = Geometry.timeUntilWallCollision(wls.get(i),circle,velocity);
			System.out.println("min"+minTimeuntilCollision);
            if(minTimeuntilCollision < tickTime){
                shortestTime = minTimeuntilCollision;
                newVelocity = Geometry.reflectWall(wls.get(i),velocity);
                System.out.println("wall Collision");
                setChanged();
                notifyObservers();
            }else{
                //no colliosion
            }

        }

        // check for square bumper (line seqments and circles) collisions
       // System.out.println("Checking square colliosions");
        for(int i=0;i<squares.size();i++) {
            List<LineSegment> squareLines = squares.get(i).getLines();
            List<Circle> circles = squares.get(i).getCircles();

            for (int x = 0; i < squareLines.size(); i++) {
                minTimeuntilCollision = Geometry.timeUntilWallCollision(squareLines.get(x), circle, velocity);
                if (minTimeuntilCollision < tickTime) {
                    shortestTime = minTimeuntilCollision;
					System.out.println("square Collision");
                    newVelocity = Geometry.reflectWall(squareLines.get(x), velocity);
                }
            }

            for (int x = 0; i < circles.size(); i++) {
                minTimeuntilCollision = Geometry.timeUntilCircleCollision(circles.get(x), circle, velocity);
                if (minTimeuntilCollision < tickTime) {
                    shortestTime = minTimeuntilCollision;
					System.out.println("Square Collision");
                    newVelocity = Geometry.reflectCircle(circle.getCenter(),circle.getCenter(),velocity);
                }
            }
        }



        // check for other Triange collisions
       // System.out.println("Checking triangle colliosions");
        for(int i=0;i<triangles.size();i++) {
            List<LineSegment> triangleLines = triangles.get(i).getLines();
            List<Circle> triangleCircles = triangles.get(i).getCircles();

            for (int x = 0; i < triangleLines.size(); i++) {
                minTimeuntilCollision = Geometry.timeUntilWallCollision(triangleLines.get(x), circle, velocity);
                if (minTimeuntilCollision < tickTime) {
                    shortestTime = minTimeuntilCollision;
					System.out.println("Triangle Collision");
                    newVelocity = Geometry.reflectWall(triangleLines.get(x), velocity);
                }
            }

            for (int x = 0; i < triangleCircles.size(); i++) {
                minTimeuntilCollision = Geometry.timeUntilCircleCollision(triangleCircles.get(i), circle, velocity);
                if (minTimeuntilCollision < tickTime) {
                    shortestTime = minTimeuntilCollision;
					System.out.println("Triangle Collision");
                    newVelocity = Geometry.reflectCircle(circle.getCenter(),ball.getVelocity(),velocity);

                }
            }
        }

            // check for other Circular Bumper collisions
//        System.out.println("Checking Circular Bumper colliosions");
//        for(int i=0;i<circles.size();i++) {
//             minTimeuntilCollision = Geometry.timeUntilCircleCollision(circles.get(i), ball,velocity);
//            newVelocity = Geometry.reflectCircle(circles.get(i).getCircle(),ball.getVelocity(),velocity);
//
//            if (minTimeuntilCollision < shortestTime) {
//                    shortestTime = minTimeuntilCollision;
//
//                }
//        }

        setChanged();
        notifyObservers();
System.out.println("Shortest Time is: "+shortestTime);
		return new CollisionDetails(newVelocity, shortestTime);
	}
	
	public void moveBall() {
		double moveTime = 0.05D;

		if(ball != null && !ball.isStopped()){
			CollisionDetails cd = timeUntilCollision();
			double tuc = cd.getTuc();
			if(tuc > moveTime){
				System.out.println("Moving ball for time no collision");
				ball = moveBallForTime(ball, moveTime);
			}else{
				System.out.println("Moving ball for mintime for collision");
				ball = moveBallForTime(ball, tuc);
				ball.setVelocity(cd.getVelocity());
			}
		}

		setChanged();
		notifyObservers();
		
	}
	
	private Ball moveBallForTime(Ball ball, double time) {
		ball.setXpos(ball.getXpos() + (ball.getVelocity().x() * time));
		ball.setYpos(ball.getXpos() + (ball.getVelocity().y() * time));
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

	public List<Gizmo> getSquares() {
		return squares;
	}

	public List<Gizmo> getCircles() {
		return circles;
	}

	public List<Gizmo> getTriangles() {
		return triangles;
	}
	
	public void addCircle(Gizmo circle) {
		circles.add(circle);
	}
	
	public void addSquare(Gizmo square) {
		squares.add(square);
	}

    public void addTriangle(Gizmo triangle) {
		triangles.add(triangle);
	}

	@Override
	public void setBallSpeed(int x, int y){
		ball.setVelocity(new Vect((double)x,(double)y));
	}

	@Override
	public void addGizmo(Gizmo gizmo) {
		gizmos.add(gizmo);
		if(gizmo.getType().equals("circle")){
			circles.add(gizmo);
		}else if(gizmo.getType().equals("square")){
			squares.add(gizmo);
		}else  if(gizmo.getType().equals("triangle")){
			triangles.add(gizmo);
		}
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
