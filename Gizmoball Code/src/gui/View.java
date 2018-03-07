package gui;

import Model.ModelAPI;
import gui.Listeners.RunListener;
import observerpattern.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class View implements Observer{

    // Model Backend
    private ModelAPI model;

    // Pixels Per Line
    private int ppl;

    // Main Frame and Content Pane
    private JFrame mainFrame;
    private Container cp;

    // Boards
    private JPanel buildBoard, runBoard, currentBoard;

    // GUIs
    private GameGUI buildGUI, runGUI; // currentGUI required? // probably not

    // Components
    private JPanel buildButtonPanel, runButtonPanel, currentButtonPanel;
    private JMenuBar buildMenuBar, runMenuBar, currentMenuBar;

    // Dimensions
    private Dimension boardDim, buttonPanelDim, idealFrameDim;

    boolean status;

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

        // GUIs
        buildGUI = new BuildModeGUI(model, this);
        runGUI = new RunModeGUI(model, this);

        // Components
        buildButtonPanel = buildGUI.createButtons();
        runButtonPanel = runGUI.createButtons();
        buildMenuBar = buildGUI.createMenuBar();
        runMenuBar = runGUI.createMenuBar();

        // Dimensions
        boardDim = new Dimension(ppl*20, ppl*20);
        System.out.println("boardDim width set to "+boardDim.width);
        System.out.println("boardDim height set to "+boardDim.height);
        buttonPanelDim = new Dimension(ppl*5,ppl*20);
        System.out.println("buttonPanelDim width set to "+buttonPanelDim.width);
        System.out.println("buttonPanelDim height set to "+buttonPanelDim.height);

        cp.setLayout(new BorderLayout());
        buildButtonPanel.setPreferredSize(buttonPanelDim);
        runButtonPanel.setPreferredSize(buttonPanelDim);
        buildBoard.setPreferredSize(boardDim);
        runBoard.setPreferredSize(boardDim);
        idealFrameDim = new Dimension(19+ buttonPanelDim.width + boardDim.width - (int)(ppl/2), boardDim.height + (ppl*2)+3);
        System.out.println("idealFrameDim width set to "+idealFrameDim.width);
        System.out.println("idealFrameDim height set to "+idealFrameDim.height);
        //idealFrameDim = new Dimension(500+132, 500+53);

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(idealFrameDim);
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

        // Add new mode's components
        currentButtonPanel = buildButtonPanel;
        currentMenuBar = buildMenuBar;
        currentBoard = buildBoard;
        addBuildKeyListener();
        addCurrentCompsToFrame();
    }

    public void addBuildKeyListener(){

        this.mainFrame.addKeyListener(
                new MagicKeyListener(
                        new RunKeyListener(mainFrame)
                )
        );
    }



    /**
     * Switches the View into Run Mode
     * This method should be called from the Controller upon a "Run Mode" button click
     */
    public void runMode(){
        mainFrame.setTitle("Gizmoball [Run Mode]");

        // Remove previous mode's components
        clearFrame();

        // Add new mode's components
        currentButtonPanel = runButtonPanel;
        currentMenuBar = runMenuBar;
        currentBoard = runBoard;
        addCurrentCompsToFrame();
    }

    /**
     * Clears the frame of all components
     */
    private void clearFrame(){
        try{
            cp.remove(currentButtonPanel);
            cp.remove(currentMenuBar);
            cp.remove(currentBoard);
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
        mainFrame.setPreferredSize(idealFrameDim);
        System.out.println("mainFrame width set to "+mainFrame.getWidth());
        System.out.println("mainFrame height set to "+mainFrame.getHeight());
        mainFrame.repaint();
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    @Override
    public void update() {
        mainFrame.repaint();
    }
}
