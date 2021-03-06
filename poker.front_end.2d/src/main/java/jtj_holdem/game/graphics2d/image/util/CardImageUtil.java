package jtj_holdem.game.graphics2d.image.util;

import java.util.HashMap;
import java.util.Map;

import jtj_holdem.game.data_structures.CardDeck;
import jtj_holdem.game.interfaces.ICard;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class CardImageUtil {

	private static Map <ICard, Image> sCardImageMap = new HashMap<ICard, Image>();
	
	static {
		
		CardDeck cardDeck = new CardDeck();
		cardDeck.populateDeck(false);
		
		ICard card = cardDeck.dealCard();
		while (card != null){
			//for each card we will create an image and place it in our static map for later reference
			String imagePath = "jtj_holdem/game/graphics2d/data/images/cards/" + 
					card.getNumber().getName() + "_" + card.getSuit().getName() + ".png";
			try {
				sCardImageMap.put(card, new Image(imagePath));
			} catch (SlickException e) {
				System.out.println("Error creating card image " + e.getMessage());
				e.printStackTrace();
			}	
			card = cardDeck.dealCard();
		}
	}
	
	public static Image getImageForCard(final ICard pCard){
		return sCardImageMap.get(pCard);
	}
}
