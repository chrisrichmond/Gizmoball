package gui;

import Model.ModelAPI;
import gui.Listeners.BuildListener;
import gui.Listeners.RunListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BuildModeGUI implements GameGUI {

    private ModelAPI model;
    private View view;

    // JPanel containing build mode buttons
    private JPanel buttonPanel;
    private JButton runModeButton;
    private JButton quitButton;
    private Font font;
    private Dimension maxButtonSize;
    private ActionListener listener;

    // JMenuBar for build mode options
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem testMenuItem;

    // JPanel for displaying information at the bottom of the screen
    private JPanel messagePanel;

    public BuildModeGUI(ModelAPI model, View view){
        this.model = model;
        this.view = view;
        font = new Font("Arial", Font.BOLD, 12);
        maxButtonSize = new Dimension(150,50);
        listener = new BuildListener(model, view);
    }

    @Override
    public JPanel createButtons() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2,1));

        runModeButton = new JButton("Run Mode");
        runModeButton.setFont(font);
        runModeButton.setMaximumSize(maxButtonSize);
        runModeButton.addActionListener(listener);

        quitButton = new JButton("Quit");
        quitButton.setFont(font);
        quitButton.setMaximumSize(maxButtonSize);
        runModeButton.addActionListener(listener);

        buttonPanel.add(runModeButton);
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
