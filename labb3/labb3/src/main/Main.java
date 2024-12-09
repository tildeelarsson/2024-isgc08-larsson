package main;

import controler.TextEditorController;
import model.FileManager;
import view.IView;
import view.ViewFactory;

public class Main {
    public static void main(String[] args) {
 
        IView view = ViewFactory.createView(); //CreateView finns i viewfactory och där hanteras valet om vilken vy som ska köras. Controller är ovetandes om vilken.
        
        FileManager fM = new FileManager();
        
     // Skapa en instans av TextEditorController och koppla ihop den med IView och FileManager för att kunna köra programmet.
        TextEditorController controller = new TextEditorController(view, fM);
     
     //Hämtar från IView som implementeras i båda vyerna. Visar det man vill skriva i vyerna på startsidan.
        view.displayMenu(); 
    }
}

