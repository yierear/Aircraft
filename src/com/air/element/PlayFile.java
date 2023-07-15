package com.air.element;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.air.manager.ElementManager;
import com.air.manager.GameElement;
import com.air.manager.GameLoad;
import com.air.show.GameLevel;

public class PlayFile extends ElementObj{
	private int attack;//攻击力
	private int moveNum=3;//移动速度值
	private String fx;
	private static String fireType = "1fireType";
	private int ATK = 1;
	public PlayFile() {}
	
	private int fireAndPlay = 1;
	
	@Override   //{X:3,y:5,f:up}
	public  ElementObj createElement(String str) {//定义字符串的规则
		String[] split = str.split(",");
		for(String str1 : split) {//X:3
			String[] split2 = str1.split(":");// 0下标 是 x,y,f   1下标是值
			switch(split2[0]) {
			case "x": this.setX(Integer.parseInt(split2[1]));break;
			case "y":this.setY(Integer.parseInt(split2[1]));break;
			case "f":this.fx=split2[1]; 
//				System.out.println(split2[1]); 
				break;
			}
		}
		this.setW(10);
		this.setH(10);
		return this;
	}
	@Override
	public void showElement(Graphics g) {
		ImageIcon icon1 = GameLoad.imgMap.get(fireType);
		this.setIcon(icon1);
		this.ATKtype(fireType);
		g.drawImage(this.getIcon().getImage(), 
				this.getX()-20, this.getY()-8, 
				this.getW(), this.getH(), null);
	}	

	@Override
	protected void move(int gameTime) {
		if(this.getX()<0 || this.getX() >615 || 
				this.getY() <0 || this.getY()>880) {
			this.setLive(false);
			return;
		}
		switch(this.fx) {
		case "up": this.setY(this.getY()-this.moveNum);break;
		case "left": this.setY(this.getY()-this.moveNum);break;
		case "right": this.setY(this.getY()-this.moveNum);break;
		case "down": this.setY(this.getY()-this.moveNum);break;
		}
	}
	
	public static void changeFire() {
		PlayFile.fireType = Play.getPlayAndFire()+"fireType";
	}
	
	public void ATKtype(String fireType) {
		// TODO 自动生成的方法存根
		switch(fireType) {
		case "1fireType": this.ATK = 1; break;
		case "2fireType": this.ATK = 2; break;
		case "3fireType": this.ATK = 4; break;
		case "4fireType": this.ATK = 8; break;
		case "5fireType": this.ATK = 10; break;
		}
	}
	public int getATK() {
		return ATK;
	}
	
}