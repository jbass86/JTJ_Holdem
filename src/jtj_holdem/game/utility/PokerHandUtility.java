package jtj_holdem.game.utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jtj_holdem.game.data_structures.PokerHand;
import jtj_holdem.game.enums.ECardNumber;
import jtj_holdem.game.enums.ECardSuit;
import jtj_holdem.game.enums.EPokerHand;
import jtj_holdem.game.interfaces.ICard;

public class PokerHandUtility {

	public static EPokerHand determineBestHand(List<ICard> pHand){
		
		return null;
	}
	
	public static PokerHand determineFlush(List<ICard> pHand){
		
		PokerHand hand = PokerHand.INVALID;
		
		Map<ECardSuit, List<ICard>> suits = new HashMap<ECardSuit, List<ICard>>();
		for (ECardSuit suit : ECardSuit.values()){
			suits.put(suit, new ArrayList<ICard>());
		}
		
		for (ICard card : pHand){
			suits.get(card.getSuit()).add(card);
		}
		
		for (ECardSuit suit : suits.keySet()){
			if (suits.get(suit).size() >= 5){
				hand = new PokerHand(EPokerHand.FLUSH, suits.get(suit));
			}
		}
		return hand;
	}
	
	public static PokerHand determineStraight(List<ICard> pHand){
		
		PokerHand hand = PokerHand.INVALID;
		
		List<ICard> sortedList = new ArrayList<>(pHand);
		Collections.sort(sortedList);
		
		List<ICard> consecutiveCards = new ArrayList<ICard>();
		int lastNumber = 0;
		for (ICard card : sortedList){
			if (lastNumber > 0){
				if (card.getNumber().getNumber() == lastNumber + 1){
					consecutiveCards.add(card);
				}else if (card.getNumber().getNumber() != lastNumber){
					consecutiveCards.clear();
				}
				if (consecutiveCards.size() == 5){
					hand = new PokerHand(EPokerHand.STRAIGHT, consecutiveCards);
					break;
				}
			}else{
				//This is the very first card so just add it to our consecutive list
				//as we have nothing to compare it to yet.
				consecutiveCards.add(card);
				if (card.getNumber() == ECardNumber.TWO){
					//since two is our first we need to check for an ace and add it if its there.
					if (sortedList.get(sortedList.size() - 1).getNumber() == ECardNumber.ACE){
						consecutiveCards.add(sortedList.get(sortedList.size() - 1));
					}
				}
			}
			lastNumber = card.getNumber().getNumber();
		}
		
		return hand;
	}
}
