package com.air.element;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Explode extends ElementObj{

	static Image[] pic = new Image[8];
	
	static int explodeCount=0; //限制爆炸次数为一次
	
	public void loadExplode() {
		for (int i=0; i<pic.length ;i++) {
			pic[i] = new ImageIcon("image/bang/bang" + (i+1) + ".png").getImage();
		}
	}
	
	
	@Override
	public void showElement(Graphics g) {
		// TODO 自动生成的方法存根
		if (explodeCount < pic.length) {
			Image icon = pic[explodeCount];
			explodeCount++;
		}
		
	}
	
}
