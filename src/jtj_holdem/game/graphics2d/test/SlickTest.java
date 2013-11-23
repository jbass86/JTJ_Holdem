package jtj_holdem.game.graphics2d.test;
import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

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
	
	public SlickTest(String gamename)
	{
		super(gamename);
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		
		
		Image newImage = new Image("res/images/cards/1.png");

	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		g.drawString("Howdy!", 10, 200);
		g.setBackground(org.newdawn.slick.Color.green);
		
		Image newImage = new Image("res/images/cards/1.png");
		g.rotate(240, 260, angle);
		Rectangle rect = new Rectangle(200, 200, 80, 120);
		g.texture(rect, newImage, 1, 1, true);
		g.flush();
		
		g.resetTransform();
		Image newImage2 = new Image("res/images/cards/5.png");		
		Rectangle rect2 = new Rectangle(300, 300, 80, 120);
		g.texture(rect2, newImage2, 1, 1, true);
		
		angle += .01;
	}

	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		System.out.println("The mouse was dragged " + newx +  " " + newy);
	}
	
	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new SlickTest("Simple Slick Game"));
			appgc.setDisplayMode(640, 480, false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(SlickTest.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}