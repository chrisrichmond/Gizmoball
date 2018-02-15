package gui.Listeners;

import Model.ModelAPI;
import gui.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class RunListener implements ActionListener{

    private Timer timer;
    private ModelAPI model;
    private View view;

    public RunListener(ModelAPI model, View view){
        this.timer = new Timer(50, this);
        this.model = model;
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
                case("Load"):
                    // implement loading functionality here
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
