package gui;

import Model.Ball;
import Model.ModelAPI;
import Model.gizmos.Gizmo;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RunModeBoard extends JPanel {

    private ModelAPI model;


    public RunModeBoard(ModelAPI model){
        this.model = model;
        setSize(new Dimension(800, 800));
    }

    @Override
    public void paintComponent(Graphics graphics){
        List<Gizmo> gizmos = model.getGizmos();
        Ball ball = model.getBall();



    }

}
