package physics;

import java.awt.Polygon;
import java.util.ArrayList;

public class TriangleClass  {

    //Maybe use Line2D ??

    private int X = 0;
    private int Y = 0;
    private int size = 1;
    private ArrayList<LineSegment> lines = new ArrayList<>();
    private ArrayList<Circle> circles = new ArrayList<>();
    private int rotation;
    private LineSegment hypo;
    private LineSegment bottom;
    private LineSegment side;
    private Circle angle;
    private Circle top;
    private Circle right;

    public TriangleClass(int X, int Y, int rotation) {
        this.X = X;
        this.Y = Y;
        this.rotation = rotation;

        if(rotation == 3) {
            hypo = new LineSegment(X, Y, (double) (X + size), (double) (Y + size));
            bottom = new LineSegment(X, Y, X, (double) (Y + size));
            side = new LineSegment(X, (double) (Y + size), (double) (X + size), (double) (Y + size));
            angle = new Circle(X, Y, 0);
            top = new Circle(X, (double) (Y + size), 0);
            right = new Circle((double) (X + size), (double) (Y + size), 0);
        }else if(rotation == 0){
            hypo = new LineSegment((double)(X + size), Y, X, (double) (Y + size));
            bottom = new LineSegment(X, Y,(double)(X + size), Y);
            side = new LineSegment(X, Y, X, (double) (Y + size));
            angle = new Circle((double) (X + size), Y, 0);
            top = new Circle(X, Y, 0);
            right = new Circle(X, (double) (Y + size), 0);
        }else if(rotation == 1) {
            hypo = new LineSegment(X, Y, (double) (X + size), (double) (Y + size));
            bottom = new LineSegment((double) (X + size), Y, (double) (X + size), (double) (Y + size));
            side = new LineSegment(X, Y, (double) (X + size), Y);
            angle = new Circle((double) (X + size), (double) (Y + size), 0);
            top = new Circle((double) (X + size), Y, 0);
            right = new Circle(X, Y, 0);
        }else{
            hypo = new LineSegment((double)(X + size), Y, X, (double) (Y + size));
            bottom = new LineSegment(X, (double) (Y + size), (double) (X + size), (double) (Y + size));
            side = new LineSegment((double) (X + size), Y, (double) (X + size), (double) (Y + size));
            angle = new Circle(X, (double) (Y + size), 0);
            top = new Circle((double) (X + size), (double) (Y + size), 0);
            right = new Circle((double) (X + size), Y, 0);
        }
        lines.add(hypo);
        lines.add(bottom);
        lines.add(side);
        circles.add(angle);
        circles.add(top);
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
        if(rotation == 3) {
            return new Polygon(new int[]{X, (X + size), X}, new int[]{Y, Y + size, Y + size}, 3);
        }else if(rotation == 0){
            return new Polygon(new int[]{X, (X + size), X}, new int[]{Y, Y , Y + size}, 3);
        }else if(rotation == 1){
            return new Polygon(new int[]{X, (X + size), (X + size)}, new int[]{Y, Y , Y + size}, 3);
        }else{
            return new Polygon(new int[]{X, (X + size), (X + size)}, new int[]{Y + size, Y , Y + size}, 3);
        }
    }

    public ArrayList<Circle> getCircles() {
        return this.circles;
    }

    public ArrayList<LineSegment> getLines() {
        return this.lines;
    }
}
