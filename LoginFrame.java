package package_1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import package_1.Client;
import package_1.ClientController;
import package_1.RegistrationFrame;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel username;
	private JLabel Password;
	private JTextField txtUsername;
	private JPasswordField passwordField;
	public int LoggedIn=0;
	public String userName;
	public String passWord;
	private JButton btnLogin,btnCancel,lblRegister;
	boolean authResult;
	ClientController cc;
	RegistrationFrame rf;
	
	Client c;
	String fname;
	String lname;
	
	 private Connection con ;
	 private PreparedStatement ps;
	 private ResultSet rs;
	    
	/**
	 * Launch the application.
	 */

	public LoginFrame() {
		LoggedIn=0;
		c=new Client();
		try{
			   con = AuctionBackend1.connection();
		   }catch(Exception e){
			  	e.printStackTrace();
	       }
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 430, 350);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(47, 79, 79));
		contentPane.setBounds(100, 100, 430, 350);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 414, 38);
		panel.setBackground(new Color(255, 165, 0));
		contentPane.add(panel);
		
		JLabel lblLoginForm = new JLabel("Login Form");
		lblLoginForm.setHorizontalTextPosition(SwingConstants.LEFT);
		lblLoginForm.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblLoginForm);
		lblLoginForm.setForeground(new Color(255, 255, 255));
		lblLoginForm.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		username = new JLabel("Username:");
		username.setFont(new Font("Tahoma", Font.BOLD, 16));
		username.setBounds(60, 80, 90, 22);
		username.setForeground(Color.WHITE);
		contentPane.add(username);
		username.setVisible(true);
		
		Password = new JLabel("Password:");
		Password.setFont(new Font("Tahoma", Font.BOLD, 16));
		Password.setBounds(60, 138, 90, 22);
		Password.setForeground(Color.WHITE);
		contentPane.add(Password);
		Password.setVisible(true);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtUsername.setForeground(new Color(0, 0, 0));
		txtUsername.setBackground(new Color(112, 128, 144));
		txtUsername.setBounds(156, 80, 156, 22);
		contentPane.add(txtUsername);
		txtUsername.setVisible(true);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 12));
		passwordField.setForeground(new Color(0, 0, 0));
		passwordField.setToolTipText("");
		passwordField.setBackground(new Color(112, 128, 144));
		passwordField.setBounds(156, 138, 156, 22);	
		contentPane.add(passwordField);
		passwordField.setVisible(true);
		
		btnLogin = new JButton("Login");
		btnLogin.setInheritsPopupMenu(true);
		btnLogin.setRequestFocusEnabled(true);
		btnLogin.setRolloverEnabled(false);
		btnLogin.setForeground(new Color(30, 144, 255));
		btnLogin.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		btnLogin.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnLogin.setBackground(new Color(47, 79, 79));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLogin.setBounds(96, 209, 89, 38);
		contentPane.add(btnLogin);
		btnLogin.setVisible(true);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(LoginData())
				{
					
					int login=0;
					try {
						//Check for the UserName and Password in the Database.
						//If() the UserName and Password Matches Open the Respective Users Account and The Auction Frame.
						
						ps = con.prepareStatement("select UserName,Password from registrationtable where UserName=? AND Password=?");
						ps.setString(1,userName);
						ps.setString(2,passWord);
						rs=ps.executeQuery();
						if(rs.next())
							login=1;
						if(login==1)
						{
							//c.setUserName(userName);
							AuctionGui AG=new AuctionGui();
							AG.setVisible(true);
							rege(ae);
							ps.close();
						}
						else
						{
							JOptionPane.showMessageDialog(null,"Invalid Username or Password!!");
						}
					} catch (Exception e) {
						System.out.println("Exception Handled during Authenticating User Input in Login Frame.");
						e.printStackTrace();
					}
				}
			}
			
			
		});
	
		
		btnCancel = new JButton("Cancel");
		btnCancel.setInheritsPopupMenu(true);
		btnCancel.setRolloverEnabled(false);
		btnCancel.setRequestFocusEnabled(true);
		btnCancel.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnCancel.setForeground(new Color(255, 0, 0));
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancel.setBackground(new Color(47, 79, 79));
		btnCancel.setBounds(242, 209, 99, 38);
		contentPane.add(btnCancel);
		btnCancel.setVisible(true);
		
		
		lblRegister = new JButton("Click Here to Regiser ");
		lblRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//RegistrationFrame rf=new RegistrationFrame();
				rf.setVisible(true);
				reg(e);
				
			 
			}
		});
		lblRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblRegister.setFocusTraversalPolicyProvider(true);
		lblRegister.setVisible(true);
	    
		lblRegister.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRegister.setForeground(new Color(240, 248, 255));
		lblRegister.setBackground(new Color(47, 79, 79));
		lblRegister.setBounds(131, 274, 192, 20);
		contentPane.add(lblRegister);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 255));
		separator.setBounds(10, 59, 394, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(new Color(0, 0, 205));
		separator_1.setBounds(10, 189, 394, 2);
		contentPane.add(separator_1);
	
	  rf = new RegistrationFrame();
	}
	

	private void reg(ActionEvent e) {
		this.setVisible(false);
		
	}
	
	private void rege(ActionEvent e) {
		this.setVisible(false);
		
	}
	
	/*String setUserName()
    {return userName;}
	 
	 String setPassword()
	 {return password;}
	 
	 void getUserName(String us)
	 {userName=us;}
	 
	 void getPassword(String pw)
	 {password=pw;}*/
	
	 //Validate the Entered Username and Password.
	 @SuppressWarnings("deprecation")
	boolean LoginData()
	{
		userName=txtUsername.getText();
		c.userName=userName;
		if(userName.equalsIgnoreCase(""))
		{
			JOptionPane.showMessageDialog(null,"Invalid Username!!");
			txtUsername.requestFocusInWindow();
			txtUsername.setText("");
		    userName="";
		    return false;
		}
		passWord=passwordField.getText();
		c.password=passWord;
		if(passWord.equalsIgnoreCase(""))
		{
			JOptionPane.showMessageDialog(null,"Invalid Password!!");
			passwordField.requestFocusInWindow();
			passwordField.setText("");
			passWord="";
		    return false;
		}
		return true;
	}

}
