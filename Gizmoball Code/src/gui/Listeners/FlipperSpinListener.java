package gui.Listeners;

import Model.ModelAPI;
import Model.gizmos.Gizmo;
import Model.gizmos.LFlipper;
import gui.View;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FlipperSpinListener implements KeyListener {

    private ModelAPI model;
    private View view;
    final private double spinDegrees = 90.0;

    public FlipperSpinListener(ModelAPI model, View view){
        this.model = model;
        this.view = view;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        for(Gizmo currentLF: model.getLeftFlippers()){
            System.out.println("Spinning "+currentLF.getID());
            ((LFlipper)currentLF).spin(spinDegrees);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        for(Gizmo currentLF: model.getLeftFlippers()){
//            System.out.println("Spinning "+currentLF.getID());
//            ((LFlipper)currentLF).spin(-(spinDegrees));
//        }
    }
}
