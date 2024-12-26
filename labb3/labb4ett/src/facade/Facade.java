package facade;

import model.*;
import java.awt.*;
import java.io.*;

public class Facade {
    private DrawManager drawManager;

    public Facade() {
        drawManager = new DrawManager();
    }

    public Line createLine(int x, int y, int x2, int y2, float lineThickness, Color lineColor) {
        return new Line(x, y, x2, y2, lineThickness, lineColor);
    }

    public Oval createOval(int x, int y, int w, int h, float lineThickness, Color lineColor, Color fillColor) {
        return new Oval(x, y, w, h, lineThickness, lineColor, fillColor);
    }

    public Rect createRect(int x, int y, int w, int h, float lineThickness, Color lineColor, Color fillColor) {
        return new Rect(x, y, w, h, lineThickness, lineColor, fillColor);
    }

    public ShapeContainer createShapeContainer() {
        return new ShapeContainer();
    }

    public void drawShape(Graphics g, ShapeComponent shape) {
        shape.draw(g);
    }

    public void saveDrawingToFile(ShapeComponent shape, String filename) {
        try {
            drawManager.saveDrawingToFile(shape, filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ShapeComponent loadDrawingFromFile(String filename) {
        try {
            return drawManager.loadDrawingFromFile(filename);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setDrawing(ShapeComponent drawing) {
        drawManager.setDrawing(drawing);
    }
}