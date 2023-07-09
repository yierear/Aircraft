package com.air.game;

import com.air.controller.GameListener;
import com.air.controller.GameThread;
import com.air.show.GameJFrame;
import com.air.show.GameMainJPanel;

public class GameStart {
	/**
	 * 程序唯一入口
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		
		GameJFrame gj = new GameJFrame();
		//实例化面板，注入到JFrame中
//		实例化监听
		GameListener listener = new GameListener();
//		实例化游戏主线程
		GameThread th = new GameThread();
	
//		注入
		gj.setKeyListener(listener);
		gj.setThread(th);
		gj.start();

	}

}
