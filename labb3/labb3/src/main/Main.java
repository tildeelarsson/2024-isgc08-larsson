package main;
import view.*;
import controler.*;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Välj användargränssnitt:");
			System.out.println("1. Konsolvy");
			System.out.println("2. Grafisk TextEditor");

			String choice = scanner.nextLine();
			String viewType = "console"; 

			if ("1".equals(choice)) {
				viewType = "console";
			} else if ("2".equals(choice)) {
				viewType = "gui";
			} else {
				System.out.println("Ogiltigt val, Konsollvyn visas nu.");
			}


			TextEditorController controller = new TextEditorController(); 
			IView view = ViewFactory.createView(viewType, controller);
			controller.setView(view);


			view.runUI();
		}
	}
}
