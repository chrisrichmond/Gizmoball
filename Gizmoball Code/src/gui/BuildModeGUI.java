package gui;

import Model.ModelAPI;
import gui.Listeners.AddSquareListener;
import gui.Listeners.BuildListener;
import gui.Listeners.GBallListener;
import gui.Listeners.RunListener;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class BuildModeGUI implements GameGUI {

    private ModelAPI model;
    private View view;

    // JPanel containing build mode buttons or add gizmo buttons
    private JPanel buttonPanel;

    private final int buttonRows = 20;

    // Build mode buttons
    private JButton runModeButton;
    private JButton addBallButton;
    private JButton addGizmoButton;
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


    // Sliders
    static final int GravMin = 0;
    static final int GravMAx = 30;
    static final int GravInit = 15;






    // Add gizmo buttons
    private JButton addSquareButton;
    private JButton addCircleButton;
    private JButton addTriangleButton;
    private JButton addAbsorberButton;
    private JButton addLeftFlipperButton;
    private JButton addRightFlipperButton;
    private JButton backButton;

    private Font font;
    private Dimension maxButtonSize;
    private GBallListener listener;

    // JMenuBar for build mode options
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem saveModelMenuItem;
    private JMenuItem settingsMenuItem;

    // JPanel for displaying information at the bottom of the screen
    private JPanel messagePanel;

    public BuildModeGUI(ModelAPI model, View view){
        this.model = model;
        this.view = view;
        font = new Font("Arial", Font.BOLD, 12);
        maxButtonSize = new Dimension(150,50);
    }

    @Override
    public JPanel createButtons(GBallListener listener) {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(buttonRows,1));

        addGizmoButton = new JButton("Add Gizmo");
        addGizmoButton.setFont(font);
        addGizmoButton.setMaximumSize(maxButtonSize);
        addGizmoButton.addActionListener(listener);

        addBallButton = new JButton("Add Ball");
        addBallButton.setFont(font);
        addBallButton.setMaximumSize(maxButtonSize);
        addBallButton.addActionListener(listener);

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

        clearBoardButton = new JButton("Clear Board");
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


        JLabel gravSlider = new JLabel("Grav", JLabel.CENTER);
        gravSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
        JSlider grav = new JSlider(JSlider.HORIZONTAL, GravMin, GravMAx, GravInit);
        //grav.addChangeListener(this);
        grav.setMajorTickSpacing(10);
        grav.setMinorTickSpacing(1);
        grav.setPaintTicks(true);
        grav.setPaintLabels(true);


        quitButton = new JButton("Quit");
        quitButton.setFont(font);
        quitButton.setMaximumSize(maxButtonSize);
        quitButton.addActionListener(listener);


        buttonPanel.add(addGizmoButton);
        buttonPanel.add(addBallButton);
        buttonPanel.add(rotateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(gravSlider);
        buttonPanel.add(grav);
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

    public JPanel createGizmoButtons(GBallListener listener) {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(buttonRows,1));

        addSquareButton = new JButton("Add Square");
        addSquareButton.setFont(font);
        addSquareButton.setMaximumSize(maxButtonSize);
        addSquareButton.addActionListener(listener);

        addCircleButton = new JButton("Add Circle");
        addCircleButton.setFont(font);
        addCircleButton.setMaximumSize(maxButtonSize);
        addCircleButton.addActionListener(listener);

        addTriangleButton = new JButton("Add Triangle");
        addTriangleButton.setFont(font);
        addTriangleButton.setMaximumSize(maxButtonSize);
        addTriangleButton.addActionListener(listener);

        addAbsorberButton = new JButton("Add Absorber");
        addAbsorberButton.setFont(font);
        addAbsorberButton.setMaximumSize(maxButtonSize);
        addAbsorberButton.addActionListener(listener);

        addLeftFlipperButton = new JButton("Add Left Flipper");
        addLeftFlipperButton.setFont(font);
        addLeftFlipperButton.setMaximumSize(maxButtonSize);
        addLeftFlipperButton.addActionListener(listener);

        addRightFlipperButton = new JButton("Add Right Flipper");
        addRightFlipperButton.setFont(font);
        addRightFlipperButton.setMaximumSize(maxButtonSize);
        addRightFlipperButton.addActionListener(listener);

        backButton = new JButton("Back");
        backButton.setFont(font);
        backButton.setMaximumSize(maxButtonSize);
        backButton.addActionListener(listener);

        buttonPanel.add(addSquareButton);
        buttonPanel.add(addCircleButton);
        buttonPanel.add(addTriangleButton);
        buttonPanel.add(addAbsorberButton);
        buttonPanel.add(addLeftFlipperButton);
        buttonPanel.add(addRightFlipperButton);
        buttonPanel.add(backButton);

        buttonPanel.setSize(new Dimension(200,800));

        return buttonPanel;
    }

    @Override
    public JMenuBar createMenuBar(GBallListener listener) {
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");

        saveModelMenuItem = new JMenuItem("Save Model");
        saveModelMenuItem.addActionListener(listener);
        saveModelMenuItem.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));

        settingsMenuItem = new JMenuItem("Settings");
        settingsMenuItem.addActionListener(listener);
        settingsMenuItem.setAccelerator(KeyStroke.getKeyStroke('E', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));

        fileMenu.add(saveModelMenuItem);
        fileMenu.add(settingsMenuItem);
        menuBar.add(fileMenu);

        return menuBar;
    }

    public JPanel createMessageField(String message) {
        messagePanel = new JPanel();
        JLabel messageLabel = new JLabel(message);

        messageLabel.setFont(font);
        messagePanel.add(messageLabel);
        messagePanel.setBorder(BorderFactory.createLineBorder(Color.blue));

        return messagePanel;
    }
}
