package model;

import java.awt.*;
import java.io.Serializable;

public class Line extends ShapeComponent implements Serializable {
	private static final long serialVersionUID = 1L;

	public Line(int x, int y, int width, int height, Color lineColor, Color fillColor, int lineWidth) {
		super(x, y, width, height, lineColor, null, lineWidth); // FillColor används inte för linjer, sätt till null
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(lineWidth));
		g2.setColor(lineColor);
		g2.drawLine(x, y, x + width, y + height);
	}
}
