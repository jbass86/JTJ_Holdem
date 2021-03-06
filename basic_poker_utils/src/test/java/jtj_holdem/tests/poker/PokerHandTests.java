package jtj_holdem.tests.poker;

import java.util.ArrayList;
import java.util.List;

import jtj_holdem.game.data.Card;
import jtj_holdem.game.data_structures.PokerHand;
import jtj_holdem.game.enums.ECardNumber;
import jtj_holdem.game.enums.ECardSuit;
import jtj_holdem.game.enums.EPokerHand;
import jtj_holdem.game.interfaces.ICard;
import jtj_holdem.game.utility.PokerHandUtility;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test all the different poker hand possibilites and all different combination to make sure that
 * my utilites work...
 * @author Josh
 *
 */
public class PokerHandTests {

	@Test
	public void testHighCard1(){

		List<ICard> initialCards = new ArrayList<ICard>();
		initialCards.add(new Card(ECardNumber.EIGHT, ECardSuit.SPADES));
		initialCards.add(new Card(ECardNumber.TWO, ECardSuit.CLUBS));
		initialCards.add(new Card(ECardNumber.THREE, ECardSuit.HEARTS));
		initialCards.add(new Card(ECardNumber.JACK, ECardSuit.SPADES));
		initialCards.add(new Card(ECardNumber.SIX, ECardSuit.HEARTS));
		initialCards.add(new Card(ECardNumber.NINE, ECardSuit.DIAMONDS));
		initialCards.add(new Card(ECardNumber.TEN, ECardSuit.HEARTS));
		List<ICard> kickers = new ArrayList<ICard>();
		kickers.add(new Card(ECardNumber.EIGHT, ECardSuit.SPADES));
		kickers.add(new Card(ECardNumber.NINE, ECardSuit.DIAMONDS));
		kickers.add(new Card(ECardNumber.TEN, ECardSuit.HEARTS));
		kickers.add(new Card(ECardNumber.SIX, ECardSuit.HEARTS));


		PokerHand hand = PokerHandUtility.determineBestHand(initialCards);

		Assert.assertEquals(hand.getHand(), EPokerHand.HIGH_CARD);	
		Assert.assertEquals(hand.getCards().get(0), new Card(ECardNumber.JACK, ECardSuit.SPADES));	
		verifyCardList(hand.getKickers(), kickers);
	}

	@Test
	public void testHighCard2(){

		List<ICard> initialCards = new ArrayList<ICard>();
		initialCards.add(new Card(ECardNumber.ACE, ECardSuit.DIAMONDS));
		initialCards.add(new Card(ECardNumber.TWO, ECardSuit.SPADES));
		initialCards.add(new Card(ECardNumber.THREE, ECardSuit.HEARTS));
		initialCards.add(new Card(ECardNumber.FOUR, ECardSuit.HEARTS));
		initialCards.add(new Card(ECardNumber.KING, ECardSuit.CLUBS));
		initialCards.add(new Card(ECardNumber.NINE, ECardSuit.HEARTS));
		initialCards.add(new Card(ECardNumber.TEN, ECardSuit.HEARTS));
		List<ICard> kickers = new ArrayList<ICard>();
		kickers.add(new Card(ECardNumber.KING, ECardSuit.CLUBS));
		kickers.add(new Card(ECardNumber.TEN, ECardSuit.HEARTS));
		kickers.add(new Card(ECardNumber.NINE, ECardSuit.HEARTS));
		kickers.add(new Card(ECardNumber.FOUR, ECardSuit.HEARTS));

		PokerHand hand = PokerHandUtility.determineBestHand(initialCards);

		Assert.assertEquals(hand.getHand(), EPokerHand.HIGH_CARD);	
		Assert.assertEquals(hand.getCards().get(0), new Card(ECardNumber.ACE, ECardSuit.DIAMONDS));
		verifyCardList(hand.getKickers(), kickers);
	}

