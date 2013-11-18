package jtj_holdem.tests;

import java.util.ArrayList;
import java.util.List;

import jtj_holdem.game.data.Card;
import jtj_holdem.game.data_structures.CardDeck;
import jtj_holdem.game.data_structures.PokerHand;
import jtj_holdem.game.enums.ECardNumber;
import jtj_holdem.game.enums.ECardSuit;
import jtj_holdem.game.interfaces.ICard;
import jtj_holdem.game.utility.PokerHandUtility;

public class CardTests {

	public static void main(String[] args) {
		
		/**
		 * Need to change this to use JUnit.
		 */
		System.out.println("Creating Deck...");
		
		CardDeck deck = new CardDeck();
		
		deck.populateDeck(false);
		System.out.println("Deck before shuffle");
		deck.printDeck();
		
		deck.shuffleDeck();
		System.out.println("###################################");
		System.out.println("Deck after shuffle");
		deck.printDeck();
		
		System.out.println("####################################");
		System.out.println("Deal a Card to me.....");
		System.out.println(deck.dealCard());
		System.out.println("Deal another card......");
		System.out.println(deck.dealCard());
		
		List<ICard> aHand = new ArrayList<ICard>();
		for (int i = 0; i < 7; i++){
			
			ICard card = deck.dealCard();
			System.out.println("dealing card:  " + card);
			aHand.add(card);
		}
		
		System.out.println("Checking my hand...");
		PokerHand hand = PokerHandUtility.determineBestHand(aHand);
		System.out.println(hand.getHand());
		System.out.println(hand.getCards());
		System.out.println(hand.getKickers());
		
//		System.out.println("check for flush");
//		PokerHand hand = PokerHandUtility.determineFlush(aHand);
//		System.out.println("hand returned");
//		System.out.println(hand.getHand());
//		System.out.println(hand.getCards());
//		
//		System.out.println("check for straight");
//		hand = PokerHandUtility.determineStraight(aHand);
//		System.out.println("hand returned");
//		System.out.println(hand.getHand());
//		System.out.println(hand.getCards());
//		
//		aHand = new ArrayList<ICard>();
//		aHand.add(new Card(ECardNumber.FOUR, ECardSuit.CLUBS));
//		aHand.add(new Card(ECardNumber.SIX, ECardSuit.CLUBS));
//		aHand.add(new Card(ECardNumber.FIVE, ECardSuit.CLUBS));
//		aHand.add(new Card(ECardNumber.JACK, ECardSuit.CLUBS));
//		aHand.add(new Card(ECardNumber.ACE, ECardSuit.CLUBS));
//		aHand.add(new Card(ECardNumber.THREE, ECardSuit.CLUBS));
//		aHand.add(new Card(ECardNumber.SEVEN, ECardSuit.CLUBS));
//		
//		System.out.println("should be a straight");
//		hand = PokerHandUtility.determineStraight(aHand);
//		System.out.println("hand returned");
//		System.out.println(hand.getHand());
//		System.out.println(hand.getCards());
//		
//		aHand = new ArrayList<ICard>();
//		aHand.add(new Card(ECardNumber.FOUR, ECardSuit.CLUBS));
//		aHand.add(new Card(ECardNumber.JACK, ECardSuit.CLUBS));
//		aHand.add(new Card(ECardNumber.FIVE, ECardSuit.CLUBS));
//		aHand.add(new Card(ECardNumber.TWO, ECardSuit.CLUBS));
//		aHand.add(new Card(ECardNumber.ACE, ECardSuit.CLUBS));
//		aHand.add(new Card(ECardNumber.THREE, ECardSuit.CLUBS));
//		aHand.add(new Card(ECardNumber.SEVEN, ECardSuit.CLUBS));
//		
//		System.out.println("should be a straight ace low");
//		hand = PokerHandUtility.determineStraight(aHand);
//		System.out.println("hand returned");
//		System.out.println(hand.getHand());
//		System.out.println(hand.getCards());
//		
//		
//		aHand = new ArrayList<ICard>();
//		aHand.add(new Card(ECardNumber.FOUR, ECardSuit.CLUBS));
//		aHand.add(new Card(ECardNumber.ACE, ECardSuit.CLUBS));
//		aHand.add(new Card(ECardNumber.FIVE, ECardSuit.CLUBS));
//		aHand.add(new Card(ECardNumber.ACE, ECardSuit.CLUBS));
//		aHand.add(new Card(ECardNumber.ACE, ECardSuit.CLUBS));
//		aHand.add(new Card(ECardNumber.THREE, ECardSuit.CLUBS));
//		aHand.add(new Card(ECardNumber.ACE, ECardSuit.CLUBS));
//		
//		System.out.println("should be a four of a kind");
//		hand = PokerHandUtility.determineFourOfAKind(aHand);
//		System.out.println("hand returned");
//		System.out.println(hand.getHand());
//		System.out.println(hand.getCards());
//		System.out.println(hand.getKickers());
//		
//		aHand = new ArrayList<ICard>();
//		aHand.add(new Card(ECardNumber.FOUR, ECardSuit.CLUBS));
//		aHand.add(new Card(ECardNumber.JACK, ECardSuit.CLUBS));
//		aHand.add(new Card(ECardNumber.FIVE, ECardSuit.CLUBS));
//		aHand.add(new Card(ECardNumber.TWO, ECardSuit.CLUBS));
//		aHand.add(new Card(ECardNumber.ACE, ECardSuit.CLUBS));
//		aHand.add(new Card(ECardNumber.THREE, ECardSuit.CLUBS));
//		aHand.add(new Card(ECardNumber.SEVEN, ECardSuit.CLUBS));
//		
//		System.out.println("should be a straight flush ace low");
//		hand = PokerHandUtility.determineStraightFlush(aHand);
//		System.out.println("hand returned");
//		System.out.println(hand.getHand());
//		System.out.println(hand.getCards());
//		
//		aHand = new ArrayList<ICard>();
//		aHand.add(new Card(ECardNumber.TWO, ECardSuit.CLUBS));
//		aHand.add(new Card(ECardNumber.ACE, ECardSuit.CLUBS));
//		aHand.add(new Card(ECardNumber.FIVE, ECardSuit.CLUBS));
//		aHand.add(new Card(ECardNumber.TWO, ECardSuit.CLUBS));
//		aHand.add(new Card(ECardNumber.ACE, ECardSuit.CLUBS));
//		aHand.add(new Card(ECardNumber.THREE, ECardSuit.CLUBS));
//		aHand.add(new Card(ECardNumber.ACE, ECardSuit.CLUBS));
//		
//		System.out.println("should be a full house");
//		hand = PokerHandUtility.determineFullHouse(aHand);
//		System.out.println("hand returned");
//		System.out.println(hand.getHand());
//		System.out.println(hand.getCards());
	}

}