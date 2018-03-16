package Testing;

import Model.Model;
import Model.ModelAPI;
import gui.View;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ViewTest {

    private ModelAPI m1;
    private View myView;

    @BeforeAll
    public  void setUp(){
        m1 = new Model();
        myView = new View(m1);
    }

    @Test
    public void testGetPPL(){
        assertEquals(25, myView.getPpl());
    }

}
