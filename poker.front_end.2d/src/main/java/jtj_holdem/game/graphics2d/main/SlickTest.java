package jtj_holdem.game.graphics2d.main;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jtj_holdem.game.data.Card;
import jtj_holdem.game.data_structures.CardDeck;
import jtj_holdem.game.enums.ECardNumber;
import jtj_holdem.game.enums.ECardSuit;
import jtj_holdem.game.graphics2d.image.util.CardImageUtil;
import jtj_holdem.game.interfaces.ICard;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class SlickTest extends BasicGame
{
	
	private float angle = 0f;
	
	private List<drawCard> drawCardList = new ArrayList<drawCard>();
	
	private CardDeck testDeck = new CardDeck();
	private ICard mDragCard;
	private Point mDragPoint;
	
	public SlickTest(String gamename){
		super(gamename);
		testDeck.populateDeck(false);
		testDeck.shuffleDeck();
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		
		ICard card = new Card(ECardNumber.ACE, ECardSuit.SPADES);
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		Rectangle background = new Rectangle(0, 0, gc.getWidth(), gc.getHeight());
		Image bgImage = new Image("jtj_holdem/game/graphics2d/data/images/backgrounds/red_poker_bg.jpg");
		g.texture(background, bgImage, 1, 1, true);
		
		g.drawString("Hello Texas Holdem!", 10, 200);
		
		ICard card = new Card(ECardNumber.ACE, ECardSuit.SPADES);
		Image newImage = CardImageUtil.getImageForCard(card);
		g.rotate(240, 260, angle);
		Rectangle rect = new Rectangle(200, 200, 80, 120);
		g.texture(rect, newImage, 1, 1, true);
		g.flush();
		
		g.resetTransform();
		card = new Card(ECardNumber.FIVE, ECardSuit.HEARTS);
		Image newImage2 = CardImageUtil.getImageForCard(card);
		Rectangle rect2 = new Rectangle(300, 300, 80, 120);
		g.texture(rect2, newImage2, 1, 1, true);
		
		if (mDragCard != null && mDragPoint != null){
			Image draggingCard = CardImageUtil.getImageForCard(mDragCard);
			if (draggingCard != null){
				Rectangle dragCardRect = new Rectangle(mDragPoint.x - 40, mDragPoint.y - 60, 80, 120);
				g.texture(dragCardRect, draggingCard, 1, 1, true);
			}
		}
		
		for (drawCard drawcard : drawCardList){
			Rectangle drawRect = new Rectangle(drawcard.cardPoint.x - 40, drawcard.cardPoint.y - 60, 80, 120);
			g.texture(drawRect, drawcard.cardImage, 1, 1, true);
		}
		
		angle += .01;
	}

	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		if (mDragCard != null){
			mDragPoint = new Point(newx, newy);
		}
	}
	
	public void mousePressed(int button, int x, int y) {
		mDragCard = testDeck.dealCard();
	}
	
	public void mouseReleased(int button, int x, int y) {
		if (mDragCard != null){
			drawCardList.add(new drawCard(CardImageUtil.getImageForCard(mDragCard), mDragPoint));
		}
		mDragCard = null;
	}
	
	public static void main(String[] args){
		try{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new SlickTest("Simple Slick Game"));
			appgc.setDisplayMode(1280, 720, false);
			appgc.start();
		}
		catch (SlickException ex){
			Logger.getLogger(SlickTest.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	private class drawCard{
		
		public Image cardImage;
		public Point cardPoint;
		
		public drawCard(Image pImage, Point pPoint){
			cardImage = pImage;
			cardPoint = pPoint;
		}
	}
	
}