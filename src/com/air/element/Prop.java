package com.air.element;

import java.awt.Graphics;

public class Prop extends ElementObj{
	

	@Override
	public void showElement(Graphics g) {
		// TODO 自动生成的方法存根
			g.drawImage(this.getIcon().getImage(), 
					this.getX(), this.getY(), 
					this.getW(), this.getH(), null);
		
	}
	
}
