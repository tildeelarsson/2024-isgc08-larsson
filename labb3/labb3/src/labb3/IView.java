package labb3;

public interface IView {
    void displayMenu();
    int getUserChoice();
    String getFileNameUI();
    void setWelcomeText(String text);
    int showConfirmDialog(String message, String title);
    void addCreateListener(Runnable listener);
    void addOpenListener(Runnable listner);
    void addSaveListener(Runnable listener);
    void addSaveAsListener(Runnable listener);
    void addExitListener(Runnable listener);
    void addWriteListener(Runnable listener);
    void addCopyListener(Runnable listener);
    void addCutListener(Runnable listener);
    void addPasteListener(Runnable listener);
    void showMessage(String message, String title);
    void showError(String message);
    void setTextArea(String contentFile);
    String getTextArea();
    void viewCurr(String content);
    String getSelectedText();
    String getFilePath();
    String getFilePathSave();
    void setTextAreaEditable(boolean editable);
    void closeView();
    String getFileContentUI();
    
}
