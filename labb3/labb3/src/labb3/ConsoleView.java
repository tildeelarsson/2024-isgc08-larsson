package labb3;


import java.util.Scanner;

public class ConsoleView implements TextEditorViewInterface {
    private Scanner scanner;

    public ConsoleView() {
        scanner = new Scanner(System.in);
    }
    
 
        public void setWelcomeText() {
            System.out.println("Välkommen till den konsollbaserade TextEditorn!");
        }
   

    @Override
    public void displayMenu() {
        System.out.println("1. Läs fil");
        System.out.println("2. Skapa ny fil");
        System.out.println("0. Avsluta");
        System.out.print("Ditt val: ");
    }

    @Override
    public String getFileNameUI() {
        System.out.print("Ange filnamn inklusive sökväg: ");
        return scanner.nextLine();
    }

    @Override
    public void viewCurr(String content) {
        System.out.println("Filinnehåll:");
        System.out.println(content);
    }


    @Override
    public void showMessage(String message, String title) {
        System.out.println(message);
    }

    @Override
    public void showError(String error) {
        System.err.println("Fel: " + error);
    }

    @Override
    public int getUserChoice() {
        return Integer.parseInt(scanner.nextLine());
    }
}

