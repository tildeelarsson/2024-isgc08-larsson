package controller;

import facade.*;
import model.*;
import java.awt.*;
import java.io.*;

public class DrawingController {
	private DrawingFacade facade;

	public DrawingController(DrawingFacade facade) {
		this.facade = facade;
	}

	public void createShape(String selectedShape, int x, int y, int width, int height, Color lineColor, Color fillColor,
			int lineWidth) {
		facade.createShape(selectedShape, x, y, width, height, lineColor, fillColor, lineWidth); // Skapa form via
																									// facade
	}

	public void drawAllShapes(Graphics g) {
		for (ShapeComponent shape : facade.getShapes()) {
			shape.draw(g);
		}
	}

	public void clearAllShapes() {
		facade.clearAllShapes();
	}

	public void saveDrawing(String filename) throws IOException {
		facade.saveDrawingToFile(filename);
	}

	public void loadDrawing(String filename) throws IOException, ClassNotFoundException {
		facade.loadDrawingFromFile(filename);
	}
}
