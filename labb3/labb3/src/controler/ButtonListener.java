package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.TextEditorView;

public class ButtonListener implements ActionListener {
	private TextEditorView view;
	private String choice;

	public ButtonListener(TextEditorView view, String choice) {
		this.view = view;
		this.choice = choice;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		view.setSelectedButton(choice);
	}
}
