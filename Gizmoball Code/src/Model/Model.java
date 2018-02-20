package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Model.gizmos.*;
import observerpattern.Observer;
import physics.*;

public class Model implements ModelAPI {
	private List<Observer> observers;
	HashMap<Gizmo,Gizmo> gizmoConnections = new HashMap<>();
	HashMap<String,Gizmo> keyConnections = new HashMap<>();
	private boolean changed;
	private boolean isBuildMode;
	private Ball ball;
	private List<Gizmo> gizmos;
	private List<Gizmo> squares;
	private List<Gizmo> circles;
	private List<Gizmo> triangles;
	private List<Gizmo> absorbers;
	private Walls walls;
 	private List<VerticalLine> lines;
	
	public Model(){
		this.observers = new ArrayList<Observer>();
		this.changed = false;
		this.gizmos = new ArrayList<Gizmo>();
		this.squares = new ArrayList<Gizmo>();
		this.circles = new ArrayList<Gizmo>();
		this.triangles = new ArrayList<Gizmo>();
		this.absorbers = new ArrayList<Gizmo>();
		this.lines = new ArrayList<VerticalLine>();

		this.ball = new BallImpl(12.0F, 10.0F, 1.0D, 5000.0D);
		this.walls = new Walls(0,0,19,19);

	 
 }

   private void applyFriction(double delta_t){
	   Double xVnew,xVold,yVold,yVnew;
	   xVold=ball.getVelocity().x();
	   yVold=ball.getVelocity().y();
	   double mu=0.005D;
	   double mu2=0.005D;
	   xVnew = xVold * (1 - mu * delta_t - mu2 * xVold * delta_t);
	   yVnew = yVold * (1 - mu * delta_t - mu2 * yVold * delta_t);
	   ball.setVelocity(new Vect(xVnew,yVnew));
 }
	private void applyGravity(double delta_t){
		Double yVold,yVnew;
		yVold=ball.getVelocity().y();
		double gravity;
		gravity=5D;
		yVnew = yVold + gravity * delta_t;
		ball.setVelocity(new Vect(ball.getVelocity().x(),yVnew));
	}
	
