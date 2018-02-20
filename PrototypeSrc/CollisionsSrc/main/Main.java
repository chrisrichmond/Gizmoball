package main;

import javax.swing.UIManager;

import model.Model;
import physics.Circle;
import physics.SquareClass;
import physics.TriangleClass;
import view.RunGui;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public class Main {

	public static void main(String[] args) {
		try {
			// Use the platform look and feel
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Look and Feel error in Main");
		}



		Model model = new Model();

		model.setBallSpeed(200, 200);

		// Vertical line at (100,100), width 300
//		model.addLineV(new VerticalLine(1, 500, 600));
//		model.addLineV(new VerticalLine(100, 200, 300));
//		model.addLineV(new VerticalLine(100, 300, 300));
//		model.addLineV(new VerticalLine(100, 400, 300));

		SquareClass square1 = new SquareClass(125, 150);
		SquareClass square2 = new SquareClass(175, 150);
		SquareClass square3 = new SquareClass(225, 150);
		SquareClass square4 = new SquareClass(300, 50);

		double width = 50;
		Circle circle1 = new Circle(150, 400, width/2);
		Circle circle2 = new Circle(250, 400, width/2);
		Circle circle3 = new Circle(350, 400, width/2);
		Circle circle4 = new Circle(450, 400, width/2);

		Circle circle5 = new Circle(350, 175, width/2);

		TriangleClass triangle1 = new TriangleClass(0, 100);
		TriangleClass triangle2 = new TriangleClass(0, 200);
		TriangleClass triangle3 = new TriangleClass(0, 300);
		TriangleClass triangle4 = new TriangleClass(0, 400);

		model.addSquare(square1);
		model.addSquare(square2);
		model.addSquare(square3);
		model.addSquare(square4);

		model.addCircle(circle1);
		model.addCircle(circle2);
		model.addCircle(circle3);
		model.addCircle(circle4);
		model.addCircle(circle5);

		model.addTriangle(triangle1);
		model.addTriangle(triangle2);
		model.addTriangle(triangle3);
		model.addTriangle(triangle4);

		RunGui gui = new RunGui(model);
		gui.createAndShowGUI();
	}
}
