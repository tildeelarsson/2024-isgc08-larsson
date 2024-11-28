package labb2;

import java.io.*;
import javax.swing.*;

public class TextEditorController {
private TextEditorView view;
private FileManager fM;

public TextEditorController(TextEditorView view, FileManager fM) {
this.view = view;
this.fM = fM;
this.view.addCreateListener(this::handleCreateFile);
this.view.addOpenListener(this::handleOpenFile);
this.view.addSaveListener(this::handleSaveFile);
this.view.addSaveAsListener(this::handleSaveFileAs);
this.view.addExitListener(this::handleExit);
this.view.addWriteListener(this::handleWrite);
this.view.addCopyListener(this::handleCopy);
this.view.addCutListener(this::handleCut);
this.view.addPasteListener(this::handlePaste);
}

private void handleCreateFile() {
String fileName = view.getFileNameUI();
try {
fM.createFile(fileName);
view.setTextArea("");
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
String filePath = view.getFilePath();
if (filePath != null) {
try {
fM.openFile(new File(filePath));
view.setTextArea(fM.getContentFile());
} catch (IOException e) {
view.showError("Fel vid öppning: " + e.getMessage());
}
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
case JOptionPane.YES_OPTION -> handleSaveFileAs();
case JOptionPane.NO_OPTION -> System.exit(0);
case JOptionPane.CANCEL_OPTION, JOptionPane.CLOSED_OPTION -> {
}
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
}

