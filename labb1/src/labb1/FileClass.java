package labb1;

import java.io.*;

public class FileClass {
    private String fileName;
    private String content;
    private String text;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String name) {
        this.fileName = name;
    }

    public void readFile() throws IOException {
        // Läs filens innehåll och uppdatera content
        if (fileName == null || fileName.isEmpty()) {
            throw new IOException("Inget filnamn angivet.");
        }

        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        setContent(content.toString()); // Sätt innehållet
    }
    

    public void writeFile() throws IOException {
        if (fileName == null || fileName.isEmpty()) {
            throw new IOException("Inget filnamn angivet.");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        }
    }
    
    
}