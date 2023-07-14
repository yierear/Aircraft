package com.air.element;

import java.awt.Graphics;
import java.util.Random;
import javax.swing.ImageIcon;
import com.air.controller.GameThread;
import com.air.manager.ElementManager;
import com.air.manager.GameElement;
import com.air.manager.GameLoad;

public class Play extends ElementObj{
	private static int HP = 100; //设一个全局传参
	private boolean left=false; //左
	private boolean up=false;   //上
	private boolean right=false;//右
	private boolean down=false; //下
	private String playType="1playType";//玩家飞机类型
	private int speed = 2;//速度
	private String effect = null;//增益类型
	public static boolean life = true;
	
	private static int playAndFire = 1;//传参决定玩家和子弹类型
	private int gapTime = 31;//发射子弹时间间隔
	
	//	变量专门用来记录当前主角面向的方向,默认为是up
	private String fx="up";
	private boolean pkType=true;//攻击状态 true 攻击  false停止
	
	public Play() {}
	public Play(int x, int y, int w, int h, int hp, ImageIcon icon) {
	}

	@Override
	public ElementObj createElement(String str) {	
		this.setHp(100);
		this.life = true;
		String[] split = str.split(",");
		this.setX(Integer.parseInt(split[0]));
		this.setY(Integer.parseInt(split[1]));
		
		ImageIcon icon2 = GameLoad.imgMap.get(split[2]);
		if(icon2 == null) {
			icon2 = GameLoad.imgMap.get("up");
		}
		this.setH(100);
		this.setW(70);
		this.setIcon(icon2);
		return this;
	}
	
	
	/**
	 * 面向对象中第1个思想： 对象自己的事情自己做
	 */
	@Override
	public void showElement(Graphics g) {
		EffectType();
		ImageIcon icon2 = GameLoad.imgMap.get(playType);
		this.setIcon(icon2);
//		绘画图片
		g.drawImage(this.getIcon().getImage(), 
				this.getX(), this.getY(), 
				this.getW(), this.getH(), null);
	}

	@Override  
	public void keyClick(boolean bl,int key) { //只有按下或者鬆開才會 调用这个方法
//		System.out.println("测试："+key);
		Random random = new Random();
		int ran=random.nextInt(5)+1;
		Play.setPlayAndFire(ran);
		if(bl) {//按下
			switch(key) {  
			case 37: 
				this.down=false;this.up=false;
				this.right=false;this.left=true; this.fx="left"; break;
			case 38: 
				this.right=false;this.left=false;
				this.down=false; this.up=true;   this.fx="up"; break;
			case 39: 
				this.down=false;this.up=false;
				this.left=false; this.right=true; this.fx="right";break;
			case 40: 
				this.right=false;this.left=false;
				this.up=false; this.down=true;  this.fx="down";break;
			case 32:
				this.playType=ran+"playType";
				PlayFile.changeFire();
				break;//换装
			}
		}else {
			switch(key) {
			case 37: this.left=false;  break;
			case 38: this.up=false;    break;
			case 39: this.right=false; break;
			case 40: this.down=false;  break;
			}
		}	
	}
	
	@Override
	protected void move(int gameTime) {
		if (this.left && this.getX()>0) {
			this.setX(this.getX() - speed);
		}
		if (this.up  && this.getY()>0) {
			this.setY(this.getY() - speed);
		}
		if (this.right && this.getX()<615-this.getW()) {  //坐标的跳转由大家来完成
			this.setX(this.getX() + speed);
		}
		if (this.down && this.getY()<830-this.getH()) {
			this.setY(this.getY() + speed);
		}
	}
	
	@Override
	protected void updateImage() {
		this.setIcon(GameLoad.imgMap.get(fx));
	}
	
	private long filetime=0L;
			
	
	@Override
	protected void add(int gameTime) {
		if(!this.pkType) {//如果是不发射状态 就直接return
			return;
		}
		if(gameTime-filetime>gapTime) {
			filetime=gameTime;
		}
		if(gameTime-filetime==gapTime-1) {
			//		传递一个固定格式   {X:3,y:5,f:up} json格式
		ElementObj obj=GameLoad.getObj("file");  		
		ElementObj element = obj.createElement(this.toString());
//		System.out.println("子弹是否为空"+element);
//		装入到集合中
		ElementManager.getManager().addElement(element, GameElement.PLAYFILE);
		}
		else {
			return;
		}
		checkSpeed(gameTime);
		checkAttack(gameTime);
	}
	
	@Override
	public String toString() {
		//  {X:3,y:5,f:up,t:A} json格式
		int x=this.getX();
		int y=this.getY();
		switch(this.fx) { // 子弹在发射时候就已经给予固定的轨迹。可以加上目标，修改json格式
		case "up": x+=(this.getIcon().getIconWidth())/2;break;  
		// 一般不会写20等数值，一般情况下 图片大小就是显示大小；一般情况下可以使用图片大小参与运算
		case "left":x+=(this.getIcon().getIconWidth())/2;break;
		case "right": x+=(this.getIcon().getIconWidth())/2; break;
		case "down": x+=(this.getIcon().getIconWidth())/2; break;
		}
		return "x:"+x+",y:"+y+",f:"+this.fx;
	}
	
	/**
	 * 判断道具类型实现相应效果
	 */
	public void EffectType() {
		this.setEffect(GameThread.getPropType());
//		System.out.println(effect);
		if(effect!=null) {
			switch(effect) {
			case "1prop": this.setHp(this.getHp()+10); GameThread.setPropType(null); break;
			case "2prop": this.setHp(this.getHp()-20); if(this.getHp()==0) this.setLive(false);
					GameThread.setPropType(null); break;
			case "3prop": this.setSpeed(4); GameThread.setPropType(null); break;
			case "4prop": this.setGapTime(12); GameThread.setPropType(null); break;
			}
		}
	}
	
	private int speedTime = 0;
	//一定时间后恢复初始速度
	private void checkSpeed(int gameTime) {	
			if(gameTime-speedTime>600) {
			speedTime=gameTime;
		}
			if(gameTime-speedTime>550) {
				this.setSpeed(2);
			}		
	}
	
	private int attackTime = 0;
	//一定时间后恢复子弹发射速度
	private void checkAttack(int gameTime) {
		if(gameTime-attackTime>600) {
			attackTime=gameTime;
	}
		if(gameTime-attackTime>550) {
			this.setGapTime(31);
		}		
}
	
	/**
	 * @说明 进行重写 在给对象hp赋值时，同时给类的HP赋值
	 */
	@Override
	public void setHp(int hp) {
		// TODO 自动生成的方法存根
		Play.setHP(hp);
		super.setHp(hp);
//		if(hp==0)
//			this.setLive(false);
	}
	
	/**
	 * HP指代play类的HP
	 * @return
	 */
	public static int getHP() {
		return HP;
	}
	public static void setHP(int hP) {
		if(hP<=100&&hP>0)
			HP = hP;
		else if(hP>100)
			HP = 100;
		else if(hP<=0) 
			HP = 0;			
	}
	
	
	
	public String getEffect() {
		return effect;
	}
	public void setEffect(String effect) {
		this.effect = effect;
	}
	
	public static int getPlayAndFire() {
		return playAndFire;
	}
	public static void setPlayAndFire(int playAndFire) {
		Play.playAndFire = playAndFire;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public void setGapTime(int gapTime) {
		this.gapTime = gapTime;
	}
	
	@Override
	public void setLive(boolean live) {
		// TODO 自动生成的方法存根
		if(live==false)
			life = false;
		super.setLive(live);
	}
	
}