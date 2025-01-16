package facade;

import java.awt.*;
import java.io.*;
import java.util.List;
import model.*;

public class DrawingFacade {
	private DrawManager model;

	public DrawingFacade(DrawManager model) {
		this.model = model;
	}

	public void clearAllShapes() {
		model.clear();
	}

	public void createShape(String selectedShape, int x, int y, int width, int height, Color lineColor, Color fillColor,
			int lineWidth) {
		ShapeComponent shape = null;

		switch (selectedShape) {
		case "Line":
			shape = new Line(x, y, width, height, lineColor, null, lineWidth);
			break;
		case "Oval":
			shape = new Oval(x, y, width, height, lineColor, fillColor, lineWidth);
			break;
		case "Rect":
			shape = new Rect(x, y, width, height, lineColor, fillColor, lineWidth);
			break;
		}

		if (shape != null) {
			model.addShape(shape);
		}
	}

	public List<ShapeComponent> getShapes() {
		return model.getShapes();
	}

	public void saveDrawingToFile(String filename) throws IOException {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
			out.writeObject(model.getShapes());
		} catch (IOException e) {
			throw new IOException("Failed to save drawing", e);
		}
	}

	public void loadDrawingFromFile(String filename) throws IOException, ClassNotFoundException {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
			@SuppressWarnings("unchecked")
			List<ShapeComponent> shapes = (List<ShapeComponent>) in.readObject();
			model.clear();
			for (ShapeComponent shape : shapes) {
				model.addShape(shape);
			}
		} catch (IOException | ClassNotFoundException e) {
			throw new IOException("Failed to load drawing", e);
		}

	}
}
