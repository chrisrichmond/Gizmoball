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
    private JButton addBallButton;
    private JButton addAbsorberButton;
    private JButton addGizmoButton;
    private JButton addLeftFlipperButton;
    private JButton addRightFlipperButton;
    private JButton rotateButton;
    private JButton deleteButton;
    private JButton moveButton;
    private JButton clearBoardButton;
    private JButton connectButton;
    private JButton disconnectButton;
    private JButton keyConnectButton;
    private JButton keyDisconnectButton;
    private JButton loadModelButton;
    private JButton reloadModelButton;
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
        buttonPanel.setLayout(new GridLayout(20,1));

        addGizmoButton = new JButton("Add Gizmo");
        addGizmoButton.setFont(font);
        addGizmoButton.setMaximumSize(maxButtonSize);
        addGizmoButton.addActionListener(listener);

        addBallButton = new JButton("Add Ball");
        addBallButton.setFont(font);
        addBallButton.setMaximumSize(maxButtonSize);
        addBallButton.addActionListener(listener);

        addAbsorberButton = new JButton("Add Absorber");
        addAbsorberButton.setFont(font);
        addAbsorberButton.setMaximumSize(maxButtonSize);
        addAbsorberButton.addActionListener(listener);

        addLeftFlipperButton = new JButton("Add left flipper");
        addLeftFlipperButton.setFont(font);
        addLeftFlipperButton.setMaximumSize(maxButtonSize);
        addLeftFlipperButton.addActionListener(listener);

        addRightFlipperButton = new JButton("Add right flipper");
        addRightFlipperButton.setFont(font);
        addRightFlipperButton.setMaximumSize(maxButtonSize);
        addRightFlipperButton.addActionListener(listener);

        rotateButton = new JButton("Rotate");
        rotateButton.setFont(font);
        rotateButton.setMaximumSize(maxButtonSize);
        rotateButton.addActionListener(listener);

        deleteButton = new JButton("Delete");
        deleteButton.setFont(font);
        deleteButton.setMaximumSize(maxButtonSize);
        deleteButton.addActionListener(listener);

        moveButton = new JButton("Move");
        moveButton.setFont(font);
        moveButton.setMaximumSize(maxButtonSize);
        moveButton.addActionListener(listener);

        clearBoardButton = new JButton("Clear board");
        clearBoardButton.setFont(font);
        clearBoardButton.setMaximumSize(maxButtonSize);
        clearBoardButton.addActionListener(listener);

        connectButton = new JButton("Connect");
        connectButton.setFont(font);
        connectButton.setMaximumSize(maxButtonSize);
        connectButton.addActionListener(listener);

        disconnectButton = new JButton("Disconnect");
        disconnectButton.setFont(font);
        disconnectButton.setMaximumSize(maxButtonSize);
        disconnectButton.addActionListener(listener);

        keyConnectButton = new JButton("Key Connect");
        keyConnectButton.setFont(font);
        keyConnectButton.setMaximumSize(maxButtonSize);
        keyConnectButton.addActionListener(listener);

        keyDisconnectButton = new JButton("Key Disconnect");
        keyDisconnectButton.setFont(font);
        keyDisconnectButton.setMaximumSize(maxButtonSize);
        keyDisconnectButton.addActionListener(listener);

        loadModelButton = new JButton("Load Model");
        loadModelButton.setFont(font);
        loadModelButton.setMaximumSize(maxButtonSize);
        loadModelButton.addActionListener(listener);

        runModeButton = new JButton("Run Mode");
        runModeButton.setFont(font);
        runModeButton.setMaximumSize(maxButtonSize);
        runModeButton.addActionListener(listener);

        quitButton = new JButton("Quit");
        quitButton.setFont(font);
        quitButton.setMaximumSize(maxButtonSize);
        quitButton.addActionListener(listener);


        buttonPanel.add(addGizmoButton);
        buttonPanel.add(addBallButton);


        buttonPanel.add(addLeftFlipperButton);
        buttonPanel.add(addRightFlipperButton);
        buttonPanel.add(rotateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(moveButton);
        buttonPanel.add(clearBoardButton);
        buttonPanel.add(connectButton);
        buttonPanel.add(disconnectButton);
        buttonPanel.add(keyConnectButton);
        buttonPanel.add(keyDisconnectButton);
        buttonPanel.add(loadModelButton);
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
