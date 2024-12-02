package labb3;

public class Main {
public static void main(String[] args) {
	
	TextEditorViewInterface view = ViewFactory.createView();
    //TextEditorView view = new TextEditorView();
    FileManager fM = new FileManager();

    TextEditorController controller = new TextEditorController((TextEditorView) view, fM);


    view.setWelcomeText();
        }
    

}


