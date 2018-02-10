package gui;

import javax.swing.*;
import java.awt.*;

public class BuildModeBoard extends JPanel {

    public BuildModeBoard(){
        setSize(new Dimension(800, 800));
    }

    @Override
    public void paintComponent(Graphics graphics){
//        for(int verticalLines = 0; verticalLines < this.getWidth(); verticalLines += widthGap){
//            graphics.drawLine(verticalLines, 0, verticalLines, this.getHeight());
//        }
//        for(int horizontalLines = 0; horizontalLines < this.getWidth(); horizontalLines += heightGap){
//            graphics.drawLine(0, horizontalLines, this.getWidth(), horizontalLines);
//        }
    }

}
