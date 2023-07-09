package com.air.show;

import java.awt.Graphics;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import com.air.element.ElementObj;
import com.air.manager.ElementManager;
import com.air.manager.GameElement;

public class GameMainJPanel extends JPanel implements Runnable{
	private ElementManager em;
	
	public GameMainJPanel() {
		// TODO 自动生成的构造函数存根
		init();
	}
	
	public void init() {
		em = ElementManager.getManager();
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO 自动生成的方法存根
		super.paint(g);
		Map<GameElement, List<ElementObj>> all = em.getGameElements();
		for(GameElement ge:GameElement.values()) {
			List<ElementObj> list = all.get(ge);
			for (int i = 0; i < list.size(); i++) {
				ElementObj obj = list.get(i);
				obj.showElement(g);
			}
		}
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
