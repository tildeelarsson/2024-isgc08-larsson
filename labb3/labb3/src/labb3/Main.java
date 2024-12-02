package labb3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IView view = ViewFactory.createView();
        FileManager fM = new FileManager();
        
        TextEditorController controller = new TextEditorController(view, fM);
        
        if (view instanceof ConsoleView) {
            controller.handleUserChoice();
        }
        
        view.displayMenu();
    }
}
