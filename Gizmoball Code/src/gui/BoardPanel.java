package gui;

import Model.Ball;
import Model.ModelAPI;
import Model.gizmos.Gizmo;
import observerpattern.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BoardPanel extends JPanel implements Observer{

    private ModelAPI model;
    private List<Gizmo> gizmos;
    private Ball ball;

    public BoardPanel(ModelAPI model){
        gizmos = new ArrayList<Gizmo>();
    }

    @Override
    public void paintComponent(Graphics graphics){
        /**
         * code for drawing gizmos, ball, etc. all goes in this method,
         * java calls paintComponent() every time JPanel.repaint is called
          */



    }

    @Override
    public void update() {
        gizmos = model.getGizmos();
        ball = model.getBall();
        repaint();
    }
}
