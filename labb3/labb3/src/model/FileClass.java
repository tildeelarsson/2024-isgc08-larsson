package model;

import java.io.*;

public class FileClass {
	private String fileName;
	private String content = "";

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
		File file = new File(this.fileName);
		StringBuilder content = new StringBuilder();

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				content.append(line).append("\n");
			}
		}
		return content.toString();
	}

	public void writeFile(String fileName, String content) throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
			writer.write(content);
		}
	}

	public String getFileName() {
		return fileName;
	}

	public String getAbsolutePath() {
		if (fileName == null || fileName.isEmpty()) {
			return "";
		}
		File file = new File(fileName);
		return file.getAbsolutePath();
	}

}