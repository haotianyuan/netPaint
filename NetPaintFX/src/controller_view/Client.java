package controller_view;

import java.awt.Point;
import java.util.Vector;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import model.Line;
import model.Oval;
import model.PaintObject;
import model.Picture;
import model.Rectangle;


/**
 * A GUI for Netpaint that has all paint objects drawn upon a Canvas. This file
 * also represents the controller as it controls how paint objects are drawn and
 * sends new paint objects to the server. All Client objects also listen to the
 * server to read the Vector of PaintObjects and repaint every time any client
 * adds a new one.
 * 
 * @author Haotian Yuan
 * 
 */
public class Client extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	private ColorPicker colorPicker;
	private RadioButton line;
	private RadioButton rectangle;
	private RadioButton oval;
	private RadioButton picture;
	private Vector<PaintObject> allPaintObjects;
	private Canvas canvas;
	private GraphicsContext gc;
	private String button;//to follow which radiobutton we are
	private double x1;//the x1,y1 is the starting point 
	private double y1;//the x2,y2 is the ending point
	private double x2;
	private double y2;
	private Color color;//the color
	@Override
	public void start(Stage primaryStage) throws Exception {
		color=color.RED;//default color
		x1 = -1;//init all mouse position int an invalid position
		x2 = -1;
		y1 = -1;
		y2 = -1;
		button = "line";//the init button is line
		BorderPane all = new BorderPane();
		canvas = new Canvas(800, 550);
		gc = canvas.getGraphicsContext2D();
		all.setCenter(canvas);
		HBox hbox = addHBox();//use hbox to set the radiobutton in a line
		all.setBottom(hbox);
		allPaintObjects = createVectorOfPaintObjects();
		drawAllPaintObects(allPaintObjects, canvas);
		MouseHandler mouse = new MouseHandler();// receive the clicked information
		canvas.setOnMousePressed(new MouseHandler());//press the mouse
		canvas.setOnMouseReleased(new MouseHandler());//drag the mouse
		canvas.setOnMouseDragged(new MouseHandler());//release the mouse
		Scene scene = new Scene(all, 800, 650);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private HBox addHBox() {
		ButtonListener handler = new ButtonListener();//to get the information that which button is selected
		HBox hbox = new HBox();//use hbox to set the radiobutton in a line
		line = new RadioButton("Line");//four radiobuttons
		rectangle = new RadioButton("Rectangle");
		oval = new RadioButton("Oval");
		picture = new RadioButton("Picture");
		colorPicker = new ColorPicker();
		colorPicker.setOnAction(new ColorChanger());
		colorPicker.setValue(color.RED);//set the default color of colorpicker
		line.setOnAction(handler);//set all buttons on action to draw the respective shape
		oval.setOnAction(handler);
		rectangle.setOnAction(handler);
		picture.setOnAction(handler);
		line.setSelected(true);//set the default button
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setSpacing(60);
		hbox.getChildren().addAll(line, rectangle, oval, picture, colorPicker);//add all elements into the box
		ToggleGroup group = new ToggleGroup();
		line.setToggleGroup(group);//add all buttons into a group to make sure each time only one can be selected
		rectangle.setToggleGroup(group);
		oval.setToggleGroup(group);
		picture.setToggleGroup(group);
		return hbox;
	}

	private void drawAllPaintObects(Vector<PaintObject> allPaintObjects, Canvas canvas) {
		// TODO Auto-generated method stub
		for (PaintObject po : allPaintObjects)
			po.draw(gc);
	}

	private Vector<PaintObject> createVectorOfPaintObjects() {
		Vector<PaintObject> allPaintObjects = new Vector<>();
		return allPaintObjects;
	}
	
	/*class name: MouseHandler
	 *Purpose: This class is get the position when mouse is pressed, druged or released, then the user can get the two position to draw the shape
	 */
	private class MouseHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {			
			boolean released=false;//to check if we released the mouse
			
			if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {

				x1 = event.getX();
				y1 = event.getY();
			}
			else if(event.getEventType()==MouseEvent.MOUSE_DRAGGED) {
				gc.clearRect(0, 0, 800, 550);
				x2 = event.getX();
				y2 = event.getY();
				if(button.compareTo("line")==0) {
					PaintObject a = new Line(color, new Point((int) x1, (int) y1), new Point((int) x2, (int) y2));
					allPaintObjects.add(a);
				}
				else if(button.compareTo("oval")==0) {
					PaintObject a = new Oval(color, new Point((int) x1, (int) y1), new Point((int) x2, (int) y2));
					allPaintObjects.add(a);
				}
				else if(button.compareTo("rectangle")==0) {
					PaintObject a = new Rectangle(color, new Point((int) x1, (int) y1), new Point((int) x2, (int) y2));
					allPaintObjects.add(a);
				}
				else if(button.compareTo("picture")==0) {
					PaintObject a = new Picture(new Point((int) x1, (int) y1), new Point((int) x2, (int) y2),"doge.jpg");
					allPaintObjects.add(a);
				}
				drawAllPaintObects(allPaintObjects, canvas);				
				allPaintObjects.remove(allPaintObjects.size()-1);//remove the last dragged shape to avoid drawing it again
			}
			else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
				released = true;
				x2 = event.getX();
				y2 = event.getY();
			}
			if(x1!=-1 && x2!=-1 &&y1!=-1 && y2!=-1 && released==true) {
			if (button.compareTo("line") == 0) {
				
					PaintObject a = new Line(color, new Point((int) x1, (int) y1), new Point((int) x2, (int) y2));

					allPaintObjects.add(a);
				
								
			}
			else if(button.compareTo("oval")==0) {
				PaintObject a = new Oval(color, new Point((int) x1, (int) y1), new Point((int) x2, (int) y2));
				allPaintObjects.add(a);
			}
			else if(button.compareTo("rectangle")==0) {
				PaintObject a = new Rectangle(color, new Point((int) x1, (int) y1), new Point((int) x2, (int) y2));
				allPaintObjects.add(a);
			}
			else if(button.compareTo("picture")==0) {
				PaintObject a = new Picture(new Point((int) x1, (int) y1), new Point((int) x2, (int) y2),"doge.jpg");
				allPaintObjects.add(a);
			}
			x1=-1;
			x2=-1;
			y1=-1;
			y2=-1;
			drawAllPaintObects(allPaintObjects, canvas);
			}
		
		}

	}
	/*class name:  ColorChanger
	 *Purpose: This class is the hander of colorpick and it is to get the information of colorpicker
	 */
	private class ColorChanger implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			color = colorPicker.getValue();//get the color of colorpicker
		}
	}
	
	/*class name: ButtonListener
	 *purpose: This class is to check which button is selected, then notify the drawing to draw the correct shape 
	 */
	private class ButtonListener implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			RadioButton buttonClicked = (RadioButton) arg0.getSource();
			if (line == buttonClicked) {//if the user select line
				button = "line";
			}
			else if(oval==buttonClicked) {//if the user select oval
				button="oval";
			}
			else if(rectangle==buttonClicked) {//if the user select rectangle
				button="rectangle";
			}
			else if(picture==buttonClicked) {//if the user select picture
				button="picture";
			}
				
				
		}
	}

}
