package jtj_holdem.game.data_structures;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import jtj_holdem.game.data.Card;
import jtj_holdem.game.enums.ECardNumber;
import jtj_holdem.game.enums.ECardSuit;
import jtj_holdem.game.interfaces.ICard;

public class CardDeck {

	private final List <ICard> mDeck = new CopyOnWriteArrayList<ICard>();
	
	public CardDeck(){}
	
	public void populateDeck(final boolean pShuffle){
		
		mDeck.clear();
		
		for (ECardNumber number : ECardNumber.values()){
			
			for (ECardSuit suit : ECardSuit.values()){
				Card aCard = new Card(number, suit);
				mDeck.add(aCard);
			}
		}	
		
		if (pShuffle){
			shuffleDeck();
		}
	}
	
	public void shuffleDeck(){
		
		Random rand = new Random(System.currentTimeMillis());
		
		for (int i = 0; i < 500; i++){
			
			int number = rand.nextInt(mDeck.size());
			ICard card = mDeck.remove(number);
			mDeck.add(card);
		}
	}
	
	public ICard dealCard(){
		if (!mDeck.isEmpty()){
			return mDeck.remove(0);
		}else{
			return null;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mDeck == null) ? 0 : mDeck.hashCode());
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
		CardDeck other = (CardDeck) obj;
		if (mDeck == null) {
			if (other.mDeck != null)
				return false;
		} else if (!mDeck.equals(other.mDeck))
			return false;
		return true;
	}

	public void printDeck(){
		
		for (ICard card : mDeck){
			System.out.println(card);
		}
	}
}