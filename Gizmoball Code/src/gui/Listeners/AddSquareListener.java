package gui.Listeners;

import Model.ModelAPI;
import Model.gizmos.SquareBumper;
import gui.View;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

public class AddSquareListener implements MouseInputListener {

    private ModelAPI model;
    private View view;
    private int xPos1;
    private int yPos1;

    public AddSquareListener(ModelAPI model, View view){
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
        if(model.addGizmo(new SquareBumper("S", xPos1, yPos1))) {  // NEED TO SORT OUT UNIQUE ID ASSIGNMENT
            view.updateMessagePanel("Drawing square at X="+xPos1+", Y="+yPos1);
        }else{
            view.updateMessagePanel("Cell already occupied!");
        }
        view.update();
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
