package com.air.element;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.air.manager.GameLoad;
import com.air.show.GameJFrame;

public class MapObj extends ElementObj{
	private int speed;
	private int y1;
	protected int number=1;//关卡，默认1

	public MapObj(int x,int y,int w,int h,ImageIcon icon) {
		super(x,y,w,h,icon);
		y1=-GameJFrame.GameY;		
		speed=1;
	}

	public ElementObj creatElement(String str) {//str：x,y,icon
		String[] split = str.split(",");
		this.setX(Integer.parseInt(split[0]));
		this.setY(Integer.parseInt(split[1]));
		ImageIcon icon=GameLoad.imgMap.get(split[2]);
		this.setW(icon.getIconWidth());
		this.setH(icon.getIconHeight());
		this.setIcon(icon);
//		随关卡变换背景
		return this;
	}

	@Override
	public void showElement(Graphics g) {
		g.drawImage(this.getIcon().getImage(), 
				this.getX(), this.getY(), 
				this.getW(), this.getH(), null);
		g.drawImage(this.getIcon().getImage(), 
				this.getX(), this.getY(), 
				this.getW(), this.getH(), null);
	}
	
	/*背景的移动*/
	public void move() {
		y+=speed;
		y1+=speed;
		if(y>GameJFrame.GameY) {
			y=-GameJFrame.GameY;
			
		}
		if(y1>GameJFrame.GameY) {
			y1=-GameJFrame.GameY;
		}
	}
}
