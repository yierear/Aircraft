package com.air.element;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.ImageIcon;

import com.air.element.ElementObj;
import com.air.manager.ElementManager;
import com.air.manager.GameElement;
import com.air.manager.GameLoad;

public class Enemy1 extends ElementObj{
	private int speed = 2;
	private String fx="up";
	private boolean pkType=true;
	
	@Override
	public void showElement(Graphics g) {
		g.drawImage(this.getIcon().getImage(), 
				this.getX(), this.getY(), 
				this.getW(), this.getH(), null);
	}
	@Override
	public ElementObj createElement(String str) {
		Random ran=new Random();
		int x=ran.nextInt(800);
		int y=0;
		this.setX(x);
		this.setY(y);
		this.setW(50);
		this.setH(50);
		this.setIcon(new ImageIcon("image/enemy/5.png"));
		return this;
	}
	
	public void move() {
		this.setY(this.getY() + speed);
	}
	
	
	
}
