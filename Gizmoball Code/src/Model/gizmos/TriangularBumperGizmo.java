package Model.gizmos;

import physics.Circle;
import physics.LineSegment;

import java.util.List;

public interface TriangularBumperGizmo {
    List<LineSegment> getLines();

    List<Circle> getCircles();
}
