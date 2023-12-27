// Deck System

import javax.swing.ImageIcon;

public class Deck
{
	private static String[] cardDeck = {"/images/card_ace.png", "/images/card_2.png", "/images/card_3.png", "/images/card_4.png", "/images/card_5.png", "/images/card_6.png", "/images/card_7.png", "/images/card_8.png", "/images/card_9.png", "/images/card_10.png", "/images/card_joker.png", "/images/card_queen.png", "/images/card_king.png"}; 
	
	public ImageIcon getImageForPly()
	{
		int index = Dealer.dealCardToPly();
		
		ImageIcon plyCardImg = new ImageIcon(getClass().getResource(cardDeck[index]));
		
		Player.setPlyHandValue(index+1);
		
		return plyCardImg;
	}
	
	public ImageIcon getImageForDeal()
	{
		int index = Dealer.dealCardToSelf();
		
		Dealer.setDealHandValue(index+1);
		ImageIcon dealCardImg = new ImageIcon(getClass().getResource(cardDeck[index]));
		
		return dealCardImg;
	}
}
