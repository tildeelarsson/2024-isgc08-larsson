package labb3;

public interface TextEditorViewInterface {
	void setWelcomeText();
    void displayMenu();
    String getFileNameUI();
    void viewCurr(String content);
    void showMessage(String message, String Title);
    void showError(String error);
    int getUserChoice();
}
