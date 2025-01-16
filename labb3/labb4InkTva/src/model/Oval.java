package model;

import java.awt.*;
import java.io.Serializable;

public class Oval extends ShapeComponent implements Serializable {
    private static final long serialVersionUID = 1L;

	public Oval(int x, int y, int width, int height, Color lineColor, Color fillColor, int lineWidth) {
        super(x, y, width, height, lineColor, fillColor, lineWidth);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(lineWidth));

        if (fillColor != null) {
            g2.setColor(fillColor);
            g2.fillOval(x, y, width, height);
        }

        g2.setColor(lineColor);
        g2.drawOval(x, y, width, height);
    }
}
