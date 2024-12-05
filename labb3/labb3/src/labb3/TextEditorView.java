package labb3;

import javax.swing.*;
import java.awt.*;

public class TextEditorView implements IView {
	
// Deklaration av alla komponenter som används i den grafisk vyn.
private JFrame frame;
private JTextArea area;
private JMenuBar menuBar;
private JMenu edit;
private JMenuItem write, copy, cut, paste;
private JButton createFile, openFile, saveFile, saveFileAs, exitFile;
private JPanel panel;

public TextEditorView() { //Konstruktur som skapar och konfigurerar kompontenterna för grafiska gränssnittet
	
frame = new JFrame("Text Editor"); //Skapar ett nytt fönster för texteditorn.
area = new JTextArea(20, 50); //Skapar en textarea där användaren kan skriva
frame.add(new JScrollPane(area), BorderLayout.CENTER); //Adderar en scrollpane för textområdet.
menuBar = new JMenuBar(); //Skapar en menybar.
panel = new JPanel(); //Skapar en panel för knappar.
area.setEditable(false); //Sätter textområdet icke redigeringsbart.
edit = new JMenu("Edit"); //Skapar en Edit meny.
write = new JMenuItem("Write"); // Skapar ett menyval för att skriva.
copy = new JMenuItem("Copy"); // Skapar ett menyval för att kopiera.
cut = new JMenuItem("Cut"); // Skapar ett menyval för att klippa.
paste = new JMenuItem("Paste"); // Skapar ett menyval för att kopiera.
edit.add(write); //Adderar skriv menyvalet i editmenyn.
edit.add(copy); //Adderar kopiera menyvalet i editmenyn.
edit.add(cut); //Adderar klippa menyvalet i editmenyn.
edit.add(paste); //Adderar klistra in menyvalet i editmenyn.
menuBar.add(edit); //Adderar editmenyn i menybaren.
panel.setLayout(new FlowLayout()); //Sätter layouten för knapparna.

// Skapar knappar för att skapa, öppna, spara, spara som och avsluta.
createFile = new JButton("Create"); 
openFile = new JButton("Open");
saveFile = new JButton("Save");
saveFileAs = new JButton("Save file as");
exitFile = new JButton("Exit");

//Lägger till knapparna i panelen.
panel.add(createFile);
panel.add(openFile);
panel.add(saveFile);
panel.add(saveFileAs);
panel.add(exitFile);
frame.add(panel, BorderLayout.SOUTH); //Lägger till panelen med knapparna i nedre delen av fönstret (framen).
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Stänger programmet när man trycker på krysset.
frame.setJMenuBar(menuBar); //Sätter menybaren i fönsretet (frame).
frame.setSize(600, 400); //Sätter fönstrets storlek.
frame.setVisible(true); //Gör fönstret synligt.
}


//Lägger till lyssnare till de olika knapparna.
public void addCreateListener(Runnable listener) {  //Det ska ej se ut såhär. Skicka med en till C ist.
createFile.addActionListener(e -> listener.run());
}

public void addOpenListener(Runnable listener) {
openFile.addActionListener(e -> listener.run());
}

public void addSaveListener(Runnable listener) {
saveFile.addActionListener(e -> listener.run());
}

// Metod för att registrera lyssnare för "SaveAs". Implementeras annorlunda än i consoleView för att här är det ej ett siffer val.
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

public void displayMenu() {
    area.setText("Välkommen till TextEditorn! \n"
    		+ "För att skriva --> skapa eller öppna en fil & tryck på edit --> write\n"
    		+ "För att kopiera, klippa eller klistra in markera och tryck på edit\n"
    		+ "Lycka till!\n");
}
  
// Egen write eftersom det inte fungerade i controllern till båda vyerna. Controllern
public void write() {
	 area.setEditable(true);
     this.showMessage("Skrivläge aktiverat", "Info");
}
    
public void showMessage(String message, String title) { // Metod för att visa ett meddelande i ett dialogfönster
JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
}

public int showConfirmDialog(String message, String title) {// Metod för att visa en bekräftelsedialog med "Ja", "Nej" och "Avbryt"-alternativ
return JOptionPane.showConfirmDialog(
null,
message,
title,
JOptionPane.YES_NO_CANCEL_OPTION
);
}

public void showError(String message) { //Metod för att visa ett felmeddelande för användaren.
JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
}

public void viewCurr(String content) { //Metod för att visa det aktuella innehållet i textområdet.
area.setText(content);
}

public void closeView() { //Metod för att stänga ner programmet.
	System.exit(0); 
}

public String getTextArea() { //Metod för att hämta texten från textarea.
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

}