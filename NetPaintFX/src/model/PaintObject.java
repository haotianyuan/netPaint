package model;

import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * PaintObject
 * 
 * PaintObject is the superclass of paint objects Oval, Rectangle, Line, and
 * Picture that can be drawn using a Color, two Points, and some Canvas methods.   
 * 
 * @author Haotian Yuan
 *
 */
public abstract class PaintObject {
	private Color color;
	private Point point1;
	private Point point2;
	private String resource;
	public PaintObject(Color a,Point point,Point point3) {
		color=a;
		point1=point;
		point2=point3;
	}
	public PaintObject(Point point,Point point3,String a) {
		point1=point;
		point2=point3;
		resource="file:NetPaintFX/images/"+a;
	}
	public abstract void draw(GraphicsContext gc);
		
}