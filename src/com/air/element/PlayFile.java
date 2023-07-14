package com.air.element;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.air.manager.ElementManager;
import com.air.manager.GameElement;
import com.air.manager.GameLoad;
import com.air.show.GameLevel;

/**
 * @说明 玩家子弹类，本类的实体对象是由玩家对象调用和创建
 * @author renjj
 * @子类的开发步骤
 *   1.继承与元素基类;重写show方法
 *   2.按照需求选择性重写其他方法例如：move等
 *   3.思考并定义子类特有的属性
 */
public class PlayFile extends ElementObj{
	private int attack;//攻击力
	private int moveNum=3;//移动速度值
	private String fx;
	private static String fireType = "1fireType";
	private int ATK = 1;
//	剩下的大家扩展; 可以扩展出多种子弹： 激光，导弹等等。(玩家类就需要有子弹类型)
	public PlayFile() {}//一个空的构造方法
//	对创建这个对象的过程进行封装，外界只需要传输必要的约定参数，返回值就是对象实体
//	private int level = GameLevel.getLevel();//获取关卡
	
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
//			case "type":
//				this.fireType=split[1]; 
//				ImageIcon icon1 = GameLoad.imgMap.get(split[2]);
//				if(icon1 == null) {
//					icon1 = GameLoad.imgMap.get("1fireType");
//				}
//				this.setIcon(icon1); break;
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
		this.setATK(fireType);
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
	
	public void setATK(String fireType) {
		// TODO 自动生成的方法存根
		switch(fireType) {
		case "1fireType": this.ATK = 1; break;
		case "2fireType": this.ATK = 2; break;
		case "3fireType": this.ATK = 3; break;
		case "4fireType": this.ATK = 4; break;
		case "5fireType": this.ATK = 5; break;
		}
	}
	public int getATK() {
		return ATK;
	}
}





