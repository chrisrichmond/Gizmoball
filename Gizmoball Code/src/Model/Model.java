package Model;

import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import gui.View;
import Model.gizmos.*;
import utilities.GizmoConstants;
import utilities.Observer;
import physics.*;

/**
 * Model class is the main class in the model package
 * Class implements the ModelAPI for view communication
 * Model class details interaction with gizmo classes for use in the UI
 * @author MW_Wed #4
 */
public class Model implements ModelAPI {

	private List<Observer> observers;
	HashMap<Gizmo,ArrayList<Gizmo>> gizmoConnections = new HashMap<>();
	HashMap<KeyEvent,ArrayList<Gizmo>> keyConnections = new HashMap<>();
	private boolean changed;
	private boolean isBuildMode;
	private Ball ball;
	private List<Gizmo> gizmos;
	private List<Gizmo> squares;
	private List<Gizmo> circles;
	private ArrayList<Gizmo> triangles;
	private List<Gizmo> absorbers;
	private List<Gizmo> leftFlippers;
	private List<Gizmo> rightFlippers;
	private Walls walls;
 	private GizmoFileHandler fileHandler;
 	private double tickTime;
 	private double gravity;
 	private double friction;
	private double mu=0.025D; // default value should be 0.025 per second
	private double mu2=0.025D; // default value should be 0.025 per line


	/**
	 * Model constructor where the fields are initialised
	 * Contains Java collections used to store different Gizmo's
	 * various methods in this class reference these fields
	 */
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
		this.gravity=2.0D;
		this.friction = 0.05D; //0.05 original

