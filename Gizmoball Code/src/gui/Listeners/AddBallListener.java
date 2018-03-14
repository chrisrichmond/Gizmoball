package gui.Listeners;

import Model.BallImpl;
import Model.ModelAPI;
import gui.View;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

public class AddBallListener implements MouseInputListener{

    private ModelAPI model;
    private View view;
    private int xPos1;
    private int yPos1;

    public AddBallListener(ModelAPI model, View view){
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
            model.replaceBall(new BallImpl("B", xPos1+0.5F, yPos1+0.5F, 0.0, 0.0));
            view.updateMessagePanel("Add Ball - Drawing ball at X="+xPos1+", Y="+yPos1);
        }else{
            view.updateMessagePanel("Cell already occupied");
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
