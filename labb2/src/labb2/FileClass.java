package labb2;
import java.io.*;
import javax.swing.*;

public class FileClass {
private String fileName;
private String content ="";
public FileClass() {
this.fileName = "";
this.content = "";
}

public FileClass(String fileName) {
this.fileName = fileName;
this.content = "";
}

public void setFileName(String fileName) {
this.fileName = fileName;
}

public void setContent(String content) {
this.content = content;
}

public String getContent() {
return content;
}

public String readFile() throws IOException {
if (fileName == null || fileName.isEmpty()) {
throw new IOException("Ingen fil är angiven.");
} StringBuilder content = new StringBuilder();
try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
String line;
while ((line = reader.readLine()) != null) {
content.append(line).append("\n");
}
}
return content.toString();
}

public void writeFile(String fileName, String content) throws IOException {
BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
writer.write(content);
writer.close();
}

public String getFileName() {
return fileName;
}

}

