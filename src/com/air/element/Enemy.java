package com.air.element;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.ImageIcon;

import com.air.manager.ElementManager;
import com.air.manager.GameElement;
import com.air.manager.GameLoad;

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
		this.setHp(3);
		enemyType=split[4];
		ImageIcon icon = GameLoad.imgMap.get(enemyType);
		this.setIcon(icon);
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
	
//	@Override
//	protected void updateImage() {
////		ImageIcon icon=GameLoad.imgMap.get(fx);
////		System.out.println(icon.getIconHeight());//得到图片的高度
////		如果高度是小于等于0 就说明你的这个图片路径有问题
//		this.setIcon(GameLoad.imgMap.get(fx));
//	}
	
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
	
//	
	@Override
	public String toString() {
		int x=this.getX();
		int y=this.getY()+this.getIcon().getIconHeight();
		x+=(this.getIcon().getIconWidth())/2;
		return "x:"+x+",y:"+y+",f:"+this.fx+",type:"+enemyType;
	}
	
}
