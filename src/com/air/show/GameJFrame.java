package com.air.show;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameJFrame extends JFrame{
	public static int GameX = 615;
	public static int GameY = 880;
	private JPanel aJPanel; //整个面板
	private JPanel mJPanel = new JPanel(); //用于显示的面板 
	private KeyListener keyListener = null;
	private MouseMotionListener mouseMotionListener = null; //鼠标监听
	private MouseListener mouseListener = null;
	private Thread thread = null; //游戏主线程
	private CardLayout cardLayout = new CardLayout();
	
	public GameJFrame() {
		// TODO 自动生成的构造函数存根
		init();
	}
	
	/**
	 * 面板的基本配置
	 */
	public void init() {
		this.setSize(GameX,GameY);
		this.setTitle("飞机大战");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * @说明 添加按钮
	 */
	public void addButton(JPanel jPanel, String str, int x, int y) {
		myButton jButton = new myButton(str);
		jButton.setBounds(x, y, 110, 50);	
		jButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				if(str.equals("开始游戏")) {
					cardLayout.show(mJPanel, "gmp");
				}else if (str.equals("选择关卡")) {
					cardLayout.show(mJPanel, "gl");
				}else if(str.equals("退出游戏")) {
					cardLayout.show(mJPanel, "gb");
				}
			}
		});
		jPanel.add(jButton);
	}
	
	/**
	 * 启动方法
	 */
	public void start() {
		if(aJPanel != null) {
			this.setContentPane(aJPanel);
		}
		if(keyListener != null) {
			this.addKeyListener(keyListener);
		}
		if(thread != null) {
			thread.start(); //启动线程
		}
		this.setVisible(true);
		
		if(this.mJPanel instanceof Runnable) {
//			如果jPanel是runnable的子类实体对象
			new Thread((Runnable)this.mJPanel).start();
		}
	}
	
	/** 
	 * @param jPanel
	 */
	public void setjPanel(JPanel jPanel) {
		aJPanel = jPanel; //为主面板赋值
		aJPanel.setLayout(null); //给全局面板添加布局
		this.setContentPane(aJPanel); //加入主面板
		mJPanel.setLayout(cardLayout); //显示面板布局
		aJPanel.add(mJPanel); // 加入显示面板
		mJPanel.setBounds(0, 50, GameX, GameY-50);
		mJPanel.setVisible(true);
				
		GameMainJPanel gmp = new GameMainJPanel();
		GameBegin gb = new GameBegin();
		GameLevel gl = new GameLevel();
		mJPanel.add(gmp,"gmp");
		mJPanel.add(gb,"gb");
		mJPanel.add(gl, "gl");
		cardLayout.show(mJPanel, "gb");
		
		addButton(aJPanel, "开始游戏",100,0);		
		addButton(aJPanel, "选择关卡",245,0);
		addButton(aJPanel, "退出游戏",390,0);
		
	}

	public void setKeyListener(KeyListener keyListener) {
		this.keyListener = keyListener;
	}

	public void setMouseMotionListener(MouseMotionListener mouseMotionListener) {
		this.mouseMotionListener = mouseMotionListener;
	}

	public void setMouseListener(MouseListener mouseListener) {
		this.mouseListener = mouseListener;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	} 
	
}
