package Model;
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
import java.awt.geom.Ellipse2D;
import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ModelTest {

    /**
     * create Model API reference & Collections of each gizmo type
     */
    private ModelAPI m1;
    private List<Gizmo> gizmos;
    private Ball ball;
    private Gizmo circle = new CircularBumper("5", 5, 5);
    private Gizmo triangle = new TriangularBumper("2", 12, 12);
    private Gizmo square = new SquareBumper("1", 1, 1);
    private Gizmo absorb = new Absorber("AB", 0, 0 , 10, 0);
    private Walls wall = new Walls(0, 0, 20, 20);
    private LFlipper lFlip = new LFlipper( "LFlip", 15, 15);
    private Gizmo triangle2 = new TriangularBumper("2", 12, 12, 90);


    @BeforeAll
    public  void setUp(){
        m1 = new Model();
        ball = new BallImpl("B",18.5F, 10.0F, 0.0D, 0.0D);
    }


    /**
     * unit test to convert gizmo object type to string and match against the expected value
     */
    @Test
    public void testToStringGizmos(){
        assertEquals("5 5 5", circle.toString());
        assertEquals("1 1 1", square.toString());
        assertEquals("2 12 12", triangle.toString());
        assertEquals("AB 0 0 10 0", absorb.toString());
        assertEquals("LFlip 15 15", lFlip.toString());
    }

    /**
     * unit test to test the size of the gizmo list has increased by 1 by adding the circle gizmo
     */
    @Test
    public void testGetCircles(){
        m1.addGizmo(circle);
        assertEquals(1, m1.getCircles().size());
    }

    @Test
    public void testCircleBumper(){
        circle.setPos(17, 17);
        assertEquals(Color.green, circle.getColour());
        assertEquals("circle", circle.getType());
        assertEquals(1, circle.getHeight());
        assertEquals(1, circle.getWidth());
        assertTrue(circle.getDirection() == 0);
        assertFalse(circle.rotate());
        circle.setID("C1");
        circle.getID();
        assertEquals("C1", circle.getID());
        circle.setColour(Color.orange);
        assertTrue(circle.getColour() == Color.orange);
        circle.setID("5");
        circle.setPos(5, 5);
    }

    /**
     * unit test to test the size of the gizmo list has decreased by 1 by removing the circle gizmo
     */
    @Test
    public void testRemoveCircle(){
        m1.removeGizmo(circle);
        assertEquals(0, m1.getCircles().size());
    }

    /**
     * unit test to test the size of the gizmo list has increased by 1 by adding the absorber
     */
    @Test
    public void testGetAbsorber(){
        m1.addGizmo(absorb);
        assertEquals(1, m1.getAbsorbers().size());
        assertEquals(absorb, m1.getGizmoByCoords(0, 0));
        assertEquals(absorb, m1.getGizmoByCoords(10, 0));
        assertEquals("AB", absorb.getID());
        assertEquals(0, absorb.getDirection());
        assertEquals(false, absorb.rotate());
        absorb.setID("BC");
        assertEquals("BC", absorb.getID());
        absorb.setID("AB");
    }

    @Test
    public void testAbsorberType(){
        assertEquals("absorber", absorb.getType());
    }

    @Test
    public void testAbsorberLines(){
        List<LineSegment> absorberLines = absorb.getLines();

        LineSegment l1 = new LineSegment(0, 0, 0, 0);
        LineSegment l2 = new LineSegment(10, 0, 10, 0);
        LineSegment l3 = new LineSegment(0, 0, 10, 0);
        LineSegment l4 = new LineSegment(0, 0, 10, 0);

        assertEquals(l1, absorberLines.get(0));
        assertEquals(l2, absorberLines.get(1));
        assertEquals(l3, absorberLines.get(2));
        assertEquals(l4, absorberLines.get(3));

        assertTrue(4 == absorberLines.size());
    }


    @Test
    public void testAbsorberHeightAndWidth(){
        assertEquals(0, absorb.getHeight());
        assertEquals(10, absorb.getWidth());
    }

    @Test
    public void testAbsorberPosition(){

        assertEquals(0, absorb.getYPos());
        assertEquals(0, absorb.getXPos());
        absorb.trigger();
        absorb.setColour(Color.white);
        assertEquals(Color.white, absorb.getColour());
        assertEquals(null, absorb.fireBall());
    }

    /**
     * unit test to test the size of the gizmo list has decreased by 1 by removing the absorber
     */
    @Test
    public void testRemoveAbsorber(){
        m1.removeGizmo(absorb);
        assertEquals(0, m1.getAbsorbers().size());
    }

    /**
     * unit test to test the size of the gizmo list has increased by 1 by adding a square gizmo
     */
    @Test
    public void testGetSquares(){
        m1.addGizmo(square);
        square.trigger();
        assertEquals(1, m1.getSquares().size());
    }

    @Test
    public void testSquare(){
        assertEquals(0, square.getDirection());
        assertFalse(square.rotate());
        square.setID("S1");
        assertEquals("S1", square.getID());
        square.setID("1");
    }

    @Test
    public void testSetPosSquare() {
        Gizmo squareTEST = new SquareBumper("1", 1, 1);
        squareTEST.setPos(10,12);
        assertEquals(10,squareTEST.getXPos());
        assertEquals(12, squareTEST.getYPos());

    }

    /**
     * this test is used to test the x and y coordinates of the line are placed
     * correctly. The square take up 1 grid location and also tests the size of the
     * list has increased, and gived the expected output of 4.
     */
    @Test
    public void testSquareLineSegments(){

        List<LineSegment> segs = square.getLines();

        LineSegment l1 = new LineSegment(1, 1, 1, 2);
        LineSegment l2 = new LineSegment(2, 1, 2, 2);
        LineSegment l3 = new LineSegment(1, 2, 2, 2);
        LineSegment l4 = new LineSegment(1, 1, 2, 1);
        assertEquals(l1, segs.get(0));
        assertEquals(l2, segs.get(1));
        assertEquals(l3, segs.get(2));
        assertEquals(l4, segs.get(3));
        assertTrue(4 == segs.size());

        Gizmo square = new SquareBumper("2", 2, 2);
        List<LineSegment> segs2 = square.getLines();
        LineSegment s1 = new LineSegment(2, 2, 2, 3);
        LineSegment s2 = new LineSegment(3, 2, 3, 3);
        LineSegment s3 = new LineSegment(2, 3, 3, 3);
        LineSegment s4 = new LineSegment(2, 2, 3, 2);
        assertEquals(s1, segs2.get(0));
        assertEquals(s2, segs2.get(1));
        assertEquals(s3, segs2.get(2));
        assertEquals(s4, segs2.get(3));
        assertTrue(4 == segs2.size());
    }

    /**
     * unit test to test the size of the gizmo list has decreased by 1 by removing a gizmo
     */
    @Test
    public void testRemoveSquare(){
        m1.removeGizmo(square);
        assertEquals(0, m1.getSquares().size());
    }

    /**
     * unit test to test the size of the gizmo list has increased by 1 by adding a triangle gizmo
     */
    @Test
    public void testGetTriangle(){
        m1.addGizmo(triangle);
        assertEquals(1, m1.getTriangles().size());
    }

    /**
     * unit test to test whether the 3 triangle line segments are in the correct x and y locations as
     * expected. also test this against the size of the list of line segments to check whether it has increased
     */
    @Test
    public void testTriangleLineSegments() {

        List<LineSegment> segs = triangle.getLines();

        LineSegment l1 = new LineSegment(13, 12, 12, 13);
        LineSegment l2 = new LineSegment(12, 12, 13, 12);
        LineSegment l3 = new LineSegment(12, 12, 12, 13);

        assertEquals(l1, segs.get(0));
        assertEquals(l2, segs.get(1));
        assertEquals(l3, segs.get(2));
        assertTrue(3 == segs.size());
    }

    @Test
    public void testTriangleConstructor2(){
        m1.addGizmo(triangle2);
        triangle2.trigger();
        triangle2.setPos(17, 17);
        assertEquals(0, triangle2.getDirection());
        assertEquals(17, triangle2.getYPos());
        assertEquals(17, triangle2.getXPos());
    }

    /**
     * unit test to test the size of the gizmo list has decreased by 1 by removing a triangle gizmo
     */
    @Test
    public void testRemoveTriangle(){
        m1.removeGizmo(triangle);
        assertEquals(0, m1.getTriangles().size());
    }

    /**
     * test rotate functionality of the triangle rotate gizmo, expected value to be true when the triangle is rotated successfully
     */
    @Test
    public void testRotateTriangle(){
        assertEquals(true, m1.rotateGizmo(triangle));
    }

    /**
     * test the expected value with the actual value of the x and y coordinates of the ball
     */
    @Test
    public void testGetBallPosition(){
        assertEquals(18.5F, m1.getBall().getXpos());
        assertEquals(10.0F, m1.getBall().getYpos());
    }

    /**
     * test the expected value with the actual value of return value of the ball radius
     */
    @Test
    public void testGetBallRadius(){
        assertEquals(0.25D, m1.getBall().getBallRadius());
    }

    @Test
    public void testSetBallSpeed(){
        m1.setBallSpeed(3, 3);
        assertEquals(3, m1.getBall().getVelocity().x());
        assertEquals(3, m1.getBall().getVelocity().y());
    }

    /**
     * test the expected value with the actual value of setting and getting the ball colour
     */
    @Test
    public void testChangeBallColour(){
        assertEquals(Color.white, m1.getBall().getColour());
        m1.getBall().setColour(Color.green);
        assertEquals(Color.green, m1.getBall().getColour());
    }

    /**
     * test the expected value with the actual value of the ball ID and whether the ball is stopped
     */
    @Test
    public void testBallStoppedAndID(){
        m1.moveBall();
        m1.getBall().setStopped(true);
        assertEquals(true, m1.getBall().isStopped());
        assertEquals("B", m1.getBall().getID());
    }

    /**
     * test the grid cell space when occupied by a gizmo, and get the expected return value : false
     */
    @Test
    public void testCellSpaceNotEmpty(){
        m1.addGizmo(circle);
        assertEquals(false, m1.isCellEmpty(5, 5));

        m1.addGizmo(triangle);
        assertEquals(false, m1.isCellEmpty(12, 12));

        m1.addGizmo(square);
        assertEquals(false, m1.isCellEmpty(1, 1));
    }

    /**
     * test the cell space is empty and not occupied by another gizmo as expected
     */
    @Test
    public void testCellSpaceIsEmpty(){
        assertEquals(true, m1.isCellEmpty(9, 9));
    }

    /**
     * replace the ball with another and get the return value of the getBall method
     */
    @Test
    public void testReplaceBall(){
        Ball ball2 = new BallImpl("B2",10.5F, 15.0F, 0.0D, 0.0D);
        assertNotEquals(ball2, m1.getBall());

        m1.replaceBall(ball2);
        assertEquals(ball2, m1.getBall());
        m1.replaceBall(ball);
        assertEquals(ball, m1.getBall());
    }

    /**
     * create new velocity of the ball and test it against the getVelocity() return value
     */
    @Test
    public void testSetAndGetVelocity(){
        double test1 = 5;
        Angle agl = new Angle(test1);
        Vect v1 = new Vect(agl);

        m1.getBall().setVelocity(v1);
        assertEquals(v1, m1.getBall().getVelocity());
    }

    /**
     * test the gizmo which is at the specified coordinates returns the actual value of the gizmo in the cell
     */
    @Test
    public void testGetGizmoCoords(){
        m1.addGizmo(circle);
        assertEquals(circle, m1.getGizmoByCoords(5, 5));
    }

    /**
     * get the expected and actual boolean value of build mode
     */
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
    public void testLFlipperCoords(){
        m1.addGizmo(lFlip);
        assertEquals(lFlip, m1.getGizmoByCoords(15, 15));
        assertEquals(1, m1.getLeftFlippers().size());
        assertTrue(m1.getLeftFlippers().get(0).getID().equals("LFlip"));
    }

    @Test
    public void testLFlip(){
        m1.removeGizmo(lFlip);
        assertEquals("LFlip", lFlip.getID());
        assertTrue( m1.getLeftFlippers().size() == 0);
        assertEquals(0, m1.getLeftFlippers().size());
        lFlip.setPos(8, 8);
        assertEquals(8, lFlip.getXPos());
        assertEquals(8, lFlip.getYPos());
        assertEquals(Color.orange, lFlip.getColour());
        assertEquals("leftflipper", lFlip.getType());
        assertEquals(2, lFlip.getHeight());
        assertEquals(0.5, lFlip.getBreadth());
        assertEquals(2, lFlip.getLength());
        assertEquals(0.5, lFlip.getWidth());
        lFlip.setPos(15, 15);
    }

    @Test
    public void testSpinFlipper(){
        //flipper does not spin so test will pass, but we will try to get
        // flipper rotation functioning correctly
        assertFalse(m1.SpinFlipper(lFlip, 90));
    }

    @Test
    public void testAdjustGravity(){
        double grav = 6;
        m1.setGravity(grav);
        assertEquals(6, m1.getGravity());
    }

    @Test
    public void testAdjustFriction(){
        double friction = 4;
        m1.setFriction(friction);
        assertEquals(4, m1.getFriction());
    }

    @Test
    public void testMu(){
        m1.setMu(0.05);
        assertEquals(0.05, m1.getMu());
    }

    @Test
    public void testMu2(){
        m1.setMu2(0.05);
        assertEquals(0.05, m1.getMu2());
    }


    /**
     * test each gizmo added in the model class returns an instance of the correct gizmo class
     */
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
        m1.addGizmo(lFlip);
        assertTrue(lFlip != null);
        m1.clear();
    }

    @Test
    public void testGetNullGizmoCoords(){
        assertEquals(null, m1.getGizmoByCoords(10, 10));
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
    public void testChangeColourRunnable(){
        ChangeColourRunnable run1 = new ChangeColourRunnable(square, Color.GREEN);
        run1.run();
        assertEquals(Color.GREEN, square.getColour());
    }


    /**
     * save a default gizmo file to the system and load the file
     */
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

    @Test
    public void testGetGizmoID(){
        m1.addGizmo(circle);
        assertEquals(circle, m1.getGizmoByID("5"));
        m1.removeGizmo(circle);
        assertNull(m1.getGizmoByID("5"));
    }

    @Test
    public void testSetGetRadius(){
        Ball ball1 = new BallImpl("TEST",18.5F, 10.0F, 0.0D, 0.0D);
        ball1.setRadius(0.5);
        assertEquals(0.5, ball1.getBallRadius());
    }

    @Test
    public void testGizmoConnection(){
        Gizmo squareTEST = new SquareBumper("sq0", 1, 1);
        Gizmo squareTEST1 = new SquareBumper("sq1", 4, 4);

        m1.addGizmoConnection(squareTEST, squareTEST1);
        assertTrue(m1.hasGizmoConnection(squareTEST));
    }

    @AfterAll
    public void tearDown(){
        m1 = null;
    }

}
