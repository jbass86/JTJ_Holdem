package jtj_holdem.game.interfaces;

import jtj_holdem.game.enums.ECardNumber;
import jtj_holdem.game.enums.ECardSuit;

public interface ICard {

	public ECardSuit getSuit();
	
	public ECardNumber getNumber();
}
