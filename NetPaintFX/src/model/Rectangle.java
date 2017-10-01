package model;

import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends PaintObject{
	private Color color;
	private Point c;
	private double width;
	private double height;
	public Rectangle(Color pink, Point point, Point point2) {
		color=pink;
		if(point.getX()<point2.getX() && point.getY()<point2.getY()) {
			c=point;
			width=point2.getX()-point.getX();
			height=point2.getY()-point.getY();
		}
		else if(point.getX()<point2.getX() && point.getY()>point2.getY()) {
			c=new Point();
			c.setLocation(point.getX(), point2.getY());
			width=point2.getX()-point.getX();
			height=point.getY()-point2.getY();
		}
		else if(point.getX()>point2.getX() && point.getY()<point2.getY()) {
			c=new Point();
			c.setLocation(point2.getX(), point.getY());
			width=point.getX()-point2.getX();
			height=point2.getY()-point.getY();
		}
		else {
			c=point2;
			width=point.getX()-point2.getX();
			
			height=point.getY()-point2.getY();
			
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(color);
		gc.fillRect(c.getX(), c.getY(), width, height);
	}
}
