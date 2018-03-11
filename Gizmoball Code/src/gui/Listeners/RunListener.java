package gui.Listeners;

import Model.ModelAPI;
import gui.View;

import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.event.MouseInputListener;

public class RunListener implements GBallListener{

    private Timer timer;
    private ModelAPI model;
    private View view;
    private MouseInputListener mouseListener, doNothingMouse;
    private KeyListener keyboardListener, doNothingKeyboard;
    private JFileChooser fileChooser;
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
                    break;
                case("Stop"):
                    timer.stop();
                    break;
                case("Tick"):
                    model.moveBall();
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
                case("Reload"):
                    System.out.println("reload pressed");
                    if(latestFile != null){
                        model.loadFile(latestFile.getAbsolutePath());
                    }
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