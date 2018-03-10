package gui.Listeners;

import Model.ModelAPI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DoNothingKeyListener implements KeyListener{

    private ModelAPI model;

    public DoNothingKeyListener(ModelAPI model){
        this.model = model;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
