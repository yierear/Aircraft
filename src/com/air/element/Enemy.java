package com.air.element;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.ImageIcon;

import com.air.element.ElementObj;
import com.air.manager.ElementManager;
import com.air.manager.GameElement;
import com.air.manager.GameLoad;

public class Enemy extends ElementObj{
	private int speed = 2;
//	private String fx="up";
//	private boolean pkType=true;
	
	@Override
	public void showElement(Graphics g) {
		g.drawImage(this.getIcon().getImage(), 
				this.getX(), this.getY(), 
				this.getW(), this.getH(), null);
	}
	@Override
	public ElementObj createElement(String str) {
		String[] split = str.split(",");
		this.setX(new Integer(split[0]));
		this.setY(new Integer(split[1]));
		ImageIcon icon3=GameLoad.imgMap.get(split[2]);
		this.setW(icon3.getIconWidth());
		this.setH(icon3.getIconHeight());
		this.setIcon(icon3);
		return this;
	}
	
	public void move() {
		this.setY(this.getY() + 1);
	}
	
//	@Override
//	protected void updateImage() {
////		ImageIcon icon=GameLoad.imgMap.get(fx);
////		System.out.println(icon.getIconHeight());//得到图片的高度
////		如果高度是小于等于0 就说明你的这个图片路径有问题
//		this.setIcon(GameLoad.imgMap.get(fx));
//	}
	
//	private long filetime=0L;
//	protected void add(int gameTime) {
//		if(!this.pkType) {//如果是不发射状态 就直接return
//			return;
//		}
//		if(gameTime-filetime>21) {
//			filetime=gameTime;
//		}
//		if(gameTime-filetime==20) {
//			//		传递一个固定格式   {X:3,y:5,f:up} json格式
//		ElementObj obj=GameLoad.getObj("file");  		
//		ElementObj element = obj.createElement(this.toString());
////		System.out.println("子弹是否为空"+element);
////		装入到集合中
//		ElementManager.getManager().addElement(element, GameElement.PLAYFILE);
////		如果要控制子弹速度等等。。。。还需要代码编写
//		}
//		else {
//			return;
//		}
////		this.pkType=false;//按一次，发射一个子弹。拼手速(也可以增加变量来控制)
////		new PlayFile(); // 构造一个类 需要做比较多的工作  可以选择一种方式，使用小工厂
////		将构造对象的多个步骤进行封装成为一个方法，返回值直接是这个对象	
//	}
//	
//	@Override
//	public String toString() {
//		int y=this.getY();
//		y+=(this.getIcon().getIconWidth())/2;
//		return ",y:"+y+",f:"+this.fx;
//	}
	
	
}
