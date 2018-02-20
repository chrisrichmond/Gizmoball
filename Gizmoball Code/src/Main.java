import Model.ModelAPI;
import Model.Model;
import Model.gizmos.*;
import gui.View;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.lang.System.getProperty;


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

//        String homePath = System.getProperty("user.home");
//
//        String gizmoPath = homePath + "\\Gizmoball";
//        String gizmoConfigPath = gizmoPath + "\\config";
//        String defaultGamePath = gizmoPath + "\\default.gizmo";
//        File gizmoPathDir = new File(gizmoPath);
//        File gizmoConfigFile = new File(gizmoConfigPath);
//        File defaultGameFile = new File(defaultGamePath);
//
//        if(!gizmoPathDir.exists()){
//            gizmoPathDir.mkdir();
//            try {
//                gizmoConfigFile.createNewFile();
//            }catch(IOException iox){
//                System.out.println("IOException trying to create config file '"+gizmoConfigPath+"'");
//            }
//        }

        ModelAPI model = new Model();
        View view = new View(model);
        view.runMode();

//        model.loadFile(defaultGameFile.getAbsolutePath());

        model.setBallSpeed(5,5);


        Gizmo square1 = new Absorber("A",0,19);
        model.addGizmo(square1);
        Gizmo square2 = new SquareBumper("S01",10,9);

        model.addGizmo(square2);


    }

}
