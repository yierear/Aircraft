package com.air.element;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.air.manager.GameLoad;

public class Boss extends Enemy{
	@Override
	public void showElement(Graphics g) {
		// TODO 自动生成的方法存根
		super.showElement(g);
		g.drawString("BOSS:", 0, 10);
		g.setColor(Color.RED);
		g.drawRect(50, 0, 100, 15);
		g.fillRect(50, 0, this.getHp(), 15);
		g.drawString(this.getHp()+"", 105, 10);
	}
	@Override
	public ElementObj createElement(String str) {
		// TODO 自动生成的方法存根
		String[] split = str.split(",");
		this.setX(Integer.parseInt(split[0]));
		this.setY(Integer.parseInt(split[1]));
		this.setW(Integer.parseInt(split[2]));	
		this.setH(Integer.parseInt(split[3]));
		this.setHp(80);
		ImageIcon icon = GameLoad.imgMap.get(split[4]);
		this.setIcon(icon);
		return this;
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
