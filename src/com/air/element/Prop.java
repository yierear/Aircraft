package com.air.element;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.ImageIcon;

import com.air.manager.GameLoad;

public class Prop extends ElementObj{
	
	@Override
	public void showElement(Graphics g) {
		// TODO 自动生成的方法存根
			g.drawImage(this.getIcon().getImage(), 
					this.getX(), this.getY(), 
					this.getW(), this.getH(), null);	
	}
	
	@Override
	public ElementObj createElement(String str) {
		String[] split = str.split(",");
		this.setX(Integer.parseInt(split[0]));
		this.setY(Integer.parseInt(split[1]));
		ImageIcon icon = GameLoad.imgMap.get(split[2]);
		this.setW(icon.getIconWidth());
		this.setY(icon.getIconHeight());
		for (int i = 0; i < split.length; i++) {
			System.out.println(split[i]);
		}
		return null;
		
	}
	@Override
	protected void move(int gameTime) {
		// TODO 自动生成的方法存根
		super.move(gameTime);
		this.setY(this.getY()+gameTime);
	}
	

	
	
	
}
