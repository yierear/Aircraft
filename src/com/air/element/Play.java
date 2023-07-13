package com.air.element;

import java.awt.Graphics;
import java.util.Random;
import javax.swing.ImageIcon;
import com.air.controller.GameThread;
import com.air.manager.ElementManager;
import com.air.manager.GameElement;
import com.air.manager.GameLoad;

public class Play extends ElementObj{
	/**
	 * 移动属性:
	 * 1.单属性  配合  方向枚举类型使用; 一次只能移动一个方向 
	 * 2.双属性  上下 和 左右    配合boolean值使用 例如： true代表上 false 为下 不动？？
	 *                      需要另外一个变来确定是否按下方向键
	 *                约定    0 代表不动   1代表上   2代表下  
	 * 3.4属性  上下左右都可以  boolean配合使用  true 代表移动 false 不移动
	 * 						同时按上和下 怎么办？后按的会重置先按的
	 * 说明：以上3种方式 是代码编写和判定方式 不一样
	 * 说明：游戏中非常多的判定，建议灵活使用 判定属性；很多状态值也使用判定属性
	 *      多状态 可以使用map<泛型,boolean>;set<判定对象> 判定对象中有时间
	 *      
	 * @问题 1.图片要读取到内存中： 加载器  临时处理方式，手动编写存储到内存中     
	 *       2.什么时候进行修改图片(因为图片是在父类中的属性存储)
	 *       3.图片应该使用什么集合进行存储
	 */
	private static int HP = 100; //设一个全局传参
	private boolean left=false; //左
	private boolean up=false;   //上
	private boolean right=false;//右
	private boolean down=false; //下
	private String playType="1playType";//玩家飞机类型
	private int speed = 2;//速度
	private String effect = null;//增益类型
	
	private static int playAndFire = 1;//传参决定玩家和子弹类型
	
	//	变量专门用来记录当前主角面向的方向,默认为是up
	private String fx="up";
	private boolean pkType=true;//攻击状态 true 攻击  false停止
	
	public Play() {}
	public Play(int x, int y, int w, int h, int hp, ImageIcon icon) {
	}
	//题外话: 过时的方法能用吗？ 可以用，也能够用，因为你不用jdk底层使用
	@Override
	public ElementObj createElement(String str) {	
		this.setHp(100);
		String[] split = str.split(",");
		this.setX(Integer.parseInt(split[0]));
		this.setY(Integer.parseInt(split[1]));
//		System.out.println("------");
//		for (int j = 0; j < split.length; j++) {
//			System.out.println(split[j]);
//		}
		
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
	/*
	 * @说明 重写方法： 重写的要求：方法名称 和参数类型序列 必须和父类的方法一样
	 * @重点 监听的数据需要改变状态值
	 */
	@Override   // 注解 通过反射机制，为类或者方法或者属性 添加的注释(相当于身份证判定)
	public void keyClick(boolean bl,int key) { //只有按下或者鬆開才會 调用这个方法
//		System.out.println("测试："+key);
		Random random = new Random();
		int ran=random.nextInt(5)+1;
		Play.setPlayAndFire(ran);
		if(bl) {//按下
			switch(key) {  //怎么优化 大家中午思考;加 监听会持续触发；有没办法触发一次
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
//			case 32: this.pkType=false; break;//关闭攻击状态
			}
		//a a
		}	
	}
	
	
//	@Override
//	public int compareTo(Play o) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
	
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
//		ImageIcon icon=GameLoad.imgMap.get(fx);
//		System.out.println(icon.getIconHeight());//得到图片的高度
//		如果高度是小于等于0 就说明你的这个图片路径有问题
		this.setIcon(GameLoad.imgMap.get(fx));
	}
	/**
	 * @额外问题：1.请问重写的方法的访问修饰符是否可以修改？
	 *           2.请问下面的add方法是否可以自动抛出异常?
	 * @重写规则：1.重写方法的方法名称和返回值 必须和父类的一样
	 * 			  2.重写的方法的传入参数类型序列，必须和父类的一样
	 *            3.重写的方法访问修饰符 只能 比父类的更加宽泛。
	 *               比方说：父类的方法是受保护的，但是现在需要在非子类调用
	 *                      可以直接子类继承，重写并super.父类方法。public方法
	 *            4.重写的方法抛出的异常 不可以比父类更加宽泛
	 * 子弹的添加 需要的是 发射者的坐标位置，发射者的方向  如果你可以变换子弹(思考，怎么处理？)
	 */
	private long filetime=0L;
//	filefime 和传入的时间 gameTime 进行比较，赋值等操作运算，控制子弹间隔
//	这个控制代码 自己写
			
	
	@Override
	protected void add(int gameTime) {
		if(!this.pkType) {//如果是不发射状态 就直接return
			return;
		}
		if(gameTime-filetime>21) {
			filetime=gameTime;
		}
		if(gameTime-filetime==20) {
			//		传递一个固定格式   {X:3,y:5,f:up} json格式
		ElementObj obj=GameLoad.getObj("file");  		
		ElementObj element = obj.createElement(this.toString());
//		System.out.println("子弹是否为空"+element);
//		装入到集合中
		ElementManager.getManager().addElement(element, GameElement.PLAYFILE);
//		如果要控制子弹速度等等。。。。还需要代码编写
		}
		else {
			return;
		}
//		this.pkType=false;//按一次，发射一个子弹。拼手速(也可以增加变量来控制)
//		new PlayFile(); // 构造一个类 需要做比较多的工作  可以选择一种方式，使用小工厂
//		将构造对象的多个步骤进行封装成为一个方法，返回值直接是这个对象	
	}
	
	@Override
	public String toString() {// 这里是偷懒，直接使用toString；建议自己定义一个方法
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
			case "1prop": this.setHp(this.getHp()+5); GameThread.setPropType(null); break;
			case "2prop": this.setHp(this.getHp()+10); GameThread.setPropType(null); break;
			case "3prop": this.setHp(this.getHp()-20); GameThread.setPropType(null); break;
			case "5prop": this.setSpeed(3); GameThread.setPropType(null); break;
			}
//			System.out.println(this.getHp());
//			System.out.println(Play.getHP());
//			System.out.println("----------");
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
	
}