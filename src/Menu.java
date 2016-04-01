
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Menu extends JPanel {

	public static  Menu menu;

	 private JButton connect;
	 public JButton singleGame;
	 public JButton multiGame;
	 private JButton startServer;
//	 private JLabel ipLabel;
//	 private JLabel portLabel;
	 private JTextField ipField;
	 private JTextField portField;
	 
	public Menu(Gra g) {
		
		super();
		
		startServer =  new JButton();
		ipField =  new JTextField();
		portField =  new JTextField();
		connect =  new JButton();
		singleGame =  new JButton();
		multiGame = new JButton();
		
		connect.setLocation(100, 60);
		connect.setSize(100,100);
		connect.setText("Po³¹cz");
	
		singleGame.setLocation(120, 60);
		singleGame.setSize(100,100);
		singleGame.setText("Single Player");
		singleGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		
		multiGame.setLocation(300, 60);
		multiGame.setSize(100,100);
		multiGame.setText("Multi Player");
		singleGame.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
	//	startServer.setLocation(300, 90);
		startServer.setSize(100,100);
		startServer.setText("Wystartuj Server");
		
		ipField.setLocation(100, 120);
		ipField.setSize(100,100);
		ipField.setText(" ");
		
		System.out.println("DEBUG");
		
		add(startServer);
		add(singleGame);
		add(multiGame);
		add(connect);

	// 	jframe.addKeyListener(this);
	}

}
