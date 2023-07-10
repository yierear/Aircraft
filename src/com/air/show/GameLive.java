package com.air.show;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.air.controller.GameThread;
import com.air.element.Play;

public class GameLive extends JPanel implements Runnable{

	public GameLive() {
		// TODO 自动生成的构造函数存根
		init();
	}
	
	public void init() {
		//血条的设置
		Font font1 = new Font("Broadway", Font.ITALIC, 10);
		this.setLayout(null);
		JLabel jLabel1 = new JLabel("hp:");
		jLabel1.setBounds(0, 10, 20, 15);
		String str1 = Play.getHp()+"";
		JLabel jLabel2 = new JLabel(str1);
		jLabel2.setFont(font1);
		jLabel2.setForeground(Color.RED);
		jLabel2.setBounds(135, 10, 25, 15);
		this.add(jLabel1);
		this.add(jLabel2);
		
		//得分的设置
		Font font2 = new Font("Broadway", Font.BOLD, 35);
		JLabel jLabel3 = new JLabel("得分:");
		jLabel3.setBounds(0, 55, 30, 15);
		String str2 = GameThread.getScore()+"";
		JLabel jLabel4 = new JLabel(str2);
		jLabel4.setFont(font2);
		jLabel4.setForeground(Color.BLUE);
		jLabel4.setBounds(50, 20, 100, 80);
		this.add(jLabel3);
		this.add(jLabel4);
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO 自动生成的方法存根
		super.paint(g);
		g.setColor(Color.red);
		g.drawRect(30, 10, 100, 15);
		g.fillRect(30, 10, Play.getHp(), 15);
	}
	
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		while(true) {
			this.repaint();
			try {
				Thread.sleep(50);
			}catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

}
