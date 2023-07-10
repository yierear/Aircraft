package com.air.show;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * @说明 游戏窗体 主要实现的功能：关闭、显示、最大最小化
 * @author 13922
 * @功能说明：
 * 		嵌入面板
 * 		启动主线程......
 * @窗体说明 窗体大小（记录样式）swing awt
 * 
 * @分析 1.面板绑定到窗体
 * 		 2. 监听绑定
 * 		 3.游戏主线程启动
 * 		 4.显示窗体
 */
public class GameJFrame extends JFrame{
	public static int GameX = 800;
	public static int GameY = 838;
	private JPanel ajPanel = new JPanel();
	private JPanel jPanel = new JPanel(); //正在进行显示的面板
	private GameLive ljPanel = new GameLive(); //血条 
	private KeyListener keyListener = null;
	private MouseMotionListener mouseMotionListener = null; //鼠标监听
	private MouseListener mouseListener = null;
	private Thread thread = null; //游戏主线程
	public CardLayout cardLayout = new CardLayout();
	private int flag = -1; //0代表选择关卡 1代表开始游戏 -1代表退出游戏
	
	
	public GameJFrame() {
		// TODO 自动生成的构造函数存根
		init();
	}	
	
	public void init() {
		this.setSize(GameX,GameY);
		this.setTitle("飞机大战");
		this.setLocationRelativeTo(null); //居中显示
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //设置退出并关闭
		this.setFocusable(true);
		
		setjPanel();
	}
	//窗体布局：存档、读档
	public void addButton() {
//		this.setLayout(manager); //布局格式，可以添加各种控件
	}
	
	//启动方法	
	public void start() {		
		if(jPanel != null) {
			this.add(jPanel);
			this.add(ljPanel);
			Thread th = new Thread(ljPanel);
			th.start();
			this.add(ajPanel);
			addButton(ajPanel, "开始游戏",615,50);		
			addButton(ajPanel, "选择关卡",615,130);
			addButton(ajPanel, "退出游戏",615,210);			
		}
		if(keyListener != null) {
			this.addKeyListener(keyListener);
		}
		if(thread != null) {
			thread.start(); //启动线程
		}
		this.setVisible(true);
		
		if(this.jPanel instanceof Runnable) {
//			如果jPanel是runnable的子类实体对象
			new Thread((Runnable)this.jPanel).start();
		}
	}

	/**set注入：通过set方法将配置文件中的数据赋值为类的属性
	 * 构造注入：需要配合构造方法
	 * spring 中ioc进行对象的自动生成、管理
	 * @param jPanel
	 */
	public void setjPanel() {
		//位置和布局
		ajPanel.setBounds(0, 0, GameX, GameY);
		ajPanel.setBackground(Color.white);
		ajPanel.setLayout(null); //给全局面板添加布局
		ljPanel.setLayout(null);
		ljPanel.setBounds(615, 650, 160, 100);
		jPanel.setBounds(0, 0, GameX-200, GameY);
		jPanel.setLayout(cardLayout); //显示面板布局
		GameLevel gl = new GameLevel();
		GameMainJPanel gmj = new GameMainJPanel();
		Thread t1 = new Thread(gmj);
		t1.start();
		GameBegin gb = new GameBegin();
		jPanel.add(gb, "gb");
		jPanel.add(gl, "gl");
		jPanel.add(gmj,"gmj");			
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
	
	public void changeContentPane() {
	}
	
	public void addButton(JPanel jPanel1, String str, int x, int y) {
		myButton jButton = new myButton(str);
		jButton.setBounds(x, y, 160, 50);	
		jButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				if(str.equals("开始游戏")) {
					cardLayout.show(jPanel, "gmj");					
				}else if (str.equals("选择关卡")) {
					cardLayout.show(jPanel, "gl");
				}else if(str.equals("退出游戏")) {
					cardLayout.show(jPanel, "gb");
				}
				requestFocus();
			}
		});
		jPanel1.add(jButton);
		
	}

}
