import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

//import server.ClientThread;


public class Gra extends JFrame  implements ActionListener{

	private Menu menu;
	//public Snake snake = new Snake();
	Snake snake;
	public Integer Score = 10;
	public JButton connect;
	private JButton disconnect;
	private JButton quitGame;
	private JButton play;
	private JScrollPane scrollPane;
	private JPanel panel1, panel2, panel3, panel4, scorePanel;
	
//public TextArea textArea ;
//	public TextField usernameNameField ;
//	public TextField usernameScoreField;
	public TextField opponentField, textField;
	private TextField tfServer, tfPort;
	private JLabel label, usernameLabel, scoreLabel, opponentScore;
	
	public  boolean connected;
	private int defaultPort;
	private String defaultHost;
	public Client client;
	
	public static Server server;
	
	
	public int x;
	
	
	private TextArea textArea = new TextArea("You can chat here:" + "\n",26,80);
	public Dimension dim;
	public static Gra Game;
	
	public int getSize ; 
	
	public Gra() 
	{
		super("Snake Game");
		
		
		
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		setVisible(true);
	//	setSize(805, 700);
		setResizable(false);
		//setLocation(dim.width / 2 - jframe.getWidth() / 2, dim.height / 2 - jframe.getHeight() / 2);
		//setLocation(300,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(dim);
		setLocation(dim.width / 2 - getWidth() / 2, dim.height / 2 - getHeight() / 2);
	//	add(createChatPanel(),BorderLayout.NORTH);
		//addKeyListener(this);
		 //add(snake);
		// add snake?
	}
	
	
	private void run(String host, int port)
	{
		
		
		
		defaultPort = port;
		defaultHost = host;
		
		tfServer = new TextField(host);
		tfPort = new TextField("" + port);
		
		connect = new JButton();
		connect.setText("Connect");
		connect.addActionListener(this);
		
		disconnect = new JButton();
		disconnect.setText("Disconnect");
		disconnect.addActionListener(this);
		disconnect.setEnabled(false);
	
		play = new JButton();
		play.setText("<<< Play");
		play.addActionListener(this);
		
		quitGame = new JButton();
		quitGame.setText("Quit");
		quitGame.addActionListener(this);
		
		
		
		panel1 = new JPanel();
		panel1.add(play);
		panel1.add(new JLabel("Port Number:"));
		panel1.add(new TextField(" "));	

		panel1.add(connect);
		panel1.add(disconnect);
		panel1.add(quitGame);
	
		
		panel2 = new JPanel();
		label = new JLabel("Enter your username below",SwingConstants.CENTER);
		textField = new TextField("anonim",55);
		
		panel2.add(label);
		panel2.add(textField);
//		scoreLabel = new JLabel("SCORE:");
//		usernameLabel = new JLabel("Enter your username");
//		panel2.add(scoreLabel);
//		usernameScoreField = new TextField("10    ");
//		panel2.add(usernameScoreField);
//		
//		
//		usernameNameField = new TextField("     ");
//		
//		panel2.add(usernameLabel);
//		panel2.add(usernameNameField);

		panel3 = new JPanel();
		//scrollPane = new JScrollPane(textArea);
		panel3.add(textArea);
		textArea.setEditable(false);
	
		panel4 = new JPanel();
//		opponentScore = new JLabel("OPPONENT SCORE");
//		panel4.add(opponentScore);
//		opponentField = new TextField("     ");
//		panel4.add(opponentField);
		
		
		Box box = Box.createVerticalBox();
	

		box.add(panel1);
		box.add(panel2);
		box.add(panel4);
		box.add(panel3);
	
		Container cp = getContentPane();
		cp.add(BorderLayout.EAST, box);
		
		
		
	//	snake = new Snake(Game);
		//add(snake,BorderLayout.NORTH);
		//add(menu,BorderLayout.SOUTH);
	//	snake.startGame();
		
		snake = new Snake(Game);
		snake.startGame();
		
		//tak samo jak jp i jp2 zrob menu i snake. snake zamien na jpanel. a render niech tylko rysuje (ma nie byc panelem)
	}
	
	public void connectionFailed()
	{
		connect.setEnabled(true);
		disconnect.setEnabled(false);
		
		label.setText("Enter your username below");
		textField.setText("Anonymous");
		// reset port number and host name as a construction time
		tfPort.setText("" + defaultPort);
		tfServer.setText(defaultHost);
		// let the user change them
		tfServer.setEditable(false);
		tfPort.setEditable(false);
		// don't react to a <CR> after the username
		textField.removeActionListener(this);
		connected = false;
	}

