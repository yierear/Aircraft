package com.air.show;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import com.air.controller.GameThread;

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
			addButton(ajPanel, "游戏说明", 615,130);
			addButton(ajPanel, "选择关卡",615,210);
			addButton(ajPanel, "退出游戏",615,290);			
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
					GameLevel.flag = false;
					cardLayout.show(jPanel, "gmj");	
//					thread = new Thread(new GameThread());
//					thread.start();
//					synchronized (thread) {
//						System.out.println(GameLevel.flag);
//						if(GameLevel.flag)
//							thread.re;
//						else
//							thread.notify();
//					}			
//					if(thread != null) {
//						try {
//							thread.start(); //点击开始游戏后再启动线程
//						} catch (Exception e1) {
//							// TODO 自动生成的 catch 块
//							e1.printStackTrace();
//						}
//					}
				} else if (str.equals("选择关卡")) {
					cardLayout.show(jPanel, "gl");	
				}else if(str.equals("退出游戏")) {
					cardLayout.show(jPanel, "gb");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
					System.exit(0);
				}else if (str.equals("游戏说明")) {
					explain();
				}
				requestFocus();
			}
		});
		jPanel1.add(jButton);
		
	}
	
	private void explain() {
//		面板
		JFrame jFrame = new JFrame();
		jFrame.setBounds(1150,100,300,500);
		jFrame.setTitle("游戏说明");
		jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //设置退出并关闭
		jFrame.setVisible(true);
//		窗体
		JPanel jPanel = new JPanel();
		jPanel.setBounds(0, 0, 300, 500);
		jPanel.setBackground(new Color(253, 209, 73));
		jFrame.add(jPanel);
//		滚动
		JScrollPane jScrollPane = new JScrollPane(
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		jScrollPane.setViewportView(jPanel);
		jPanel.setPreferredSize(new Dimension(200,1120));
		jPanel.revalidate();
		jFrame.add(jScrollPane);		
//		标题
		JLabel jLabel = new JLabel("游戏说明");
		Font font = new Font("华文楷体", Font.BOLD, 50);
		jLabel.setBounds(140, 10, 40, 50);
		jLabel.setFont(font);
		jPanel.add(jLabel);
//		具体说明
		//按钮说明
		Font font1 = new Font("华文楷体", Font.PLAIN, 15);
		String string = "1.按钮说明：\n"
				+ "①“开始游戏”：点击可以直接开始游戏，系统将根据得分自动进入下一关\n"
				+ "②“选择关卡”：点击可以选择游戏关卡，点击此按钮后再次点击开始游戏即可进入所选关卡\n"
				+ "③“退出游戏”：点击1s后将退出游戏";
		JTextArea jArea = new JTextArea(string,5,5);
		jArea.setBounds(20, 80, 260, 450);
		jArea.setFont(font1);
		jArea.setLineWrap(true);
		jArea.setBackground(new Color(251,248,211));
		jPanel.add(jArea);
		//控制说明
		String string1 = "2.控制说明：\n"
				+ "①按上下左右键进行主角的控制\n"
				+ "②按空格键进行子弹和主角皮肤的变化";
		JTextArea jArea1 = new JTextArea(string1,4,5);
		jArea1.setBounds(20, 680, 260, 450);
		jArea1.setFont(font1);
		jArea1.setLineWrap(true);
		jArea1.setBackground(new Color(251,248,211));
		jPanel.add(jArea1);
		//关卡说明
		String string2 = "3.关卡说明：\n"
				+ "①游戏的共有6关\n"
				+ "②游戏失败后会自动跳转至第一关\n"
				+ "③可以自由选择关卡";
		JTextArea jArea2 = new JTextArea(string2,4,5);
		jArea2.setBounds(20, 1280, 260, 450);
		jArea2.setFont(font1);
		jArea2.setLineWrap(true);
		jArea2.setBackground(new Color(251,248,211));
		jPanel.add(jArea2);		
		//道具说明
		String string3 = "4.道具说明：\n"
				+ "①血包：玩家获得一个血包可回血10点\n"
				+ "②强化包：玩家获得一个强化包可以增加子弹攻击速度\n"
				+ "③加速包：玩家获得一个加速包可以提升移动速度\n"
				+ "④炸弹：玩家触碰到一个炸弹会掉20点血";
		JTextArea jArea3 = new JTextArea(string3,5,5);
		jArea3.setBounds(20, 1880, 260, 450);
		jArea3.setFont(font1);
		jArea3.setLineWrap(true);
		jArea3.setBackground(new Color(251,248,211));
		jPanel.add(jArea3);
		
		//飞机＆子弹形态一览
		String string4 = "5.飞机形态及子弹形态说明：\n"
				+"①初始形态：灰色飞机+紫色子弹。攻击力：1\n"
				+"②第二形态：绿色飞机+绿色子弹。攻击力：2\n"
				+"③第三形态：黑色飞机+红色子弹。攻击力：3\n"
				+"④第四形态：红白色飞机+橙色子弹。攻击力：4\n"
				+"⑥第五形态：蓝色飞机+蓝色子弹。攻击力：5\n"
				+"tips:按下空格键即可随机切换形态";
		JTextArea jArea4 = new JTextArea(string4,10,4);
		jArea4.setBounds(20, 2480, 260, 450);
		jArea4.setFont(font1);
		jArea4.setLineWrap(true);
		jArea4.setBackground(new Color(251,248,211));
		jPanel.add(jArea4);
		

		//敌机＆boss
		String string5 = "6.敌机的相关说明：\n"
				+ "①普通敌机：\n"
				+"初级敌机：攻击力：2	 血量：1\n"
				+"二级敌机：攻击力：2	 血量：4\n"
				+"三级敌机：攻击力：4	 血量：8\n"
				+"四级敌机：攻击力：6	 血量：16\n"
				+"五级敌机：攻击力：8	 血量：24\n"
				+"六级敌机：攻击力：10	 血量：32\n"
				+"终级敌机：攻击力：10	 血量：40\n"
				+ "②BOSS:\n"
				+"初级boss：攻击力：8	 血量：50\n"
				+"二级boss：攻击力：8	 血量：100\n"
				+"三级boss：攻击力：16	 血量：150\n"
				+"四级boss：攻击力：24	 血量：200\n"
				+"五级boss：攻击力：32	 血量：250\n"
				+"终级boss：攻击力：40	 血量：300\n";
		JTextArea jArea5 = new JTextArea(string5,25,5);
		jArea5.setBounds(20, 3080, 260, 450);
		jArea5.setFont(font1);
		jArea5.setLineWrap(true);
		jArea5.setBackground(new Color(251,248,211));
		jPanel.add(jArea5);	
		
}		
}
