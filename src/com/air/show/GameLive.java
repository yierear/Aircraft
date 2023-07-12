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
	}
	
	public void init() {
		this.setLayout(null);
		//关卡的显示
		JLabel jLabel = new JLabel("关卡:");
		jLabel.setBounds(0, 10, 30, 15);
		Font font = new Font("Broadway", Font.BOLD, 35);
		String str = GameLevel.getLevel()+"";
//		System.out.println(str);
 		JLabel jLabel0 = new JLabel(str);
 		jLabel0.setForeground(Color.ORANGE);
 		jLabel0.setFont(font);
 		jLabel0.setBounds(50, 0, 100, 40);
		this.add(jLabel);
		this.add(jLabel0);
		
		//血条的设置
		Font font1 = new Font("Broadway", Font.ITALIC, 15);
		JLabel jLabel1 = new JLabel("hp:");
		jLabel1.setBounds(0, 40, 20, 15);
		String str1 = Play.getHP()+"";
		JLabel jLabel2 = new JLabel(str1);
		jLabel2.setFont(font1);
		jLabel2.setForeground(Color.RED);
		jLabel2.setBounds(125, 40, 35, 15);
		this.add(jLabel1);
		this.add(jLabel2);
		
		//得分的设置
		Font font2 = new Font("Broadway", Font.BOLD, 35);
		JLabel jLabel3 = new JLabel("得分:");
		jLabel3.setBounds(0, 75, 30, 15);
		String str2 = GameThread.getScore()+"";
//		System.out.println(str2);
		JLabel jLabel4 = new JLabel(str2);
		jLabel4.setFont(font2);
		jLabel4.setForeground(Color.BLUE);
		jLabel4.setBounds(50, 60, 100, 40);
		this.add(jLabel3);
		this.add(jLabel4);
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO 自动生成的方法存根
		init();
		super.paint(g);
		g.setColor(Color.red);
		g.drawRect(25, 40, 100, 15);
		g.fillRect(25, 40, Play.getHP(), 15);
	}
	
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		while(true) {
			this.removeAll();
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
