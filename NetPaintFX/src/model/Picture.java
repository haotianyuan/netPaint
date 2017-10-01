package model;

import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Picture extends PaintObject{
	private Image x;
	private Point a;
	private Point b;
	private Point c;
	private double width;
	private double height;
	public Picture(Point point, Point point2, String string) {
		// TODO Auto-generated constructor stub
		x=new Image("file:NetPaintFX/images/doge.jpeg",false);
		if(point.getX()<point2.getX()) {
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
		gc.drawImage(x, c.getX(), c.getY(), width, height);
		
	}


}