	@Test
	public void highCardTest3(){

		List<ICard> initialCards = new ArrayList<ICard>();
		initialCards.add(new Card(ECardNumber.NINE, ECardSuit.DIAMONDS));
		initialCards.add(new Card(ECardNumber.QUEEN, ECardSuit.SPADES));
		initialCards.add(new Card(ECardNumber.KING, ECardSuit.HEARTS));
		initialCards.add(new Card(ECardNumber.SIX, ECardSuit.SPADES));
		initialCards.add(new Card(ECardNumber.SEVEN, ECardSuit.CLUBS));
		initialCards.add(new Card(ECardNumber.FIVE, ECardSuit.HEARTS));
		initialCards.add(new Card(ECardNumber.ACE, ECardSuit.HEARTS));	



		List<ICard> handCards = new ArrayList<ICard>();
		handCards.add(new Card(ECardNumber.ACE, ECardSuit.HEARTS));
		List<ICard> kickers = new ArrayList<ICard>();
		kickers.add(new Card(ECardNumber.KING, ECardSuit.HEARTS));
		kickers.add(new Card(ECardNumber.QUEEN, ECardSuit.SPADES));
		kickers.add(new Card(ECardNumber.NINE, ECardSuit.DIAMONDS));
		kickers.add(new Card(ECardNumber.SEVEN, ECardSuit.CLUBS));

		PokerHand hand = PokerHandUtility.determineBestHand(initialCards);

		Assert.assertEquals(hand.getHand(), EPokerHand.HIGH_CARD);	
		verifyCardList(hand.getCards(), handCards);
		verifyCardList(hand.getKickers(), kickers);
	}

	@Test
	public void testOnePair1(){

		List<ICard> initialCards = new ArrayList<ICard>();
		initialCards.add(new Card(ECardNumber.ACE, ECardSuit.DIAMONDS));
		initialCards.add(new Card(ECardNumber.TWO, ECardSuit.SPADES));
		initialCards.add(new Card(ECardNumber.THREE, ECardSuit.HEARTS));
		initialCards.add(new Card(ECardNumber.FOUR, ECardSuit.HEARTS));
		initialCards.add(new Card(ECardNumber.ACE, ECardSuit.CLUBS));
		initialCards.add(new Card(ECardNumber.NINE, ECardSuit.HEARTS));
		initialCards.add(new Card(ECardNumber.TEN, ECardSuit.HEARTS));
		List<ICard> handCards = new ArrayList<ICard>();
		handCards.add(new Card(ECardNumber.ACE, ECardSuit.DIAMONDS));
		handCards.add(new Card(ECardNumber.ACE, ECardSuit.CLUBS));
		List<ICard> kickers = new ArrayList<ICard>();
		kickers.add(new Card(ECardNumber.TEN, ECardSuit.HEARTS));
		kickers.add(new Card(ECardNumber.NINE, ECardSuit.HEARTS));
		kickers.add(new Card(ECardNumber.FOUR, ECardSuit.HEARTS));

		PokerHand hand = PokerHandUtility.determineBestHand(initialCards);

		Assert.assertEquals(hand.getHand(), EPokerHand.ONE_PAIR);	
		verifyCardList(hand.getCards(), handCards);
		verifyCardList(hand.getKickers(), kickers);
	}

	@Test
	public void testOnePair2(){

		List<ICard> initialCards = new ArrayList<ICard>();
		initialCards.add(new Card(ECardNumber.ACE, ECardSuit.DIAMONDS));
		initialCards.add(new Card(ECardNumber.TWO, ECardSuit.SPADES));
		initialCards.add(new Card(ECardNumber.THREE, ECardSuit.HEARTS));
		initialCards.add(new Card(ECardNumber.FOUR, ECardSuit.HEARTS));
		initialCards.add(new Card(ECardNumber.TWO, ECardSuit.CLUBS));
		initialCards.add(new Card(ECardNumber.NINE, ECardSuit.HEARTS));
		initialCards.add(new Card(ECardNumber.TEN, ECardSuit.HEARTS));
		List<ICard> handCards = new ArrayList<ICard>();
		handCards.add(new Card(ECardNumber.TWO, ECardSuit.SPADES));
		handCards.add(new Card(ECardNumber.TWO, ECardSuit.CLUBS));
		List<ICard> kickers = new ArrayList<ICard>();
		kickers.add(new Card(ECardNumber.ACE, ECardSuit.DIAMONDS));
		kickers.add(new Card(ECardNumber.TEN, ECardSuit.HEARTS));
		kickers.add(new Card(ECardNumber.NINE, ECardSuit.HEARTS));

		PokerHand hand = PokerHandUtility.determineBestHand(initialCards);

		Assert.assertEquals(hand.getHand(), EPokerHand.ONE_PAIR);	
		verifyCardList(hand.getCards(), handCards);
		verifyCardList(hand.getKickers(), kickers);
	}

