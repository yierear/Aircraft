package com.air.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import javax.swing.Box;
import javax.swing.ImageIcon;

import com.air.element.Boss;
import com.air.element.ElementObj;
import com.air.element.Explode;
import com.air.element.Next;
import com.air.manager.GameLoad;
/**
 * @说明  加载器(工具：用户读取配置文件的工具)工具类,大多提供的是 static方法
 * @author ch'ri's't'ye'e'e
 *
 */

public class GameLoad {
//	得到资源管理器
	private static ElementManager em=ElementManager.getManager();
	
//	图片集合  使用map来进行存储     枚举类型配合移动(扩展)
	public static Map<String,ImageIcon> imgMap = new HashMap<>();
	
//	public static Map<String,List<ImageIcon>> imgMaps;
//	用户读取文件的类
	private static Properties pro =new Properties();	
	
	/**
	 * @说明 地图加载方法
	 * */
	public static void MapLoad(int mapid) {
		loadObj();
		String mapString="0,0,600,1000,"+mapid+"bg";//x,y,icon
//		System.out.println(mapString);
		ElementObj mapobj=getObj("map"); 
//		System.out.println(mapobj);
		ElementObj map = mapobj.createElement(mapString);
//		解耦,降低代码和代码之间的耦合度 可以直接通过 接口或者是抽象父类就可以获取到实体对象
		em.addElement(map, GameElement.MAPS);
		
		
//		解耦,降低代码和代码之间的耦合度 可以直接通过 接口或者是抽象父类就可以获取到实体对象

	}

	/**
	 *@说明 加载图片代码
	 *加载图片 代码和图片之间差 一个 路径问题 
	 */
	public static void loadImg() {//可以带参数，因为不同的关也可能需要不一样的图片资源
		String texturl="com/air/text/GameData.pro";//文件的命名可以更加有规律
		ClassLoader classLoader = GameLoad.class.getClassLoader();
		InputStream texts = classLoader.getResourceAsStream(texturl);
//		imgMap用于存放数据
		pro.clear();
		try {
//			System.out.println(texts);
			pro.load(texts);
			Set<Object> set = pro.keySet();//是一个set集合
			for(Object o:set) {
				String url=pro.getProperty(o.toString());
				imgMap.put(o.toString(), new ImageIcon(url));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * 加载敌人
	 * */
	public static void loadNpc(int mapId) {
		loadObj();
		Random random=new Random();
		int x,y,num;
		y = -random.nextInt(100);
		String enemyStr="";
		
		switch(mapId) {
		default:
			num = random.nextInt(2) + 1;// 敌机图片编号
			switch(num) {
			case 2:
				x = -random.nextInt(600);break;
			default:
				x = random.nextInt(600);break;
			}
			
			break;
		case 2:
			num = random.nextInt(3) + 1;// 敌机图片编号
			switch(num) {
			case 2:
			case 4:
				x = -random.nextInt(600);break;
			case 3:
				x = random.nextInt(1200);break;
			default:
				x = random.nextInt(600);break;
			}
			
			break;
		case 3:
			num = random.nextInt(4) + 1;// 敌机图片编号
			switch(num) {
			case 2:
			case 4:
				x = -random.nextInt(600);break;
			case 3:
			case 5:
				x = random.nextInt(1200);break;
			default:
				x = random.nextInt(600);break;
			}
			
			break;
		case 4:
			num = random.nextInt(5) + 1;// 敌机图片编号
			switch(num) {
			case 2:
			case 6:
				x = -random.nextInt(600);break;
			case 3:
			case 5:
				x = random.nextInt(1200);break;
			default:
				x = random.nextInt(600);break;
			}
			
			break;
		case 5:
			num = random.nextInt(6) + 1;// 敌机图片编号
			switch(num) {
			case 6:
			case 4:
				x = -random.nextInt(600);break;
			case 7:
			case 5:
				x = random.nextInt(1200);break;
			default:
				x = random.nextInt(600);break;
			}
			
			break;
		case 6:
			num = random.nextInt(7) + 1;// 敌机图片编号
			switch(num) {
			case 9:
			case 4:
				x = -random.nextInt(600);break;
			case 3:
			case 8:
				x = random.nextInt(1200);break;
			default:
				x = random.nextInt(600);break;
			}
			break;
		}
		enemyStr = x + "," + y + ",60,60," + num + "enemy";
//		Explode explode = new Explode(0,0);
//		explode.setX(x);
//		explode.setY(y);
//		explode.showElement(g);
		//创建普通敌机
		ElementObj enemyobj = getObj("enemy");
		ElementObj enemy = enemyobj.createElement(enemyStr);
		em.addElement(enemy, GameElement.ENEMY);		
	}
	
	/**
	 * 加载BOSS
	 */
	public static void loadBoss(int mapid) {
		loadObj();
		String bossStr="";
		bossStr= "75,-500,450,300,"+mapid+"boss";
		ElementObj boss = new Boss().createElement(bossStr);
		em.addElement(boss, GameElement.BOSS);
	}
	
	/**
	 * 加载道具
	 * */
	public static void loadProp(int mapid) {
		loadObj();
		Random random = new Random();
		int propx,propy,num;
		for (int i = 0; i < mapid*20; i++) {//道具数等于关卡*15
			propy=-800-random.nextInt(3000);
			propx=random.nextInt(600);			
			num=random.nextInt(4)+1;//道具 1~4
			String propStr=propx+","+propy+",30,30,"+num+"prop";
			ElementObj propobj=getObj("prop");
			ElementObj prop = propobj.createElement(propStr);
			em.addElement(prop, GameElement.PROP);
		}		
	}
	
	
	/**
	 * 加载玩家
	 * */
	public static void loadPlay() {
		loadObj();
		String playStr="200,500,up";
//		String playStr="200,500,20,20,up";//x,y,图片
		ElementObj playobj=getObj("play");  
		ElementObj play = playobj.createElement(playStr);
//		解耦,降低代码和代码之间的耦合度 可以直接通过 接口或者是抽象父类就可以获取到实体对象
		em.addElement(play, GameElement.PLAY);
		
	}
	private static Map<String,Class<?>> objMap=new HashMap<>();
	
	public static ElementObj getObj(String str) {	
//		System.out.println(str);
		try {
			Class<?> class1=objMap.get(str);
//			System.out.println(class1);
			Object newInstance;
			newInstance = class1.newInstance();
			if (newInstance instanceof ElementObj) {
				return (ElementObj)newInstance;}
		} catch (InstantiationException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public static void loadObj() {
		String texturl="com/air/text/obj.pro";//文件的命名可以更加有规律
		ClassLoader classLoader = GameLoad.class.getClassLoader();
		InputStream texts = classLoader.getResourceAsStream(texturl);
		pro.clear();
		try {
			pro.load(texts);
			Set<Object> set = pro.keySet();//是一个set集合
			for(Object o:set) {
				String classUrl=pro.getProperty(o.toString());
//				使用反射的方式直接将 类进行获取
				Class<?> forName = Class.forName(classUrl);
				objMap.put(o.toString(), forName);
			}	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public static void next(String type) {
		em.addElement(new Next().createElement(type), GameElement.NEXT);
	}
	public static void main(String[] args) {
//		loadNpc(1);
//		loadProp(1);
	}
}
