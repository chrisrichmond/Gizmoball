package gui.Listeners;

import Model.ModelAPI;
import Model.gizmos.Absorber;
import gui.View;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

public class AddAbsorberListener implements MouseInputListener {

    private ModelAPI model;
    private View view;
    private int xPos1;
    private int yPos1;
    private int xPos2;
    private int yPos2;
    private boolean valid;

    public AddAbsorberListener(ModelAPI model, View view) {
        this.model = model;
        this.view = view;
        this.valid = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        xPos1 = (int)(e.getX()/view.getPpl());
        yPos1 = (int)(e.getY()/view.getPpl());
        if(model.isCellEmpty(xPos1, yPos1)&& (model.isCellEmpty(xPos1, yPos1))){  // NEED TO SORT OUT UNIQUE ID ASSIGNMENT
            view.updateMessagePanel("Add Absorber - Drawing absorber from X="+xPos1+", Y="+yPos1+" to ...");
            valid = true;
        }else{
            view.updateMessagePanel("Cell already occupied!");
            valid = false;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        xPos2 = (int)(e.getX()/view.getPpl());
        yPos2 = (int)(e.getY()/view.getPpl()); // may be redundant if absorbers are going to be restricted to only allow a height of 1

        if(xPos1 <= xPos2) {
            for (int i = xPos1; i <= xPos2; i++) {
                if (!model.isCellEmpty(i, yPos1)) {
                    valid = false;
                }
            }
            if (valid) {
                if(xPos2 > 19) {
                    xPos2 = 19;
                }
                view.updateMessagePanel("Drawing absorber from StartX=" + xPos1 + ", StartY=" + yPos1 + " to EndX=" + (xPos2 + 1) + ", EndY=" + (yPos1 + 1));
                model.addGizmo(new Absorber("A", xPos1, yPos1, xPos2 + 1, yPos1 + 1));
            } else {
                view.updateMessagePanel("Absorber cannot be drawn through existing gizmos!");
            }
        }else{
            view.updateMessagePanel("Please draw absorbers from left to right");
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
