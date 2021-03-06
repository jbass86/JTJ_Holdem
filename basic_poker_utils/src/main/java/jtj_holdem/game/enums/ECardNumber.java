package jtj_holdem.game.enums;

public enum ECardNumber {

	TWO(2, "two"),
	THREE(3, "three"),
	FOUR(4, "four"),
	FIVE(5, "five"),
	SIX(6, "six"),
	SEVEN(7, "seven"),
	EIGHT(8, "eight"),
	NINE(9, "nine"),
	TEN(10, "ten"),
	JACK(11, "jack"),
	QUEEN(12, "queen"),
	KING(13, "king"),
	ACE(14, "ace");
	
	private final int mNumber;
	
	private final String mName;
	
	private ECardNumber(final int pNumber, final String pName){
		mNumber = pNumber;
		mName = pName;
	}
	
	public int getNumber(){
		return mNumber;
	}
	
	public String getName(){
		return mName;
	}
}
