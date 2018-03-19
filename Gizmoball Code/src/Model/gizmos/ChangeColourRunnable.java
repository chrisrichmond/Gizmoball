package Model.gizmos;

import utilities.GizmoConstants;

import java.awt.*;

public class ChangeColourRunnable implements Runnable {

    private Gizmo gizmo;
    private Color colour;

    public ChangeColourRunnable(Gizmo gizmo, Color colour){
        this.gizmo = gizmo;
        this.colour = colour;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(GizmoConstants.triggerTimeoutMillis);
        }catch(InterruptedException ix){
            System.out.println("Colour change was interrupted prematurely");
        }finally {
            gizmo.setColour(colour);
        }
    }
}