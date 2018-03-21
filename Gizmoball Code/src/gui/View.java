package gui;

import Model.ModelAPI;
import com.sun.org.apache.xpath.internal.SourceTree;
import gui.Listeners.BuildListener;
import gui.Listeners.GBallListener;
import gui.Listeners.RunListener;
import gui.Listeners.SettingsListener;
import utilities.Observer;

import javax.swing.*;
import java.awt.*;

public class View implements Observer{

    // Model Backend
    private ModelAPI model;

    // Pixels Per Line
    private int ppl;

    // Frame and Content Pane
    private JFrame mainFrame;
    private Container cp; // content pane for mainFrame

    // Boards
    private JPanel buildBoard, runBoard, currentBoard;

    // GUIs & Listeners
    private GameGUI buildGUI, runGUI; // currentGUI required? // probably not
    private GBallListener buildListener, runListener;

    // Components
    private JPanel buildButtonPanel, addGizmoButtonPanel, runButtonPanel, currentButtonPanel;
    private JPanel buildMessagePanel, runMessagePanel, currentMessagePanel;
    private JMenuBar buildMenuBar, runMenuBar, currentMenuBar;

    // Dimensions
    private Dimension boardDim, buttonPanelDim, messagePanelDim, idealFrameDim;

    boolean status;
    private boolean isBuildMode;

