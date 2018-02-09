package gui;

import Model.ModelAPI;
import observerpattern.Observer;

import javax.swing.*;
import java.awt.*;

public class View implements Observer{

    // Model Backend Declaration
    private ModelAPI model;

    // Main Frame Declaration
    private JFrame mainFrame;
    private GridBagConstraints gridBagConstraints;

    // Board Declarations
    private Dimension boardDim;
    private JPanel buildBoard, runBoard, currentBoard;

    // GUI Declarations
    private GameGUI buildGUI, runGUI; // currentGUI required? // probably not

    // Component Declarations
    private Dimension buttonPanelDim;
    private JPanel buildButtonPanel, runButtonPanel, currentButtonPanel;
    private JMenuBar buildMenuBar, runMenuBar, currentMenuBar;

    public View(ModelAPI model){
        // Model Backend Definition
        this.model = model;

        // Main Frame Definition
        mainFrame = new JFrame("Gizmoball");
        gridBagConstraints = new GridBagConstraints();

        // Board Definitions
        boardDim = new Dimension(800, 800);
        buildBoard = new BuildModeBoard();
        runBoard = new RunModeBoard();

        // GUI Definitions
        buildGUI = new BuildModeGUI();
        runGUI = new RunModeGUI();

        // Component Definitions
        buttonPanelDim = new Dimension(200,800);
        buildButtonPanel = buildGUI.createButtons();
        runButtonPanel = runGUI.createButtons();
        buildMenuBar = buildGUI.createMenuBar();
        runMenuBar = runGUI.createMenuBar();


        mainFrame.getContentPane().setLayout(new GridLayout());
//        buildButtonPanel.setPreferredSize(buttonPanelDim);
//        runButtonPanel.setPreferredSize(buttonPanelDim);
//        buildBoard.setPreferredSize(boardDim);
//        runBoard.setPreferredSize(boardDim);

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //mainFrame.
        mainFrame.pack();
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
            mainFrame.getContentPane().remove(currentButtonPanel);
            mainFrame.getContentPane().remove(currentMenuBar);
            mainFrame.getContentPane().remove(currentBoard);
        }catch(NullPointerException npx){
            System.out.println(npx.getStackTrace() + " NullPointerException: trying to remove JComponent that wasn't there");
        }catch(ClassCastException ccx){
            System.out.println(ccx.getStackTrace() + " ClassCastException: tried to cast currentBoard (which is of interface type Board) as JPanel");
        }
    }

    private void addCurrentCompsToFrame(){
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.weighty = 0;
        mainFrame.getContentPane().add(currentMenuBar);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 2;
        mainFrame.getContentPane().add(currentButtonPanel);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 5;
        mainFrame.getContentPane().add(currentBoard);
        mainFrame.repaint();
    }

    @Override
    public void update() {

    }
}
