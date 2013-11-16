package jtj_holdem.game.tests;

import jtj_holdem.game.data_structures.CardDeck;

public class CardTests {

	public static void main(String[] args) {
		
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
	}

}