package myApp;


//Imports
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Rectangle;

public class AppGUI extends JFrame {
	
	
	//Swing Variables are defined here
	protected JTextArea EText;
	protected JTextArea DText;
	protected JButton btnEncrypt;
	protected JTextArea KeyText;
	protected JTextField txtFile;
	protected JLabel Datelabel;
	
	//Call The Algorithm class
	AppAlgorithm aesAlgo;
	

	
	
public AppGUI (){
	

	super("Encryption App");
	setForeground(new Color(0, 0, 255));
	getContentPane().setForeground(new Color(0, 0, 0));
	getContentPane().setBackground(new Color(192, 192, 192));
	

	
	
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	getContentPane().setLayout(null);//Absolute positioning
	setBounds(0,0,764,428); //set size of JFrame
	setLocationRelativeTo(null);
	
	
	//Button For Encryption
	btnEncrypt = new JButton("Encrypt");
	btnEncrypt.setBackground(new Color(255, 0, 0));
	btnEncrypt.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {

			//Check if the text area is empty, if empty, display error message
			if (EText.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "You Must write something to Encrypt");
			} else if (KeyText.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "You Must have a key for Encryption");
			}
			else {
			try {
				//Get the key from the textfield and pass it to the AppAlgorithm class for encryption
				String myKey = KeyText.getText();
				aesAlgo = new AppAlgorithm(myKey);
				String plainText = EText.getText();
				String encryptedText = aesAlgo.encrypt(plainText);
				DText.setText(encryptedText);
			}
			
			catch(Exception e1) {
				e1.printStackTrace();
				
			}
		}
		}
	});
	btnEncrypt.setBounds(10, 77, 89, 23);
	getContentPane().add(btnEncrypt);
	
	
	//Button for Decryption
	JButton btnDecrypt = new JButton("Decrypt");
	btnDecrypt.setBackground(new Color(0, 0, 255));
	btnDecrypt.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			//Check if the text area is empty, if empty, display error message
			if (DText.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "You must have Something to Decrypt");
			} else if (KeyText.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "You Must have a key for Decryption");
			}
			
			else {
			
			try{
				//Get the key from the textfield and pass it to the AppAlgorithm class for encryption
				String myKey = KeyText.getText();
				aesAlgo = new AppAlgorithm(myKey);
				String encryptedText = DText.getText();
				String plainText = aesAlgo.decrypt(encryptedText);
				DText.setText(plainText);
			}catch(Exception e1){
				
			}
			}
		}
	});
	btnDecrypt.setBounds(633, 77, 89, 23);
	getContentPane().add(btnDecrypt);
	
	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(10, 132, 320, 201);
	getContentPane().add(scrollPane);
	
	EText = new JTextArea();
	EText.setBounds(new Rectangle(5, 5, 5, 5));
	scrollPane.setViewportView(EText);
	
	JScrollPane scrollPane_1 = new JScrollPane();
	scrollPane_1.setBounds(442, 132, 280, 201);
	getContentPane().add(scrollPane_1);
	
	DText = new JTextArea();
	scrollPane_1.setViewportView(DText);
	
	KeyText = new JTextArea("Put Your Own Key Here");
	KeyText.setBounds(252, 76, 227, 22);
	getContentPane().add(KeyText);
	
	JLabel lblKeyForEncryptiondecryption = new JLabel("Key For Encryption/Decryption");
	lblKeyForEncryptiondecryption.setBounds(289, 34, 150, 31);
	getContentPane().add(lblKeyForEncryptiondecryption);
	
	JButton btnSaveEncryptedText = new JButton("Save Encrypted Text");
	
	btnSaveEncryptedText.addActionListener(new ActionListener() {
		
		
		public void actionPerformed(ActionEvent arg0) {
			//Check if the text area is empty, if empty, display error message
			if (txtFile.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "There was an error while saving the file");
			} else {
				
			
			//Save file to the project folder
			try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(txtFile.getText()))) {
			    DText.write(fileOut);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Encrypted Text is Saved to A file, which can be located from the folder of this Application (Java_OO_project), Note In order to view the file, you have to open it with notepad etc.");
		
			
		}
		}
	});
	btnSaveEncryptedText.setBounds(572, 9, 150, 23);
	getContentPane().add(btnSaveEncryptedText);
	
	txtFile = new JTextField("your filename");
	txtFile.setBounds(572, 34, 150, 20);
	getContentPane().add(txtFile);
	txtFile.setColumns(10);
	
	AppDate NewDate = new AppDate();
	String TimeAndDate = NewDate.reportDate;
	
	Datelabel = new JLabel(TimeAndDate);
	Datelabel.setBounds(0, 0, 131, 20);
	getContentPane().add(Datelabel);
	
}

public static void main(String[] args) {
	AppGUI frame = new AppGUI();
	frame.setVisible(true);
}
}

