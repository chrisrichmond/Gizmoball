package Model;

import Model.gizmos.Gizmo;
import utilities.Observable;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface ModelAPI extends Observable{

    /**
     * Gets a list of all Gizmo objects in the ModelAPI object
     * @return list of Gizmos
     */
    public List<Gizmo> getGizmos();

    /**
     * Gets a reference to the BallImpl object
     * @return a BallImpl object
     */
    public Ball getBall();

    /**
     * Gets a boolean value to indicate that either the game is in build mode or it is not
     * @return true if the game is in build mode or false if the game is in run mode
     */
    public boolean isBuildMode();

    /**
     *
     * @param xPos
     * @param yPos
     * @return null if no gizmo is found at specified coordinates, otherwise return gizmo at these coords
     */
    public Gizmo getGizmoByCoords(int xPos, int yPos);

    public void clear();

    public Walls getWalls();

    public HashMap<Gizmo, ArrayList<Gizmo>> getGizmoConnections();

    public void moveBall();

    public void setBallSpeed(int x, int y);

    public Gizmo getGizmoByID(String ID);

    public double getMu();

    public double getMu2();

    public void setMu(double mu);

    public void setMu2(double mu2);

    public boolean isCellEmpty(int xPos, int yPos);

    public void setGravity(double gravity);

    public void setFriction(double friction);

    public void addGizmoConnection(Gizmo gizmo, Gizmo gizmo2);

    public boolean hasGizmoConnection(Gizmo gizmo);

    /**
     *
     * @param gizmo
     * @return false if cell location is already occupied
     */
    public boolean addGizmo(Gizmo gizmo);

    public boolean rotateGizmo(Gizmo gizmo);

    public boolean removeGizmo(Gizmo gizmo);

    public void loadFile(String filename) throws FileNotFoundException;

    public void saveFile(String filename);

    public void replaceBall(Ball ball);

    public List<Gizmo> getSquares();

    public List<Gizmo> getCircles();

    public List<Gizmo> getTriangles();

    public List<Gizmo> getAbsorbers();

    public List<Gizmo> getLeftFlippers();

    public List<Gizmo> getRightFlippers();

    public double getGravity();

    public double getFriction();

    public boolean SpinFlipper(Gizmo flipper, double degrees);
}
