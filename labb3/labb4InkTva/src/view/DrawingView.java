package view;

import javax.swing.*;
import controller.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;



public class DrawingView extends JPanel {
	public JButton lineButton, ovalButton, rectButton, clearButton;
	private JButton lineColorButton, fillColorButton;
	private JSpinner lineWidthSpinner, widthSpinner, heightSpinner;
	private Color currentLineColor = Color.BLACK;
	private Color currentFillColor = Color.WHITE;
	private String selectedShape = "Line";
	private DrawingController controller;

	public DrawingView(DrawingController controller) {
		this.controller = controller;
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		controlPanel.setPreferredSize(new Dimension(600, 100));
		controlPanel.setBackground(new Color(255, 240, 240));
		
		lineButton = new JButton("Line");
		ovalButton = new JButton("Oval");
		rectButton = new JButton("Rect");
		clearButton = new JButton("Erase everything");
		lineWidthSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		widthSpinner = new JSpinner(new SpinnerNumberModel(50, 1, 200, 1));
		heightSpinner = new JSpinner(new SpinnerNumberModel(50, 1, 200, 1));
		lineColorButton = new JButton("Line Color");
		fillColorButton = new JButton("Fill Color");
		
	
		controlPanel.add(lineButton);
		controlPanel.add(ovalButton);
		controlPanel.add(rectButton);
		controlPanel.add(new JLabel("Line Width:"));
		controlPanel.add(lineWidthSpinner);
		controlPanel.add(new JLabel("Width:"));
		controlPanel.add(widthSpinner);
		controlPanel.add(new JLabel("Height:"));
		controlPanel.add(heightSpinner);
		controlPanel.add(lineColorButton);
		controlPanel.add(fillColorButton);
		controlPanel.add(clearButton);
		add(controlPanel, BorderLayout.NORTH);
		
		 lineButton.addActionListener(new ShapeButtonListener(this));
		 ovalButton.addActionListener(new ShapeButtonListener(this));
		 rectButton.addActionListener(new ShapeButtonListener(this));
		
		lineColorButton.addActionListener(new ColorButtonListener(this)); 
		fillColorButton.addActionListener(new FillColorButtonListener(this));
		
		 clearButton.addActionListener(e -> {
	            controller.clearAllShapes(); 
	            System.out.println("Clearing");
	            repaint();
	        });
		
		  MouseClickListener mouseClickListener = new MouseClickListener(this, controller);
	      addMouseListener(mouseClickListener);
	      addMouseMotionListener(mouseClickListener);
	}
	

	public void addShapeButtonListener(ActionListener listener) {
		lineButton.addActionListener(listener);
		ovalButton.addActionListener(listener);
		rectButton.addActionListener(listener);
	}

	public void addMouseClickListener(MouseAdapter listener) {
		addMouseListener(listener);
		addMouseMotionListener(listener);
	}


	public String getSelectedShape() {
		return selectedShape;
	}

	public void setSelectedShape(String selectedShape) {
		this.selectedShape = selectedShape;
	}

	public int getLineWidth() {
		return (int) lineWidthSpinner.getValue();
	}

	public int getShapeWidth() {
		return (int) widthSpinner.getValue();
	}

	public int getShapeHeight() {
		return (int) heightSpinner.getValue();
	}
	
	public Color getCurrentLineColor() {
		return currentLineColor;
	}

	public Color getCurrentFillColor() {
		return currentFillColor;
	}

	public void updateLineColor(Color color) {
		currentLineColor = color;
	}

	public void updateFillColor(Color color) {
		currentFillColor = color;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		System.out.println("Repainting..");
	    super.paintComponent(g);
	    controller.drawAllShapes(g);
	   
	}
}