	private CollisionDetails timeUntilCollision() {
		System.out.println("MODEL: ball is at x="+ball.getXpos()+" y="+ball.getYpos()+" diameter="+ball.getBallRadius()*2);

		//git testing
        Circle circle = ball.getCircle();
        Vect velocity= ball.getVelocity();
        Vect newVelocity=velocity;
        double tickTime=0.05D;
        double shortestTime =5000D;
		double minTimeuntilCollision;
		boolean hasCollided = false;

		System.out.println("MODEL: ball velocity is "+ball.getVelocity());
		// check for collision on gizmo walls
        List<LineSegment> wls= walls.getLineSegments();
        for(int i=0;i<wls.size();i++){

			minTimeuntilCollision = Geometry.timeUntilWallCollision(wls.get(i),circle,velocity);
            if(minTimeuntilCollision <= tickTime){
                shortestTime = minTimeuntilCollision;
                newVelocity = Geometry.reflectWall(wls.get(i),velocity);
                System.out.println("Wall Collision");

            }else{
                //no colliosion
            }

        }

        // check for square bumper (line seqments and circles) collisions
       // System.out.println("Checking square colliosions");
        for(int i=0;i<squares.size();i++) {
            List<LineSegment> squareLines = squares.get(i).getLines();
            List<Circle> circles = squares.get(i).getCircles();

            for (int x = 0; x < squareLines.size(); x++) {
                minTimeuntilCollision = Geometry.timeUntilWallCollision(squareLines.get(x), circle, velocity);
                if (minTimeuntilCollision <= tickTime) {
                    shortestTime = minTimeuntilCollision;
					System.out.println("Square Collision");
					// trigger the gizmo
					for(Gizmo currentSquare: squares){
						if(currentSquare.getLines().contains(squareLines.get(x))){
							currentSquare.trigger();
						}
					}

					newVelocity = Geometry.reflectWall(squareLines.get(x), velocity);
                }
            }

            for (int x = 0; x < circles.size(); x++) {
                minTimeuntilCollision = Geometry.timeUntilCircleCollision(circles.get(x), circle, velocity);
                if (minTimeuntilCollision <= tickTime) {
                    shortestTime = minTimeuntilCollision;
					System.out.println("Circle Collision");
                    newVelocity = Geometry.reflectCircle(circles.get(x).getCenter(),circle.getCenter(),velocity);

					// trigger the gizmo

//					for(Gizmo currentCircle: circles){
//						if(currentCircle.getLines().contains(circles.get(x))){
//							currentCircle.trigger();
//						}
//					}
                }
            }
        }

      //Absorber Collsions
		for(int i=0;i<absorbers.size();i++) {
			List<LineSegment> squareLines = absorbers.get(i).getLines();
			List<Circle> circles = absorbers.get(i).getCircles();

			for (int x = 0; x < squareLines.size(); x++) {
				minTimeuntilCollision = Geometry.timeUntilWallCollision(squareLines.get(x), circle, velocity);
				if (minTimeuntilCollision <= tickTime) {
					shortestTime = minTimeuntilCollision;
					System.out.println("Absorber Collision");
					// trigger the gizmo
					absorbers.get(i).storeGizmoBall(ball);


					newVelocity = Geometry.reflectWall(squareLines.get(x), velocity);
				}
			}

			for (int x = 0; x < circles.size(); x++) {
				minTimeuntilCollision = Geometry.timeUntilCircleCollision(circles.get(x), circle, velocity);
				if (minTimeuntilCollision <= tickTime) {
					shortestTime = minTimeuntilCollision;
					System.out.println("Circle Collision");
					newVelocity = Geometry.reflectCircle(circles.get(x).getCenter(),circle.getCenter(),velocity);

					// trigger the gizmo

//					for(Gizmo currentCircle: circles){
//						if(currentCircle.getLines().contains(circles.get(x))){
//							currentCircle.trigger();
//						}
//					}
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
                if (minTimeuntilCollision <= tickTime) {
                    shortestTime = minTimeuntilCollision;
					System.out.println("Triangle Collision");
                    newVelocity = Geometry.reflectWall(triangleLines.get(x), velocity);

					// trigger the gizmo
					for(Gizmo currentTriangle: triangles){
						if(currentTriangle.getLines().contains(triangleLines.get(x))){
							currentTriangle.trigger();
						}
					}
                }
            }

            for (int x = 0; x < triangleCircles.size(); x++) {
                minTimeuntilCollision = Geometry.timeUntilCircleCollision(triangleCircles.get(x), circle, velocity);
                if (minTimeuntilCollision <= tickTime) {
                    shortestTime = minTimeuntilCollision;
					System.out.println("Triangle Collision");
					newVelocity = Geometry.reflectCircle(triangleCircles.get(x).getCenter(),circle.getCenter(),velocity);


                }
            }
        }

        			//   check for Circular Bumper collisions
			// System.out.println("Checking Circular Bumper colliosions");
			for(int i=0;i<circles.size();i++) {
				minTimeuntilCollision = Geometry.timeUntilCircleCollision(circles.get(i).getCircle(), ball.getCircle(),velocity);


            if (minTimeuntilCollision <= tickTime) {
				System.out.println("Circle Collision");
                    shortestTime = minTimeuntilCollision;
				newVelocity = Geometry.reflectCircle(circles.get(i).getCircle().getCenter(),ball.getCircle().getCenter(),velocity);

               }
        }

		//   check for Circular Bumper collisions


		System.out.println("Shortest Time is: "+shortestTime);
		return new CollisionDetails(newVelocity, shortestTime);
	}
	
	public void moveBall() {
		double moveTime = 0.05D;

		if(ball != null && !ball.isStopped()){
			CollisionDetails cd = timeUntilCollision();
			double tuc = cd.getTuc();
			if(tuc > moveTime){
				ball = moveBallForTime(ball, moveTime);
			}else{
				ball = moveBallForTime(ball, tuc);
				ball.setVelocity(cd.getVelocity());
			}
		}

		setChanged();
		notifyObservers();
		
	}
	
	private Ball moveBallForTime(Ball ball, double time) {
		ball.setXpos(ball.getXpos() + (float)(ball.getVelocity().x() * time));
		ball.setYpos(ball.getYpos() + (float)(ball.getVelocity().y() * time));
		applyFriction(time);
		applyGravity(time);
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
		return walls;
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
		}else  if(gizmo.getType().equals("absorber")){
			absorbers.add(gizmo);
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
