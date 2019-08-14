package package_1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.math.*;

public class BidSettings extends JFrame {

	/**
	 * 
	 */
	AuctionGui ag;
	Client c;
	private static final int noOfBids=0;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel txtAmount;
	String textAmount;
	double bidAmount;
	double dCurrentPrice;
	PreparedStatement ps;
	ResultSet rs;
	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public BidSettings() throws ClassNotFoundException, SQLException {
		
	Connection con = AuctionBackend1.connection();
	ag = new AuctionGui();
	
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 450, 310);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	contentPane.setLayout(new BorderLayout(0, 0));
	setContentPane(contentPane);
	
	JPanel placeBidPanel = new JPanel();
	placeBidPanel.setBackground(new Color(255, 255, 255));
	placeBidPanel.setBounds(100,100,450,300);
	contentPane.add(placeBidPanel);
	placeBidPanel.setLayout(null);

  	
  	JLabel lblBidSettings = new JLabel(" Bid Settings ");
  	lblBidSettings.setHorizontalAlignment(SwingConstants.CENTER);
  	lblBidSettings.setFont(new Font("Open Sans Semibold", Font.BOLD, 16));
  	lblBidSettings.setBounds(160, 10, 90, 30);
  	placeBidPanel.add(lblBidSettings);
  	
  	
  	JLabel lblNotice_1 = new JLabel(" Enter the Amount you want to Bid. ");
  	lblNotice_1.setFont(new Font("Open Sans Semibold", Font.PLAIN, 11));
	lblBidSettings.setHorizontalAlignment(SwingConstants.CENTER);
  	lblBidSettings.setFont(new Font("Myanmar Text", Font.BOLD, 12));
  	lblNotice_1.setBounds(125, 45, 200, 20);
  	placeBidPanel.add(lblNotice_1);
  	
  	JLabel lblNotice_2 = new JLabel("1000 will be used to Bid");
  	lblNotice_2.setFont(new Font("Open Sans Semibold", Font.PLAIN, 11));
  	lblNotice_2.setBounds(160, 70, 130, 20);
  	placeBidPanel.add(lblNotice_2);
  	
  	JPanel moneyPanel = new JPanel();
  	moneyPanel.setBackground(UIManager.getColor("Button.foreground"));
  	moneyPanel.setForeground(new Color(80, 80, 80));
  	moneyPanel.setBounds(20, 115, 385, 100);
  	moneyPanel.setLayout(null);
  	placeBidPanel.add(moneyPanel);
  	
  	JLabel Plus_Icon = new JLabel();
    Plus_Icon.setLayout(null);
  	JLabel Minus_Icon = new JLabel();
  	txtAmount = new JLabel();
  	
  	Image img8=new ImageIcon(this.getClass().getResource("/icon-add.png")).getImage();
  	Image img9=new ImageIcon(this.getClass().getResource("/icon-minus.png")).getImage();
  	Image img10=new ImageIcon(this.getClass().getResource("/icon-minus-32-grey.png")).getImage(); 
  	
