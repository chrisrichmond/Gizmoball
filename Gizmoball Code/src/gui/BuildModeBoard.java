package gui;

import Model.ModelAPI;

import javax.swing.*;
import java.awt.*;

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
        Graphics2D graphics2D = (Graphics2D)graphics;

        paintGridLines(graphics2D);
    }

    private void paintGridLines(Graphics2D graphics2D){
        graphics2D.setColor(Color.BLACK);
        for(int verticalLines = 0; verticalLines < 20; verticalLines++){
            graphics2D.drawLine(verticalLines*ppl, 0, verticalLines*ppl, 20*ppl);
        }
        for(int horizontalLines = 0; horizontalLines < 20; horizontalLines++){
            graphics2D.drawLine(0, horizontalLines*ppl, 20*ppl, horizontalLines*ppl);
        }
    }

}
