package com.air.element;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.air.manager.GameLoad;

/**
 * @说明 敌人子弹类
 * @author 墨洋
 *
 */
public class EnemyFire extends ElementObj{
	private int attack;//攻击力
	private int moveNum=3;//移动速度值
	private String fx;
	private String fireType;
//	剩下的大家扩展; 可以扩展出多种子弹： 激光，导弹等等。(玩家类就需要有子弹类型)
	public EnemyFire() {}//一个空的构造方法
//	对创建这个对象的过程进行封装，外界只需要传输必要的约定参数，返回值就是对象实体
	
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
//			case "type":
//				this.fireType=split2[1]; 
//				ImageIcon icon1 = GameLoad.imgMap.get(split2[1]);
////				if(icon1 == null) {
////					icon1 = GameLoad.imgMap.get("1fireType");
////				}
//				this.setIcon(icon1); break;
			}
		}
		this.setW(10);
		this.setH(10);
		return this;
	}
	@Override
	public void showElement(Graphics g) {
		ImageIcon icon1 = GameLoad.imgMap.get("3fireType");
		this.setIcon(icon1);
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
}