		this.ball = new BallImpl("B",18.5F, 10.0F, 0.0D, 0.0D);
		this.walls = new Walls(0,0,20,20);
	}

	/**
	 * @modifies this
	 * @param ball object instance of Ball IFace
	 * effects: sets ball == new ball reference
	 */
 	public void replaceBall(Ball ball){
		this.ball = ball;
	}

	/**
	 * method call wrapped in try catch
	 * @param filename var will be loaded from file system
	 * @throws FileNotFoundException
	 */
 	public void loadFile(String filename) throws FileNotFoundException{
	    clear();
	    fileHandler.loadFromFile(filename);
	    setChanged();
	    notifyObservers();
    }

	/**
	 *@param filename  will != null
	 */
	@Override
	public void saveFile(String filename) {
		fileHandler.saveToFile(filename);

	}

	/**
	 * used to clear the data structure of each gizmo type for board clearance
	 */
	public void clear(){
 	    gizmos.clear();
 	    squares.clear();
 	    triangles.clear();
 	    circles.clear();
 	    absorbers.clear();
 	    leftFlippers.clear();
 	    rightFlippers.clear();
 	    gizmoConnections.clear();
 	    keyConnections.clear();
 	    setChanged();
 	    notifyObservers();
    }

	/**
	 * requires delta_t == double
	 * abstract Invariant delta_t > 0
	 * @param delta_t
	 */
	private void applyFriction(double delta_t){
		System.out.println("################## " + delta_t);
		Double xVnew,xVold,yVold,yVnew;
		xVold=ball.getVelocity().x();
		yVold=ball.getVelocity().y();

		xVnew = xVold * (1 - mu * delta_t - mu2 * Math.abs(xVold) * delta_t);
		yVnew = yVold * (1 - mu * delta_t - mu2 * Math.abs(yVold) * delta_t);
		ball.setVelocity(new Vect(xVnew,yVnew));
	}

	private void applyGravity(){
		if(ball.isStopped()) {
			ball.setVelocity(new Vect(0,0));
		}else{
			Double yVold, yVnew;
			yVold = ball.getVelocity().y();
			yVnew = yVold + gravity;
			ball.setVelocity(new Vect(ball.getVelocity().x(), yVnew));
		}
	}


	/**
	 * calculates time until collision using the gizmo types
	 * @return new CollisionDetails with @params :  newVelocity && shortestTime
	 */
	private CollisionDetails timeUntilCollision() {
		System.out.println("MODEL: ball is at x="+ball.getXpos()+" y="+ball.getYpos()+" diameter="+ball.getBallRadius()*2);

		//git testing
		Circle circle = ball.getCircle();
		Vect velocity= ball.getVelocity();
		Vect newVelocity=velocity;
		double shortestTime =5000D;
		double minTimeuntilCollision;
		boolean hasCollided = false;
		String collisionType = "nothing";

		System.out.println("MODEL: ball velocity is "+ball.getVelocity());
		// check for collision on gizmo walls
		List<LineSegment> wls= walls.getLineSegments();
		for(int i=0;i<wls.size();i++){

			minTimeuntilCollision = Geometry.timeUntilWallCollision(wls.get(i),circle,velocity);
			if(minTimeuntilCollision <= tickTime){
				shortestTime = minTimeuntilCollision;
				newVelocity = Geometry.reflectWall(wls.get(i),velocity);
				System.out.println("Wall Collision");
				collisionType = "wall";
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
                    if(hasGizmoConnection(squares.get(i))) {
                        ArrayList<Gizmo> connected = gizmoConnections.get(squares.get(i));
                        for (int a = 0; a < connected.size(); a++) {
                            connected.get(a).trigger();
                        }
                    }
                    squares.get(i).trigger();
					collisionType = "square";
					newVelocity = Geometry.reflectWall(squareLines.get(x), velocity);
				}
			}

			for (int x = 0; x < circles.size(); x++) {
				minTimeuntilCollision = Geometry.timeUntilCircleCollision(circles.get(x), circle, velocity);
				if (minTimeuntilCollision <= tickTime) {
					shortestTime = minTimeuntilCollision;
					System.out.println("Circle Collision");
					newVelocity = Geometry.reflectCircle(circles.get(x).getCenter(),circle.getCenter(),velocity);
					collisionType = "square";
					//Trigger
                    if(hasGizmoConnection(squares.get(i))) {
                        ArrayList<Gizmo> connected = gizmoConnections.get(squares.get(i));
                        for (int a = 0; a < connected.size(); a++) {
                            connected.get(a).trigger();
                        }
                    }
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

					collisionType = "absorber";


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
					collisionType = "absorber";
				}
			}
		}



		// check for other Triange collisions
		// System.out.println("Checking triangle colliosions");
		for(int i=0;i<triangles.size();i++) {
			List<LineSegment> triangleLines = triangles.get(i).getLines();
			List<Circle> triangleCircles = triangles.get(i).getCircles();

			for (int x = 0; x < triangleLines.size(); x++) {
				minTimeuntilCollision = Geometry.timeUntilWallCollision(triangleLines.get(x), circle, velocity);
				if (minTimeuntilCollision <= tickTime) {
					shortestTime = minTimeuntilCollision;
					System.out.println("Triangle Collision");
					newVelocity = Geometry.reflectWall(triangleLines.get(x), velocity);

					collisionType = "triangle";

					// trigger the gizmo
                    if(hasGizmoConnection(triangles.get(i))) {
                        ArrayList<Gizmo> connected = gizmoConnections.get(triangles.get(i));
                        for (int a = 0; a < connected.size(); a++) {
                            connected.get(a).trigger();
                        }
                    }
                    triangles.get(i).trigger();
				}
			}

			for (int x = 0; x < triangleCircles.size(); x++) {
				minTimeuntilCollision = Geometry.timeUntilCircleCollision(triangleCircles.get(x), circle, velocity);
				if (minTimeuntilCollision <= tickTime) {
					shortestTime = minTimeuntilCollision;
					System.out.println("Triangle Collision");
					newVelocity = Geometry.reflectCircle(triangleCircles.get(x).getCenter(),circle.getCenter(),velocity);
                    if(hasGizmoConnection(triangles.get(i))) {
                        ArrayList<Gizmo> connected = gizmoConnections.get(triangles.get(i));
                        for (int a = 0; a < connected.size(); a++) {
                            connected.get(a).trigger();
                        }
                    }
                    triangles.get(i).trigger();
					collisionType = "triangle";

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
                if(hasGizmoConnection(circles.get(i))) {
                    ArrayList<Gizmo> connected = gizmoConnections.get(circles.get(i));
                    for (int a = 0; a < connected.size(); a++) {
                        connected.get(a).trigger();
                    }
                }
                circles.get(i).trigger();
				collisionType = "circle";
			}
		}

		//   check for Circular Bumper collisions


		System.out.println("Shortest Time is: "+shortestTime);
		return new CollisionDetails(newVelocity, shortestTime, collisionType);
	}


	/**
	 * fires ball from absorber && makes ball moved based on Time Until Collision
	 */
	public void moveBall() {



		if(ball != null && !ball.isStopped()){
			CollisionDetails cd = timeUntilCollision();
			double tuc = cd.getTuc();
			if(!cd.getCollisionType().equals("absorber")) {
				if (tuc >= friction) {

					ball = moveBallForTime(ball, friction);
					applyFriction(friction);

				} else if (!ball.isStopped()) {

					ball = moveBallForTime(ball, tuc);
					ball.setVelocity(cd.getVelocity());
					applyFriction(tuc);
				}
			}
			applyGravity();

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

		return ball;

	}

	public Gizmo getGizmoByID(String ID){
		for(Gizmo currentGizmo: gizmos){
			if(currentGizmo.getID().equals(ID)){
				return currentGizmo;
			}
		}
		return null;
	}

	private void keyPressed(KeyEvent key) {
		ArrayList<Gizmo> x;
		x=keyConnections.get(key);
		for(int i=0;i<x.size();i++){
			x.get(i).trigger();
		}
	}

	/**
	 *
	 * @return ball reference var
	 */
	@Override
	public Ball getBall() {
		return ball;
	}

	/**
	 *
	 * @return true if user in build mode
	 */
	@Override
	public boolean isBuildMode() {
		return isBuildMode;
	}

	/**
	 *
	 * @return walls var
	 */
	@Override
	public Walls getWalls() {
		return walls;
	}

	@Override
	public HashMap<Gizmo, ArrayList<Gizmo>> getGizmoConnections() {
		return gizmoConnections;
	}

	/**
	 *
	 * @return List of squares gizmo type
	 */
	public List<Gizmo> getSquares() {
		return squares;
	}

	/**
	 *
	 * @return List of circle gizmo type
	 */
	public List<Gizmo> getCircles() {
		return circles;
	}

	/**
	 *
	 * @return List of triangle gizmo type
	 */
	public List<Gizmo> getTriangles() {
		return triangles;
	}

	/**
	 *
	 * @return List of absorber gizmo type
	 */
	public List<Gizmo> getAbsorbers(){
		return absorbers;
	}

	/**
	 *
	 * @return Left Flipper
	 */
	public List<Gizmo> getLeftFlippers(){
		return leftFlippers;
	}

	/**
	 *
	 * @return Right Flipper
	 */
	public List<Gizmo> getRightFlippers(){
		return rightFlippers;
	}

	/**
	 *
	 * @param flipper will rotate left && right flipper
	 * @param degrees rotates based on degree value
	 * @return boolean value
	 */
	@Override
	public boolean SpinFlipper(Gizmo flipper, double degrees) {
		if(gizmos.contains(flipper)){
			if(leftFlippers.contains(flipper)){
				((LFlipper)flipper).spin(degrees);
			}else if(rightFlippers.contains(flipper)){

			}
		}

		return false;
	}


	/**
	 *
	 * @param gizmo is removed from board based on user choice
	 * @return true if successfully removed
	 */
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

	/**
	 *
	 * @param x co-ord in game board
	 * @param y co-ord in game board
	 */
	@Override
	public void setBallSpeed(int x, int y){
		ball.setVelocity(new Vect((double)x,(double)y));
	}

	/**
	 *
	 * @param gizmo added to empty cell space
	 * @return true if successfully added
	 */
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
			} else if (gizmo.getType().equals("leftflipper")) {
				leftFlippers.add(gizmo);
			} else if (gizmo.getType().equals("rightflipper")) {
				rightFlippers.add(gizmo);
			}
			System.out.println("added "+gizmo.getType()+" gizmo '"+gizmo.getID()+"' at X="+gizmo.getYPos()+", Y="+gizmo.getYPos());
			setChanged();
			notifyObservers();
		}else{
			success = false;
		}


		return success;
	}


	/**
	 *
	 * @param gizmo will be assigned a unique name
	 * @return Gizmo
	 */
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

	/**
	 *
	 * @param gizmo will rotate triangle in build mode
	 * @return true if triangle rotated successfully
	 */
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

	/**
	 *
	 * @param xPos in board
	 * @param yPos in board
	 * @return Gizmo at specified board position
	 */
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

	/**
	 *
	 * @param xPos in board
	 * @param yPos in board
	 * @return true if cell space empty
	 */
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
				System.out.println("cell is already occupied by a gizmo of type "+currentGizmo.getType());
				//view.updateMessagePanel("Cell Occupied!!");
				empty = false;
			}else if((currentGizmo.getType().equals("absorber")) || (currentGizmo.getType().equals("leftflipper")) ||(currentGizmo.getType().equals("rightflipper"))){
				if (((xPos >= currentXpos) && (xPos < currentXpos2))
						&& (yPos >= currentYpos) && (yPos < currentYpos2)) {
					// cell is already occupied by an absorber or a leftflipper or a rightflipper
					System.out.println("cell is already occupied by a gizmo of type " + currentGizmo.getType());
					empty = false;
				}else if(((currentGizmo.getType().equals("leftflipper")) || (currentGizmo.getType().equals("rightflipper")))/* && ((gizmo.getType().equals("leftflipper"))||(gizmo.getType().equals("rightflipper"))||(gizmo.getType().equals("absorber")))*/
						&& (((GizmoConstants.flipperXbound + xPos > currentXpos) && (xPos < currentXpos2))
						&& (GizmoConstants.flipperYbound + yPos > currentYpos) && (yPos < currentYpos2))){
						// TODO fix the fact that standard gizmos cannot be placed to the left of LFlippers

					System.out.println("flipper bounds cannot be placed over existing collision area (flippers take up an area of 2Lx2L)");
					System.out.println("cell is already occupied by a gizmo of type " + currentGizmo.getType());
					empty = false;
				}
			}
		}
		return empty;
	}


	public void addGizmoConnection(Gizmo gizmo, Gizmo gizmo2) {
		if(gizmoConnections.containsKey(gizmo)){
			System.out.println("adding gizmo connection");
			gizmoConnections.get(gizmo).add(gizmo2);
		}else{
			System.out.println("adding gizmo connection");
			gizmoConnections.put(gizmo, new ArrayList<>());
			gizmoConnections.get(gizmo).add(gizmo2);
		}

	}
	public void removeGizmoConnection(Gizmo gizmo, Gizmo gizmo2) {
		if(gizmoConnections.containsKey(gizmo)){
			System.out.println("removing gizmo connection");
			gizmoConnections.get(gizmo).remove(gizmo2);
		}else{
			System.out.println("nothing to remove");
		}

	}
	public boolean hasGizmoConnection(Gizmo gizmo) {

		if(gizmoConnections.containsKey(gizmo)){
			return true;
		}
		return false;
	}

	public void addKeyConnection(KeyEvent key,Gizmo gizmo1) {
		if(keyConnections.containsKey(key)){
			System.out.println("adding key connection");
			keyConnections.get(key).add(gizmo1);
		}else{
			System.out.println("adding key connection");
			keyConnections.put(key, new ArrayList<>());
			keyConnections.get(key).add(gizmo1);
		}


	}
	public void removeKeyConnection(KeyEvent key,Gizmo gizmo1) {
		if(keyConnections.containsKey(key)){
			System.out.println("removing key connection");
			keyConnections.get(key).remove(gizmo1);
		}else{
			System.out.println("Nothing to remove");

		}


	}
	public boolean hasKeyConnection(KeyEvent key) {

		if(keyConnections.containsKey(key)){
			return true;
		}
		return false;

	}

	@Override
	public void setGravity(double gravity) {
		this.gravity = gravity;
		System.out.println("======= Set Gravity       " + this.gravity);
	}

	@Override
	public double getGravity(){
		return gravity;
	}

	@Override
	public void setMu(double mu) {
		this.mu = mu;
	}

	@Override
	public void setMu2(double mu2) {
		this.mu2 = mu2;
	}

	@Override
	public void setFriction(double frict) {
		this.friction = frict;
		System.out.println("======= Set Friction       " + this.friction);
	}


	@Override
	public double getFriction(){
		return friction;
	}

	@Override
	public double getMu(){
		return mu;
	}

	@Override
	public double getMu2(){
		return mu2;
	}


	/**
	 *
	 * @param o the Observer object which is subscribing
	 */
	@Override
	public void attach(Observer o) {
		observers.add(o);
	}

	/**
	 *
	 * @param o the Observer object which is unsubscribing
	 */
	@Override
	public void detach(Observer o) {
		observers.remove(o);
	}

	/**
	 * modifies boolean changed value
	 */
	@Override
	public void setChanged() {
		changed = true;
	}

	/**
	 * notify observers to be updated based on data being changed
	 */
	@Override
	public void notifyObservers() {
		if(changed){
			for (Observer currentObserver: observers){
				currentObserver.update();
			}
			changed = false;
		}
	}

	/**
	 *
	 * @return List of Gizmos
	 */
	@Override
	public List<Gizmo> getGizmos(){
		return gizmos;
	}

}
