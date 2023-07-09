package com.air.show;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class RoundBorder implements Border{
	public RoundBorder() {
		// TODO 自动生成的构造函数存根
//		setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(214,128,2)));
	}
	
	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		// TODO 自动生成的方法存根
		g.setColor(new Color(214,128,2));
		g.drawRoundRect(0, 0, c.getWidth()-1, c.getHeight()-1, 50, 50);
	}

	@Override
	public Insets getBorderInsets(Component c) {
		// TODO 自动生成的方法存根
		return new Insets(0, 0, 0, 0);
	}

	@Override
	public boolean isBorderOpaque() {
		// TODO 自动生成的方法存根
		return false;
	}
	
}
