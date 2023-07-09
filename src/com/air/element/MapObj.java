package com.air.element;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.air.manager.GameLoad;
import com.air.show.GameJFrame;

public class MapObj extends ElementObj{
	
	// private ImageIcon img=new ImageIcon("img/Background/1.png");
	 private int moveY=0; //从图片顶端向下滚动
	 private int moveYY=800;//从图片底端向上滚动，大小等于图片的长度
	 private String bg="bg1";
	 
	 public MapObj() {}
	 public MapObj(int x,int y,int w,int h,ImageIcon icon) {
		 super(x,y,w,h,icon);
	 }
	 
	 @Override
		public ElementObj createElement(String str) {
			String[] split = str.split(",");
			this.setX(Integer.parseInt(split[0]));
			this.setY(Integer.parseInt(split[1]));
			ImageIcon icon2 = GameLoad.imgMap.get(split[2]);
			this.setW(icon2.getIconWidth());
			this.setH(icon2.getIconHeight());
			this.setIcon(icon2);
			return this;
		}
	@Override
	public void showElement(Graphics g) {	
		// TODO 自动生成的方法存根
		 g.drawImage(this.getIcon().getImage(), 
				 this.getX(), this.getY(),  
				 this.getW(), this.getH(), null);
		 g.drawImage(this.getIcon().getImage(), 
				 this.getX(), this.getY(),  
				 this.getW(), this.getH(), null);
	}

	@Override
	protected void move(int gameTime) {
		// TODO 自动生成的方法存根
		super.move(gameTime);
		this.setY(this.getY()+gameTime);
		
//		moveY=(moveY>=800)?0:moveY;
//		moveY+=gameTime;
//		moveYY=(moveYY<=0)?800:moveYY;
//		 moveYY-=gameTime; 
	}
	
	protected void updateImage() {
		this.setIcon(GameLoad.imgMap.get(bg));
	}
}
