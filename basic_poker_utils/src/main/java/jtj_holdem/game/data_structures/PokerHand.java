package jtj_holdem.game.data_structures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import jtj_holdem.game.enums.ECardNumber;
import jtj_holdem.game.enums.EPokerHand;
import jtj_holdem.game.interfaces.ICard;
import jtj_holdem.game.utility.PokerHandUtility;

/**
 * A data structure containing the logical contents of a Poker Hand as well as all of the logic needed
 * in order to compare one hand against another.
 * @author Josh Bass
 *
 */
public class PokerHand implements Comparable<PokerHand>{

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

	@Override
	public int compareTo(PokerHand pO) {

		if (getHand().compareTo(pO.getHand()) == 0){
			//both hands are the same so we have to get more technical.

			switch(mHand){
			case STRAIGHT_FLUSH:
				//straight flush should just follow the same rules as a straight
				return compareStraight(pO);
			case FOUR_OF_A_KIND:
				return compareOfAKind(pO);
			case FULL_HOUSE:
				return compareFullHouse(pO);
			case FLUSH:
				return compareFlush(pO);
			case STRAIGHT:
				return compareStraight(pO);
			case THREE_OF_A_KIND:
				return compareOfAKind(pO);
			case TWO_PAIR:
				return compareTwoPair(pO);
			case ONE_PAIR:
				return compareOfAKind(pO);
			case HIGH_CARD:
				return compareOfAKind(pO);
			default:
				return 0;
			}

		}
		return getHand().compareTo(pO.getHand());	
	}
	
	/**
	 * Compares one straight against another by just looking at the high card in the straight.  However extra care must be taken
	 * in order to remove the ace if its an ace low straight for proper results
	 * @param pO - the other hand
	 * @return an integer between -1 and 1 based on whether this hand was less than equal to or greater than the other hand.
	 */
	private int compareStraight(final PokerHand pO){
		
		List<ICard> hand1 = new ArrayList<ICard>(getCards());
		List<ICard> hand2 = new ArrayList<ICard>(pO.getCards());
		Collections.sort(hand1);
		Collections.sort(hand2);
		
		//if either hand is an ace low straight the sorting will actually put ace at the top of the list which would
		//set it way higher then it should be so it needs to be removed
		if (hand1.get(0).getNumber() == ECardNumber.TWO && hand1.get(hand1.size() - 1).getNumber() == ECardNumber.ACE){
			hand1.remove(hand1.size() - 1);
		}
		if (hand2.get(0).getNumber() == ECardNumber.TWO && hand2.get(hand2.size() - 1).getNumber() == ECardNumber.ACE){
			hand2.remove(hand2.size() - 1);
		}
		
		//compare the highest card in the stright of each hand
		return hand1.get(hand1.size() - 1).getNumber().compareTo(hand2.get(hand2.size() - 2).getNumber());
	}
	
	/**
	 * Compares one flush against another, Simply just goes down the list of high cards in the flush until it finds
	 * one that dosent equal the other.
	 * @param pO - the other hand
	 * @return an integer between -1 and 1 based on whether this hand was less than equal to or greater than the other hand.
	 */
	private int compareFlush(final PokerHand pO){
		
		List<ICard> hand1 = new ArrayList<ICard>(getCards());
		List<ICard> hand2 = new ArrayList<ICard>(pO.getCards());
		Collections.sort(hand1);
		Collections.sort(hand2);
		
		for (int i = hand1.size() - 1; i >= 0; i--){
			if (hand1.get(i).getNumber().compareTo(hand2.get(i).getNumber()) != 0){
				return hand1.get(i).getNumber().compareTo(hand2.get(i).getNumber());
			}
		}
		//if we got this far then that means every single card in the flush was the same...
		return 0;
	}
	
	/**
	 * This method should be able to compare any "of a kind" all the way from 4 of a kind down to high card
	 * We just assume obviosuly that both hands are the same and each has the same number of kickers however many
	 * that may be
	 * @param pO - the other hand
	 * @return an integer between -1 and 1 based on whether this hand was less than equal to or greater than the other hand.
	 */
	private int compareOfAKind(final PokerHand pO){
		
		List<ICard> hand1 = new ArrayList<ICard>(getCards());
		List<ICard> hand2 = new ArrayList<ICard>(pO.getCards());
		Collections.sort(hand1);
		Collections.sort(hand2);
		
		List<ICard> kickers1 = new ArrayList<ICard>(getKickers());
		List<ICard> kickers2 = new ArrayList<ICard>(pO.getKickers());
		Collections.sort(kickers1);
		Collections.sort(kickers2);
		
		//first compare greatest card of the hand
		if (hand1.get(hand1.size() - 1).getNumber().compareTo(hand2.get(hand2.size() - 1).getNumber()) != 0){
			return hand1.get(hand1.size() - 1).getNumber().compareTo(hand2.get(hand2.size() - 1).getNumber());
		}else{
			for (int i = kickers1.size() - 1; i >= 0; i--){
				if (kickers1.get(i).getNumber().compareTo(kickers2.get(i).getNumber()) != 0){
					return kickers1.get(i).getNumber().compareTo(kickers2.get(i).getNumber());
				}
			}
		}
		//the of a kind and all kickers were the same so these hands are equal...
		return 0;
	}
	
