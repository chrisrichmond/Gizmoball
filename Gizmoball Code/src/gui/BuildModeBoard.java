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
        for(int verticalLines = 0; verticalLines < 20; verticalLines += ppl){
            graphics.drawLine(verticalLines*ppl, 0, verticalLines*ppl, 20*ppl);
        }
        for(int horizontalLines = 0; horizontalLines < 20; horizontalLines += ppl){
            graphics.drawLine(0, horizontalLines*ppl, 20*ppl, horizontalLines*ppl);
        }
    }

}
