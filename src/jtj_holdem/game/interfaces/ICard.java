package jtj_holdem.game.interfaces;

import jtj_holdem.game.enums.ECardNumber;
import jtj_holdem.game.enums.ESuit;

public interface ICard {

	public ESuit getSuit();
	
	public ECardNumber getCardNumber();
}
