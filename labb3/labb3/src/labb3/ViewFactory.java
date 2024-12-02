package labb3;

import java.util.Scanner;

public class ViewFactory {
    public static IView createView() {
    	
    	Scanner scanner = new Scanner(System.in);
        System.out.println("Välj gränssnitt:");
        System.out.println("1. Konsoll");
        System.out.println("2. Grafiskt");

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            System.out.println("Du valde: " + choice); 

            switch (choice) {
                case 1:
                    return new ConsoleView();
                case 2:
                    return new TextEditorView();
                default:
                    throw new IllegalArgumentException("Ogiltigt val.");
            }
        } catch (Exception e) {
            System.out.println("Fel vid inmatning: " + e.getMessage());
            throw e;
        }
    }
}