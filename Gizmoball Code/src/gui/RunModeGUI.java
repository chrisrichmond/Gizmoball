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
    private Font font;
    private Dimension maxButtonSize;

    // JMenuBar for run mode options
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem testMenuItem;

    // JPanel for displaying information at the bottom of the screen
    private JPanel messagePanel;

    public RunModeGUI(){
        font = new Font("Arial", Font.BOLD, 12);
        maxButtonSize = new Dimension(150,50);
    }

    @Override
    public JPanel createButtons() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(7,1));

        startButton = new JButton("Start");
        startButton.setFont(font);
        startButton.setMaximumSize(maxButtonSize);

        stopButton = new JButton("Stop");
        stopButton.setFont(font);
        stopButton.setMaximumSize(maxButtonSize);

        tickButton = new JButton("Tick");
        tickButton.setFont(font);
        tickButton.setMaximumSize(maxButtonSize);

        loadButton = new JButton("Load Model");
        loadButton.setFont(font);
        loadButton.setMaximumSize(maxButtonSize);

        reloadButton = new JButton("Reload Model");
        reloadButton.setFont(font);
        reloadButton.setMaximumSize(maxButtonSize);

        buildModeButton = new JButton("Build Mode");
        buildModeButton.setFont(font);
        buildModeButton.setMaximumSize(maxButtonSize);

        quitButton = new JButton("Quit");
        quitButton.setFont(font);
        quitButton.setMaximumSize(maxButtonSize);

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(tickButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(reloadButton);
        buttonPanel.add(buildModeButton);
        buttonPanel.add(quitButton);
        buttonPanel.setVisible(true);

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
