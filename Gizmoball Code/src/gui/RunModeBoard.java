package gui;

import Model.Ball;
import Model.ModelAPI;
import Model.gizmos.*;
import physics.LineSegment;
import physics.TriangleClass;

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

        paintBounds(graphics2D);

        // Draw Ball
        if(ball != null){
            graphics2D.setColor(ball.getColour());
            float x = (float)(ppl*((float)ball.getXpos() - (float)ball.getBallRadius()));
            float y = (float)(ppl*((float)ball.getYpos() - (float)ball.getBallRadius()));
            float diameter = (float)ppl*((float)ball.getBallRadius()*2);

            graphics2D.fillOval((int)x,(int)y,(int)diameter,(int)diameter);

            System.out.println("VIEW: ball drawn at x="+x+" y="+y+" diameter="+ball.getBallRadius()*2);
        }

        // Draw Gizmos

        for (Gizmo currentGizmo: gizmos) {

            int x = ppl*(currentGizmo.getXPos());
            int y = ppl*(currentGizmo.getYPos());
            int width = ppl*(currentGizmo.getWidth());
            int height = ppl*(currentGizmo.getHeight());
            graphics2D.setColor(currentGizmo.getColour());

            if(currentGizmo.getType().equals("absorber")){
                // Draw Absorber
                graphics2D.fillRoundRect(x, y, width, height, 15, 15);
            }else if(currentGizmo.getType().equals("circle")){
                // Draw CircularBumper
                graphics2D.setPaint(Color.blue);
                //graphics2D.fill(currentGizmo.getCircle().toEllipse2D()); //Outline circles
                graphics2D.fillOval(x,y,width,height); //Solid Circles

                graphics.drawOval(x, y, width, height);
            }else if(currentGizmo.getType().equals("square")){
                // Draw SquareBumper
                graphics2D.fillRect(x,y,width,height);

                // Debugging Collision Boundaries
                for (LineSegment currentLine: currentGizmo.getLines()){
                    graphics2D.drawLine((int)(ppl*currentLine.p1().x()),(int)(ppl*currentLine.p1().y()),(int)(ppl*currentLine.p2().x()),(int)(ppl*currentLine.p2().y()));
                }

            }else if(currentGizmo.getType().equals("triangle")){
                // Draw TriangularBumper
                int[] xpoints = ((Polygon)currentGizmo.getShape()).xpoints;
                int[] ypoints = ((Polygon)currentGizmo.getShape()).ypoints;
                int[] scaledXpoints = new int[3];
                int[] scaledYpoints = new int[3];

                for(int i = 0; i < 3; i++) {
                    scaledXpoints[i] = xpoints[i]*ppl;
                    scaledYpoints[i] = ypoints[i]*ppl;
                }

                Polygon scaledTriangle = new Polygon(scaledXpoints, scaledYpoints, 3);
                graphics2D.fillPolygon(scaledTriangle);
            }

        }

    }

    private void paintBounds(Graphics2D graphics2D) {
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawLine(20 * ppl, 0, 20 * ppl, 20 * ppl);
        graphics2D.drawLine(0, 20 * ppl, 20 * ppl, 20 * ppl);
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
