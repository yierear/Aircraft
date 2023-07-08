package com.air.element;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 * @说明 所有元素的基类
 * @author 13922
 *
 */
public abstract class ElementObj {
	private int x;
	private int y;
	private int w;
	private int h;
	private ImageIcon icon;
//	还有各种必要的状态值（生存）
	private boolean live = true; //生存状态 true--存活 false--死亡
						  //可以采用枚举值来定义状态【状态之间不能冲突】
//	注明：当重新定义一个判定状态的变量，需要思考：1.初始化 2.值的改变 3.值的判定
	public ElementObj() {  //这个构造无作用，只是为了继承不报错
		// TODO 自动生成的构造函数存根
	}
	
	/**
	 * @说明 带参数的构造方法；可以由子类传输数据到父类
	 * @param x 左上角x坐标
	 * @param y 左上角y坐标
	 * @param w w宽度
	 * @param h h高度
	 * @param icon 图片
	 */
	public ElementObj(int x, int y, int w, int h, ImageIcon icon) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.icon = icon;
	}
	
	/**
	 * @说明 抽象方法，显示元素
	 * @param g 画笔用于绘画
	 */
	public abstract void showElement(Graphics g);

	
	/**
	 * @说明 使用父类定义接收键盘事件的方法
	 * 		 只有需要实现键盘监听的子类，重写这个方法（约定）
	 * @说明 方法2 使用接口的方式需要在监听类进行类型转换
	 * @param bl 点击的类型 true 代表按下 false 代表松开
	 * @param key 代表触发的键盘的code值
	 */	
	public void keyClick(boolean bl,int key) { //不是强制必须重写的

	}
	/**
	 * @说明 移动方法；需要移动的子类，请重写这个方法
	 */
	protected void move(int gameTime) {	
	}
	
	/**
	 * @设计模式 移动方法；需要移动的子类 请重写这个方法
	 * 			 1.移动 2.换装 3.子弹发射
	 */
	public final void model(int gameTime) {
//		先换装
		updateImage();
//		再移动
		move(gameTime);
//		再发射子弹
		add(gameTime);
	}
	
	protected void updateImage() {}
	
	protected void add(int gameTime) {}

//	死亡方法 给子类继承的
	public void die() {
		
	}
	
	public ElementObj createElement(String str) {
		return null;
	}
	
	/**
	 * @说明 本方法返回 元素的碰撞矩形对象（实时返回）
	 * @return
	 */
	public Rectangle getRectangle() {
//		可以将这个数据进行处理，如擦过的效果
		return new Rectangle(x,y,w,h);
	}
	
	/**
	 * @说明 碰撞方法
	 * 一个是this对象 一个是传入值obj
	 * @param boolean 返回true有碰撞 false则没有
	 */
	public boolean pk(ElementObj obj){
		return this.getRectangle().intersects(obj.getRectangle());
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}
	

	
}
