public class Player
{
	private static int plyMoney = 500;
	private static int plyBet = 0;
	private static int plyHandValue = 0;
	private static int plyWinCount = 0;
	
	public static int getPlyMoney()
	{
		return plyMoney;
	}
	public static void setPlyMoney(int money)
	{
		plyMoney += money;
	}
	public static void setPlyHandValue(int value)
	{
		plyHandValue += value;
	}
	public static int getPlyHandValue()
	{
		return plyHandValue;
	}
	public static void setBet(int bet)
	{
		plyBet = bet;
	}
	public static int getBet()
	{
		return Player.plyBet;
	}
	public static int getWinCount()
	{
		return plyWinCount;
	}
	public static void setWinCount(int winCount)
	{
		plyWinCount += winCount;
	}
}
