package gui;

import Model.ModelAPI;
import gui.Listeners.AddSquareListener;
import gui.Listeners.BuildListener;
import gui.Listeners.GBallListener;
import gui.Listeners.RunListener;
import utilities.GizmoConstants;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class BuildModeGUI implements GameGUI {

    private ModelAPI model;
    private View view;
    private Color themeBG = GizmoConstants.buildColourTheme;
    private Color themeFG = GizmoConstants.getComplimentaryColour(GizmoConstants.buildColourTheme);

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

    private JSlider gravSlider;
    private JLabel gravSliderLabel;
    private GravSliderListener gravSliderListener;

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
        addGizmoButton.setForeground(themeFG);
        addGizmoButton.setBackground(themeBG);
        addGizmoButton.setFocusPainted(false);


        addBallButton = new JButton("Add Ball");
        addBallButton.setFont(font);
        addBallButton.setMaximumSize(maxButtonSize);
        addBallButton.addActionListener(listener);
        addBallButton.setForeground(themeFG);
        addBallButton.setBackground(themeBG);
        addBallButton.setFocusPainted(false);

        rotateButton = new JButton("Rotate");
        rotateButton.setFont(font);
        rotateButton.setMaximumSize(maxButtonSize);
        rotateButton.addActionListener(listener);
        rotateButton.setForeground(themeFG);
        rotateButton.setBackground(themeBG);
        rotateButton.setFocusPainted(false);

        deleteButton = new JButton("Delete");
        deleteButton.setFont(font);
        deleteButton.setMaximumSize(maxButtonSize);
        deleteButton.addActionListener(listener);
        deleteButton.setForeground(themeFG);
        deleteButton.setBackground(themeBG);
        deleteButton.setFocusPainted(false);

        moveButton = new JButton("Move");
        moveButton.setFont(font);
        moveButton.setMaximumSize(maxButtonSize);
        moveButton.addActionListener(listener);
        moveButton.setForeground(themeFG);
        moveButton.setBackground(themeBG);
        moveButton.setFocusPainted(false);


        clearBoardButton = new JButton("Clear Board");
        clearBoardButton.setFont(font);
        clearBoardButton.setMaximumSize(maxButtonSize);
        clearBoardButton.addActionListener(listener);
        clearBoardButton.setForeground(themeFG);
        clearBoardButton.setBackground(themeBG);
        clearBoardButton.setFocusPainted(false);

        connectButton = new JButton("Connect");
        connectButton.setFont(font);
        connectButton.setMaximumSize(maxButtonSize);
        connectButton.addActionListener(listener);
        connectButton.setForeground(themeFG);
        connectButton.setBackground(themeBG);
        connectButton.setFocusPainted(false);

        disconnectButton = new JButton("Disconnect");
        disconnectButton.setFont(font);
        disconnectButton.setMaximumSize(maxButtonSize);
        disconnectButton.addActionListener(listener);
        disconnectButton.setForeground(themeFG);
        disconnectButton.setBackground(themeBG);
        disconnectButton.setFocusPainted(false);

        keyConnectButton = new JButton("Key Connect");
        keyConnectButton.setFont(font);
        keyConnectButton.setMaximumSize(maxButtonSize);
        keyConnectButton.addActionListener(listener);
        keyConnectButton.setForeground(themeFG);
        keyConnectButton.setBackground(themeBG);
        keyConnectButton.setFocusPainted(false);

        keyDisconnectButton = new JButton("Key Disconnect");
        keyDisconnectButton.setFont(font);
        keyDisconnectButton.setMaximumSize(maxButtonSize);
        keyDisconnectButton.addActionListener(listener);
        keyDisconnectButton.setForeground(themeFG);
        keyDisconnectButton.setBackground(themeBG);
        keyDisconnectButton.setFocusPainted(false);

        loadModelButton = new JButton("Load Model");
        loadModelButton.setFont(font);
        loadModelButton.setMaximumSize(maxButtonSize);
        loadModelButton.addActionListener(listener);
        loadModelButton.setForeground(themeFG);
        loadModelButton.setBackground(themeBG);
        loadModelButton.setFocusPainted(false);

        runModeButton = new JButton("Run Mode");
        runModeButton.setFont(font);
        runModeButton.setMaximumSize(maxButtonSize);
        runModeButton.addActionListener(listener);
        runModeButton.setForeground(themeFG);
        runModeButton.setBackground(themeBG);
        runModeButton.setFocusPainted(false);


        gravSliderLabel = new JLabel("Gravity Slider", JLabel.CENTER);
        gravSliderLabel.setFont(font);
        //JLabel SPACERH4X = new JLabel("", JLabel.CENTER);
        gravSliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JSlider gravSlider = new JSlider(JSlider.HORIZONTAL, GravMin, GravMAx, GravInit);
        // This line does nothing ??? what WHY -- idk man
        //gravSlider.setPreferredSize(new Dimension(100,100));

        //grav.addChangeListener(this);
        gravSlider.setMajorTickSpacing(10);
        gravSlider.setMinorTickSpacing(1);
        gravSlider.setPaintTicks(true);
        gravSlider.setPaintLabels(true);
        gravSliderListener = new GravSliderListener(model, view, gravSlider);
        gravSlider.addChangeListener(gravSliderListener);


        quitButton = new JButton("Quit");
        quitButton.setFont(font);
        quitButton.setMaximumSize(maxButtonSize);
        quitButton.addActionListener(listener);
        quitButton.setForeground(themeFG);
        quitButton.setBackground(themeBG);
        quitButton.setFocusPainted(false);


        buttonPanel.add(addGizmoButton);
        buttonPanel.add(addBallButton);
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

        //buttonPanel.add(SPACERH4X);
        //buttonPanel.add(SPACERH4X);
        buttonPanel.add(gravSliderLabel);
        buttonPanel.add(gravSlider);




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
        addSquareButton.setForeground(Color.WHITE);
        addSquareButton.setBackground(Color.BLACK);
        addSquareButton.setFocusPainted(false);


        addCircleButton = new JButton("Add Circle");
        addCircleButton.setFont(font);
        addCircleButton.setMaximumSize(maxButtonSize);
        addCircleButton.addActionListener(listener);
        addCircleButton.setForeground(Color.WHITE);
        addCircleButton.setBackground(Color.BLACK);
        addCircleButton.setFocusPainted(false);


        addTriangleButton = new JButton("Add Triangle");
        addTriangleButton.setFont(font);
        addTriangleButton.setMaximumSize(maxButtonSize);
        addTriangleButton.addActionListener(listener);
        addTriangleButton.setForeground(Color.WHITE);
        addTriangleButton.setBackground(Color.BLACK);
        addTriangleButton.setFocusPainted(false);


        addAbsorberButton = new JButton("Add Absorber");
        addAbsorberButton.setFont(font);
        addAbsorberButton.setMaximumSize(maxButtonSize);
        addAbsorberButton.addActionListener(listener);
        addAbsorberButton.setForeground(Color.WHITE);
        addAbsorberButton.setBackground(Color.BLACK);
        addAbsorberButton.setFocusPainted(false);


        addLeftFlipperButton = new JButton("Add Left Flipper");
        addLeftFlipperButton.setFont(font);
        addLeftFlipperButton.setMaximumSize(maxButtonSize);
        addLeftFlipperButton.addActionListener(listener);
        addLeftFlipperButton.setForeground(Color.WHITE);
        addLeftFlipperButton.setBackground(Color.BLACK);
        addLeftFlipperButton.setFocusPainted(false);


        addRightFlipperButton = new JButton("Add Right Flipper");
        addRightFlipperButton.setFont(font);
        addRightFlipperButton.setMaximumSize(maxButtonSize);
        addRightFlipperButton.addActionListener(listener);
        addRightFlipperButton.setForeground(Color.WHITE);
        addRightFlipperButton.setBackground(Color.BLACK);
        addRightFlipperButton.setFocusPainted(false);


        backButton = new JButton("Back");
        backButton.setFont(font);
        backButton.setMaximumSize(maxButtonSize);
        backButton.addActionListener(listener);
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.BLACK);
        backButton.setFocusPainted(false);




        // Adding rotate to AddGizmo menu
        rotateButton = new JButton("Rotate");
        rotateButton.setFont(font);
        rotateButton.setMaximumSize(maxButtonSize);
        rotateButton.addActionListener(listener);
        rotateButton.setForeground(Color.WHITE);
        rotateButton.setBackground(Color.BLACK);
        rotateButton.setFocusPainted(false);





        buttonPanel.add(addSquareButton);
        buttonPanel.add(addCircleButton);
        buttonPanel.add(addTriangleButton);
        buttonPanel.add(addAbsorberButton);
        buttonPanel.add(addLeftFlipperButton);
        buttonPanel.add(addRightFlipperButton);
        // Adding rotate to AddGizmo menu
        buttonPanel.add(rotateButton);
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

        messageLabel.setFont(font.deriveFont(Font.BOLD, 13f));
        messagePanel.add(messageLabel);
        messagePanel.setBorder(BorderFactory.createLineBorder(Color.blue));

        return messagePanel;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int GravityFromSlider = gravSlider.getValue();
        System.out.println(GravityFromSlider);
    }
}
