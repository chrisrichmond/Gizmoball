package Testing;
import Model.Model;
import Model.ModelAPI;
import Model.gizmos.CircularBumper;
import Model.gizmos.Gizmo;
import Model.gizmos.SquareBumper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ModelTest {

    private ModelAPI m1;
    private List<Gizmo> gizmos;
    private Gizmo circle = new CircularBumper("5", 5, 5);

    private Gizmo square = new SquareBumper("1", 1, 1);

    @BeforeAll
    public  void setUp(){
        m1 = new Model();
    }

    @Test
    public void testGizmoAddCircle(){
        m1.addGizmo(circle);
        assertEquals(m1.getGizmos().size(), 1);
        assertEquals(m1.getCircles().size(), 1);
    }

    @Test
    public void testGizmoAddSquare(){
        m1.addGizmo(square);
        assertEquals(m1.getGizmos().size(), 1);
        assertEquals(m1.getSquares().size(), 1);

    }

 }
