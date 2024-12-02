package labb3;

import java.util.Scanner;

public class ConsoleView implements IView {
    private Scanner scanner = new Scanner(System.in);
 

	@Override
    public void displayMenu() {
        System.out.println("Välj ett alternativ:");
        System.out.println("1. Öppna fil");
        System.out.println("2. Skapa en fil");
        System.out.println("3. Spara fil");
        System.out.println("4. Avsluta");
    }
	
	 @Override
	    public int getUserChoice() {
		 String input = scanner.nextLine();
	        System.out.println("Du angav: " + input);
	        return Integer.parseInt(input);
	    }
	 
	 @Override
	 public String getFileContentUI() {
	        System.out.println("");
	        return scanner.nextLine();
	    }

	 @Override
	public void viewCurr(String content) {
        System.out.println("Filinnehåll:");
        System.out.println(content);
    }

    @Override
    public String getFileNameUI() {
        System.out.println("Ange filnamn:");
        return scanner.nextLine();
    }

    @Override
    public void setWelcomeText(String text) {
        System.out.println(text); 
    }

    @Override
    public int showConfirmDialog(String message, String title) {
        System.out.println(title + ": " + message + " (y/n)");
        String input = scanner.nextLine();
        return input.equalsIgnoreCase("y") ? 0 : 1;  // Returnerar 0 för "ja", 1 för "nej"
    }

    @Override
    public void showMessage(String message, String title) {
        System.out.println(title + ": " + message);
    }

    @Override
    public void showError(String message) {
        System.out.println("Fel: " + message);
    }
    
    @Override
    public void closeView() {
        System.exit(0);
    }


    // Tomma implementationer för de metoder som inte används i ConsoleView
  
    
    @Override
    public void addCreateListener(Runnable listener) {
        // Tomt: Ingen användning i konsolvyn
    }
    
    @Override
    public void addOpenListener(Runnable listener) {
        // Tomt: Ingen användning i konsolvyn
    }
    
    @Override
    public void addSaveListener(Runnable listener) {
        // Tomt: Ingen användning i konsolvyn
    }

    @Override
    public void addSaveAsListener(Runnable listener) {
        // Tomt: Ingen användning i konsolvyn
    }

    @Override
    public void addExitListener(Runnable listener) {
        // Tomt: Ingen användning i konsolvyn
    }

    @Override
    public void addWriteListener(Runnable listener) {
        // Tomt: Ingen användning i konsolvyn
    }

    @Override
    public void addCopyListener(Runnable listener) {
        // Tomt: Ingen användning i konsolvyn
    }

    @Override
    public void addCutListener(Runnable listener) {
        // Tomt: Ingen användning i konsolvyn
    }

    @Override
    public void addPasteListener(Runnable listener) {
        // Tomt: Ingen användning i konsolvyn
    }

    @Override
    public String getTextArea() {
        return ""; // Returnera tomt eftersom detta inte används i ConsoleView
    }

    @Override
    public void setTextArea(String contentFile) {
        // Tomt: Ingen användning i konsolvyn
    }


    @Override
    public String getSelectedText() {
        return ""; // Returnera tomt eftersom detta inte används i ConsoleView
    }

    @Override
    public String getFilePath() {
        return ""; // Returnera tomt eftersom detta inte används i ConsoleView
    }

    @Override
    public String getFilePathSave() {
        return ""; // Returnera tomt eftersom detta inte används i ConsoleView
    }

    @Override
    public void setTextAreaEditable(boolean editable) {
        // Tomt: Ingen användning i konsolvyn
    }
}
