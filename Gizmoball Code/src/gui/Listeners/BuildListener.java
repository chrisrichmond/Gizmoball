package gui.Listeners;

import Model.ModelAPI;
import gui.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class BuildListener implements ActionListener {

    private ModelAPI model;
    private View view;
    private JFileChooser fileChooser;
    private File latestFile;

    public BuildListener(ModelAPI model, View view){
        this.model = model;
        this.view = view;
        this.fileChooser = new JFileChooser();
        this.latestFile = null;
    }

    @Override
    public final void actionPerformed(final ActionEvent e) {
        switch(e.getActionCommand()) {
            // For Build Mode button menu
            case("Add Gizmo"):
                view.addGizmoBuildMode();
                break;
            case("Add Ball"):

                break;
            case("Rotate"):

                break;
            case("Delete"):

                break;
            case("Move"):

                break;
            case("Clear Board"):

                break;
            case("Connect"):

                break;
            case("Disconnect"):

                break;
            case("Key Connect"):

                break;
            case("Key Disconnect"):

                break;
            case("Load Model"):
                int returnVal = fileChooser.showOpenDialog(view.getMainFrame());

                if(returnVal == JFileChooser.APPROVE_OPTION){
                    // open chosen file
                    latestFile = fileChooser.getSelectedFile();
                    model.loadFile(latestFile.getAbsolutePath());
                }else{
                    // close window
                }
                break;
            case("Run Mode"):
                view.runMode();
                break;
            case("Quit"):
                System.out.println("Quitting...");
                System.exit(0);
                break;

            // Build Mode "Add Gizmo" button menu
            case("Add Square"):

                break;
            case("Add Circle"):

                break;
            case("Add Triangle"):

                break;
            case("Add Absorber"):

                break;
            case("Add Left Flipper"):

                break;
            case("Add Right Flipper"):

                break;
            case("Back"):
                view.buildMode();
                break;
        }
    }
}
