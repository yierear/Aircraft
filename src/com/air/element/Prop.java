package com.air.element;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.ImageIcon;

import com.air.manager.GameLoad;

public class Prop extends ElementObj{
	private static int hp = 1;
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
		return this;
		
	}
	@Override
	protected void move(int gameTime) {
		this.setY(this.getY() + 1);
	}
	@Override
	public void setLive(boolean live) {
		this.hp--;
	}
	
	/**
	 * @Override  //说明 这个设置扣血等的方法 需要自己思考重新编写。
		public void setLive(boolean live) {
//			被调用一次 就减少一次血。
			if("IRON".equals(name)) {// 水泥墙需要4下
				this.hp--;
				if(this.hp >0) {
					return;
				}
			}
			super.setLive(live);
		}
	 * */
	

	
	
	
}
