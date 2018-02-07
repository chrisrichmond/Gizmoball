package physics;

import java.awt.Polygon;
import java.util.ArrayList;

public class TriangleClass  {

    //Maybe use Line2D ??

    private int X = 0;
    private int Y = 0;
    private int size = 100;
    private ArrayList<LineSegment> lines = new ArrayList<>();
    private ArrayList<Circle> circles = new ArrayList<>();

    public TriangleClass(int X, int Y) {
        this.X = X;
        this.Y = Y;
        LineSegment hypo = new LineSegment(X, Y, (double) (X + size), (double) (Y + size));
        lines.add(hypo);
        LineSegment bottom = new LineSegment(X, Y, X, (double) (Y + size));
        lines.add(bottom);
        LineSegment side = new LineSegment(X, (double) (Y + size), (double) (X + size), (double) (Y + size));
        lines.add(side);

        Circle angle = new Circle(X, Y, 0);
        circles.add(angle);
        Circle top = new Circle(X, (double) (Y + size), 0);
        circles.add(top);
        Circle right = new Circle((double) (X + size), (double) (Y + size), 0);
        circles.add(right);
    }

//
//    public Ellipse2D toEllipse2D() {
//        return new Ellipse2D.Double(centerPoint.x() - radius,
//                centerPoint.y() - radius,
//                2 * radius,
//                2 * radius);
//    }

    public Polygon makeTriangle() {
       return new Polygon(new int[] {X, (X + size), X}, new int[] {Y, Y + size, Y + size}, 3);
    }

    public ArrayList<Circle> getCircles() {
        return this.circles;
    }

    public ArrayList<LineSegment> getLines() {
        return this.lines;
    }
}
