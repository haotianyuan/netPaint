package model;
/*Author: Haotian Yuan
 * This class is to set the element of a line, then draw the line out
 * 
 */

import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;

import javafx.scene.canvas.GraphicsContext;

public class Line extends PaintObject implements Serializable{
	private javafx.scene.paint.Color color;
	private Point point1;//two points of a line
	private Point point2;
	public Line(Color a,Point point,Point point3) {
		super(a,point,point3);
		point1=point;
		point2=point3;
		
		ColorTypeConverter temp=new ColorTypeConverter();
		color=temp.Awt2Fx(a);
		
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setStroke(color);//fill the line color
		gc.strokeLine(point1.getX(), point1.getY(), point2.getX(), point2.getY());
		gc.fill();
	}
}
