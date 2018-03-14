package gui.Listeners;

import Model.ModelAPI;
import Model.gizmos.TriangularBumper;
import gui.View;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

public class AddTriangleListener implements MouseInputListener {

    private ModelAPI model;
    private View view;
    private int xPos1;
    private int yPos1;

    public AddTriangleListener(ModelAPI model, View view) {
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
        if(model.addGizmo(new TriangularBumper("T", xPos1, yPos1))) {  // NEED TO SORT OUT UNIQUE ID ASSIGNMENT
            view.updateMessagePanel("Add Triangle - Drawing triangle at X="+xPos1+", Y="+yPos1);
        }else{
            view.updateMessagePanel("Cell already occupied!");
        }
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
