package gui.Listeners;

import Model.ModelAPI;
import gui.View;
import utilities.GizmoConstants;

import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

public class SettingsListener implements GBallListener {

    private ModelAPI model;
    private View view;

    public SettingsListener(ModelAPI model, View view){
        this.model = model;
        this.view = view;
    }

    @Override
    public void setMouseListener(MouseInputListener mouseListener) {

    }

    @Override
    public void setKeyboardListener(KeyListener keyboardListener) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            // Build Theme Selection
            case("Build Black"):
                System.out.println("build black selected");
                GizmoConstants.buildColourTheme = Color.black;
                break;
            case("Build White"):
                GizmoConstants.buildColourTheme = Color.white;
                break;
            case("Build Red"):
                GizmoConstants.buildColourTheme = Color.red;
                break;
            case("Build Orange"):
                GizmoConstants.buildColourTheme = Color.orange;
                break;

            // Run Theme Selection
            case("Run Black"):
                GizmoConstants.buildColourTheme = Color.black;
                break;
            case("Run White"):
                GizmoConstants.runColourTheme = Color.white;
                break;
            case("Run Red"):
                GizmoConstants.runColourTheme = Color.red;
                break;
            case("Run Orange"):
                GizmoConstants.runColourTheme = Color.orange;
                break;
        }
        view.update();
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

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

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
