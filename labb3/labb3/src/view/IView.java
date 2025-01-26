package view;

public interface IView {
	String getFileNameUI();

	void showConfirmDialog();

	void showMessage(String message, String title);

	void setTextArea(String contentFile);

	String getTextArea();

	String getSelectedText();

	String getFilePath();

	void viewCurr(String content);

	String getContent();

	void runUI();

	void setSelectedButton();

	void setSelectedOperation();
}
