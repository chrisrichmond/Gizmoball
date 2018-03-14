package physics;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class RectangleClass{

    private int X = 0;
    private int Y = 0;
    private float width = 1;
    private float height = 1;
    private ArrayList<LineSegment> lines = new ArrayList<>();
    private ArrayList<Circle> circles = new ArrayList<>();

    public RectangleClass(int X, int Y, int width, int height) {
        this(X, Y, (float)width, (float)height);
    }

    public RectangleClass(int X, int Y, float width, float height) {
        this.X = X;
        this.Y = Y;
        this.width = width;
        this.height = height;

        LineSegment leftSide = new LineSegment((double)X, (double)Y, (double)X, (double)(Y + height));
        lines.add(leftSide);
        LineSegment rightSide = new LineSegment((double)X + width,(double) Y, (double)X + width, (double)(Y + height));
        lines.add(rightSide);
        LineSegment topSide = new LineSegment((double)X, (double) (Y + height), (double)(X + width), (double)(Y + height));
        lines.add(topSide);
        LineSegment bottomSide = new LineSegment((double)X, (double)Y, (double)(X + width), (double)Y);
        lines.add(bottomSide);

        Circle tLeft = new Circle((double)X, (double)(Y + height), 0);
        circles.add(tLeft);
        Circle tRight = new Circle((double)(X+ width), (double)(Y + height), 0);
        circles.add(tRight);
        Circle botLeft = new Circle((double)X, (double)Y, 0);
        circles.add(botLeft);
        Circle botRight = new Circle((double)(X + width), Y, 0);
        circles.add(botRight);
    }

    public ArrayList<LineSegment> getLines(){
        return this.lines;
    }

    public ArrayList<Circle> getCircles(){
        return this.circles;
    }

//    public Ellipse2D toEllipse2D() {
//        return new Ellipse2D.Double(centerPoint.x() - radius,
//                centerPoint.y() - radius,
//                2 * radius,
//                2 * radius);
//    }

    public Rectangle2D.Double makeRectangle() {
        return new Rectangle2D.Double((double)X,(double)Y,(double)width,(double)height);
    }
}