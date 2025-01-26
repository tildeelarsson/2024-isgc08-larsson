package controler;

import model.FileManager;
import view.IView;
import java.io.*;

public class TextEditorController {
	private IView view;
	private FileManager fM;

	public TextEditorController() {
		fM = new FileManager();
	}

	public void setView(IView view) {
		this.view = view;
	}

	public void handleEvent(String choice) {
		switch (choice) {
		case "Exit" -> handleExit();
		case "Create" -> handleCreate();
		case "Open" -> handleOpen();
		case "Save" -> handleSave();
		case "Save file as" -> handleSaveAs();
		default -> view.showMessage("Okänt val: " + choice, "Error");
		}
	}

	public void handleOperation(String operation) {
		switch (operation) {
		case "Copy" -> handleCopy();
		case "Cut" -> handleCut();
		case "Paste" -> handlePaste();
		default -> view.showMessage("Okänd operation: " + operation, "Error");
		}
	}

	public void handleCreate() {
		String fileName = view.getFileNameUI();
		if (fileName != null && !fileName.trim().isEmpty()) {
			fM.createFile(fileName);
			view.viewCurr("");
			view.showMessage("Ny fil skapad: " + fileName, "Info");
		} else {
			view.showMessage("Fel vid skapandet: " + "", "Error");
		}
	}

	public void handleOpen() {
		String filePath = view.getFileNameUI();
		if (filePath == null || filePath.isEmpty()) {
			view.showMessage("Ingen fil vald." + "", "Error");
			return;
		}

		try {
			String content = fM.openFile(filePath);
			view.showMessage("Filens innehåll:" + "", "Innehåll");
			view.viewCurr(content);
		} catch (IOException e) {
			view.showMessage("Fel vid öppning: " + e.getMessage(), "Error");
		}
	}

	public void handleSave() {
		String content = view.getContent();
		String fileName = fM.getCurrentFileName();

		if (fileName == null || fileName.isEmpty()) {
			view.showMessage("Inget filnamn är angivet. Vänligen skapa eller spara filen först.", "Error");
			return;
		}

		try {
			fM.saveFile(content, fileName);
			view.showMessage("Fil sparad: " + fileName, "Info");
		} catch (IOException e) {
			view.showMessage("Fel vid sparande: " + e.getMessage(), "Error");
		}
	}

	public void handleSaveAs() {
		String content = view.getTextArea();
		String filePath = view.getFilePath();

		if (filePath != null) {
			try {
				fM.saveFileAs(content, filePath);
				view.showMessage("Fil sparad som: " + filePath, "Info");
				System.exit(0);
			} catch (IOException e) {
				view.showMessage("Fel: " + e.getMessage(), "Error");
			}
		} else {
			view.showMessage("Spara som avbröts.", "Error");
		}
	}

	public void handleExit() {
		if (fM.isUnsaved()) {
			view.showConfirmDialog();
		} else {
			System.exit(0);
		}
	}

	public void handleCopy() {
		String selectedText = view.getSelectedText();
		if (selectedText != null) {
			fM.copy(selectedText);
			view.showMessage("Text kopierad.", "Info");
		} else {
			view.showMessage("Ingen text vald att kopiera." + "", "Error");
		}
	}

	public void handleCut() {
		String selectedText = view.getSelectedText();
		if (selectedText != null) {
			fM.cut(selectedText);
			view.setTextArea(view.getTextArea().replace(selectedText, ""));
			view.showMessage("Text klippt.", "Info");
		} else {
			view.showMessage("Ingen text vald att klippa." + "", "Error");
		}
	}

	public void handlePaste() {
		fM.paste();
		view.setTextArea(fM.getContentFile());
	}

}