    public View(ModelAPI model){
        // Model Backend
        this.model = model;
        model.attach(this); // observer attachment

        // Pixels Per Line
        this.ppl = 25;

        // Main Frame and Content Pane
        mainFrame = new JFrame("Gizmoball");
        cp = mainFrame.getContentPane();

        // Boards
        buildBoard = new BuildModeBoard(model, ppl);
        runBoard = new RunModeBoard(model, ppl);

        // GUIs & Listeners
        buildGUI = new BuildModeGUI(model, this);
        runGUI = new RunModeGUI(model, this);
        buildListener = new BuildListener(model, this);
        runListener = new RunListener(model, this);

        buildBoard.addMouseListener(buildListener);
        runBoard.addMouseListener(runListener);

        // Components
        buildButtonPanel = buildGUI.createButtons(buildListener);
        addGizmoButtonPanel = ((BuildModeGUI)buildGUI).createGizmoButtons(buildListener);
        runButtonPanel = runGUI.createButtons(runListener);
        buildMessagePanel = buildGUI.createMessageField("- Build Mode -");
        runMessagePanel = runGUI.createMessageField("- Run Mode -");
        buildMenuBar = buildGUI.createMenuBar(buildListener);
        runMenuBar = runGUI.createMenuBar(runListener);

        // Dimensions
        boardDim = new Dimension(ppl*20, ppl*20);
        System.out.println("boardDim width set to "+boardDim.width);
        System.out.println("boardDim height set to "+boardDim.height);
        buttonPanelDim = new Dimension(ppl*5,ppl*20);
        System.out.println("buttonPanelDim width set to "+buttonPanelDim.width);
        System.out.println("buttonPanelDim height set to "+buttonPanelDim.height);
        messagePanelDim = new Dimension(ppl*25, ppl*2);
        System.out.println("messagePanelDim width set to "+messagePanelDim.width);
        System.out.println("messagePanelDim height set to "+messagePanelDim.height);

        // Set layout and preferred component dimensions

        cp.setLayout(new BorderLayout());
        //cp.setLayout(new GridBagLayout());

        buildButtonPanel.setPreferredSize(buttonPanelDim);
        addGizmoButtonPanel.setPreferredSize(buttonPanelDim);
        runButtonPanel.setPreferredSize(buttonPanelDim);
        buildMessagePanel.setPreferredSize(messagePanelDim);
        runMessagePanel.setPreferredSize(messagePanelDim);
        buildBoard.setPreferredSize(boardDim);
        runBoard.setPreferredSize(boardDim);
        idealFrameDim = new Dimension(messagePanelDim.width+2, boardDim.height + messagePanelDim.height + (ppl*1)-2);
        System.out.println("idealFrameDim width set to "+idealFrameDim.width);
        System.out.println("idealFrameDim height set to "+idealFrameDim.height);
        //idealFrameDim = new Dimension(500+132, 500+53);

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        cp.setPreferredSize(idealFrameDim);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);

    }

    /**
     * Switches the View into Build Mode
     * This method should be called from the Controller upon a "Build Mode" button click
     */
    public void buildMode(){
        mainFrame.setTitle("Gizmoball [Build Mode]");

        // Remove previous mode's components
        clearFrame();

        setBuildModeMessage("- Build Mode -");
        // Add new mode's components
        currentButtonPanel = buildButtonPanel;
        currentMenuBar = buildMenuBar;
        currentBoard = buildBoard;
        currentMessagePanel = buildMessagePanel;
        addBuildKeyListener();
        addCurrentCompsToFrame();
        isBuildMode = true;
    }

    /**
     * Switches the View FROM Build Mode INTO Build Mode but with an alternate button panel exclusively for adding Gizmos
     * This method should be called from the Controller upon an "Add Gizmo" button click
     */
    public void addGizmoBuildMode(){
        mainFrame.setTitle("Gizmoball [Build Mode]");

        // Remove previous mode's components
        clearFrame();

        setBuildModeMessage("Add a Gizmo to the board");
        // Add new mode's components
        currentButtonPanel = addGizmoButtonPanel;
        currentMenuBar = buildMenuBar;
        currentBoard = buildBoard;
        currentMessagePanel = buildMessagePanel;
        addBuildKeyListener();
        addCurrentCompsToFrame();
        isBuildMode = true;
    }

    /**
     * Switches the View into Run Mode
     * This method should be called from the Controller upon a "Run Mode" button click
     */
    public void runMode(){
        mainFrame.setTitle("Gizmoball [Run Mode]");

        // Remove previous mode's components
        clearFrame();

        setRunModeMessage("- Run Mode -");
        // Add new mode's components
        currentButtonPanel = runButtonPanel;
        currentMenuBar = runMenuBar;
        currentBoard = runBoard;
        currentMessagePanel = runMessagePanel;
        addCurrentCompsToFrame();
        isBuildMode = false;
    }

    public void addBuildKeyListener(){

        this.mainFrame.addKeyListener(
                new MagicKeyListener(
                        new RunKeyListener(mainFrame)
                )
        );
    }

    /**
     * Clears the frame of all components
     */
    private void clearFrame(){
        try{
            cp.remove(currentButtonPanel);
            cp.remove(currentMenuBar);
            cp.remove(currentBoard);
            cp.remove(currentMessagePanel);
        }catch(NullPointerException npx){
            System.out.println(npx.getStackTrace() + " NullPointerException: trying to remove JComponent that wasn't there");
        }catch(ClassCastException ccx){
            System.out.println(ccx.getStackTrace() + " ClassCastException: tried to cast currentBoard (which is of interface type Board) as JPanel");
        }
    }

    private void addCurrentCompsToFrame(){
        cp.add(currentMenuBar, BorderLayout.PAGE_START);
        cp.add(currentButtonPanel, BorderLayout.LINE_START);
        cp.add(currentBoard, BorderLayout.CENTER);
        cp.add(currentMessagePanel, BorderLayout.PAGE_END);
        cp.setPreferredSize(idealFrameDim);
        System.out.println("mainFrame width set to "+mainFrame.getWidth());
        System.out.println("mainFrame height set to "+mainFrame.getHeight());
        mainFrame.repaint();
        mainFrame.pack();
        //mainFrame.setLocationRelativeTo(null);
    }

    public void openSettingsFrame(){

        //
        // Run project, Open Settings menu, Add Gizmo, it breaks
        //


        JFrame settingsFrame = new JFrame("Gizmoball Settings");
        Container settingsCp = settingsFrame.getContentPane();
        settingsCp.setLayout(new GridLayout(5,2));

        GBallListener settingsListener = new SettingsListener(model, this);

        JLabel buildThemeLabel = new JLabel("Select Build Theme");
        JLabel runThemeLabel = new JLabel("Select Run Theme");
        ButtonGroup buildGroup = new ButtonGroup();
        ButtonGroup runGroup = new ButtonGroup();

        JRadioButton buildBlack = new JRadioButton("Black");
        JRadioButton buildWhite = new JRadioButton("White");
        JRadioButton buildRed = new JRadioButton("Red");
        JRadioButton buildOrange = new JRadioButton("Orange");
        buildBlack.setActionCommand("Build Black");
        buildWhite.setActionCommand("Build White");
        buildRed.setActionCommand("Build Red");
        buildOrange.setActionCommand("Build Orange");

        JRadioButton runBlack = new JRadioButton("Black");
        JRadioButton runWhite = new JRadioButton("White");
        JRadioButton runRed = new JRadioButton("Red");
        JRadioButton runOrange = new JRadioButton("Orange");
        runBlack.setActionCommand("Run Black");
        runWhite.setActionCommand("Run White");
        runRed.setActionCommand("Run Red");
        runOrange.setActionCommand("Run Orange");

        buildBlack.addActionListener(settingsListener);
        buildWhite.addActionListener(settingsListener);
        buildRed.addActionListener(settingsListener);
        buildOrange.addActionListener(settingsListener);

        runBlack.addActionListener(settingsListener);
        runWhite.addActionListener(settingsListener);
        runRed.addActionListener(settingsListener);
        runOrange.addActionListener(settingsListener);

        buildGroup.add(buildBlack);
        buildGroup.add(buildWhite);
        buildGroup.add(buildRed);
        buildGroup.add(buildOrange);

        runGroup.add(buildBlack);
        runGroup.add(buildWhite);
        runGroup.add(buildRed);
        runGroup.add(buildOrange);

//        buildWhite.setSelected(true);
//        runBlack.setSelected(true);

        settingsCp.setPreferredSize(new Dimension(300,200));
        settingsCp.add(buildThemeLabel, SwingConstants.CENTER);
        settingsCp.add(runThemeLabel, SwingConstants. CENTER);
        settingsCp.add(buildBlack);
        settingsCp.add(runBlack);
        settingsCp.add(buildWhite);
        settingsCp.add(runWhite);
        settingsCp.add(buildRed);
        settingsCp.add(runRed);
        settingsCp.add(buildOrange);
        settingsCp.add(runOrange);

        settingsFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        settingsFrame.pack();
        settingsFrame.setLocationRelativeTo(null);
        settingsFrame.setVisible(true);


    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    /**
     * This method does not change the view between build and run mode, only call it when game is staying in the same mode but you wish to alter the message in the message panel
     */
    public void updateMessagePanel(String message){

        // Remove current message panel
        try{
            cp.remove(currentMessagePanel);
        }catch(NullPointerException npx){
            System.out.println(npx.getStackTrace() + " NullPointerException: trying to remove JComponent that wasn't there");
        }catch(ClassCastException ccx){
            System.out.println(ccx.getStackTrace() + " ClassCastException: tried to cast currentBoard (which is of interface type Board) as JPanel");
        }

        // add new updated message panel
        if(isBuildMode){
            setBuildModeMessage(message);
        }else{
            setRunModeMessage(message);
        }

        cp.add(currentMessagePanel, BorderLayout.PAGE_END);
        mainFrame.repaint();
        mainFrame.pack();
        //mainFrame.setLocationRelativeTo(null);

    }

    private void setBuildModeMessage(String message){
        buildMessagePanel = buildGUI.createMessageField(message);
        currentMessagePanel = buildMessagePanel;
    }

    private void setRunModeMessage(String message){
        runMessagePanel = runGUI.createMessageField(message);
    }

    public int getPpl(){
        return ppl;
    }

    public boolean isBuildMode(){
        return isBuildMode;
    }

    @Override
    public void update() {
        mainFrame.repaint();
        ((BuildModeGUI)buildGUI).setSliderValues((int)model.getGravity(), (int)(model.getMu()*1000));
        System.out.println("Gravity = "+model.getGravity());
        System.out.println("Mu = "+model.getMu());
    }
}
