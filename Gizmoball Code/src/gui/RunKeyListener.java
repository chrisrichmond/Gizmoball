package gui;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RunKeyListener implements KeyListener {

    private JFrame model;
    public RunKeyListener(JFrame game) {
        this.model = game;
    }
    public void keyPressed(KeyEvent arg0) {

        System.out.println("Pressed");
    }
    public void keyReleased(KeyEvent arg0) {
        System.out.println("Realesed");
    }
    public void keyTyped(KeyEvent arg0){

    }


}
