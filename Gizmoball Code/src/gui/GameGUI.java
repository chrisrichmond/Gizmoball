package gui;

import javax.swing.*;

public interface GameGUI {

    JPanel createButtons();
    JMenuBar createMenuBar();
    JPanel createMessageField(String message);
}
