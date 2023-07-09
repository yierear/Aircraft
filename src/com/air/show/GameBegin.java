package com.air.show;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GameBegin extends JPanel{
	public GameBegin() {
		// TODO 自动生成的构造函数存根
		init();
	}
	
	public void init() {
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO 自动生成的方法存根
		ImageIcon icon = new ImageIcon("image/background/8.png");
		g.drawImage(icon.getImage(), 0, 0, 600, 800, this);
	}
}
