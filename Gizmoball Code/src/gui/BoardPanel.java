package gui;

import Model.Ball;
import Model.ModelAPI;
import Model.gizmos.*;
import observerpattern.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BoardPanel extends JPanel implements Observer{

    private ModelAPI model;
    private boolean isBuildMode;
    private int widthGap; // width of cells on the 20x20 grid layout
    private int heightGap; // height of cells on the 20x20 grid layout
    private List<Gizmo> gizmos;
    private Ball ball;

    public BoardPanel(ModelAPI model){
        model.attach(this);
        gizmos = new ArrayList<Gizmo>();
    }

    @Override
    public void paintComponent(Graphics graphics){
        /**
         * code for drawing gizmos, ball, etc. all goes in this method,
         * java calls paintComponent() every time JPanel.repaint is called
          */

        if(isBuildMode) {
            // Build Mode - Draw grid outline
            for(int verticalLines = 0; verticalLines < this.getWidth(); verticalLines += widthGap){
                graphics.drawLine(verticalLines, 0, verticalLines, this.getHeight());
            }
            for(int horizontalLines = 0; horizontalLines < this.getWidth(); horizontalLines += heightGap){
                graphics.drawLine(0, horizontalLines, this.getWidth(), horizontalLines);
            }
        }else{
            // Run Mode
        }

        // Draw gizmos
        for(Gizmo currentGizmo: gizmos){
            if(currentGizmo instanceof AbsorberGizmo){
                // Draw Absorber

            }else if(currentGizmo instanceof CircularBumperGizmo){
                // Draw CircularBumper
                graphics.drawOval(convertXToCoords(currentGizmo.getXPos()),convertYToCoords(currentGizmo.getYPos()),widthGap,heightGap);
            }else if(currentGizmo instanceof SquareBumperGizmo){
                // Draw SquareBumper
                graphics.drawRect(convertXToCoords(currentGizmo.getXPos()),convertYToCoords(currentGizmo.getYPos()),widthGap,heightGap);
            }else if(currentGizmo instanceof TriangularBumperGizmo){
                // Draw TriangularBumper

            }
        }

        // Draw ball (build mode only?? can the ball be inserted using build mode?)


    }

    @Override
    public void update() {
        widthGap = this.getWidth() / 20;
        heightGap = this.getHeight() / 20;
        isBuildMode = model.isBuildMode();
        gizmos = model.getGizmos();
        ball = model.getBall();
        //this.repaint(); // going to try calling this from the frame instead of lower down
    }

    /**
     * Converts an x position from the model grid 20x20 to its screen display equivalent coordinate value
     * @param xPos integer x position between 0 and 19 inclusive
     * @return integer x coordinate value on panel
     */
    public int convertXToCoords(int xPos){
        return (xPos*widthGap)-widthGap;
    }

    /**
     * Converts a y position from the model grid 20x20 to its screen display equivalent coordinate value
     * @param yPos integer y position between 0 and 19 inclusive
     * @return integer y coordinate value on panel
     */
    public int convertYToCoords(int yPos){
        return (yPos*heightGap)-heightGap;
    }
}
