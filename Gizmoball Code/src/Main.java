import Model.ModelAPI;
import Model.Model;
import Model.gizmos.*;
import gui.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

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


        String homePath = System.getProperty("user.home");

        String gizmoPath = homePath + "/GizmoFiles";
        String gizmoConfigPath = gizmoPath + "/config";
        String defaultGamePath = gizmoPath + "/default.gizmo";
        File gizmoPathDir = new File(gizmoPath);
        File gizmoConfigFile = new File(gizmoConfigPath);
        File defaultGameFile = new File(defaultGamePath);
        FileInputStream in;
        FileWriter fw;

        // Check if main game files directory exists and if not then create it
        if(!gizmoPathDir.exists()){
            gizmoPathDir.mkdir();
            System.out.println("Main Gizmoball directory not found, created at '"+gizmoPath+"'");
        }
        // Check if game config file inside main game files directory exists and if not create it
        if(!gizmoConfigFile.exists()){
            try {
                gizmoConfigFile.createNewFile();
                System.out.println("Gizmoball config file not found, created at '"+gizmoConfigPath+"'");

                // write code here for writing default config stuff to file
//                fw = new FileWriter(gizmoConfigFile);
//                fw.write();
//                fw.close();

            }catch(IOException iox){
                System.out.println("IOException trying to create config file '"+gizmoConfigPath+"'");
            }
        }
        // Check if default game file exists inside main game files directory and if not create it
        if(!defaultGameFile.exists()){
            try {
                defaultGameFile.createNewFile();
                System.out.println("Gizmoball config file not found, created at '"+gizmoConfigPath+"'");

                // write code here for writing default game file stuff to file
                fw = new FileWriter(defaultGameFile);
                fw.write("Circle C0 0 0\n");
                fw.write("Circle C1 1 1\n");
                fw.write("Circle C2 2 2\n");
                fw.write("Circle C3 3 3\n");
                fw.write("Circle C4 4 4\n");
                fw.write("Circle C5 5 5\n");
                fw.write("Circle C6 6 6\n");
                fw.write("Circle C7 7 7\n");
                fw.write("Circle C8 8 8\n");
                fw.write("Circle C9 9 9\n");
                fw.write("Circle C10 10 10\n");
                fw.write("Circle C11 11 11\n");
                fw.write("Circle C12 12 12\n");
                fw.write("Circle C13 13 13\n");
                fw.write("Circle C14 14 14\n");
                fw.write("Circle C15 15 15\n");
                fw.write("Circle C16 16 16\n");
                fw.write("Circle C17 17 17\n");
                fw.write("Circle C18 18 18\n");
                fw.write("Circle C19 19 19\n");
                fw.close();

            }catch(IOException iox){
                System.out.println("IOException trying to create config file '"+gizmoConfigPath+"'");
            }
        }

        ModelAPI model = new Model();
        View view = new View(model);
        view.buildMode();

        model.loadFile(defaultGameFile.getAbsolutePath());
        System.out.println("Loaded default game file '"+defaultGamePath+"'");

        model.setBallSpeed(0,0);


        //Gizmo absorber1 = new Absorber("A",0,19);
        //Gizmo absorber2 = new Absorber("A",0,0);
        //model.addGizmo(absorber1);
        //model.addGizmo(absorber2);


    }

}
