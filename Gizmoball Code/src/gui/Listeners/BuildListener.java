package gui.Listeners;

import Model.ModelAPI;
import gui.View;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.*;
import java.io.File;

public class BuildListener implements GBallListener {

    private ModelAPI model;
    private View view;
    private MouseInputListener mouseListener;
    private KeyListener keyboardListener;
    private JFileChooser fileChooser;
    private File latestFile;

    public BuildListener(ModelAPI model, View view){
        this.model = model;
        this.view = view;

        this.mouseListener = new DoNothingMouseListener(model);
        this.keyboardListener = new DoNothingKeyListener(model);
        this.fileChooser = new JFileChooser();
        this.latestFile = null;
    }

    @Override
    public final void actionPerformed(final ActionEvent e) {
        switch(e.getActionCommand()) {
            // For Build Mode button menu
            case("Add Gizmo"):
                view.addGizmoBuildMode();
                break;
            case("Add Ball"):

                break;
            case("Rotate"):

                break;
            case("Delete"):

                break;
            case("Move"):

                break;
            case("Clear Board"):
                model.clear();
                break;
            case("Connect"):

                break;
            case("Disconnect"):

                break;
            case("Key Connect"):

                break;
            case("Key Disconnect"):

                break;
            case("Load Model"):
                int returnVal = fileChooser.showOpenDialog(view.getMainFrame());

                if(returnVal == JFileChooser.APPROVE_OPTION){
                    // open chosen file
                    latestFile = fileChooser.getSelectedFile();
                    model.loadFile(latestFile.getAbsolutePath());
                }else{
                    // close window
                }
                break;
            case("Run Mode"):
                view.runMode();
                break;
            case("Quit"):
                System.out.println("Quitting...");
                System.exit(0);
                break;

            // Build Mode "Add Gizmo" button menu
            case("Add Square"):
                final MouseInputListener addSquareListener = new AddSquareListener(model, view);

                view.setBuildModeMessage("Click on the board to add a square");
                view.update();

                System.out.println("Set build mode message for adding squares");
                setMouseListener(addSquareListener);
                break;
            case("Add Circle"):

                break;
            case("Add Triangle"):

                break;
            case("Add Absorber"):

                break;
            case("Add Left Flipper"):

                break;
            case("Add Right Flipper"):

                break;
            case("Back"):
                view.buildMode();
                break;
        }
    }

    public void setKeyboardListener(KeyListener keyboardListener){
        this.keyboardListener = keyboardListener;
        System.out.println("keyboardListener field set to "+ keyboardListener);
    }

    public void setMouseListener(MouseInputListener mouseListener){
        this.mouseListener = mouseListener;
        System.out.println("mouseListener field set to "+ mouseListener);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        keyboardListener.keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyboardListener.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyboardListener.keyReleased(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseListener.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseListener.mousePressed(e);
        System.out.println("mouse pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseListener.mouseReleased(e);
        System.out.println("mouse released");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mouseListener.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mouseListener.mouseExited(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseListener.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseListener.mouseMoved(e);
    }
}
