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

	public static PokerHand determineBestHand(List<ICard> pHand){

		PokerHand hand = determineStraightFlush(pHand);
		if (!hand.isValid()){
			hand = determineFourOfAKind(pHand);
		}
		if (!hand.isValid()){
			hand = determineFullHouse(pHand);
		}
		if (!hand.isValid()){
			hand = determineFlush(pHand);
		}
		if (!hand.isValid()){
			hand = determineStraight(pHand);
		}
		if (!hand.isValid()){
			hand = determineThreeOfAKind(pHand);
		}
		if (!hand.isValid()){
			hand = determineTwoPair(pHand);
		}
		if (!hand.isValid()){
			hand = determineOnePair(pHand);
		}
		if (!hand.isValid()){
			List <ICard> cards = new ArrayList<ICard>(pHand);
			Collections.sort(cards);
			List <ICard> kickers = new ArrayList<ICard>(pHand);
			kickers.remove(cards.get(cards.size() - 1));
			Collections.sort(kickers);
			while (kickers.size() > 4){
				kickers.remove(0);
			}
			hand = new PokerHand(EPokerHand.HIGH_CARD, Collections.singletonList(cards.get(cards.size() - 1)), 
					kickers);
		}

		return hand;
	}

	public static PokerHand determineStraightFlush(List <ICard> pHand){

		PokerHand hand = PokerHand.INVALID;

		PokerHand flushHand = determineFlush(pHand);	
		if (flushHand.isValid()){

			PokerHand straightFlushHand = determineStraight(flushHand.getCards());
			if (straightFlushHand.isValid()){
				hand = new PokerHand(EPokerHand.STRAIGHT_FLUSH, straightFlushHand.getCards(), Collections.<ICard> emptyList());
			}
		}	
		return hand;
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
				Collections.sort(suits.get(suit));
				hand = new PokerHand(EPokerHand.FLUSH, suits.get(suit), Collections.<ICard> emptyList());
			}
		}
		return hand;
	}

	public static PokerHand determineStraight(List<ICard> pHand){

		PokerHand hand = PokerHand.INVALID;

		List<ICard> sortedList = new ArrayList<ICard>(pHand);
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
					hand = new PokerHand(EPokerHand.STRAIGHT, consecutiveCards, Collections.<ICard> emptyList());
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

	public static PokerHand determineFourOfAKind(final List<ICard> pHand){

		PokerHand hand = PokerHand.INVALID;
		Map<ECardNumber, List<ICard>> numbers = organizeByNumber(pHand);

		for (ECardNumber number : numbers.keySet()){
			if (numbers.get(number).size() == 4){
				List <ICard> kickers = new ArrayList<ICard>(pHand);
				kickers.removeAll(numbers.get(number));
				Collections.sort(kickers);
				while (kickers.size() > 1){
					kickers.remove(0);
				}
				hand = new PokerHand(EPokerHand.FOUR_OF_A_KIND, numbers.get(number), kickers);
				break;
			}
		}
		return hand;
	}

	public static PokerHand determineFullHouse(final List<ICard> pHand){

		PokerHand hand = PokerHand.INVALID;
		Map<ECardNumber, List<ICard>> numbers = organizeByNumber(pHand);

		List <ICard> threeOfAKind = Collections.emptyList();
		List <ICard> pair = Collections.emptyList();
		for (ECardNumber number : numbers.keySet()){
			if (numbers.get(number).size() == 3){
				threeOfAKind = numbers.get(number);
			}else if (numbers.get(number).size() == 2){		
				pair = numbers.get(number);
			}
		}
		if (!threeOfAKind.isEmpty() && !pair.isEmpty()){
			List <ICard> fullHouse = new ArrayList<ICard>(threeOfAKind);
			fullHouse.addAll(pair);
			hand = new PokerHand(EPokerHand.FULL_HOUSE, fullHouse, Collections.<ICard> emptyList());
		}
		return hand;
	}

	public static PokerHand determineThreeOfAKind(final List<ICard> pHand){

		PokerHand hand = PokerHand.INVALID;
		Map<ECardNumber, List<ICard>> numbers = organizeByNumber(pHand);

		for (ECardNumber number : numbers.keySet()){
			if (numbers.get(number).size() == 3){
				List <ICard> kickers = new ArrayList<ICard>(pHand);
				kickers.removeAll(numbers.get(number));
				Collections.sort(kickers);
				while (kickers.size() > 2){
					kickers.remove(0);
				}
				hand = new PokerHand(EPokerHand.THREE_OF_A_KIND, numbers.get(number), kickers);
				break;
			}
		}
		return hand;
	}

	public static PokerHand determineTwoPair(final List<ICard> pHand){

		PokerHand hand = PokerHand.INVALID;
		Map<ECardNumber, List<ICard>> numbers = organizeByNumber(pHand);

		List <ICard> pair1 = Collections.emptyList();
		List <ICard> pair2 = Collections.emptyList();
		for (ECardNumber number : numbers.keySet()){
			if (numbers.get(number).size() == 2){
				if (pair1.isEmpty()){
					pair1 = numbers.get(number);
				}else if (!pair1.isEmpty() && pair2.isEmpty()){
					pair2 = numbers.get(number);
				}
			}
		}
		if (!pair1.isEmpty() && !pair2.isEmpty()){
			List <ICard> pairs = new ArrayList<ICard>(pair1);
			pairs.addAll(pair2);
			List <ICard> kickers = new ArrayList<ICard>(pHand);
			kickers.removeAll(pairs);
			Collections.sort(kickers);
			while (kickers.size() > 1){
				kickers.remove(0);
			}
			hand = new PokerHand(EPokerHand.TWO_PAIR, pairs, kickers);
		}
		return hand;
	}

	public static PokerHand determineOnePair(final List <ICard> pHand){
		PokerHand hand = PokerHand.INVALID;
		Map<ECardNumber, List<ICard>> numbers = organizeByNumber(pHand);

		for (ECardNumber number : numbers.keySet()){
			if (numbers.get(number).size() == 2){
				List <ICard> kickers = new ArrayList<ICard>(pHand);
				kickers.removeAll(numbers.get(number));
				Collections.sort(kickers);
				while (kickers.size() > 3){
					kickers.remove(0);
				}
				hand = new PokerHand(EPokerHand.ONE_PAIR, numbers.get(number), kickers);
			}
		}		
		return hand;
	}

	public static Map<ECardNumber, List<ICard>> organizeByNumber(final List<ICard> pHand){

		Map<ECardNumber, List<ICard>> numbers = new HashMap<ECardNumber, List<ICard>>();
		for (ECardNumber number : ECardNumber.values()){
			numbers.put(number, new ArrayList<ICard>());
		}
		for (ICard card : pHand){
			numbers.get(card.getNumber()).add(card);
		}
		return numbers;
	}
}
