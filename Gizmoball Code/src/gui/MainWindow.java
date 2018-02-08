package gui;

import Model.ModelAPI;
import observerpattern.Observable;
import observerpattern.Observer;

import javax.swing.*;

/**
 * MainWindow is the main class for implementing the View components of MVC for Gizmoball
 *
 * This class provides the frame for the game elements to be held in and holds panels for components as fields
 */
public class MainWindow implements Observer{

    private ModelAPI model; // reference to the backend gizmoball model
    private boolean isBuildMode; // true if user is currently in build mode, false if currently in run mode
    private JFrame mainFrame;
    private JPanel boardPanel;
    private Menu menu;

    public MainWindow(ModelAPI model){
        this.model = model;
        isBuildMode = false;
        model.attach(this);
        boardPanel = new BoardPanel(model);
        menu = new Menu(model);
        
        mainFrame.add(menu.getBuildMenuPanel());
        mainFrame.add(menu.getRunMenuPanel());
        
    }

    @Override
    public void update() {
        isBuildMode = model.isBuildMode();
    }

}
