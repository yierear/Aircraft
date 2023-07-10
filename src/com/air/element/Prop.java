package com.air.element;

import java.awt.Graphics;
import java.util.Random;

public class Prop extends ElementObj{
//	private int speed=2;
	
	@Override
	public void showElement(Graphics g) {
		// TODO 自动生成的方法存根
			g.drawImage(this.getIcon().getImage(), 
					this.getX(), this.getY(), 
					this.getW(), this.getH(), null);	
	}
	
	@Override
	public ElementObj createElement(String str) {
		Random random  = new Random();
		int x=random.nextInt(800);
		int y=0;
		this.setX(x);
		this.setY(y);
		this.setW(30);
		this.setH(30);
		String[] split = str.split(",");
		this.setX(Integer.parseInt(split[0]));
		return null;
		
	}
	@Override
	protected void move(int gameTime) {
		// TODO 自动生成的方法存根
		super.move(gameTime);
		this.setY(this.getY()+gameTime);
	}
	

	
	
	
}
