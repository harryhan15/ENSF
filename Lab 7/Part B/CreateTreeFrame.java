import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class CreateTreeFrame extends JFrame {
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
					System.out.println( "File not found" );
				}
				
				while(s.hasNext()){
					String id = s.next();
					String faculty = s.next();
					String major = s.next();
					String year = s.next();
					
					tree.insert(id, faculty, major, year);
				}
				
				setVisible(false);
			}
		});
		
		this.pack();
	}
}
	
	