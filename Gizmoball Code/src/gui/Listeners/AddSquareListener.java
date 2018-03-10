package gui.Listeners;

import Model.ModelAPI;
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
        view.setBuildModeMessage("Dropping square at X="+xPos1+", Y="+yPos1);
        System.out.println("Dropping square at X="+xPos1+", Y="+yPos1);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        xPos1 = (int)(e.getX()/view.getPpl());
        yPos1 = (int)(e.getY()/view.getPpl());
        view.setBuildModeMessage("Dropping square at X="+xPos1+", Y="+yPos1);
        System.out.println("Dropping square at X="+xPos1+", Y="+yPos1);
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
