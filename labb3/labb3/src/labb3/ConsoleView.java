package labb3;

import java.util.Scanner;

//Denna klass implementerar ett konsolbaserat användargränssnitt och följer IView-gränssnittet.
public class ConsoleView implements IView {
	// Scanner för att läsa användarinmatning.
	private Scanner scanner = new Scanner(System.in); 
	
    private Runnable openListener; //Runnable är en lyssnare som kapslar in koden så att den kan köras senare i run();
	private Runnable createListener;
    private Runnable writeListener;
    private Runnable saveListener;
    private Runnable exitListener;
    
    
    public void getUserChoice() { //Använder Scanner för att läsa inmatning från användare.
        String input = scanner.nextLine();
        System.out.println("Du angav: " + input);
        try {
            int choice = Integer.parseInt(input); //En int (siffra) som ska in. Konvertera till int.
            handleUserChoice(choice);
        } catch (NumberFormatException e) {
            System.out.println("Ogiltig inmatning, vänligen ange ett nummer mellan 1 och 5.");
        }
    }

    
    public void displayMenu() { //Här visas huvudmenyn i konsollen om man har valt det gränsnittet. Anropas av main.
    	boolean running = true; 
    	 System.out.println ("Hej och välkommen till den konosollbaserade vyn! \n");
    	  while (running) {
    	        System.out.println("Välj ett alternativ:");
    	        System.out.println("1. Öppna fil");
    	        System.out.println("2. Skapa en fil");
    	        System.out.println("3. Skriv i filen");
    	        System.out.println("4. Spara fil");
    	        System.out.println("5. Avsluta");
    	        this.getUserChoice(); //Skriver ut du angav och vad man har angett, om man angav något rimligt.
    	  }
    }
    
    public void handleUserChoice(int choice) {
        switch (choice) {
            case 1 -> openListener.run(); //Här körs lyssnaren när användaren väljer 1. I controller kopplas metoder sedan till dessa (handleOpen).
            case 2 -> createListener.run();
            case 3 -> writeListener.run();
            case 4 -> saveListener.run();
            case 5 -> exitListener.run();
            default -> System.out.println("Ogiltigt val!");
        }
    }

   // Metod för att registrera lyssnare för "öppna fil".
    @Override
    public void addOpenListener(Runnable listener) {
        this.openListener = listener;
    }
    
    @Override
    public void addCreateListener(Runnable listener) {
        this.createListener = listener;
    }
    
    @Override
    public void addWriteListener(Runnable listener) {
       this.writeListener = listener;
    }
    //Egen writeMetod för att den i controller inte fungerade på denna vy. Controller hänvisar tillbaka till metoden i view som fördelar baserat på vy val i och med ViewFactory klassen.
    public void write() {
        System.out.println("Ange text som du vill skriva i filen:");
        String text = scanner.nextLine(); // Hämta text från användaren
        System.out.println("Texten som du skrev är: " + text); //Skriver ut texten som användaren har matat in.
    }
    

    @Override
    public void addSaveListener(Runnable listener) {
        this.saveListener = listener;
    }

    @Override
    public void addExitListener(Runnable listener) {
        this.exitListener = listener;
    }
    
	 @Override
	public void viewCurr(String content) { //Metod för att visa det aktuella filinnehållet i konsolen.
        System.out.println(content); //Innehållet skrivs ut.
    }

    @Override
    public String getFileNameUI() { //Metod för att hämta filnamn från användaren.
        System.out.println("Ange filnamn:"); //Skrivs ut så att användaren informeras om att det kan matas in.
        return scanner.nextLine(); //Returnerar filnamnet som en sträng.
    }
    
    @Override
    public int showConfirmDialog(String message, String title) { //Metod för en bekräftelsediaglog för ett meddelande.
        System.out.println(title + ": " + message + " (y/n)"); //Meddelandet som ska visas
        String input = scanner.nextLine(); //Tar emot input som sparas som en string i input. NextLine läser in en heltrad tills enterslag.
        return input.equalsIgnoreCase("y") ? 0 : 1;  // Returnerar 0 för "ja", 1 för "nej"
    }

    @Override
    public void showMessage(String message, String title) { //Metod för att visa ett medelande till användaren
        System.out.println(title + ": " + message); //Medelandet skrivs ut såhär. title för texten och meddelandetexten skrivs inom ramarna.
    }

    @Override
    public void showError(String message) { //Metod för felmedelande
        System.out.println("Fel: " + message); //Här skrivs Fel ut, och sedan de medelande som man vill ha ex: felmeddelande.
    }
    
    @Override
    public void closeView() { //Metod för att avlsuta programmet: Avsluta whileloopen så att inga nya förslag ges.
        System.out.println("Tack för idag!\n") ; //En trevlig text som skrivs ut
        System.exit(0); //Här avlsutas självas programmet genom anropp till closeview i controller.
    }

    
    //Tomma eftersom dom ej används, behövs det verkligen?
    @Override
    public void addSaveAsListener(Runnable listener) {
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
        // Tomt: Ingen användning i ConsoleView.
    }
}
