package gui;

import Model.ModelAPI;
import observerpattern.Observable;
import observerpattern.Observer;

import javax.swing.*;

public class MainWindow implements Observer{

    private ModelAPI model;
    private JFrame mainFrame;
    private JPanel boardPanel, menuPanel;

    public MainWindow(ModelAPI model){
        this.model = model;
        model.attach(this);
    }

    @Override
    public void update(Observable o) {

    }

}
