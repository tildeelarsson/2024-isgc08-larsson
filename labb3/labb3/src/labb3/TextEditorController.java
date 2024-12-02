package labb3;

import javax.swing.*;
import java.io.*;

public class TextEditorController  {
    private IView view;
    private FileManager fM;
    
    // Konstruktor som tar emot en vy som implementerar IView
    public TextEditorController(IView view, FileManager fM) {
        this.view = view;
        this.fM = fM;
        
        if (view instanceof TextEditorView) {
            view.addCreateListener(this::handleCreateFile);
            view.addOpenListener(this::handleOpenFile);
            view.addSaveListener(this::handleSaveFile);
            view.addSaveAsListener(this::handleSaveFileAs);
            view.addExitListener(this::handleExit);
            view.addWriteListener(this::handleWrite);
            view.addCopyListener(this::handleCopy);
            view.addCutListener(this::handleCut);
            view.addPasteListener(this::handlePaste);
            view.setWelcomeText("Välkommen!\n"
                    + "För att skriva --> skapa eller öppna en fil & tryck på edit --> write.\n"
                    + "För att kopiera, klippa eller klistra in markera och tryck på edit.\n"
                    + "Lycka till!");
            }
        
        }
 
    public void handleUserChoice() {
        if (view instanceof ConsoleView) {
            ConsoleView consoleView = (ConsoleView) view;
            boolean running = true;

            while (running) {
                consoleView.displayMenu();
                int choice = consoleView.getUserChoice();

                switch (choice) {
                    case 1:
                        String fileName = consoleView.getFileNameUI();
                        try {
                            fM.openFile(new File(fileName));
                            String content = fM.getContentFile();
                            consoleView.viewCurr(content);
                        } catch (IOException e) {
                            consoleView.showError("Fel vid öppning: " + e.getMessage());
                        }
                        break;
                    case 2:
                        fileName = consoleView.getFileNameUI();
                        System.out.println("Ange innehåll att spara:");
                        String fileContent = consoleView.getFileContentUI();
                        try {
                            fM.saveFile(fileName, fileContent);
                            consoleView.showMessage("Filen har sparats.", "");
                        } catch (IOException e) {
                            consoleView.showError("Fel vid sparning: " + e.getMessage());
                        }
                        break;
                    case 3:
                        fileName = consoleView.getFileNameUI();
                        fileContent = consoleView.getFileContentUI();
                        try {
                            fM.createFile(fileName);
                            fM.saveFile(fileName, fileContent);
                            consoleView.showMessage("Ny fil skapad och sparad: " + fileName, "Skapa fil");
                        } catch (IOException e) {
                            consoleView.showError("Fel vid skapandet: " + e.getMessage());
                        }
                        break;
                    case 4:
                        consoleView.showMessage("Programmet avslutas.", "Avsluta");
                        running = false;
                        consoleView.closeView();
                        break;
                    default:
                        consoleView.showError("Ogiltigt val.");
                        break;
                }
            }
        }
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
            view.showMessage("Filen är redan sparad. Programmet avslutas nu.", "Ingen osparad ändring");
            System.exit(0);
        }
    }

    private void handleWrite() {
        view.setTextAreaEditable(true);
        view.showMessage("Skrivläge aktiverat", "Info");
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
    
        
        public void setView(IView view) {
            this.view = view;
        }
       
    }

