package Testing;
import Model.Model;
import Model.Ball;
import Model.BallImpl;
import Model.ModelAPI;
import Model.gizmos.CircularBumper;
import Model.gizmos.Gizmo;
import Model.gizmos.SquareBumper;
import Model.gizmos.TriangularBumper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ModelTest {

    private ModelAPI m1;
    private List<Gizmo> gizmos;
    private Ball ball;
    private Gizmo circle = new CircularBumper("5", 5, 5);
    private Gizmo triangle = new TriangularBumper("2", 12, 12);
    private Gizmo square = new SquareBumper("1", 1, 1);

    @BeforeAll
    public  void setUp(){
        m1 = new Model();
        ball = new BallImpl("B",18.5F, 10.0F, 0.0D, 0.0D);
    }

    @Test
    public void testGetCircles(){
        m1.addGizmo(circle);
        assertEquals(m1.getCircles().size(), 1);
    }

    @Test
    public void testRemoveCircle(){
        m1.removeGizmo(circle);
        assertEquals(m1.getCircles().size(), 0);
    }

    @Test
    public void testGetSquares(){
        m1.addGizmo(square);
        assertEquals(m1.getSquares().size(), 1);
    }

    @Test
    public void testRemoveSquare(){
        m1.removeGizmo(square);
        assertEquals(m1.getSquares().size(), 0);
    }

    @Test
    public void testGetTriangle(){
        m1.addGizmo(triangle);
        assertEquals(m1.getTriangles().size(), 1);
    }

    @Test
    public void testRemoveTriangle(){
        m1.removeGizmo(triangle);
        assertEquals(m1.getTriangles().size(), 0);
    }

    @Test
    public void testRotateTriangle(){
        assertEquals(m1.rotateGizmo(triangle), true);
    }

    @Test
    public void testGetBallPosition(){
        assertEquals(m1.getBall().getXpos(), 18.5F);
        assertEquals(m1.getBall().getYpos(), 10.0F);
    }

    @Test
    public void testGetBallRadius(){
        assertEquals(m1.getBall().getBallRadius(), 0.25D);
    }

    @Test
    public void testChangeBallColour(){
        assertEquals(m1.getBall().getColour(), Color.black);
        m1.getBall().setColour(Color.green);
        assertEquals(m1.getBall().getColour(), Color.green);
    }

    @Test
    public void testBallStoppedandID(){
        m1.getBall().setStopped(true);
        assertEquals(m1.getBall().isStopped(), true);
        assertEquals(m1.getBall().getID(), "B");
    }

    @Test
    public void testCellSpaceNotEmpty(){
        m1.addGizmo(circle);
        assertEquals(m1.isCellEmpty(5, 5), false);

        m1.addGizmo(triangle);
        assertEquals(m1.isCellEmpty(12, 12), false);

        m1.addGizmo(square);
        assertEquals(m1.isCellEmpty(1, 1), false);
    }

    @Test
    public void testCellSpaceisEmpty(){
        assertEquals(m1.isCellEmpty(9, 9), true);
    }


}
