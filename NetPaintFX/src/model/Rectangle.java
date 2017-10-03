package model;
/*Author: Haotian Yuan
 * This class is to init elements of a rectangle, then draw the rectangle
 * 
 */
import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends PaintObject{
	private Color color;//the color
	private Point c;//left-upper point
	private double width;//width of the rectangle
	private double height;//height of the rectangle
	public Rectangle(Color pink, Point point, Point point2) {
		super(pink,point,point2);
		if(point.getX()<point2.getX() && point.getY()<point2.getY()) {//if point is the left-upper point
			c=point;
			width=point2.getX()-point.getX();
			height=point2.getY()-point.getY();
		}
		else if(point.getX()<point2.getX() && point.getY()>point2.getY()) {//if point is left-down point
			c=new Point();
			c.setLocation(point.getX(), point2.getY());
			width=point2.getX()-point.getX();
			height=point.getY()-point2.getY();
		}
		else if(point.getX()>point2.getX() && point.getY()<point2.getY()) {//if point is right-upper point
			c=new Point();
			c.setLocation(point2.getX(), point.getY());
			width=point.getX()-point2.getX();
			height=point2.getY()-point.getY();
		}
		else {//if point is right-buttom point
			c=point2;
			width=point.getX()-point2.getX();
			
			height=point.getY()-point2.getY();
			
		}
		color=pink;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(color);
		gc.fillRect(c.getX(), c.getY(), width, height);
	}
}
