package model;

import java.io.*;

public class FileManager {
	private FileClass file;
	private boolean isUnsaved;
	private String clipboard = "";
	private String content;

	public FileManager() {
		this.file = new FileClass();
		this.isUnsaved = false;
	}

	public String getCurrentFileName() {
		return file.getFileName();
	}

	public boolean hasUnsavedChanges() {
		return isUnsaved;
	}

	public void createFile(String fileName) {
		this.file.setFileName(fileName);
		this.file.setContent("");
		isUnsaved = true;
	}

	public String openFile(String filePath) throws IOException {
		file.setFileName(filePath);
		String content = file.readFile();
		file.setContent(content);
		return content;
	}

	public boolean exitFile(String content, String fileName) throws IOException {
		if (isUnsaved) {
			if (fileName == null || fileName.isEmpty()) {
				throw new IOException("Ingen fil vald för att spara.");
			}
			saveFile(content, fileName);
			return true;
		}
		return false;
	}

	public void saveFile(String content, String fileName) throws IOException {
		if (fileName == null || fileName.isEmpty()) {
			throw new IOException("Filnamn kan inte vara tomt.");
		}
		file.setContent(content);
		file.writeFile(fileName, file.getContent());
		isUnsaved = false;
	}

	public void saveFileAs(String content, String filePath) throws IOException {
		if (filePath == null || filePath.isEmpty()) {
			throw new IOException("Ingen fil valdes.");
		}
		file.setContent(content);
		file.setFileName(filePath);
		file.writeFile(filePath, file.getContent());
		isUnsaved = false;
	}

	public boolean isUnsaved() {
		return isUnsaved;
	}

	public void setContent(String content) {
		this.content = content;
		file.setContent(content);
	}

	public void copy(String textToCopy) {
		file.setContent(textToCopy);
		isUnsaved = true;
	}

	public void cut(String textToCut) {
		if (textToCut != null && !textToCut.isEmpty()) {
			clipboard = textToCut;
			String currentContent = file.getContent();
			String updatedContent = currentContent.replace(textToCut, "");
			file.setContent(updatedContent);
			isUnsaved = true;
		}
	}

	public String paste() {
		String currentContent = file.getContent();
		file.setContent(currentContent + clipboard);
		isUnsaved = true;
		return currentContent;
	}

	public String getContentFile() {
		return file.getContent();
	}

}
