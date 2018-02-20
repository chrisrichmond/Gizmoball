package Model;

import physics.LineSegment;

import java.util.ArrayList;
import java.util.List;

public class Walls {
	private int xpos1;
	private int ypos1;
	private int ypos2;
	private int xpos2;
	private List<LineSegment> lineSegs;
	
	public Walls(int x1,int y1,int x2,int y2){
		this.xpos1 = x1;
		this.ypos1 = y1;
		this.xpos2 = x2;
		this.ypos2 = y2;
		lineSegs = new ArrayList<LineSegment>();
		LineSegment line1 = new LineSegment(xpos1, ypos1, xpos2, ypos1);
		LineSegment line2 = new LineSegment(xpos1, ypos1, xpos1, ypos2);
		LineSegment line3 = new LineSegment(xpos2, ypos1, xpos2, ypos2);
		LineSegment line4 = new LineSegment(xpos1, ypos2, xpos2, ypos2);
		lineSegs.add(line1);
		lineSegs.add(line2);
		lineSegs.add(line3);
		lineSegs.add(line4);
  }

  public List<LineSegment> getLineSegments(){
	  return lineSegs;
  }

  public int getWidth(){
  	return xpos2+1;
  }

  public int getHeight(){
  	return ypos2+1;
  }
	
	
	

	
}
