package jtj_holdem.game.enums;

public enum ECardSuit {

	HEARTS("hearts"),
	SPADES("spades"),
	CLUBS("clubs"),
	DIAMONDS("diamonds");
	
	private final String mName;
	
	private ECardSuit(final String pName){
		mName = pName;
	}
	
	public String getName(){
		return mName;
	}
	
}
