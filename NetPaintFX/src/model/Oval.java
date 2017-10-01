package model;

import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Oval extends PaintObject{
	private Color color;
	private Point a;
	private Point b;
	private Point c;
	private double width;
	private double height;
	public Oval(Color black, Point point, Point point2) {
		color=black;
		a=point;
		b=point2;
		if(a.getX()<b.getX() && a.getY()<b.getY()) {
			c=a;
			width=b.getX()-a.getX();
			height=b.getY()-a.getY();
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
			c=b;
			width=a.getX()-b.getX();
			height=a.getY()-b.getY();
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(color);
		gc.fillOval(c.getX(), c.getY(), width, height);
	}
}
