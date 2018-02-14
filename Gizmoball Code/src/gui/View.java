package gui;

import Model.ModelAPI;
import observerpattern.Observer;

import javax.swing.*;
import java.awt.*;

public class View implements Observer{

    // Model Backend Declaration
    private ModelAPI model;

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

    // Dimension and Constraints Declarations
    private Dimension boardDim, buttonPanelDim;
    private GridBagConstraints gridBagConstraints;

    public View(ModelAPI model){
        // Model Backend Definition
        this.model = model;

        // Main Frame and Content Pane Definition
        mainFrame = new JFrame("Gizmoball");
        cp = mainFrame.getContentPane();

        // Board Definitions
        buildBoard = new BuildModeBoard();
        runBoard = new RunModeBoard(model);

        // GUI Definitions
        buildGUI = new BuildModeGUI();
        runGUI = new RunModeGUI();

        // Component Definitions
        buildButtonPanel = buildGUI.createButtons();
        runButtonPanel = runGUI.createButtons();
        buildMenuBar = buildGUI.createMenuBar();
        runMenuBar = runGUI.createMenuBar();

        // Dimension and Constraints Definitions
        boardDim = new Dimension(400, 400);
        buttonPanelDim = new Dimension(100,400);
        gridBagConstraints = new GridBagConstraints();

        cp.setLayout(new BorderLayout());
        buildButtonPanel.setPreferredSize(buttonPanelDim);
        runButtonPanel.setPreferredSize(buttonPanelDim);
        buildBoard.setPreferredSize(boardDim);
        runBoard.setPreferredSize(boardDim);

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(500,500));
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
//        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridy = 0;
//        gridBagConstraints.weightx = 0;
//        gridBagConstraints.weighty = 0;
//        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
//        mainFrame.getContentPane().add(currentMenuBar, gridBagConstraints);
        cp.add(currentMenuBar, BorderLayout.PAGE_START);

//        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridy = 1;
//        gridBagConstraints.gridwidth = 1;
//        gridBagConstraints.gridheight = 2;
//        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
//        mainFrame.getContentPane().add(currentButtonPanel, gridBagConstraints);
        cp.add(currentButtonPanel, BorderLayout.LINE_START);

//        gridBagConstraints.gridx = 1;
//        gridBagConstraints.gridy = 1;
//        gridBagConstraints.gridwidth = 5;
//        gridBagConstraints.gridheight = 5;
//        gridBagConstraints.anchor = GridBagConstraints.CENTER;
//        mainFrame.getContentPane().add(currentBoard, gridBagConstraints);
        cp.add(currentBoard, BorderLayout.CENTER);
        mainFrame.repaint();
    }

    @Override
    public void update() {
        //mainFrame.repaint();
    }
}
