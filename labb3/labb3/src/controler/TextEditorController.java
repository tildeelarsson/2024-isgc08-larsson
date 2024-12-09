package controler;

import javax.swing.*;

import model.FileManager;
import view.IView;

import java.io.*;

public class TextEditorController  {
    private IView view;
    private FileManager fM;
    
    // Konstruktor som tar emot en vy som implementerar IView
    public TextEditorController(IView view, FileManager fM) {
        this.view = view;
        this.fM = fM;
   
       // Här kopplas metoder från controller till lyssnare i vyerna.
        view.addOpenListener(this::handleOpenFile);
        view.addCreateListener(this::handleCreateFile);
        view.addWriteListener(this::handleWrite);
        view.addSaveListener(this::handleSaveFile);
        view.addExitListener(this::handleExit);
        view.addSaveAsListener(this::handleSaveFileAs);
        view.addCopyListener(this::handleCopy);
        view.addCutListener(this::handleCut);
        view.addPasteListener(this::handlePaste);
        }
 
    private void handleCreateFile() {
        String fileName = view.getFileNameUI();
        try {
            fM.createFile(fileName);
            view.setTextArea("");  // Rensa textområdet efter skapande av fil
            view.showMessage("Ny fil skapad: " + fileName, "Info");
        } catch (IOException e) {
            view.showError("Fel vid skapandet: " + e.getMessage());
        }
    }

    private void handleOpenFile() {
        if (fM.hasUnsavedChanges()) {
            int option = view.showConfirmDialog(
                "Det finns osparade ändringar. Vill du spara innan du öppnar en ny fil?",
                "Spara ändringar"
            );
            switch (option) {
                case JOptionPane.YES_OPTION:
                    handleSaveFile();
                    break;
                case JOptionPane.NO_OPTION:
                    break;
                case JOptionPane.CANCEL_OPTION:
                    return;
            }
        }
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                fM.openFile(selectedFile);
                view.setTextArea(fM.getContentFile());
            } catch (IOException e) {
                view.showError("Fel vid öppning: " + e.getMessage());
            }
        } else {
            view.showMessage("Öppning avbröts.", "Info");
        }
    }
   

    private void handleSaveFile() {
        String content = view.getTextArea();
        String fileName = fM.getCurrentFileName();
        if (fileName == null || fileName.isEmpty()) {
            view.showError("Inget filnamn är angivet. Vänligen skapa eller spara filen först.");
            return;
        }
        try {
            fM.saveFile(fileName, content);
            view.showMessage("Fil sparad: " + fileName, "Info");
            view.viewCurr(content);
        } catch (IOException e) {
            view.showError("Fel: " + e.getMessage());
        }
    }

    private void handleSaveFileAs() {
        String filePath = view.getFilePathSave();
        if (filePath != null) {
            try {
                fM.saveFileAs(view.getTextArea(), filePath);
                view.showMessage("Fil sparad som: " + filePath, "Info");
            } catch (IOException e) {
                view.showError("Fel: " + e.getMessage());
            }
            view.closeView();
        } else {
            view.showError("Spara som avbröts.");
        }
    }

    private void handleExit() {
        if (!fM.isUnsaved()) {
            int option = view.showConfirmDialog(
                "Det finns osparade ändringar. Vill du spara innan du avslutar?",
                "Avsluta"
            );
            switch (option) {
                case JOptionPane.YES_OPTION:
                    handleSaveFileAs();
                    break;
                case JOptionPane.NO_OPTION:
                    System.exit(0);
                    break;
                case JOptionPane.CANCEL_OPTION:
                case JOptionPane.CLOSED_OPTION:
                    break;
            }
        } else {
            view.showMessage("Filen är redan sparad. Programmet avslutas nu.", "Ingen osparad ändring"); //Funderar på om man skriver själva texten här eler i vyerna.
            System.exit(0);
        }
    }

    private void handleWrite() {
    	view.write();
    }

    private void handleCopy() {
        String selectedText = view.getSelectedText();
        if (selectedText != null) {
            fM.copy(selectedText);
            view.showMessage("Text kopierad.", "Info");
        } else {
            view.showError("Ingen text vald att kopiera.");
        }
    }

    private void handleCut() {
        String selectedText = view.getSelectedText();
        if (selectedText != null) {
            fM.cut(selectedText);
            view.setTextArea(view.getTextArea().replace(selectedText, ""));
            view.showMessage("Text klippt.", "Info");
        } else {
            view.showError("Ingen text vald att klippa.");
        }
    }

    private void handlePaste() {
        fM.paste();
        view.setTextArea(fM.getContentFile());
    }
        
       
    }

