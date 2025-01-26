package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controler.TextEditorController;

public class ConsoleView implements IView {

	private TextEditorController c;
	private String content = "";
	private BufferedReader in;

	public ConsoleView(TextEditorController con) {
		in = new BufferedReader(new InputStreamReader(System.in));
		c = con;
	}

	public void runUI() {
		String input = "100";
		try {
			while (!"0".equals(input)) {
				System.out.println("Välj ett alternativ:");
				System.out.println("0. Avsluta");
				System.out.println("1. Skapa en fil");
				System.out.println("2. Öppna en fil");
				System.out.println("3. Spara en fil");
				System.out.print("Ditt val: ");
				input = in.readLine();

				if ("1".equals(input)) {
					c.handleEvent("Create");
				} else if ("2".equals(input)) {
					c.handleEvent("Open");
				} else if ("3".equals(input)) {
					c.handleEvent("Save");
				} else if ("0".equals(input)) {
					c.handleEvent("Exit");
				} else {

					System.out.println("Ogiltig inmatning");
				}
			}
		} catch (IOException e) {
			System.out.println("Fel i runUI");
		}
	}

	@Override
	public void viewCurr(String content) {
		System.out.println(content);
	}

	public String getFileNameUI() {
		System.out.println("Ange filnamn:");
		try {
			return in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getContent() {
		return content;
	}

	@Override
	public void showConfirmDialog() {
		System.out.println("");
	}

	@Override
	public void showMessage(String message, String title) {
		System.out.println(title + ": " + message);
	}

	public String getTextArea() {
		return null;
	}

	@Override
	public void setTextArea(String text) {
		System.out.println(text);
	}

	@Override
	public String getSelectedText() {
		return "";
	}

	public String getFilePath() {
		return null;
	}

	@Override
	public void setSelectedButton() {
	}

	@Override
	public void setSelectedOperation() {

	}

}
