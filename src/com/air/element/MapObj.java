package com.air.element;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Timer;

import javax.swing.ImageIcon;

import com.air.manager.GameLoad;
import com.air.show.GameJFrame;

public class MapObj extends ElementObj{
	
	// private ImageIcon img=new ImageIcon("img/Background/1.png");
	 private int moveY=0; //从图片顶端向下滚动
	 private int moveYY=800;//从图片底端向上滚动，大小等于图片的长度
//	 private String bg="bg1";
	
	 
	 public MapObj() {}
	 public MapObj(int x,int y,int w,int h,ImageIcon icon) {
		 super(x,y,w,h,icon);
		 
	 }
	 
	 @Override
		public ElementObj createElement(String str) {
//		 System.out.println(str);
			String[] split = str.split(",");
			this.setX(Integer.parseInt(split[0]));
			this.setY(Integer.parseInt(split[1]));
			this.setW(Integer.parseInt(split[2]));	
			this.setH(Integer.parseInt(split[3]));
//			System.out.println(split[2]);
			ImageIcon icon = GameLoad.imgMap.get(split[4]);
//			System.out.println(split[3]);
			
			this.setIcon(icon);
//			System.out.println(split[4]);
			
			return this;
		}
	@Override
	public void showElement(Graphics g) {	
		// TODO 自动生成的方法存根
		 
		 Graphics2D g2d=(Graphics2D) g;
		 if (this.getIcon().getImage()==null) {
			return;
		}
		 g.drawImage(this.getIcon().getImage(), 
				 this.getX(), this.getY(),  
				 this.getW(), this.getH(), null);
		 g2d.drawImage(this.getIcon().getImage(), 
				 this.getX(), this.getY()-this.getH(),  
				 this.getW(), this.getH(), null);
	}

	@Override
	protected void move(int gameTime) {
		// TODO 自动生成的方法存根
//		super.move(gameTime);
		
		this.setY(this.getY()+1);
		if (this.getY()>=this.getH()) {
			this.setY(0);
		}
 
	}
	
//	protected void updateImage() {
//		this.setIcon(GameLoad.imgMap.get(bg));
//	}
}
