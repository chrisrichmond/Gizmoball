package utilities;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GizmoConstants {

    public static String homePath = System.getProperty("user.home");
    public static String fileExtension = ".gizmo";

    public static String gizmoPath = homePath + "/GizmoFiles";
    public static String gizmoConfigPath = gizmoPath + "/config";
    public static String defaultGamePath = gizmoPath + "/default.gizmo";

    public static File gizmoPathDir = new File(gizmoPath);
    public static File gizmoConfigFile = new File(gizmoConfigPath);
    public static File defaultGameFile = new File(defaultGamePath);

    public static final int flipperXbound = 2;
    public static final int flipperYbound = 2;

    public static Color buildColourTheme = Color.white;
    public static Color runColourTheme = Color.black;

    public static Color getComplimentaryColour(Color colour) {
        return new Color(255-colour.getRed(), 255-colour.getGreen(), 255-colour.getBlue());
    }

    public static final int timerMillis = 50;

    public static void writeDefaultFile(File gameFile){
        try{
            FileWriter fw = new FileWriter(gameFile);
            fw.write("Square S 0 2\n");
            fw.write("Square S1 1 2\n");
            fw.write("Square S2 0 3\n");
            fw.write("Square S3 1 4\n");
            fw.write("Square S4 2 3\n");
            fw.write("Square S5 2 5\n");
            fw.write("Square S6 1 6\n");
            fw.write("Square S7 4 4\n");
            fw.write("Square S8 4 5\n");
            fw.write("Square S9 4 6\n");
            fw.write("Circle C 4 2\n");
            fw.write("Triangle T 2 2\n");
            fw.write("Triangle T1 0 4\n");
            fw.write("Rotate T1\n");
            fw.write("Rotate T1\n");
            fw.write("Triangle T2 2 4\n");
            fw.write("Triangle T3 2 6\n");
            fw.write("Rotate T3\n");
            fw.write("Triangle T4 0 6\n");
            fw.write("Rotate T4\n");
            fw.write("Rotate T4\n");
            fw.write("Square S10 7 2\n");
            fw.write("Square S11 18 2\n");
            fw.write("Square S12 17 2\n");
            fw.write("Square S13 16 3\n");
            fw.write("Square S14 16 4\n");
            fw.write("Square S15 17 5\n");
            fw.write("Square S16 18 5\n");
            fw.write("Square S17 19 3\n");
            fw.write("Square S18 19 4\n");
            fw.write("Triangle T5 19 2\n");
            fw.write("Triangle T6 19 5\n");
            fw.write("Rotate T6\n");
            fw.write("Triangle T7 16 5\n");
            fw.write("Rotate T7\n");
            fw.write("Rotate T7\n");
            fw.write("Triangle T8 16 2\n");
            fw.write("Rotate T8\n");
            fw.write("Rotate T8\n");
            fw.write("Triangle T9 6 2\n");
            fw.write("Rotate T9\n");
            fw.write("Rotate T9\n");
            fw.write("Triangle T10 8 2\n");
            fw.write("Triangle T11 8 4\n");
            fw.write("Rotate T11\n");
            fw.write("Triangle T12 6 4\n");
            fw.write("Rotate T12\n");
            fw.write("Rotate T12\n");
            fw.write("Rotate T12\n");
            fw.write("Triangle T13 6 6\n");
            fw.write("Rotate T13\n");
            fw.write("Rotate T13\n");
            fw.write("Triangle T14 8 6\n");
            fw.write("Square S19 8 3\n");
            fw.write("Square S20 7 4\n");
            fw.write("Square S21 6 5\n");
            fw.write("Square S22 7 6\n");
            fw.write("Square S23 10 5\n");
            fw.write("Square S24 10 4\n");
            fw.write("Square S25 10 3\n");
            fw.write("Square S26 12 3\n");
            fw.write("Square S27 12 4\n");
            fw.write("Square S28 12 5\n");
            fw.write("Square S29 14 3\n");
            fw.write("Square S30 14 4\n");
            fw.write("Square S31 14 5\n");
            fw.write("Square S32 11 2\n");
            fw.write("Square S33 13 2\n");
            fw.write("Triangle T15 10 2\n");
            fw.write("Rotate T15\n");
            fw.write("Rotate T15\n");
            fw.write("Rotate T15\n");
            fw.write("Triangle T16 14 2\n");
            fw.write("Triangle T17 10 6\n");
            fw.write("Rotate T17\n");
            fw.write("Rotate T17\n");
            fw.write("Triangle T18 12 6\n");
            fw.write("Rotate T18\n");
            fw.write("Rotate T18\n");
            fw.write("Triangle T19 14 6\n");
            fw.write("Rotate T19\n");
            fw.write("Rotate T19\n");
            fw.write("Triangle T20 12 2\n");
            fw.write("Absorber A 0 19 20 20\n");
            fw.write("Triangle T22 0 10\n");
            fw.write("Square S34 0 16\n");
            fw.write("Square S35 1 16\n");
            fw.write("Square S36 2 16\n");
            fw.write("Square S37 3 15\n");
            fw.write("Square S38 3 14\n");
            fw.write("Square S39 3 11\n");
            fw.write("Triangle T28 3 12\n");
            fw.write("Rotate T28\n");
            fw.write("Triangle T29 3 13\n");
            fw.write("Triangle T30 2 13\n");
            fw.write("Rotate T30\n");
            fw.write("Square S40 1 13\n");
            fw.write("Square S41 1 10\n");
            fw.write("Square S42 2 10\n");
            fw.write("Triangle T31 3 10\n");
            fw.write("Square S43 0 11\n");
            fw.write("Square S44 0 12\n");
            fw.write("Square S45 0 13\n");
            fw.write("Square S46 0 14\n");
            fw.write("Square S47 0 15\n");
            fw.write("Triangle T21 3 16\n");
            fw.write("Rotate T21\n");
            fw.write("Triangle T23 19 16\n");
            fw.write("Square S48 17 16\n");
            fw.write("Square S49 18 16\n");
            fw.write("Square S55 5 12\n");
            fw.write("Square S56 5 13\n");
            fw.write("Square S57 8 12\n");
            fw.write("Square S58 8 13\n");
            fw.write("Square S59 5 14\n");
            fw.write("Square S60 5 15\n");
            fw.write("Square S61 8 14\n");
            fw.write("Square S62 8 15\n");
            fw.write("Square S63 6 14\n");
            fw.write("Triangle T26 7 14\n");
            fw.write("Rotate T26\n");
            fw.write("Triangle T27 8 11\n");
            fw.write("Triangle T32 7 10\n");
            fw.write("Triangle T33 6 10\n");
            fw.write("Rotate T33\n");
            fw.write("Rotate T33\n");
            fw.write("Rotate T33\n");
            fw.write("Triangle T34 5 11\n");
            fw.write("Rotate T34\n");
            fw.write("Rotate T34\n");
            fw.write("Rotate T34\n");
            fw.write("Triangle T35 5 16\n");
            fw.write("Rotate T35\n");
            fw.write("Rotate T35\n");
            fw.write("Triangle T36 8 16\n");
            fw.write("Rotate T36\n");
            fw.write("Rotate T36\n");
            fw.write("Triangle T37 10 10\n");
            fw.write("Square S64 10 11\n");
            fw.write("Square S65 10 12\n");
            fw.write("Square S66 10 13\n");
            fw.write("Square S67 10 14\n");
            fw.write("Square S68 10 15\n");
            fw.write("Square S69 11 16\n");
            fw.write("Square S70 12 16\n");
            fw.write("Square S71 13 16\n");
            fw.write("Triangle T38 14 16\n");
            fw.write("Triangle T39 10 16\n");
            fw.write("Rotate T39\n");
            fw.write("Rotate T39\n");
            fw.write("Square S72 15 11\n");
            fw.write("Square S73 15 12\n");
            fw.write("Square S74 15 13\n");
            fw.write("Square S75 15 14\n");
            fw.write("Square S76 15 15\n");
            fw.write("Triangle T40 15 16\n");
            fw.write("Rotate T40\n");
            fw.write("Rotate T40\n");
            fw.write("Square S50 16 16\n");
            fw.write("Triangle T24 15 10\n");
            fw.write("Ball B 9.5 0.5 0.0 0.0\n");
            fw.close();
        }catch(IOException iox){

        }
    }

}
