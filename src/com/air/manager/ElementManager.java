package com.air.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.air.element.ElementObj;
import com.air.manager.GameElement;

public class ElementManager {
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
	}
	
	public void init() {
		gameElements = new HashMap<GameElement, List<ElementObj>>();
		//将每种元素集合放入map中
		for(GameElement ge:GameElement.values()) { //通过循环读取枚举类型 添加集合
			gameElements.put(ge, new ArrayList<ElementObj>());
		}
	}	
}