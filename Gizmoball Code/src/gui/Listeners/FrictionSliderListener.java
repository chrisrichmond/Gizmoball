package gui.Listeners;

import Model.ModelAPI;
import gui.View;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class FrictionSliderListener implements ChangeListener {

    private ModelAPI model;
    private View view;
    private JSlider frictionSlider;

    public FrictionSliderListener(ModelAPI model, View view, JSlider frictionSlider){
        this.model = model;
        this.view = view;
        this.frictionSlider = frictionSlider;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if(e.getSource() == frictionSlider) {
            view.updateMessagePanel("Friction = "+((double)frictionSlider.getValue()/1000));
            model.setMu((double)frictionSlider.getValue()/1000);
            model.setMu2((double)frictionSlider.getValue()/1000);
            System.out.println("mu set to "+ model.getMu());
            System.out.println("mu2 set to "+ model.getMu2());
        }
    }
}
