package labb3;

import java.util.Scanner;

public class ViewFactory {
    public static IView createView() { // // Metoden skapar och returnerar en instans av en vy baserat på användarens val. Sedan anropar main createView.
    	Scanner scanner = new Scanner(System.in); //Skapar en scanner för att läsa input från konsollen.
    	
    	while(true) { //While loop för att användaren ska kunna göra ett giltligt val.
        System.out.println("Välj gränssnitt:"); // Visas för användaren i konsollen.
        System.out.println("1. Konsoll"); //^
        System.out.println("2. Grafiskt"); //^

        try { 
            int choice = Integer.parseInt(scanner.nextLine()); // Läser in användarens val som en integer och konverterar från string till int.
            System.out.println("Du valde: " + choice); //Här skrivs användarens val ut i konsollen.

            switch (choice) { //Om användaren trycker 1, returneras konsollvyn. Om 2 returneras grafiskt gränssnitt. Om en annan siffra inmatas kastas ett undantag för ogiltligt val.
                case 1:
                    return new ConsoleView(); //Här returneras den konsollbaserade vyn.
                case 2:
                    return new TextEditorView(); // Här returneras de grafiska gränssnittet.
                default:
                    throw new IllegalArgumentException("Ogiltigt val."); //Om man matar in en anna siffra än 1 eller 2.
            }
        } catch (Exception e) { //Om ett fel sker under inmatning skrivs ett felmeddelande ut.
            System.out.println("Fel vid inmatning men programmet fortsätter: " + e.getMessage()); //Detta skriv ut och while loopen fortsätter tills val 1 eller 2 väljs.
        }
    	}
    }
}