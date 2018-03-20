package gui.Listeners;

import Model.ModelAPI;
import gui.View;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GravSliderListener implements ChangeListener{

    private ModelAPI model;
    private View view;
    private JSlider gravSlider;

    public GravSliderListener(ModelAPI model, View view, JSlider gravSlider){
        this.model = model;
        this.view = view;
        this.gravSlider = gravSlider;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if(e.getSource() == gravSlider){
            System.out.println("#################################=============================---------------------------grav");
            model.setGravity(gravSlider.getValue());
        }
    }
}
