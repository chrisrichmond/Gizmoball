package gui.Listeners;

import Model.ModelAPI;
import gui.RunModeGUI;
import gui.View;

import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class RunListener implements GBallListener{

    private Timer timer;
    private ModelAPI model;
    private View view;
    private MouseInputListener mouseListener, doNothingMouse;
    private KeyListener keyboardListener, doNothingKeyboard;
    private JFileChooser fileChooser;
    private FileNameExtensionFilter extensionFilter;
    private File latestFile;

    public RunListener(ModelAPI model, View view){
        this.timer = new Timer(50, this);
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
        if(e.getSource() == timer){
            model.moveBall();
        }else{
            switch(e.getActionCommand()){
                case("Start"):
                    timer.start();
                    RunModeGUI.disableButtons();
                    break;
                case("Stop"):
                    timer.stop();
                    RunModeGUI.enableButtons();
                    break;
                case("Tick"):
                    model.moveBall();
                    break;
                case("Load Model"):
                    int returnVal = fileChooser.showOpenDialog(view.getMainFrame());

                    if(returnVal == JFileChooser.APPROVE_OPTION){
                        // open chosen file
                        latestFile = fileChooser.getSelectedFile();
                        try {
                            model.loadFile(latestFile.getAbsolutePath());
                        }catch(FileNotFoundException ex){
                            System.out.println("File input not found ");
                        }
                    }else{
                        // close window
                    }

                    break;
                case("Reload"):
                    System.out.println("reload pressed");
                    if(latestFile != null){
                        try {
                            model.loadFile(latestFile.getAbsolutePath());
                        }catch(FileNotFoundException ex){
                            System.out.println("File input not found ");
                        }                    }
                    break;
                case("Build Mode"):
                    timer.stop();
                    view.buildMode();
                    break;
                case("Quit"):
                    System.out.println("Quitting...");
                    System.exit(0);
                    break;
            }
        }
    }

    @Override
    public void setMouseListener(MouseInputListener mouseListener) {
        this.mouseListener = mouseListener;
    }

    @Override
    public void setKeyboardListener(KeyListener keyboardListener) {
        this.keyboardListener = keyboardListener;
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
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseListener.mouseReleased(e);
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