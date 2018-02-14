package gui;

import Model.Ball;
import Model.ModelAPI;
import Model.gizmos.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RunModeBoard extends JPanel {

    private ModelAPI model;


    public RunModeBoard(ModelAPI model){
        this.model = model;
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    @Override
    public void paintComponent(Graphics graphics){
        Graphics2D graphics2D = (Graphics2D)graphics;
        List<Gizmo> gizmos = model.getGizmos();
        Ball ball = model.getBall();

        // Draw Ball
        if(ball != null){
            graphics2D.setColor(Color.BLACK);
            int x = (int) (ball.getXCoords() - ball.getBallRadius());
            int y = (int) (ball.getXCoords() - ball.getBallRadius());

            graphics2D.fillOval(x,y,(int)(ball.getBallRadius()*2),(int)(ball.getBallRadius()*2));
        }

        // Draw Gizmos
//        for (Gizmo currentGizmo: gizmos) {
//            if(currentGizmo instanceof AbsorberGizmo){
//                // Draw Absorber
//
//            }else if(currentGizmo instanceof CircularBumperGizmo){
//                // Draw CircularBumper
//
//
//                //graphics.drawOval(convertXToCoords(currentGizmo.getXPos()),convertYToCoords(currentGizmo.getYPos()),widthGap,heightGap);
//            }else if(currentGizmo instanceof SquareBumperGizmo){
//                // Draw SquareBumper
//
//
//                //graphics.drawRect(convertXToCoords(currentGizmo.getXPos()),convertYToCoords(currentGizmo.getYPos()),widthGap,heightGap);
//            }else if(currentGizmo instanceof TriangularBumperGizmo){
//                // Draw TriangularBumper
//
//            }
//
//        }

    }

    /**
     * Converts an x position from the model grid 20x20 to its screen display equivalent coordinate value
     * @param xPos integer x position between 0 and 19 inclusive
     * @return integer x coordinate value on panel
     */
//    public int convertXToCoords(int xPos){
//        return (xPos*widthGap)-widthGap;
//    }

    /**
     * Converts a y position from the model grid 20x20 to its screen display equivalent coordinate value
     * @param yPos integer y position between 0 and 19 inclusive
     * @return integer y coordinate value on panel
     */
//    public int convertYToCoords(int yPos){
//        return (yPos*heightGap)-heightGap;
//    }

}
