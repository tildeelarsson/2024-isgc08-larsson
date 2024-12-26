package view;

import javax.swing.*;
import facade.Facade;
import model.*;

import java.awt.*;

public class DrawSnowmanSwing extends JPanel {

    private JFrame f;
    private ShapeComponent snowman;
    private Facade facade;

    public DrawSnowmanSwing() {
        facade = new Facade();
        snowman = facade.loadDrawingFromFile("snowman.test1");

        if (snowman == null) {
            createSnowmanDrawing();
        }

        f = new JFrame("Snowman");
        f.add(this);
        f.setSize(500, 500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    private void createSnowmanDrawing() {
        ShapeContainer container = new ShapeContainer();
        container.add(new Oval(100, 100, 70, 70, 2.0f, Color.BLACK, Color.WHITE)); 
        container.add(new Line(120, 160, 150, 160, 2.0f, Color.PINK));
        container.add(new Oval(120, 130, 10, 10, 2.0f, Color.BLACK, Color.WHITE)); 
        container.add(new Oval(140, 130, 10, 10, 2.0f, Color.BLACK, Color.WHITE)); 
        container.add(new Oval(130, 140, 10, 10, 2.0f, Color.BLACK, Color.ORANGE)); 

        ShapeContainer hat = new ShapeContainer();
        hat.add(new Line(100, 100, 170, 100, 3.0f, Color.BLACK)); 
        hat.add(new Rect(110, 50, 50, 50, 3.0f, Color.BLACK, Color.BLACK));
        container.add(hat);

        container.add(new Oval(80, 170, 110, 90, 2.0f, Color.BLACK, Color.WHITE)); 
        container.add(new Oval(60, 260, 130, 110, 2.0f, Color.BLACK, Color.WHITE)); 
        snowman = container;
        facade.setDrawing(snowman);
        facade.saveDrawingToFile(snowman, "snowman.test1");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        snowman.draw(g);
    }
}