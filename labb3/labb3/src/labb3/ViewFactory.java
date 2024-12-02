package labb3;

import java.util.Scanner;

public class ViewFactory {
    public static TextEditorViewInterface createView() {
        System.out.println("Välj gränssnitt:");
        System.out.println("1. Konsoll");
        System.out.println("2. Grafiskt");
        System.out.print("Ditt val: ");

        Scanner scanner = new Scanner(System.in);
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                return new ConsoleView();
            case 2:
                return new TextEditorView();
            default:
                throw new IllegalArgumentException("Ogiltigt val.");
        }
    }
}
