import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class FindFrame extends JFrame{
	
BinSearchTree tree;
Node node;
	
	private JPanel buttonPanel;
	private JPanel filePanel;
	private JTextField fileField;
	private JLabel label;
	
	private JButton cancelButton;
	private JButton okButton;
	
	private Container c;
	
	
	public FindFrame(BinSearchTree binSearchTree) {
		super("Find");
		
		tree = binSearchTree;
		
		c = getContentPane();
		
		buttonPanel = new JPanel();
		okButton = new JButton("OK");
		okButton.setEnabled(false);
		cancelButton = new JButton("Cancel");
		
		buttonPanel.add(cancelButton);
		buttonPanel.add(okButton);
		
		label = new JLabel("Enter The ID:");
		fileField = new JTextField(5);
		
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
				String idNum = fileField.getText();
				
				node = tree.find(tree.root, idNum);
				
				if(node != null)
				{
					JOptionPane.showMessageDialog(null, node.toString());
				}
				else
					JOptionPane.showMessageDialog(null, "Cannot Find ID.", "Error Message", JOptionPane.ERROR_MESSAGE);
				System.out.println(node.toString());
				setVisible(false);
			}
		});
		
		this.setLocationRelativeTo(null);
		this.setSize(400, 100);
	}

}
