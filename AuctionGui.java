package package_1;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;

import java.nio.file.*;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.TimerTask;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import net.proteanit.sql.*;

public class AuctionGui extends JFrame{

	//Initialization.
	Integer pl1=50,pl2=700;
	Timer t1 ,t2;
	
	private static final long serialVersionUID = 1L;
	public static String finalTimer;
	private JTabbedPane tabbedPane;
	private JButton btnSearch;
	private JPanel homeTab,trendingBidsTab;
	private JLabel drawerIconMenu,drawerIconArrowTop;
	
	private JPanel navigationPanel;
	private JLabel lblHome,lblAccount,lblSell,lblMyBids,lblCategory,lblSavedBids,lblLogout;
	
	private JPanel contentPane;
	private JTextField searchTextField;
	private JPanel accountPanel,sellPanel,myBidsPanel,savedBidsPanel,moreInfoPanel,myProdPanel;
	private JLabel lblFirstName,lblLastName,lblEmail,lblUsername,lblPassword,lblAddress, lblGender;
	
	private JLabel txtFN, txtLN, txtEmail, txtUsername, passwordField;
	private JTextArea txtAddress;
	private JLabel txtGender;
	private JLabel lblAccountDetails;
	private JButton btnChangePassword;
	private JLabel lblNewPassword,lblNewCPassword;
	private JTextField txtNewPassword,txtNewCPassword;
	private JButton btnSaveChanges;
	private JButton btnCancel;
	
	private JLabel lblSellProduct,lblProductName,lblProductDetails,lblProductImage,lblBasePrice,lblSPCategory,lblTimeLeft,txtProductImage,lblPath,txtPath;
	private JLabel lblspecifyCategory,txtTimeLeft,lblProductId,txtProductId,lblMyBidsTitle,lblSavedBidsTitle;
	private JLabel lblCurrentPrice,txtCurrentPrice;
	private JTextField txtProductName,txtBasePrice,txtspecifyCategory;
	private JTextArea  txtArProductDetails;
	private String[] options;
	private JComboBox<String> comboBoxCategory;
	private JMenuItem categoryCar,categoryElectronics,categoryPainting,categoryStatue,categoryWatch,categoryOthers,empty;
	private String Path,selectCategory;
	private JButton btnBrowse,btnSell,btnSave,btnPlaceBid;
	private JTable myBidsTable,homeHomeTable,homeTrendingTable,savedBidsTable,myProdTable;
	private JScrollPane myBidsScrollPane,savedBidsScrollPane,homeHomeScrollPane,homeTrendingScrollPane,myProdScrollPane;
	private DefaultTableModel myBidsmodel,savedBidsmodel,homeHomemodel,homeTrendingmodel,myProdmodel;
	
	private JLabel lblMoreProductInfo,lblInfoProductName,lblInfoProductDetails,lblInfoProductImage,lblInfoBasePrice,lblInfoSPCategory,txtInfoProductImage,lblInfoPath,txtInfoPath;
	private JLabel txtInfoProductName,txtInfoBasePrice,lblInfoUsername,txtInfoUsername,lblInfoCurrentPrice,txtInfoCurrentPrice,txtInfoSPCategory,lblInfoTimeLeft,txtInfoTimeLeft;
	private JLabel lblInfoProductID,txtInfoProductID;
	private JTextArea  txtInfoArProductDetails;
	private JButton btnInfoBrowse,btnInfoSave,btnInfoPlaceBid;
	private String InfoPath;
	private JLabel lblMyProd,lblMyProdTitle;
	
	Object myBidsrows[][];
	Object savedBidsrows[][];
	Object myProdrows[][];
	
	Pattern p;
    Matcher m;
    boolean b;
    
    private Connection con ;
    private PreparedStatement ps;
    private ResultSet rs;
    FileOutputStream fos;
    Blob blob;
    byte[] bImg;

    
    String productName;
    String productBasePrice;
    String specifyCategory;
    String productDescription;
    String getProID;

     
    String selectCategoryHeader;
    Vector<String> vectorMyBidsHeader;
    Vector<Vector<String>> vectorMyBidsTable;
    
    Vector<String> vectorSaveBidsHeader;
    Vector<Vector<String>> vectorSaveBidsTable;
    
    Vector<String> vectorMyProdHeader;
    Vector<Vector<String>> vectorMyProdTable;
    
    static int interval=10*60;
	static int timeleft;
	static int seconds,minutes=9;
	static int delay = 1000;
	static int period = 1000;
	java.util.Timer timer;
	Client c; 
 	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuctionGui auctionFrame = new AuctionGui();
					auctionFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public AuctionGui() throws SQLException {
		
		try{
			   con = AuctionBackend1.connection();
		   }catch(Exception e){
			  	e.printStackTrace();
	       }
		Client c = new Client();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1386, 742);
		contentPane = new JPanel();
		//contentPane.setBackground(new Color(67, 67, 67));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setPreferredSize(getMaximumSize());
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel headerpanel = new JPanel();
		headerpanel.setBounds(0, 0, 1370, 143);
		headerpanel.setBackground(new Color(38, 33, 99));
		contentPane.add(headerpanel);
		headerpanel.setLayout(null);
	
		/*JLabel lblAuctionSystem = new JLabel("");
		lblAuctionSystem.setBounds(387, 0, 493, 143);
		headerpanel.add(lblAuctionSystem);
		lblAuctionSystem.setHorizontalAlignment(SwingConstants.CENTER);
		Image img=new ImageIcon(this.getClass().getResource("/AuctionLogo2.1.jpg")).getImage();
		lblAuctionSystem.setIcon(new ImageIcon(img));
		lblAuctionSystem.setVisible(false);
		*/
				
		//DropDown Menu for Category Search.
		//--------------------------JTabbed Pane.-----------------------------------------
		
		
				tabbedPane = new JTabbedPane(JTabbedPane.TOP);
				tabbedPane.setBounds(0, 143, 1365, 560);
				//tabbedPane.setEnabledAt(132, true);
				//tabbedPane.setBackgroundAt(132, new Color(67, 67, 67));
				tabbedPane.setVisible(true);
				contentPane.add(tabbedPane);
				
				//-------------------Home Tab----------------------------------------
				
				
				homeTab= new JPanel();
				homeTab.setBorder(null);
				tabbedPane.addTab("   Home                               ", new ImageIcon("G:\\homelogo.png"), homeTab, null);
				JLabel lblBackground = new JLabel("");
				lblBackground.setBounds(0, 0, 1360, 503);
				Image img2=new ImageIcon(this.getClass().getResource("/black_background_wood-wallpaper.jpg")).getImage();
				lblBackground.setIcon(new ImageIcon(img2));
				homeTab.setLayout(null);
				//homeTab.add(lblBackground);
				
				String homeTabcolumns[]={"ProductId","Product Name","Base Price","Category","Maximum Bidder","Current Bid Price","No Of Bids","Image"};

				homeHomeTable = new JTable();
				homeHomeTable.setBounds(0, 44, 1365, 516);
				
				homeHomemodel = new DefaultTableModel();
				homeHomemodel.setColumnIdentifiers(homeTabcolumns);
				homeHomeTable =new JTable(homeHomemodel);
				homeHomeTable.setShowVerticalLines(true);
				homeHomeTable.setShowHorizontalLines(true);
				homeHomeTable.setRowHeight(120);
				
				homeHomeTable.getColumnModel().getColumn(0);
				homeHomeTable.getColumnModel().getColumn(1);
				homeHomeTable.getColumnModel().getColumn(2);
				homeHomeTable.getColumnModel().getColumn(3);
				homeHomeTable.getColumnModel().getColumn(4);
				homeHomeTable.getColumnModel().getColumn(5);
				homeHomeTable.getColumnModel().getColumn(6);
				homeHomeTable.getColumnModel().getColumn(7).setPreferredWidth(150);
				
