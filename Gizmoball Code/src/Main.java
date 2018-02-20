import Model.ModelAPI;
import Model.Model;
import Model.gizmos.*;
import gui.View;


public class Main {

    public static void main(String[] args){
        /**
         * Initialisation of MVC
         *
         * [1] - Model is created and initialises internal data structures.
         * [2] - View is created, taking reference to the Model as a parameter.
         * [3] - View subscribes to change-propagation mechanism (Observer) of the Model using attach method.
         * [4] - View creates Controller. Passes Controller a reference to itself and to Model.
         * [5] - Controller subscribes to change-propagation mechanism of the Model using attach method.
         * [6] - Controller starts processing events
         *
         */

        ModelAPI model = new Model();
        View view = new View(model);
        view.runMode();
        model.setBallSpeed(5,5);

<<<<<<< HEAD
        Gizmo square1 = new Absorber(2,17);
        model.addGizmo(square1);
        Gizmo square2 = new SquareBumper(13,11);
=======
        Gizmo square1 = new Absorber("A",0,19);
        model.addGizmo(square1);
        Gizmo square2 = new SquareBumper("S01",10,9);
>>>>>>> b435679634c278402e8feaeb4ac5de6d2b191fb8
        model.addGizmo(square2);


    }

}
