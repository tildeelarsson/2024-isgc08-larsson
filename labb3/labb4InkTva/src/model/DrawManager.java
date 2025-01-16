package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DrawManager {
	private List<ShapeComponent> shapes;

	public DrawManager() {
		shapes = new ArrayList<>();
	}

	public void addShape(ShapeComponent shape) {
		shapes.add(shape);
	}

	public void clear() {
		shapes.clear();
	}

	public List<ShapeComponent> getShapes() {
		return shapes;
	}

	public void drawAllShapes(Graphics g) {
		for (ShapeComponent shape : shapes) {
			shape.draw(g);
		}
	}
}
