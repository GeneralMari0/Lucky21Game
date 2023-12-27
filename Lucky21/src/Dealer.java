public class Dealer 
{
	private static int dealHandValue = 0;
	
	public static int dealCardToPly()
	{
		int cardValue = (int) (0 + Math.random() * 12);
		
		return cardValue;
	}
	public static int dealCardToSelf()
	{
		int cardValue = (int) (1 + Math.random() * 12);	
		
		return cardValue;
	}
	public static int getDealHandValue()
	{
		return dealHandValue;
	}
	public static void setDealHandValue(int value)
	{
		dealHandValue += value;
		if (dealHandValue > 21)
		{
			dealHandValue = 21;
		}
	}

}