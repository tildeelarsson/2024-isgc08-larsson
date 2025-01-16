package controller;

import view.DrawingView;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseClickListener extends MouseAdapter {
	private DrawingView view;
	private DrawingController controller;

	public MouseClickListener(DrawingView view, DrawingController controller) {
		this.view = view;
		this.controller = controller;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		int width = view.getShapeWidth();
		int height = view.getShapeHeight();
		Color lineColor = view.getCurrentLineColor();
		Color fillColor = view.getCurrentFillColor();
		int lineWidth = view.getLineWidth();

		controller.createShape(view.getSelectedShape(), x, y, width, height, lineColor, fillColor, lineWidth);
		view.repaint();
	}
}