
package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.TextEditorView;

public class OperationListener implements ActionListener {
	private TextEditorView view;
	private String operation;

	public OperationListener(TextEditorView view, String operation) {
		this.view = view;
		this.operation = operation;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		view.setSelectedOperation(operation);
	}
}
