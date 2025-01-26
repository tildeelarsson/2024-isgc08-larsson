package view;

import controler.TextEditorController;

public class ViewFactory {

	public static IView createView(String viewType, TextEditorController controller) {
		if (viewType.equalsIgnoreCase("console")) {
			return new ConsoleView(controller);
		} else if (viewType.equalsIgnoreCase("gui")) {
			return new TextEditorView(controller);
		} else {
			throw new IllegalArgumentException("Ogiltig vytyp: " + viewType);
		}
	}
}
