package interface_graphique;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import com.sun.javacard.apduio.Apdu;
import com.sun.javacard.apduio.CadTransportException;

public class MAIN implements ActionListener{


	/*ATM*/
	public JFrame frmMyBank;
	JButton retry ,exit,Retrive_insuff_Back,retry_max_reached_fixed,retry_eneter_amount ,goback;
	JButton Exit_Code_PIN,Enter_Code_PIN;
	public static JPanel panel;
	private JPasswordField PIN_INPUT;
	/*Balance*/
	JLabel enteramount;
	JButton Balance_goback,Balance_exit ,retryCode;
	/*Wrong Code*/
	JButton Exit_Wrong_Code,Retry_Wrong_Code;
	/*Wrong Input*/
	JButton WrongInput_Retry,WrongInput_Exit;
	/*LimitAttempts*/
	JButton LimitAttempts_Exit;
	/*Distributeur*/
	JButton checkbalance ,Retrieve,cashdeposit,ExitHome;
	/*Retrivemoney*/
	JButton Retrivemoney_dix, Retrivemoney_vingt,Retrivemoney_khamsin,Retrivemoney_cent,Retrivemoney_mytyn,Retrivemoney_custom,Retrivemoney_back;
	JButton Deposit_dix,Deposit_vingt,Deposit_khamsin,Deposit_cent,Deposit_mytyn,Deposit_custom,Deposit_back;
	JTextField Retrive_Enter_Amount_textField;
	JButton Retrive_Enter_Amount_goback,Retrive_Enter_Amount_Validate;
	JButton Retrive_Success_Exit,Retrive_Success_Back;

	JTextField Deposit_Enter_Amount_textField;
	JButton Deposit_Enter_Amount_goback,Deposit_Enter_Amount_Validate;
	JButton Deposit_Success_Exit,Deposit_Success_Back;

	/**/

	public Apdu apdu;
	static Services client;



