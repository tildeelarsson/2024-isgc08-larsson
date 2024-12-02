package labb3;

import javax.swing.*;
import java.awt.*;

public class TextEditorView implements TextEditorViewInterface {
private JFrame frame;
private JTextArea area;
private JMenuBar menuBar;
private JMenu edit;
private JMenuItem write, copy, cut, paste;
private JButton createFile, openFile, saveFile, saveFileAs, exitFile;
private JPanel panel;
public TextEditorView() {
frame = new JFrame("Text Editor");
area = new JTextArea(20, 50);
frame.add(new JScrollPane(area), BorderLayout.CENTER);
menuBar = new JMenuBar();
panel = new JPanel();
area.setEditable(false);
setWelcomeText();
edit = new JMenu("Edit");
write = new JMenuItem("Write");
copy = new JMenuItem("Copy");
cut = new JMenuItem("Cut");
paste = new JMenuItem("Paste");
edit.add(write);
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
frame.add(panel, BorderLayout.SOUTH);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setJMenuBar(menuBar);
frame.setSize(600, 400);
frame.getContentPane().setBackground(new Color(255, 182, 193)); // RGB för ljus rosa
frame.setVisible(true);
}
public void addCreateListener(Runnable listener) {
createFile.addActionListener(e -> listener.run());
}
public void addOpenListener(Runnable listener) {
openFile.addActionListener(e -> listener.run());
}
public void addSaveListener(Runnable listener) {
saveFile.addActionListener(e -> listener.run());
}
public void addSaveAsListener(Runnable listener) {
saveFileAs.addActionListener(e -> listener.run());
}
public void addExitListener(Runnable listener) {
exitFile.addActionListener(e -> listener.run());
}
public void addWriteListener(Runnable listener) {
write.addActionListener(e -> listener.run());
}
public void addCopyListener(Runnable listener) {
copy.addActionListener(e -> listener.run());
}
public void addCutListener(Runnable listener) {
cut.addActionListener(e -> listener.run());
}
public void addPasteListener(Runnable listener) {
paste.addActionListener(e -> listener.run());
}
public void setWelcomeText() {
area.setText("Välkommen till TextEditor! \n"
		+ "För att skriva --> skapa eller öppna en fil & tryck på edit --> write.\n"
		+ "För att kopiera, klippa eller klistra in markera och tryck på edit.\n"
	    + "Lycka till!");
		

}
public void showMessage(String message, String title) {
JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
}
public int showConfirmDialog(String message, String title) {
return JOptionPane.showConfirmDialog(
null,
message,
title,
JOptionPane.YES_NO_CANCEL_OPTION
);
}
public void showError(String message) {
JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
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
public void setTextArea(String text) {
area.setText(text);
}
public String getSelectedText() {
return area.getSelectedText();
}
public String getFilePath() {
JFileChooser fileChooser = new JFileChooser();
int result = fileChooser.showOpenDialog(frame);
if (result == JFileChooser.APPROVE_OPTION) {
return fileChooser.getSelectedFile().getAbsolutePath();
} else {
return null;
}
}
public String getFilePathSave() {
JFileChooser fileChooser = new JFileChooser();
int result = fileChooser.showSaveDialog(frame);
if (result == JFileChooser.APPROVE_OPTION) {
return fileChooser.getSelectedFile().getAbsolutePath();
} else {
return null;
}
}
public void setTextAreaEditable(boolean editable) {
area.setEditable(editable);
}
public String getFileNameUI() {
String fileName = JOptionPane.showInputDialog(frame, "Ange filnamn:", "Filnamn", JOptionPane.PLAIN_MESSAGE);
if (fileName == null || fileName.trim().isEmpty()) {
showError("Filnamn kan inte vara tomt!");
return null;
}
return fileName;
}

@Override
public void displayMenu() {
    // Grafiska menyer hanteras via GUI-komponenter, så denna kan lämnas tom.
}

@Override
public int getUserChoice() {
    return -1; // Returnera dummy-värde, hanteras inte i GUI.
}
}