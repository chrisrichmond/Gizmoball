package Model;

import physics.LineSegment;

public class VerticalLine {

    private int xpos;
    private int ypos;
    private int width;
    private LineSegment lineSeg;

    public VerticalLine(int xpos, int ypos, int width){
        this.xpos = xpos;
        this.ypos = ypos;
        this.width = width;
        lineSeg = new LineSegment(xpos, ypos, xpos + width, ypos);
    }

    public int getXpos() {
        return xpos;
    }

    public int getYpos() {
        return ypos;
    }

    public int getWidth() {
        return width;
    }

    public LineSegment getLineSeg() {
        return lineSeg;
    }
}
