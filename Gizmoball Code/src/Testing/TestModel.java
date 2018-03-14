package Testing;
import Model.Model;
import Model.ModelAPI;
import Model.gizmos.CircularBumper;
import Model.gizmos.Gizmo;
import Model.gizmos.SquareBumper;
import Model.gizmos.TriangularBumper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ModelTest {

    private ModelAPI m1;
    private List<Gizmo> gizmos;
    private Gizmo circle = new CircularBumper("5", 5, 5);
    private Gizmo triangle = new TriangularBumper("2", 12, 12);
    private Gizmo square = new SquareBumper("1", 1, 1);

    @BeforeAll
    public  void setUp(){
        m1 = new Model();
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


 }
