package controller;

import view.DrawingView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class FillColorButtonListener implements ActionListener {
	private DrawingView view;

	public FillColorButtonListener(DrawingView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Color newColor = JColorChooser.showDialog(view, "Choose Fill Color", view.getCurrentFillColor());

		if (newColor != null) {

			view.updateFillColor(newColor);
		}
	}
}
