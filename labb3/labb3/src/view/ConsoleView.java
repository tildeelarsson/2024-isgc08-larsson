package view;

import java.util.Scanner;

import controler.TextEditorController;


public class ConsoleView implements IView {

	private Scanner scanner = new Scanner(System.in); 
	private TextEditorController c;
	 private String content = "";
	
	 public ConsoleView(TextEditorController con) { 
	        c=con;
	 }
    
	 
    public void getUserChoice() { 
        String input = scanner.nextLine();
        System.out.println("Du angav: " + input);
        try {
            int choice = Integer.parseInt(input);
            handleUserChoice(choice);
        } catch (NumberFormatException e) {
            System.out.println("Ogiltig inmatning, vänligen ange ett nummer mellan 1 och 5.");
        }
    }

    
    public void displayMenu() { 
    	boolean running = true; 
    	 System.out.println ("Hej och välkommen till den konosollbaserade vyn! \n");
    	  while (running) {
    	        System.out.println("Välj ett alternativ:");
    	        System.out.println("1. Öppna fil");
    	        System.out.println("2. Skapa en fil");
    	        System.out.println("3. Skriv i filen");
    	        System.out.println("4. Spara fil");
    	        System.out.println("5. Avsluta");
    	        this.getUserChoice(); 
    	  }
    }
    
    public void handleUserChoice(int choice) {
        switch (choice) {
            case 1:
                relayOpen();
                break;
            case 2:
                relayCreate();
                break;
            case 3:
                relayWrite();
                break;
            case 4:
                relaySave();
                break;
            case 5:
                relayExit();
                break;
            default:
                System.out.println("Ogiltigt val! Vänligen välj ett nummer mellan 1 och 5.");
        }
    }

    @Override
	public void relayCreate() {
		c.handleEvent("Create");
			
	}


	@Override
	public void relayExit() {
		c.handleEvent("Exit");
		
	}


	@Override
	public void relaySave() {
		c.handleEvent("Save");
		
	}


	@Override
	public void relaySaveAs() {
		// TODO Auto-generated method stub
		//Tom
	}


	@Override
	public void relayWrite() {
		c.handleEvent("Write");
		
	}


	@Override
	public void relayOpen() {
		c.handleEvent("Open");
		
	}


	@Override
	public void relayCopy() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void relayCut() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void relayPaste() {
		// TODO Auto-generated method stub
		
	}

   
	 @Override
	public void viewCurr(String content) { 
        System.out.println(content);
    }

    @Override
    public String getFileNameUI() { 
    	Scanner scanner = new Scanner(System.in);
        System.out.println("Ange filnamn:"); 
        return scanner.nextLine(); 
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
        // Läser in text och sparar den i content
        Scanner scanner = new Scanner(System.in);
        System.out.println("Skriv din text");
        content = scanner.nextLine();
        return content;
    }

    @Override
    public void setTextArea(String text) {
        System.out.println(text);
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
    public void setTextAreaEditable(boolean editable) {
        // Tomt: Ingen användning i ConsoleView.
    }
}


	