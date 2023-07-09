package com.air.show;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class GameLevel extends JPanel{
	public GameLevel() {
		// TODO 自动生成的构造函数存根
		init();
	}
	
	public void init() {
		 this.setLayout(getLayout());
	}
	
	public void addButton(JPanel jPanel, String str, int x, int y) {
		myButton jButton = new myButton(str);
		jButton.setBounds(x, y, 110, 50);	
		jButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				
			}
		});
		jPanel.add(jButton);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO 自动生成的方法存根
		ImageIcon icon = new ImageIcon("image/background/1.png");
		g.drawImage(icon.getImage(), 0, 0, 600, 800, this);
	}
}
