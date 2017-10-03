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
import model.PaintObject;


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
	private Color color;
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		x1 = -1;
		x2 = -1;
		y1 = -1;
		y2 = -1;
		button = "line";//the init button is line
		BorderPane all = new BorderPane();
		canvas = new Canvas(800, 550);
		gc = canvas.getGraphicsContext2D();
		all.setCenter(canvas);
		HBox hbox = addHBox();
		all.setBottom(hbox);
		allPaintObjects = createVectorOfPaintObjects();
		drawAllPaintObects(allPaintObjects, canvas);
		MouseHandler mouse = new MouseHandler();// receive the clicked information
		canvas.setOnMousePressed(new MouseHandler());
		canvas.setOnMouseReleased(new MouseHandler());
		canvas.setOnMouseDragged(new MouseHandler());
		Scene scene = new Scene(all, 800, 650);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private HBox addHBox() {
		ButtonListener handler = new ButtonListener();
		HBox hbox = new HBox();
		line = new RadioButton("Line");
		rectangle = new RadioButton("Rectangle");
		oval = new RadioButton("Oval");
		picture = new RadioButton("Picture");
		colorPicker = new ColorPicker();
		colorPicker.setOnAction(new ColorChanger());

		line.setOnAction(handler);
		line.setSelected(true);
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setSpacing(60);
		hbox.getChildren().addAll(line, rectangle, oval, picture, colorPicker);
		ToggleGroup group = new ToggleGroup();

		line.setToggleGroup(group);

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
				PaintObject a = new Line(Color.RED, new Point((int) x1, (int) y1), new Point((int) x2, (int) y2));
				allPaintObjects.add(a);
				drawAllPaintObects(allPaintObjects, canvas);
				
				allPaintObjects.remove(allPaintObjects.size()-1);
			}
			else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
				released = true;
				x2 = event.getX();
				y2 = event.getY();
			}
			if (button.compareTo("line") == 0) {
				if(x1!=-1 && x2!=-1 &&y1!=-1 && y2!=-1 && released==true) {
					PaintObject a = new Line(Color.RED, new Point((int) x1, (int) y1), new Point((int) x2, (int) y2));

					allPaintObjects.add(a);
					x1=-1;
					x2=-1;
					y1=-1;
					y2=-1;
					drawAllPaintObects(allPaintObjects, canvas);
				}
			}
		
		}

	}

	private class ColorChanger implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			color = colorPicker.getValue();

		}
	}

	private class ButtonListener implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			RadioButton buttonClicked = (RadioButton) arg0.getSource();
			if (line == buttonClicked) {
				button = "line";
			}
		}
	}

}
