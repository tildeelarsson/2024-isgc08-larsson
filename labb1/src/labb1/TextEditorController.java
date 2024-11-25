package labb1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.io.*;

public class TextEditorController {
    private FileManager model;
    private TextEditorView view;
    private String clipboard;
    private boolean fileOpenedOrCreated = false;
    private boolean isContentChanged = false; // Flagga för att spåra om innehållet har ändrats

    public TextEditorController(FileManager model, TextEditorView view) {
        this.model = model;
        this.view = view;

        // Lyssnare för övre knappar
        view.addButtonListener(0, new CreateButtonListener()); // Create
        view.addButtonListener(1, new OpenButtonListener()); // Open
        view.addButtonListener(2, new SaveButtonListener()); // Save
        view.addButtonListener(3, new SaveFileAsButtonListener()); // SaveFileAs
        view.addButtonListener(4, new ExitButtonListener()); // Exit
        
        
        // Lyssnare för edit-knapparna
        view.addWriteButtonListener(new WriteButtonListener()); //Write
        view.addCutButtonListener(new CutButtonListener()); //Cut
        view.addCopyButtonListener(new CopyButtonListener()); //Copy
        view.addPasteButtonListener(new PasteButtonListener()); //Paste
        view.getTextArea().getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                isContentChanged = true;
            }
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                isContentChanged = true;
            }
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                isContentChanged = true;
            }
        });
    }

   

    public void create() {
        String fileName = JOptionPane.showInputDialog("Enter the filename:");
        if (fileName == null || fileName.isEmpty()) {
            view.showError("Please provide a valid file name."); 
            return;
        }
  
        model.createFile(fileName);
        JOptionPane.showMessageDialog(null, "File '" + fileName + "' has been created successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        view.setText("");
        fileOpenedOrCreated = true;
    }

    public void open() {
        if (checkIfSaveNeeded()) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    model.openFile(selectedFile.getAbsolutePath()); // Öppnar filen
                    model.getCurrentFile().readFile(); // Läser filens nuvarande innehåll
                    view.viewCurr(model.getCurrentFile().getContent()); // Visar nuvarande innehåll
                    fileOpenedOrCreated = true;
                    isContentChanged = false; // Reset flag
                } catch (IOException e) {
                    view.showError("Fel vid läsning av filen: " + e.getMessage());
                }
            } else {
                view.showError("Ingen fil valdes.");
            }
        }
    }

    private boolean checkIfSaveNeeded() {
        if (isContentChanged) {
            int response = JOptionPane.showConfirmDialog(view.getFrame(),
                    "Vill du spara ändringarna innan du öppnar en ny fil?",
                    "Spara ändringar",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                save(); // Spara ändringarna
                isContentChanged = false; // Reset flag
                return true;
            } else if (response == JOptionPane.NO_OPTION) {
                isContentChanged = false; // Reset flag
                return true;
            } else {
                return false; // Användaren avbröt
            }
        } else {
            return true; // Inget behov av att spara
        }
    }

    public void save() {
        String content = view.getText(); // Hämtar textinnehållet
        try {
            model.saveFile(content);
            JOptionPane.showMessageDialog(null, "Filen har sparats framgångsrikt.", "Framgång", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Fel vid sparande av fil: " + e.getMessage(), "Fel", JOptionPane.ERROR_MESSAGE);
        }
    }
    
 
    public void saveFileAs() {
        String content = view.getText(); // Hämtar textinnehållet från vyn
        boolean isSaved = model.saveFileAs(content); // Anropar FileManager för att spara filen
        
        // Stänger programmet endast om filen har sparats framgångsrikt
        if (isSaved) {
            System.exit(0); // 
        }
    }

    public void exit() {
        // Kontrollera om det finns osparade ändringar
        if (isContentChanged) {
            int response = JOptionPane.showConfirmDialog(view.getFrame(),
                    "Vill du spara ändringarna innan du stänger programmet?",
                    "Spara ändringar",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            
            if (response == JOptionPane.YES_OPTION) {
                save(); // Spara ändringarna
            } else if (response == JOptionPane.CANCEL_OPTION) {
                return; // Avbryt stängning
            }
        }
        
        // Stäng programmet
        view.closeView(); // Stäng fönstret
        System.exit(0); // Avsluta programmet
    }
    
    
    private class CreateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            create();
        }
    }
    
    private class OpenButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            open();
        }
    }

    
    private class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            save();
        }
    }

    private class SaveFileAsButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            saveFileAs();  
        }
    }

    private class ExitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            exit();
        }
    }
    
    

  //Här börjar edit-knapparna
    
    public void write() {
    	 if (!fileOpenedOrCreated) {
             view.showError("Du måste öppna eller skapa en fil först.");
            return; // Avbryt om ingen fil är öppen
         }
        view.getTextArea().setEditable(true);
        view.getTextArea().requestFocus(); 
        view.getTextArea().setText("Nu går det bra att skriva...Markera mig och tryck på cut så försvinner jag :)"); 
    }

    public void cut() {
        String selectedText = view.getTextArea().getSelectedText(); // Hämta den markerade texten
        if (selectedText != null && !selectedText.isEmpty()) {
            clipboard = selectedText; // Sparar den klippta texten i urklipp
            String currentText = view.getText(); // Hämtar den nuvarande text
            view.setText(currentText.replace(selectedText, "")); // Tar bort den klippta texten
        } else {
            view.showError("Ingen text vald att klippa.");
        }
    }

    public void copy() {
        String selectedText = view.getTextArea().getSelectedText(); // Hämtar den valda texten
        if (selectedText != null && !selectedText.isEmpty()) {
            clipboard = selectedText; // Sparar den kopierade texten i urklipp
        } else {
            view.showError("Ingen text vald att kopiera.");
        }
    }

    public void paste() {
        if (clipboard != null) {
            String currentText = view.getText(); // Hämtar nuvarande text
            view.setText(currentText + clipboard); // Klistrar in texten
        } else {
            view.showError("Inget att klistra in.");
        }
    }
    
    private class WriteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	write(); // Anropar write-metoden
        }
    }

    private class CutButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cut(); // Anropar cut-metoden
        }
    }

    private class CopyButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            copy(); // Anropar copy-metoden
        }
    }

    private class PasteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            paste(); // Anropar paste-metoden
        }
    }

}