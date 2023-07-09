package com.air.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.air.element.ElementObj;
import com.air.manager.GameElement;

/**
 * @说明 本类是元素管理器，专门存储所有的元素
 * 同时，提供方法给予视图和控制获取数据
 * @author 13922
 * @问题1：存储所有数据，如何存放？ list map set 3大集合
 * @问题2：管理器是视图和控制要访问，管理器就必须只有一个，单例模式
 *
 */

public class ElementManager {
	/** 
	 * 枚举类型，当作map的key用来区分不一样的资源，用于获取资源
	 * 
	 * List中元素的泛型应该是元素基类
	 * 所有元素都可以放到map集合中，显示模块只需要获取到这个map
	 * 显示界面所有元素（调用基类）可以用showElement()
	 */
	private Map<GameElement, List<ElementObj>> gameElements;

	public Map<GameElement, List<ElementObj>> getGameElements() {
		return gameElements;
	}	
//	添加元素（多半由加载器调用）
	public void addElement(ElementObj obj,GameElement ge) {
		gameElements.get(ge).add(obj);  //添加对象到集合中，按key值进行存储
	}
//	依据key返回list集合，取出某一类元素
	public List<ElementObj> getElementsByKey(GameElement ge){
		return gameElements.get(ge);
	}
	
	/**
	 * 单例模式：内存中有且只有一个实例
	 * 分两种：
	 * ①饿汉模式----启动就自动加载实例
	 * ②饱汉模式----需要时才加载实例
	 * 
	 * 编写方式：
	 * 	1.需要一个静态的属性 定义一个常量
	 *  2.提供一个静态的方法 返回这个实例
	 *   3.一般为防止其他人自己使用，构造方法私有化
	 */
	
	//饱汉
	private static ElementManager EM = null; //引用
	//synchronized线程锁--->保证本方法执行中只有一个线程
	public static synchronized ElementManager getManager() {
		if(EM==null) {
			EM = new ElementManager();
		}
		return EM;
	}
	
	private ElementManager() { //私有化构造方法  构造方法不能继承
		init();
		/**
		 * 本方法是为将来可能出现的功能拓展，可以用重写init方法实现
		 */
	}
	
	public void init() {
		gameElements = new HashMap<GameElement, List<ElementObj>>();
		//将每种元素集合放入map中
		for(GameElement ge:GameElement.values()) { //通过循环读取枚举类型 添加集合
			gameElements.put(ge, new ArrayList<ElementObj>());
		}
	}
	
//	static { //饿汉实例化 静态语句块是在类被加载的时候直接执行
//		EM = new ElementManager(); //只会执行一次
//	}
	
}
