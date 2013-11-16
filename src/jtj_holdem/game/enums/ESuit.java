package jtj_holdem.game.enums;

public enum ESuit {

	HEART("heart"),
	SPADE("spade"),
	CLUB("club"),
	DIAMOND("diamond");
	
	private final String mName;
	
	private ESuit(final String pName){
		mName = pName;
	}
	
	public String getName(){
		return mName;
	}
	
}
