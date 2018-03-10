package gui;

import gui.Listeners.GBallListener;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

public interface GameGUI {

    JPanel createButtons(GBallListener listener);
    JMenuBar createMenuBar(GBallListener listener);
    JPanel createMessageField(String message);
}
