package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.*;
import controler.TextEditorController;
import listenerklasser.CopyListener;
import listenerklasser.CreateListener;
import listenerklasser.CutListener;
import listenerklasser.ExitListener;
import listenerklasser.OpenListener;
import listenerklasser.PasteListener;
import listenerklasser.SaveAsListener;
import listenerklasser.SaveListener;
import listenerklasser.WriteListener;


public class TextEditorView implements IView {

    private JFrame frame;
    private JTextArea area;
    private JMenuBar menuBar;
    private JMenu edit;
    private JMenuItem write, copy, cut, paste;
    private JButton createFile, openFile, saveFile, saveFileAs, exitFile;
    private JPanel panel;
    private TextEditorController c;

    public TextEditorView(TextEditorController con) {
        c = con; 
        

        frame = new JFrame("Text Editor"); 
        area = new JTextArea(20, 50);
        frame.add(new JScrollPane(area), BorderLayout.CENTER);
        
        menuBar = new JMenuBar();
        panel = new JPanel();
        
        area.setEditable(false); 
        
       
        edit = new JMenu("Edit");
        write = new JMenuItem("Write");
        copy = new JMenuItem("Copy");
        cut = new JMenuItem("Cut");
        paste = new JMenuItem("Paste");
        
       
        edit.add(write);
        edit.add(copy);
        edit.add(cut);
        edit.add(paste);
        
       
        menuBar.add(edit);
        
        panel.setLayout(new FlowLayout()); 
        
       
        createFile = new JButton("Create"); 
        openFile = new JButton("Open");
        saveFile = new JButton("Save");
        saveFileAs = new JButton("Save file as");
        exitFile = new JButton("Exit");


        panel.add(createFile);
        panel.add(openFile);
        panel.add(saveFile);
        panel.add(saveFileAs);
        panel.add(exitFile);
        
        
        createFile.addActionListener(new CreateListener(this));
        openFile.addActionListener(new OpenListener(this));
        saveFile.addActionListener(new SaveListener(this));
        saveFileAs.addActionListener(new SaveAsListener(this));
        exitFile.addActionListener(new ExitListener(this));
        

        write.addActionListener(new WriteListener(this));
        copy.addActionListener(new CopyListener(this));
        cut.addActionListener(new CutListener(this));
        paste.addActionListener(new PasteListener(this));
   
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(menuBar); 
        frame.setSize(600, 400); 
        frame.add(panel, BorderLayout.SOUTH); 
        frame.setVisible(true); 
    }

   
    
    public void relayExit() {
        c.handleEvent("Exit");
    }
    
    public void relayCreate() {
        c.handleEvent("Create");
    }

    public void relayOpen() {
        c.handleEvent("Open");
    }

    public void relaySave() {
        c.handleEvent("Save");
    }

    public void relaySaveAs() {
        c.handleEvent("SaveAs");
    }

    public void relayWrite() {
        c.handleEvent("Write");
    }

    public void relayCopy() {
        c.handleEvent("Copy");
    }

    public void relayCut() {
        c.handleEvent("Cut");
    }

    public void relayPaste() {
        c.handleEvent("Paste");
    }

    public void displayMenu() {
        area.setText("Välkommen till TextEditorn! \n"
                + "För att skriva --> skapa eller öppna en fil & tryck på edit --> write\n"
                + "För att kopiera, klippa eller klistra in markera och tryck på edit\n"
                + "Lycka till!\n");
    }

  
    
    public void setTextAreaEditable(boolean editable) {
        area.setEditable(editable);
    }

    public void showMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public void showConfirmDialog() {
    	int option = JOptionPane.showConfirmDialog(panel,"Filen har osparade ändringar vill du spara innan du stänger ner?", "Notis", JOptionPane.YES_NO_OPTION);
        
        if (option==JOptionPane.YES_OPTION) { 
            c.handleSave();
            System.exit(0);
          }
        else if (option==JOptionPane.NO_OPTION)	
        {
        	System.exit(0);
        }
    }


    public void viewCurr(String content) {
        area.setText(content);
    }

    public void closeView() {
        System.exit(0);
    }

    public String getTextArea() {
        return area.getText();
    }
    
    public String getContent() {
        return area.getText();
    }

    public void setTextArea(String text) {
        area.setText(text);
    }

    public String getSelectedText() {
        return area.getSelectedText();
    }

    
    public String getFilePath() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(area);
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();
        } else {
            return null;
        }
    }


    public String getFileNameUI() {
        String fileName = JOptionPane.showInputDialog(area, "Ange filnamn:", "Filnamn", JOptionPane.PLAIN_MESSAGE);
        if (fileName == null || fileName.trim().isEmpty()) {
            showMessage("Filnamn kan inte vara tomt!" + "", "Error");
            return null;
        }
        return fileName;
    }
}
