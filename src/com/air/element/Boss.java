package com.air.element;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import com.air.manager.GameLoad;
import com.air.show.GameLevel;
import com.air.controller.GameThread;
import com.air.manager.ElementManager;
import com.air.manager.GameElement;

public class Boss extends ElementObj{
	private int speed = 1;
	private String fx="down";
	private boolean pkType=true;
	private int hp;
	private String bossType;
	private int fireType;
	
	@Override
	public void showElement(Graphics g) {
		// TODO 自动生成的方法存根
		g.drawImage(this.getIcon().getImage(), 
				this.getX(), this.getY(), 
				this.getW(), this.getH(), null);
		if (this.getY()+this.getH()>=0) {
			g.drawString("BOSS:", 0, 10);
			g.setColor(Color.RED);
			g.drawRect(50, 0, 50*GameLevel.getLevel(), 15);
			g.fillRect(50, 0, this.getHp(), 15);
			g.drawString(this.getHp()+"", 0, 30);
		}
		
	}
	@Override
	public ElementObj createElement(String str) {
		String[] split = str.split(",");
		this.setX(Integer.parseInt(split[0]));
		this.setY(Integer.parseInt(split[1]));
		this.setW(Integer.parseInt(split[2]));	
		this.setH(Integer.parseInt(split[3]));
		bossType=split[4];
		this.setHp(80);//设置boss血量和子弹类型
		ImageIcon icon = GameLoad.imgMap.get(bossType);
		this.setIcon(icon);
		HpType(bossType);
		return this;
	}
	
	@Override
	public void move(int gametime) {
		
		if (gametime%5==0 && this.getY()<0) {//控制移动速度
			this.setY(this.getY()+1);
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
//		如果要控制子弹速度等等。。。。还需要代码编写
//		}
//		else {
//			return;
		}
//		this.pkType=false;//按一次，发射一个子弹。拼手速(也可以增加变量来控制)
//		new PlayFile(); // 构造一个类 需要做比较多的工作  可以选择一种方式，使用小工厂
//		将构造对象的多个步骤进行封装成为一个方法，返回值直接是这个对象	
	}
	
	@Override
	public String toString() {
		int x=this.getX();
		int y=this.getY()+getH();
		int w=50;
		int h=50;
		x+=(this.getW())/2;
		String fireType="";	//子弹类型
		String type="boss";//敌机类型
		switch(bossType) {
		case "1boss": fireType="1fireType"; break;
		case "2boss": fireType="1fireType"; break;
		case "3boss": fireType="2fireType"; break;
		case "4boss": fireType="3fireType"; break;
		case "5boss": fireType="4fireType"; break;
		case "6boss": fireType="5fireType"; break;
		}
		return "x:"+x+",y:"+y+",w:"+w+",h:"+h+",f:"+this.fx+",firetype:"+fireType+",type:"+type;
	}
	
	public void HpType(String bossType) {
		// TODO 自动生成的方法存根
		switch(bossType) {
		case "1boss": this.hp = 50; break;
		case "2boss": this.hp = 100; break;
		case "3boss": this.hp = 150; break;
		case "4boss": this.hp = 200; break;
		case "5boss": this.hp = 250; break;
		case "6boss": this.hp = 300; break;
		}
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
		if(hp<=0) {
			hp=0;
			this.setLive(false);
		}
	}

}
