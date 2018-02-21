import Model.ModelAPI;
import Model.Model;
import Model.gizmos.*;
import gui.View;

import java.io.File;
import java.io.FileInputStream;
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


//        String homePath = System.getProperty("user.home");
//
//        String gizmoPath = homePath + "/GizmoFiles";
//        String gizmoConfigPath = gizmoPath + "/config";
//        String defaultGamePath = gizmoPath + "/default.gizmo";
//        File gizmoPathDir = new File(gizmoPath);
//        File gizmoConfigFile = new File(gizmoConfigPath);
//        File defaultGameFile = new File(defaultGamePath);
//        FileInputStream in;
//
//        if(!gizmoPathDir.exists()){
//            gizmoPathDir.mkdir();
//            System.out.println("Main Gizmoball directory not found, created at '"+gizmoPath+"'");
//        }
//        if(!gizmoConfigFile.exists()){
//            try {
//                gizmoConfigFile.createNewFile();
//                System.out.println("Gizmoball config file not found, created at '"+gizmoConfigPath+"'");
//            }catch(IOException iox){
//                System.out.println("IOException trying to create config file '"+gizmoConfigPath+"'");
//            }
//        }
//        if(!defaultGameFile.exists()){
//            try {
//                gizmoConfigFile.createNewFile();
//                System.out.println("Gizmoball config file not found, created at '"+gizmoConfigPath+"'");
//            }catch(IOException iox){
//                System.out.println("IOException trying to create config file '"+gizmoConfigPath+"'");
//            }
//        }


//        // create and load default properties
//        Properties defaultProps = new Properties();
//        try {
//            in = new FileInputStream(gizmoConfigPath);
//            defaultProps.load(in);
//            in.close();
//        }catch(IOException iox){
//            System.out.println("IOException trying to load default config file properties from '"+gizmoConfigPath+"'");
//        }
//
//        // create application properties with default
//        Properties applicationProps = new Properties(defaultProps);
//
//        // load properties from last invocation
//        try{
//            in = new FileInputStream("appProperties");
//            applicationProps.load(in);
//            in.close();
//        }catch(IOException iox){
//            System.out.println("IOException trying to load application properties
//        }


        ModelAPI model = new Model();
        View view = new View(model);
        view.runMode();

//        model.loadFile(defaultGameFile.getAbsolutePath());
        System.out.println("Loaded default game file '"+defaultGamePath+"'");

        model.setBallSpeed(0,0);


        //Gizmo absorber1 = new Absorber("A",0,19);
        //Gizmo absorber2 = new Absorber("A",0,0);
        //model.addGizmo(absorber1);
        //model.addGizmo(absorber2);


    }

}
