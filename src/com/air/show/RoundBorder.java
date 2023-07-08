package com.air.show;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

public class RoundBorder implements Border{
	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		// TODO 自动生成的方法存根
		g.setColor(Color.BLACK);
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
