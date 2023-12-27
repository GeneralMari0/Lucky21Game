/* Lucky 21
 * Created by Mason Paquette, Chase Chang, and Jun Li Liu for COMP 371.
 * 
 * Lucky 21 is our take on the classic card game BlackJack.
 * The objective of this game is to obtain a hand value that is higher than the dealers.
 * If your hand is higher than the dealers, you will win twice your bet.
 * If you match the dealers hand value, you win 1.5 times your bet.
 * Otherwise, if you lose, then you get nothing and lose your bet.
 * 
 * Have fun!
 */

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

public class MainWindow{

	private static MainWindow[] windowCreate = new MainWindow[100];
	private static int windowCount = 0;
	
	private JFrame frame;
	private JTextField betField;
	private int hitCount = 0;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					windowCreate[0] = new MainWindow();
					windowCreate[0].frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});		
			
		}

	
	public MainWindow() {
		initialize();
	}


	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 128, 0));
		frame.setBackground(new Color(0, 128, 0));
		frame.setResizable(false);
		frame.setBounds(100, 100, 960, 540);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String gameMusic = "/music/gamblemusic.wav";
		playMusic(gameMusic);
		
		JButton hitButt = new JButton("Hit");
		hitButt.setBounds(285, 446, 145, 31);
		hitButt.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		frame.getContentPane().setLayout(null);
		hitButt.setVisible(false);
		
		ImageIcon cardImg = new ImageIcon(this.getClass().getResource("/images/card_back.png"));
		JLabel deckImage = new JLabel(cardImg);
		deckImage.setBounds(489, 11, 105, 146);
		frame.getContentPane().add(deckImage);
		frame.getContentPane().add(hitButt);
		deckImage.setVisible(false);
		
		JButton standButt = new JButton("Stand");
		standButt.setBounds(500, 446, 115, 31);
		standButt.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		frame.getContentPane().add(standButt);
		standButt.setVisible(false);
		
		JButton betButt = new JButton("Place bet");
		betButt.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		betButt.setBounds(401, 265, 145, 48);
		frame.getContentPane().add(betButt);
		betButt.setVisible(true);
		
		JLabel potLabel = new JLabel("");
		potLabel.setForeground(new Color(255, 255, 255));
		potLabel.setBackground(Color.RED);
		potLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		potLabel.setBounds(20, 168, 340, 44);
		frame.getContentPane().add(potLabel);
		potLabel.setVisible(false);
		
		JLabel plyMoneyLabel = new JLabel("Your Balance: $" + Player.getPlyMoney());
		plyMoneyLabel.setForeground(new Color(255, 255, 255));
		plyMoneyLabel.setBackground(Color.RED);
		plyMoneyLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		plyMoneyLabel.setBounds(20, 204, 340, 44);
		frame.getContentPane().add(plyMoneyLabel);
		plyMoneyLabel.setVisible(true);
		
		JLabel plyAbilityLabel = new JLabel("Active Ability: " + Ability.getAbilityName());
		plyAbilityLabel.setForeground(new Color(255, 255, 255));
		plyAbilityLabel.setBackground(Color.RED);
		plyAbilityLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		plyAbilityLabel.setBounds(20, 239, 340, 44);
		frame.getContentPane().add(plyAbilityLabel);
		plyAbilityLabel.setVisible(true);
		
		Deck dealCards = new Deck();
		JLabel dealCard1 = new JLabel(dealCards.getImageForDeal());
		frame.getContentPane().add(dealCard1);
		dealCard1.setBounds(360, 11, 103, 148);
		dealCard1.setVisible(false);
		
		JLabel dealCard2 = new JLabel(dealCards.getImageForDeal());
		frame.getContentPane().add(dealCard2);
		dealCard2.setBounds(489, 11, 102, 145);
		dealCard2.setVisible(false);
		
		Deck plyCards = new Deck();
		
		JLabel plyCard1 = new JLabel(plyCards.getImageForPly());
		frame.getContentPane().add(plyCard1);
		plyCard1.setBounds(360, 275, 103, 148);
		plyCard1.setVisible(false);
		
		JLabel plyCard2 = new JLabel(plyCards.getImageForPly());
		frame.getContentPane().add(plyCard2);
		plyCard2.setBounds(499, 275, 103, 148);
		plyCard2.setVisible(false);
		
		betField = new JTextField();
		betField.setFont(new Font("Arial", Font.PLAIN, 20));
		betField.setBounds(417, 222, 115, 42);
		frame.getContentPane().add(betField);
		betField.setColumns(10);
		
		JLabel betLabel = new JLabel("Please place a bet.");
		betLabel.setForeground(new Color(255, 255, 255));
		betLabel.setBackground(SystemColor.activeCaption);
		betLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		betLabel.setBounds(370, 168, 438, 52);
		frame.getContentPane().add(betLabel);
		
		JButton quitButt = new JButton("Quit Game");
		quitButt.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		quitButt.setBounds(778, 429, 138, 48);
		frame.getContentPane().add(quitButt);
		
		JLabel winLoseLabel = new JLabel("");
		winLoseLabel.setHorizontalAlignment(SwingConstants.CENTER);
		winLoseLabel.setForeground(Color.WHITE);
		winLoseLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		winLoseLabel.setBackground(SystemColor.activeCaption);
		winLoseLabel.setBounds(185, 223, 638, 52);
		frame.getContentPane().add(winLoseLabel);
		winLoseLabel.setVisible(false);
		
		JLabel gameTitle = new JLabel("LUCKY 21");
		gameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		gameTitle.setOpaque(true);
		gameTitle.setForeground(Color.BLACK);
		gameTitle.setFont(new Font("Arial Black", Font.BOLD, 55));
		gameTitle.setBackground(Color.WHITE);
		gameTitle.setBounds(231, 22, 504, 97);
		frame.getContentPane().add(gameTitle);
		
		JLabel tableEdgeLeft = new JLabel("");
		tableEdgeLeft.setOpaque(true);
		tableEdgeLeft.setBackground(new Color(184, 134, 11));
		tableEdgeLeft.setBounds(926, 0, 18, 501);
		frame.getContentPane().add(tableEdgeLeft);
		
		JLabel tableEdgeRight = new JLabel("");
		tableEdgeRight.setOpaque(true);
		tableEdgeRight.setBackground(new Color(184, 134, 11));
		tableEdgeRight.setBounds(0, 0, 18, 501);
		frame.getContentPane().add(tableEdgeRight);
		
		JLabel tableEdgeTop = new JLabel("");
		tableEdgeTop.setOpaque(true);
		tableEdgeTop.setBackground(new Color(184, 134, 11));
		tableEdgeTop.setBounds(10, 0, 924, 18);
		frame.getContentPane().add(tableEdgeTop);
		
		JLabel tableEdgeTop_1 = new JLabel("");
		tableEdgeTop_1.setOpaque(true);
		tableEdgeTop_1.setBackground(new Color(184, 134, 11));
		tableEdgeTop_1.setBounds(10, 483, 924, 18);
		frame.getContentPane().add(tableEdgeTop_1);
		
		JLabel gameCreated = new JLabel("Created by:");
		gameCreated.setForeground(new Color(255, 255, 255));
		gameCreated.setBackground(Color.RED);
		gameCreated.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		gameCreated.setBounds(20, 334, 340, 44);
		frame.getContentPane().add(gameCreated);
		gameCreated.setVisible(true);
		
		
		JLabel mason = new JLabel("Mason P.");
		mason.setForeground(new Color(255, 255, 255));
		mason.setBackground(Color.RED);
		mason.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		mason.setBounds(20, 366, 340, 44);
		frame.getContentPane().add(mason);
		mason.setVisible(true);
		
		JLabel jun  = new JLabel("Junli L.");
		jun.setForeground(new Color(255, 255, 255));
		jun.setBackground(Color.RED);
		jun.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		jun.setBounds(20, 389, 340, 44);
		frame.getContentPane().add(jun);
		jun.setVisible(true);
		
		JLabel chase  = new JLabel("Chase C.");
		chase.setForeground(new Color(255, 255, 255));
		chase.setBackground(Color.RED);
		chase.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		chase.setBounds(20, 413, 340, 44);
		frame.getContentPane().add(chase);
		chase.setVisible(true);
		
		//Universal button listener
		
		ActionListener plyInput = new ActionListener() {
			@Override
			
			public void actionPerformed(ActionEvent e) {
				Object o = e.getSource();
				
				if (o == hitButt)
				{
					++hitCount;
					if(hitCount == 1)
					{
						JLabel plyCard3 = new JLabel(plyCards.getImageForPly());
						frame.getContentPane().add(plyCard3);
						plyCard3.setBounds(621, 275, 103, 148);
						plyCard3.setVisible(true);
					}
					else if (hitCount == 2)
					{
						JLabel plyCard4 = new JLabel(plyCards.getImageForPly());
						frame.getContentPane().add(plyCard4);
						plyCard4.setBounds(743, 275, 103, 148);
						plyCard4.setVisible(true);
					}
					else
					{
						winLoseLabel.setText("You cannot hit more than twice.");
						frame.getContentPane().add(winLoseLabel);
						winLoseLabel.setVisible(true);
					}
					if (isPlyBust())
					{
						String loser = "/music/lose.wav";
						playMusic(loser);
						hitButt.setVisible(false);
						standButt.setVisible(false);
						frame.getContentPane().add(winLoseLabel);
						Player.setWinCount(-Player.getWinCount());
						
						if (Ability.noLoseMoney())
						{
							Player.setPlyMoney(Player.getBet());
						}
						
						if (Player.getPlyMoney() <= 0)
						{
							potLabel.setVisible(false);
							plyOutMoney();
						}
						else
						{
							winLoseLabel.setText("You've exceeded 21. You Busted!");
							winLoseLabel.setVisible(true);
							restartGame();
						}
						
						winLoseLabel.setVisible(true);					
						
					}
					
				}
				else if (o == standButt)
				{
					int dealHand = Dealer.getDealHandValue();
					int plyHand = Player.getPlyHandValue();
					int plyBet = Player.getBet();
					
					deckImage.setVisible(false);
					hitButt.setVisible(false);
					standButt.setVisible(false);
					
					frame.getContentPane().add(winLoseLabel);
					winLoseLabel.setVisible(true);
					
					if (dealHand > plyHand)
					{				
						if (Player.getPlyMoney() <= 0 && !Ability.noLoseMoney())
						{
							potLabel.setVisible(false);
							String loser = "/music/lose.wav";
							playMusic(loser);
							plyOutMoney();
						}
						else
						{
							if (Ability.noLoseMoney())
							{
								Player.setPlyMoney(Player.getBet());
							}
							String loser = "/music/lose.wav";
							playMusic(loser);
							winLoseLabel.setText("The dealer's hand was higher. Bust!");
							Player.setWinCount(-Player.getWinCount());
							restartGame();
						}
					}
					else if (dealHand == plyHand)
					{
						String winner = "/music/win.wav";
						playMusic(winner);
						winLoseLabel.setText("You matched the dealer! You win 1.5 times the pot");
						
						if (Ability.doubleBet())
						{
							Player.setPlyMoney((int) ((plyBet*1.5) * 2));
						}
						else if (Ability.fourTimesBet())
						{
							Player.setPlyMoney((int) ((plyBet*1.5) * 4));
						}
						else if (Ability.eightTimesBet())
						{
							Player.setPlyMoney((int) ((plyBet*1.5) * 8));
						}
						else
						{
							Player.setPlyMoney((int) (plyBet*1.5));
						}
						Player.setWinCount(1);
						restartGame();
					}
					else
					{
						String winner = "/music/win.wav";
						playMusic(winner);
						winLoseLabel.setText("Your hand was higher, you win 2 times the pot!");
						
						if (Ability.doubleBet())
						{
							Player.setPlyMoney((int) ((plyBet*2) * 2));
						}
						else if (Ability.fourTimesBet())
						{
							Player.setPlyMoney((int) ((plyBet*2) * 4));
						}
						else if (Ability.eightTimesBet())
						{
							Player.setPlyMoney((int) ((plyBet*2) * 8));
						}
						else
						{
							Player.setPlyMoney((int) (plyBet*2));
						}
						Player.setWinCount(1);
						restartGame();
					}
				}
				else if (o == betButt)
				{
					int bet = 0;
					
					while (true)
					{
						try
						{
							bet = Integer.parseInt(betField.getText());
							break;
						}
						catch(NumberFormatException ex)
						{
							System.out.println("You must enter an integer");
							betLabel.setText("You must enter a integer");
							break;
						}
					}
					
					if (bet > 0 && bet <= Player.getPlyMoney())
					{
						Player.setPlyMoney(-bet);
						Player.setBet(bet);
						
						betButt.setVisible(false);
						betField.setVisible(false);
						betLabel.setVisible(false);
						gameTitle.setVisible(false);
						
						gameCreated.setVisible(false);
						mason.setVisible(false);
						jun.setVisible(false);
						chase.setVisible(false);
						plyCard1.setVisible(true);
						plyCard2.setVisible(true);
						dealCard1.setVisible(true);
						dealCard2.setVisible(true);
						deckImage.setVisible(true);
						potLabel.setVisible(true);
						hitButt.setVisible(true);
						standButt.setVisible(true);
					
						potLabel.setText("Current Pot: $" + bet);
					}
					else if (bet < 0)
					{
						betLabel.setText("You can't bet less than 0.");
					}
					else if (bet == 0)
					{
						betLabel.setText("You can't bet 0 dollars.");
					}
					else if (bet > Player.getPlyMoney())
					{
						betLabel.setText("You only have $" + Player.getPlyMoney());
					}
					else
					{
						betLabel.setText("You must add a bet");
					}
					
				}
				else if (o == quitButt)
				{
					System.exit(0);
				}
				
			}
		};
		
		//Button listener objects
		
		hitButt.addActionListener(plyInput);
		standButt.addActionListener(plyInput);
		betButt.addActionListener(plyInput);
		quitButt.addActionListener(plyInput);
		
	}
	
	private void playMusic(String resource) {
		 try {
			 
			 URL url = getClass().getResource(resource);
	         // Open an audio input stream.
	         AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
	         // Get a sound clip resource.
	         Clip soundClip = AudioSystem.getClip();
	         // Open audio clip and load samples from the audio input stream.
	         
	         if (windowCount == 0 && resource.equals("/music/gamblemusic.wav"))
	         {
	        	 soundClip.open(audioIn);
	        	 soundClip.start();
	        	 soundClip.loop(100);
	         }
	         else if (windowCount != 0 && resource.equals("/music/gamblemusic.wav"))
	         {
	         }
	         else
	         {
	        	 soundClip.open(audioIn);
	        	 soundClip.start();
	         }
	      } catch (UnsupportedAudioFileException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (LineUnavailableException e) {
	         e.printStackTrace();
	      }
	}
	
	private void restartGame()
	{
		JButton restartGame = new JButton("Play Again");
		restartGame.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		restartGame.setBounds(401, 172, 145, 48);
		frame.getContentPane().add(restartGame);
		
		ActionListener plyInput = new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Object but = e.getSource();
				
				if (but == restartGame)
				{
					Player.setPlyHandValue(-Player.getPlyHandValue());
					Dealer.setDealHandValue(-Dealer.getDealHandValue());
					
					windowCount++;
					windowCreate[windowCount] =  new MainWindow();
					windowCreate[windowCount-1].frame.setVisible(false);
					windowCreate[windowCount].frame.setVisible(true);
					
				}
			}
		};
		restartGame.addActionListener(plyInput);
	}
	
	private void plyOutMoney()
	{
		JLabel plyMoneyOutLabel1 = new JLabel("Sorry, but it looks like you've run out of money.");
		plyMoneyOutLabel1.setForeground(Color.WHITE);
		plyMoneyOutLabel1.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		plyMoneyOutLabel1.setBackground(SystemColor.activeCaption);
		plyMoneyOutLabel1.setBounds(219, 168, 548, 52);
		frame.getContentPane().add(plyMoneyOutLabel1);
		plyMoneyOutLabel1.setVisible(true);
		
		JLabel plyMoneyOutLabel2 = new JLabel("Better luck next time!");
		plyMoneyOutLabel2.setForeground(Color.WHITE);
		plyMoneyOutLabel2.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		plyMoneyOutLabel2.setBackground(SystemColor.activeCaption);
		plyMoneyOutLabel2.setBounds(360, 202, 548, 52);
		frame.getContentPane().add(plyMoneyOutLabel2);
		plyMoneyOutLabel2.setVisible(true);
	}
	
	private boolean isPlyBust()
	{
		boolean isBust;
		int plyHandValue = Player.getPlyHandValue();
		
		if (plyHandValue > 21)
		{
			isBust = true;
			return isBust;
		}
		else
		{					
			isBust = false;
			return isBust;
		}
	}
}