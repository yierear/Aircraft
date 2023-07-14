package com.air.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.air.element.ElementObj;
import com.air.manager.ElementManager;
import com.air.manager.GameElement;

public class GameListener implements KeyListener{

	private ElementManager em=ElementManager.getManager();
	
	/*通过一个集合来记录所有按下的键，如果重复触发，就直接结束*/
	private Set<Integer> set=new HashSet<Integer>();
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
//		拿到玩家集合
//		System.out.println("按下"+e.getKeyCode());
		int key=e.getKeyCode();
		if (set.contains(key)) {//板顶集合中是否已经存在，包含这个对象
//			如果包含直接结束方法
			return;
		}
		set.add(key);
		List<ElementObj> play = em.getElementsByKey(GameElement.PLAY);
		for (ElementObj obj : play) {
			obj.keyClick(true,e.getKeyCode());
		}
	}

	/**松开*/
	@Override
	public void keyReleased(KeyEvent e) {
		if (!set.contains(e.getKeyCode())) {
			return;
		}
		set.remove(e.getKeyCode());//移除数据
		List<ElementObj> play = em.getElementsByKey(GameElement.PLAY);
		for (ElementObj obj : play) {
			obj.keyClick(false,e.getKeyCode());
		}
	}

}
