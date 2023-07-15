package com.air.element;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.ImageIcon;

import com.air.controller.GameThread;
import com.air.manager.ElementManager;
import com.air.manager.GameElement;
import com.air.manager.GameLoad;
import com.air.show.GameLevel;

public class Enemy extends ElementObj{
	private int speed = 1;
	private String fx="down";
	private boolean pkType=true;
	private int hp;
	Random random=new Random();
	int x=random.nextInt(400);
	private String enemyType;	//敌人类型
	
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
		enemyType=split[4];
		ImageIcon icon = GameLoad.imgMap.get(enemyType);
		this.setIcon(icon);
		HpType(enemyType);
		return this;
	}
	
	@Override
	public void move(int gametime) {
		
		if (this.getX()>x+200) {
			this.setX(this.getX()-1);
		}
		if (this.getX()<x) {
			this.setX(this.getX()+1);
		}
		if (this.getX()>10&&this.getX()<600) {
			this.setY(this.getY() + speed);
		}
		
	}
	
	private long filetime=0L;

	protected void add(int gameTime) {
		if(!this.pkType) {//如果是不发射状态 就直接return
			return;
		}
		if(gameTime-filetime>201) {
			filetime=gameTime;
		}
		if(gameTime-filetime==200) {
//			//		传递一个固定格式   {X:3,y:5,f:up} json格式
		ElementObj obj=GameLoad.getObj("enemyfire");  		
		ElementObj element = obj.createElement(this.toString());
//		System.out.println("子弹是否为空"+element);
//		装入到集合中
		ElementManager.getManager().addElement(element, GameElement.ENEMYFILE);
		}	
	}
	
//	
	@Override
	public String toString() {
		int x=this.getX();
		int y=this.getY()+getH();
		x+=(this.getW())/2;
		int w=10;
		int h=10;
		String fireType="";	//子弹类型
		switch(enemyType) {
		case "1enemy": fireType="1fireType"; break;
		case "2enemy": fireType="1fireType"; break;
		case "3enemy": fireType="2fireType"; break;
		case "4enemy": fireType="3fireType"; break;
		case "5enemy": fireType="4fireType"; break;
		case "6enemy": fireType="5fireType"; break;
		case "7enemy": fireType="5fireType"; break;
		}
		return "x:"+x+",y:"+y+",w:"+w+",h:"+h+",f:"+this.fx+",type:"+fireType;
	}
	
	public void HpType(String enemyType) {
		// TODO 自动生成的方法存根
		switch(enemyType) {
		case "1enemy": this.hp = 1; break;
		case "2enemy": this.hp = 4; break;
		case "3enemy": this.hp = 8; break;
		case "4enemy": this.hp = 16; break;
		case "5enemy": this.hp = 24; break;
		case "6enemy": this.hp = 32; break;
		case "7enemy": this.hp = 40; break;
		}
	}
	/**
	 * HP指代enemy类的HP
	 * @return
	 */
	public int getHP() {
		return hp;
	}
	public void setHP(int hP) {
		this.hp=hP;
		if(hp<=0) {
			hp=0;
			this.setLive(false);
		}	
	}

}
