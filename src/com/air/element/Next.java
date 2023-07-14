package com.air.element;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import com.air.show.GameJFrame;

public class Next extends ElementObj{
	private String type = "0"; //0代表中间关卡 1代表最终关
	
	@Override
	public void showElement(Graphics g) {
		// TODO 自动生成的方法存根
		g.drawImage(this.getIcon().getImage(),
				this.getX(), this.getY(), 
				this.getW(), this.getH(), null);
	}
	
	@Override
	public ElementObj createElement(String str) {
		// TODO 自动生成的方法存根
		this.setX(0);
		this.setY(0);
		this.setW(GameJFrame.GameX-200);
		this.setH(GameJFrame.GameY);
		this.type = str;
		if(type.equals("0")) //中间页
			this.setIcon(new ImageIcon("image/next.png"));
		else if(type.equals("1")) //结束页
			this.setIcon(new ImageIcon("image/succeed.png"));
		else { //失败页
			this.setIcon(new ImageIcon("image/fail.png"));
		}
		return this;
	}
}
