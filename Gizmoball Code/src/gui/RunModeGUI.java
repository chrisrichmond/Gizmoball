package gui;

import javax.swing.*;
import java.awt.*;

public class RunModeGUI implements GameGUI {

    // JPanel containing run mode buttons
    private JPanel buttonPanel;
    private JButton startButton;
    private JButton stopButton;
    private JButton tickButton;
    private JButton loadButton;
    private JButton reloadButton;
    private JButton buildModeButton;
    private JButton quitButton;

    // JMenuBar for run mode options
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem testMenuItem;

    // JPanel for displaying information at the bottom of the screen
    private JPanel messagePanel;

    public RunModeGUI(){

    }

    @Override
    public JPanel createButtons() {
        buttonPanel = new JPanel();
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        tickButton = new JButton("Tick");
        loadButton = new JButton("Load Model");
        reloadButton = new JButton("Reload Model");
        buildModeButton = new JButton("Build Mode");
        quitButton = new JButton("Quit");

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(tickButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(reloadButton);
        buttonPanel.add(buildModeButton);
        buttonPanel.add(quitButton);

        buttonPanel.setSize(new Dimension(200,800));

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
