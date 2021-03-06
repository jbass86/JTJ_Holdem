package jtj_holdem.game.data;

import jtj_holdem.game.enums.ECardNumber;
import jtj_holdem.game.enums.ECardSuit;
import jtj_holdem.game.interfaces.ICard;

public class Card implements ICard{

	private final ECardNumber mNumber;
	
	private final ECardSuit mSuit;
	
	public Card(final ECardNumber pNumber, final ECardSuit pSuit){
		
		mNumber = pNumber;
		mSuit = pSuit;
	}

	@Override
	public ECardSuit getSuit() {
		return mSuit;
	}

	@Override
	public ECardNumber getNumber() {
		return mNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mNumber == null) ? 0 : mNumber.hashCode());
		result = prime * result + ((mSuit == null) ? 0 : mSuit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (mNumber != other.mNumber)
			return false;
		if (mSuit != other.mSuit)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Card: " + mNumber + " of " + mSuit;
	}

	@Override
	public int compareTo(ICard pCompare) {
		return Integer.compare(mNumber.getNumber(), pCompare.getNumber().getNumber());
	}
}
