package jtj_holdem.game.data_structures;

import java.util.Collections;
import java.util.List;

import jtj_holdem.game.enums.EPokerHand;
import jtj_holdem.game.interfaces.ICard;

public class PokerHand {

	public static final PokerHand INVALID = new PokerHand(EPokerHand.NO_HAND, Collections.<ICard> emptyList(),
			Collections.<ICard> emptyList());
	
	private final EPokerHand mHand;
	
	private final List<ICard> mCards;
	
	private final List<ICard> mKickers;
	
	public PokerHand(final EPokerHand pHand, final List<ICard> pCards, final List<ICard> pKickers){
		mHand = pHand;
		mCards = pCards;
		mKickers = pKickers;
	}

	public EPokerHand getHand() {
		return mHand;
	}

	public List<ICard> getCards() {
		return mCards;
	}
	
	public List<ICard> getKickers() {
		return mKickers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mCards == null) ? 0 : mCards.hashCode());
		result = prime * result + ((mHand == null) ? 0 : mHand.hashCode());
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
		PokerHand other = (PokerHand) obj;
		if (mCards == null) {
			if (other.mCards != null)
				return false;
		} else if (!mCards.equals(other.mCards))
			return false;
		if (mHand != other.mHand)
			return false;
		return true;
	}
	
	public boolean isValid(){
		return !this.equals(INVALID);
	}
}