	public static void main(String[] args) throws IOException,CadTransportException {
		client = new Services();
		client.Connect();
		try {
			client.Select();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CadTransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MAIN m = new MAIN();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});




	}
	public MAIN()
	{
		frmMyBank = new JFrame();
		frmMyBank.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		frmMyBank.setResizable(false);
		frmMyBank.setTitle("My Bank");
		frmMyBank.setBounds(200, 200, 500, 450);
		frmMyBank.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMyBank.setVisible(true);
		ATM();
	}

	public void ATM() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.WHITE);

		JPanel innerPanel = new JPanel(new GridBagLayout());
		innerPanel.setOpaque(false); // Make the panel transparent for background visibility
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		// Title label
		JLabel lblTitle = new JLabel("Enter Code PIN", SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTitle.setOpaque(true);
		lblTitle.setBackground(new Color(208, 232, 252));
		lblTitle.setForeground(new Color(0, 0, 0));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		innerPanel.add(lblTitle, gbc);

		// PIN input field
		PIN_INPUT = new JPasswordField(4);
		PIN_INPUT.setHorizontalAlignment(SwingConstants.CENTER);
		PIN_INPUT.setFont(new Font("Tahoma", Font.PLAIN, 32));
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		innerPanel.add(PIN_INPUT, gbc);

		// Enter button
		Enter_Code_PIN = new JButton("Enter");
		Enter_Code_PIN.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Enter_Code_PIN.setForeground(Color.WHITE);
		Enter_Code_PIN.setBackground(new Color(0, 168, 222));
		Enter_Code_PIN.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/arrow-right-circle.png")));
		Enter_Code_PIN.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		Enter_Code_PIN.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		innerPanel.add(Enter_Code_PIN, gbc);

		// Exit button
		Exit_Code_PIN = new JButton("Exit");
		Exit_Code_PIN.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Exit_Code_PIN.setForeground(Color.WHITE);
		Exit_Code_PIN.setBackground(new Color(220, 53, 69));
		Exit_Code_PIN.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/log-out.png")));
		Exit_Code_PIN.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		Exit_Code_PIN.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 2;
		innerPanel.add(Exit_Code_PIN, gbc);
		panel.add(innerPanel, BorderLayout.CENTER);
		// Background image panel
		JLabel backgroundLabel = new JLabel(new ImageIcon(getClass().getResource("/interface_graphique/background2.jpg")));
		panel.add(backgroundLabel);
		backgroundLabel.setLayout(new BorderLayout()); // Use the label as a container for the panel
		backgroundLabel.add(innerPanel, BorderLayout.CENTER);

		// Set the frame content
		frmMyBank.setContentPane(panel);
		frmMyBank.validate();
		frmMyBank.repaint();
	}



	public void Distributeur() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.WHITE);
		JPanel innerPanel = new JPanel(new GridBagLayout());
		innerPanel.setOpaque(false); // Make the panel transparent for background visibility
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		// Welcome label
		JLabel lblWelcome = new JLabel("Welcome!", SwingConstants.CENTER);
		lblWelcome.setForeground(new Color(0, 0, 0));
		lblWelcome.setOpaque(true);
		lblWelcome.setBackground(new Color(208, 232, 252));
		lblWelcome.setFont(new Font("Sylfaen", Font.BOLD, 30));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		innerPanel.add(lblWelcome, gbc);

		// Check Balance button
		checkbalance = new JButton("Check Balance");
		checkbalance.setForeground(Color.WHITE);
		checkbalance.setBackground(new Color(0, 168, 222));
		checkbalance.setFont(new Font("Sylfaen", Font.BOLD, 19));
		checkbalance.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/wallet.png")));
		checkbalance.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		innerPanel.add(checkbalance, gbc);

		// Retrieve Money button
		Retrieve = new JButton("Retrieve Money");
		Retrieve.setForeground(Color.WHITE);
		Retrieve.setBackground(new Color(0, 168, 222));
		Retrieve.setFont(new Font("Sylfaen", Font.BOLD, 19));
		Retrieve.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/withdraw-money.png")));
		Retrieve.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 1;
		innerPanel.add(Retrieve, gbc);

		// Cash Deposit button
		cashdeposit = new JButton("Cash Deposit");
		cashdeposit.setForeground(Color.WHITE);
		cashdeposit.setBackground(new Color(0, 168, 222));
		cashdeposit.setFont(new Font("Sylfaen", Font.BOLD, 19));
		cashdeposit.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/cash.png")));
		cashdeposit.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 2;
		innerPanel.add(cashdeposit, gbc);

		// Exit button
		ExitHome = new JButton("Exit");
		ExitHome.setForeground(Color.WHITE);
		ExitHome.setOpaque(true);
		ExitHome.setBackground(new Color(220, 53, 69));
		ExitHome.setFont(new Font("Sylfaen", Font.BOLD, 19));
		ExitHome.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/exit.png")));
		ExitHome.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 2;
		innerPanel.add(ExitHome, gbc);

		// Background image
		JLabel backgroundLabel = new JLabel(new ImageIcon(getClass().getResource("/interface_graphique/background2.jpg")));
		panel.add(backgroundLabel);
		backgroundLabel.setLayout(new BorderLayout()); // Use the label as a container for the panel
		backgroundLabel.add(innerPanel, BorderLayout.CENTER);

		frmMyBank.setContentPane(panel);
		frmMyBank.validate();
		frmMyBank.repaint();
	}

	public void Balance() {
		// Create a main panel with BorderLayout
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.WHITE);

		// Inner panel for components using GridBagLayout
		JPanel innerPanel = new JPanel(new GridBagLayout());
		innerPanel.setOpaque(false); // Make the panel transparent for background visibility
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// "Your Balance in TND" Label
		enteramount = new JLabel("Your Balance in TND:");
		enteramount.setForeground(new Color(0, 0, 0));
		enteramount.setOpaque(true);
		enteramount.setBackground(new Color(208, 232, 252));
		enteramount.setFont(new Font("Tahoma", Font.BOLD, 19));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		innerPanel.add(enteramount, gbc);

		// "Go Back" Button
		Balance_goback = new JButton("Go Back");
		Balance_goback.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/reply.png")));
		Balance_goback.setForeground(new Color(4, 109, 181));
		Balance_goback.setFont(new Font("Tahoma", Font.BOLD, 16));
		Balance_goback.setOpaque(true);
		Balance_goback.setBackground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		Balance_goback.addActionListener(this);
		innerPanel.add(Balance_goback, gbc);

		// "Exit" Button
		Balance_exit = new JButton("Exit");
		Balance_exit.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/exit.png")));
		Balance_exit.setForeground(Color.WHITE);
		Balance_exit.setOpaque(true);
		Balance_exit.setBackground(new Color(220, 53, 69));
		Balance_exit.setFont(new Font("Tahoma", Font.BOLD, 16));
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.LINE_END;
		Balance_exit.addActionListener(this);
		innerPanel.add(Balance_exit, gbc);

		// Add the inner panel to the main panel
		panel.add(innerPanel, BorderLayout.CENTER);

		// Background Label with Image
		JLabel backgroundLabel = new JLabel(new ImageIcon(getClass().getResource("/interface_graphique/background2.jpg")));
		panel.add(backgroundLabel);
		backgroundLabel.setLayout(new BorderLayout()); // Use the label as a container for the panel
		backgroundLabel.add(innerPanel, BorderLayout.CENTER);

		// Set the panel in the frame
		frmMyBank.setContentPane(panel);
		frmMyBank.validate();
		frmMyBank.repaint();
	}



	public void WrongInput() {

		JPanel panel = new JPanel();
		panel.setLayout(null);
		JLabel lblNewLabel = new JLabel("Wrong Input !");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 33));
		lblNewLabel.setForeground(new Color(4, 109, 181));
		lblNewLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		lblNewLabel.setIcon(new ImageIcon("C:\\\\Users\\\\nours\\\\eclipse-workspace\\\\interface_graphique\\\\src\\\\interface_graphique\\\\x-circle.png"));
		lblNewLabel.setBounds(54, 193, 324, 96);
		panel.add(lblNewLabel);

		WrongInput_Retry = new JButton(" Retry ");
		WrongInput_Retry.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		WrongInput_Retry.setForeground(new Color(0, 168, 222));
		WrongInput_Retry.setIcon(new ImageIcon("C:\\\\Users\\\\nours\\\\eclipse-workspace\\\\interface_graphique\\\\src\\\\interface_graphique\\\\reload.png"));
		WrongInput_Retry.setFont(new Font("Tahoma", Font.PLAIN, 25));
		WrongInput_Retry.setBounds(23, 339, 195, 57);
		panel.add(WrongInput_Retry);
		WrongInput_Retry.addActionListener(this);

		WrongInput_Exit = new JButton("Exit ");
		WrongInput_Exit.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		WrongInput_Exit.setForeground(new Color(0, 168, 222));
		WrongInput_Exit.setIcon(new ImageIcon("C:\\\\Users\\\\nours\\\\eclipse-workspace\\\\interface_graphique\\\\src\\\\interface_graphique\\\\exit.png"));
		WrongInput_Exit.setFont(new Font("Tahoma", Font.PLAIN, 25));
		WrongInput_Exit.setBounds(296, 339, 160, 57);
		panel.add(WrongInput_Exit);
		WrongInput_Exit.addActionListener(this);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\\\Users\\\\nours\\\\eclipse-workspace\\\\interface_graphique\\\\src\\\\interface_graphique\\\\Cash.jpg"));
		lblNewLabel_1.setBounds(0, 0, 500, 500);
		panel.add(lblNewLabel_1);

		panel.setVisible(true);
		frmMyBank.add(panel);
		frmMyBank.setContentPane(panel);

		frmMyBank.validate();
		frmMyBank.repaint();
	}
	public void WrongInputCode() {

		panel = new JPanel();
		panel.setLayout(null);
		JLabel lblNewLabel = new JLabel("Wrong Input !");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 33));
		lblNewLabel.setForeground(new Color(4, 109, 181));

		lblNewLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		lblNewLabel.setIcon(new ImageIcon("/interface_graphique/x-circle.png"));
		lblNewLabel.setBounds(54, 193, 324, 96);
		panel.add(lblNewLabel);

		retryCode = new JButton(" Retry ");
		retryCode.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		retryCode.setForeground(new Color(0, 168, 222));
		retryCode.setIcon(new ImageIcon("/interface_graphique/reload.png"));
		retryCode.setFont(new Font("Tahoma", Font.PLAIN, 25));
		retryCode.setBounds(23, 339, 195, 57);
		panel.add(retryCode);
		retryCode.addActionListener(this);

		WrongInput_Exit = new JButton("Exit ");
		WrongInput_Exit.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		WrongInput_Exit.setForeground(new Color(0, 168, 222));
		WrongInput_Exit.setIcon(new ImageIcon("/interface_graphique/exit.png"));
		WrongInput_Exit.setFont(new Font("Tahoma", Font.PLAIN, 25));
		WrongInput_Exit.setBounds(296, 339, 160, 57);
		panel.add(WrongInput_Exit);
		WrongInput_Exit.addActionListener(this);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("/interface_graphique/Cash.jpg"));
		lblNewLabel_1.setBounds(0, 0, 500, 500);
		panel.add(lblNewLabel_1);

		panel.setVisible(true);
		frmMyBank.add(panel);
		frmMyBank.setContentPane(panel);

		frmMyBank.validate();
		frmMyBank.repaint();
	}
	public void WrongCode() {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.WHITE);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Title label
		JLabel lblTitle = new JLabel("Wrong Code PIN!", SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTitle.setForeground(new Color(4, 109, 181));
		lblTitle.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/alert-octagon.png")));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		panel.add(lblTitle, gbc);

		// Retry button
		Retry_Wrong_Code = new JButton("Retry");
		Retry_Wrong_Code.setFont(new Font("Tahoma", Font.PLAIN, 25));
		Retry_Wrong_Code.setForeground(new Color(0, 168, 222));
		Retry_Wrong_Code.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/reload.png")));
		Retry_Wrong_Code.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		panel.add(Retry_Wrong_Code, gbc);

		// Exit button
		Exit_Wrong_Code = new JButton("Exit");
		Exit_Wrong_Code.setFont(new Font("Tahoma", Font.PLAIN, 25));
		Exit_Wrong_Code.setForeground(Color.WHITE);
		Exit_Wrong_Code.setBackground(new Color(220, 53, 69));
		Exit_Wrong_Code.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/exit.png")));
		Exit_Wrong_Code.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 1;
		panel.add(Exit_Wrong_Code, gbc);

		// Background image
		JLabel background = new JLabel(new ImageIcon(getClass().getResource("/interface_graphique/Cash.jpg")));
		frmMyBank.setContentPane(background);
		background.setLayout(new BorderLayout());
		background.add(panel, BorderLayout.CENTER);
		panel.setVisible(true);
		frmMyBank.add(panel);
		frmMyBank.setContentPane(panel);

		frmMyBank.validate();
		frmMyBank.repaint();
	}


	public void Retrivemoney() {
		// Create a main panel with BorderLayout
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.WHITE);

		// Inner panel for components using GridBagLayout
		JPanel innerPanel = new JPanel(new GridBagLayout());
		innerPanel.setOpaque(false); // Make the panel transparent for background visibility
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// "Choose the amount in TND" Label
		JLabel amount = new JLabel("Choose the amount in TND :");
		amount.setForeground(new Color(0, 0, 0));
		amount.setFont(new Font("Sylfaen", Font.BOLD, 19));
		amount.setOpaque(true);
		amount.setBackground(new Color(208, 232, 252));
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		innerPanel.add(amount, gbc);

		// "10" Button
		Retrivemoney_dix = new JButton("10");
		Retrivemoney_dix.setForeground(new Color(0, 168, 222));
		Retrivemoney_dix.setFont(new Font("Sylfaen", Font.BOLD, 14));
		Retrivemoney_dix.setOpaque(true);
		Retrivemoney_dix.setBackground(Color.WHITE);
		Retrivemoney_dix.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		innerPanel.add(Retrivemoney_dix, gbc);

		// "20" Button
		Retrivemoney_vingt = new JButton("20");
		Retrivemoney_vingt.setForeground(new Color(0, 168, 222));
		Retrivemoney_vingt.setFont(new Font("Sylfaen", Font.BOLD, 14));
		Retrivemoney_vingt.setOpaque(true);
		Retrivemoney_vingt.setBackground(Color.WHITE);
		Retrivemoney_vingt.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 1;
		innerPanel.add(Retrivemoney_vingt, gbc);

		// "50" Button
		Retrivemoney_khamsin = new JButton("50");
		Retrivemoney_khamsin.setForeground(new Color(0, 168, 222));
		Retrivemoney_khamsin.setFont(new Font("Tahoma", Font.BOLD, 14));
		Retrivemoney_khamsin.setOpaque(true);
		Retrivemoney_khamsin.setBackground(Color.WHITE);
		Retrivemoney_khamsin.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 2;
		innerPanel.add(Retrivemoney_khamsin, gbc);

		// "100" Button
		Retrivemoney_cent = new JButton("100");
		Retrivemoney_cent.setForeground(new Color(0, 168, 222));
		Retrivemoney_cent.setFont(new Font("Sylfaen", Font.BOLD, 14));
		Retrivemoney_cent.setOpaque(true);
		Retrivemoney_cent.setBackground(Color.WHITE);
		Retrivemoney_cent.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 2;
		innerPanel.add(Retrivemoney_cent, gbc);

		// "200" Button
		Retrivemoney_mytyn = new JButton("200");
		Retrivemoney_mytyn.setForeground(new Color(0, 168, 222));
		Retrivemoney_mytyn.setFont(new Font("Sylfaen", Font.BOLD, 14));
		Retrivemoney_mytyn.setOpaque(true);
		Retrivemoney_mytyn.setBackground(Color.WHITE);
		Retrivemoney_mytyn.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 3;
		innerPanel.add(Retrivemoney_mytyn, gbc);

		// "Custom" Button
		Retrivemoney_custom = new JButton("custom");
		Retrivemoney_custom.setForeground(Color.WHITE);
		Retrivemoney_custom.setFont(new Font("Sylfaen", Font.BOLD, 14));
		Retrivemoney_custom.setOpaque(true);
		Retrivemoney_custom.setBackground(new Color(0, 168, 222));
		Retrivemoney_custom.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 3;
		innerPanel.add(Retrivemoney_custom, gbc);

		// "GoBack" Button
		Retrivemoney_back = new JButton(" GoBack");
		Retrivemoney_back.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/reply.png"))); // Corrected image path
		Retrivemoney_back.setForeground(Color.WHITE);
		Retrivemoney_back.setBackground(new Color(220, 53, 69)); // Updated background color
		Retrivemoney_back.setFont(new Font("Sylfaen", Font.BOLD, 14));
		Retrivemoney_back.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		innerPanel.add(Retrivemoney_back, gbc);

		// Background Label with Image
		JLabel backgroundLabel = new JLabel(new ImageIcon(getClass().getResource("/interface_graphique/background2.jpg")));
		panel.add(backgroundLabel, BorderLayout.CENTER);
		backgroundLabel.setLayout(new BorderLayout()); // Use the label as a container for the innerPanel
		backgroundLabel.add(innerPanel, BorderLayout.CENTER);

		// Set the panel in the frame
		frmMyBank.setContentPane(panel);
		frmMyBank.validate();
		frmMyBank.repaint();
	}
	public void Retrive_Enter_Amount() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.WHITE);

		// Inner panel with GridBagLayout for alignment
		JPanel innerPanel = new JPanel(new GridBagLayout());
		innerPanel.setOpaque(false); // Make the panel transparent for background visibility
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Enter amount label
		JLabel enteramount = new JLabel("Enter the amount in TND:");
		enteramount.setForeground(new Color(4, 109, 181));
		enteramount.setFont(new Font("Tahoma", Font.BOLD, 19));
		enteramount.setOpaque(true);
		enteramount.setBackground(new Color(208, 232, 252));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		innerPanel.add(enteramount, gbc);

		// Amount text field
		Retrive_Enter_Amount_textField = new JTextField();
		Retrive_Enter_Amount_textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		innerPanel.add(Retrive_Enter_Amount_textField, gbc);

		// Validate button
		Retrive_Enter_Amount_Validate = new JButton("Validate");
		Retrive_Enter_Amount_Validate.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/tick-mark.png"))); // Corrected image path
		Retrive_Enter_Amount_Validate.setForeground(Color.WHITE);
		Retrive_Enter_Amount_Validate.setBackground(new Color(0, 168, 222)); // Updated background color
		Retrive_Enter_Amount_Validate.setFont(new Font("Tahoma", Font.BOLD, 16));
		Retrive_Enter_Amount_Validate.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 2;
		innerPanel.add(Retrive_Enter_Amount_Validate, gbc);

		// Go Back button
		Retrive_Enter_Amount_goback = new JButton("Go Back");
		Retrive_Enter_Amount_goback.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/reply.png"))); // Corrected image path
		Retrive_Enter_Amount_goback.setForeground(Color.WHITE);
		Retrive_Enter_Amount_goback.setBackground(new Color(220, 53, 69)); // Updated background color
		Retrive_Enter_Amount_goback.setFont(new Font("Tahoma", Font.BOLD, 16));
		Retrive_Enter_Amount_goback.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 3;
		innerPanel.add(Retrive_Enter_Amount_goback, gbc);


		// Background image
		JLabel backgroundLabel = new JLabel(new ImageIcon(getClass().getResource("/interface_graphique/background2.jpg"))); // Corrected image path
		panel.add(backgroundLabel, BorderLayout.CENTER);
		backgroundLabel.setLayout(new BorderLayout()); // Set layout to BorderLayout for innerPanel positioning
		backgroundLabel.add(innerPanel, BorderLayout.CENTER);

		frmMyBank.setContentPane(panel);
		frmMyBank.validate();
		frmMyBank.repaint();
	}
	public void Retrive_Success() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.WHITE);

		// Inner panel with GridBagLayout for alignment
		JPanel innerPanel = new JPanel(new GridBagLayout());
		innerPanel.setOpaque(false); // Make the panel transparent for background visibility
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Success message label
		JLabel lblNewLabel = new JLabel("Task Done Successfully");
		lblNewLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(208, 232, 252));
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/check.png"))); // Corrected image path
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		innerPanel.add(lblNewLabel, gbc);

		// Back to menu button
		Retrive_Success_Back = new JButton("Back To Menu");
		Retrive_Success_Back.addActionListener(this);
		Retrive_Success_Back.setForeground(Color.WHITE);
		Retrive_Success_Back.setBackground(new Color(0, 168, 222)); // Updated background color
		Retrive_Success_Back.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/reply.png"))); // Corrected image path
		Retrive_Success_Back.setFont(new Font("Tahoma", Font.PLAIN, 25));
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		innerPanel.add(Retrive_Success_Back, gbc);

		// Exit button
		Retrive_Success_Exit = new JButton("Exit");
		Retrive_Success_Exit.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/exit.png"))); // Corrected image path
		Retrive_Success_Exit.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		Retrive_Success_Exit.setForeground(Color.WHITE);
		Retrive_Success_Exit.setBackground(new Color(220, 53, 69)); // Updated background color
		Retrive_Success_Exit.setFont(new Font("Tahoma", Font.PLAIN, 26));
		Retrive_Success_Exit.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 1;
		innerPanel.add(Retrive_Success_Exit, gbc);

		// Background image
		JLabel backgroundLabel = new JLabel(new ImageIcon(getClass().getResource("/interface_graphique/background2.jpg"))); // Corrected image path
		panel.add(backgroundLabel, BorderLayout.CENTER);
		backgroundLabel.setLayout(new BorderLayout()); // Set layout to BorderLayout for innerPanel positioning
		backgroundLabel.add(innerPanel, BorderLayout.CENTER);

		frmMyBank.setContentPane(panel);
		frmMyBank.validate();
		frmMyBank.repaint();
	}
	public void WrongInputRetrieve() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.WHITE);

		// Inner panel with GridBagLayout for alignment
		JPanel innerPanel = new JPanel(new GridBagLayout());
		innerPanel.setOpaque(false); // Make the panel transparent for background visibility
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Label for wrong input message
		JLabel lblNewLabel = new JLabel("Wrong Input!");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 33));
		lblNewLabel.setForeground(new Color(4, 109, 181));
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/x-circle.png"))); // Corrected image path
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		innerPanel.add(lblNewLabel, gbc);

		// Retry button
		retry_eneter_amount = new JButton("Retry");
		retry_eneter_amount.setForeground(new Color(0, 168, 222));
		retry_eneter_amount.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/reload.png"))); // Corrected image path
		retry_eneter_amount.setFont(new Font("Tahoma", Font.PLAIN, 25));
		retry_eneter_amount.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 1;
		innerPanel.add(retry_eneter_amount, gbc);

		// Exit button
		WrongInput_Exit = new JButton("Exit");
		WrongInput_Exit.setForeground(new Color(0, 168, 222));
		WrongInput_Exit.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/exit.png"))); // Corrected image path
		WrongInput_Exit.setFont(new Font("Tahoma", Font.PLAIN, 25));
		WrongInput_Exit.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 1;
		innerPanel.add(WrongInput_Exit, gbc);

		// Background image
		JLabel backgroundLabel = new JLabel(new ImageIcon(getClass().getResource("/interface_graphique/background2.jpg"))); // Corrected image path
		panel.add(backgroundLabel, BorderLayout.CENTER);
		backgroundLabel.setLayout(new BorderLayout()); // Set layout to BorderLayout for innerPanel positioning
		backgroundLabel.add(innerPanel, BorderLayout.CENTER);

		frmMyBank.setContentPane(panel);
		frmMyBank.validate();
		frmMyBank.repaint();
	}

	public void Cashdeposit() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.WHITE);

		JPanel innerPanel = new JPanel(new GridBagLayout());
		innerPanel.setOpaque(false); // Make the panel transparent for background visibility
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Amount label
		JLabel amount = new JLabel("Choose the amount in TND:");
		amount.setForeground(new Color(0, 0, 0));
		amount.setFont(new Font("Sylfaen", Font.BOLD, 19));
		amount.setOpaque(true);
		amount.setBackground(new Color(208, 232, 252));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		innerPanel.add(amount, gbc);

		// Buttons with updated background color
		Deposit_dix = new JButton("10");
		Deposit_dix.setForeground(new Color(0, 168, 222));
		Deposit_dix.setBackground(Color.WHITE); // Updated background color
		Deposit_dix.setFont(new Font("Sylfaen", Font.BOLD, 14));
		Deposit_dix.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		innerPanel.add(Deposit_dix, gbc);

		Deposit_vingt = new JButton("20");
		Deposit_vingt.setForeground(new Color(0, 168, 222));
		Deposit_vingt.setBackground(Color.WHITE); // Updated background color
		Deposit_vingt.setFont(new Font("Sylfaen", Font.BOLD, 14));
		Deposit_vingt.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 1;
		innerPanel.add(Deposit_vingt, gbc);

		Deposit_khamsin = new JButton("50");
		Deposit_khamsin.setForeground(new Color(0, 168, 222));
		Deposit_khamsin.setBackground(Color.WHITE); // Updated background color
		Deposit_khamsin.setFont(new Font("Sylfaen", Font.BOLD, 14));
		Deposit_khamsin.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 2;
		innerPanel.add(Deposit_khamsin, gbc);

		Deposit_cent = new JButton("100");
		Deposit_cent.setForeground(new Color(0, 168, 222));
		Deposit_cent.setBackground(Color.WHITE); // Updated background color
		Deposit_cent.setFont(new Font("Sylfaen", Font.BOLD, 14));
		Deposit_cent.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 2;
		innerPanel.add(Deposit_cent, gbc);

		Deposit_mytyn = new JButton("200");
		Deposit_mytyn.setForeground(new Color(0, 168, 222));
		Deposit_mytyn.setBackground(Color.WHITE); // Updated background color
		Deposit_mytyn.setFont(new Font("Sylfaen", Font.BOLD, 14));
		Deposit_mytyn.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 3;
		innerPanel.add(Deposit_mytyn, gbc);

		Deposit_custom = new JButton("Custom");
		Deposit_custom.setForeground(Color.WHITE);
		Deposit_custom.setBackground(new Color(0, 168, 222)); // Updated background color
		Deposit_custom.setFont(new Font("Sylfaen", Font.BOLD, 14));
		Deposit_custom.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 3;
		innerPanel.add(Deposit_custom, gbc);

		Deposit_back = new JButton("Go Back");
		Deposit_back.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/reply.png"))); // Corrected image path
		Deposit_back.setForeground(Color.WHITE);
		Deposit_back.setBackground(new Color(220, 53, 69)); // Updated background color
		Deposit_back.setFont(new Font("Sylfaen", Font.BOLD, 14));
		Deposit_back.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		innerPanel.add(Deposit_back, gbc);

		// Background image
		JLabel backgroundLabel = new JLabel(new ImageIcon(getClass().getResource("/interface_graphique/background2.jpg"))); // Corrected image path
		panel.add(backgroundLabel, BorderLayout.CENTER);
		backgroundLabel.setLayout(new BorderLayout());
		backgroundLabel.add(innerPanel, BorderLayout.CENTER);

		frmMyBank.setContentPane(panel);
		frmMyBank.validate();
		frmMyBank.repaint();
	}
	public void Deposit_Enter_Amount() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.WHITE);

		// Inner panel with GridBagLayout for alignment
		JPanel innerPanel = new JPanel(new GridBagLayout());
		innerPanel.setOpaque(false); // Make the panel transparent for background visibility
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Enter amount label
		JLabel enteramount = new JLabel("Enter the amount in TND:");
		enteramount.setForeground(new Color(4, 109, 181));
		enteramount.setFont(new Font("Tahoma", Font.BOLD, 19));
		enteramount.setOpaque(true);
		enteramount.setBackground(new Color(208, 232, 252));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		innerPanel.add(enteramount, gbc);

		// Amount text field
		Deposit_Enter_Amount_textField = new JTextField();
		Deposit_Enter_Amount_textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		innerPanel.add(Deposit_Enter_Amount_textField, gbc);

		// Validate button
		Deposit_Enter_Amount_Validate = new JButton("Validate");
		Deposit_Enter_Amount_Validate.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/tick-mark.png"))); // Corrected image path
		Deposit_Enter_Amount_Validate.setForeground(Color.WHITE);
		Deposit_Enter_Amount_Validate.setBackground(new Color(0, 168, 222)); // Updated background color
		Deposit_Enter_Amount_Validate.setFont(new Font("Tahoma", Font.BOLD, 16));
		Deposit_Enter_Amount_Validate.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 2;
		innerPanel.add(Deposit_Enter_Amount_Validate, gbc);

		// Go Back button
		Deposit_Enter_Amount_goback = new JButton("Go Back");
		Deposit_Enter_Amount_goback.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/reply.png"))); // Corrected image path
		Deposit_Enter_Amount_goback.setForeground(Color.WHITE);
		Deposit_Enter_Amount_goback.setBackground(new Color(220, 53, 69)); // Updated background color
		Deposit_Enter_Amount_goback.setFont(new Font("Tahoma", Font.BOLD, 16));
		Deposit_Enter_Amount_goback.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 3;
		innerPanel.add(Deposit_Enter_Amount_goback, gbc);


		// Background image
		JLabel backgroundLabel = new JLabel(new ImageIcon(getClass().getResource("/interface_graphique/background2.jpg"))); // Corrected image path
		panel.add(backgroundLabel, BorderLayout.CENTER);
		backgroundLabel.setLayout(new BorderLayout()); // Set layout to BorderLayout for innerPanel positioning
		backgroundLabel.add(innerPanel, BorderLayout.CENTER);

		frmMyBank.setContentPane(panel);
		frmMyBank.validate();
		frmMyBank.repaint();
	}
	public void MinDeposit() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.WHITE);

		// Inner panel with GridBagLayout for alignment
		JPanel innerPanel = new JPanel(new GridBagLayout());
		innerPanel.setOpaque(false); // Make the panel transparent for background visibility
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Warning label
		JLabel warning = new JLabel("The minimum of Deposit is 10 TND!");
		warning.setForeground(new Color(4, 109, 181));
		warning.setFont(new Font("Tahoma", Font.PLAIN, 22));
		warning.setOpaque(true);
		warning.setBackground(new Color(208, 232, 252));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		innerPanel.add(warning, gbc);

		// Retry button
		retry = new JButton("Retry");
		retry.setForeground(Color.WHITE);
		retry.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/reload.png"))); // Corrected image path
		retry.setBackground(new Color(0, 168, 222)); // Updated background color
		retry.setFont(new Font("Tahoma", Font.PLAIN, 18));
		retry.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 1;
		innerPanel.add(retry, gbc);

		// Exit button
		exit = new JButton("Exit");
		exit.setForeground(Color.WHITE);
		exit.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/exit.png"))); // Corrected image path
		exit.setBackground(new Color(220, 53, 69)); // Updated background color
		exit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		exit.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 1;
		innerPanel.add(exit, gbc);

		// Background image
		JLabel backgroundLabel = new JLabel(new ImageIcon(getClass().getResource("/interface_graphique/background2.jpg"))); // Corrected image path
		panel.add(backgroundLabel, BorderLayout.CENTER);
		backgroundLabel.setLayout(new BorderLayout()); // Set layout to BorderLayout for innerPanel positioning
		backgroundLabel.add(innerPanel, BorderLayout.CENTER);

		frmMyBank.setContentPane(panel);
		frmMyBank.validate();
		frmMyBank.repaint();
	}
	public void insuffissantBalance() {
		// Panel setup
		panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.WHITE);

		// Content panel for labels and buttons
		JPanel contentPanel = new JPanel(new GridBagLayout());
		contentPanel.setOpaque(false);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Insufficient balance warning label
		JLabel warningLabel = new JLabel("Insufficient balance!");
		warningLabel.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/cashless.png"))); // Adjusted path
		warningLabel.setFont(new Font("Tahoma", Font.PLAIN, 29));
		warningLabel.setOpaque(true);
		warningLabel.setBackground(new Color(208, 232, 252));
		warningLabel.setForeground(new Color(0, 0, 0));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		contentPanel.add(warningLabel, gbc);

		// Retry button
		retry = new JButton("Retry");
		retry.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/reply.png"))); // Adjusted path
		retry.setFont(new Font("Tahoma", Font.PLAIN, 25));
		retry.setForeground(new Color(0, 168, 222));
		retry.setOpaque(true);
		retry.setBackground(Color.WHITE);
		retry.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		contentPanel.add(retry, gbc);

		// Exit button
		exit = new JButton("Exit");
		exit.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/exit.png"))); // Adjusted path
		exit.setFont(new Font("Tahoma", Font.PLAIN, 25));
		exit.setForeground(new Color(0, 168, 222));
		exit.addActionListener(this);
		exit.setOpaque(true);
		exit.setBackground(new Color(220, 53, 69));
		gbc.gridx = 1;
		gbc.gridy = 1;
		contentPanel.add(exit, gbc);

		// Background image
		JLabel backgroundLabel = new JLabel(new ImageIcon(getClass().getResource("/interface_graphique/Cash.jpg"))); // Adjusted path
		backgroundLabel.setLayout(new BorderLayout());
		backgroundLabel.add(contentPanel, BorderLayout.CENTER);

		panel.add(backgroundLabel, BorderLayout.CENTER);

		// Update the frame
		frmMyBank.setContentPane(panel);
		frmMyBank.validate();
		frmMyBank.repaint();
	}

	public void LimitAttempts() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.WHITE);

		JPanel innerPanel = new JPanel(new GridBagLayout());
		innerPanel.setOpaque(false); // Make the panel transparent for background visibility
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Label for limit reached message
		JLabel lblNewLabel = new JLabel("Limit attempts reached!");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(208, 232, 252));
		lblNewLabel.setFont(new Font("Sylfaen", Font.BOLD, 30));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		innerPanel.add(lblNewLabel, gbc);

		// Exit button with updated background color
		LimitAttempts_Exit = new JButton("Exit");
		LimitAttempts_Exit.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/exit.png"))); // Corrected image path
		LimitAttempts_Exit.setForeground(Color.WHITE);
		LimitAttempts_Exit.setBackground(new Color(220, 53, 69)); // Updated background color
		LimitAttempts_Exit.setFont(new Font("Sylfaen", Font.BOLD, 19));
		LimitAttempts_Exit.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		innerPanel.add(LimitAttempts_Exit, gbc);

		// Background image
		JLabel backgroundLabel = new JLabel(new ImageIcon(getClass().getResource("/interface_graphique/background2.jpg"))); // Corrected image path
		panel.add(backgroundLabel, BorderLayout.CENTER);
		backgroundLabel.setLayout(new BorderLayout());
		backgroundLabel.add(innerPanel, BorderLayout.CENTER);

		frmMyBank.setContentPane(panel);
		frmMyBank.validate();
		frmMyBank.repaint();
	}

	public void MinRetrieve() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.WHITE);

		// Inner panel with GridBagLayout for alignment
		JPanel innerPanel = new JPanel(new GridBagLayout());
		innerPanel.setOpaque(false); // Make the panel transparent for background visibility
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Warning label
		JLabel warning = new JLabel("The minimum of Retrieve is 10 TND!");
		warning.setForeground(new Color(4, 109, 181));
		warning.setFont(new Font("Tahoma", Font.PLAIN, 22));
		warning.setOpaque(true);
		warning.setBackground(new Color(208, 232, 252));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		innerPanel.add(warning, gbc);

		// Retry button
		retry = new JButton("Retry");
		retry.setForeground(Color.WHITE);
		retry.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/reload.png"))); // Corrected image path
		retry.setBackground(new Color(0, 168, 222)); // Updated background color
		retry.setFont(new Font("Tahoma", Font.PLAIN, 18));
		retry.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 1;
		innerPanel.add(retry, gbc);

		// Exit button
		exit = new JButton("Exit");
		exit.setForeground(Color.WHITE);
		exit.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/exit.png"))); // Corrected image path
		exit.setBackground(new Color(220, 53, 69)); // Updated background color
		exit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		exit.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 1;
		innerPanel.add(exit, gbc);

		// Background image
		JLabel backgroundLabel = new JLabel(new ImageIcon(getClass().getResource("/interface_graphique/background2.jpg"))); // Corrected image path
		panel.add(backgroundLabel, BorderLayout.CENTER);
		backgroundLabel.setLayout(new BorderLayout()); // Set layout to BorderLayout for innerPanel positioning
		backgroundLabel.add(innerPanel, BorderLayout.CENTER);

		frmMyBank.setContentPane(panel);
		frmMyBank.validate();
		frmMyBank.repaint();
	}
	public void EmptyInput() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.WHITE);

		// Inner panel with GridBagLayout for alignment
		JPanel innerPanel = new JPanel(new GridBagLayout());
		innerPanel.setOpaque(false); // Make the panel transparent for background visibility
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Label for empty input message
		JLabel warning = new JLabel("Empty Input!");
		warning.setForeground(new Color(0, 0, 0));
		warning.setFont(new Font("Tahoma", Font.PLAIN, 22));
		warning.setOpaque(true);
		warning.setBackground(new Color(208, 232, 252));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		innerPanel.add(warning, gbc);

		// Retry button
		retry = new JButton("Retry");
		retry.setForeground(new Color(0, 168, 222));
		retry.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/reload.png"))); // Corrected image path
		retry.setFont(new Font("Tahoma", Font.PLAIN, 18));
		retry.setOpaque(true);
		retry.setBackground(Color.WHITE);
		retry.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 1;
		innerPanel.add(retry, gbc);

		// Exit button
		exit = new JButton("Exit");
		exit.setForeground(Color.WHITE);
		exit.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		exit.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/exit.png"))); // Corrected image path
		exit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		exit.setOpaque(true);
		exit.setBackground(new Color(220, 53, 69));
		exit.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 2;
		innerPanel.add(exit, gbc);

		// Background image
		JLabel backgroundLabel = new JLabel(new ImageIcon(getClass().getResource("/interface_graphique/background2.jpg"))); // Corrected image path
		panel.add(backgroundLabel, BorderLayout.CENTER);
		backgroundLabel.setLayout(new BorderLayout()); // Set layout to BorderLayout for innerPanel positioning
		backgroundLabel.add(innerPanel, BorderLayout.CENTER);

		frmMyBank.setContentPane(panel);
		frmMyBank.validate();
		frmMyBank.repaint();
	}
	public void multiple() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.WHITE);

		// Inner panel with GridBagLayout for alignment
		JPanel innerPanel = new JPanel(new GridBagLayout());
		innerPanel.setOpaque(false); // Make the panel transparent for background visibility
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Label for multiple validation message
		JLabel warning = new JLabel("Amount must be a multiple of 10!");
		warning.setForeground(new Color(0, 0, 0));
		warning.setFont(new Font("Tahoma", Font.PLAIN, 20));
		warning.setOpaque(true);
		warning.setBackground(new Color(208, 232, 252));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		innerPanel.add(warning, gbc);

		// Retry button
		retry = new JButton("Retry");
		retry.setForeground(new Color(0, 168, 222));
		retry.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/reload.png"))); // Corrected image path
		retry.setFont(new Font("Tahoma", Font.PLAIN, 18));
		retry.setOpaque(true);
		retry.setBackground(Color.WHITE);
		retry.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 1;
		innerPanel.add(retry, gbc);

		// Exit button
		exit = new JButton("Exit");
		exit.setForeground(Color.WHITE);
		exit.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		exit.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/exit.png"))); // Corrected image path
		exit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		exit.setOpaque(true);
		exit.setBackground(new Color(220, 53, 69));
		exit.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 2;
		innerPanel.add(exit, gbc);

		// Background image
		JLabel backgroundLabel = new JLabel(new ImageIcon(getClass().getResource("/interface_graphique/background2.jpg"))); // Corrected image path
		panel.add(backgroundLabel, BorderLayout.CENTER);
		backgroundLabel.setLayout(new BorderLayout()); // Set layout to BorderLayout for innerPanel positioning
		backgroundLabel.add(innerPanel, BorderLayout.CENTER);

		frmMyBank.setContentPane(panel);
		frmMyBank.validate();
		frmMyBank.repaint();
	}
	public void tooLong() {
		// Panel setup
		panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.WHITE);

		// Content panel for warning and buttons
		JPanel contentPanel = new JPanel(new GridBagLayout());
		contentPanel.setOpaque(false);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Warning label
		JLabel warningLabel = new JLabel("Amount exceeds max limit (5 characters)!");
		warningLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		warningLabel.setForeground(new Color(0, 0, 0));
		warningLabel.setOpaque(true);
		warningLabel.setBackground(new Color(208, 232, 252));
		warningLabel.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/error.png"))); // Adjusted path
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		contentPanel.add(warningLabel, gbc);

		// Retry button
		retry = new JButton("Retry");
		retry.setFont(new Font("Tahoma", Font.PLAIN, 18));
		retry.setForeground(new Color(0, 168, 222));
		retry.setOpaque(true);
		retry.setBackground(Color.WHITE);
		retry.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/reload.png"))); // Adjusted path
		retry.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		contentPanel.add(retry, gbc);

		// Exit button
		exit = new JButton("Exit");
		exit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		exit.setForeground(new Color(0, 168, 222));
		exit.setOpaque(true);
		exit.setBackground(new Color(220, 53, 69));
		exit.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/exit.png"))); // Adjusted path
		exit.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 2;
		contentPanel.add(exit, gbc);

		// Background image
		JLabel backgroundLabel = new JLabel(new ImageIcon(getClass().getResource("/interface_graphique/CashewBank1.png"))); // Adjusted path
		backgroundLabel.setLayout(new BorderLayout());
		backgroundLabel.add(contentPanel, BorderLayout.CENTER);

		panel.add(backgroundLabel, BorderLayout.CENTER);

		// Update the frame
		frmMyBank.setContentPane(panel);
		frmMyBank.validate();
		frmMyBank.repaint();
	}


	public void insuffissantBalanceAmountFixed() {
		// Panel setup
		panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.WHITE);

		// Content panel for labels and buttons
		JPanel contentPanel = new JPanel(new GridBagLayout());
		contentPanel.setOpaque(false);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Insufficient balance warning label
		JLabel warningLabel = new JLabel("Insufficient balance!");
		warningLabel.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/cashless.png"))); // Adjusted path
		warningLabel.setFont(new Font("Tahoma", Font.PLAIN, 29));
		warningLabel.setForeground(new Color(0, 0, 0));
		warningLabel.setOpaque(true);
		warningLabel.setBackground(new Color(208, 232, 252));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		contentPanel.add(warningLabel, gbc);

		// Retry button
		Retrive_insuff_Back = new JButton("Retry");
		Retrive_insuff_Back.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/reply.png"))); // Adjusted path
		Retrive_insuff_Back.setFont(new Font("Tahoma", Font.PLAIN, 25));
		Retrive_insuff_Back.setForeground(new Color(0, 168, 222));
		Retrive_insuff_Back.setOpaque(true);
		Retrive_insuff_Back.setBackground(Color.WHITE);
		Retrive_insuff_Back.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 1;
		contentPanel.add(Retrive_insuff_Back, gbc);

		// Exit button
		exit = new JButton("Exit");
		exit.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/exit.png"))); // Adjusted path
		exit.setFont(new Font("Tahoma", Font.PLAIN, 25));
		exit.setForeground(Color.WHITE);
		exit.setOpaque(true);
		exit.setBackground(new Color(220, 53, 69));
		exit.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 2;
		contentPanel.add(exit, gbc);

		// Background image
		JLabel backgroundLabel = new JLabel(new ImageIcon(getClass().getResource("/interface_graphique/Cash.jpg"))); // Adjusted path
		backgroundLabel.setLayout(new BorderLayout());
		backgroundLabel.add(contentPanel, BorderLayout.CENTER);

		panel.add(backgroundLabel, BorderLayout.CENTER);

		// Update the frame
		frmMyBank.setContentPane(panel);
		frmMyBank.validate();
		frmMyBank.repaint();
	}


	public void MaxReached() {
		panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.WHITE);

		// Center content panel
		JPanel contentPanel = new JPanel(new GridBagLayout());
		contentPanel.setOpaque(false);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Warning label
		JLabel warning = new JLabel("Maximum account limit reached!");
		warning.setForeground(new Color(0, 0, 0));
		warning.setFont(new Font("Tahoma", Font.PLAIN, 22));
		warning.setOpaque(true);
		warning.setBackground(new Color(208, 232, 252));
		warning.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/error.png"))); // Corrected path
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		contentPanel.add(warning, gbc);

		// Retry button
		retry = new JButton("Retry");
		retry.setForeground(new Color(0, 168, 222));
		retry.setFont(new Font("Tahoma", Font.PLAIN, 18));
		retry.setOpaque(true);
		retry.setBackground(Color.WHITE);
		retry.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/reload.png"))); // Corrected path
		retry.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 1;
		contentPanel.add(retry, gbc);

		// Exit button
		exit = new JButton("Exit");
		exit.setForeground(Color.WHITE);
		exit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		exit.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/exit.png"))); // Corrected path
		exit.setOpaque(true);
		exit.setBackground(new Color(220, 53, 69));
		exit.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 2;
		contentPanel.add(exit, gbc);

		// Background image
		JLabel backgroundLabel = new JLabel(new ImageIcon(getClass().getResource("/interface_graphique/CashewBank1.png"))); // Corrected path
		backgroundLabel.setLayout(new BorderLayout());
		backgroundLabel.add(contentPanel, BorderLayout.CENTER);

		panel.add(backgroundLabel, BorderLayout.CENTER);

		frmMyBank.setContentPane(panel);
		frmMyBank.validate();
		frmMyBank.repaint();
	}
	public void MaxReachedAmountFixed() {
		panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.WHITE);

		// Inner panel for structured layout
		JPanel innerPanel = new JPanel(new GridBagLayout());
		innerPanel.setOpaque(false); // Transparent for background visibility
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Warning label
		JLabel warning = new JLabel("Maximum account limit reached!");
		warning.setForeground(new Color(0, 0, 0));
		warning.setFont(new Font("Tahoma", Font.PLAIN, 22));
		warning.setOpaque(true);
		warning.setBackground(new Color(208, 232, 252));
		warning.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/error.png"))); // Corrected path
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		innerPanel.add(warning, gbc);

		// Retry button
		retry_max_reached_fixed = new JButton("Retry");
		retry_max_reached_fixed.setForeground(new Color(0, 168, 222));
		retry_max_reached_fixed.setFont(new Font("Tahoma", Font.PLAIN, 18));
		retry_max_reached_fixed.setOpaque(true);
		retry_max_reached_fixed.setBackground(Color.WHITE);
		retry_max_reached_fixed.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/reload.png"))); // Corrected path
		retry_max_reached_fixed.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 1;
		innerPanel.add(retry_max_reached_fixed, gbc);

		// Exit button
		exit = new JButton("Exit");
		exit.setForeground(new Color(0, 168, 222));
		exit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		exit.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/exit.png"))); // Corrected path
		exit.setOpaque(true);
		exit.setBackground(new Color(220, 53, 69));
		exit.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 2;
		innerPanel.add(exit, gbc);

		// Background image
		JLabel backgroundLabel = new JLabel(new ImageIcon(getClass().getResource("/interface_graphique/CashewBank1.png"))); // Corrected path
		panel.add(backgroundLabel, BorderLayout.CENTER);
		backgroundLabel.setLayout(new BorderLayout()); // Allow layout on the background
		backgroundLabel.add(innerPanel, BorderLayout.CENTER);

		frmMyBank.setContentPane(panel);
		frmMyBank.validate();
		frmMyBank.repaint();
	}
	public void WrongLengthCode() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.WHITE);

		// Inner panel with GridBagLayout for alignment
		JPanel innerPanel = new JPanel(new GridBagLayout());
		innerPanel.setOpaque(false); // Transparent for background visibility
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Label for error message
		JLabel lblNewLabel = new JLabel("Wrong length Code!");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(208, 232, 252));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/alert-octagon.png"))); // Corrected image path
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		innerPanel.add(lblNewLabel, gbc);

		// Retry button
		Retry_Wrong_Code = new JButton("Retry");
		Retry_Wrong_Code.setForeground(new Color(0, 168, 222));
		Retry_Wrong_Code.setFont(new Font("Tahoma", Font.PLAIN, 25));
		Retry_Wrong_Code.setOpaque(true);
		Retry_Wrong_Code.setBackground(Color.WHITE);
		Retry_Wrong_Code.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/reload.png"))); // Corrected image path
		Retry_Wrong_Code.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 1;
		innerPanel.add(Retry_Wrong_Code, gbc);

		// Exit button
		Exit_Wrong_Code = new JButton("Exit");
		Exit_Wrong_Code.setForeground(new Color(0, 168, 222));
		Exit_Wrong_Code.setFont(new Font("Tahoma", Font.PLAIN, 25));
		Exit_Wrong_Code.setOpaque(true);
		Exit_Wrong_Code.setBackground(new Color(220, 53, 69));
		Exit_Wrong_Code.setIcon(new ImageIcon(getClass().getResource("/interface_graphique/exit.png"))); // Corrected image path
		Exit_Wrong_Code.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 2;
		innerPanel.add(Exit_Wrong_Code, gbc);

		// Background image
		JLabel backgroundLabel = new JLabel(new ImageIcon(getClass().getResource("/interface_graphique/background2.jpg"))); // Corrected image path
		panel.add(backgroundLabel, BorderLayout.CENTER);
		backgroundLabel.setLayout(new BorderLayout()); // Set layout to BorderLayout for innerPanel positioning
		backgroundLabel.add(innerPanel, BorderLayout.CENTER);

		frmMyBank.setContentPane(panel);
		frmMyBank.validate();
		frmMyBank.repaint();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==Enter_Code_PIN)
		{
			String Field = PIN_INPUT.getText();
			if (Field.length()==0)
			{
				EmptyInput();
			}else {
				if(Field.length()!=4){
					WrongLengthCode();
				}else {
					if(Field.matches("\\d+")) {
						byte data []=new byte[Field.length()];
						for (int i=Field.length()-1 ; i>=0 ; i--)
						{
							try {
								data[i]=  Byte.parseByte(String.valueOf(Field.charAt(i)) );
							} catch (NumberFormatException nfe) {
								WrongInput();
								break;
							}}

						try {
							apdu=client.Msg( (byte) 0x40, (byte) 0x7f,data,(byte) 0x05);
						} catch (IOException e1) {
							System.out.print("here");
							e1.printStackTrace();
						} catch (CadTransportException e1) {
							System.out.print("here2");
							e1.printStackTrace();
						}
						if (apdu.getStatus() == 0x9000)
						{Distributeur();}
						else if (apdu.dataOut[0]==0)
						{LimitAttempts();}
						else
						{WrongCode();}
					}else {WrongInputCode();}
				}
			}

		}
		if(e.getSource()==retryCode) {
			ATM();
		}
		if(e.getSource()==Exit_Code_PIN)
		{
			client.Deselect();
			System.exit(0);

		}
		if (e.getSource()==Retry_Wrong_Code)
		{
			ATM();
		}
		if (e.getSource() ==Exit_Wrong_Code)
		{
			client.Deselect();
			System.exit(0);
		}
		if(e.getSource()==WrongInput_Retry)
		{
			ATM();
		}
		if(e.getSource()==WrongInput_Exit)
		{
			client.Deselect();
			System.exit(0);
		}
		if (e.getSource()==LimitAttempts_Exit)
		{
			client.Deselect();
			System.exit(0);
		}
		//Retrieve Money
		if(e.getSource()==Retrieve)
		{
			Retrivemoney();
		}
		if(e.getSource()==Retrivemoney_dix )
		{	try {
			apdu=client.Msg( (byte) 0x20, (byte) 0x7f,null,(byte) 0x00);
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (CadTransportException e1) {
			e1.printStackTrace();
		}
			byte c []= new byte[5];
			c=apdu.dataOut;
			int aux=10000;
			int x=0;
			for (int i=0 ; i<c.length ; i++)
			{
				x=x+c[i]*aux;
				aux=aux/10;
			}


			if(x<10) {
				insuffissantBalanceAmountFixed();

			}else {
				try {
					apdu=client.Msg( (byte) 0x01, (byte) 0x7f,null,(byte) 0x00);
				} catch (IOException e1) {
					System.out.print("here");
					e1.printStackTrace();
				} catch (CadTransportException e1) {
					System.out.print("here2");
					e1.printStackTrace();
				}
				if (apdu.getStatus() == 0x9000)
				{Retrive_Success();}
			}

		}
		if(e.getSource()==Retrivemoney_vingt)
		{
			try {
				apdu=client.Msg( (byte) 0x20, (byte) 0x7f,null,(byte) 0x00);
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (CadTransportException e1) {
				e1.printStackTrace();
			}
			byte c []= new byte[5];
			c=apdu.dataOut;
			int aux=10000;
			int x=0;
			for (int i=0 ; i<c.length ; i++)
			{
				x=x+c[i]*aux;
				aux=aux/10;
			}


			if(x<20) {
				insuffissantBalanceAmountFixed();

			}else {
				try {
					apdu=client.Msg( (byte) 0x02, (byte) 0x7f,null,(byte) 0x00);
				} catch (IOException e1) {
					System.out.print("here");
					e1.printStackTrace();
				} catch (CadTransportException e1) {
					System.out.print("here2");
					e1.printStackTrace();
				}
				if (apdu.getStatus() == 0x9000)
				{Retrive_Success();}
			}

		}
		if(e.getSource()==Retrivemoney_khamsin) {
			try {
				apdu=client.Msg( (byte) 0x20, (byte) 0x7f,null,(byte) 0x00);
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (CadTransportException e1) {
				e1.printStackTrace();
			}
			byte c []= new byte[5];
			c=apdu.dataOut;
			int aux=10000;
			int x=0;
			for (int i=0 ; i<c.length ; i++)
			{
				x=x+c[i]*aux;
				aux=aux/10;
			}


			if(x<50) {
				insuffissantBalanceAmountFixed();

			}else {
				try {
					apdu=client.Msg( (byte) 0x03, (byte) 0x7f,null,(byte) 0x00);
				} catch (IOException e1) {
					System.out.print("here");
					e1.printStackTrace();
				} catch (CadTransportException e1) {
					System.out.print("here2");
					e1.printStackTrace();
				}
				if (apdu.getStatus() == 0x9000)
				{Retrive_Success();}
			}
		}
		if(e.getSource()==Retrivemoney_cent )
		{
			try {
				apdu=client.Msg( (byte) 0x20, (byte) 0x7f,null,(byte) 0x00);
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (CadTransportException e1) {
				e1.printStackTrace();
			}
			byte c []= new byte[5];
			c=apdu.dataOut;
			int aux=10000;
			int x=0;
			for (int i=0 ; i<c.length ; i++)
			{
				x=x+c[i]*aux;
				aux=aux/10;
			}


			if(x<100) {
				insuffissantBalanceAmountFixed();

			}else {
				try {
					apdu=client.Msg( (byte) 0x04, (byte) 0x7f,null,(byte) 0x00);
				} catch (IOException e1) {
					System.out.print("here");
					e1.printStackTrace();
				} catch (CadTransportException e1) {
					System.out.print("here2");
					e1.printStackTrace();
				}
				if (apdu.getStatus() == 0x9000)
				{Retrive_Success();}
			}

		}
		if(e.getSource()==Retrivemoney_mytyn) {
			try {
				apdu=client.Msg( (byte) 0x20, (byte) 0x7f,null,(byte) 0x00);
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (CadTransportException e1) {
				e1.printStackTrace();
			}
			byte c []= new byte[5];
			c=apdu.dataOut;
			int aux=10000;
			int x=0;
			for (int i=0 ; i<c.length ; i++)
			{
				x=x+c[i]*aux;
				aux=aux/10;
			}


			if(x<200) {
				insuffissantBalanceAmountFixed();

			}else {
				try {
					apdu=client.Msg( (byte) 0x05, (byte) 0x7f,null,(byte) 0x00);
				} catch (IOException e1) {
					System.out.print("here");
					e1.printStackTrace();
				} catch (CadTransportException e1) {
					System.out.print("here2");
					e1.printStackTrace();
				}
				if (apdu.getStatus() == 0x9000)
				{Retrive_Success();}
			}
		}
		if(e.getSource()==Retrive_Enter_Amount_goback)
		{
			Retrivemoney();
		}
		if(e.getSource()==Retrivemoney_custom )
		{
			Retrive_Enter_Amount();
		}


		if (e.getSource()==Retrive_Enter_Amount_Validate)
		{	 String Field = Retrive_Enter_Amount_textField.getText();

			try {
				apdu=client.Msg( (byte) 0x20, (byte) 0x7f,null,(byte) 0x00);
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (CadTransportException e1) {
				e1.printStackTrace();
			}
			byte c []= new byte[5];
			c=apdu.dataOut;
			int aux=10000;
			int x=0;
			for (int i=0 ; i<c.length ; i++)
			{
				x=x+c[i]*aux;
				aux=aux/10;
			}



			if (Field.length()==0  )
			{ System.out.println(Field.length());
				EmptyInput();}
			else if(Field.length()>5)
			{tooLong(); }
			else {
				int y=0;
				try {
					y=Integer.parseInt(Field);
				}
				catch(NumberFormatException nfe) {
					System.out.println("NumberFormatException: " + nfe.getMessage());
					WrongInputRetrieve();
					return;
				}
				if(Integer.parseInt(Field)<10)
				{MinRetrieve();}
				else if(Integer.parseInt(Field)>x)
				{System.out.println("testt");
					insuffissantBalance();}
				else {
					byte data []=new byte[5];
					for (int i=0 ; i<=Field.length()-1 ; i++)
					{
						try {
							data[ data.length-1-i]=  Byte.parseByte(String.valueOf(Field.charAt(Field.length()-1-i)) );
						} catch (NumberFormatException nfe) {
							WrongInput();
							break;
						}}
					if (data[4]!=0x00) {multiple();}
					else {
						try {
							apdu=client.Msg( (byte) 0x06, (byte) 0x7f,data,(byte) 0x05);
						} catch (IOException e1) {
							System.out.print("here");
							e1.printStackTrace();
						} catch (CadTransportException e1) {
							System.out.print("here2");
							e1.printStackTrace();
						}
						if (apdu.getStatus() == 0x9000)
						{Retrive_Success();}
					}
				}


			}


		}
		if (e.getSource()==retry)
		{
			Retrive_Enter_Amount();
		}
		if (e.getSource()==exit)
		{
			System.exit(0);
		}
		if (e.getSource()==Retrive_Success_Exit)
		{
			client.Deselect();
			System.exit(0);
		}
		if (e.getSource()==Retrive_Success_Back)
		{
			Retrivemoney();
		}
		if (e.getSource()==Retrive_insuff_Back)
		{
			Retrivemoney();
		}
		if (e.getSource()==Retrivemoney_back) {
			Distributeur();
		}
		if(e.getSource()==retry_eneter_amount) {
			Retrive_Enter_Amount();
		}
		//balance check
		if(e.getSource()==checkbalance)
		{
			Balance();
			try {
				apdu=client.Msg( (byte) 0x20, (byte) 0x7f,null,(byte) 0x00);
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (CadTransportException e1) {
				e1.printStackTrace();
			}
			byte c []= new byte[5];
			c=apdu.dataOut;
			int aux=10000;
			int x=0;
			for (int i=0 ; i<c.length ; i++)
			{
				x=x+c[i]*aux;
				aux=aux/10;
			}
			enteramount.setText("Your Balance in TND :"+x);
		}
		if(e.getSource()==Balance_goback)
		{
			Distributeur();
		}
		if(e.getSource()==Balance_exit)
		{
			client.Deselect();
			System.exit(0);
		}
		if(e.getSource()==goback)
		{
			Distributeur();
		}
		if(e.getSource()==ExitHome){
			System.exit(0);
		}

		//*****************Cash Deposit************************

		if(e.getSource()==cashdeposit)
		{
			Cashdeposit();
		}
		if(e.getSource()==Deposit_dix )
		{
			try {
				apdu=client.Msg( (byte) 0x20, (byte) 0x7f,null,(byte) 0x00);
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (CadTransportException e1) {
				e1.printStackTrace();
			}
			byte c []= new byte[5];
			c=apdu.dataOut;
			int aux=10000;
			int x=0;
			for (int i=0 ; i<c.length ; i++)
			{
				x=x+c[i]*aux;
				aux=aux/10;
			}

			int y=x+10;
			if(y>10000) {
				MaxReachedAmountFixed();

			}else {
				try {
					apdu=client.Msg( (byte) 0x11, (byte) 0x7f,null,(byte) 0x00);
				} catch (IOException e1) {
					System.out.print("here");
					e1.printStackTrace();
				} catch (CadTransportException e1) {
					System.out.print("here2");
					e1.printStackTrace();
				}
				if (apdu.getStatus() == 0x9000)
				{Retrive_Success();}
			}
		}
		if(e.getSource()==Deposit_vingt)
		{
			try {
				apdu=client.Msg( (byte) 0x20, (byte) 0x7f,null,(byte) 0x00);
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (CadTransportException e1) {
				e1.printStackTrace();
			}
			byte c []= new byte[5];
			c=apdu.dataOut;
			int aux=10000;
			int x=0;
			for (int i=0 ; i<c.length ; i++)
			{
				x=x+c[i]*aux;
				aux=aux/10;
			}

			int y=x+20;
			if(y>10000) {
				MaxReachedAmountFixed();

			}else {
				try {
					apdu=client.Msg( (byte) 0x12, (byte) 0x7f,null,(byte) 0x00);
				} catch (IOException e1) {
					System.out.print("here");
					e1.printStackTrace();
				} catch (CadTransportException e1) {
					System.out.print("here2");
					e1.printStackTrace();
				}
				if (apdu.getStatus() == 0x9000)
				{Retrive_Success();}
			}
		}
		if(e.getSource()==Deposit_khamsin )
		{
			try {
				apdu=client.Msg( (byte) 0x20, (byte) 0x7f,null,(byte) 0x00);
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (CadTransportException e1) {
				e1.printStackTrace();
			}
			byte c []= new byte[5];
			c=apdu.dataOut;
			int aux=10000;
			int x=0;
			for (int i=0 ; i<c.length ; i++)
			{
				x=x+c[i]*aux;
				aux=aux/10;
			}

			int y=x+50;
			if(y>10000) {
				MaxReachedAmountFixed();

			}else {
				try {
					apdu=client.Msg( (byte) 0x13, (byte) 0x7f,null,(byte) 0x00);
				} catch (IOException e1) {
					System.out.print("here");
					e1.printStackTrace();
				} catch (CadTransportException e1) {
					System.out.print("here2");
					e1.printStackTrace();
				}
				if (apdu.getStatus() == 0x9000)
				{Retrive_Success();}
			}
		}
		if(e.getSource()==Deposit_cent) {
			try {
				apdu=client.Msg( (byte) 0x20, (byte) 0x7f,null,(byte) 0x00);
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (CadTransportException e1) {
				e1.printStackTrace();
			}
			byte c []= new byte[5];
			c=apdu.dataOut;
			int aux=10000;
			int x=0;
			for (int i=0 ; i<c.length ; i++)
			{
				x=x+c[i]*aux;
				aux=aux/10;
			}

			int y=x+100;
			if(y>10000) {
				MaxReachedAmountFixed();

			}else {
				try {
					apdu=client.Msg( (byte) 0x14, (byte) 0x7f,null,(byte) 0x00);
				} catch (IOException e1) {
					System.out.print("here");
					e1.printStackTrace();
				} catch (CadTransportException e1) {
					System.out.print("here2");
					e1.printStackTrace();
				}
				if (apdu.getStatus() == 0x9000)
				{Retrive_Success();}
			}
		}
		if(e.getSource()==Deposit_mytyn )
		{
			try {
				apdu=client.Msg( (byte) 0x20, (byte) 0x7f,null,(byte) 0x00);
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (CadTransportException e1) {
				e1.printStackTrace();
			}
			byte c []= new byte[5];
			c=apdu.dataOut;
			int aux=10000;
			int x=0;
			for (int i=0 ; i<c.length ; i++)
			{
				x=x+c[i]*aux;
				aux=aux/10;
			}

			int y=x+200;
			if(y>10000) {
				MaxReachedAmountFixed();

			}else {
				try {
					apdu=client.Msg( (byte) 0x15, (byte) 0x7f,null,(byte) 0x00);
				} catch (IOException e1) {
					System.out.print("here");
					e1.printStackTrace();
				} catch (CadTransportException e1) {
					System.out.print("here2");
					e1.printStackTrace();
				}
				if (apdu.getStatus() == 0x9000)
				{Retrive_Success();}
			}
		}
		if(e.getSource()==Deposit_custom )
		{
			Deposit_Enter_Amount();
		}
		if(e.getSource()==retry_max_reached_fixed ) {
			Cashdeposit();
		}
		if(e.getSource()==Deposit_back )
		{
			Distributeur();
		}
		if (e.getSource()==Deposit_Enter_Amount_goback)
		{
			Cashdeposit();
		}
		if (e.getSource()==Deposit_Enter_Amount_Validate)
		{

			String Field = Deposit_Enter_Amount_textField.getText();
			try {
				apdu=client.Msg( (byte) 0x20, (byte) 0x7f,null,(byte) 0x00);
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (CadTransportException e1) {
				e1.printStackTrace();
			}
			byte c []= new byte[5];
			c=apdu.dataOut;
			int aux=10000;
			int x=0;
			for (int i=0 ; i<c.length ; i++)
			{
				x=x+c[i]*aux;
				aux=aux/10;
			}



			if (Field.length()==0  )
			{ System.out.println(Field.length());
				EmptyInput();}
			else if(Field.length()>5)
			{tooLong(); }
			else {
				int y=0;
				try {
					y=Integer.parseInt(Field);
				}
				catch(NumberFormatException nfe) {
					System.out.println("NumberFormatException: " + nfe.getMessage());
					WrongInputRetrieve();
					return;
				}
				if(Integer.parseInt(Field)<10)
				{MinDeposit();}
				else if(Integer.parseInt(Field)+x>10000)
				{
					MaxReached();}
				else {
					byte data []=new byte[5];
					for (int i=0 ; i<=Field.length()-1 ; i++)
					{
						try {
							data[ data.length-1-i]=  Byte.parseByte(String.valueOf(Field.charAt(Field.length()-1-i)) );
						} catch (NumberFormatException nfe) {
							WrongInput();
							break;
						}}
					if (data[4]!=0x00) {multiple();}
					else {
						try {
							apdu=client.Msg( (byte) 0x16, (byte) 0x7f,data,(byte) 0x05);
						} catch (IOException e1) {
							System.out.print("here");
							e1.printStackTrace();
						} catch (CadTransportException e1) {
							System.out.print("here2");
							e1.printStackTrace();
						}
						if (apdu.getStatus() == 0x9000)
						{Retrive_Success();}
					}
				}


			}


		}
	}

}
