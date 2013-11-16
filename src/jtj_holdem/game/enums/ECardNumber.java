package jtj_holdem.game.enums;

public enum ECardNumber {

	TWO(2, "Two"),
	THREE(3, "Three"),
	FOUR(4, "Four"),
	FIVE(5, "Five"),
	SIX(6, "Six"),
	SEVEN(7, "Seven"),
	EIGHT(8, "Eight"),
	NINE(9, "Nine"),
	TEN(10, "Ten"),
	JACK(11, "Jack"),
	QUEEN(12, "Queen"),
	KING(13, "King"),
	ACE(14, "Ace");
	
	private final int mNumber;
	
	private final String mName;
	
	private ECardNumber(final int pNumber, final String pName){
		mNumber = pNumber;
		mName = pName;
	}
	
	public int getNumber(){
		return mNumber;
	}
}
