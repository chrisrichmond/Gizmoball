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
            case ("Run Mode"):
                view.runMode();
                break;
            case("Quit"):
                System.out.println("Quitting...");
                System.exit(0);
        }
    }
}
