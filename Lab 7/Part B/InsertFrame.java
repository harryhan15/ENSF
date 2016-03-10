import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class InsertFrame extends JFrame{
	
BinSearchTree tree;
Node node;
	
	private JPanel buttonPanel;
	private JPanel filePanel;
	private JPanel textPanel;

	private JTextField fileField1;
	private JTextField fileField2;
	private JTextField fileField3;
	private JTextField fileField4;
	private JLabel IDlabel;
	private JLabel Facultylabel;
	private JLabel Majorlabel;
	private JLabel Yearlabel;
	
	
	private JButton ReturnButton;
	private JButton InsertButton;
	
	private Container c;
	
	
	public InsertFrame(BinSearchTree newTree) {
		super("InsertFrame");
		
		tree = newTree;
		
		c = getContentPane();
		
		buttonPanel = new JPanel();
		ReturnButton = new JButton("Return to Main Window");
		InsertButton = new JButton("Insert");
		
		buttonPanel.add(ReturnButton);
		buttonPanel.add(InsertButton);
		InsertButton.setEnabled(false);

		IDlabel = new JLabel("    Student ID    ");
		fileField1 = new JTextField(10);
		
		Facultylabel = new JLabel("       Faculty       ");
		fileField2 = new JTextField(10);
		
		Majorlabel = new JLabel("        Major        ");
		fileField3 = new JTextField(10);
		
		Yearlabel = new JLabel("         Year         ");
		fileField4 = new JTextField(10);
		
		filePanel = new JPanel();
		filePanel.add(IDlabel);
		filePanel.add(fileField1);

		filePanel.add(Facultylabel);
		filePanel.add(fileField2);

		filePanel.add(Majorlabel);
		filePanel.add(fileField3);

		filePanel.add(Yearlabel);
		filePanel.add(fileField4);

		filePanel.setPreferredSize(new Dimension(250, 200));

		fileField1.getDocument().addDocumentListener(new MyDocumentListener());
		fileField2.getDocument().addDocumentListener(new MyDocumentListener());
		fileField3.getDocument().addDocumentListener(new MyDocumentListener());
		fileField4.getDocument().addDocumentListener(new MyDocumentListener());

		//((AbstractDocument) fileField2.getDocument()).setDocumentFilter(new MyDocumentFilter());
		//((AbstractDocument) fileField3.getDocument()).setDocumentFilter(new MyDocumentFilter());
		
		c.add(buttonPanel, "South");
		c.add(filePanel, BorderLayout.LINE_START);
		
		ReturnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				setVisible(false);
			}
		});
		
		InsertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt){
				String id = fileField1.getText();
				String faculty = fileField2.getText();
				String major = fileField3.getText();
				String year = fileField4.getText();
			
				tree.insert(id, faculty, major, year);
			
				String msg = "Added Student ID: " + id + "\nFaculty: " + faculty + 
						"\nMajor: " + major + "\nYear: " + year;
			
					JOptionPane.showMessageDialog(null, msg);
		
				System.out.println(msg);
				setVisible(false);
			}
			
		});
		
		this.setLocationRelativeTo(null);
		this.setSize(300, 200);
	}

	class MyDocumentListener implements DocumentListener {
		public void changedUpdate(DocumentEvent e) {
    		changed();
  		}
		public void removeUpdate(DocumentEvent e) {
    		changed();
  		}
 		public void insertUpdate(DocumentEvent e) {
 			changed();
		}
			
		public void changed() {
			if (fileField1.getText().equals("") || fileField2.getText().equals("") || fileField3.getText().equals("") || fileField4.getText().equals("") || tree.root == null){
				InsertButton.setEnabled(false);
   	  		}
     		else {
       			InsertButton.setEnabled(true);
    		}
		}
	}
}

//Experimenting around, copied code from stack overflow
/*
class MyDocumentFilter extends DocumentFilter {

    @Override
    public void replace(FilterBypass fb, int i, int i1, String string, AttributeSet as) throws BadLocationException {
        for (int n = string.length(); n > 0; n--) {
            char c = string.charAt(n - 1);
            if (Character.isAlphabetic(c) || c == ' ') {
                super.replace(fb, i, i1, String.valueOf(c), as);
            } else {
                System.out.println("Not allowed");
            }
        }
    }

    @Override
    public void remove(FilterBypass fb, int i, int i1) throws BadLocationException {
        super.remove(fb, i, i1);
    }

    @Override
    public void insertString(FilterBypass fb, int i, String string, AttributeSet as) throws BadLocationException {
        super.insertString(fb, i, string, as);

    }
*/