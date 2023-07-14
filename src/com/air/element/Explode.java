package com.air.element;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import com.air.manager.GameLoad;

public class Explode extends ElementObj{
	int x,y,w,h;
	static Image[] pic = new Image[8];
	
	public Explode(int x,int y) {
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
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
	public ElementObj createElement(String str) {
		String[] split = str.split(",");
		this.setX(Integer.parseInt(split[0]));
		this.setY(Integer.parseInt(split[1]));
		this.setW(Integer.parseInt(split[2]));
		this.setY(Integer.parseInt(split[3]));
		ImageIcon icon = GameLoad.imgMap.get(split[4]);
		this.setIcon(icon);
		return this;
	}
	
	@Override
	public void showElement(Graphics g) {
<<<<<<< HEAD
=======
		// TODO 自动生成的方法存根
>>>>>>> 4d6c40a6c7e59fb8776c248625d540f7800be017
		if (count<=7) {
			g.drawImage(pic[count], 
					x, y,
					w,h,null);
			count++;
		}
	}
	
}
