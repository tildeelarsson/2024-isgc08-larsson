package labb1;
import java.io.*;
import java.io.File;
import java.util.LinkedList;
import javax.swing.*;

public class FileManager {
    private int index;
    private LinkedList<File> v;
    private FileClass currentFile;

    public FileManager() {
        v = new LinkedList<>();
        currentFile = new FileClass();
    }
 
   public FileClass getCurrentFile() {
	   return currentFile;
   }

    public void createFile(String fileName) {
       currentFile.setFileName(fileName);
       currentFile.setContent(""); // Skapar en ny fil med tomt innehåll
    }
    
    public void openFile(String fileName) {
    	 currentFile.setFileName(fileName);
    
    }
  
    public void saveFile(String content) throws IOException {
        currentFile.setContent(content); // Sätter innehållet i filen (currentFile = filen).
        currentFile.writeFile(); 
    }
    
    public void exitFile() {
    	System.exit(0);
        
    }
    
    
 
    public boolean saveFileAs(String content) {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showSaveDialog(null);
        
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            currentFile.setFileName(selectedFile.getAbsolutePath());
            try {
                saveFile(content); // Metod för att spara filen
                JOptionPane.showMessageDialog(null, "Fil sparad som: " + selectedFile.getAbsolutePath(), "Sparad", JOptionPane.INFORMATION_MESSAGE);
                return true; // Filen sparades framgångsrikt
            } catch (IOException e) {
                System.err.println("Fel vid sparande av fil: " + e.getMessage());
                JOptionPane.showMessageDialog(null, "Fel vid sparande av fil: " + e.getMessage(), "Fel", JOptionPane.ERROR_MESSAGE);
                return false; // Fel vid sparande
            }
        } else if (returnValue == JFileChooser.CANCEL_OPTION) {
            // Användaren klickar på Avbryt och kan fortsätta att redigera
            JOptionPane.showMessageDialog(null, "Sparning avbröts. Du kan fortsätta redigera.", "Avbruten", JOptionPane.WARNING_MESSAGE);
            return false; // Ingen fil sparas här
        }
        
        return false; // Om något annat händer
    }
    
    public void writeText(String text) {
    	currentFile.setContent(text);
    }
    
    public void cutText(String text) {
       String currentContent = currentFile.getContent();
       currentFile.setContent(currentContent.replace(text, "")); 
    }
    
    public void copyText(String text) {	
    	
    }
    
    public void pasteText(String text) {
    	String currentContent = currentFile.getContent();
    	currentFile.setContent(currentContent + text);
    }
    
    
    
    
}
  

