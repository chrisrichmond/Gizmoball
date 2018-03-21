package gui.Listeners;

import Model.ModelAPI;
import Model.gizmos.Gizmo;
import gui.View;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

public class GizmoDisconnectListener implements MouseInputListener{

    private ModelAPI model;
    private View view;
    private Gizmo firstGizmo;
    private Gizmo secondGizmo;
    private int xPos1;
    private int yPos1;
    private int xPos2;
    private int yPos2;

    public GizmoDisconnectListener(ModelAPI model, View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        xPos1 = (int)(e.getX()/view.getPpl());
        yPos1 = (int)(e.getY()/view.getPpl());
        if(!model.isCellEmpty(xPos1,yPos1)){
            firstGizmo = model.getGizmoByCoords(xPos1,yPos1);
            view.updateMessagePanel("Gizmo Disconnect - Selected Gizmo '"+firstGizmo.getID()+"' at X="+firstGizmo.getXPos()+", Y="+firstGizmo.getYPos()+" . . .");
        }else{
            view.updateMessagePanel("No gizmo located here! (Gizmo 1)");
            firstGizmo = null;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        xPos2 = (int)(e.getX()/view.getPpl());
        yPos2 = (int)(e.getY()/view.getPpl());
        if(firstGizmo != null){
            if(!model.isCellEmpty(xPos2,yPos2)) {
                secondGizmo = model.getGizmoByCoords(xPos2, yPos2);
                model.removeGizmoConnection(firstGizmo, secondGizmo);
                view.updateMessagePanel("Disconnected '"+firstGizmo.getID()+"' to '"+secondGizmo.getID()+"' !");
            }else{
                view.updateMessagePanel("No gizmo located here! (Gizmo 2)");
            }
        }
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