				homeHomeScrollPane = new JScrollPane(homeHomeTable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				homeHomeScrollPane.setBounds(0, 44, 1365, 516);
				homeHomeScrollPane.setVisible(true);
				homeTab.add(homeHomeScrollPane,BorderLayout.CENTER);
				
				

				//---------------------------------Trending Bids--------------------------------------
					
				trendingBidsTab = new JPanel();
				trendingBidsTab.setBorder(new LineBorder(new Color(0, 0, 0)));
				tabbedPane.addTab("  Trending Bids                          ", new ImageIcon("G:\\SmalltrendingLogo.jpg"), trendingBidsTab, null);
				trendingBidsTab.setLayout(null);
				JLabel lblBackground1 = new JLabel("");
				lblBackground1.setBounds(0, 0, 1360, 503);
				Image img3=new ImageIcon(this.getClass().getResource("/black_background_wood-wallpaper.jpg")).getImage();
				lblBackground1.setIcon(new ImageIcon(img3));
				//trendingBidsTab.add(lblBackground1);
				
				String trendingTabcolumns[]={"ProductId","Product Name","Base Price","Category","Maximum Bidder","Current Bid Price","No Of Bids","Image"};

				homeTrendingTable = new JTable();
				homeTrendingTable.setBounds(0, 44, 1365, 516);
				
				homeTrendingmodel = new DefaultTableModel();
				homeTrendingmodel.setColumnIdentifiers(trendingTabcolumns);
				homeTrendingTable =new JTable(homeTrendingmodel);
				homeTrendingTable.setShowVerticalLines(true);
				homeTrendingTable.setShowHorizontalLines(true);
				homeTrendingTable.setRowHeight(120);
				
				homeTrendingTable.getColumnModel().getColumn(0);
				homeTrendingTable.getColumnModel().getColumn(1);
				homeTrendingTable.getColumnModel().getColumn(2);
				homeTrendingTable.getColumnModel().getColumn(3);
				homeTrendingTable.getColumnModel().getColumn(4);
				homeTrendingTable.getColumnModel().getColumn(5);
				homeTrendingTable.getColumnModel().getColumn(6);
				homeTrendingTable.getColumnModel().getColumn(7).setPreferredWidth(150);
				
				homeTrendingScrollPane = new JScrollPane(homeTrendingTable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				homeTrendingScrollPane.setBounds(0, 44, 1365, 516);
				homeTrendingScrollPane.setVisible(true);
				trendingBidsTab.add(homeTrendingScrollPane,BorderLayout.CENTER);
				
				//-----------------------------------JtabbedPane Ends------------------------------------------
		
		//Search Icon and Search Code
		searchTextField = new JTextField();
		searchTextField.setText("Enter Product ID");
		searchTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(searchTextField.getText().equals("Enter Product ID"))
					searchTextField.setText("");
			}
			public void focusLost(FocusEvent e) {
				if(searchTextField.getText().equals(""))
					searchTextField.setText("Enter Product ID");
			}
		});
		searchTextField.setHorizontalAlignment(SwingConstants.LEFT);
		searchTextField.setFont(new Font("Times New Roman", Font.BOLD, 16));
		searchTextField.setBounds(890, 81, 196, 34);
		searchTextField.setBackground(new Color(112, 128, 144));
		searchTextField.setColumns(10);
		headerpanel.add(searchTextField);
		
		
		//Search Button
		btnSearch = new JButton("");
		
		btnSearch.setIcon(new ImageIcon("G:\\Navigation-Drawer-Icons\\google-web-search-50.png"));
		btnSearch.setBounds(1086, 81, 35, 34);
		headerpanel.add(btnSearch);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		options = new String[7];
		options[0]="";
		options[1]=" Car ";
		options[2]=" Watch ";
		options[3]=" Statue ";
		options[4]=" Painting ";
		options[5]=" Electronics ";
		options[6]=" Others ";
		for(int i=0;i<=6;i++)
		{
			comboBox.addItem(options[i]);
		}
		comboBox.setBackground(new Color(112, 128, 144));
		comboBox.setForeground(new Color(255, 255, 255));
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox.setBounds(1146, 81, 190, 34);
		headerpanel.add(comboBox);
		
		empty=new JMenuItem("");
		comboBox.add(empty);
		
		categoryCar=new JMenuItem(" Car ");
		categoryCar.setRequestFocusEnabled(false);
		categoryCar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		categoryCar.setBackground(new Color(112, 128, 144));
		categoryCar.setForeground(new Color(65, 131, 215));
		comboBox.add(categoryCar);
		
		categoryWatch=new JMenuItem(" Watch ");
		categoryWatch.setRequestFocusEnabled(false);
		categoryWatch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		categoryWatch.setBackground(new Color(112, 128, 144));
		categoryWatch.setForeground(new Color(65, 131, 215));
		comboBox.add(categoryWatch);
		
		categoryStatue=new JMenuItem(" Statue ");
		categoryStatue.setRequestFocusEnabled(false);
		categoryStatue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		categoryStatue.setBackground(new Color(112, 128, 144));
		categoryStatue.setForeground(new Color(65, 131, 215));
		comboBox.add(categoryStatue);
		
		categoryPainting=new JMenuItem(" Painting ");
		categoryPainting.setRequestFocusEnabled(false);
		categoryPainting.setFont(new Font("Tahoma", Font.PLAIN, 14));
		categoryPainting.setBackground(new Color(112, 128, 144));
		categoryPainting.setForeground(new Color(65, 131, 215));
		comboBox.add(categoryPainting);
		
		categoryElectronics=new JMenuItem(" Electronics ");
		categoryElectronics.setRequestFocusEnabled(false);
		categoryElectronics.setFont(new Font("Tahoma", Font.PLAIN, 14));
		categoryElectronics.setBackground(new Color(112, 128, 144));
		categoryElectronics.setForeground(new Color(65, 131, 215));
		comboBox.add(categoryElectronics);
		
		categoryOthers=new JMenuItem(" Others ");
		categoryOthers.setRequestFocusEnabled(false);
		categoryOthers.setFont(new Font("Tahoma", Font.PLAIN, 14));
		categoryOthers.setBackground(new Color(112, 128, 144));
		categoryOthers.setForeground(new Color(65, 131, 215));
		comboBox.add(categoryOthers);
		 
          comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		     
			  btnSearch.addMouseListener(new MouseAdapter() {
			 @Override
	     	   public void mouseClicked(MouseEvent arg0) {
				
				int flag=0;
				System.out.println("In Search Action Listener");
				Vector<String> vectorCombo1 = new Vector<String>();    //5  Columns.
				Vector<String> vectorCombo2 = new Vector<String>();    //3  Columns.
				Vector<String> vectorFinalCombo = new Vector<String>();
				if(comboBox.getSelectedItem().equals(""))
				{
					try {
						System.out.println("text"+searchTextField.getText());
						ps = con.prepareStatement("select ProID,ProName,BasePrice,ProCategory from selltable where ProID=?");
						  ps.setString(1, searchTextField.getText());
						  rs = ps.executeQuery();
						if(rs.next())  //Only One Product becoz Search with ProID is Unique.
						{
							vectorCombo1.add(0,rs.getString("ProID"));
							vectorCombo1.add(1,rs.getString("ProName"));
							vectorCombo1.add(2,rs.getString("BasePrice"));
							vectorCombo1.add(3,rs.getString("ProCategory"));
							
							   //blob=rs.getBlob("Image");
			   		    	   //bImg=blob.getBytes(1, (int)blob.length());
			   		    	   //ImageIcon imgTemp = new ImageIcon(new ImageIcon().getImage(bImg));//Remaining:-retrieving Image from Database.
			   		    	//vectorCombo1.add(4,imgTemp);
			   		    	System.out.println("In 1st if");
						}
						
						ps = con.prepareStatement("select UserName,CurrentPrice,NoOfBids from bidtable where ProID=?");
						  ps.setString(1, searchTextField.getText());
						  rs=ps.executeQuery();	
						if(rs.next())
						{
							vectorCombo2.add(0,rs.getString("UserName"));
							vectorCombo2.add(1,rs.getString("CurrentPrice"));
							vectorCombo2.add(2,rs.getString("NoOfBids"));
							System.out.println("In 2nd if");
						}
						vectorFinalCombo.add(0, vectorCombo1.get(0));   //ProID.
						vectorFinalCombo.add(1, vectorCombo1.get(1));   //Prodname.
						vectorFinalCombo.add(2, vectorCombo1.get(2));   //Base Price.
						vectorFinalCombo.add(3, vectorCombo1.get(3));   //Pro Category.
						vectorFinalCombo.add(4, vectorCombo2.get(0));   //MaxBidderName.
						vectorFinalCombo.add(5, vectorCombo2.get(1));    //MaxBidderPrice.
						vectorFinalCombo.add(6, vectorCombo2.get(2));    //NoOfBids.
						//vectorFinalCombo.add(7, vectorCombo1.get(4));    //Image.
						System.out.println("In before add");
						 homeHomemodel.addRow(new Object[]{vectorFinalCombo.get(0),vectorFinalCombo.get(1),vectorFinalCombo.get(2),vectorFinalCombo.get(3),vectorFinalCombo.get(4),vectorFinalCombo.get(5),vectorFinalCombo.get(6)});
						flag=1;
						
 						
					} catch (Exception e2) {
						System.out.println("Exception found in Search Combobox");
						e2.printStackTrace();
					}
						
				}
			    else if(comboBox.getSelectedItem().equals(" Car "))
			    {		
					selectCategoryHeader=" Car ";
			    }
				else if(comboBox.getSelectedItem().equals(" Watch "))
			    {		
					selectCategoryHeader=" Watch ";
			    }
				else if(comboBox.getSelectedItem().equals(" Statue "))
			    {		
					selectCategoryHeader=" Statue ";
			    }
				else if(comboBox.getSelectedItem().equals(" Statue "))
			    {		
					selectCategoryHeader=" Statue ";
			    }
				else if(comboBox.getSelectedItem().equals(" Electronics "))
			    {		
					selectCategoryHeader=" Electronics ";
			    }
				else if(comboBox.getSelectedItem().equals(" Others "))
			    {		
					selectCategoryHeader=" Others ";
			    }
				if(flag==0)
				{
					int prodCount=0;
					
				try {
				    
						ps = con.prepareStatement("select ProID,ProName,BasePrice,ProCategory from selltable where ProCategory=?");
						   ps.setString(1, selectCategoryHeader);
						   rs = ps.executeQuery();
						while(rs.next())  //Multiple Products will be Selected.
						{
							
							vectorCombo1.add(0,rs.getString("ProID"));
							vectorCombo1.add(1,rs.getString("ProName"));
							vectorCombo1.add(2,rs.getString("BasePrice"));
							vectorCombo1.add(3,rs.getString("ProCategory"));
							
							 //  blob=rs.getBlob("Image");
			   		    	   //bImg=blob.getBytes(1, (int)blob.length());
			   		    	  // ImageIcon imgTemp = new ImageIcon(new ImageIcon().getImage(bImg));//Remaining:-retrieving Image from Database.
			   		    	//vectorCombo1.add(4,imgTemp);
			   		    	prodCount++;
						}
						
				    int i=prodCount-1;
				    int index=0;
			        while(i>=0)
			        {
			    	  index=4*(i);
                        
						ps = con.prepareStatement("select UserName,CurrentPrice,NoOfBids from bidtable where ProID=?");
						  ps.setString(1, vectorCombo1.get(index));
						  rs=ps.executeQuery();	
						if(rs.next())
						{
							vectorCombo2.add(0,rs.getString("UserName"));
							vectorCombo2.add(1,rs.getString("CurrentPrice"));
							vectorCombo2.add(2,rs.getString("NoOfBids"));
							i--;
						}
			        }
			        int k=0,x=0;
		      		 while(prodCount>k)
		      		 { 
		      			vectorFinalCombo.add(x++, vectorCombo1.get(k));   //ProID
		      			vectorFinalCombo.add(x++, vectorCombo1.get(k+1));   //ProName
		      			vectorFinalCombo.add(x++, vectorCombo1.get(k+2));  //Base Price
		      			vectorFinalCombo.add(x++, vectorCombo1.get(k+3));   //ProdCategory.
		      			vectorFinalCombo.add(x++, vectorCombo2.get(k+1));     //Max Bidder.
		      			vectorFinalCombo.add(x++, vectorCombo2.get(k));  //CurrentPrice[Max Bid].
		      			vectorFinalCombo.add(x++, vectorCombo2.get(k+2));  //No Of Bids.
		      			//vectorFinalCombo.add(x++, vectorCombo1.get(k+4));  //Image.
		      	      homeHomemodel.addRow(new Object[]{vectorFinalCombo.get(x-7),vectorFinalCombo.get(x-6),vectorFinalCombo.get(x-5),vectorFinalCombo.get(x-4),vectorFinalCombo.get(x-3),vectorFinalCombo.get(x-2),vectorFinalCombo.get(x-1)});
		                k++;
		                
		      		 }
 						
					} catch (Exception e2) {
						System.out.println("Exception found in Search Combobox");
						e2.printStackTrace();
					}
				}
			}
		  });
	      }
        });
		
		//------------------------Navigation Drawer Starts.-------------------------------------
		
		
		navigationPanel = new JPanel();
		navigationPanel.setBounds(0, 40, 135, 80);
		navigationPanel.setBackground(new Color(38, 33, 99));
		navigationPanel.setLayout(null);
		headerpanel.add(navigationPanel);
		//getRootPane().setGlassPane(navigationPanel);
		//getRootPane().setBounds(0, 40, 135, 660);
	  	 //getRootPane().getGlassPane().setVisible(true);
		
		
		lblHome = new JLabel("Home");
	    lblHome.setHorizontalTextPosition(SwingConstants.CENTER);
	    lblHome.setFocusTraversalPolicyProvider(true);
	    lblHome.setOpaque(true);
	    lblHome.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(65, 105, 225)));
	    lblHome.setBackground(new Color(38, 33, 99 ));
	    lblHome.setForeground(Color.WHITE);
	    lblHome.setFont(new Font("Myanmar Text", Font.BOLD, 14));
		lblHome.setHorizontalAlignment(SwingConstants.CENTER);
		lblHome.setBounds(140, 10, 80, 50);
		navigationPanel.add(lblHome);
		
		
		lblAccount = new JLabel("Account");
		lblAccount.setOpaque(true);
		lblAccount.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(65, 105, 225)));
		lblAccount.setBackground(new Color(38, 33, 99));
		lblAccount.setForeground(Color.WHITE);
		lblAccount.setFont(new Font("Myanmar Text", Font.BOLD, 14));
		lblAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccount.setBounds(230, 10, 80, 50);
		navigationPanel.add(lblAccount);
		
		
	    lblSell = new JLabel("Sell");
	    lblSell.setOpaque(true);
	    lblSell.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(65, 105, 225)));
	    lblSell.setBackground(new Color(38, 33, 99));
	    lblSell.setForeground(Color.WHITE);
	    lblSell.setFont(new Font("Myanmar Text", Font.BOLD, 14));
	    lblSell.setHorizontalAlignment(SwingConstants.CENTER);
		lblSell.setBounds(320, 10, 80, 50);
		navigationPanel.add(lblSell);
		
		lblMyProd = new JLabel("My Prod");
		lblMyProd.setOpaque(true);
		lblMyProd.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(65, 105, 225)));
		lblMyProd.setForeground(Color.WHITE);
		lblMyProd.setBackground(new Color(38, 33, 99));
		lblMyProd.setFont(new Font("Myanmar Text", Font.BOLD, 14));
		lblMyProd.setHorizontalAlignment(SwingConstants.CENTER);
		lblMyProd.setBounds(410, 10, 80, 50);
		navigationPanel.add(lblMyProd);
		
		lblMyBids = new JLabel("My Bids");
		lblMyBids.setOpaque(true);
		lblMyBids.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(65, 105, 225)));
		lblMyBids.setForeground(Color.WHITE);
		lblMyBids.setBackground(new Color(38, 33, 99));
		lblMyBids.setFont(new Font("Myanmar Text", Font.BOLD, 14));
		lblMyBids.setHorizontalAlignment(SwingConstants.CENTER);
		lblMyBids.setBounds(500, 10, 80, 50);
		navigationPanel.add(lblMyBids);
		
		lblSavedBids = new JLabel("Saved Bids");
		lblSavedBids.setOpaque(true);
		lblSavedBids.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(65, 105, 225)));
		lblSavedBids.setForeground(Color.WHITE);
		lblSavedBids.setBackground(new Color(38, 33, 99));
		lblSavedBids.setFont(new Font("Myanmar Text", Font.BOLD, 14));
		lblSavedBids.setHorizontalAlignment(SwingConstants.CENTER);
		lblSavedBids.setBounds(590, 10, 80, 50);
		navigationPanel.add(lblSavedBids);
		
		lblLogout = new JLabel("Logout");
		lblLogout.setOpaque(true);
		lblLogout.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(65, 105, 225)));
		lblLogout.setForeground(Color.WHITE);
		lblLogout.setBackground(new Color(38, 33, 99));
		lblLogout.setFont(new Font("Myanmar Text", Font.BOLD, 14));
		lblLogout.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogout.setBounds(680, 10, 80, 50);
		navigationPanel.add(lblLogout);
		
		
  		
		//Code to Open the Navigation Drawer Effect.
		t1= new Timer(50,new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(pl1 > 900){
					t1.stop();
				}
				else{
					navigationPanel.setSize(pl1,navigationPanel.getHeight());
					pl1+=50;
				}
				
			}
		});
		
		//Code to Close the Navigation Drawer Effect.
		t2= new Timer(50,new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(pl2 < 100){
					t2.stop();
				}
				else{
					navigationPanel.setSize(pl2,navigationPanel.getHeight());
					pl2-=50;
				}
				
			}
		});
		
		//Drawer Icon
	    drawerIconMenu = new JLabel();
	    drawerIconArrowTop = new JLabel();
	    drawerIconArrowTop.setHorizontalAlignment(SwingConstants.CENTER);
	    drawerIconArrowTop.setOpaque(true);
	    drawerIconArrowTop.setBackground(new Color(65, 105, 225));
		drawerIconArrowTop.setBounds(0, 0, 135, 70);
		drawerIconArrowTop.setVisible(false);
		drawerIconMenu.setBounds(0, 0, 135, 70);
		navigationPanel.add(drawerIconArrowTop);
		navigationPanel.add(drawerIconMenu);
		Image img5=new ImageIcon(this.getClass().getResource("/if_Menu3.png")).getImage();
		drawerIconMenu.setIcon(new ImageIcon(img5));
		drawerIconMenu.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		Image img6=new ImageIcon(this.getClass().getResource("/double_arrow_top.png")).getImage();
		
		drawerIconMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			   	   
				   t1.start();
				   pl1=50;
				  // t2.stop();
			  	   drawerIconArrowTop.setIcon(new ImageIcon(img6));
			  	   drawerIconArrowTop.setVisible(true);
			  	   drawerIconMenu.setVisible(false);	
			}
		});
		
		drawerIconArrowTop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//t1.stop();
				t2.start();
				pl2=900;
				drawerIconMenu.setIcon(new ImageIcon(img5));
				drawerIconMenu.setVisible(true);
				drawerIconArrowTop.setVisible(false);
			}
		});
		
		
	
	    //------------------------Navigation Drawer Ends.--------------------------------
		
		
		
		
		//---------Creating Different Panels for Each Navigation Label------------
		
		moreInfoPanel = new JPanel();
		moreInfoPanel.setBounds(0, 143, 1365, 560);
		//moreInfoPanel.setBackground(new Color(67, 67, 67));
		moreInfoPanel.setLayout(null);
		contentPane.add(moreInfoPanel);
		moreInfoPanel.setVisible(false);
		
		accountPanel = new JPanel();
		accountPanel.setBounds(0, 143, 1365, 560);
		//accountPanel.setBackground(new Color(0, 0, 0));
		accountPanel.setLayout(null);
		contentPane.add(accountPanel);
		accountPanel.setVisible(false);
		
		sellPanel = new JPanel();
		sellPanel.setBounds(0, 143, 1365, 560);
		//sellPanel.setBackground(new Color(67, 67, 67));
		sellPanel.setLayout(null);
		contentPane.add(sellPanel);
		sellPanel.setVisible(false);
		
		myProdPanel = new JPanel();
		myProdPanel.setBounds(0, 190, 1365, 560);
		//myProdPanel.setBackground(new Color(67, 67, 67));
		myProdPanel.setLayout(new BorderLayout(0, 0));
		contentPane.add(myProdPanel);
		myProdPanel.setVisible(false);
		
		myBidsPanel = new JPanel();
		myBidsPanel.setBounds(0, 190, 1365, 560);
		//myBidsPanel.setBackground(new Color(67, 67, 67));
		myBidsPanel.setLayout(new BorderLayout(0, 0));
		contentPane.add(myBidsPanel);
		myBidsPanel.setVisible(false);
		
		savedBidsPanel = new JPanel();
		savedBidsPanel.setBounds(0, 190, 1365, 560);
		//savedBidsPanel.setBackground(new Color(67, 67, 67));
		savedBidsPanel.setLayout(null);
		contentPane.add(savedBidsPanel);
		savedBidsPanel.setVisible(false);	
	
		
		
		//-----------------------------Contents for Account Panel-------------------------
	
		
		lblAccountDetails = new JLabel("Account Details");
		lblAccountDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccountDetails.setFont(new Font("Myanmar Text", Font.BOLD, 18));
		lblAccountDetails.setBounds(560, 5, 150, 30);
		accountPanel.add(lblAccountDetails);
		
		JSeparator separatorAP_1 = new JSeparator();
		separatorAP_1.setBackground(new Color(0, 0, 205));
		separatorAP_1.setBounds(15, 45, 1300, 3);
		accountPanel.add(separatorAP_1);
		
		
		//Retrieve The Info from the Database to Display all the Info of the Logged in User.
		//String firstName = null,lastName= null,gender= null,email= null,address= null,userName= null,password = null;
		ps = con.prepareStatement("select * from registrationtable");
		rs=ps.executeQuery();
		while(rs.next())
		{
			c.firstName=rs.getString("FirstName");
			c.lastName=rs.getString("LastName");
			c.gender=rs.getString("Gender");
			c.email=rs.getString("Email");
			c.address=rs.getString("Address");
			c.userName=rs.getString("UserName");
			c.password=rs.getString("Password");
		}
			
		lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFirstName.setForeground(new Color(0, 0, 0));
		lblFirstName.setBounds(35, 75, 110, 25);
		accountPanel.add(lblFirstName);
		
		txtFN = new JLabel();
		txtFN.setText(c.firstName);
		txtFN.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtFN.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtFN.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		txtFN.setBackground(new Color(112, 128, 144));
		txtFN.setBounds(155, 75, 150, 25);
		accountPanel.add(txtFN);
		
	    lblLastName = new JLabel("Last Name:");
	    lblLastName.setForeground(new Color(0, 0, 0));
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLastName.setBounds(455, 75, 110, 25);
		accountPanel.add(lblLastName);
		
		txtLN = new JLabel();
		txtLN.setText(c.lastName);
		txtLN.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtLN.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		txtLN.setBackground(new Color(112, 128, 144));
		txtLN.setBounds(575, 75, 150, 25);
		accountPanel.add(txtLN);
		
		lblGender = new JLabel("Gender:");
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGender.setForeground(new Color(0, 0, 0));
		lblGender.setBackground(new Color(47, 79, 79));
		lblGender.setBounds(825, 75, 110, 25);
		accountPanel.add(lblGender);
		
		txtGender = new JLabel();
		txtGender.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		txtGender.setText(c.gender);
		txtGender.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtGender.setBackground(new Color(112, 128, 144));
		txtGender.setBounds(935, 75, 150, 25);
		accountPanel.add(txtGender);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setForeground(new Color(0, 0, 0));
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEmail.setBounds(35, 150, 110, 25);
		accountPanel.add(lblEmail);
		
		txtEmail = new JLabel();
		txtEmail.setText(c.email);
		txtEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtEmail.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		txtEmail.setBackground(new Color(112, 128, 144));
		txtEmail.setBounds(155, 150, 150, 25);
		accountPanel.add(txtEmail);
		
		lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUsername.setForeground(new Color(0, 0, 0));
		lblUsername.setBounds(455, 150, 110, 25);
		accountPanel.add(lblUsername);
		
		txtUsername = new JLabel();
		txtUsername.setText(c.userName);
		txtUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtUsername.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		txtUsername.setBackground(new Color(112, 128, 144));
		txtUsername.setBounds(575, 150, 150, 25);
		accountPanel.add(txtUsername);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassword.setForeground(new Color(0, 0, 0));
		lblPassword.setBounds(825, 150, 110, 25);
		accountPanel.add(lblPassword);
		
		passwordField = new JLabel();
		passwordField.setText(c.password);
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 14));
		passwordField.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		passwordField.setBackground(new Color(112, 128, 144));
		passwordField.setBounds(935, 150, 150, 25);
		accountPanel.add(passwordField, BorderLayout.CENTER);
		
		lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAddress.setForeground(Color.BLACK);
		lblAddress.setBounds(455, 225, 110, 25);
		accountPanel.add(lblAddress);
		
		txtAddress = new JTextArea();
		txtAddress.setText(c.address);
		txtAddress.setLineWrap(true);
		txtAddress.setFocusTraversalPolicyProvider(true);
		txtAddress.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtAddress.setForeground(new Color(255, 255, 255));
		txtAddress.setBackground(new Color(112, 128, 144));
		txtAddress.setBounds(575, 225, 350, 75);
		accountPanel.add(txtAddress);
		
		btnChangePassword = new JButton("Change Password");
		btnChangePassword.setForeground(new Color(30, 144, 255));
		btnChangePassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnChangePassword.setBackground(new Color(112, 128, 144));
		btnChangePassword.setBounds(575, 325, 200, 30);
		accountPanel.add(btnChangePassword);
		
		
		JSeparator separatorAP_2 = new JSeparator();
		separatorAP_2.setBackground(new Color(0, 0, 205));
		separatorAP_2.setBounds(15, 360, 1300, 3);
		accountPanel.add(separatorAP_2);
        
		
		lblNewPassword = new JLabel("Enter New Password:");
		lblNewPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewPassword.setForeground(Color.BLACK);
		lblNewPassword.setBounds(155, 375, 185, 30);
		accountPanel.add(lblNewPassword);
		
		lblNewCPassword = new JLabel("Confirm Password:");
		lblNewCPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewCPassword.setForeground(Color.black);
		lblNewCPassword.setBounds(650, 375, 185, 30);
		accountPanel.add(lblNewCPassword);
		
		
		txtNewPassword = new JTextField();
		txtNewPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtNewPassword.setBackground(new Color(112, 128, 144));
		txtNewPassword.setBounds(350, 375, 150, 30);
		accountPanel.add(txtNewPassword);
		
		txtNewCPassword = new JTextField();
		txtNewCPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtNewCPassword.setBackground(new Color(112, 128, 144));
		txtNewCPassword.setBounds(845, 375, 150, 30);
		accountPanel.add(txtNewCPassword);
		
		btnSaveChanges = new JButton("Save Changes");
		btnSaveChanges.setForeground(new Color(30, 144, 255));
		btnSaveChanges.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSaveChanges.setBackground(new Color(47, 79, 79));
		btnSaveChanges.setBounds(350, 450, 150, 40);
		accountPanel.add(btnSaveChanges);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setForeground(new Color(255, 0, 0));
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCancel.setBackground(new Color(47, 79, 79));
		btnCancel.setBounds(650, 450, 100, 40);
		accountPanel.add(btnCancel);
		
		lblNewPassword.setVisible(false);
		lblNewCPassword.setVisible(false);
		txtNewPassword.setVisible(false);
		txtNewCPassword.setVisible(false);
		btnSaveChanges.setVisible(false);
		btnCancel.setVisible(false);
		
		                    //--------Internal Account Panel Mouse Listeners-------------
		
		
		btnChangePassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				lblNewPassword.setVisible(true);
				lblNewCPassword.setVisible(true);
				txtNewPassword.setVisible(true);
				txtNewCPassword.setVisible(true);
				String pass = txtNewPassword.getText().toString();
				btnSaveChanges.setVisible(true);
				btnSaveChanges.addMouseListener(new MouseAdapter() {
					//Database Connectivity to Change the Password of the Current User.
					@Override
					public void mouseClicked(MouseEvent e) {
					try {
						ps = con.prepareStatement("Update registrationtable set Password=? where UserName=?");
						ps.setString(1,pass);
						ps.setString(2, c.userName);
						ps.executeUpdate();
						ps.close();
						JOptionPane.showMessageDialog(null,"Your Password has been Successfully Updated!!"+"Username:"+c.userName+"  Password:"+pass);
					} catch (SQLException e1) {
						System.out.println("SQLException in save button of Account Panel");
						e1.printStackTrace();
					}
									
					}
				});
				btnCancel.setVisible(true);
				btnCancel.addMouseListener(new MouseAdapter() {
					
					//Shuts the Password Label.
					@Override
					public void mouseClicked(MouseEvent e) {
						lblNewPassword.setVisible(false);
						lblNewCPassword.setVisible(false);
						txtNewPassword.setVisible(false);
						txtNewCPassword.setVisible(false);
						btnSaveChanges.setVisible(false);
						btnCancel.setVisible(false);
					}
				});
				
			}
		});
		
		
		//-----------------------------Contents for Selling Panel------------------
		
		
		lblSellProduct = new JLabel("Sell Product");
		lblSellProduct.setHorizontalAlignment(SwingConstants.CENTER);
		lblSellProduct.setFont(new Font("Myanmar Text", Font.BOLD, 16));
		lblSellProduct.setBounds(560, 5, 150, 30);
		sellPanel.add(lblSellProduct);
		
		JSeparator separatorSP_1 = new JSeparator();
		separatorSP_1.setBackground(new Color(0, 0, 205));
		separatorSP_1.setBounds(15, 45, 1300, 3);
		sellPanel.add(separatorSP_1);
		
		lblProductName = new JLabel();
		lblProductName.setText(" Product Name: ");
		lblProductName.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		lblProductName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProductName.setBounds(50, 75, 135, 25);
		sellPanel.add(lblProductName);
		
		txtProductName = new JTextField();
		txtProductName.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtProductName.setForeground(Color.WHITE);
		txtProductName.setBackground(new Color(112, 128, 144));
		txtProductName.setBounds(195, 75, 150, 25);
		sellPanel.add(txtProductName);
		
		lblBasePrice = new JLabel();
		lblBasePrice.setText(" Base Price: ");
		lblBasePrice.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblBasePrice.setBounds(50, 200, 110, 25);
		sellPanel.add(lblBasePrice);
		
		txtBasePrice = new JTextField();
		txtBasePrice.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtBasePrice.setForeground(Color.WHITE);
		txtBasePrice.setBackground(new Color(112, 128, 144));
		txtBasePrice.setBounds(170, 200, 130, 25);
		sellPanel.add(txtBasePrice);
		
		lblSPCategory = new JLabel();
		lblSPCategory.setText(" Category :");
		lblSPCategory.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSPCategory.setBounds(50, 300, 110, 25);
		sellPanel.add(lblSPCategory);
		
		lblProductDetails =new JLabel();
		lblProductDetails.setText(" Product Details: ");
		lblProductDetails.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProductDetails.setBounds(400, 75, 150, 25);
		sellPanel.add(lblProductDetails);
		
		txtArProductDetails = new JTextArea();
		txtArProductDetails.setLineWrap(true);
		txtArProductDetails.setFocusTraversalPolicyProvider(true);
		txtArProductDetails.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtArProductDetails.setForeground(Color.WHITE);
		txtArProductDetails.setBackground(new Color(112, 128, 144));
		txtArProductDetails.setBounds(400, 110, 200, 200);
		sellPanel.add(txtArProductDetails);
			
		lblProductImage = new JLabel();
		lblProductImage.setText(" Product Image ");
		lblProductImage.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProductImage.setBounds(650, 75, 150, 25);
		sellPanel.add(lblProductImage);
		
		txtProductImage = new JLabel();
		txtProductImage.setBounds(650, 110, 700, 365);
		txtProductImage.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		sellPanel.add(txtProductImage);
		
		lblPath = new JLabel();
		lblPath.setText(" PATH: ");
		lblPath.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPath.setBounds(528, 510, 75, 25);
		sellPanel.add(lblPath);
		
		txtPath = new JLabel();
		txtPath.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtPath.setBackground(new Color(112, 128, 144));
		txtPath.setForeground(Color.BLACK);
		txtPath.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		txtPath.setBounds(600, 510, 450, 25);
		sellPanel.add(txtPath);
		
		
		 //--------Code for Selecting Image from the Directory.
		
		
		 btnBrowse = new JButton();
		 btnBrowse.setText(" Browse ");
		 btnBrowse.setForeground(new Color(30, 144, 255));
		 btnBrowse.setFont(new Font("Tahoma", Font.BOLD, 16));
		 btnBrowse.setBackground(new Color(47, 79, 79));
		 btnBrowse.setBounds(1200, 475, 150, 40);
		 sellPanel.add(btnBrowse);
		 btnBrowse.addMouseListener(new MouseAdapter() {
			 	@Override
			 	public void mouseClicked(MouseEvent arg0) {
			 		JFileChooser file = new JFileChooser();
			 		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","png","jpeg");
			 		file.addChoosableFileFilter(filter);
			 		int result = file.showSaveDialog(null);
			 		if(result==JFileChooser.APPROVE_OPTION)
			 		{
			 			File selectedFile= file.getSelectedFile();
			 			Path = selectedFile.getAbsolutePath();
			 			System.out.println("Path: "+Path);
			 			txtPath.setText(Path);
			 			ImageIcon icon = new ImageIcon(Path);
			 			txtProductImage.setIcon(icon);
			 		}
			 		else if(result == JFileChooser.CANCEL_OPTION)
			 		{
			 			JOptionPane.showMessageDialog(null, " No Path Found ");
			 		}
			 	}
			 });
		 
		//---------Adding Items to ComboBox
		
		
		comboBoxCategory = new JComboBox<String>();
		options[0]=" ";
		options[1]=" Car ";
		options[2]=" Watch ";
		options[3]=" Statue ";
		options[4]=" Painting ";
		options[5]=" Electronics ";
		options[6]=" Others ";
		for(int i=0;i<=6;i++)
		{
			comboBoxCategory.addItem(options[i]);
		}
		
		comboBoxCategory.setBackground(new Color(112, 128, 144));
		comboBoxCategory.setForeground(new Color(255, 255, 255));
		comboBoxCategory.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBoxCategory.setBounds(170, 300, 140, 25);
		sellPanel.add(comboBoxCategory);		
		
		empty=new JMenuItem("");
		comboBoxCategory.add(empty);
		
		categoryCar=new JMenuItem(" Car ");
		categoryCar.setRequestFocusEnabled(false);
		categoryCar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		categoryCar.setBackground(new Color(112, 128, 144));
		categoryCar.setForeground(new Color(65, 131, 215));
		comboBoxCategory.add(categoryCar);
		
		categoryWatch=new JMenuItem(" Watch ");
		categoryWatch.setRequestFocusEnabled(false);
		categoryWatch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		categoryWatch.setBackground(new Color(112, 128, 144));
		categoryWatch.setForeground(new Color(65, 131, 215));
		comboBoxCategory.add(categoryWatch);
		
		categoryStatue=new JMenuItem(" Statue ");
		categoryStatue.setRequestFocusEnabled(false);
		categoryStatue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		categoryStatue.setBackground(new Color(112, 128, 144));
		categoryStatue.setForeground(new Color(65, 131, 215));
		comboBoxCategory.add(categoryStatue);
		
		categoryPainting=new JMenuItem(" Painting ");
		categoryPainting.setRequestFocusEnabled(false);
		categoryPainting.setFont(new Font("Tahoma", Font.PLAIN, 14));
		categoryPainting.setBackground(new Color(112, 128, 144));
		categoryPainting.setForeground(new Color(65, 131, 215));
		comboBoxCategory.add(categoryPainting);
		
		categoryElectronics=new JMenuItem(" Electronics ");
		categoryElectronics.setRequestFocusEnabled(false);
		categoryElectronics.setFont(new Font("Tahoma", Font.PLAIN, 14));
		categoryElectronics.setBackground(new Color(112, 128, 144));
		categoryElectronics.setForeground(new Color(65, 131, 215));
		comboBoxCategory.add(categoryElectronics);
		
		categoryOthers=new JMenuItem(" Others ");
		categoryOthers.setRequestFocusEnabled(false);
		categoryOthers.setFont(new Font("Tahoma", Font.PLAIN, 14));
		categoryOthers.setBackground(new Color(112, 128, 144));
		categoryOthers.setForeground(new Color(65, 131, 215));
		comboBoxCategory.add(categoryOthers);
		
	    lblspecifyCategory = new JLabel();
	    lblspecifyCategory.setText("Specify Your Category:");
	    lblspecifyCategory.setFont(new Font("Tahoma", Font.BOLD, 16));
	    lblspecifyCategory.setBounds(300, 75, 110, 25);
	    lblspecifyCategory.setVisible(false);
		sellPanel.add(lblspecifyCategory);
		
		txtspecifyCategory = new JTextField();
		txtspecifyCategory.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtspecifyCategory.setBackground(new Color(112, 128, 144));
		txtspecifyCategory.setBounds(420, 75, 150, 25);
		txtspecifyCategory.setVisible(false);
		sellPanel.add(txtspecifyCategory);
		
		
		comboBoxCategory.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(comboBoxCategory.getSelectedItem().equals(" Car "))
			    {		
					selectCategory=" Car ";
			    }
				else if(comboBoxCategory.getSelectedItem().equals(" Watch "))
			    {		
					selectCategory=" Watch ";
			    }
				else if(comboBoxCategory.getSelectedItem().equals(" Statue "))
			    {		
					selectCategory=" Statue ";
			    }
				else if(comboBoxCategory.getSelectedItem().equals(" Statue "))
			    {		
					selectCategory=" Statue ";
			    }
				else if(comboBoxCategory.getSelectedItem().equals(" Electronics "))
			    {		
					selectCategory=" Electronics ";
			    }
				else if(comboBoxCategory.getSelectedItem().equals(" Others "))
			    {		
					lblspecifyCategory.setVisible(true);
					txtspecifyCategory.setVisible(true);
					selectCategory = txtspecifyCategory.getText();
			    }
				else{
					JOptionPane.showMessageDialog(null, "Please Select a Category",null, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnSell = new JButton();
		btnSell.setText(" Sell ");
		btnSell.setForeground(new Color(30, 144, 255));
		btnSell.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSell.setBackground(new Color(47, 79, 79));
		btnSell.setBounds(300, 450, 150, 40);
		sellPanel.add(btnSell);
		 
		btnSell.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			if(ValidateInfo())
			 {
			int yesOrNo=JOptionPane.showConfirmDialog(null, " Are you Sure you want to Place the Product for Bidding ?", "Confirmation Message", JOptionPane.YES_NO_OPTION);
			if(yesOrNo==0){
				
				//If User Clicks Yes Option.
				
			    lblProductId = new JLabel();
				lblProductId.setText("Product Id (AutoGenerated):");
				
				Random rand = new Random(); 
				int value = rand.nextInt(5000); 
				txtProductId = new JLabel();
				String str=Integer.toString(value);
				txtProductId.setText(str);
				
				lblTimeLeft= new JLabel();
			    txtTimeLeft = new JLabel();
				lblTimeLeft.setText("Time Left (For Closed Bidding): ");
				
				 timer = new java.util.Timer();
				   timer.scheduleAtFixedRate(new TimerTask() {

				        public void run() {
				            timeleft=setInterval();
				            seconds = timeleft%60;
				            if(seconds == 0)
				            	minutes--;
				            if((seconds/10)==0)
				        	 {  finalTimer=String.format("0%d:0%d", minutes,seconds);
				            	txtTimeLeft.setText("0"+Integer.toString(minutes) + " : 0"+Integer.toString(seconds) );
				        	  
				        	 }
				            else
				            {   finalTimer=String.format("0%d:%d", minutes,seconds);
				            	txtTimeLeft.setText("0"+Integer.toString(minutes) + " : "+Integer.toString(seconds) );
				             }
				            }
				    }, delay, period);
				
				
			    Object complexMsg[] = { lblProductId, txtProductId, lblTimeLeft,txtTimeLeft};

			    JOptionPane optionPane = new JOptionPane();
			    optionPane.setMessage(complexMsg);
			    optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
			    JDialog dialog = optionPane.createDialog(null, " Note: ");
			    dialog.setVisible(true);
				
			    //Store the ProductId and the TimeLeft in the Database.
				//Add the Rest info to the database.
				//Add selectCategory String to the Database.
			    //Add image Path in the Database.
			     File imgfile = new File(txtPath.getText());
			     FileInputStream fis = null;
				try {
					fis = new FileInputStream(imgfile);
				} catch (FileNotFoundException e1) {
					System.out.println("Excecption in File Input Stream ");
					e1.printStackTrace();
				} 
			    try {
					ps= con.prepareStatement("insert into selltable values (?,?,?,?,?,?,?)");
					ps.setString(1,txtProductName.getText());
					ps.setString(2, txtArProductDetails.getText());
					ps.setString(3, txtProductId.getText());
					ps.setDouble(4, Double.parseDouble(txtBasePrice.getText()));
					ps.setString(5, selectCategory);
					ps.setString(6, c.userName);
					ps.setBinaryStream(7, (InputStream)fis , (int)imgfile.length() );
					ps.executeUpdate();
					ps = con.prepareStatement(" insert into bidtable (ProID,BasePrice,CurrentPrice,NoOfBids) values (?,?,?,?)");
					ps.setString(1, txtProductId.getText());
					ps.setDouble(2, Double.parseDouble(txtBasePrice.getText()));
					ps.setDouble(3, Double.parseDouble(txtBasePrice.getText()));
					ps.setInt(4, 0);
					ps.executeUpdate();
					ps.close();
					
				} catch (SQLException e) {
					System.out.println("SQLException in sell button of Sell Panel");
					e.printStackTrace();
				}
				
			}
			else{
				txtProductName.setText("");
				txtBasePrice.setText("");
				txtArProductDetails.setText("");
				txtProductImage.setText("");
				selectCategory="";
				txtspecifyCategory.setText("");
				txtPath.setText("");
			   }
			}
		  }
		});
		
		
		
		//-----------------------------Contents for MyBids Panel------------------
		
		lblMyBidsTitle = new JLabel(" My Bids ");
		lblMyBidsTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblMyBidsTitle.setFont(new Font("Myanmar Text", Font.BOLD, 16));
		lblMyBidsTitle.setBounds(560, 150, 150, 30);
		contentPane.add(lblMyBidsTitle);
		lblMyBidsTitle.setVisible(false);
		
		
		//Database Connectivity to Retrieve the User Bidding Info from the Database.
				
		//-----------------------------Contents for  SavedBids Panel------------------
		
		lblSavedBidsTitle = new JLabel(" Saved Bids ");
		lblSavedBidsTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblSavedBidsTitle.setFont(new Font("Myanmar Text", Font.BOLD, 16));
		lblSavedBidsTitle.setBounds(560, 150, 150, 30);
		contentPane.add(lblSavedBidsTitle);
		lblSavedBidsTitle.setVisible(false);
		
		//Database Connectivity to Retrieve the User Saved Bidding Info from the Database.
		
		//Database Connectivity to Retrieve the User Bidding Info from the Database.
		
	    //-----------------------------Contents for  MyProducts Panel------------------
				
		lblMyProdTitle = new JLabel(" My Products ");
		lblMyProdTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblMyProdTitle.setFont(new Font("Myanmar Text", Font.BOLD, 16));
	    lblMyProdTitle.setBounds(560, 150, 150, 30);
		contentPane.add(lblMyProdTitle);
		lblMyProdTitle.setVisible(false);
				
		//Database Connectivity to Retrieve the Users Product Bidding Info from the Database.
		
		
		//-----------------------------Contents for More Info Panel--------------------
		
		//Opens when A particular Row from the table is Clicked.
		
		
		lblMoreProductInfo = new JLabel("Product Details");
		lblMoreProductInfo.setBounds(560, 5, 150, 30);
		lblMoreProductInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoreProductInfo.setFont(new Font("Myanmar Text", Font.BOLD, 16));
		moreInfoPanel.add(lblMoreProductInfo);
		
		JSeparator separatorSP_2 = new JSeparator();
		separatorSP_2.setBackground(new Color(0, 0, 205));
		separatorSP_2.setBounds(15, 45, 1300, 3);
		moreInfoPanel.add(separatorSP_2);
		
		lblInfoProductName = new JLabel();
		lblInfoProductName.setText(" Product Name: ");
		lblInfoProductName.setBounds(50, 75, 135, 25);
		lblInfoProductName.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		lblInfoProductName.setFont(new Font("Tahoma", Font.BOLD, 16));
		moreInfoPanel.add(lblInfoProductName);
		
		txtInfoProductName = new JLabel();
		txtInfoProductName.setBounds(195, 75, 140, 25);
		txtInfoProductName.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtInfoProductName.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		txtInfoProductName.setBackground(new Color(112, 128, 144));
		moreInfoPanel.add(txtInfoProductName);
		
		lblInfoProductID = new JLabel();
		lblInfoProductID.setText(" Product ID: ");
		lblInfoProductID.setBounds(50, 130, 135, 25);
		lblInfoProductID.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		lblInfoProductID.setFont(new Font("Tahoma", Font.BOLD, 16));
		moreInfoPanel.add(lblInfoProductID);
		
		txtInfoProductID = new JLabel();
		txtInfoProductID.setBounds(195, 130, 140, 25);
		txtInfoProductID.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtInfoProductID.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		txtInfoProductID.setBackground(new Color(112, 128, 144));
		moreInfoPanel.add(txtInfoProductID);
		
		
		lblInfoBasePrice = new JLabel();
		lblInfoBasePrice.setText(" Base Price: ");
		lblInfoBasePrice.setBounds(50, 185, 110, 25);
		lblInfoBasePrice.setFont(new Font("Tahoma", Font.BOLD, 16));
		moreInfoPanel.add(lblInfoBasePrice);
		
		txtInfoBasePrice = new JLabel();
		txtInfoBasePrice.setBounds(170, 185, 130, 25);
		txtInfoBasePrice.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtInfoBasePrice.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		txtInfoBasePrice.setBackground(new Color(112, 128, 144));
		moreInfoPanel.add(txtInfoBasePrice);
		
		lblInfoCurrentPrice = new JLabel();
		lblInfoCurrentPrice.setText(" Current Bid Price: ");
		lblInfoCurrentPrice.setBounds(10, 240, 175, 25);
		lblInfoCurrentPrice.setFont(new Font("Tahoma", Font.BOLD, 16));
		moreInfoPanel.add(lblInfoCurrentPrice);
		
		txtInfoCurrentPrice = new JLabel();
		txtInfoCurrentPrice.setBounds(195, 240, 130, 25);
		txtInfoCurrentPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtInfoCurrentPrice.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		txtInfoCurrentPrice.setBackground(new Color(112, 128, 144));
		moreInfoPanel.add(txtInfoCurrentPrice);
		
		lblInfoSPCategory = new JLabel();
		lblInfoSPCategory.setText(" Category : ");
		lblInfoSPCategory.setBounds(50, 300, 110, 25);
		lblInfoSPCategory.setFont(new Font("Tahoma", Font.BOLD, 16));
		moreInfoPanel.add(lblInfoSPCategory);
		
		txtInfoSPCategory = new JLabel();
		txtInfoSPCategory.setBounds(170, 300, 140, 25);
		txtInfoSPCategory.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtInfoSPCategory.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		moreInfoPanel.add(txtInfoSPCategory);
		
		lblInfoUsername = new JLabel();
		lblInfoUsername.setText(" Username : ");
		lblInfoUsername.setBounds(50, 375, 110, 25);
		lblInfoUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
		moreInfoPanel.add(lblInfoUsername);
		
		txtInfoUsername = new JLabel();
		txtInfoUsername.setBounds(170, 375, 130, 25);
		txtInfoUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtInfoUsername.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		txtInfoUsername.setBackground(new Color(112, 128, 144));
		moreInfoPanel.add(txtInfoUsername);
		
		
		 lblInfoProductDetails = new JLabel();
		 lblInfoProductDetails.setBounds(350, 75, 150, 25);
		 lblInfoProductDetails.setText(" Product Info.");
		 lblInfoProductDetails.setFont(new Font("Tahoma", Font.BOLD, 16));
		 moreInfoPanel.add(lblInfoProductDetails);
		 
		txtInfoArProductDetails = new JTextArea();
		txtInfoArProductDetails.setLineWrap(true);
		txtInfoArProductDetails.setFocusTraversalPolicyProvider(true);
		txtInfoArProductDetails.setFont(new Font("Tahoma", Font.ROMAN_BASELINE, 14));
		txtInfoArProductDetails.setForeground(new Color(255, 255, 255));
		txtInfoArProductDetails.setBackground(new Color(112, 128, 144));
		txtInfoArProductDetails.setBounds(350, 110, 200, 200);
		moreInfoPanel.add(txtInfoArProductDetails);
			
		lblInfoProductImage = new JLabel();
		lblInfoProductImage.setText(" Product Image ");
		lblInfoProductImage.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblInfoProductImage.setBounds(720, 75, 150, 25);
		moreInfoPanel.add(lblInfoProductImage);
		
		txtInfoProductImage = new JLabel();
		txtInfoProductImage.setBounds(600, 110, 700, 365);
		txtInfoProductImage.setBackground(new Color(112, 128, 144));
		txtInfoProductImage.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		moreInfoPanel.add(txtInfoProductImage);
		
		lblInfoPath = new JLabel();
		lblInfoPath.setText(" PATH: ");
		lblInfoPath.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblInfoPath.setBounds(528, 510, 75, 25);
		moreInfoPanel.add(lblInfoPath);
		
		txtInfoPath = new JLabel();
		txtInfoPath.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtInfoPath.setBackground(new Color(112, 128, 144));
		txtInfoPath.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		txtInfoPath.setBounds(600, 510, 450, 25);
		moreInfoPanel.add(txtInfoPath);
		
		
		lblInfoTimeLeft = new JLabel(" Time Left(For Closed Bidding) ");
		lblInfoTimeLeft.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblInfoTimeLeft.setBounds(340, 320, 220, 25);
		moreInfoPanel.add(lblInfoTimeLeft);
		
		txtInfoTimeLeft = new JLabel();
		txtInfoTimeLeft.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtInfoTimeLeft.setBackground(new Color(112, 128, 144));
		txtInfoTimeLeft.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		txtInfoTimeLeft.setBounds(350, 355, 150, 25);
		moreInfoPanel.add(txtInfoTimeLeft);
		
		
		btnInfoSave = new JButton();
		btnInfoSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				//Add the ProductId to the Username Table who clicked the "Save" Button and then Show the Details of the Product in that user's MySavedBids Panel.
				try {
					ps = con.prepareStatement("insert into savebidstable values (?,?) ");
					ps.setString(1,txtInfoProductID.getText());
					ps.setString(2,c.userName);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "This Product has been Added to your Saved Bids Drawer."); 
				} catch (SQLException e) {
					System.out.println("Exception in btnInfoSave");
					e.printStackTrace();
				}
				
			}
		});
		btnInfoSave.setBounds(250, 445, 110, 30);
		btnInfoSave.setText(" Save ");
		btnInfoSave.setForeground(new Color(0, 0, 0));
		btnInfoSave.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnInfoSave.setBackground(new Color(255, 140, 0));
		 moreInfoPanel.add(btnInfoSave);
		 
		 
		 //A separate JFrame for User to Place the Bid.
	 
		btnInfoPlaceBid = new JButton();
		btnInfoPlaceBid.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
			try {
				BidSettings  bs = new BidSettings();
				bs.setVisible(true);
			} catch (Exception e1) {
				System.out.println("Exception in btnInfoPlaceBid");  //Open the Bidding Frame.
				e1.printStackTrace();
			}            
			}
		});
		btnInfoPlaceBid.setBounds(430, 445, 140, 30);
		btnInfoPlaceBid.setText(" Place Bid ");
		btnInfoPlaceBid.setForeground(new Color(192, 192, 192));
		btnInfoPlaceBid.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnInfoPlaceBid.setBackground(new Color(65, 105, 225));
	    moreInfoPanel.add(btnInfoPlaceBid);
		 
	    
	    
		//-----------------------------------All Mouse Listeners--------------------------------------
	   
	    
	    lblHome.addMouseListener(new MouseAdapter() {
			@Override
			//---------Home Panel [JTabbedPane]-------------
			public void mouseClicked(MouseEvent arg0) {
			
				tabbedPane.setVisible(true);
				homeTab.setVisible(true);
				trendingBidsTab.setVisible(true);
				savedBidsPanel.setVisible(false);
				sellPanel.setVisible(false);
				myProdPanel.setVisible(false);
				myBidsPanel.setVisible(false);
				accountPanel.setVisible(false);
				lblMyBidsTitle.setVisible(false);
				lblMyProdTitle.setVisible(false);
				moreInfoPanel.setVisible(false);
				lblSavedBidsTitle.setVisible(false);
				
				homeFetchData( c);
			}
		});
	    
		lblAccount.addMouseListener(new MouseAdapter() {
			@Override
			
			//---------Account Panel--------------- 
			public void mouseClicked(MouseEvent e) {
				
				tabbedPane.setVisible(false);
				homeTab.setVisible(false);
				trendingBidsTab.setVisible(false);
				savedBidsPanel.setVisible(false);
				sellPanel.setVisible(false);
				myProdPanel.setVisible(false);
				myBidsPanel.setVisible(false);
				accountPanel.setVisible(true);
				lblMyBidsTitle.setVisible(false);
				lblMyProdTitle.setVisible(false);
				moreInfoPanel.setVisible(false);
				lblSavedBidsTitle.setVisible(false);
			}
		});
		
		lblSell.addMouseListener(new MouseAdapter() {
			@Override
			//----------Sell Panel-----------------
			public void mouseClicked(MouseEvent e) {
				
				tabbedPane.setVisible(false);
				homeTab.setVisible(false);
				trendingBidsTab.setVisible(false);
				accountPanel.setVisible(false);
				savedBidsPanel.setVisible(false);
				myProdPanel.setVisible(false);
				myBidsPanel.setVisible(false);
				sellPanel.setVisible(true);
				lblMyBidsTitle.setVisible(false);
				lblMyProdTitle.setVisible(false);
				moreInfoPanel.setVisible(false);
				lblSavedBidsTitle.setVisible(false);
				
			}
		});
		lblMyProd.addMouseListener(new MouseAdapter() {
			@Override
			//----------MyBids Panel-----------------
			public void mouseClicked(MouseEvent e) {
				
				tabbedPane.setVisible(false);
				homeTab.setVisible(false);
				trendingBidsTab.setVisible(false);
				accountPanel.setVisible(false);
				savedBidsPanel.setVisible(false);
				sellPanel.setVisible(false);
				myBidsPanel.setVisible(false);
				myProdPanel.setVisible(true);
				lblMyBidsTitle.setVisible(false);
				lblMyProdTitle.setVisible(true);
				moreInfoPanel.setVisible(false);
				lblSavedBidsTitle.setVisible(false);
				
				String myProdcolumns[]={"ProductId","Product Name","Base Price","Maximum Bidder","Current Bid Price","Image"};
				
				myProdTable = new JTable();
				myProdTable.setBounds(0, 45, 1365, 516);
				
				myProdmodel = new DefaultTableModel();
				myProdmodel.setColumnIdentifiers(myProdcolumns);
				
				myProdTable = new JTable(myProdmodel);
				myProdTable.setShowVerticalLines(true);
				myProdTable.setShowHorizontalLines(true);
				myProdTable.setRowHeight(120);
				
				myProdTable.getColumnModel().getColumn(0).setPreferredWidth(50);
				myProdTable.getColumnModel().getColumn(1).setPreferredWidth(50);
				myProdTable.getColumnModel().getColumn(2).setPreferredWidth(50);
				myProdTable.getColumnModel().getColumn(3).setPreferredWidth(50);
				myProdTable.getColumnModel().getColumn(4).setPreferredWidth(50);
				myProdTable.getColumnModel().getColumn(5).setPreferredWidth(150);
				
				myProdScrollPane = new JScrollPane(myProdTable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				myProdScrollPane.setBounds(0, 44, 1365, 516);
				myProdScrollPane.setVisible(true);
				myProdPanel.add(myProdScrollPane,BorderLayout.CENTER);
				
				myProdFetchData(c);   //To constantly Update the Data in the MyBidsTable.
			
			}
		});
		lblMyBids.addMouseListener(new MouseAdapter() {
			@Override
			//----------MyBids Panel-----------------
			public void mouseClicked(MouseEvent e) {
				
				tabbedPane.setVisible(false);
				homeTab.setVisible(false);
				trendingBidsTab.setVisible(false);
				accountPanel.setVisible(false);
				savedBidsPanel.setVisible(false);
				sellPanel.setVisible(false);
				myBidsPanel.setVisible(true);
				myProdPanel.setVisible(false);
				lblMyBidsTitle.setVisible(true);
				lblMyProdTitle.setVisible(false);
				moreInfoPanel.setVisible(false);
				lblSavedBidsTitle.setVisible(false);
				
				myBidsTable = new JTable();
				myBidsTable.setBounds(0, 44, 1365, 516);
				
				String myBidscolumns[]={"ProductId","Product Name","Your Bid Price","Maximum Bidder","Current Bid Price","Image"};
				
			    myBidsmodel = new DefaultTableModel();
				myBidsmodel.setColumnIdentifiers(myBidscolumns);
				myBidsTable =new JTable(myBidsmodel);
				myBidsTable.setShowVerticalLines(true);
				myBidsTable.setShowHorizontalLines(true);
				myBidsTable.setRowHeight(120);
		
				myBidsTable.getColumnModel().getColumn(0).setPreferredWidth(50);
				myBidsTable.getColumnModel().getColumn(1).setPreferredWidth(50);
				myBidsTable.getColumnModel().getColumn(2).setPreferredWidth(50);
				myBidsTable.getColumnModel().getColumn(3).setPreferredWidth(50);
				myBidsTable.getColumnModel().getColumn(4).setPreferredWidth(50);
				myBidsTable.getColumnModel().getColumn(5).setPreferredWidth(150);
				
				
				myBidsScrollPane = new JScrollPane(myBidsTable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				myBidsScrollPane.setBounds(0, 44, 1365, 516);
				myBidsScrollPane.setVisible(true);
				myBidsPanel.add(myBidsScrollPane,BorderLayout.CENTER);
				
				myBidsFetchData(c);   //To constantly Update the Data in the MyBidsTable.
			}
		});
		
		lblSavedBids.addMouseListener(new MouseAdapter() {
			@Override
			//----------SavedBids Panel-----------------
			public void mouseClicked(MouseEvent e) {
				
				tabbedPane.setVisible(false);
				homeTab.setVisible(false);
				trendingBidsTab.setVisible(false);
				accountPanel.setVisible(false);
				sellPanel.setVisible(false);
				myBidsPanel.setVisible(false);
				savedBidsPanel.setVisible(true);
				myProdPanel.setVisible(false);
				lblMyBidsTitle.setVisible(false);
				lblMyProdTitle.setVisible(false);
				moreInfoPanel.setVisible(false);
				lblSavedBidsTitle.setVisible(true);
				
				String savedBidscolumns[]={"ProductId","Product Name","Seller Username","Maximum Bidder","Current Bid Price","Image"};
				
				savedBidsTable = new JTable();
				savedBidsTable.setBounds(0, 44, 1365, 516);
				
				savedBidsmodel = new DefaultTableModel();
				savedBidsmodel.setColumnIdentifiers(savedBidscolumns);
				savedBidsTable =new JTable(savedBidsmodel);
				savedBidsTable.setShowVerticalLines(true);
				savedBidsTable.setShowHorizontalLines(true);
				savedBidsTable.setRowHeight(120);
				savedBidsTable.getColumnModel().getColumn(5).setPreferredWidth(150);
			
				savedBidsTable.getColumnModel().getColumn(0).setPreferredWidth(50);
				savedBidsTable.getColumnModel().getColumn(1).setPreferredWidth(50);
				savedBidsTable.getColumnModel().getColumn(2).setPreferredWidth(50);
				savedBidsTable.getColumnModel().getColumn(3).setPreferredWidth(50);
				savedBidsTable.getColumnModel().getColumn(4).setPreferredWidth(50);
				savedBidsTable.getColumnModel().getColumn(5).setPreferredWidth(150);
				
				savedBidsScrollPane = new JScrollPane(savedBidsTable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				savedBidsScrollPane.setBounds(0, 44, 1365, 516);
				savedBidsScrollPane.setVisible(true);
				savedBidsPanel.add(savedBidsScrollPane,BorderLayout.CENTER);
				
				savedBidsFetchData(c);          //To constantly Update the Data in the SavedBidsTable.
			}
		});
		
		lblLogout.addMouseListener(new MouseAdapter() {
			@Override
			//-------------Logout Panel------------
			public void mouseClicked(MouseEvent e) {
				
				tabbedPane.setVisible(false);
				homeTab.setVisible(false);
				trendingBidsTab.setVisible(false);
				accountPanel.setVisible(false);
				sellPanel.setVisible(false);
				myBidsPanel.setVisible(false);
				savedBidsPanel.setVisible(false);
				myProdPanel.setVisible(false);
				lblMyBidsTitle.setVisible(false);
				lblMyProdTitle.setVisible(false);
				lblSavedBidsTitle.setVisible(false);
				
				int yesOrNo = JOptionPane.showConfirmDialog(null, " Are you sure you want to Logout?", "Logout Screen", JOptionPane.YES_NO_OPTION);
				if(yesOrNo==0)
				{
					//User Clicked Yes redirect to Login page.
				    LoginFrame lf = new LoginFrame();
				    lf.setVisible(true);
				    reg();
				}
				
			}
		});
	}
	
       public void reg()
       {
		 this.setVisible(false);
       }
       
       public String getProdId()
       {
    	   System.out.println("txtProductId.getText():"+txtProductId.getText());
    	   return txtProductId.getText();
       }
       public String getProdBasePrice()
       {
    	   return txtBasePrice.getText();
       }
	
       //-------------------------Fetch Data constantly from the Database to HomeTable .-------------
       
       public void homeFetchData(Client c)
       {
    	   try {
   			/*
   			myBidsTable.setModel(DbUtils.resultSetToTableModel(rs));
   			*/
       		  String mbtProID=null;
       		  int prodCount=0;
       		  Vector<String> temp = new Vector<String>();  //contains 4 Columns. Image Column is Not Added.
       		  tabbedPane.setVisible(true);
       		  trendingBidsTab.setVisible(true); 
       		  homeTab.setVisible(true);
       		  ps = con.prepareStatement("select ProID,ProName,BasePrice,ProCategory,Image from selltable");
       		    rs=ps.executeQuery();
     
       		  while(rs.next())
       		  {   
       			mbtProID = rs.getString("ProID");  
       		    temp.add(mbtProID);
       		    temp.add(rs.getString("ProName"));
       		    temp.add(rs.getString("BasePrice"));
       		    temp.add(rs.getString("ProCategory"));
       		    blob=rs.getBlob("Image");
		        bImg=blob.getBytes(1, (int)blob.length());
		    	   //ImageIcon imgTemp = new ImageIcon(new ImageIcon().getImage(bImg));//Remaining:-retrieving Image from Database.
		    	   //temp2.addElement(imgTemp);
       		    prodCount++;
       		    System.out.println("Inside 1st while:"+prodCount);
       		  }
       		 Vector<String> temp1 = new Vector<String>();
       		 
  		     for(int z=prodCount-1,y=0;z>=0;z--)
  		    {
  			  temp1.add(y++, temp.get(z*4));
  			  temp1.add(y++,temp.get(z*4+1));
  			  temp1.add(y++, temp.get(z*4+2));
  			  temp1.add(y++, temp.get(z*4+3));
  			  //temp1.add(y++, temp.get(y)); for Image
  		    }
       		Vector<String> temp2 = new Vector<String>();  //contains 3 Columns.
       		int i=prodCount-1;
        	
  		      while(i>=0)
  		    {
  			  int index=4*i;
  		       ps = con.prepareStatement("select UserName,CurrentPrice,NoOfBids from bidtable where ProID=?");   
  		       ps.setString(1, temp.get(index));
    		      rs=ps.executeQuery();
    		      while(rs.next())  
 		           {     
 		    	    temp2.add(rs.getString("UserName"));
 		    	    temp2.add(rs.getString("CurrentPrice"));
 		    	    temp2.add(rs.getString("NoOfBids"));
 		          }
    		     i--;
  		    }
  		      
  		    Vector<String> finalhomeTable = new Vector<String>();
     		 int k=0,x=0;
     		 while(prodCount>k)
     		 { 
     			finalhomeTable.add(x++, temp1.get(k));   //ProID
     			finalhomeTable.add(x++, temp1.get(k+1));   //ProName
     			finalhomeTable.add(x++, temp1.get(k+2));  //BasePrice
     			finalhomeTable.add(x++, temp1.get(k+3));   //Category.
     			finalhomeTable.add(x++, temp2.get(k));  //Max Bidder Username.
     			finalhomeTable.add(x++, temp2.get(k+1));  //CurrentPrice.
     			finalhomeTable.add(x++, temp2.get(k+2));  //NoOfBids.
     	      // finalhomeTable.add(x++, temp1.get(k+4));  //Image .
     		   homeHomemodel.addRow(new Object[]{finalhomeTable.get(x-7),finalhomeTable.get(x-6),finalhomeTable.get(x-5),finalhomeTable.get(x-4),finalhomeTable.get(x-3),finalhomeTable.get(x-2),finalhomeTable.get(x-1)});
     		   k++;
               
     		 }
    	   }catch(Exception e)
    	   {
    		   System.out.println("Exception Caught is homeFetchData Function.");
    		   e.printStackTrace();
    	   }
       }
       
       //-------------------------Fetch Data constantly from the Database to MyBidsTable .-------------
       
       public void myBidsFetchData(Client c)
       {
    	   //Remaining:-If user Clicks on a particular row , show MoreInfo panel without save or Sell Button.
    	   try {
			/*
			myBidsTable.setModel(DbUtils.resultSetToTableModel(rs));
			*/
    		  String mbtProID=null;
    		  vectorMyBidsTable = new Vector<Vector<String>>(); 
    		  int prodCount=0;
    		  ps = con.prepareStatement("select ProID,MyBidPrice from buyertable where Username=?");
    		    ps.setString(1, c.userName);
    		    rs=ps.executeQuery();
    		   Vector<String> temp = new Vector<String>();
    		   if(!rs.next())
    		   {
    			   myBidsPanel.setVisible(false);
    			   JOptionPane.showMessageDialog(null, "Your My Bids Table is Empty!!");
    		   }
    		 else
    		 {
    			 myBidsPanel.setVisible(true);
    		  while(rs.next())
    		  {   
    			mbtProID = rs.getString("ProID");  
    		    temp.add(mbtProID);
    		    temp.add(Double.toString(rs.getDouble("MyBidPrice")));
    		    prodCount++;
    		  }
    		  Vector<String> temp1 = new Vector<String>();
    		  for(int z=prodCount-1,y=0;z>=0;z--)
    		  {
    			 temp1.add(y++, temp.get(z*2));
    			 temp1.add(y++,temp.get(z*2+1));
    		  }
    		  Vector<String> temp2 = new Vector<String>();
    		  
    		   int i=prodCount-1;
    	
    		  while(i>=0)
    		  {
    			int index=2*(i-1);
     		     
    		   ps = con.prepareStatement("select ProName,Image from selltable where ProID=?");   
    		     ps.setString(1, temp.get(index));
      		     rs=ps.executeQuery();
      		     if(rs.next())  //Will Only get 1 Value.
   		         {     
   		    	   temp2.add(rs.getString("ProName"));
   		    	   blob=rs.getBlob("Image");
   		    	   bImg=blob.getBytes(1, (int)blob.length());
   		    	   //ImageIcon imgTemp = new ImageIcon(new ImageIcon().getImage(bImg));//Remaining:-retrieving Image from Database.
   		    	   //temp2.addElement(imgTemp);
   		         }
      		     i--;
    		  }
      		     Vector<String> temp3 = new Vector<String>();
      		   int j=prodCount-1;
    		  
      		   while(j>=0)
      		    {
      			 int index=2*(i-1);
    		  
      		      ps = con.prepareStatement("select CurrentPrice,UserName from bidtable where ProID=?");
      		       ps.setString(1, temp.get(index));
  		           rs=ps.executeQuery();
  		           if(rs.next())
  		           {
  		        	   temp3.add(rs.getString("UserName"));
  		        	   temp3.add(Double.toString(rs.getDouble("CurrentPrice")));
  		           }
  		           j--;
    		  }
      		 Vector<String> finalMyBidsTable = new Vector<String>();
      		 int k=0,x=0;
      		 while(prodCount>k)
      		 { 
      	       finalMyBidsTable.add(x++, temp1.get(k));   //ProID
      	       finalMyBidsTable.add(x++, temp2.get(k));   //ProName
      	       finalMyBidsTable.add(x++, temp1.get(k+1));  //MyBidPrice
      	       finalMyBidsTable.add(x++, temp3.get(k+1));   //CurrentPrice[Max Bid].
      	       finalMyBidsTable.add(x++, temp3.get(k));  //Max Bidder Username.
      	      // finalMyBidsTable.add(x++, temp2.get(k+1));  //Image .
      		   myBidsmodel.addRow(new Object[]{finalMyBidsTable.get(x-5),finalMyBidsTable.get(x-4),finalMyBidsTable.get(x-3),finalMyBidsTable.get(x-2),finalMyBidsTable.get(x-1)});
                k++;
                
      		 }
    		}
    		   
    		  
		} catch (SQLException e) {
			System.out.println("SQLException in MyBids fetch Method.");
			e.printStackTrace();
		}
    	  
       }
       
       //-------------------------Fetch Data constantly from the Database to SavedBidsTable .-------------
       
       
       public void savedBidsFetchData(Client c)
       {
    	   //Remaining:-If user Clicks on a particular row , show MoreInfo panel without save or Sell Button.
    	   try {
			/*
			myBidsTable.setModel(DbUtils.resultSetToTableModel(rs));
			*/
    		   System.out.println("Inside Try Block");
    		  String mbtProID=null;
    		  int prodCount=0;
    		  Vector<String> temp = new Vector<String>();  //contains only ProductId.
    		  savedBidsPanel.setVisible(true); 
    		  ps = con.prepareStatement("select ProID from savebidstable where Username=?");
    		    ps.setString(1, c.userName);
    		    rs=ps.executeQuery();
    		    
    		  while(rs.next())
    		  {   
    			mbtProID = rs.getString("ProID");  
    		    temp.add(mbtProID);
    		    prodCount++;
    		    System.out.println("Inside 1st while:"+prodCount);
    		  }
    		  Vector<String> temp1 = new Vector<String>();  //Storing the Product Id in the Descending Order.
    		  for(int z=prodCount-1,y=0;z>=0;z--)
    		  {
    			 temp1.add(y++, temp.get(z));
    		  }
    		  Vector<String> temp2 = new Vector<String>();  //Has 3 columns
    		  
    		   int i=prodCount-1;
    	
    		  while(i>=0)
    		  {
    		   ps = con.prepareStatement("select ProName,UserName,Image from selltable where ProID=?");   
    		     ps.setString(1, temp.get(i));
      		     rs=ps.executeQuery();
      		     if(rs.next())  //Will Only get 1 Value.
   		         {     
   		    	   temp2.add(rs.getString("ProName"));
   		    	   temp2.add(rs.getString("UserName"));
   		    	   blob=rs.getBlob("Image");
   		    	   bImg=blob.getBytes(1, (int)blob.length());
   		    	   //ImageIcon imgTemp = new ImageIcon(new ImageIcon().getImage(bImg));//Remaining:-retrieving Image from Database.
   		    	   //temp2.add(imgTemp);
   		    	  System.out.println("Inside 2nd if"+i); 
   		         }
      		     i--;
    		  }
    		  
      		   Vector<String> temp3 = new Vector<String>();   //Has 2 Columns.
      		   int j=prodCount-1;
    		  
      		   while(j>=0)
      		    {
      		     ps = con.prepareStatement("select CurrentPrice,UserName from bidtable where ProID=?");
      		       ps.setString(1, temp.get(j));
  		           rs=ps.executeQuery();
  		           if(rs.next())
  		           {
  		        	   temp3.add(rs.getString("UserName"));
  		        	   temp3.add(Double.toString(rs.getDouble("CurrentPrice")));
  		        	   System.out.println("Inside 3rd while:"+j);
  		           }
  		           j--;
    		  }
      		 Vector<String> finalSavedBidsTable = new Vector<String>();
      		 int k=0,x=0;
      		 while(prodCount>k)
      		 { 
      			finalSavedBidsTable.add(x++, temp1.get(k));   //ProID
      			finalSavedBidsTable.add(x++, temp2.get(k));   //ProName
      	        finalSavedBidsTable.add(x++, temp2.get(k+1));  //Seller UserName
      	        finalSavedBidsTable.add(x++, temp3.get(k));   //Max Bidder Username.
      	        finalSavedBidsTable.add(x++, temp3.get(k+1));     //CurrentPrice[Max Bid].
      	        //finalSavedBidsTable.add(x++, temp2.get(k+2));  //Image .
      	      savedBidsmodel.addRow(new Object[]{finalSavedBidsTable.get(x-5),finalSavedBidsTable.get(x-4),finalSavedBidsTable.get(x-3),finalSavedBidsTable.get(x-2),finalSavedBidsTable.get(x-1)});
                k++;
                System.out.println("Inside 4th While:"+k);  
      		 }
    		  
		} catch (SQLException e) {
			System.out.println("SQLException in Saved Bids fetch Method.");
			e.printStackTrace();
		}
    	  
       }
       
       
       
//-------------------------Fetch Data constantly from the Database to MyProductBidsTable .-------------
       
       
       public void myProdFetchData(Client c)
       {
    	   System.out.println("In myProductFetchData");
    	   
    	   //Remaining:-If user Clicks on a particular row , show MoreInfo panel without save or Sell Button.
    	   try {
			/*
			myBidsTable.setModel(DbUtils.resultSetToTableModel(rs));
			*/
    		  String mbtProID=null;
    		  vectorMyProdTable = new Vector<Vector<String>>(); 
    		  int prodCount=0;
    		  ps = con.prepareStatement("select ProID from selltable where UserName=?");
    		    ps.setString(1, c.userName);
    		    rs=ps.executeQuery();
    		   Vector<String> temp = new Vector<String>();  //contains only ProductId.
    		 
    		 
    	      myProdPanel.setVisible(true);
    	      System.out.println("in Else");
    	      System.out.println("before While");
    		  while(rs.next())
    		  {   
    			mbtProID = rs.getString("ProID");  
    		    temp.add(mbtProID);
    		    prodCount++;
    		    System.out.println("In first While: "+prodCount);
    		  }
    		  System.out.println("After While");
    		  Vector<String> temp1 = new Vector<String>();  //Storing the Product Id in the Descending Order.
    		  for(int z=prodCount-1,y=0;z>=0;z--)
    		  {
    			 temp1.add(y++, temp.get(z));
     		    System.out.println("In 2nd While: "+y);
    		  }
    		  Vector<String> temp2 = new Vector<String>();  //Has 3 columns
    		   int i=prodCount-1;
    	
    		  while(i>=0)
    		  {
    		   ps = con.prepareStatement("select ProName,BasePrice,Image from selltable where ProID=?");   
    		     ps.setString(1, temp.get(i));
      		     rs=ps.executeQuery();
      		     if(rs.next())  //Will Only get 1 Value.
   		         {     
   		    	   temp2.add(rs.getString("ProName"));
   		    	   temp2.add(Double.toString(rs.getDouble("BasePrice")));
   		    	   blob=rs.getBlob("Image");
   		    	   bImg=blob.getBytes(1, (int)blob.length());
   		    	   //ImageIcon imgTemp = new ImageIcon(new ImageIcon().getImage(bImg));//Remaining:-retrieving Image from Database.
   		    	  // temp2.add(imgTemp);
       		    System.out.println("In 3rd While: "+i);
   		         }
      		     i--;
    		  }
    		  
      		   Vector<String> temp3 = new Vector<String>();   //Has 2 Columns.
      		   i=prodCount-1;
      		   int j=prodCount-1;
    		  
      		   while(j>=0)
      		    {
      		     ps = con.prepareStatement("select CurrentPrice,UserName from bidtable where ProID=?");
      		       ps.setString(1, temp.get(i));
  		           rs=ps.executeQuery();
  		           if(rs.next())
  		           {
  		        	   temp3.add(rs.getString("UserName"));
  		        	   temp3.add(Double.toString(rs.getDouble("CurrentPrice")));
  		    		    System.out.println("In 4th While: "+j);
  		           }
  		           j--;
    		  }
      		 Vector<String> finalmyProdTable = new Vector<String>();
      		 int k=0,x=0;
      		 while(prodCount>k)
      		 { 
      			finalmyProdTable.add(x++, temp1.get(k));   //ProID
      			finalmyProdTable.add(x++, temp2.get(k));   //ProName
      			finalmyProdTable.add(x++, temp2.get(k+1));  //Base Price
      			finalmyProdTable.add(x++, temp3.get(k));   //Max Bidder Username.
      			finalmyProdTable.add(x++, temp3.get(k+1));  //CurrentPrice[Max Bid].
      			//finalmyProdTable.add(x++, temp2.get(k+2));  //Image .
      			myProdmodel.addRow(new Object[]{finalmyProdTable.get(x-5),finalmyProdTable.get(x-4),finalmyProdTable.get(x-3),finalmyProdTable.get(x-2),finalmyProdTable.get(x-1)});        
      			k++;
      			System.out.println("In add row: " +k);
                
      		 }
      		myProdTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
		        public void valueChanged(ListSelectionEvent event) {
		            // do some actions here, for example
		            // print first column value from selected row
		        	getProID=myProdTable.getValueAt(myProdTable.getSelectedRow(), 0).toString();
		            System.out.println(getProID);
		            if(getProID!=null) 
		    		 {
		    		  System.out.println("Inside More Info Panel");	 
		    		  moreInfoPanel.setVisible(true);
		    		  myProdPanel.setVisible(false);
		    		  try {
						ps=con.prepareStatement("select * from selltable where ProID=?");
						ps.setString(1, getProID);
						rs=ps.executeQuery();
			    		  if(rs.next())
			    		  {
			    			  txtInfoProductName.setText(rs.getString("ProName"));
			    			  txtInfoProductID.setText(rs.getString("ProID"));
			    			  txtInfoBasePrice.setText(rs.getString("BasePrice"));
			    			  txtInfoArProductDetails.setText(rs.getString("ProDetails"));
			    			  txtInfoSPCategory.setText(rs.getString("ProCategory"));
			    			  txtInfoUsername.setText(rs.getString("UserName"));
			    			  blob=rs.getBlob("Image");
			  		    	  bImg=blob.getBytes(1, (int)blob.length());
			  		    	  //ImageIcon imgTemp = new ImageIcon(new ImageIcon().getImage(bImg));//Remaining:-retrieving Image from Database.
			  		    	 // txtInfoProductImage.add(imgTemp);
			  		    	
			    		  }
			    		   ps=con.prepareStatement("select CurrentPrice from bidtable where ProID=?");
			    		   ps.setString(1, getProID);
			    		   rs=ps.executeQuery();
			    		   if(rs.next())
			    		   {
			    			   txtInfoCurrentPrice.setText(rs.getString("CurrentPrice"));
			    		   }
					} catch (Exception e) {
						System.out.println("Exception in MyProductTable ListSelectionListener.");
						e.printStackTrace();
					}
		    		  
		    	 }
		        }
		    });

    		 System.out.println("Outside ListListener");
    		     		 
    		 //-------------------Enable the MoreInfoPanel for that Specific ProductId--------------------;
    		
    		 
    		 
    		  
		} catch (SQLException e) {
			System.out.println("SQLException in My Product fetch Method.");
			e.printStackTrace();																																
		}
    	  
       }
       
       public int getMyBidsRowCount()
       {
    	   return myBidsrows.length;
       }
       public int getSavedBidsRowCount()
       {
    	   return savedBidsrows.length;
       }
       private  int setInterval() {
    	    if (interval == 0)
    	        timer.cancel();
    	    return --interval;
    	}
	//------------------------------Validate Info Functions-------------------
	
	public boolean ValidateInfo()
	{
		 productName=txtProductName.getText();
   	     p = Pattern.compile("[^a-z]", Pattern.CASE_INSENSITIVE);
   	     m = p.matcher(productName);
   	     b = m.find();
		 if(b || productName.equalsIgnoreCase(""))
   	        {
				JOptionPane.showMessageDialog(null,"Invalid Product Name!!",null, JOptionPane.ERROR_MESSAGE);
				txtProductName.requestFocusInWindow();
				txtProductName.setText("");
						productName="";					 
						return false;
			}
			System.out.println(productName);
			
			productBasePrice=txtBasePrice.getText();
	   	    p = Pattern.compile("[^0-9]", Pattern.CASE_INSENSITIVE);
	   	    m = p.matcher(productBasePrice);
	   	    b = m.find();
			if(b || productBasePrice.equalsIgnoreCase(""))
	   	      {
			    JOptionPane.showMessageDialog(null,"Invalid Base Price Name!!",null, JOptionPane.ERROR_MESSAGE);
			    txtBasePrice.requestFocusInWindow();
			    txtBasePrice.setText("");
			    productBasePrice="";					 
				return false;
			  }
		  System.out.println(productName);
		 
		return true;
	}
}
