package gui.Listeners;

import Model.ModelAPI;
import gui.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

public class RunListener implements ActionListener{

    private Timer timer;
    private ModelAPI model;
    private View view;
    private JFileChooser fileChooser;

    public RunListener(ModelAPI model, View view){
        this.timer = new Timer(50, this);
        this.model = model;
        this.view = view;
        this.fileChooser = new JFileChooser();
    }

    @Override
    public final void actionPerformed(final ActionEvent e) {
        if(e.getSource() == timer){
            model.moveBall();
        }else{
            switch(e.getActionCommand()){
                case("Start"):
                    timer.start();
                    break;
                case("Stop"):
                    timer.stop();
                    break;
                case("Tick"):
                    model.moveBall();
                    break;
                case("Load Model"):
                    // implement loading functionality here
                    int returnVal = fileChooser.showOpenDialog(view.getMainFrame());

                    if(returnVal == JFileChooser.APPROVE_OPTION){
                        // open chosen file
                        File file = fileChooser.getSelectedFile();
                        model.loadFile(file.getAbsolutePath());
                    }else{
                        // close window
                    }

                    break;
                case("Reload"):
                    // implement reloading functionality here
                    break;
                case("Build Mode"):
                    view.buildMode();
                    break;
                case("Quit"):
                    System.out.println("Quitting...");
                    System.exit(0);
                    break;
            }
        }
    }
}
