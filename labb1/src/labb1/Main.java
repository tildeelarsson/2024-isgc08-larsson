package labb1;

public class Main {
    public static void main(String[] args) {
        TextEditorView view = new TextEditorView();
        FileManager model = new FileManager();
        TextEditorController controller = new TextEditorController(model, view);
    }
}