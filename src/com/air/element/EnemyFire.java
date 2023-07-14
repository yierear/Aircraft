package com.air.element;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.air.manager.GameLoad;

public class EnemyFire extends ElementObj{
	private int attack;//攻击力
	private int moveNum=3;//移动速度值
	private String fx;
	private String fireType;
	private int ATK;

	public EnemyFire() {}//一个空的构造方法
	
	@Override   //{X:3,y:5,f:up}
	public  ElementObj createElement(String str) {//定义字符串的规则
		String[] split = str.split(",");
		for(String str1 : split) {//X:3
			String[] split2 = str1.split(":");// 0下标 是 x,y,f   1下标是值
			switch(split2[0]) {
			case "x": this.setX(Integer.parseInt(split2[1]));break;
			case "y":this.setY(Integer.parseInt(split2[1]));break;
			case "w":this.setW(Integer.parseInt(split2[1]));break;
			case "h":this.setH(Integer.parseInt(split2[1]));break;
			case "f":this.fx=split2[1]; 
//				System.out.println(split2[1]); 
				break;
			case "type":
				this.fireType=split2[1]; 
				ImageIcon icon1 = GameLoad.imgMap.get(split2[1]);
				this.setIcon(icon1);
				ATKtype(fireType);
				break;				
			}
		}
		return this;
	}
	@Override
	public void showElement(Graphics g) {
		g.drawImage(this.getIcon().getImage(), 
				this.getX(), this.getY(), 
				this.getW(), this.getH(), null);
	}	

	@Override
	protected void move(int gameTime) {
		if(this.getY()<0 ||this.getY()>880) {
			this.setLive(false);
			return;
		}
		
		this.setY(this.getY()+this.moveNum);
	}
	
	public void ATKtype(String fireType) {
		// TODO 自动生成的方法存根
		switch(fireType) {
		case "1fireType": this.ATK = 2; break;
		case "2fireType": this.ATK = 4; break;
		case "3fireType": this.ATK = 6; break;
		case "4fireType": this.ATK = 8; break;
		case "5fireType": this.ATK = 10; break;
		}
	}

	public int getATK() {
		return ATK;
	}
	

}
