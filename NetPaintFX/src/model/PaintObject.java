package model;

import javafx.scene.canvas.GraphicsContext;

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

	public abstract void draw(GraphicsContext gc);
		
}