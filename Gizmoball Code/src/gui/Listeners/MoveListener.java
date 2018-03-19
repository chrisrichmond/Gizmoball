package gui.Listeners;

import Model.ModelAPI;
import Model.gizmos.*;
import gui.View;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

public class MoveListener implements MouseInputListener {

    private ModelAPI model;
    private View view;
    private int xPos1;
    private int yPos1;
    private int xPos2;
    private int yPos2;
    private Gizmo movedGizmo;

    public MoveListener(ModelAPI model, View view){
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
            movedGizmo = model.getGizmoByCoords(xPos1,yPos1);
            view.updateMessagePanel("Move mode - Grabbed Gizmo '"+movedGizmo.getID()+"' at X="+movedGizmo.getXPos()+", Y="+movedGizmo.getYPos()+" . . .");
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        xPos2 = (int)(e.getX()/view.getPpl());
        yPos2 = (int)(e.getY()/view.getPpl());
        if(model.isCellEmpty(xPos2,yPos2)){
            model.removeGizmo(movedGizmo);

            if(movedGizmo.getType().equals("square")) {
                movedGizmo = new SquareBumper(movedGizmo.getID(), xPos2, yPos2);
            }else if(movedGizmo.getType().equals("circle")) {
                movedGizmo = new CircularBumper(movedGizmo.getID(), xPos2, yPos2);
            }else if(movedGizmo.getType().equals("triangle")) {
                movedGizmo = new TriangularBumper(movedGizmo.getID(), xPos2, yPos2, ((TriangularBumper)movedGizmo).getRotation());
            }else if(movedGizmo.getType().equals("absorber")){
                movedGizmo = new Absorber(movedGizmo.getID(), xPos2, yPos2, xPos2+(int)movedGizmo.getWidth(), yPos2+(int)movedGizmo.getHeight());
            }else if(movedGizmo.getType().equals("leftflipper")){

            }else if(movedGizmo.getType().equals("rightflipper")){

            }
            model.addGizmo(movedGizmo);
            view.updateMessagePanel("Dropped Gizmo '"+movedGizmo.getID()+"' at X="+movedGizmo.getXPos()+", Y="+movedGizmo.getYPos()+" . . .");
        }else{
            view.updateMessagePanel("Cell already occupied!");
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
