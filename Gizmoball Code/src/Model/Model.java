package Model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Model.gizmos.*;
import com.sun.xml.internal.ws.util.StringUtils;
import utilities.Observer;
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
	private List<Gizmo> leftFlippers;
	private List<Gizmo> rightFlippers;
	private Walls walls;
 	private List<VerticalLine> lines;
 	private GizmoFileHandler fileHandler;
 	private double tickTime;
	
	public Model(){
		this.observers = new ArrayList<Observer>();
		this.changed = false;
		this.gizmos = new ArrayList<Gizmo>();
		this.squares = new ArrayList<Gizmo>();
		this.circles = new ArrayList<Gizmo>();
		this.triangles = new ArrayList<Gizmo>();
		this.absorbers = new ArrayList<Gizmo>();
		this.leftFlippers = new ArrayList<Gizmo>();
		this.rightFlippers = new ArrayList<Gizmo>();
		this.fileHandler = new GizmoFileHandler(this);
		this.tickTime = 0.05D;

		this.ball = new BallImpl("B",18.5F, 10.0F, 0.0D, 0.0D);
		this.walls = new Walls(0,0,20,20);

	 
 	}

 	public void replaceBall(Ball ball){
		this.ball = ball;
	}

 	public void loadFile(String filename) throws FileNotFoundException{
	    clear();
	    fileHandler.loadFromFile(filename);
	    setChanged();
	    notifyObservers();
    }

	@Override
	public void saveFile(String filename) {
		fileHandler.saveToFile(filename);

	}

	public void clear(){
 	    gizmos.clear();
 	    squares.clear();
 	    triangles.clear();
 	    circles.clear();
 	    absorbers.clear();
 	    leftFlippers.clear();
 	    rightFlippers.clear();
 	    setChanged();
 	    notifyObservers();
    }

   private void applyFriction(double delta_t){
	   Double xVnew,xVold,yVold,yVnew;
	   xVold=ball.getVelocity().x();
	   yVold=ball.getVelocity().y();
	   double mu=tickTime/2; // default value should be 0.025 per second
	   double mu2=0.025D; // default value should be 0.025 per line
	   xVnew = xVold * (1 - mu * delta_t - mu2 * Math.abs(xVold) * delta_t);
	   yVnew = yVold * (1 - mu * delta_t - mu2 * Math.abs(yVold) * delta_t);
	   ball.setVelocity(new Vect(xVnew,yVnew));
 	}
	private void applyGravity(double delta_t){
   		if(ball.isStopped()) {
			ball.setVelocity(new Vect(0,0));
		}else{
			Double yVold, yVnew;
			yVold = ball.getVelocity().y();
			double gravity;
			gravity = 5D;
			yVnew = yVold + gravity * delta_t;
			ball.setVelocity(new Vect(ball.getVelocity().x(), yVnew));
		}
	}
	
	private CollisionDetails timeUntilCollision() {
		System.out.println("MODEL: ball is at x="+ball.getXpos()+" y="+ball.getYpos()+" diameter="+ball.getBallRadius()*2);

		//git testing
        Circle circle = ball.getCircle();
        Vect velocity= ball.getVelocity();
        Vect newVelocity=velocity;
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
					squares.get(i).trigger();

					newVelocity = Geometry.reflectWall(squareLines.get(x), velocity);
                }
            }

            for (int x = 0; x < circles.size(); x++) {
                minTimeuntilCollision = Geometry.timeUntilCircleCollision(circles.get(x), circle, velocity);
                if (minTimeuntilCollision <= tickTime) {
                    shortestTime = minTimeuntilCollision;
					System.out.println("Circle Collision");
                    newVelocity = Geometry.reflectCircle(circles.get(x).getCenter(),circle.getCenter(),velocity);
					//Trigger
					squares.get(i).trigger();
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

					System.out.println("Absorber Collision");
					// trigger the gizmo
					absorbers.get(i).storeGizmoBall(ball);



				}
			}

			for (int x = 0; x < circles.size(); x++) {
				minTimeuntilCollision = Geometry.timeUntilCircleCollision(circles.get(x), circle, velocity);
				if (minTimeuntilCollision <= tickTime) {
					shortestTime = minTimeuntilCollision;
					System.out.println("Circle Collision 111");
					newVelocity = Geometry.reflectCircle(circles.get(x).getCenter(),circle.getCenter(),velocity);

					// trigger the gizmo
					absorbers.get(i).storeGizmoBall(ball);
					ball.setStopped(true);
				}
			}
		}



        // check for other Triange collisions
       // System.out.println("Checking triangle colliosions");
        for(int i=0;i<triangles.size();i++) {
        	System.out.println("Checking Triangle number : "+i);
            List<LineSegment> triangleLines = triangles.get(i).getLines();
            List<Circle> triangleCircles = triangles.get(i).getCircles();

            for (int x = 0; x < triangleLines.size(); x++) {
                minTimeuntilCollision = Geometry.timeUntilWallCollision(triangleLines.get(x), circle, velocity);
                System.out.println("Triangle line min time until : "+minTimeuntilCollision);
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
			}else if( !ball.isStopped()){
				ball = moveBallForTime(ball, tuc);
				ball.setVelocity(cd.getVelocity());
			}

		}
		if(ball.isStopped()){
			for(int x=0;x<absorbers.size();x++){
				System.out.println("ffffffffffffff");
				absorbers.get(x).fireBall();
				ball.setStopped(false);
				ball.setVelocity(new Vect(0,-15));
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

	private void keyPressed(String key) {
		Gizmo x=keyConnections.get(key);
		x.fireBall();
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

	public List<Gizmo> getAbsorbers(){
		return absorbers;
	}

	public List<Gizmo> getLeftFlippers(){
		return leftFlippers;
	}

	public List<Gizmo> getRightFlippers(){
		return rightFlippers;
	}

	@Override
	public boolean removeGizmo(Gizmo gizmo) {
		boolean success = false;

		if(gizmos.contains(gizmo)){
			gizmos.remove(gizmo);
			success = true;
			for (Gizmo currentGizmo: gizmos) {
				System.out.println(currentGizmo.getID());
			}
		}
		if(squares.contains(gizmo)){
			squares.remove(gizmo);
			for (Gizmo currentGizmo: squares) {
				System.out.println(currentGizmo.getID());
			}
		}
		if(circles.contains(gizmo)){
			circles.remove(gizmo);
		}
		if(triangles.contains(gizmo)){
			triangles.remove(gizmo);
		}
		if(absorbers.contains(gizmo)){
			absorbers.remove(gizmo);
		}
		if(leftFlippers.contains(gizmo)){
			leftFlippers.remove(gizmo);
		}
		if(rightFlippers.contains(gizmo)){
			rightFlippers.remove(gizmo);
		}

		setChanged();
		notifyObservers();

		return success;
	}

	@Override
	public void setBallSpeed(int x, int y){
		ball.setVelocity(new Vect((double)x,(double)y));
	}

	@Override
	public boolean addGizmo(Gizmo gizmo) {
		boolean success = true;

		gizmo = giveUniqueName(gizmo);

		if(isCellEmpty(gizmo.getXPos(), gizmo.getYPos())){
			// gizmo is in an eligible location
			gizmos.add(gizmo);
			if (gizmo.getType().equals("circle")) {
				circles.add(gizmo);
			} else if (gizmo.getType().equals("square")) {
				squares.add(gizmo);
			} else if (gizmo.getType().equals("triangle")) {
				System.out.println("Adding Triangle");
				triangles.add(gizmo);
			} else if (gizmo.getType().equals("absorber")) {
				absorbers.add(gizmo);
			}
			System.out.println("added "+gizmo.getType()+" gizmo '"+gizmo.getID()+"' at X="+gizmo.getYPos()+", Y="+gizmo.getYPos());
			setChanged();
			notifyObservers();
		}


		return success;
	}

	public Gizmo giveUniqueName(Gizmo gizmo){
		Gizmo uniqueGizmo = gizmo;
		//String originalID = gizmo.getID();
		String uniqueID = gizmo.getID();
		String uniqueIDnum = uniqueID.replaceAll("[A-Za-z]", "");
		int prefixZeroes = (uniqueIDnum.replaceAll("[^0]", "")).length();
		String uniqueIDtype = uniqueID.replaceAll("[^A-Za-z]", "");

		int nextIDNum = 0;

		for(int i = 0; i < gizmos.size(); i++){
			// loop through all gizmos currently in the model
			if(!gizmos.get(i).equals(gizmo)){
				// currentGizmo is not gizmo (used to ensure that the parameter gizmo hasn't already been added to 'gizmos' in the model)

				if(uniqueIDnum.length() == 0){
					// gizmo ID has no ID number, only a type
					uniqueIDnum = "0";
				}

				if(uniqueID.equals(gizmos.get(i).getID())){							// EXAMPLE	uniqueID = "S01"	currentGizmo.getID() = "S01"
					// gizmo ID is the same as the currentGizmo ID
					int uniqueIDnumInt = Integer.parseInt(uniqueIDnum) + 1;			//			uniqueIDnum = "01"	uniqueIDnumInt = 2
					uniqueIDnum = "";												//			uniqueIDnum = ""
					for(int j = 0; j < prefixZeroes; j++) {
						uniqueIDnum = uniqueIDnum + "0";							//			1st pass: uniqueIDnum = "0"
					}
					uniqueIDnum = uniqueIDnum + Integer.toString(uniqueIDnumInt);	//			uniqueIDnumInt = 2	uniqueIDnum = "02"
					uniqueID = uniqueIDtype + uniqueIDnum;
					uniqueGizmo.setID(uniqueID);					//			uniqueIDtype = "S"	uniqueIDnum = "02"

					i = -1;	// reset counter variable as we need to check the whole list again now
				}
			}else{
				// currentGizmo is the SAME as gizmo
			}
		}

//		if(uniqueIDnum > 0){
//			uniqueGizmo.setID(uniqueGizmo.getID() + uniqueIDnum);
//		}

		return uniqueGizmo;
	}

	@Override
	public boolean rotateGizmo(Gizmo gizmo) {
		boolean success = false;

		if(gizmo.getType().equals("triangle")){
			gizmo.rotate();
			setChanged();
			notifyObservers();
			success = true;
		}

		return success;
	}

	@Override
	public Gizmo getGizmoByCoords(int xPos, int yPos) {
		for(Gizmo currentGizmo: gizmos) {
			if((currentGizmo.getXPos() == xPos)&&(currentGizmo.getYPos() == yPos)) {
				// currentGizmo is at these coordinates
				return currentGizmo;
			}else if(currentGizmo.getType().equals("absorber")){
				int absorberXPos1 = ((Absorber)currentGizmo).getXPos();
				int absorberYPos1 = ((Absorber)currentGizmo).getYPos();
				int absorberXPos2 = ((Absorber)currentGizmo).getXPos2();
				int absorberYPos2 = ((Absorber)currentGizmo).getYPos2();
				if((yPos == absorberYPos1)&&(xPos >= absorberXPos1)&&(xPos <= absorberXPos2)) {
					// currentGizmo is an absorber and passes through at least these coordinates
					return currentGizmo;
				}
			}
		}
		// no gizmo here
		return null;
	}

	public boolean isCellEmpty(int xPos, int yPos){
		boolean empty = true;

		for(Gizmo currentGizmo: gizmos) {
			int[][] currentBounds = currentGizmo.getBounds();
			int currentXpos = currentBounds[0][0];
			int currentYpos = currentBounds[0][1];
			int currentXpos2 = currentBounds[1][0];
			int currentYpos2 = currentBounds[1][1];
			if((currentXpos == xPos) && (currentYpos == yPos)){
				// cell is already occuped by currentGizmo
				System.out.println("cell is already occuped by currentGizmo");
				empty = false;
			}else if(((currentGizmo.getType().equals("absorber")) || (currentGizmo.getType().equals("leftflipper")) ||(currentGizmo.getType().equals("rightflipper")))
					&&((xPos >= currentXpos) && (xPos < currentXpos2))
					&&(yPos >= currentYpos) && (yPos < currentYpos2)){
				// cell is already occupied by an absorber or a leftflipper or a rightflipper
				System.out.println("cell is already occupied by a gizmo of type "+currentGizmo.getType());
				empty = false;
			}
		}
		return empty;
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
