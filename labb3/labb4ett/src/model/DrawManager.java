package model;

import java.io.*;

public class DrawManager {
    private ShapeComponent drawing;

    public DrawManager() {
        drawing = new ShapeContainer();
    }

    public void saveDrawingToFile(ShapeComponent shape, String filename) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(shape);
        }
    }

    public ShapeComponent loadDrawingFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (ShapeComponent) in.readObject();
        }
    }

    public ShapeComponent getDrawing() {
        return drawing;
    }

    public void setDrawing(ShapeComponent drawing) {
        this.drawing = drawing;
    }
}