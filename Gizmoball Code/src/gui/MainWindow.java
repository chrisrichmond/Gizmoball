package gui;

import Model.ModelAPI;
import observerpattern.Observer;

import javax.swing.*;
import java.awt.*;

/**
 * MainWindow is the main class for implementing the View components of MVC for Gizmoball
 *
 * This class provides the frame for the game elements to be held in and holds panels for components as fields
 */
public class MainWindow implements Observer{

    private ModelAPI model; // reference to the backend gizmoball model
    private boolean isBuildMode; // true if user is currently in build mode, false if currently in run mode
    private JFrame mainFrame; // main JFrame object
    private JPanel boardPanel; // JPanel object for representing the main gizmoball board play area
    private JPanel currentMenuPanel; // JPanel object reference to one of the 2 menu panels that can be active for build or run mode

    // Build Menu Components
    private JPanel buildMenuPanel;
    private JButton gotoRunMode;

    // Run Menu Components
    private JPanel runMenuPanel;
    private JButton gotoBuildMode;


    public MainWindow(ModelAPI model){
        this.model = model;
        model.attach(this);
        isBuildMode = model.isBuildMode();
        mainFrame = new JFrame("Gizmoball");
        boardPanel = new BoardPanel(model);

        // Build Menu Components
        buildMenuPanel = new JPanel();
        gotoRunMode = new JButton("Go to Run Mode");
        buildMenuPanel.add(gotoRunMode);

        // Run Menu Components
        runMenuPanel = new JPanel();
        gotoBuildMode = new JButton("Go to Build Mode");
        runMenuPanel.add(gotoBuildMode);

        currentMenuPanel = runMenuPanel;

        mainFrame.getContentPane().setLayout(new BoxLayout(mainFrame.getContentPane(), BoxLayout.X_AXIS));
        mainFrame.getContentPane().add(currentMenuPanel);
        mainFrame.getContentPane().add(boardPanel);
        mainFrame.getContentPane().setPreferredSize(new Dimension(1000, 800));

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);

        mainFrame.pack();
        
    }
    
    /**
     * Checks the current game mode and refreshes the frame title accordingly
     **/
    private void refreshFrameTitle(){
        if(isBuildMode){
            mainFrame.setTitle("Gizmoball [Build Mode]");
        }else{
            mainFrame.setTitle("Gizmoball [Run Mode]");
        }
    }

    /**
     * Checks the current game mode and refreshes the menu panel accordingly
     */
    private void refreshCurrentMenu(){
        if(isBuildMode){
            mainFrame.remove(runMenuPanel);
            currentMenuPanel = buildMenuPanel;
        }else{
            mainFrame.remove(buildMenuPanel);
            currentMenuPanel = runMenuPanel;
        }
        mainFrame.add(currentMenuPanel);
    }

    @Override
    public void update() {
        isBuildMode = model.isBuildMode();
        refreshFrameTitle();
        refreshCurrentMenu();
        mainFrame.repaint();
    }

}
