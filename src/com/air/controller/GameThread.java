package com.air.controller;

import java.util.List;
import java.util.Map;
import com.air.element.ElementObj;
import com.air.manager.ElementManager;
import com.air.manager.GameElement;
import com.air.manager.GameLoad;
import com.air.show.GameLevel;

/**
 * @说明 游戏的主线程，用于控制游戏加载，游戏关卡，游戏运行时自动化
 * 		游戏判定
 * @author 墨洋
 * @继承 使用继承的方式实现多线程（一般建议使用接口实现）
 */
public class GameThread extends Thread{
	private static int score = 0;	
	private ElementManager em;
	
	private static String propType = null;
//	private String bg="bg1";
	public GameThread() {
		em=ElementManager.getManager();
	}
	
	@Override
	public void run() {//游戏的run方法 主线程
		while (true) {
//		游戏开始前 读进度条，加载游戏资源（场景资源）
			gameLoad();
//		游戏进行时 游戏过程中
			gameRun();
//		游戏场景结束 游戏资源回收（场景资源）
			gameOver();
			
			try {
				sleep(50);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}

	/**
	 * 游戏的加载
	 */
	private void gameLoad() {

		GameLoad.loadImg();//加载图片
		GameLoad.MapLoad(GameLevel.getLevel());//传等级
//		加载主角
		GameLoad.loadPlay();//可带参数，单机还是2人		
//		加载敌人NPC
		GameLoad.loadNpc(GameLevel.getLevel());
//		加载道具
		GameLoad.loadProp(GameLevel.getLevel());
	}
	/**
	 * 游戏进行时
	 * 1.自动化玩家的移动 碰撞 死亡
	 * 2.新元素的增加（NPC死亡后出现道具）
	 * 3.游戏暂停。。。
	 * 先实现主角的移动
	 */
	private int gameTime=0;
	private void gameRun() {
		while (!GameLevel.flag) {//true可以变为变量，用于控制关卡结束等
			Map<GameElement, List<ElementObj>> all = em.getGameElements();
			List<ElementObj> enemys = em.getElementsByKey(GameElement.ENEMY);
			List<ElementObj> fires = em.getElementsByKey(GameElement.PLAYFILE);
			List<ElementObj> enemyfires = em.getElementsByKey(GameElement.ENEMYFILE);
			List<ElementObj> plays = em.getElementsByKey(GameElement.PLAY);
			List<ElementObj> props = em.getElementsByKey(GameElement.PROP);
			moveAndUpdate(all, gameTime);//	游戏元素自动化方法
			
//			加载敌人NPC
//			if(!plays.isEmpty() && gameTime%10000==0) {
//				GameLoad.loadNpc(GameLevel.getLevel());
//			}
			
			ElementPK(enemys,fires,0);//敌人和玩家子弹
			ElementPK(plays, props,1);//玩家和道具
			ElementPK(plays, enemyfires,0);//玩家和敌人子弹	
			
			try {
				sleep(30);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			gameTime++;//唯一的时间控制
		}
	}
	
	private void ElementPK(List<ElementObj> listA,List<ElementObj> listB,int flag) {
		for (int i = 0; i < listA.size(); i++) {
			ElementObj character=listA.get(i);
			for (int j = 0; j < listB.size(); j++) {
				ElementObj thing=listB.get(j);				
				if (character.pk(thing)) {
					thing.setLive(false);
					if (flag==1) {//子弹和敌人&玩家
						if (character.getHp()<=0) {
							character.setLive(false);	//碰到子弹 血量无 人物死亡					
						}
						else {
							character.setLive(true);//还有血量 生存
						}
					}
					else if (flag==0) {//道具和玩家
						character.setLive(true);//人物生存
					}
				}			
					break;
				}
			}
		}

//	游戏元素自动化方法
	public void moveAndUpdate(Map<GameElement, List<ElementObj>> all,int gameTime) {
//		GameElement.values();//隐藏方法 返回值是一个数组
		for (GameElement ge : GameElement.values()) {
			List<ElementObj> list = all.get(ge);
//			for (int i = 0; i < list.size(); i++) {
			for (int i = list.size()-1; i >= 0; i--) {
				ElementObj obj=list.get(i);//读取为基类
				if (!obj.isLive()) {//如果死亡
//					list.remove(i--);
//					启动一个死亡方法（死亡动画，掉装备）
					obj.die();
					list.remove(i);
					continue;
				}
				obj.model(gameTime);
			}
		}
	}
	
	/**
	 * 游戏分数计算
	 */
	public static int getScore() {
		return score;
	}
	
	/**
	 * 游戏切换关卡
	 */
	private void gameOver() {
//		关卡递增
		if(!GameLevel.flag)
			GameLevel.setLevel(GameLevel.getLevel()+1);
		GameLevel.flag = false;
//		资源回收
		Map<GameElement,List<ElementObj>> all = em.getGameElements();
		for(GameElement ge: GameElement.values()) {
			List<ElementObj> list = all.get(ge);
			for (int i = list.size()-1; i >=0; i--) {
				list.remove(i);
			}
		}		
	}

	public static String getPropType() {
		return propType;
	}
	
	public static void setPropType(String propType) {
		GameThread.propType = propType;
	}	
	
	
}
