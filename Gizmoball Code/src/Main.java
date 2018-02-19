import Model.ModelAPI;
import Model.Model;
import Model.gizmos.Gizmo;
import Model.gizmos.SquareBumper;
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
        model.setBallSpeed(15,15);

        Gizmo square1 = new SquareBumper(5,5);
        model.addGizmo(square1);
    }

}
