package view;

import java.awt.BorderLayout;
import controler.*;
import java.awt.FlowLayout;
import javax.swing.*;

public class TextEditorView implements IView {

	private JFrame frame;
	private JTextArea area;
	private JMenuBar menuBar;
	private JMenu edit;
	private JMenuItem copy, cut, paste;
	private JButton createFile, openFile, saveFile, saveFileAs, exitFile;
	private JPanel panel;
	private TextEditorController c;

	public TextEditorView(TextEditorController con) {
		c = con;

		frame = new JFrame("Text Editor");
		area = new JTextArea(20, 50);
		frame.add(new JScrollPane(area), BorderLayout.CENTER);

		menuBar = new JMenuBar();
		panel = new JPanel();

		area.setEditable(true);

		edit = new JMenu("Edit");
		copy = new JMenuItem("Copy");
		cut = new JMenuItem("Cut");
		paste = new JMenuItem("Paste");

		edit.add(copy);
		edit.add(cut);
		edit.add(paste);

		menuBar.add(edit);

		panel.setLayout(new FlowLayout());

		createFile = new JButton("Create");
		openFile = new JButton("Open");
		saveFile = new JButton("Save");
		saveFileAs = new JButton("Save file as");
		exitFile = new JButton("Exit");

		panel.add(createFile);
		panel.add(openFile);
		panel.add(saveFile);
		panel.add(saveFileAs);
		panel.add(exitFile);

		createFile.addActionListener(new ButtonListener(this, "Create"));
		openFile.addActionListener(new ButtonListener(this, "Open"));
		saveFile.addActionListener(new ButtonListener(this, "Save"));
		saveFileAs.addActionListener(new ButtonListener(this, "Save file as"));
		exitFile.addActionListener(new ButtonListener(this, "Exit"));

		copy.addActionListener(new OperationListener(this, "Copy"));
		cut.addActionListener(new OperationListener(this, "Cut"));
		paste.addActionListener(new OperationListener(this, "Paste"));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(menuBar);
		frame.setSize(600, 400);
		frame.add(panel, BorderLayout.SOUTH);
		frame.setVisible(true);
	}

	public void setSelectedButton(String choice) {
		c.handleEvent(choice);
	}

	public void setSelectedOperation(String operation) {
		c.handleOperation(operation);
	}

	public void runUI() {
		area.setText("Välkommen till TextEditorn!");
	}

	public void showMessage(String message, String title) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}

	public void showConfirmDialog() {
		int option = JOptionPane.showConfirmDialog(panel,
				"Filen har osparade ändringar vill du spara innan du stänger ner?", "Notis", JOptionPane.YES_NO_OPTION);

		if (option == JOptionPane.YES_OPTION) {
			c.handleSave();
			System.exit(0);
		} else if (option == JOptionPane.NO_OPTION) {
			System.exit(0);
		}
	}

	public void viewCurr(String content) {
		area.setText(content);
	}

	public void closeView() {
		System.exit(0);
	}

	public String getTextArea() {
		return area.getText();
	}

	public String getContent() {
		return area.getText();
	}

	public void setTextArea(String text) {
		area.setText(text);
	}

	public String getSelectedText() {
		return area.getSelectedText();
	}

	public String getFilePath() {
		JFileChooser fileChooser = new JFileChooser();
		int result = fileChooser.showSaveDialog(area);
		if (result == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile().getAbsolutePath();
		} else {
			return null;
		}
	}

	public String getFileNameUI() {
		String fileName = JOptionPane.showInputDialog(area, "Ange filnamn:", "Filnamn", JOptionPane.PLAIN_MESSAGE);
		if (fileName == null || fileName.trim().isEmpty()) {
			showMessage("Filnamn kan inte vara tomt!" + "", "Error");
			return null;
		}
		return fileName;
	}

	@Override
	public void setSelectedButton() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSelectedOperation() {
		// TODO Auto-generated method stub

	}

}