	@Test
	public void testOnePair3(){

		List<ICard> initialCards = new ArrayList<ICard>();
		initialCards.add(new Card(ECardNumber.ACE, ECardSuit.DIAMONDS));
		initialCards.add(new Card(ECardNumber.TWO, ECardSuit.SPADES));
		initialCards.add(new Card(ECardNumber.THREE, ECardSuit.HEARTS));
		initialCards.add(new Card(ECardNumber.TEN, ECardSuit.SPADES));
		initialCards.add(new Card(ECardNumber.SIX, ECardSuit.CLUBS));
		initialCards.add(new Card(ECardNumber.NINE, ECardSuit.HEARTS));
		initialCards.add(new Card(ECardNumber.TEN, ECardSuit.HEARTS));
		List<ICard> handCards = new ArrayList<ICard>();
		handCards.add(new Card(ECardNumber.TEN, ECardSuit.SPADES));
		handCards.add(new Card(ECardNumber.TEN, ECardSuit.HEARTS));
		List<ICard> kickers = new ArrayList<ICard>();
		kickers.add(new Card(ECardNumber.ACE, ECardSuit.DIAMONDS));
		kickers.add(new Card(ECardNumber.SIX, ECardSuit.CLUBS));
		kickers.add(new Card(ECardNumber.NINE, ECardSuit.HEARTS));

		PokerHand hand = PokerHandUtility.determineBestHand(initialCards);

		Assert.assertEquals(hand.getHand(), EPokerHand.ONE_PAIR);	
		verifyCardList(hand.getCards(), handCards);
		verifyCardList(hand.getKickers(), kickers);
	}

	@Test
	public void testTwoPair1(){

		List<ICard> initialCards = new ArrayList<ICard>();
		initialCards.add(new Card(ECardNumber.ACE, ECardSuit.DIAMONDS));
		initialCards.add(new Card(ECardNumber.TWO, ECardSuit.SPADES));
		initialCards.add(new Card(ECardNumber.TEN, ECardSuit.HEARTS));
		initialCards.add(new Card(ECardNumber.TEN, ECardSuit.SPADES));
		initialCards.add(new Card(ECardNumber.SIX, ECardSuit.CLUBS));
		initialCards.add(new Card(ECardNumber.NINE, ECardSuit.HEARTS));
		initialCards.add(new Card(ECardNumber.ACE, ECardSuit.HEARTS));
		List<ICard> handCards = new ArrayList<ICard>();
		handCards.add(new Card(ECardNumber.TEN, ECardSuit.SPADES));
		handCards.add(new Card(ECardNumber.TEN, ECardSuit.HEARTS));
		handCards.add(new Card(ECardNumber.ACE, ECardSuit.DIAMONDS));
		handCards.add(new Card(ECardNumber.ACE, ECardSuit.HEARTS));
		List<ICard> kickers = new ArrayList<ICard>();
		kickers.add(new Card(ECardNumber.NINE, ECardSuit.HEARTS));

		PokerHand hand = PokerHandUtility.determineBestHand(initialCards);

		Assert.assertEquals(hand.getHand(), EPokerHand.TWO_PAIR);	
		verifyCardList(hand.getCards(), handCards);
		verifyCardList(hand.getKickers(), kickers);
	}

