package gui.Listeners;

import Model.ModelAPI;
import Model.gizmos.Gizmo;
import Model.gizmos.LFlipper;
import physics.Angle;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

public class DoNothingMouseListener implements MouseInputListener {

    private ModelAPI model;

    public DoNothingMouseListener(ModelAPI model){
        this.model = model;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
