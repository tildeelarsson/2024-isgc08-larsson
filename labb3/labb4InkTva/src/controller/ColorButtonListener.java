package controller;

import view.DrawingView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class ColorButtonListener implements ActionListener {
	private DrawingView view;

	public ColorButtonListener(DrawingView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Color newColor = JColorChooser.showDialog(view, "Choose Line Color", view.getCurrentLineColor());

		if (newColor != null) {
			view.updateLineColor(newColor);
		}
	}
}
