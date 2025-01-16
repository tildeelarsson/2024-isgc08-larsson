package main;

import controller.*;
import facade.DrawingFacade;
import model.DrawManager;
import view.DrawingView;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.*;

public class GuiRun {
	public static void main(String[] args) {

		DrawManager model = new DrawManager();
		DrawingFacade facade = new DrawingFacade(model);
		DrawingController controller = new DrawingController(facade);
		DrawingView view = new DrawingView(controller);

		JFrame frame = new JFrame("Drawing App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.add(view);
		frame.setVisible(true);

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					System.out.println("Saving drawing to file");
					facade.saveDrawingToFile("drawing.ser");
					System.out.println("Drawing saved.");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			@Override
			public void windowOpened(WindowEvent e) {
				File file = new File("drawing.ser");
				if (file.exists()) {
					try {
						System.out.println("Loading drawing from file");
						facade.loadDrawingFromFile("drawing.ser");
						view.repaint();
						System.out.println("Drawing loaded.");
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				} else {
					System.out.println("No saved drawing found.");
				}
			}
		});
	}
}
