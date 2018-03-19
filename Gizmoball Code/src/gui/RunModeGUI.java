package gui;

import Model.ModelAPI;
import gui.Listeners.GBallListener;
import gui.Listeners.RunListener;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class RunModeGUI implements GameGUI {

    private ModelAPI model;
    private View view;

    // JPanel containing run mode buttons
    private JPanel buttonPanel;
    private static JButton startButton;
    private static JButton stopButton;
    private static JButton tickButton;
    private static JButton loadButton;
    private JButton reloadButton;
    private static JButton buildModeButton;
    private JButton quitButton;
    private Font font;
    private Dimension maxButtonSize;
    private GBallListener listener;

    // JMenuBar for run mode options
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem testMenuItem;

    // JPanel for displaying information at the bottom of the screen
    private JPanel messagePanel;

    public RunModeGUI(ModelAPI model, View view){
        this.model = model;
        font = new Font("Arial", Font.BOLD, 12);
        maxButtonSize = new Dimension(150,50);
        listener = new RunListener(model, view);
    }

    public static void disableButtons(){
        tickButton.setEnabled(false);
        loadButton.setEnabled(false);
        buildModeButton.setEnabled(false);
        startButton.setEnabled(false);
        stopButton.setEnabled(true);

    }


    public static void enableButtons(){
        tickButton.setEnabled(true);
        loadButton.setEnabled(true);
        buildModeButton.setEnabled(true);
        stopButton.setEnabled(false);
        startButton.setEnabled(true);
    }

    @Override
    public JPanel createButtons(GBallListener listener) {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(7,1));

        startButton = new JButton("Start");
        startButton.setFont(font);
        startButton.setMaximumSize(maxButtonSize);
        startButton.addActionListener(listener);
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(Color.BLACK);
        startButton.setFocusPainted(false);

        stopButton = new JButton("Stop");
        stopButton.setFont(font);
        stopButton.setEnabled(false);
        stopButton.setMaximumSize(maxButtonSize);
        stopButton.addActionListener(listener);
        stopButton.setForeground(Color.WHITE);
        stopButton.setBackground(Color.BLACK);
        stopButton.setFocusPainted(false);

        tickButton = new JButton("Tick");
        tickButton.setFont(font);
        tickButton.setMaximumSize(maxButtonSize);
        tickButton.addActionListener(listener);
        tickButton.setForeground(Color.WHITE);
        tickButton.setBackground(Color.BLACK);
        tickButton.setFocusPainted(false);

        loadButton = new JButton("Load Model");
        loadButton.setFont(font);
        loadButton.setMaximumSize(maxButtonSize);
        loadButton.addActionListener(listener);
        loadButton.setForeground(Color.WHITE);
        loadButton.setBackground(Color.BLACK);
        loadButton.setFocusPainted(false);

        reloadButton = new JButton("Reload");
        reloadButton.setFont(font);
        reloadButton.setMaximumSize(maxButtonSize);
        reloadButton.addActionListener(listener);
        reloadButton.setForeground(Color.WHITE);
        reloadButton.setBackground(Color.BLACK);
        reloadButton.setFocusPainted(false);

        buildModeButton = new JButton("Build Mode");
        buildModeButton.setFont(font);
        buildModeButton.setMaximumSize(maxButtonSize);
        buildModeButton.addActionListener(listener);
        buildModeButton.setForeground(Color.WHITE);
        buildModeButton.setBackground(Color.BLACK);
        buildModeButton.setFocusPainted(false);

        quitButton = new JButton("Quit");
        quitButton.setFont(font);
        quitButton.setMaximumSize(maxButtonSize);
        quitButton.addActionListener(listener);
        quitButton.setForeground(Color.WHITE);
        quitButton.setBackground(Color.BLACK);
        quitButton.setFocusPainted(false);

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
    public JMenuBar createMenuBar(GBallListener listener) {
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        testMenuItem = new JMenuItem("Test Item");

        fileMenu.add(testMenuItem);
        menuBar.add(fileMenu);

        return menuBar;
    }

    @Override
    public JPanel createMessageField(String message) {
        messagePanel = new JPanel();
        JLabel messageLabel = new JLabel(message);

        messageLabel.setFont(font);
        messagePanel.add(messageLabel);
        messagePanel.setBorder(BorderFactory.createLineBorder(Color.blue));

        return messagePanel;
    }
}
