package labb2;

public class Main {
public static void main(String[] args) {
	TextEditorView view = new TextEditorView();
FileManager fM = new FileManager();
TextEditorController controller = new TextEditorController(view, fM);
}
}

