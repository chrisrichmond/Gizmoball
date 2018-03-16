package Testing;
import Model.Model;
import Model.Ball;
import Model.Walls;
import Model.BallImpl;
import Model.ModelAPI;
import Model.gizmos.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import physics.Angle;
import physics.LineSegment;
import physics.Vect;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ModelTest {

    private ModelAPI m1;
    private List<Gizmo> gizmos;
    private Ball ball;
    private Gizmo circle = new CircularBumper("5", 5, 5);
    private Gizmo triangle = new TriangularBumper("2", 12, 12);
    private Gizmo square = new SquareBumper("1", 1, 1);
    private Gizmo absorb = new Absorber("AB", 0, 0 , 10, 0);
    private Walls wall = new Walls(0, 0, 20, 20);

    @BeforeAll
    public  void setUp(){
        m1 = new Model();
        ball = new BallImpl("B",18.5F, 10.0F, 0.0D, 0.0D);
    }

    @Test
    public void testGetCircles(){
        m1.addGizmo(circle);
        assertEquals(1, m1.getCircles().size());
    }

    @Test
    public void testRemoveCircle(){
        m1.removeGizmo(circle);
        assertEquals(0, m1.getCircles().size());
    }


    @Test
    public void testGetAbsorber(){
        m1.addGizmo(absorb);
        assertEquals(1, m1.getAbsorbers().size());
    }

    @Test
    public void testRemoveAbsorber(){
        m1.removeGizmo(absorb);
        assertEquals(0, m1.getAbsorbers().size());
    }

    @Test
    public void testGetSquares(){
        m1.addGizmo(square);
        assertEquals(1, m1.getSquares().size());
    }

    @Test
    public void testSquareLineSegs(){

        List<LineSegment> segs = square.getLines();

        LineSegment l1 = new LineSegment(1, 1, 1, 2);
        LineSegment l2 = new LineSegment(2, 1, 2, 2);
        assertEquals(l1, segs.get(0));
        assertEquals(l2, segs.get(1));
    }

    @Test
    public void testRemoveSquare(){
        m1.removeGizmo(square);
        assertEquals(0, m1.getSquares().size());
    }

    @Test
    public void testGetTriangle(){
        m1.addGizmo(triangle);
        assertEquals(1, m1.getTriangles().size());
    }

    @Test
    public void testRemoveTriangle(){
        m1.removeGizmo(triangle);
        assertEquals(0, m1.getTriangles().size());
    }

    @Test
    public void testRotateTriangle(){
        assertEquals(true, m1.rotateGizmo(triangle));
    }

    @Test
    public void testGetBallPosition(){
        assertEquals(18.5F, m1.getBall().getXpos());
        assertEquals(10.0F, m1.getBall().getYpos());
    }

    @Test
    public void testGetBallRadius(){
        assertEquals(0.25D, m1.getBall().getBallRadius());
    }

    @Test
    public void testChangeBallColour(){
        assertEquals(Color.black, m1.getBall().getColour());
        m1.getBall().setColour(Color.green);
        assertEquals(Color.green, m1.getBall().getColour());
    }

    @Test
    public void testBallStoppedAndID(){
        m1.getBall().setStopped(true);
        assertEquals(true, m1.getBall().isStopped());
        assertEquals("B", m1.getBall().getID());
    }

    @Test
    public void testCellSpaceNotEmpty(){
        m1.addGizmo(circle);
        assertEquals(false, m1.isCellEmpty(5, 5));

        m1.addGizmo(triangle);
        assertEquals(false, m1.isCellEmpty(12, 12));

        m1.addGizmo(square);
        assertEquals(false, m1.isCellEmpty(1, 1));
    }

    @Test
    public void testCellSpaceIsEmpty(){
        assertEquals(true, m1.isCellEmpty(9, 9));
    }

    @Test
    public void testReplaceBall(){
        Ball ball2 = new BallImpl("B2",10.5F, 15.0F, 0.0D, 0.0D);
        assertNotEquals(ball2, m1.getBall());

        m1.replaceBall(ball2);
        assertEquals(ball2, m1.getBall());
        m1.replaceBall(ball);
        assertEquals(ball, m1.getBall());
    }

    @Test
    public void testSetAndGetVelocity(){
        double test1 = 5;
        Angle agl = new Angle(test1);
        Vect v1 = new Vect(agl);

        m1.getBall().setVelocity(v1);
        assertEquals(v1, m1.getBall().getVelocity());
    }

    @Test
    public void testGetGizmoCoords(){
        m1.addGizmo(circle);
        assertEquals(circle, m1.getGizmoByCoords(5, 5));
    }

    @Test
    public void testBoolBuildMode(){
        assertEquals(false, m1.isBuildMode());
        assertTrue(!m1.isBuildMode());
    }

    @Test
    public void testGetWalls(){
        assertEquals(wall.getLineSegments(), m1.getWalls().getLineSegments());
        assertEquals(wall.getHeight(), m1.getWalls().getHeight());
        assertEquals(wall.getWidth(), m1.getWalls().getWidth());
    }

    @Test
    public void testInstanceOfClass(){
        m1.addGizmo(square);
        assertTrue(square instanceof  SquareBumper);
        m1.addGizmo(circle);
        assertTrue(circle instanceof CircularBumper);
        m1.addGizmo(triangle);
        assertTrue(triangle instanceof  TriangularBumper);
        m1.addGizmo(absorb);
        assertTrue(absorb instanceof Absorber);
        m1.clear();
    }

    @Test
    public void testGizmoClearBoard(){
        m1.addGizmo(circle);
        assertEquals(1, m1.getCircles().size());
        assertTrue(1 == m1.getCircles().size());
        m1.clear();
        assertEquals(0, m1.getCircles().size());
        assertTrue(0 == m1.getCircles().size());
    }

    @Test
    public void testSaveAndLoad(){
        m1.saveFile("default.gizmo");
        try {
            m1.loadFile("default.gizmo");
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("file not found");
        }
        m1.clear();
    }

    @AfterAll
    public void tearDown(){
        m1 = null;
    }
}
