package model;
/*Author: Haotian Yuan
 * This class is to set the element of a picture, then draw the picture out
 * 
 */
import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Picture extends PaintObject{
	private Image x;//the image
	private Point c;//the left-upper position
	private double width;//width of the picture
	private double height;//height of the picture
	public Picture(Point point, Point point2, String string) {
		super(point,point2,string);
		x=new Image("file:NetPaintFX/images/doge.jpeg",false);
		if(point.getX()<point2.getX()) {//if point is the left-upper point
			c=point;
			width=point2.getX()-point.getX();
			height=point2.getY()-point.getY();
		}
		else if(point.getX()<point2.getX() && point.getY()>point2.getY()) {//if the point is left-down point
			c=new Point();
			c.setLocation(point.getX(), point2.getY());
			width=point2.getX()-point.getX();
			height=point.getY()-point2.getY();
		}
		else if(point.getX()>point2.getX() && point.getY()<point2.getY()) {//if the point is right-upper point
			c=new Point();
			c.setLocation(point2.getX(), point.getY());
			width=point.getX()-point2.getX();
			height=point2.getY()-point.getY();
		}
		else {//if the point is right down point
			c=point2;
			width=point.getX()-point2.getX();
			height=point.getY()-point2.getY();
		}
		
	}

	
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(x, c.getX(), c.getY(), width, height);
		
	}


}
