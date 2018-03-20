package gui.Listeners;

import Model.BallImpl;
import Model.ModelAPI;
import gui.RunKeyListener;
import gui.View;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

public class KeyConnect implements MouseInputListener{

    private ModelAPI model;
    private View view;
    private int xPos1;
    private int yPos1;

    public KeyConnect(ModelAPI model, View view){
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

        if(model.isCellEmpty(xPos1, yPos1)){
            view.updateMessagePanel("No gizmo is present to connect to");

        }else{
            view.updateMessagePanel("Key connected at X="+xPos1+", Y="+yPos1);
            view.addBuildKeyListener();
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