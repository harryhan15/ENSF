import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class CreateTreeFrame extends JFrame{
	BinSearchTree tree;
	
	private JPanel buttonPanel;
	private JPanel filePanel;
	private JTextField fileField;
	private JLabel label;
	
	private JButton cancelButton;
	private JButton okButton;
	
	private Container c;
	
	public CreateTreeFrame(BinSearchTree binSearchTree) {
		super("Create Tree From Frame");
		
		tree = binSearchTree;
		
		c = getContentPane();
		
		buttonPanel = new JPanel();
		okButton = new JButton("OK");
		okButton.setEnabled(false);
		cancelButton = new JButton("Cancel");
		
		buttonPanel.add(cancelButton);
		buttonPanel.add(okButton);
		
		label = new JLabel("Enter The File Name:");
		fileField = new JTextField(20);
		
		filePanel = new JPanel();
		filePanel.add(label);
		filePanel.add(fileField);
		
		c.add(buttonPanel, "South");
		c.add(filePanel, "Center");
		
		fileField.getDocument().addDocumentListener(new DocumentListener() {
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
				if (fileField.getText().equals("")){
					okButton.setEnabled(false);
   	  			}
     			else {
       				okButton.setEnabled(true);
    			}
    		}
  		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				setVisible(false);
			}
		});
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				File textFile = new File(fileField.getText());
				
				Scanner s = null;
				try {
					s = new Scanner(textFile);
				} catch( FileNotFoundException e ) {
					JOptionPane.showMessageDialog(null, "File Not Found.\nNote: Please include the extension of the file (.txt).", "Error Message", JOptionPane.ERROR_MESSAGE);
				}
				
				if(s != null) {
					while(s.hasNext()){
						String id = s.next();
						String faculty = s.next();
						String major = s.next();
						String year = s.next();
					
						tree.insert(id, faculty, major, year);
					}
				setVisible(false);
				}
			}
		});
		
		this.setLocationRelativeTo(null);
		this.setSize(400, 100);
	}

}
	
	