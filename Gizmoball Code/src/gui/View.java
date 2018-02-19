package gui;

import Model.ModelAPI;
import gui.Listeners.RunListener;
import observerpattern.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View implements Observer{

    // Model Backend Declaration
    private ModelAPI model;

    // Pixels Per Line
    private int ppl;

    // Main Frame and Content Pane Declaration
    private JFrame mainFrame;
    private Container cp;

    // Board Declarations
    private JPanel buildBoard, runBoard, currentBoard;

    // GUI Declarations
    private GameGUI buildGUI, runGUI; // currentGUI required? // probably not

    // Component Declarations
    private JPanel buildButtonPanel, runButtonPanel, currentButtonPanel;
    private JMenuBar buildMenuBar, runMenuBar, currentMenuBar;

    // Dimension Declarations
    private Dimension boardDim, buttonPanelDim, idealFrameDim;

    // Controller/Listener Declarations

    public View(ModelAPI model){
        // Model Backend Definition
        this.model = model;

        model.attach(this); // observer attachment

        this.ppl = 25;

        // Main Frame and Content Pane Definition
        mainFrame = new JFrame("Gizmoball");
        cp = mainFrame.getContentPane();

        // Board Definitions
        buildBoard = new BuildModeBoard();
        runBoard = new RunModeBoard(model, ppl);

        // GUI Definitions
        buildGUI = new BuildModeGUI();
        runGUI = new RunModeGUI(model, this);

        // Component Definitions
        buildButtonPanel = buildGUI.createButtons();
        runButtonPanel = runGUI.createButtons();
        buildMenuBar = buildGUI.createMenuBar();
        runMenuBar = runGUI.createMenuBar();

        // Dimension Definitions
        boardDim = new Dimension(ppl*(model.getWalls().getWidth()), ppl*(model.getWalls().getHeight()));
        buttonPanelDim = new Dimension(100,ppl*(model.getWalls().getHeight()));

        // Controller/Listener Definitions

        cp.setLayout(new BorderLayout());
        buildButtonPanel.setPreferredSize(buttonPanelDim);
        runButtonPanel.setPreferredSize(buttonPanelDim);
        buildBoard.setPreferredSize(boardDim);
        runBoard.setPreferredSize(boardDim);
        idealFrameDim = new Dimension(buttonPanelDim.width + boardDim.width, buttonPanelDim.height);

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(idealFrameDim);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

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
        addCurrentCompsToFrame();
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
        mainFrame.repaint();
    }

    @Override
    public void update() {
        mainFrame.repaint();
    }
}
