
public class Ability {
	
	public static String getAbilityName()
	{
		int playerWins = Player.getWinCount();
		
		String abilityName;
		
		if (playerWins >= 2 && playerWins < 5)
		{
			abilityName = "All Mine";
		}
		else if (playerWins >= 5 && playerWins < 8)
		{
			abilityName = "Twice the pride";
		}
		else if (playerWins >= 8 && playerWins < 10)
		{
			abilityName = "Four Turtledoves";
		}
		else if (playerWins >= 10)
		{
			abilityName = "Lucky Number 8";
		}
		else
		{
			abilityName = "None";
		}
		
		return abilityName;
	}
	
	public static boolean noLoseMoney()
	{
		int playerWins = Player.getWinCount();
		boolean noMoneyLost;
		
		if (playerWins >= 2 && playerWins < 5)
		{
			noMoneyLost = true;
		}
		else
		{
			noMoneyLost = false;
		}
		
		return noMoneyLost;
	}
	public static boolean doubleBet()
	{
		int playerWins = Player.getWinCount();
		boolean twiceBet;
		if (playerWins >= 5 && playerWins < 8)
		{
			twiceBet = true;
		}
		else
		{
			twiceBet = false;
		}
		
		return twiceBet;
	}
	public static boolean fourTimesBet()
	{
		int playerWins = Player.getWinCount();
		boolean fourTimesBet;
		
		if (playerWins >= 8 && playerWins < 10)
		{
			fourTimesBet = true;
		}
		else
		{
			fourTimesBet = false;
		}
		
		
		return fourTimesBet;
	}
	public static boolean eightTimesBet()
	{
		int playerWins = Player.getWinCount();
		boolean eightTimesBet;
		
		if (playerWins > 10)
		{
			eightTimesBet = true;
		}
		else
		{
			eightTimesBet = false;
		}
		
		return eightTimesBet;
	}
}
