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
		//	System.out.println("dealing card:  " + card);
			aHand.add(card);
		}
		
		System.out.println("Checking my HAND1!!!...");
		PokerHand hand = PokerHandUtility.determineBestHand(aHand);
		System.out.println(hand.getHand());
		System.out.println(hand.getCards());
		System.out.println(hand.getKickers());
		
		List<ICard> aHand2 = new ArrayList<ICard>();
		for (int i = 0; i < 7; i++){
			
			ICard card = deck.dealCard();
			//System.out.println("dealing card2:  " + card);
			aHand2.add(card);
		}
		
		System.out.println("Checking my HAND2!!!...");
		PokerHand hand2 = PokerHandUtility.determineBestHand(aHand2);
		System.out.println(hand2.getHand());
		System.out.println(hand2.getCards());
		System.out.println(hand2.getKickers());
		
		System.out.println("Which hand wins???");
		int winner = hand.compareTo(hand2);
		System.out.println(winner);
		if (winner > 0){
			System.out.println("Hand 1 Wins!!!!");
		}else if (winner < 0){
			System.out.println("Hand 2 Wins!!!!");
		}else{
			System.out.println("They are equal Split...");
		}
	}

}
