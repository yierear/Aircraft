package com.air.element;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.ImageIcon;

import com.air.manager.GameLoad;

public class Prop extends ElementObj{
	
	private static String propType = null;
	
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
		this.setIcon(icon);
		propType = split[4];
		return this;
	}
	
	@Override
	protected void move(int gameTime) {
		this.setY(this.getY() + 1);
	}
	
	public static String getPropType() {
		return propType;
	}

}