	/**
	 * Compare two pair first high pair then if necessary low pair then finally if they are totally equal then compare
	 * the kicker.
	 * @param pO - the other hand
	 * @return an integer between -1 and 1 based on whether this hand was less than equal to or greater than the other hand.
	 */
	private int compareTwoPair(final PokerHand pO){
		
		Map<ECardNumber, List<ICard>> numbers1 = PokerHandUtility.organizeByNumber(getCards());
		Map<ECardNumber, List<ICard>> numbers2 = PokerHandUtility.organizeByNumber(pO.getCards());
		
		List<ICard> highpair1 = new ArrayList<ICard>();
		List<ICard> lowpair1 = new ArrayList<ICard>();		
		List<ICard> highpair2 = new ArrayList<ICard>();
		List<ICard> lowpair2 = new ArrayList<ICard>();
		
		for (ECardNumber number : numbers1.keySet()){
			if (numbers1.get(number).size() == 2){
				if (lowpair1.isEmpty()){
					lowpair1 = numbers1.get(number);
				}else{
					highpair1 = numbers1.get(number);
				}
			}
		}
		for (ECardNumber number : numbers2.keySet()){
			if (numbers2.get(number).size() == 2){
				if (lowpair2.isEmpty()){
					lowpair2 = numbers2.get(number);
				}else{
					highpair2 = numbers2.get(number);
				}
			}
		}
		
		if (highpair1.get(0).getNumber().compareTo(highpair2.get(0).getNumber()) != 0){
			return highpair1.get(0).getNumber().compareTo(highpair2.get(0).getNumber());
		}
		if (lowpair1.get(0).getNumber().compareTo(lowpair2.get(0).getNumber()) != 0){
			return lowpair1.get(0).getNumber().compareTo(lowpair1.get(0).getNumber());
		}
		if (getKickers().get(0).getNumber().compareTo(pO.getKickers().get(0).getNumber()) != 0){
			//only one kicker in two pair
			return getKickers().get(0).getNumber().compareTo(pO.getKickers().get(0).getNumber());
		}
		
		return 0;
	}
	
	/**
	 * For a full house we need to determine which cards are the three of a kind and which are the 
	 * pair then compare.  The pair only comes into play if the trips are equal.
	 * @param pO - the other hand
	 * @return an integer between -1 and 1 based on whether this hand was less than equal to or greater than the other hand.
	 */
	private int compareFullHouse(final PokerHand pO){
		
		Map<ECardNumber, List<ICard>> numbers1 = PokerHandUtility.organizeByNumber(getCards());
		Map<ECardNumber, List<ICard>> numbers2 = PokerHandUtility.organizeByNumber(pO.getCards());
		
		List<ICard> trips1 = new ArrayList<ICard>();
		List<ICard> trips2 = new ArrayList<ICard>();		
		List<ICard> pair1 = new ArrayList<ICard>();
		List<ICard> pair2 = new ArrayList<ICard>();
		
		for (ECardNumber number : numbers1.keySet()){
			if (numbers1.get(number).size() == 3){
				trips1 = numbers1.get(number);
			}else if (numbers1.get(number).size() == 2){		
				pair1 = numbers1.get(number);
			}
		}
		for (ECardNumber number : numbers2.keySet()){
			if (numbers2.get(number).size() == 3){
				trips2 = numbers2.get(number);
			}else if (numbers2.get(number).size() == 2){		
				pair2 = numbers2.get(number);
			}
		}
		
		if (trips1.get(0).getNumber().compareTo(trips2.get(0).getNumber()) != 0){
			return trips1.get(0).getNumber().compareTo(trips2.get(0).getNumber());
		}
		if (pair1.get(0).getNumber().compareTo(pair2.get(0).getNumber()) != 0){
			return pair1.get(0).getNumber().compareTo(pair2.get(0).getNumber());
		}
		return 0;
	}
}
