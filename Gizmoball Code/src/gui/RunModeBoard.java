package gui;

import Model.Ball;
import Model.ModelAPI;
import Model.gizmos.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RunModeBoard extends JPanel {

    private ModelAPI model;
    private int ppl; // pixels per line


    public RunModeBoard(ModelAPI model, int ppl){
        this.model = model;
        this.setBorder(BorderFactory.createLineBorder(Color.blue));
        this.ppl = ppl;
    }

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D)graphics;
        List<Gizmo> gizmos = model.getGizmos();
        Ball ball = model.getBall();
        System.out.println("in paintComponent method of RunModeBoard");

        // Draw Ball
        if(ball != null){
            System.out.println("ball isn't null");
            graphics2D.setColor(Color.BLACK);
            float x = (float)(ppl*((float)ball.getXpos() - (float)ball.getBallRadius()));
            float y = (float)(ppl*((float)ball.getYpos() - (float)ball.getBallRadius()));
            float diameter = (float)ppl*((float)ball.getBallRadius()*2);

            graphics2D.fillOval((int)x,(int)y,(int)diameter,(int)diameter);

            System.out.println("ball drawn at x="+x+" y="+y+" diameter="+ball.getBallRadius()*2);
        }

        // Draw Gizmos

        System.out.println("size of gizmos: "+gizmos.size());
        for (Gizmo currentGizmo: gizmos) {

            int x = ppl*(currentGizmo.getXPos());
            int y = ppl*(currentGizmo.getYPos());
            int width = ppl*(currentGizmo.getWidth());
            int height = ppl*(currentGizmo.getHeight());

            if(currentGizmo.getType().equals("absorber")){
                // Draw Absorber
                graphics2D.fillRoundRect(x, y, width, height, 15, 15);
            }else if(currentGizmo.getType().equals("circle")){
                // Draw CircularBumper


                graphics.drawOval(x, y, width, height);
            }else if(currentGizmo.getType().equals("square")){
                // Draw SquareBumper


                //graphics.drawRect(convertXToCoords(currentGizmo.getXPos()),convertYToCoords(currentGizmo.getYPos()),widthGap,heightGap);
            }else if(currentGizmo.getType().equals("triangle")){
                // Draw TriangularBumper

            }

        }

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
