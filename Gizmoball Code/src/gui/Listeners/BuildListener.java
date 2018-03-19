package gui.Listeners;

import Model.ModelAPI;
import gui.View;
import utilities.GizmoConstants;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;

public class BuildListener implements GBallListener {

    private ModelAPI model;
    private View view;
    private MouseInputListener mouseListener, doNothingMouse;
    private KeyListener keyboardListener, doNothingKeyboard;
    private JFileChooser fileChooser;
    private FileNameExtensionFilter extensionFilter;
    private File latestFile;

    public BuildListener(ModelAPI model, View view){
        this.model = model;
        this.view = view;
        this.doNothingMouse = new DoNothingMouseListener(model);
        this.doNothingKeyboard = new DoNothingKeyListener(model);
        this.mouseListener = doNothingMouse;
        this.keyboardListener = doNothingKeyboard;
        this.fileChooser = new JFileChooser();
        this.extensionFilter = new FileNameExtensionFilter("Gizmo file", "gizmo");
        this.fileChooser.setFileFilter(extensionFilter);
        this.fileChooser.setAcceptAllFileFilterUsed(false);
        this.latestFile = null;
    }

    @Override
    public final void actionPerformed(final ActionEvent e) {
        switch(e.getActionCommand()) {
            // Build Mode button menu
            case("Add Gizmo"):
                view.addGizmoBuildMode();
                view.updateMessagePanel("Add Gizmo - Select a gizmo type to add to the board");
                setMouseListener(doNothingMouse);
                break;
            case("Add Ball"):
                final MouseInputListener addBallListener = new AddBallListener(model, view);
                view.updateMessagePanel("Add Ball - Click on the board to add a new ball");
                setMouseListener(addBallListener);
                break;
            case("Rotate"):
                final MouseInputListener rotateListener = new RotateListener(model, view);
                view.updateMessagePanel("Rotate - Click on a triangular gizmo to rotate it");
                setMouseListener(rotateListener);
                break;
            case("Delete"):
                final MouseInputListener deleteListener = new DeleteListener(model, view);
                view.updateMessagePanel("Delete - Click on the board to delete a gizmo");
                setMouseListener(deleteListener);
                break;
            case("Move"):
                final MouseInputListener moveListener = new MoveListener(model, view);
                view.updateMessagePanel("Move - Click and drag a gizmo to reposition it");
                setMouseListener(moveListener);
                break;
            case("Clear Board"):
                model.clear();
                view.updateMessagePanel("Board Cleared");
                break;
            case("Connect"):
                view.updateMessagePanel("Connect Gizmo");
                break;
            case("Disconnect"):
                view.updateMessagePanel("Disonnect Gizmo");
                break;
            case("Key Connect"):
                view.updateMessagePanel("Key Connect Gizmo");
                break;
            case("Key Disconnect"):
                view.updateMessagePanel("Key Disconnect Gizmo");
                break;
            case("Load Model"):
                view.updateMessagePanel("Load Model");
                fileChooser.setDialogTitle("Specify a file to load");
                fileChooser.setCurrentDirectory(GizmoConstants.gizmoPathDir);
                int returnVal = fileChooser.showOpenDialog(view.getMainFrame());
                fileChooser.setDialogTitle("Load a model from file");
                if(returnVal == JFileChooser.APPROVE_OPTION){
                    // open chosen file
                    latestFile = fileChooser.getSelectedFile();
                    try {
                        model.loadFile(latestFile.getAbsolutePath());
                    }catch(FileNotFoundException ex){
                        ex.printStackTrace();
                    }
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
                view.updateMessagePanel("Add Square - Click on the board to add a square");
                setMouseListener(addSquareListener);
                break;
            case("Add Circle"):
                final MouseInputListener addCircleListener = new AddCircleListener(model, view);
                view.updateMessagePanel("Add Circle - Click on the board to add a circle");
                setMouseListener(addCircleListener);
                break;
            case("Add Triangle"):
                final MouseInputListener addTriangleListener = new AddTriangleListener(model, view);
                view.updateMessagePanel("Add Triangle - Click on the board to add a triangle");
                setMouseListener(addTriangleListener);
                break;
            case("Add Absorber"):
                final MouseInputListener addAbsorberListener = new AddAbsorberListener(model, view);
                view.updateMessagePanel("Add Absorber - Click on the board to add an absorber");
                setMouseListener(addAbsorberListener);
                break;
            case("Add Left Flipper"):
                final MouseInputListener addLeftFlipperListener = new AddLeftFlipperListener(model, view);
                view.updateMessagePanel("Add LFlipper - Click on the board to add a left flipper");
                setMouseListener(addLeftFlipperListener);
                break;
            case("Add Right Flipper"):
                final MouseInputListener addRightFlipperListener = new AddRightFlipperListener(model, view);
                view.updateMessagePanel("Add RFlipper - Click on the board to add a right flipper");
                setMouseListener(addRightFlipperListener);
                break;
            case("Back"):
                setMouseListener(doNothingMouse);
                view.buildMode();
                break;


            // Build Mode File Menu menuItems
            case("Save Model"):
                fileChooser.setDialogTitle("Specify a file to save");
                fileChooser.setCurrentDirectory(GizmoConstants.gizmoPathDir);
                int returnVal2 = fileChooser.showSaveDialog(view.getMainFrame());
                if(returnVal2 == JFileChooser.APPROVE_OPTION){
                    // save file
                    File fileToSave = fileChooser.getSelectedFile();
                    String filenameToSave = fileToSave.getAbsolutePath();
                    int filenameLength = filenameToSave.length();
                    int fileExtensionLength = GizmoConstants.fileExtension.length();

                    try{
                        if(filenameToSave.substring(filenameLength - fileExtensionLength).equals(GizmoConstants.fileExtension)){
                            // filename already includes extension so no need to add it
                            model.saveFile(filenameToSave);
                        }else{
                            // filename does not include extension so we need to append it here
                            model.saveFile(filenameToSave+GizmoConstants.fileExtension);
                        }
                    }catch(IndexOutOfBoundsException ioobx){
                        model.saveFile(filenameToSave+GizmoConstants.fileExtension);
                    }

                }else{
                    // close window
                }
                break;
            case("Settings"):

                //
                // Run project, Open Settings menu, Add Gizmo, it breaks
                //


                view.openSettingsFrame();
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
