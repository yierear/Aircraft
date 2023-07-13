package com.air.element;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;

public class Explode extends ElementObj{
	int x,y;
	static Image[] pic = new Image[8];
	
	public Explode(int x,int y) {
		this.x=x;
		this.y=y;
	}
	
	
	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public void loadExplode() {
		for (int i=0; i<pic.length ;i++) {
			pic[i] = new ImageIcon("image/bang/bang" + (i+1) + ".png").getImage();
			pic[i].getWidth(null);
		}
	}
	
	int count;//当前显示的图片序号
	
	
	@Override
	public void showElement(Graphics g) {
		// TODO 自动生成的方法存根
//		if (explodeCount < pic.length) {
//			Image icon = pic[explodeCount];
//			explodeCount++;
//		}
		if (count<=7) {
			g.drawImage(pic[count], x, y, null);
			count++;
		}
	}
	
}
