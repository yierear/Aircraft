package com.air.element;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.air.manager.GameLoad;
import com.air.show.GameJFrame;

public class MapObj extends ElementObj{
	public static Image bg = Toolkit.getDefaultToolkit().getImage("image/background/1.png");
	int height = 630;//图片高度
	int firsty = 0,secondy = -700;//firsty第一张图的y坐标；secondy第二张图的y坐标
	protected int number=1;//关卡，默认1
	public MapObj() {}
	public MapObj(int x,int y,int w,int h,ImageIcon icon) {
		super(x,y,w,h,icon);
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
		g.drawImage(this.bg, 
				0, firsty, 
				775, height, null);
		g.drawImage(this.bg, 
				0, secondy, 
				775, height, null);
	}
	
	public void move() {
		if (secondy>=620) {
			secondy=firsty-height;
		}
		else if (firsty>=620) {
			firsty=secondy-height;
		}
		secondy+=3;
		firsty+=3;
	}
	
//	public void updateImage() {
//		this.setIcon(GameLoad.imgMap.get(number));
//	}

}
