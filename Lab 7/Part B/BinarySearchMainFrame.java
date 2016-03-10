import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class BinarySearchMainFrame extends JFrame {
	BinSearchTree binSearchTree;
	private JPanel buttonsPanel;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JLabel titleLabel;
	
	private CreateTreeFrame createTreeFrame;
	private FindFrame findFrame;
	private InsertFrame insertFrame;
	
	//Buttons
	private JButton insertButton;
	private JButton findButton;
	private JButton browseButton;
	private JButton createButton;
	
	private Container c;
	
	public BinarySearchMainFrame() {
		super("Student Records");
		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		binSearchTree = new BinSearchTree();
		
		c = getContentPane();
		
		titleLabel = new JLabel("", SwingConstants.CENTER);
		titleLabel.setText("An Application To Maintain Student Records");
		
		buttonsPanel = new JPanel();
		insertButton = new JButton("Insert");
		findButton = new JButton("Find");
		browseButton = new JButton("Browse");
		createButton = new JButton("Create Tree From File");

		buttonsPanel.add(insertButton);
		buttonsPanel.add(findButton);
		buttonsPanel.add(browseButton);
		buttonsPanel.add(createButton);
		
		textArea = new JTextArea(5,20);
		scrollPane = new JScrollPane(textArea);
		textArea.setEditable(false);
		textArea.insert("Input a text file to display the records of all students.", 0);
		textArea.setFont(new Font("Courier New", Font.PLAIN, 12));

		findButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt){
				findFrame = new FindFrame(binSearchTree);
				findFrame.setVisible(true);
			}
		});

		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt){
				insertFrame = new InsertFrame(binSearchTree);
				insertFrame.setVisible(true);
			}
		});

		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				
				if(binSearchTree.root == null){
					JOptionPane.showMessageDialog(null, "No file has been read in.", "Error Message", JOptionPane.ERROR_MESSAGE);
				}
				else {
					try {
						binSearchTree.print_tree(binSearchTree.root, pw);
					} catch(IOException e) {
						System.out.println("Couldn't print.");
					}
					textArea.setText("ID  " + "   Faculty  " + "  Major  " + " Year   \n" + "-------------------------------------\n"  + sw.toString());
				}
				pw.close();
			}
		});
		
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				createTreeFrame = new CreateTreeFrame(binSearchTree);
				createTreeFrame.setVisible(true);
			}
		});
		
		c.add(titleLabel, "North");
		c.add(scrollPane, "Center");
		c.add(buttonsPanel, "South");
		
		this.setLocationRelativeTo(null);
		this.setSize(600, 400);
		this.setVisible(true);
	}
	
	public static void main( String[] args ) {
		BinarySearchMainFrame mainFrame = new BinarySearchMainFrame();
	}
}