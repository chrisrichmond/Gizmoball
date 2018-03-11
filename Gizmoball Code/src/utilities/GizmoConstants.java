package utilities;

import java.io.File;

public class GizmoConstants {

    public static String homePath = System.getProperty("user.home");

    public static String gizmoPath = homePath + "/GizmoFiles";
    public static String gizmoConfigPath = gizmoPath + "/config";
    public static String defaultGamePath = gizmoPath + "/default.gizmo";

    public static File gizmoPathDir = new File(gizmoPath);
    public static File gizmoConfigFile = new File(gizmoConfigPath);
    public static File defaultGameFile = new File(defaultGamePath);

}
