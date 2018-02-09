package gui;

import javax.swing.*;

public class BuildModeGUI implements GameGUI {

    // JPanel containing build mode buttons
    private JPanel buttonPanel;
    private JButton runModeButton;
    private JButton quitButton;

    // JMenuBar for build mode options
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem testMenuItem;

    // JPanel for displaying information at the bottom of the screen
    private JPanel messagePanel;

    public BuildModeGUI(){

    }

    @Override
    public JPanel createButtons() {
        buttonPanel = new JPanel();
        runModeButton = new JButton("Run Mode");
        quitButton = new JButton("Quit");

        buttonPanel.add(runModeButton);
        buttonPanel.add(quitButton);

        return buttonPanel;
    }

    @Override
    public JMenuBar createMenuBar() {
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        testMenuItem = new JMenuItem("Test Item");

        fileMenu.add(testMenuItem);
        menuBar.add(fileMenu);

        return menuBar;
    }

    @Override
    public JPanel createMessageField() {
        messagePanel = new JPanel();

        return messagePanel;
    }
}
