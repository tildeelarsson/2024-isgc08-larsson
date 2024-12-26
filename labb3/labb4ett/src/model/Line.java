package model;

import java.awt.*;

public class Line implements ShapeComponent {
    private int x, y, x2, y2;
    private float lineWidth;
    private Color lineColor;

    public Line(int inx, int iny, int inx2, int iny2, float lineWidth, Color lineColor) {
        x = inx;
        y = iny;
        x2 = inx2;
        y2 = iny2;
        this.lineWidth = lineWidth;
        this.lineColor = lineColor;
    }

    public void draw(Graphics g) {
        g.setColor(lineColor);
        ((Graphics2D) g).setStroke(new BasicStroke(lineWidth));
        g.drawLine(x, y, x2, y2);
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
    	
    }

    public void setLineWidth(float width) {
        this.lineWidth = width;
    }
}