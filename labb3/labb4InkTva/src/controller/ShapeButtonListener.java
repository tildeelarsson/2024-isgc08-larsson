package controller;

import view.DrawingView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShapeButtonListener implements ActionListener {
	private DrawingView view;

	public ShapeButtonListener(DrawingView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String selectedShape = e.getActionCommand();
		view.setSelectedShape(selectedShape);
		System.out.println("Shape selected: " + selectedShape);
	}
}
