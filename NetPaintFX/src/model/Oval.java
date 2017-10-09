package model;
/*Author: Haotian Yuan
 * This class is to set the element of an oval, then draw the oval out
 * 
 */
import java.awt.Point;
import java.io.Serializable;

import javafx.scene.canvas.GraphicsContext;
import java.awt.Color;

public class Oval extends PaintObject implements Serializable{
	private Color color;
	private Point c;//the left-upper point 
	private double width;
	private double height;
	public Oval(Color black, Point point, Point point3) {
		super(black,point,point3);
		
		color=black;
		if(point.getX()<point3.getX() && point.getY()<point3.getY()) {//if point is the left-upper point
			c=point;
			width=point3.getX()-point.getX();
			height=point3.getY()-point.getY();
		}
		else if(point.getX()<point3.getX() && point.getY()>point3.getY()) {//if the point is left-down point
			c=new Point();
			c.setLocation(point.getX(), point3.getY());
			width=point3.getX()-point.getX();
			height=point.getY()-point3.getY();
		}
		else if(point.getX()>point3.getX() && point.getY()<point3.getY()) {//if the point is right-upper point
			c=new Point();
			c.setLocation(point3.getX(), point.getY());
			width=point.getX()-point3.getX();
			height=point3.getY()-point.getY();
		}
		else {//if the point is right down point
			c=point3;
			width=point.getX()-point3.getX();
			height=point.getY()-point3.getY();
		}
		
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		ColorTypeConverter temp=new ColorTypeConverter();
		gc.setFill(temp.Awt2Fx(color));
		gc.fillOval(c.getX(), c.getY(), width, height);
	}
}
