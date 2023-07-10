package com.air.element;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.ImageIcon;

import com.air.manager.GameLoad;

public class Prop extends ElementObj{
	
	@Override
	public void showElement(Graphics g) {
		g.drawImage(this.getIcon().getImage(), 
				this.getX(), this.getY(), 
				this.getW(), this.getH(), null);	
	}
	
	@Override
	public ElementObj createElement(String str) {
		String[] split = str.split(",");
		this.setX(Integer.parseInt(split[0]));
		this.setY(Integer.parseInt(split[1]));
		this.setW(Integer.parseInt(split[2]));	
		this.setH(Integer.parseInt(split[3]));
		ImageIcon icon = GameLoad.imgMap.get(split[4]);
		for (int i = 0; i < split.length; i++) {
			System.out.println(split[i]);
		}
		this.setIcon(icon);
		return this;
		
	}
	@Override
	protected void move(int gameTime) {
		// TODO 自动生成的方法存根
		super.move(gameTime);
		this.setY(this.getY() + 1);
	}
	

	
	
	
}
