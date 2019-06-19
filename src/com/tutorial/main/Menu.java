package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.tutorial.main.Game.STATE;

public class Menu extends MouseAdapter {
	
	private Game game;
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	
	public void mousePressed(MouseEvent e) {
		
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState == STATE.Menu) {
			
			// play button
			if(mouseOver(mx, my, 210, 150, 200, 64)) {		
				
				game.gameState = STATE.Difficulty;
				AudioPlayer.getSound("menu_sound").play();
				return;
			}
			
			// help button
			if(mouseOver(mx, my, 210, 250, 200, 64)) {
				AudioPlayer.getSound("menu_sound").play();
				game.gameState = STATE.Help;
			}
			
			// quit button
			if(mouseOver(mx, my, 210, 350, 200, 64)) {
				System.exit(1);
			}
		}
		
		if(game.gameState == STATE.Difficulty) {
			
			// normal button
			if(mouseOver(mx, my, 210, 150, 200, 64)) {
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemies();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
			
				game.diff = 0;
				
				AudioPlayer.getSound("menu_sound").play();
			}
			
			// hard button
			if(mouseOver(mx, my, 210, 250, 200, 64)) {
				
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemies();
				handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
			
				game.diff = 1;
				
				AudioPlayer.getSound("menu_sound").play();
			}
			
			// back button
			if(mouseOver(mx, my, 210, 350, 200, 64)) {
				game.gameState = STATE.Menu;
				AudioPlayer.getSound("menu_sound").play();
				return;
			}
		}
		
		// back button for help menu
		if(game.gameState == STATE.Help) {
			if(mouseOver(mx, my, 210, 350, 200, 64)) {
				game.gameState = STATE.Menu;
				AudioPlayer.getSound("menu_sound").play();
				return;
			}
		}
		
		// Try again button
			if(game.gameState == STATE.End) {
				if(mouseOver(mx, my, 210, 350, 270, 64)) {
					game.gameState = STATE.Menu;
					hud.setLevel(1);
					hud.setScore(0);
					
					AudioPlayer.getSound("menu_sound").play();
				}
			}
				
			
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public void tick() {
		
	}

	public void render(Graphics g) {	
		
		if(game.gameState == STATE.Menu) {
			
			Font fnt = new Font("ariel", 1, 50);
			Font fnt2 = new Font("ariel", 1, 50);
			
			g.setFont(fnt);
			g.setColor(Color.black);
			g.drawString("Dodge 'Em", 175, 90);
			
			g.setFont(fnt2);
			g.drawRect(210, 150, 200, 64);
			g.drawString("Play", 260, 200);
			
			g.drawRect(210, 250, 200, 64);
			g.drawString("Help", 260, 300);
			
			g.drawRect(210, 350, 200, 64);
			g.drawString("Quit", 260, 400);
			
		} else if(game.gameState == STATE.Help) {
			
			Font fnt = new Font("ariel", 1, 50);
			Font fnt2 = new Font("ariel", 1, 50);
			Font fnt3 = new Font("ariel", 1, 20);
			
			g.setFont(fnt);
			g.setColor(Color.black);
			g.drawString("Help", 240, 70);
			
			g.setFont(fnt3);
			g.drawString("Use WASD keys to move and dodge enemies", 100, 200);
			
			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 260, 400);
		} else if(game.gameState == STATE.End) {
			
			Font fnt = new Font("ariel", 1, 50);
			Font fnt2 = new Font("ariel", 1, 50);
			Font fnt3 = new Font("ariel", 1, 20);
			
			g.setFont(fnt);
			g.setColor(Color.black);
			g.drawString("Game Over", 200, 90);
			
			g.setFont(fnt3);
			g.drawString("You finished with a score of " + hud.getScore(), 190, 200);
			
			g.setFont(fnt2);
			g.drawRect(210, 350, 270, 64);
			g.drawString("Play Again", 210, 400);
		} else if(game.gameState == STATE.Difficulty) {
			
			Font fnt = new Font("ariel", 1, 50);
			Font fnt2 = new Font("ariel", 1, 50);
			
			g.setFont(fnt);
			g.setColor(Color.black);
			g.drawString("Select Difficulty", 120, 90);
			
			g.setFont(fnt2);
			g.drawRect(210, 150, 200, 64);
			g.drawString("Normal", 215, 200);
			
			g.drawRect(210, 250, 200, 64);
			g.drawString("Hard", 250, 300);
			
			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 250, 400);
		}
		
	}
	
}
