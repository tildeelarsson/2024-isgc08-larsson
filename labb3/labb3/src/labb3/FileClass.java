package labb3;

import java.io.*;
import javax.swing.*;

public class FileClass {
private String fileName; // Privat variabel för att lagra filnamnet.
private String content =""; // // Privat variabel för att lagra filinnehållet.

// Standardkonstruktor som initialiserar fileName och content som tomma strängar.
public FileClass() {
this.fileName = ""; //Sätter fileName till en tom sträng.
this.content = ""; //Sätter content till en tom sträng.
}
// Konstruktor som tar emot ett filnamn och initialiserar content som tomt.
public FileClass(String fileName) {
this.fileName = fileName; //// Sätter fileName till det angivna värdet.
this.content = ""; //// Sätter content till en tom sträng.
}

public void setFileName(String fileName) { //Metod för att sätta filnamnet.
this.fileName = fileName;
}

public void setContent(String content) { //Metod för att sätta kontent.
this.content = content;
}

public String getContent() { //Metod för att "greppa" kontentet.
return content;
}

public String readFile() throws IOException { //Läser filen och returnerar content som fil finns.
if (fileName == null || fileName.isEmpty()) {
throw new IOException("Ingen fil är angiven.");
} StringBuilder content = new StringBuilder();
try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
String line;
while ((line = reader.readLine()) != null) {
content.append(line).append("\n");
}
}
return content.toString(); //Här returneras det. Som en vanlig sträng.
}

public void writeFile(String fileName, String content) throws IOException { //Returnnerar kontent
// Skapar en BufferedWriter som används för att skriva till en fil.
BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
// Skriver det angivna innehållet till filen.
writer.write(content);
// Stänger filen efter att ha skrivit innehållet.
writer.close();
}

public String getFileName() { //Metod för att ta in filnamnet och returnera detta.
return fileName;
}

}
