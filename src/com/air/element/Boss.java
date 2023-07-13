package com.air.element;

import java.awt.Color;
import java.awt.Graphics;

public class Boss extends Enemy{
	@Override
	public void showElement(Graphics g) {
		// TODO 自动生成的方法存根
		super.showElement(g);
		g.drawString("BOSS:", 0, 10);
		g.setColor(Color.RED);
		g.drawRect(50, 0, 100, 15);
		g.fillRect(50, 0, this.getHp(), 15);
	}
	@Override
	public ElementObj createElement(String str) {
		// TODO 自动生成的方法存根
		return super.createElement(str);
	}
	
	@Override
	public void move(int gametime) {
		this.setY(this.getY()+this.getSpeed());
			
	}
		
	@Override
	public void setLive(boolean live) {
		// TODO 自动生成的方法存根
		this.setHp(this.getHp()-5);		
	}
	
}
