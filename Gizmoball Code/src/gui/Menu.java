package gui;

import Model.ModelAPI;
import observerpattern.Observer;

import javax.swing.*;

/**
 * Menu is a class for holding the 'Run' menu panel and 'Build' menu panel for their respective modes
 *
 * These panels should be located in the same position on a frame and only one panel may be visible at any given time as the game can only
 * be in one mode (either 'Run' mode or 'Build' mode) at any given time
 */
public class Menu implements Observer{

    private ModelAPI model;
    private boolean isBuildMode;
    private JPanel buildMenuPanel;
    private JPanel runMenuPanel;

    public Menu(ModelAPI model){
        model.attach(this);
        isBuildMode = false;
        buildMenuPanel = new JPanel();
        runMenuPanel = new JPanel();
        
        buildMenuPanel.setVisible(false);
        runMenuPanel.setVisible(true);
    }
    
    /**
     * Only to be accessed internally; this method checks which mode the menu is in and changes the visibilty of the panels accordingly
     * 
     * Observer Pattern should ensure this is called every time the system updates 
     * to check for a potential change from build mode to run mode or vice versa
     **/
    private void refreshVisibility(){
        if(isBuildMode){
            menu.getBuildMenuPanel().setVisible(true);
            menu.getRunMenuPanel().setVisible(false);
        }else{
            menu.getBuildMenuPanel().setVisible(false);
            menu.getRunMenuPanel().setVisible(true);
        }
    }
    

    public void getBuildMenuPanel(){
        return buildMenuPanel;
    }
    
    public void getRunMenuPanel(){
        return runMenuPanel;
    }
    
    @Override
    public void update() {
        isBuildMode = model.isBuildMode();
        refreshVisibility();
    }
}
