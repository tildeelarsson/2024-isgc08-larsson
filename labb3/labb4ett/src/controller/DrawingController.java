package controller;

import model.*;
import java.io.*;
public class DrawingController {
    private DrawManager drawManager;

    public DrawingController() {
        drawManager = new DrawManager();
    }

    public void addShape(ShapeComponent shape) {
        drawManager.getDrawing().add(shape);
    }

    public void saveDrawing(String filename) {
        try {
            drawManager.saveDrawingToFile(drawManager.getDrawing(), filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadDrawing(String filename) {
        try {
            ShapeComponent loadedDrawing = drawManager.loadDrawingFromFile(filename);
            drawManager.setDrawing(loadedDrawing);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ShapeComponent getModel() {
        return drawManager.getDrawing();
    }
}