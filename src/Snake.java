
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

/**
 * @author Jaryt Bustard
 */
public class Snake  implements Runnable, ActionListener,KeyListener
{

	public static Snake snake;
	public RenderPanel renderPanel;
	public Timer timer = new Timer(10, this);

	public ArrayList<Point> snakeParts = new ArrayList<Point>();

	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 10;
	private Gra game;
	public int ticks = 0, direction = 4, tailLength = 10, time;
	public Integer score;
	public Point head, cherry;

	public Random random;
	public Server server;
	public boolean over = false, paused;
	//public Client client;
	public Dimension dim;
	int x;
	public ChatMessage testMsg ;
	
	int NumberOfThreads;
	
	public Snake(Gra g)
	{
	
		game = g;
		dim = g.dim;
		g.add(renderPanel = new RenderPanel(this));
		g.addKeyListener(this);
		
	}

	
	
	public void startGame()
	{

		//if(server.getNumberOfThreads(x) >1)
		
		{
		over = false;
		paused = true;
		time = 0;
		score = 0;
		tailLength = 14;
		ticks = 0;
		direction = DOWN;
		head = new Point(0, -1);
		random = new Random();
		snakeParts.clear();
		cherry = new Point(random.nextInt(67), random.nextInt(63));
		
		timer.start();
		}
	}

	private void addKeyListener(Snake snake2) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		renderPanel.repaint();
		ticks++;
		

	//  testMsg = new ChatMessage(ChatMessage.SCORE,String.valueOf(snake.GetScore()));
		
		if (ticks % 2 == 0 && head != null && !over && !paused)
		{
			time++;

			snakeParts.add(new Point(head.x, head.y));
			
			
			
			if (direction == UP)
			{
				
				if (head.y - 1 >= 0 && noTailAt(head.x, head.y - 1))
				{
					head = new Point(head.x, head.y - 1);
				}
				else
				{
					
					testMsg = new ChatMessage(ChatMessage.SCORE,String.valueOf(String.valueOf(GetScore())));
					game.client.sendMessage(testMsg);
					over = true;
					

				}
			}

			if (direction == DOWN)
			{
				if (head.y + 1 < 64 && noTailAt(head.x, head.y + 1))
				{
					head = new Point(head.x, head.y + 1);
				}
				else
				{
					
					testMsg = new ChatMessage(ChatMessage.SCORE,String.valueOf(String.valueOf(GetScore())));
					game.client.sendMessage(testMsg);
					over = true;
					
				}
			}

			if (direction == LEFT)
			{
				if (head.x - 1 >= 0 && noTailAt(head.x - 1, head.y))
				{
					head = new Point(head.x - 1, head.y);
				}
				else
				{
					testMsg = new ChatMessage(ChatMessage.SCORE,String.valueOf(String.valueOf(GetScore())));
					game.client.sendMessage(testMsg);
					over = true;
					
				}
			}

			if (direction == RIGHT)
			{
				if (head.x + 1 < 68 && noTailAt(head.x + 1, head.y))
				{
					head = new Point(head.x + 1, head.y);
				}
				else
				{
					testMsg = new ChatMessage(ChatMessage.SCORE,String.valueOf(String.valueOf(GetScore())));
					game.client.sendMessage(testMsg);
					over = true;
				}
			}

			if (snakeParts.size() > tailLength)
			{
				snakeParts.remove(0);
			}

			if (cherry != null)
			{
				if (head.equals(cherry))
				{
					score += 100;
				
					tailLength = tailLength + 5;
					cherry.setLocation(random.nextInt(67), random.nextInt(60));
		
				}
			}
		}
	
	}

	public boolean noTailAt(int x, int y)
	{
		
		for (Point point : snakeParts)
		{
			
			if (point.equals(new Point(x, y)))
			{
			
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		int i = e.getKeyCode();

		if ((i == KeyEvent.VK_A || i == KeyEvent.VK_LEFT) && direction != RIGHT)
		{
			direction = LEFT;
		}

		if ((i == KeyEvent.VK_D || i == KeyEvent.VK_RIGHT) && direction != LEFT)
		{
			direction = RIGHT;
		}

		if ((i == KeyEvent.VK_W || i == KeyEvent.VK_UP) && direction != DOWN)
		{
			direction = UP;
		}

		if ((i == KeyEvent.VK_S || i == KeyEvent.VK_DOWN) && direction != UP)
		{
			direction = DOWN;
		}
		
		if(i == KeyEvent.VK_T)
		{

			if(direction == DOWN)
			{
				head = new Point(head.x, head.y +direction *2);
			}
			if(direction == UP)
			{
				head = new Point(head.x, head.y - direction*3 );
			}
			if(direction == LEFT)
			{
				head = new Point(head.x - direction, head.y);
			}
			if(direction == RIGHT)
			{
				head = new Point(head.x + direction, head.y);
			}
		}


	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		
	}
	public Integer GetScore()
	{
		return score;
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}
     //  1 turbo dla snake
     // pobranie z kompa id 
	 //  kazdy message wysylany do serwera w postaci stringow
	// cs message przesylam toStriing do chat meessage  i tyle
	// klient i serwer musi miec dodana strukture ktora bedzie miec przechowywanie tych message
	// funkcja sprawdzajace czy to jest cs message 
	// tag jest dodawany za pomoca super
	// cs message musi miec funkce parsujaca wszystkie message (zeby oddzielic od tagow )

	@Override
	public void run() {
		// TODO Auto-generated method stub
	
	}
	
	
	/*
	  sproboj zeobic najpierw csMessage
	 1. polaczenie snake+client (chat w obu)
	 2. wynik gry na chat
	 3. Zapis nazwa + wynik do bazy (DBMS) 1 tabela: imie i wynik
	 */
	
	
	
	
	/*
	  sproboj zeobic najpierw csMessage
	 1. polaczenie snake+client (chat w obu)
	 2
	 3. wynik gry na chat
	 4. Zapis nazwa + wynik do bazy (DBMS) 1 tabela: imie i wynik
	 */
	
}