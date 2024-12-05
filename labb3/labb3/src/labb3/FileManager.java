package labb3;

import java.io.*;
import javax.swing.*;


public class FileManager {
private FileClass file;
private String currentFileName;
private boolean isUnsaved = false;
private String clipboard = "";
public FileManager() {
this.file = new FileClass();
this.isUnsaved = false;
}
public String getCurrentFileName() {
return currentFileName;
}
public boolean hasUnsavedChanges() {
return isUnsaved;
}

public void createFile(String fileName) throws IOException {
File file = new File(fileName);
if (file.exists()) {
throw new IOException("Filen finns redan: " + fileName);
}
this.file.setFileName(fileName);
this.file.setContent("");
this.file.writeFile(fileName, "");
currentFileName = fileName;
isUnsaved = true;
}

public void openFile(File file) throws IOException {
if (file == null || !file.exists()) {
throw new FileNotFoundException("Filen hittades inte: " + (file != null ? file.getAbsolutePath() : "null"));
}
StringBuilder contentBuilder = new StringBuilder();
try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
String line;
while ((line = reader.readLine()) != null) {
contentBuilder.append(line).append("\n");
}
}
this.file.setFileName(file.getAbsolutePath());
this.file.setContent(contentBuilder.toString());
currentFileName = this.file.getFileName(); //Får filnamnet från FileClass genom anrop till metoden getFileName.
isUnsaved = false; //Sätter den till sparad när en fil har öppnats. (Kravet var automatisk sparning vid öppning)
}

public void saveFile(String fileName, String content) throws IOException { //Meotd som tar emot filnamn och content från filklassen.
file.setFileName(fileName);
file.setContent(content);
file.writeFile(fileName, content);
isUnsaved = false; //Det ska sparas vid spara.
}

public void saveFileAs(String content, String filePath) throws IOException {
file.setContent(content);
file.setFileName(filePath);
isUnsaved = false; //Det ska sparas vid spara som.
}

public boolean isUnsaved() {
	return !isUnsaved;	
}

public void writeFile() throws IOException {
BufferedWriter writer = new BufferedWriter(new FileWriter(file.getFileName()));
writer.write(file.getContent());
writer.close();
isUnsaved = true;
		}

public void copy(String textToCopy) {
file.setContent(textToCopy);
isUnsaved = true; // inte sparat
}

public void cut(String textToCut) {
if (textToCut != null && !textToCut.isEmpty()) {
clipboard = textToCut;
String currentContent = file.getContent();
String updatedContent = currentContent.replace(textToCut, "");
file.setContent(updatedContent);
isUnsaved = true; // inte sparat
}
}

public void paste() {
String currentContent = file.getContent();
file.setContent(currentContent + clipboard);
isUnsaved = true; // inte sparat
}

public String getContentFile() {
return file.getContent(); //Returnerar getcontent metoden från fileclass (som returnerar content).
}

}
