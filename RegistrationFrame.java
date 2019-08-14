package package_1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import package_1.Client;
import package_1.ClientController;
import package_1.LoginFrame;

public class RegistrationFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtFN;
	private JTextField txtLN;
	private JTextField txtEmail;
	private JTextField txtUsername;
	private JPasswordField passwordField;
	private JPasswordField CpasswordField;
	private JRadioButton rdbtnMale,rdbtnFemale;
	private JTextArea txtArAddress;
	private JLabel lblRegistrationForm,lblFirstName,lblLastName,lblEmail,lblUsername,lblPassword,lblConfirmPassword,lblAddress, lblGender;
	private JButton btnCancel,btnRegister;
	ButtonGroup group;
	String Gender;
    public int Registered;
    
    Client c2;
    
    String firstName;
    String lastName;
    boolean gender;
    String email;
    String username;
    String password;
    String cPassword;
    String address;
    
    Pattern p;
    Matcher m;
    boolean b;

    private Connection con ;
    private PreparedStatement ps;
    private ResultSet rs;
    
    
	/**
	 * Create the frame.
	 */
	public RegistrationFrame() {
		Registered=0;
        
		 try{
			   con = AuctionBackend1.connection();
		   }catch(Exception e){
			  	e.printStackTrace();
	       }
		 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 580);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(47, 79, 79));
		contentPane.setBounds(100, 100, 450, 590);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(240, 255, 240));
		panel.setBackground(new Color(255, 140, 0));
		panel.setBounds(0, 0, 434, 45);
		contentPane.add(panel);
		
		lblRegistrationForm = new JLabel("Registration Form");
		lblRegistrationForm.setForeground(new Color(245, 245, 245));
		lblRegistrationForm.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblRegistrationForm);
		
		lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFirstName.setForeground(new Color(255, 255, 255));
		lblFirstName.setBounds(38, 76, 107, 20);
		contentPane.add(lblFirstName);
		
	    lblLastName = new JLabel("Last Name:");
		lblLastName.setForeground(new Color(255, 255, 255));
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLastName.setBounds(38, 107, 107, 20);
		contentPane.add(lblLastName);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEmail.setBounds(80, 196, 65, 20);
		contentPane.add(lblEmail);
		
		lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUsername.setForeground(new Color(255, 255, 255));
		lblUsername.setBounds(44, 227, 101, 20);
		contentPane.add(lblUsername);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setBounds(50, 258, 95, 20);
		contentPane.add(lblPassword);
		
		lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setForeground(new Color(255, 255, 255));
		lblConfirmPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblConfirmPassword.setBounds(0, 291, 166, 20);
		contentPane.add(lblConfirmPassword);
		
		lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAddress.setForeground(Color.WHITE);
		lblAddress.setBounds(38, 333, 73, 23);
		contentPane.add(lblAddress);
		
		txtArAddress = new JTextArea();
		txtArAddress.setLineWrap(true);
		txtArAddress.setFocusTraversalPolicyProvider(true);
		txtArAddress.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtArAddress.setForeground(new Color(255, 255, 255));
		txtArAddress.setBackground(new Color(112, 128, 144));
		txtArAddress.setBounds(123, 353, 221, 95);
		contentPane.add(txtArAddress);
		
		
		btnCancel = new JButton("Back");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame lf=new LoginFrame();
				lf.setVisible(true);
				visible(e);
				
			}
		});
		btnCancel.setRolloverEnabled(false);
		btnCancel.setRequestFocusEnabled(false);
		btnCancel.setForeground(new Color(255, 0, 0));
		btnCancel.setBackground(new Color(47, 79, 79));
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCancel.setBounds(96, 475, 89, 36);
		contentPane.add(btnCancel);
		
		txtFN = new JTextField();
		txtFN.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtFN.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtFN.setBackground(new Color(112, 128, 144));
		txtFN.setBounds(155, 74, 146, 22);
		txtFN.setColumns(10);
		contentPane.add(txtFN);
		
		
		txtLN = new JTextField();
		txtLN.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtLN.setBackground(new Color(112, 128, 144));
		txtLN.setBounds(155, 106, 146, 23);
		txtLN.setColumns(10);
		contentPane.add(txtLN);
		
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtEmail.setBackground(new Color(112, 128, 144));
		txtEmail.setBounds(153, 195, 189, 23);
		txtEmail.setColumns(10);
		contentPane.add(txtEmail);
		
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtUsername.setBackground(new Color(112, 128, 144));
		txtUsername.setBounds(153, 226, 189, 23);
		txtUsername.setColumns(10);
		contentPane.add(txtUsername);
		
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 14));
		passwordField.setBackground(new Color(112, 128, 144));
		passwordField.setBounds(153, 258, 189, 23);
		contentPane.add(passwordField, BorderLayout.CENTER);
		
		
		CpasswordField = new JPasswordField();
		CpasswordField.setBackground(new Color(112, 128, 144));
		CpasswordField.setFont(new Font("Tahoma", Font.BOLD, 14));
		CpasswordField.setBounds(155, 290, 189, 23);
		contentPane.add(CpasswordField, BorderLayout.CENTER);
		
		lblGender = new JLabel("Gender:");
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGender.setForeground(new Color(255, 255, 255));
		lblGender.setBackground(new Color(47, 79, 79));
		lblGender.setBounds(62, 156, 73, 14);
		contentPane.add(lblGender);
		
		rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setRequestFocusEnabled(false);
		rdbtnMale.setRolloverEnabled(false);
		rdbtnMale.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtnMale.setForeground(new Color(255, 255, 255));
		rdbtnMale.setBackground(new Color(47, 79, 79));
		rdbtnMale.setBounds(156, 153, 65, 23);
		contentPane.add(rdbtnMale);
		
		rdbtnFemale = new JRadioButton("Female",false);
		rdbtnFemale.setRolloverEnabled(false);
		rdbtnFemale.setRequestFocusEnabled(false);
		rdbtnFemale.setForeground(new Color(255, 255, 255));
		rdbtnFemale.setBackground(new Color(47, 79, 79));
		rdbtnFemale.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtnFemale.setBounds(237, 153, 80, 23);
		contentPane.add(rdbtnFemale);
		
		group=new ButtonGroup();
		group.add(rdbtnMale);
		group.add(rdbtnFemale);
		
		
		btnRegister = new JButton("Register\r\n");
		btnRegister.setRolloverEnabled(false);
		btnRegister.setRequestFocusEnabled(true);
		btnRegister.setForeground(new Color(30, 144, 255));
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnRegister.setBackground(new Color(47, 79, 79));
		btnRegister.setBounds(237, 474, 107, 38);
		contentPane.add(btnRegister);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 255));
		separator.setBounds(10, 459, 414, 6);
		contentPane.add(separator);
		btnRegister.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					if(RegisterData()){
						Registered=1;
						ps = con.prepareStatement("insert into registrationtable values(?,?,?,?,?,?,?)");
						ps.setString(1,firstName);
						ps.setString(2,lastName);
						ps.setString(3,Gender);
						ps.setString(4,email);
						ps.setString(5,address);
						ps.setString(6,username);
						ps.setString(7,password);
						ps.executeUpdate();
						ps.close();
						JOptionPane.showMessageDialog(null,
								"Data Registered Successfully.");
						
					/* try {
						sendDataToController(Registered);
					} catch (FileNotFoundException e1) {
						System.out.println("Exception Handled during Authenticating User Input in Registration Frame on Register Button Click."+e1);
						e1.printStackTrace();
					}	 
					*/
						//Store the Data in the Database.
					}
				} catch (Exception e1) {
					System.out.println("Exception Caught in Register Button.");
					e1.printStackTrace();
				}		
		 }
		});
		
	}
	void visible(ActionEvent e)
	{
		this.setVisible(false);
	}
	
	   //Function To Validate All the Input.
	   

		@SuppressWarnings("deprecation")
		boolean RegisterData() throws Exception{
	    	 firstName=txtFN.getText();
	    	 p = Pattern.compile("[^a-z]", Pattern.CASE_INSENSITIVE);
	    	 m = p.matcher(firstName);
	    	 b = m.find();
				if(b || firstName.equalsIgnoreCase(""))
	    	     {
					JOptionPane.showMessageDialog(null,"Invalid First Name!!");
					txtFN.requestFocusInWindow();
							txtFN.setText("");
							firstName="";					 
							return false;
				}
				System.out.println(firstName);
				
			lastName=txtLN.getText();
		    p = Pattern.compile("[^a-z]", Pattern.CASE_INSENSITIVE);
	    	m = p.matcher(lastName);
	    	b = m.find();
			 if(lastName.equalsIgnoreCase("") || b)
				{
					JOptionPane.showMessageDialog(null,"Invalid Last Name!!");
					txtLN.requestFocusInWindow();
							txtLN.setText("");
							lastName="";
							return false;
				}
			 System.out.println(lastName);
			 
			 gender = false;
			try{
		     gender=rdbtnMale.isSelected();
			}catch(Exception e)
			{
				System.out.println("Exception Caught in Gender.");
				return false;
			}
		    if(gender==true)
		    	Gender="M";
		    else
		    	Gender="F";
		    System.out.println(Gender);
		    
		    
		    email=txtEmail.getText();
		    if(email.equalsIgnoreCase("") || email.equals("@gmail.com")|| email.equals("@yahoo"))
			{
				JOptionPane.showMessageDialog(null,"Invalid Email ID!!");
				txtEmail.requestFocusInWindow();
						txtEmail.setText("");
						email="";		
						return false;
			}
		    System.out.println(email);
		    
		    username=txtUsername.getText();    
		    boolean test=false;
	    	//boolean test = To check in the Database If any User is Present with The same UserName.
		    ps = con.prepareStatement("select UserName from registrationtable where UserName=?");
		    ps.setString(1, username);
		    rs= ps.executeQuery();
		    if(rs.next())
		    {
		      test=true;
		    }
		    
	    	if(test)
	    	{
	    	  JOptionPane.showMessageDialog(null,"This Username Already Exists.");
	    	  txtUsername.requestFocusInWindow();
				txtUsername.setText("");
						username="";		
						return false;
	    	}
		    if(username.equalsIgnoreCase(""))
			{
				JOptionPane.showMessageDialog(null,"Invalid Email ID!!");
				txtUsername.requestFocusInWindow();
				txtUsername.setText("");
						username="";		
						return false;
			}
		    System.out.println(username);
			password=passwordField.getText();
			cPassword=CpasswordField.getText();
			
			if(!(cPassword.equals(password)))
			{
				JOptionPane.showMessageDialog(null,"Password did'nt Match!!");
				CpasswordField.requestFocusInWindow();
				CpasswordField.setText("");
								
						return false;
			}
			System.out.println(password);
			
			address=txtArAddress.getText();
			System.out.println(address);
			
			return true;
	}
}