	@Test
	public void testTwoPair2(){

		List<ICard> initialCards = new ArrayList<ICard>();
		initialCards.add(new Card(ECardNumber.SIX, ECardSuit.DIAMONDS));
		initialCards.add(new Card(ECardNumber.TWO, ECardSuit.CLUBS));
		initialCards.add(new Card(ECardNumber.NINE, ECardSuit.HEARTS));
		initialCards.add(new Card(ECardNumber.TEN, ECardSuit.SPADES));
		initialCards.add(new Card(ECardNumber.SIX, ECardSuit.CLUBS));
		initialCards.add(new Card(ECardNumber.THREE, ECardSuit.DIAMONDS));
		initialCards.add(new Card(ECardNumber.NINE, ECardSuit.SPADES));
		List<ICard> handCards = new ArrayList<ICard>();
		handCards.add(new Card(ECardNumber.SIX, ECardSuit.DIAMONDS));
		handCards.add(new Card(ECardNumber.SIX, ECardSuit.CLUBS));
		handCards.add(new Card(ECardNumber.NINE, ECardSuit.HEARTS));
		handCards.add(new Card(ECardNumber.NINE, ECardSuit.SPADES));
		List<ICard> kickers = new ArrayList<ICard>();
		kickers.add(new Card(ECardNumber.TEN, ECardSuit.SPADES));

		PokerHand hand = PokerHandUtility.determineBestHand(initialCards);

		Assert.assertEquals(hand.getHand(), EPokerHand.TWO_PAIR);	
		verifyCardList(hand.getCards(), handCards);
		verifyCardList(hand.getKickers(), kickers);
	}

	@Test
	public void testTwoPair3(){

		List<ICard> initialCards = new ArrayList<ICard>();
		initialCards.add(new Card(ECardNumber.THREE, ECardSuit.SPADES));
		initialCards.add(new Card(ECardNumber.KING, ECardSuit.HEARTS));
		initialCards.add(new Card(ECardNumber.NINE, ECardSuit.HEARTS));
		initialCards.add(new Card(ECardNumber.KING, ECardSuit.SPADES));
		initialCards.add(new Card(ECardNumber.SIX, ECardSuit.CLUBS));
		initialCards.add(new Card(ECardNumber.THREE, ECardSuit.CLUBS));
		initialCards.add(new Card(ECardNumber.ACE, ECardSuit.SPADES));
		List<ICard> handCards = new ArrayList<ICard>();
		handCards.add(new Card(ECardNumber.THREE, ECardSuit.SPADES));
		handCards.add(new Card(ECardNumber.THREE, ECardSuit.CLUBS));
		handCards.add(new Card(ECardNumber.KING, ECardSuit.HEARTS));
		handCards.add(new Card(ECardNumber.KING, ECardSuit.SPADES));
		List<ICard> kickers = new ArrayList<ICard>();
		kickers.add(new Card(ECardNumber.ACE, ECardSuit.SPADES));

		PokerHand hand = PokerHandUtility.determineBestHand(initialCards);

		Assert.assertEquals(hand.getHand(), EPokerHand.TWO_PAIR);	
		verifyCardList(hand.getCards(), handCards);
		verifyCardList(hand.getKickers(), kickers);
	}

	@Test
	public void test3OfaKind1(){

		List<ICard> initialCards = new ArrayList<ICard>();
		initialCards.add(new Card(ECardNumber.KING, ECardSuit.CLUBS));
		initialCards.add(new Card(ECardNumber.KING, ECardSuit.SPADES));
		initialCards.add(new Card(ECardNumber.FOUR, ECardSuit.HEARTS));
		initialCards.add(new Card(ECardNumber.KING, ECardSuit.DIAMONDS));
		initialCards.add(new Card(ECardNumber.SEVEN, ECardSuit.DIAMONDS));
		initialCards.add(new Card(ECardNumber.THREE, ECardSuit.CLUBS));
		initialCards.add(new Card(ECardNumber.ACE, ECardSuit.SPADES));
		List<ICard> handCards = new ArrayList<ICard>();
		handCards.add(new Card(ECardNumber.KING, ECardSuit.CLUBS));
		handCards.add(new Card(ECardNumber.KING, ECardSuit.SPADES));
		handCards.add(new Card(ECardNumber.KING, ECardSuit.DIAMONDS));
		List<ICard> kickers = new ArrayList<ICard>();
		kickers.add(new Card(ECardNumber.ACE, ECardSuit.SPADES));
		kickers.add(new Card(ECardNumber.SEVEN, ECardSuit.DIAMONDS));

		PokerHand hand = PokerHandUtility.determineBestHand(initialCards);

		Assert.assertEquals(hand.getHand(), EPokerHand.THREE_OF_A_KIND);	
		verifyCardList(hand.getCards(), handCards);
		verifyCardList(hand.getKickers(), kickers);
	}

	
	
	private static void verifyCardList(final List<ICard> pList1, final List<ICard> pList2){
		for (ICard card : pList1){
			Assert.assertEquals(pList2.contains(card), true);
		}
	}
}
