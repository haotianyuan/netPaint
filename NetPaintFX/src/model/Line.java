package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Light.Point;
import javafx.scene.paint.Color;

public class Line extends PaintObject{
	private Color color;
	private java.awt.Point point1;
	private java.awt.Point point2;
	
	public Line(Color a,java.awt.Point b,java.awt.Point c) {
		color=a;
		point1=b;
		point2=c;
		
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setStroke(color);
		gc.strokeLine(point1.getX(), point1.getY(), point2.getX(), point2.getY());
		gc.fill();
	}
}
