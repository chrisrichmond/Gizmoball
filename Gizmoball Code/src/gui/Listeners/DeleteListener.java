package gui.Listeners;

import Model.ModelAPI;
import Model.gizmos.Gizmo;
import gui.View;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

public class DeleteListener implements MouseInputListener {

    private ModelAPI model;
    private View view;
    private int xPos1;
    private int yPos1;
    private Gizmo deletedGizmo;

    public DeleteListener(ModelAPI model, View view){
        this.model = model;
        this.view = view;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        xPos1 = (int)(e.getX()/view.getPpl());
        yPos1 = (int)(e.getY()/view.getPpl());
        deletedGizmo = model.getGizmoByCoords(xPos1, yPos1);

        if(model.removeGizmo(deletedGizmo)){
            view.updateMessagePanel("[ Deleted Gizmo '"+deletedGizmo.getID()+"' ]");
        }
        view.update();
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
