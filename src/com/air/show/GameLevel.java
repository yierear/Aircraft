package com.air.show;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.air.element.Play;


public class GameLevel extends JPanel{
	private static int Level = 1;
	private int primary = 0; //leve初始值
	public static boolean flag = false; //true则变了
	private static final int BUTTON_WIDTH = 180;
	private static final int BUTTON_HEIGHT = 100;
	
	public GameLevel() {
		// TODO 自动生成的构造函数存根
		init();
	}
	
	public void init() {
		 this.setLayout(null);		 
		 addButton(this, "1", 75, 150);
		 addButton(this, "2", 360, 150);
		 addButton(this, "3", 75, 350);
		 addButton(this, "4", 360, 350);
		 addButton(this, "5", 75, 550);
		 addButton(this, "6", 360, 550);
	}
	
	/**
	 * @说明 增加按钮
	 * @param jPanel 面板
	 * @param str 按钮文字
	 * @param x 按钮横坐标
	 * @param y 按钮纵坐标
	 */
	public void addButton(JPanel jPanel, String str, int x, int y) {
		myButton jButton = new myButton(str);
		jButton.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
		jButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				primary = getLevel();
				switch(str){
				case "1": Level = 1; Play.setHp(100); break;
				case "2": Level = 2; Play.setHp(100); break;
				case "3": Level = 3; Play.setHp(100); break;
				case "4": Level = 4; Play.setHp(100); break;
				case "5": Level = 5; Play.setHp(100); break;
				case "6": Level = 6; Play.setHp(100); break;
				}
//				System.out.println(Level);
				if(primary != getLevel()) {
					flag = true;
				}else {
					flag = false;
				}
			}
		});		
		jPanel.add(jButton);
	}
		
	/**
	 * @说明 对全局变量 游戏关卡的设置
	 * @return
	 */
	
	public static int getLevel() {
		return Level;
	}

	public static void setLevel(int level) {
		Level = level;
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO 自动生成的方法存根
		ImageIcon icon = new ImageIcon("image/level.png");
		g.drawImage(icon.getImage(), 0, 0, 600, 800, this);
	}
}
