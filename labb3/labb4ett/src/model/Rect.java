package model;

import java.awt.*;

public class Rect implements ShapeComponent {
	private int x, y, h, w;
	private float lineWidth;
	private Color lineColor, fillColor;

	public Rect(int inx, int iny, int inw, int inh, float lineWidth, Color lineColor, Color fillColor) {
		x = inx;
		y = iny;
		h = inh;
		w = inw;
		this.lineWidth = lineWidth;
		this.lineColor = lineColor;
		this.fillColor = fillColor;
	}

	public void draw(Graphics g) {
		g.setColor(fillColor);
		g.fillRect(x, y, w, h);
		g.setColor(lineColor);
		g.drawRect(x, y, w, h);
	}

	public void add(ShapeComponent s) { 
		
	}
	
	public void remove(ShapeComponent s) {
		
	}
	
	public ShapeComponent getContainer() { 
		return null; 
		}
	

	public void setLineColor(Color color) {
		this.lineColor = color;
	}

	public void setFillColor(Color color) {
		this.fillColor = color;
	}

	public void setLineWidth(float width) {
		this.lineWidth = width;
	}
}