  	/*System.out.println("ag.getProdId():"+ag.getProdId());
  	ps = con.prepareStatement("select CurrentPrice from bidtable where ProID=?");
  	ps.setString(1, ag.getProdId());
  	rs = ps.executeQuery();
  	if(rs.next())
  	{
  		dCurrentPrice = rs.getDouble("CurrentPrice");
  	}
  	*/
  	//Code to Determine the Number of Digits in Base Price.
  	int n=(int) dCurrentPrice;
  	int i=0;
  	while(n>0)
  	{
  	  n=n/10;
  	  i++;
  	}
  	double basePrice = dCurrentPrice + Math.pow(10,i-1);
  	String strCurrentPrice = Double.toString(basePrice);
  	dCurrentPrice=basePrice;
  	txtAmount.setText(strCurrentPrice);
  	Plus_Icon.addMouseListener(new MouseAdapter() {
  		@Override		
  		public void mouseClicked(MouseEvent e) {
  			
  			//Retrieve the Current Value From The Database.
  			//value in the textField = Current value + 1000;
  			//if(value in textField == current value + 1000)
  	  		int n=(int) dCurrentPrice;
  		    int i=0;
  		  	while(n>0)
  		  	{
  		  	  n=n/10;
  		  	  i++;
  		  	}
  		    dCurrentPrice = dCurrentPrice + Math.pow(10,i-1);
  		  	String strCurrentPrice = Double.toString(dCurrentPrice);
  		  	txtAmount.setText(strCurrentPrice);
  			Minus_Icon.setBackground(new Color(255,0,0));
  			Minus_Icon.setIcon(new ImageIcon(img9));
  		}
  	});
  	Plus_Icon.setBackground(new Color(0,255,0));
  	Plus_Icon.setOpaque(true);
  	Plus_Icon.setIcon(new ImageIcon(img8));
  	Plus_Icon.setBounds(340, 5, 40, 40);
  	moneyPanel.add(Plus_Icon);
  	
  	Minus_Icon.setBackground(Color.WHITE);
  	Minus_Icon.setIcon(new ImageIcon(img10));
  	Minus_Icon.addMouseListener(new MouseAdapter() {
  		@Override
  		public void mouseClicked(MouseEvent e) {
  			int n=(int) dCurrentPrice;
  		    int i=0;
  		  	while(n>0)
  		  	{
  		  	  n=n/10;
  		  	  i++;
  		  	}
  		    dCurrentPrice = dCurrentPrice - Math.pow(10,i-1);
  		  	String strCurrentPrice = Double.toString(dCurrentPrice);
  		  	txtAmount.setText(strCurrentPrice);
  		  	if(dCurrentPrice==basePrice)
  		  	{
  			 Minus_Icon.setBackground(new Color(255,255,255));
  			 Minus_Icon.setIcon(new ImageIcon(img10));
  		  	}
  	
  		}
  	});
  	Minus_Icon.setOpaque(true);
  	Minus_Icon.setBounds(340, 50, 40, 40);
  	moneyPanel.add(Minus_Icon);
  	
  	
  	txtAmount.setBackground(new Color(112, 128, 144));
  	txtAmount.setFont(new Font("Tahoma", Font.BOLD, 18));
  	txtAmount.setBounds(132, 22, 187, 56);
  	moneyPanel.add(txtAmount);
  	
