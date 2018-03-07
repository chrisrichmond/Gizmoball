package Model;

import Model.gizmos.Gizmo;
import observerpattern.Observable;

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

    public Walls getWalls();

    public void moveBall();

    public void setBallSpeed(int x, int y);

    public void addGizmo(Gizmo gizmo);

    public void loadFile(String filename);

    public void replaceBall(Ball ball);

//    public void addCircle(CircularBumperGizmo circle);
//
//    public void addSquare(SquareBumperGizmo square);
//
//    public void addTriangle(TriangularBumperGizmo triangle);
}
