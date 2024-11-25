package labb1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TextEditorView {
    private JTextArea textArea;
    private JButton[] buttons; // En Array som håller alla övre knappar
    private JFrame frame;
    private JTextField textField;

 // Nya Edit-knappar för den nedre delen 
    private JButton writeButton;
    private JButton cutButton;
    private JButton copyButton;
    private JButton pasteButton;

    public TextEditorView() {

        frame = new JFrame("Text Editor");
        textArea = new JTextArea();
        textField = new JTextField(20);
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        textArea.setEditable(false); // Gör textområdet inaktivt från början för att man ej ska kunna skriva förens man trycker på write.
        setWelcomeText();
        
        textArea.setBackground(new Color(255, 228, 225)); // Ljus rosa
        scrollPane.setBackground(new Color(255, 228, 225)); // Ljus rosa
        
        // Pga array.
        String[] buttonLabels = {"Create", "Open", "Save", "Save file as", "Exit"};
        buttons = new JButton[buttonLabels.length];
        
     
       
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(textField, BorderLayout.SOUTH);
        frame.getContentPane().setBackground(new Color(255, 182, 193)); // RGB för ljus rosa
        
        //Min knapp panel för de övre knapparna
        JPanel buttonPanel = new JPanel(); 
        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttonPanel.add(buttons[i]);
        }
        
        // En egen panel för Edit-knapparna
        JPanel editPanel = new JPanel();
        writeButton = new JButton("Write");
        cutButton = new JButton("Cut");
        copyButton = new JButton("Copy");
        pasteButton = new JButton("Paste");
        

        editPanel.add(writeButton);
        editPanel.add(cutButton);
        editPanel.add(copyButton);
        editPanel.add(pasteButton);
        
        // Adderar båda panelerna till framen.
        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(editPanel, BorderLayout.SOUTH); 
        frame.setVisible(true);
    }

    public void setWelcomeText() {
    	textArea.setText("Välkommen!\n"
    			+ "För att skriva ---> create (ny fil) eller open (existerande fil).\n"
    			+ "För att kopiera, klippa ut eller klistra in ---> Markera texten och tryck på copy, cut eller paste.\n"
    			+ "För att spara ---> Save.\n"
    			+ "För att spara ner din fil och stänga text editorn ---> Save As.\n"
    			+ "För att stänga ner text editorn ----> Exit.\n"
    			+ "Lycka till! :)\n ");
    }

    public JFrame getFrame() {
        return frame;
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void closeView() {
        frame.dispose();
    }

    public String getFileName() {
        return textField.getText();
    }

    public void setText(String text) {
        textArea.setText(text);
    }

    public String getSelectedText() {
    	return textArea.getSelectedText();
    }

    public String getText() {
        return textArea.getText();
    }

    public JTextArea getTextArea() {
        return textArea; 
    }

    public void viewCurr(String content) {
        textArea.setText(content);
    }

    //Eftersom jag hade en array här, nedanför blir det separerar till edit-knapparna
    public void addButtonListener(int index, ActionListener listener) {
        if (index >= 0 && index < buttons.length) {
            buttons[index].addActionListener(listener);
        }
    }


    public void addWriteButtonListener(ActionListener listener) {
        writeButton.addActionListener(listener);
    }

    public void addCutButtonListener(ActionListener listener) {
        cutButton.addActionListener(listener);
    }

    public void addCopyButtonListener(ActionListener listener) {
        copyButton.addActionListener(listener);
    }

    public void addPasteButtonListener(ActionListener listener) {
        pasteButton.addActionListener(listener);
    }}