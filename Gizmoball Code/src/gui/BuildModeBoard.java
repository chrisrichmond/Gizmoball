package gui;

import Model.Ball;
import Model.ModelAPI;
import Model.gizmos.Gizmo;
import physics.LineSegment;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BuildModeBoard extends JPanel {

    private ModelAPI model;
    private int ppl; // pixels per line


    public BuildModeBoard(ModelAPI model, int ppl){
        this.model = model;
        this.setBorder(BorderFactory.createLineBorder(Color.blue));
        this.ppl = ppl;
    }

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D)graphics;

        paintGridLines(graphics2D);
        List<Gizmo> gizmos = model.getGizmos();
        Ball ball = model.getBall();

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
                graphics2D.fill(currentGizmo.getCircle().toEllipse2D());
                //graphics2D.fillOval(x,y,width,height);

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
                Polygon scaledTriangle = new Polygon(new int[] {x, (x + ppl), x}, new int[] {y, y + ppl, y + ppl}, 3);
                graphics2D.fillPolygon(scaledTriangle);
            }

        }
    }

    private void paintGridLines(Graphics2D graphics2D){
        graphics2D.setColor(Color.BLACK);
        for(int verticalLines = 0; verticalLines < 21; verticalLines++){
            graphics2D.drawLine(verticalLines*ppl, 0, verticalLines*ppl, 20*ppl);
        }
        for(int horizontalLines = 0; horizontalLines < 21; horizontalLines++){
            graphics2D.drawLine(0, horizontalLines*ppl, 20*ppl, horizontalLines*ppl);
        }
    }

}
