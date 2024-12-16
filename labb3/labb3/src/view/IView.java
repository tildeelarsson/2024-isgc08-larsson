package view;

public interface IView {
	void displayMenu(); 

	String getFileNameUI();
	void showConfirmDialog();
	void showMessage(String message, String title);
	void setTextArea(String contentFile);
	String getTextArea();
	String getSelectedText();
	String getFilePath();
	void setTextAreaEditable(boolean editable);
	void viewCurr(String content);
	String getContent();
	void relayCreate();
	void relayExit();
	void relaySave();
	void relaySaveAs();
	void relayWrite();
	void relayOpen();
	void relayCopy();
	void relayCut();
	void relayPaste();

}