	public void append(String str)
	{
		System.out.println("test text:" + textArea.getText());
	//	textArea.append(str);
		textArea.setCaretPosition(textArea.getText().length() -1);
	}
	public static void main(String[] args)
	{
		Game = new Gra();
		
		Game.run("localhost",1500);
		

		
	}
	
	private JPanel createChatPanel()
	{
		JPanel newPanel = new JPanel();
		newPanel.setLocation(10, 10);
		
		newPanel.setSize(10, 10);
		newPanel.setLayout(new GridBagLayout());
		JLabel label = new JLabel("Enter username:");
		JTextField userName = new JTextField(20);
		 
		newPanel.add(label);
		newPanel.add(userName);
		return newPanel;
	}
	
	public int LiczbaWatkow(int Rozmiar)
	{
		return Rozmiar;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object status = e.getSource();
	
		if(connected && status == textField  ) {
			// just have to send the message
			client.sendMessage(new ChatMessage(ChatMessage.MESSAGE, textField.getText()));				
			textField.setText("");
		
		}
		

		if(status == play  )
		{
			
//			System.out.println(server.getNumberOfThreads() + " to liczba threadow");
	//		System.out.println(server.al.size() + " a moze teraz dziala");
			
			//add(snake,BorderLayout.NORTH);
			//add(menu,BorderLayout.SOUTH);
			
			
			snake.paused = !snake.paused;
			snake.renderPanel.setFocusable(true);
			//snake.renderPanel.setVisible(true);
			//snake.renderPanel.requestDefaultFocus();
			snake.renderPanel.requestFocusInWindow();
			//snake.renderPanel.requestFocus();
			snake.renderPanel.setVisible(true);
			Game.addKeyListener(snake.renderPanel);
//			System.out.println();
			return;
			
		}
		
		if(status == connect)
		{
			
			String username = textField.getText().trim();
			
			
			// empty username ignore it
			if(username.length() == 0)
				return;
			// empty serverAddress ignore it
			String server = tfServer.getText().trim();
			if(server.length() == 0)
				return;
			// empty or invalid port numer, ignore it
			String portNumber = tfPort.getText().trim();
			if(portNumber.length() == 0)
				return;
			int port = 0;
			try {
				port = Integer.parseInt(portNumber);
			}
			catch(Exception en) {
				return;   // nothing I can do if port number is not valid
			}

			// try creating a new Client with GUI
			client = new Client(server, port, username,textArea);
			// test if we can start the Client
			if(!client.start()) 
				return;
			textField.setText("");
			label.setText("Enter your message below");
			connected = true;
			
			// disable login button
			connect.setEnabled(false);
			// enable the 2 buttons
			disconnect.setEnabled(true);
			
			// disable the Server and Port JTextField
			tfServer.setEditable(false);
			tfPort.setEditable(false);
			// Action listener for when the user enter a message
			textField.addActionListener(this);
		//	client.sendMessage(new ChatMessage(ChatMessage.MESSAGE,"zaczal grac"));
			
		}
		
		if(status == disconnect)
		{
			client.sendMessage(new ChatMessage(ChatMessage.LOGOUT, ""));
			connect.setEnabled(true);
			disconnect.setEnabled(false);
			return;
		}
		
		if(status == quitGame)
				System.exit(0);
	}

}


/*
 * DO ZROBIENIA
 * 1 CLIENT SERWER
 * 2 BAZA DANYCH
 * 3 MODYFIKACJA SNAKE DO TURBO   I   REAKTYWACJA -40 HEALTH
 *  
 * za ktorym razem podjal probe i poprawienie zbierania wynikow    + zablokowanie odpalenia multi
 *  + limit na klientow
 * */

 // snake odpalony dopiero kiedy jest polaczenie
//
//  wyniki wyswietlaja sie na serverze   - napisane jest za ktorym podejsciem
// zrobic komunikaty i zabezpeczeniach przy loginach i logoutach
 
// wyczyszcenie kodu
// poprawienie oxceptionow
// zrobienie dzialajacego disconnected
// poprawienie turbo
// poprawienie okna w snaku zeby nie wyjezdza³ 
/// zabezpieczenie na ilosc graczy?
