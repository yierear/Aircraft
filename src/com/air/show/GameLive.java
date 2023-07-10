package com.air.show;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GameLive extends JPanel implements Runnable{

	public GameLive() {
		// TODO 自动生成的构造函数存根
		init();
	}
	
	public void init() {
		
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO 自动生成的方法存根
		super.paint(g);
		g.setColor(Color.red);
		g.drawRect(50, 10, 100, 10);
		g.fillRect(50, 10, Play.getHp(), HEIGHT);
	}
	
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		
	}

}
