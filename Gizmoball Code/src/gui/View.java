package gui;

import Model.ModelAPI;
import observerpattern.Observer;

import javax.swing.*;

public class View implements Observer{

    // Model Backend Declaration
    private ModelAPI model;

    // Main Frame Declaration
    private JFrame mainFrame;

    // Board Declarations
    private Board buildBoard, runBoard, currentBoard;

    // GUI Declarations
    private GameGUI buildGUI, runGUI; // currentGUI required?

    // Component Declarations
    private JPanel buildButtonPanel, runButtonPanel, currentButtonPanel;
    private JMenuBar buildMenuBar, runMenuBar, currentMenuBar;

    public View(ModelAPI model){
        this.model = model;

        mainFrame = new JFrame("Gizmoball");

        buildBoard = new BuildModeBoard();
        runBoard = new RunModeBoard();

        buildGUI = new BuildModeGUI();
        runGUI = new RunModeGUI();
    }

    /**
     * Switches the View into Build Mode
     * This method should be called from the Controller
     */
    public void buildMode(){
        mainFrame.setTitle("Gizmoball [Build Mode]");

        // Remove previous mode's components
        clearFrame();

        // Add new mode's components
        currentButtonPanel = buildButtonPanel;
        currentMenuBar = buildMenuBar;
        currentBoard = buildBoard;
    }

    /**
     * Switches the View into Run Mode
     * This method should be called from the Controller
     */
    public void runMode(){
        mainFrame.setTitle("Gizmoball [Run Mode]");

        // Remove previous mode's components
        clearFrame();

        // Add new mode's components
        currentButtonPanel = runButtonPanel;
        currentMenuBar = runMenuBar;
        currentBoard = runBoard;
    }

    /**
     * Clears the frame of all components
     */
    private void clearFrame(){
        try{
            mainFrame.remove(currentButtonPanel);
            mainFrame.remove(currentMenuBar);
            mainFrame.remove((JPanel)currentBoard);
        }catch(NullPointerException npx){
            System.out.println(npx.getStackTrace() + " NullPointerException: trying to remove JComponent that wasn't there");
        }catch(ClassCastException ccx){
            System.out.println(ccx.getStackTrace() + " ClassCastException: tried to cast currentBoard (which is of interface type Board) as JPanel");
        }
    }

    @Override
    public void update() {

    }
}
