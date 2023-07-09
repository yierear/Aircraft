package com.air.show;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Shape;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;

public class myButton extends JButton{
	private Shape shape = null;
	private Color quit = new Color(253, 209, 73);// 离开时颜色
	
	public myButton(String s) {
		super(s);
		setBorder(BorderFactory.createRaisedBevelBorder());//凸起效果
        setMargin(new Insets(0,0,0,0));//去除文字与按钮的边沿
        setBorder(new RoundBorder());//圆角矩形边界
        setContentAreaFilled(false);//取消原先画矩形的设置
        //setBorderPainted(false);//会导致按钮没有明显边界
        setFocusPainted(false);//去除文字周围的虚线框
	}
	
	public void paintComponent(Graphics g) {
		if (getModel().isArmed()) {
            g.setColor(new Color(196,98,0));//按下后按钮变成橙色
			
        } else {
            g.setColor(quit);
        }
        g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1,50,50);//填充圆角矩形边界
        // 这个调用会画一个标签和焦点矩形。
        super.paintComponent(g);
	}
}
