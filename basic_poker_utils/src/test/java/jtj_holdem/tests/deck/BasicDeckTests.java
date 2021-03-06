package jtj_holdem.tests.deck;

import jtj_holdem.game.data_structures.CardDeck;
import jtj_holdem.game.enums.ECardNumber;
import jtj_holdem.game.enums.ECardSuit;
import jtj_holdem.game.interfaces.ICard;

import org.junit.Assert;
import org.junit.Test;

public class BasicDeckTests {

	@Test
	public void deckCreationTest(){
		
		CardDeck deck = new CardDeck();
		deck.populateDeck(false);
	
		Assert.assertEquals(deck.getDeckSize(), 52);		
		deck.shuffleDeck();
		Assert.assertEquals(deck.getDeckSize(), 52);
	}
	
	@Test
	public void deckCardChecks(){
		
		CardDeck deck = new CardDeck();
		deck.populateDeck(false);
		
		for (ECardNumber number : ECardNumber.values()){
			for(ECardSuit suit : ECardSuit.values()){
				ICard card = deck.dealCard();
				Assert.assertEquals(card.getSuit(), suit);
				Assert.assertEquals(card.getNumber(), number);
			}
		}	
	}
	
}