  	JButton btnPlaceBidCancel = new JButton("Cancel");
  	btnPlaceBidCancel.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent arg0) {
  			contentPane.setVisible(false);
  			//Redirect To the MoreInfo Panel.
  		}
  	});
  	btnPlaceBidCancel.setBounds(45,225,110,30);
  	btnPlaceBidCancel.setForeground(new Color(255, 0, 0));
  	btnPlaceBidCancel.setFont(new Font("Tahoma", Font.BOLD, 15));
  	btnPlaceBidCancel.setBackground(new Color(70, 130, 180));
  	placeBidPanel.add(btnPlaceBidCancel);
  	
  	JButton btnPlaceBidPlaceBid = new JButton();
  	btnPlaceBidPlaceBid.setText(" Place Bid ");
  	btnPlaceBidPlaceBid.setForeground(new Color(0, 0, 255));
  	btnPlaceBidPlaceBid.setFont(new Font("Tahoma", Font.BOLD, 15));
  	btnPlaceBidPlaceBid.setBackground(new Color(70, 130, 180));
    btnPlaceBidPlaceBid.addActionListener(new ActionListener() {
    	int yesOrNo;
  		public void actionPerformed(ActionEvent arg0) {
  			
  		   //Add the ProductId to the Usernames table who clicked the "Place Bid" Button and show the Details in the user's MyBids Panel.
  	 
  	        bidAmount=Double.parseDouble(txtAmount.getText());	
  		    yesOrNo = JOptionPane.showConfirmDialog(null, " Place the Bid at "+bidAmount+"?", "Confirmation Message", JOptionPane.YES_NO_OPTION);
			if(yesOrNo==0)
			{
				int bids=0;
				int flag=0;
				int exFlag=0;
				//User Clicked Yes.
				try {
					
					//code to first check if the User has bid Before if so then Update the Current Bid Value.
					ps = con .prepareStatement("select Username from buyertable where ProID=? AND Username=?");
					  ps.setString(1, ag.getProdId());
					  ps.setString(2, c.userName);
					rs=ps.executeQuery();		
					if(rs.next())   //For existing Bidder.
					{
						exFlag=1;
						ps = con.prepareStatement("update buyertable set MyBidPrice=? where UserName=? AND ProID=?");
						  ps.setDouble(1, bidAmount);
						  ps.setString(2, c.userName);
						  ps.setString(3, ag.getProdId());
						  ps.executeUpdate();
						ps=con.prepareStatement("Update bidtable set CurrentPrice=?,UserName=? where ProID=?");//remaining:-to update username.
					      ps.setDouble(1, bidAmount);
					      ps.setString(2, c.userName);
						  ps.setString(3, ag.getProdId());
						  ps.executeUpdate();
						 
						ps.close();	
					}
					else if(exFlag==0)
					{
					   //For First Time Bidder.Check if any one has Bid on the Same Product in Past.
					  ps=con.prepareStatement("select ProID from buyertable where UserName = ?");
					  ps.setString(1, ag.getProdId());
					  rs=ps.executeQuery();
					  if(rs.next())    //If Someone has Bid in Past then just Update the bidtable.
					  {
						  flag=1;
						  ps = con.prepareStatement("insert into buyertable values (?,?,?)");
						    ps.setString(1, ag.getProdId());
						    ps.setString(2, c.userName);
						    ps.setDouble(3, bidAmount);
						    ps.executeUpdate();
						  ps=con.prepareStatement("update bidtable set CurrentPrice=? where ProID=? AND UserName=?");
						    ps.setDouble(1, bidAmount);
						    ps.setString(2, ag.getProdId());
						    ps.setString(3, c.userName);
						  ps.close();		    
					  }
					  else if(flag==0)  //If NoOne has Bid on the Product in Past then insert new Values in the bidtable as well.
					  {
				        ps = con.prepareStatement("insert into buyertable values (?,?,?)");
					      ps.setString(1, ag.getProdId());
					      ps.setString(2, c.userName);
					      ps.setDouble(3, bidAmount);
					      ps.executeUpdate();
				        ps = con.prepareStatement("insert into bidtable(ProID,BasePrice,CurrentPrice,userName) values(?,?,?,?)");
				          ps.setString(1, ag.getProdId());
				          ps.setDouble(2, Double.parseDouble(ag.getProdBasePrice()));
				          ps.setDouble(3, bidAmount);
				          ps.setString(4, c.userName);
				          ps.executeUpdate();
					    ps = con.prepareStatement("select NoOfBids from bidtable where ProID=?");
				 	      ps.setString(1, ag.getProdId());
					      rs=ps.executeQuery();
					    if(rs.next())
					    {
					       bids=rs.getInt("NoOfBids");
   					        bids++; 
					    }
					    ps = con.prepareStatement("insert into bidtable (NoOfBids) values(?) where ProID=?");
					      ps.setInt(1,bids);
					      ps.setString(2, ag.getProdId());
					      ps.executeUpdate();
					       
					      ps.close();
					      rs.close();
					  }
					}
				} catch (SQLException e) {
					System.out.println("SQLException in PlaceBid button in Bid settings.");
					e.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(null, " Thank You for Placing the Bid!!. This Product has been Added to your My Bids Drawer.");
			}
			else
			{
				JOptionPane.showMessageDialog(null, " Bidding Cancelled!!",null,JOptionPane.ERROR_MESSAGE);
			}
  		}
  	});
    btnPlaceBidPlaceBid.setBounds(225, 225, 125, 30);
  	placeBidPanel.add(btnPlaceBidPlaceBid);
  	
}
}
