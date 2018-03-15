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
    private LineSegment leftSide;
    private LineSegment rightSide;
    private LineSegment topSide;
    private LineSegment bottomSide;
    private Circle tLeft;
    private Circle tRight;
    private Circle botLeft;
    private Circle botRight;

    public RectangleClass(int X, int Y, int width, int height) {
        this(X, Y, (float)width, (float)height);
    }

    public RectangleClass(int X, int Y, float width, float height) {
        this.X = X;
        this.Y = Y;
        this.width = width;
        this.height = height;

        leftSide = new LineSegment((double)X, (double)Y, (double)X, (double)(Y + height));
        lines.add(leftSide);
        rightSide = new LineSegment((double)X + width,(double) Y, (double)X + width, (double)(Y + height));
        lines.add(rightSide);
        topSide = new LineSegment((double)X, (double) (Y + height), (double)(X + width), (double)(Y + height));
        lines.add(topSide);
        bottomSide = new LineSegment((double)X, (double)Y, (double)(X + width), (double)Y);
        lines.add(bottomSide);

        tLeft = new Circle((double)X, (double)(Y + height), 0);
        circles.add(tLeft);
        tRight = new Circle((double)(X+ width), (double)(Y + height), 0);
        circles.add(tRight);
        botLeft = new Circle((double)X, (double)Y, 0);
        circles.add(botLeft);
        botRight = new Circle((double)(X + width), Y, 0);
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

    public void rotate(Vect pivot, Angle angle){
        // Rotate LineSegments
        lines.clear();
        Geometry.rotateAround(leftSide, pivot, angle);
        Geometry.rotateAround(rightSide, pivot, angle);
        Geometry.rotateAround(topSide, pivot, angle);
        Geometry.rotateAround(bottomSide, pivot, angle);

        // Rotate Circles
        circles.clear();
        Geometry.rotateAround(tLeft, pivot, angle);
        Geometry.rotateAround(tRight, pivot, angle);
        Geometry.rotateAround(botLeft, pivot, angle);
        Geometry.rotateAround(botRight, pivot, angle);
    }
}