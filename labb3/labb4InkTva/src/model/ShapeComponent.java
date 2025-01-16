package model;


import java.awt.*;
import java.io.Serializable;

public abstract class ShapeComponent implements Serializable {
	private static final long serialVersionUID = 1L;
	protected int x, y;
    protected int width;
    protected int height;
    protected Color lineColor;
    protected Color fillColor;
    protected int lineWidth;

    public ShapeComponent(int x, int y, int width, int height, Color lineColor, Color fillColor, int lineWidth) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.lineColor = lineColor;
        this.fillColor = fillColor != null ? fillColor : Color.WHITE;
        this.lineWidth = lineWidth;
    }

    public abstract void draw(Graphics g);
}
