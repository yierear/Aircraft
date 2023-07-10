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
	private String fx="down";
	private boolean pkType=true;
	
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
		this.setW(Integer.parseInt(split[2]));	
		this.setH(Integer.parseInt(split[3]));
		ImageIcon icon = GameLoad.imgMap.get(split[4]);
		this.setIcon(icon);
		return this;
	}
	
	@Override
	public void move(int gametime) {
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
////			filetime=gameTime;
////		}
////		if(gameTime-filetime==20) {
//			//		传递一个固定格式   {X:3,y:5,f:up} json格式
//		this.pkType=false;
//		ElementObj obj=GameLoad.getObj("file");  		
//		ElementObj element = obj.createElement(this.toString());
////		System.out.println("子弹是否为空"+element);
////		装入到集合中
//		ElementManager.getManager().addElement(element, GameElement.PLAYFILE);
////		如果要控制子弹速度等等。。。。还需要代码编写
////		}
////		else {
////			return;
////		}
////		this.pkType=false;//按一次，发射一个子弹。拼手速(也可以增加变量来控制)
////		new PlayFile(); // 构造一个类 需要做比较多的工作  可以选择一种方式，使用小工厂
////		将构造对象的多个步骤进行封装成为一个方法，返回值直接是这个对象	
//	}
//	}
////	
//	@Override
//	public String toString() {
//		int x=this.getX();
//		int y=this.getY();
//		switch(this.fx) { // 子弹在发射时候就已经给予固定的轨迹。可以加上目标，修改json格式
//		case "up": x+=(this.getIcon().getIconWidth())/2;break;  
//		// 一般不会写20等数值，一般情况下 图片大小就是显示大小；一般情况下可以使用图片大小参与运算
//		case "left":x+=(this.getIcon().getIconWidth())/2;break;
//		case "right": x+=(this.getIcon().getIconWidth())/2; break;
//		case "down": x+=(this.getIcon().getIconWidth())/2; break;
//		}//个人认为： 玩游戏有助于 理解面向对象思想;不能专门玩，需要思考，父类应该怎么抽象，子类应该怎么实现
////		学习技术不犯法，但是不要用技术做犯法的事.
//		return "x:"+x+",y:"+y+",f:"+this.fx+",type:fireType2";
//	}
//	
	
}
