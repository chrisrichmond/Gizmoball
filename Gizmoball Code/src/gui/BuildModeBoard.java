package gui;

import Model.Ball;
import Model.ModelAPI;
import Model.gizmos.Gizmo;
import Model.gizmos.LFlipper;
import Model.gizmos.TriangularBumper;
import gui.Listeners.BuildListener;
import physics.LineSegment;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.util.ConcurrentModificationException;
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
        try {
            for (Gizmo currentGizmo : gizmos) {

                int x = ppl * (currentGizmo.getXPos());
                int y = ppl * (currentGizmo.getYPos());
                int width = ppl * (currentGizmo.getWidth());
                int height = ppl * (currentGizmo.getHeight());
                graphics2D.setColor(currentGizmo.getColour());

                if (currentGizmo.getType().equals("absorber")) {
                    // Draw Absorber
                    graphics2D.fillRoundRect(x, y, width, height, 15, 15);
                } else if (currentGizmo.getType().equals("circle")) {
                    // Draw CircularBumper
                    graphics2D.setPaint(Color.blue);
                    graphics2D.fill(currentGizmo.getCircle().toEllipse2D());
                    graphics2D.fillOval(x, y, width, height);

                    graphics.drawOval(x, y, width, height);
                } else if (currentGizmo.getType().equals("square")) {
                    // Draw SquareBumper
                    graphics2D.fillRect(x, y, width, height);

                    // Debugging Collision Boundaries
                    for (LineSegment currentLine : currentGizmo.getLines()) {
                        graphics2D.drawLine((int) (ppl * currentLine.p1().x()), (int) (ppl * currentLine.p1().y()), (int) (ppl * currentLine.p2().x()), (int) (ppl * currentLine.p2().y()));
                    }

                } else if (currentGizmo.getType().equals("triangle")) {
                    // Draw TriangularBumper
                    int[] xpoints = ((Polygon) currentGizmo.getShape()).xpoints;
                    int[] ypoints = ((Polygon) currentGizmo.getShape()).ypoints;
                    int[] scaledXpoints = new int[3];
                    int[] scaledYpoints = new int[3];

                    for (int i = 0; i < 3; i++) {
                        scaledXpoints[i] = xpoints[i] * ppl;
                        scaledYpoints[i] = ypoints[i] * ppl;
                    }

                    for (LineSegment currentLine : currentGizmo.getLines()) {
                        graphics2D.drawLine((int) (ppl * currentLine.p1().x()), (int) (ppl * currentLine.p1().y()), (int) (ppl * currentLine.p2().x()), (int) (ppl * currentLine.p2().y()));
                    }

                    Polygon scaledTriangle = new Polygon(scaledXpoints, scaledYpoints, 3);
                    graphics2D.fillPolygon(scaledTriangle);
                } else if (currentGizmo.getType().equals("leftflipper")) {
                    // Draw LeftFlipper
                    System.out.println("DRAWING LEFTFLIPPER");
                    graphics2D.fillRoundRect(x, y, (int) ((LFlipper) currentGizmo).getBreadth(), (int) ((LFlipper) currentGizmo).getLength(), 15, 15);
                }

            }
        }catch(ConcurrentModificationException cmx){
            System.out.println("ConcurrentModificationException drawing build board, retrying...");
            this.paintComponent(graphics);
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
